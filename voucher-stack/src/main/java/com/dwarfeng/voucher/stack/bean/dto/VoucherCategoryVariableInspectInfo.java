package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 凭证类型变量查看信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryVariableInspectInfo implements Dto {

    private static final long serialVersionUID = 2006645044617191910L;
    
    private StringIdKey voucherCategoryKey;
    private String variableId;

    public VoucherCategoryVariableInspectInfo() {
    }

    public VoucherCategoryVariableInspectInfo(StringIdKey voucherCategoryKey, String variableId) {
        this.voucherCategoryKey = voucherCategoryKey;
        this.variableId = variableId;
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

    @Override
    public String toString() {
        return "VoucherCategoryVariableInspectInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
