package com.dwarfeng.voucher.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

public class HibernateVoucherVariableKey implements Key {

    private static final long serialVersionUID = -1813706923991942660L;

    private Long voucherId;
    private String variableId;

    public HibernateVoucherVariableKey() {
    }

    public HibernateVoucherVariableKey(Long voucherId, String variableId) {
        this.voucherId = voucherId;
        this.variableId = variableId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HibernateVoucherVariableKey that = (HibernateVoucherVariableKey) o;

        if (!Objects.equals(voucherId, that.voucherId)) return false;
        return Objects.equals(variableId, that.variableId);
    }

    @Override
    public int hashCode() {
        int result = voucherId != null ? voucherId.hashCode() : 0;
        result = 31 * result + (variableId != null ? variableId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateVoucherVariableKey{" +
                "voucherId=" + voucherId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
