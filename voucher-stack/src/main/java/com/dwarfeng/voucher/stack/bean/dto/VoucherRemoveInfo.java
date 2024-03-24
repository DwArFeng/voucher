package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherRemoveInfo implements Dto {

    private static final long serialVersionUID = -2350235229337634127L;

    private LongIdKey voucherKey;

    public VoucherRemoveInfo() {
    }

    public VoucherRemoveInfo(LongIdKey voucherKey) {
        this.voucherKey = voucherKey;
    }

    public LongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(LongIdKey voucherKey) {
        this.voucherKey = voucherKey;
    }

    @Override
    public String toString() {
        return "VoucherRemoveInfo{" +
                "voucherKey=" + voucherKey +
                '}';
    }
}
