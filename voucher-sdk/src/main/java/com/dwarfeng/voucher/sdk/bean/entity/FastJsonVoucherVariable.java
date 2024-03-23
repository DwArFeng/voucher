package com.dwarfeng.voucher.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.sdk.bean.key.FastJsonVoucherVariableKey;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 凭证变量。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucherVariable implements Bean {

    private static final long serialVersionUID = -7201147985749904030L;

    public static FastJsonVoucherVariable of(VoucherVariable voucherVariable) {
        if (Objects.isNull(voucherVariable)) {
            return null;
        } else {
            return new FastJsonVoucherVariable(
                    FastJsonVoucherVariableKey.of(voucherVariable.getKey()),
                    voucherVariable.getValue(),
                    voucherVariable.getLastUpdatedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVoucherVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "last_updated_date", ordinal = 3)
    private Date lastUpdatedDate;

    public FastJsonVoucherVariable() {
    }

    public FastJsonVoucherVariable(FastJsonVoucherVariableKey key, String value, Date lastUpdatedDate) {
        this.key = key;
        this.value = value;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public FastJsonVoucherVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonVoucherVariableKey key) {
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
        return "FastJsonVoucherVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
