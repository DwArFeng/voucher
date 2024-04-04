package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送器处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface PushHandler extends Handler {

    /**
     * 检查重置时执行的推送操作。
     */
    void checkReset() throws HandlerException;
}
