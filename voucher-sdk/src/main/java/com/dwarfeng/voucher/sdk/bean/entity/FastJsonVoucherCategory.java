package com.dwarfeng.voucher.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;

import java.util.Objects;

/**
 * FastJson 凭证类型。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucherCategory implements Bean {

    private static final long serialVersionUID = -7590341334463852083L;

    public static FastJsonVoucherCategory of(VoucherCategory voucherCategory) {
        if (Objects.isNull(voucherCategory)) {
            return null;
        } else {
            return new FastJsonVoucherCategory(
                    FastJsonStringIdKey.of(voucherCategory.getKey()),
                    voucherCategory.isEnabled(),
                    voucherCategory.getName(),
                    voucherCategory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;

    @JSONField(name = "name", ordinal = 3)
    private String name;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonVoucherCategory() {
    }

    public FastJsonVoucherCategory(FastJsonStringIdKey key, boolean enabled, String name, String remark) {
        this.key = key;
        this.enabled = enabled;
        this.name = name;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonVoucherCategory{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
