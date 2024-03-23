package com.dwarfeng.voucher.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 凭证类型变量主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryVariableKey implements Key {

    private static final long serialVersionUID = -8210781507991394606L;

    private String voucherCategoryId;
    private String variableId;

    public VoucherCategoryVariableKey() {
    }

    public VoucherCategoryVariableKey(String voucherCategoryId, String variableId) {
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

        VoucherCategoryVariableKey that = (VoucherCategoryVariableKey) o;

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
        return "VoucherCategoryVariableKey{" +
                "voucherCategoryId='" + voucherCategoryId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
