package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableInspectInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariablePutInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableRemoveInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;

import javax.annotation.Nullable;

/**
 * 凭证类型变量操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryVariableOperateHandler extends Handler {

    /**
     * 查看凭证类型变量。
     *
     * <p>
     * 这个方法接收一个 VoucherCategoryVariableInspectInfo 对象，然后返回对应的凭证类型变量。<br>
     * 如果凭证类型变量不存在，则返回 null。
     *
     * @param inspectInfo 一个包含查看凭证类型变量所需信息的 VoucherCategoryVariableInspectInfo 对象。
     * @return 对应的凭证类型变量。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    VoucherCategoryVariable inspect(VoucherCategoryVariableInspectInfo inspectInfo) throws HandlerException;

    /**
     * 设置凭证类型变量。
     *
     * <p>
     * 这个方法接收一个 VoucherCategoryVariablePutInfo 对象，然后设置对应的凭证类型变量。<br>
     * 如果凭证类型变量不存在，则创建一个新的凭证类型变量；如果凭证类型变量存在，则更新对应的凭证类型变量。
     *
     * @param putInfo 一个包含放置凭证类型变量所需信息的 VoucherCategoryVariablePutInfo 对象。
     * @throws HandlerException 处理器异常。
     */
    void put(VoucherCategoryVariablePutInfo putInfo) throws HandlerException;

    /**
     * 移除凭证类型变量。
     *
     * <p>
     * 这个方法接收一个 VoucherCategoryVariableRemoveInfo 对象，然后移除对应的凭证类型变量。<br>
     * 如果凭证类型变量不存在，则什么都不做。
     *
     * @param removeInfo 一个包含移除凭证类型变量所需信息的 VoucherCategoryVariableRemoveInfo 对象。
     * @throws HandlerException 处理器异常。
     */
    void remove(VoucherCategoryVariableRemoveInfo removeInfo) throws HandlerException;
}
