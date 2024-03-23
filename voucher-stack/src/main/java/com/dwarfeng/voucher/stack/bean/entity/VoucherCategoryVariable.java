package com.dwarfeng.voucher.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;

import java.util.Date;

/**
 * 凭证类型变量。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryVariable implements Entity<VoucherCategoryVariableKey> {

    private static final long serialVersionUID = -2151261963229050873L;

    private VoucherCategoryVariableKey key;
    private String value;
    private Date lastUpdatedDate;

    public VoucherCategoryVariable() {
    }

    public VoucherCategoryVariable(VoucherCategoryVariableKey key, String value, Date lastUpdatedDate) {
        this.key = key;
        this.value = value;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public VoucherCategoryVariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(VoucherCategoryVariableKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public String toString() {
        return "VoucherCategoryVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
