package com.dwarfeng.voucher.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

/**
 * 凭证变量主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherVariableKey implements Key {

    private static final long serialVersionUID = -1724388447148550347L;

    private Long voucherId;
    private String variableId;

    public VoucherVariableKey() {
    }

    public VoucherVariableKey(Long voucherId, String variableId) {
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
    public String toString() {
        return "VoucherVariableKey{" +
                "voucherId=" + voucherId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
