package com.dwarfeng.voucher.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;

import java.util.Date;
import java.util.Objects;

/**
 * 凭证变量。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherVariable implements Entity<VoucherVariableKey> {

    private static final long serialVersionUID = 9119184636663640423L;

    private VoucherVariableKey key;
    private String value;
    private Date lastUpdatedDate;

    public VoucherVariable() {
    }

    public VoucherVariable(VoucherVariableKey key, String value, Date lastUpdatedDate) {
        this.key = key;
        this.value = value;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public VoucherVariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(VoucherVariableKey key) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherVariable that = (VoucherVariable) o;

        if (!Objects.equals(key, that.key)) return false;
        if (!Objects.equals(value, that.value)) return false;
        return Objects.equals(lastUpdatedDate, that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VoucherVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
