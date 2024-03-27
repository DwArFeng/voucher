package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.voucher.stack.exception.CheckerException;
import com.dwarfeng.voucher.stack.handler.Checker;

/**
 * 检查器构造器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerMaker {

    /**
     * 返回构造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 构造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的检查器信息生成一个检查器对象。
     *
     * <p>
     * 可以保证传入的检查器信息中的类型是支持的。
     *
     * @param type  检查器类型。
     * @param param 检查器参数。
     * @return 构造的检查器。
     * @throws CheckerException 检查器异常。
     */
    Checker makeChecker(String type, String param) throws CheckerException;
}
