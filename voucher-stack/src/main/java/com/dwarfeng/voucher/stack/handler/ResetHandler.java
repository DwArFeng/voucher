package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置检查。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetCheck() throws HandlerException;
}
