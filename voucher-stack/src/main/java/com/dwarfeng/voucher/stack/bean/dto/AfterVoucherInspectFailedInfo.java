package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 凭证查看失败后回调信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class AfterVoucherInspectFailedInfo implements Dto {

    private static final long serialVersionUID = 853173604960102275L;

    private StringIdKey voucherCategoryKey;
    private LongIdKey voucherKey;

    public AfterVoucherInspectFailedInfo() {
    }

    public AfterVoucherInspectFailedInfo(StringIdKey voucherCategoryKey, LongIdKey voucherKey) {
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
        return "AfterVoucherInspectFailedInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", voucherKey=" + voucherKey +
                '}';
    }
}
