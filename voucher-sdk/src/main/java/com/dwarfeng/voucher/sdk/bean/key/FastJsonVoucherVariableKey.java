package com.dwarfeng.voucher.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;

import java.util.Objects;

/**
 * FastJson 凭证变量主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucherVariableKey implements Key {

    private static final long serialVersionUID = -6012543468564963155L;

    public static FastJsonVoucherVariableKey of(VoucherVariableKey voucherVariableKey) {
        if (Objects.isNull(voucherVariableKey)) {
            return null;
        } else {
            return new FastJsonVoucherVariableKey(
                    voucherVariableKey.getVoucherId(),
                    voucherVariableKey.getVariableId()
            );
        }
    }

    @JSONField(name = "voucher_id", ordinal = 1)
    private Long voucherId;

    @JSONField(name = "variable_id", ordinal = 2)
    private String variableId;

    public FastJsonVoucherVariableKey() {
    }

    public FastJsonVoucherVariableKey(Long voucherId, String variableId) {
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

        FastJsonVoucherVariableKey that = (FastJsonVoucherVariableKey) o;

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
        return "FastJsonVoucherVariableKey{" +
                "voucherId=" + voucherId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
