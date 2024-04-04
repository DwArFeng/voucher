package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.voucher.stack.handler.CleanupHandler;
import com.dwarfeng.voucher.stack.service.CleanupQosService;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class CleanupQosServiceImpl implements CleanupQosService {

    private final CleanupHandler cleanupHandler;

    private final ServiceExceptionMapper sem;

    public CleanupQosServiceImpl(CleanupHandler cleanupHandler, ServiceExceptionMapper sem) {
        this.cleanupHandler = cleanupHandler;
        this.sem = sem;
    }

    @PreDestroy
    public void dispose() throws HandlerException {
        cleanupHandler.stop();
        cleanupHandler.offline();
    }

    @Override
    public boolean isOnline() throws ServiceException {
        try {
            return cleanupHandler.isOnline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清理服务是否上线时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void online() throws ServiceException {
        try {
            cleanupHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上线清理服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void offline() throws ServiceException {
        try {
            cleanupHandler.offline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下线清理服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isLockHolding() throws ServiceException {
        try {
            return cleanupHandler.isLockHolding();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清理服务是否正在持有锁时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return cleanupHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清理服务是否启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            cleanupHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("启动清理服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            cleanupHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("停止清理服务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return cleanupHandler.isWorking();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断清理服务是否正在工作时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void cleanup() throws ServiceException {
        try {
            cleanupHandler.cleanup();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("立即执行清理作业时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
