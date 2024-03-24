package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证变量查看信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherVariableInspectInfo implements Dto {

    private static final long serialVersionUID = -4498791885322648815L;
    
    private LongIdKey voucherKey;
    private String variableId;

    public VoucherVariableInspectInfo() {
    }

    public VoucherVariableInspectInfo(LongIdKey voucherKey, String variableId) {
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
        return "VoucherVariableInspectInfo{" +
                "voucherKey=" + voucherKey +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
