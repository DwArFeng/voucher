package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 凭证类型变量设置信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryVariablePutInfo implements Dto {

    private static final long serialVersionUID = 6029452283825533686L;
    
    private StringIdKey voucherCategoryKey;
    private String variableId;
    private String value;

    public VoucherCategoryVariablePutInfo() {
    }

    public VoucherCategoryVariablePutInfo(StringIdKey voucherCategoryKey, String variableId, String value) {
        this.voucherCategoryKey = voucherCategoryKey;
        this.variableId = variableId;
        this.value = value;
    }

    public StringIdKey getVoucherCategoryKey() {
        return voucherCategoryKey;
    }

    public void setVoucherCategoryKey(StringIdKey voucherCategoryKey) {
        this.voucherCategoryKey = voucherCategoryKey;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VoucherCategoryVariablePutInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", variableId='" + variableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
