package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.voucher.stack.bean.dto.*;

/**
 * 凭证操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherOperateHandler extends Handler {

    /**
     * 创建凭证。
     *
     * <p>
     * 这个方法接收一个 VoucherCreateInfo 对象，然后返回创建的凭证的 LongIdKey。
     *
     * <p>
     * <code>createInfo</code> 的 <code>voucherKey</code> 字段为可选值，如果为 <code>null</code>，则表示由系统自动生成；
     * 如果不为 <code>null</code>，则表示使用指定的值。<br>
     * 如果 <code>createInfo</code> 的 <code>voucherKey</code> 字段不为 <code>null</code>，
     * 则需要保证不存在相同主键的凭证，否则会抛出异常。
     *
     * @param createInfo 一个包含创建凭证所需信息的 VoucherCreateInfo 对象。
     * @return 创建的凭证的 LongIdKey。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey create(VoucherCreateInfo createInfo) throws HandlerException;

    /**
     * 查看凭证。
     *
     * <p>
     * 这个方法接收一个 VoucherInspectInfo 对象，然后返回对应的凭证。
     *
     * @param inspectInfo 一个包含查看凭证所需信息的 VoucherInspectInfo 对象。
     * @return 对应的凭证查看结果。
     * @throws HandlerException 处理器异常。
     */
    VoucherInspectResult inspect(VoucherInspectInfo inspectInfo) throws HandlerException;

    /**
     * 作废凭证。
     *
     * <p>
     * 这个方法接收一个 VoucherInvalidInfo 对象，然后作废对应的凭证。
     *
     * @param invalidInfo 一个包含作废凭证所需信息的 VoucherInvalidInfo 对象。
     * @throws HandlerException 处理器异常。
     */
    void invalid(VoucherInvalidInfo invalidInfo) throws HandlerException;

    /**
     * 删除凭证。
     *
     * <p>
     * 这个方法接收一个 VoucherRemoveInfo 对象，然后删除对应的凭证。
     *
     * @param removeInfo 一个包含删除凭证所需信息的 VoucherRemoveInfo 对象。
     * @throws HandlerException 处理器异常。
     */
    void remove(VoucherRemoveInfo removeInfo) throws HandlerException;
}
