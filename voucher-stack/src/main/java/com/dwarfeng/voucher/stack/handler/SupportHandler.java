package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 支持处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface SupportHandler extends Handler {

    /**
     * 重置检查器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetChecker() throws HandlerException;
}
