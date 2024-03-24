package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证作废信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherInvalidInfo implements Dto {

    private static final long serialVersionUID = -7703190381774638875L;

    private LongIdKey voucherKey;

    public VoucherInvalidInfo() {
    }

    public VoucherInvalidInfo(LongIdKey voucherKey) {
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
        return "VoucherInvalidInfo{" +
                "voucherKey=" + voucherKey +
                '}';
    }
}
