package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 凭证查看成功后回调信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class AfterVoucherInspectSucceedInfo implements Dto {

    private static final long serialVersionUID = -3824411150641008106L;

    private StringIdKey voucherCategoryKey;
    private LongIdKey voucherKey;

    public AfterVoucherInspectSucceedInfo() {
    }

    public AfterVoucherInspectSucceedInfo(StringIdKey voucherCategoryKey, LongIdKey voucherKey) {
        this.voucherCategoryKey = voucherCategoryKey;
        this.voucherKey = voucherKey;
    }

    public StringIdKey getVoucherCategoryKey() {
        return voucherCategoryKey;
    }

    public void setVoucherCategoryKey(StringIdKey voucherCategoryKey) {
        this.voucherCategoryKey = voucherCategoryKey;
    }

    public LongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(LongIdKey voucherKey) {
        this.voucherKey = voucherKey;
    }

    @Override
    public String toString() {
        return "AfterVoucherInspectSucceedInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", voucherKey=" + voucherKey +
                '}';
    }
}
