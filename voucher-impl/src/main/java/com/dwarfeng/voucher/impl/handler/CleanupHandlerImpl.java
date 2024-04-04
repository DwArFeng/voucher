package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.impl.handler.CuratorDistributedLockHandler;
import com.dwarfeng.subgrade.impl.handler.Worker;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.handler.CleanupHandler;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class CleanupHandlerImpl implements CleanupHandler {

    private final CuratorDistributedLockHandler distributedLockHandler;

    private final CleanupProcessor cleanupProcessor;

    public CleanupHandlerImpl(
            CuratorFramework curatorFramework,
            @Value("${curator.latch_path.cleanup.leader_latch}") String leaderLatchPath,
            CleanupWorker worker,
            CleanupProcessor cleanupProcessor
    ) {
        this.cleanupProcessor = cleanupProcessor;
        distributedLockHandler = new CuratorDistributedLockHandler(curatorFramework, leaderLatchPath, worker);
    }

    @BehaviorAnalyse
    @Override
    public boolean isOnline() {
        return distributedLockHandler.isOnline();
    }

    @BehaviorAnalyse
    @Override
    public void online() throws HandlerException {
        distributedLockHandler.online();
    }

    @BehaviorAnalyse
    @Override
    public void offline() throws HandlerException {
        distributedLockHandler.offline();
    }

    @BehaviorAnalyse
    @Override
    public boolean isStarted() {
        return distributedLockHandler.isStarted();
    }

    @BehaviorAnalyse
    @Override
    public void start() throws HandlerException {
        distributedLockHandler.start();
    }

    @BehaviorAnalyse
    @Override
    public void stop() throws HandlerException {
        distributedLockHandler.stop();
    }

    @BehaviorAnalyse
    @Override
    public void cleanup() throws HandlerException {
        try {
            cleanupProcessor.cleanup();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @BehaviorAnalyse
    @Override
    public boolean isLockHolding() {
        return distributedLockHandler.isLockHolding();
    }

    @BehaviorAnalyse
    @Override
    public boolean isWorking() {
        return distributedLockHandler.isWorking();
    }

    @Component
    public static class CleanupWorker implements Worker {

        private final ApplicationContext ctx;

        private final ThreadPoolTaskScheduler scheduler;

        @Value("${cleanup.cron}")
        private String cron;

        private Future<?> cleanupFuture;

        public CleanupWorker(ApplicationContext ctx, ThreadPoolTaskScheduler scheduler) {
            this.ctx = ctx;
            this.scheduler = scheduler;
        }

        @Override
        public void work() {
            CleanupTask cleanupTask = ctx.getBean(CleanupTask.class);
            cleanupFuture = scheduler.schedule(cleanupTask, new CronTrigger(cron));
        }

        @Override
        public void rest() {
            cleanupFuture.cancel(true);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class CleanupTask implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(CleanupTask.class);

        private final CleanupProcessor cleanupProcessor;

        public CleanupTask(CleanupProcessor cleanupProcessor) {
            this.cleanupProcessor = cleanupProcessor;
        }

        @Override
        public void run() {
            try {
                // 执行清理作业。
                cleanupProcessor.cleanup();
            } catch (Exception e) {
                LOGGER.warn("执行清理作业时发送异常, 清理未完成, 异常信息如下: ", e);
            }
        }
    }
}
