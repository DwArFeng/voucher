package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.DistributedLockHandler;

/**
 * 清理处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CleanupHandler extends DistributedLockHandler {

    /**
     * 立即执行清理作业。
     *
     * @throws HandlerException 处理器异常。
     */
    void cleanup() throws HandlerException;
}
