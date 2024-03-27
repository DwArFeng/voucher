package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 检查器处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerHandler extends Handler {

    /**
     * 根据指定的检查器信息构造一个检查器。
     *
     * @param type  检查器类型。
     * @param param 检查器参数。
     * @return 构造的检查器。
     * @throws HandlerException 检查器异常。
     */
    Checker make(String type, String param) throws HandlerException;
}
