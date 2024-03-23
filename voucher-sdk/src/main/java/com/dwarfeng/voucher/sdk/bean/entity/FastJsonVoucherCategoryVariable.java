package com.dwarfeng.voucher.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.sdk.bean.key.FastJsonVoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 凭证类型变量。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucherCategoryVariable implements Bean {

    private static final long serialVersionUID = -6447637993703097867L;

    public static FastJsonVoucherCategoryVariable of(VoucherCategoryVariable voucherCategoryVariable) {
        if (Objects.isNull(voucherCategoryVariable)) {
            return null;
        } else {
            return new FastJsonVoucherCategoryVariable(
                    FastJsonVoucherCategoryVariableKey.of(voucherCategoryVariable.getKey()),
                    voucherCategoryVariable.getValue(),
                    voucherCategoryVariable.getLastUpdatedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVoucherCategoryVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "last_updated_date", ordinal = 3)
    private Date lastUpdatedDate;

    public FastJsonVoucherCategoryVariable() {
    }

    public FastJsonVoucherCategoryVariable(FastJsonVoucherCategoryVariableKey key, String value, Date lastUpdatedDate) {
        this.key = key;
        this.value = value;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public FastJsonVoucherCategoryVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonVoucherCategoryVariableKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public String toString() {
        return "FastJsonVoucherCategoryVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
