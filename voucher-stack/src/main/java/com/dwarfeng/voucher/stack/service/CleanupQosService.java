package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 清理 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CleanupQosService extends Service {

    /**
     * 判断清理服务是否上线。
     *
     * @return 是否上线。
     * @throws ServiceException 服务异常。
     */
    boolean isOnline() throws ServiceException;

    /**
     * 上线清理服务。
     *
     * @throws ServiceException 服务异常。
     */
    void online() throws ServiceException;

    /**
     * 下线清理服务。
     *
     * @throws ServiceException 服务异常。
     */
    void offline() throws ServiceException;

    /**
     * 判断清理服务是否正在持有锁。
     *
     * @return 清理服务是否正在持有锁。
     * @throws ServiceException 服务异常。
     */
    boolean isLockHolding() throws ServiceException;

    /**
     * 判断清理服务是否启动。
     *
     * @return 清理服务是否启动。
     * @throws ServiceException 服务异常。
     */
    boolean isStarted() throws ServiceException;

    /**
     * 启动清理服务。
     *
     * @throws ServiceException 服务异常。
     */
    void start() throws ServiceException;

    /**
     * 停止清理服务。
     *
     * @throws ServiceException 服务异常。
     */
    void stop() throws ServiceException;

    /**
     * 判断清理服务是否正在工作。
     *
     * @return 清理服务是否正在工作。
     * @throws ServiceException 服务异常。
     */
    boolean isWorking() throws ServiceException;

    /**
     * 立即执行清理作业。
     *
     * @throws ServiceException 服务异常。
     */
    void cleanup() throws ServiceException;
}
