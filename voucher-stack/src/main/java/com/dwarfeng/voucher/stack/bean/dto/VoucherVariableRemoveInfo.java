package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证变量移除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = 3073448056079137983L;
    
    private LongIdKey voucherKey;
    private String variableId;

    public VoucherVariableRemoveInfo() {
    }

    public VoucherVariableRemoveInfo(LongIdKey voucherKey, String variableId) {
        this.voucherKey = voucherKey;
        this.variableId = variableId;
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

    @Override
    public String toString() {
        return "VoucherVariableRemoveInfo{" +
                "voucherKey=" + voucherKey +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
