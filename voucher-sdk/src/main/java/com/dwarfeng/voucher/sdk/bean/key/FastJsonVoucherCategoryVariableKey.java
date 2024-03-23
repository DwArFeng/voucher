package com.dwarfeng.voucher.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;

import java.util.Objects;

/**
 * FastJson 凭证类型变量主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucherCategoryVariableKey implements Key {

    private static final long serialVersionUID = 8207691650506865917L;

    public static FastJsonVoucherCategoryVariableKey of(VoucherCategoryVariableKey voucherCategoryVariableKey) {
        if (Objects.isNull(voucherCategoryVariableKey)) {
            return null;
        } else {
            return new FastJsonVoucherCategoryVariableKey(
                    voucherCategoryVariableKey.getVoucherCategoryId(),
                    voucherCategoryVariableKey.getVariableId()
            );
        }
    }

    @JSONField(name = "voucher_category_id", ordinal = 1)
    private String voucherCategoryId;

    @JSONField(name = "variable_id", ordinal = 2)
    private String variableId;

    public FastJsonVoucherCategoryVariableKey() {
    }

    public FastJsonVoucherCategoryVariableKey(String voucherCategoryId, String variableId) {
        this.voucherCategoryId = voucherCategoryId;
        this.variableId = variableId;
    }

    public String getVoucherCategoryId() {
        return voucherCategoryId;
    }

    public void setVoucherCategoryId(String voucherCategoryId) {
        this.voucherCategoryId = voucherCategoryId;
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

        FastJsonVoucherCategoryVariableKey that = (FastJsonVoucherCategoryVariableKey) o;

        if (!Objects.equals(voucherCategoryId, that.voucherCategoryId))
            return false;
        return Objects.equals(variableId, that.variableId);
    }

    @Override
    public int hashCode() {
        int result = voucherCategoryId != null ? voucherCategoryId.hashCode() : 0;
        result = 31 * result + (variableId != null ? variableId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonVoucherCategoryVariableKey{" +
                "voucherCategoryId='" + voucherCategoryId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
