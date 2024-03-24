package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证查看信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherInspectInfo implements Dto {

    private static final long serialVersionUID = -8817018265477375210L;

    private LongIdKey voucherKey;

    public VoucherInspectInfo() {
    }

    public VoucherInspectInfo(LongIdKey voucherKey) {
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
        return "VoucherInspectInfo{" +
                "voucherKey=" + voucherKey +
                '}';
    }
}
