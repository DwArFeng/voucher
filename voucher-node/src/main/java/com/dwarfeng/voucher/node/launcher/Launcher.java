package com.dwarfeng.voucher.node.launcher;

import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.node.handler.LauncherSettingHandler;
import com.dwarfeng.voucher.stack.service.CheckerSupportMaintainService;
import com.dwarfeng.voucher.stack.service.CleanupQosService;
import com.dwarfeng.voucher.stack.service.ResetQosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性执行重置。
            mayResetCheckerSupport(ctx);

            // 根据启动器设置处理器的设置，选择性上线清理服务。
            mayOnlineCleanup(ctx);
            // 根据启动器设置处理器的设置，选择性启动清理服务。
            mayEnableCleanup(ctx);

            // 根据启动器设置处理器的设置，选择启动重置服务。
            mayStartReset(ctx);
        });
    }

    private static void mayResetCheckerSupport(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置进行启动。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 判断是否重置检查器支持。
        if (launcherSettingHandler.isResetCheckerSupport()) {
            LOGGER.info("重置检查器支持...");
            CheckerSupportMaintainService maintainService = ctx.getBean(CheckerSupportMaintainService.class);
            try {
                maintainService.reset();
            } catch (ServiceException e) {
                LOGGER.warn("检查器支持重置失败，异常信息如下", e);
            }
        }
    }

    private static void mayOnlineCleanup(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置进行启动。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理清理处理器的启动选项。
        CleanupQosService cleanupQosService = ctx.getBean(CleanupQosService.class);

        // 清理处理器是否上线清理服务。
        long onlineCleanupDelay = launcherSettingHandler.getOnlineCleanupDelay();
        if (onlineCleanupDelay == 0) {
            LOGGER.info("立即上线清理服务...");
            try {
                cleanupQosService.online();
            } catch (ServiceException e) {
                LOGGER.error("无法上线清理服务，异常原因如下", e);
            }
        } else if (onlineCleanupDelay > 0) {
            LOGGER.info("{} 毫秒后上线清理服务...", onlineCleanupDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("上线清理服务...");
                        try {
                            cleanupQosService.online();
                        } catch (ServiceException e) {
                            LOGGER.error("无法上线清理服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + onlineCleanupDelay)
            );
        }
    }

    private static void mayEnableCleanup(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置进行启动。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理清理处理器的启动选项。
        CleanupQosService cleanupQosService = ctx.getBean(CleanupQosService.class);

        // 清理处理器是否启动清理服务。
        long enableCleanupDelay = launcherSettingHandler.getEnableCleanupDelay();
        if (enableCleanupDelay == 0) {
            LOGGER.info("立即启动清理服务...");
            try {
                cleanupQosService.online();
            } catch (ServiceException e) {
                LOGGER.error("无法启动清理服务，异常原因如下", e);
            }
        } else if (enableCleanupDelay > 0) {
            LOGGER.info("{} 毫秒后启动清理服务...", enableCleanupDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动清理服务...");
                        try {
                            cleanupQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动清理服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + enableCleanupDelay)
            );
        }
    }

    private static void mayStartReset(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置进行启动。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理重置处理器的启动选项。
        ResetQosService resetQosService = ctx.getBean(ResetQosService.class);

        // 重置处理器是否启动重置服务。
        long startResetDelay = launcherSettingHandler.getStartResetDelay();
        if (startResetDelay == 0) {
            LOGGER.info("立即启动重置服务...");
            try {
                resetQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动重置服务，异常原因如下", e);
            }
        } else if (startResetDelay > 0) {
            LOGGER.info("{} 毫秒后启动重置服务...", startResetDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动重置服务...");
                        try {
                            resetQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动重置服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + startResetDelay)
            );
        }
    }
}
