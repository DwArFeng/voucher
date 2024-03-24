package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.voucher.stack.bean.dto.VoucherVariableInspectInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherVariablePutInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherVariableRemoveInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 凭证变量操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherVariableOperateHandler extends Handler {

    /**
     * 查看凭证变量。
     *
     * <p>
     * 这个方法接收一个 VoucherVariableInspectInfo 对象，然后返回对应的凭证变量。<br>
     * 如果凭证变量不存在，则返回 null。
     *
     * @param inspectInfo 一个包含查看凭证变量所需信息的 VoucherVariableInspectInfo 对象。
     * @return 对应的凭证变量。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    VoucherVariable inspect(VoucherVariableInspectInfo inspectInfo) throws HandlerException;

    /**
     * 设置凭证变量。
     *
     * <p>
     * 这个方法接收一个 VoucherVariablePutInfo 对象，然后设置对应的凭证变量。<br>
     * 如果凭证变量不存在，则创建一个新的凭证变量；如果凭证变量存在，则更新对应的凭证变量。
     *
     * @param putInfo 一个包含放置凭证变量所需信息的 VoucherVariablePutInfo 对象。
     * @throws HandlerException 处理器异常。
     */
    void put(VoucherVariablePutInfo putInfo) throws HandlerException;

    /**
     * 批量设置凭证变量。
     *
     * <p>
     * 这个方法接收一个 VoucherVariablePutInfo 对象列表，然后批量设置对应的凭证变量。<br>
     * 对于列表中的每一个 VoucherVariablePutInfo 对象，
     * 如果凭证变量不存在，则创建一个新的凭证变量；如果凭证变量存在，则更新对应的凭证变量。
     *
     * @param putInfos 一个包含放置凭证变量所需信息的 VoucherVariablePutInfo 对象列表。
     * @throws HandlerException 处理器异常。
     */
    void batchPut(List<VoucherVariablePutInfo> putInfos) throws HandlerException;

    /**
     * 移除凭证变量。
     *
     * <p>
     * 这个方法接收一个 VoucherVariableRemoveInfo 对象，然后移除对应的凭证变量。<br>
     * 如果凭证变量不存在，则什么都不做。
     *
     * @param removeInfo 一个包含移除凭证变量所需信息的 VoucherVariableRemoveInfo 对象。
     * @throws HandlerException 处理器异常。
     */
    void remove(VoucherVariableRemoveInfo removeInfo) throws HandlerException;
}
