package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证变量设置信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherVariablePutInfo implements Dto {

    private static final long serialVersionUID = 5640605841921636843L;
    
    private LongIdKey voucherKey;
    private String variableId;
    private String value;

    public VoucherVariablePutInfo() {
    }

    public VoucherVariablePutInfo(LongIdKey voucherKey, String variableId, String value) {
        this.voucherKey = voucherKey;
        this.variableId = variableId;
        this.value = value;
    }

    public LongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(LongIdKey voucherKey) {
        this.voucherKey = voucherKey;
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
        return "VoucherVariablePutInfo{" +
                "voucherKey=" + voucherKey +
                ", variableId='" + variableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
