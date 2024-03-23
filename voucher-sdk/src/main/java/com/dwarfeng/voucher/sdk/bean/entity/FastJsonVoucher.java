package com.dwarfeng.voucher.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;

import java.util.Objects;

/**
 * FastJson 凭证。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucher implements Bean {

    private static final long serialVersionUID = -4577111937482295888L;

    public static FastJsonVoucher of(Voucher voucher) {
        if (Objects.isNull(voucher)) {
            return null;
        } else {
            return new FastJsonVoucher(
                    FastJsonLongIdKey.of(voucher.getKey()),
                    FastJsonStringIdKey.of(voucher.getCategoryKey()),
                    voucher.getContent(),
                    voucher.getRemark(),
                    voucher.isValid()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "category_key", ordinal = 2)
    private FastJsonStringIdKey categoryKey;

    @JSONField(name = "content", ordinal = 3)
    private String content;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    @JSONField(name = "valid", ordinal = 5)
    private boolean valid;

    public FastJsonVoucher() {
    }

    public FastJsonVoucher(
            FastJsonLongIdKey key, FastJsonStringIdKey categoryKey, String content, String remark, boolean valid
    ) {
        this.key = key;
        this.categoryKey = categoryKey;
        this.content = content;
        this.remark = remark;
        this.valid = valid;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(FastJsonStringIdKey categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "FastJsonVoucher{" +
                "key=" + key +
                ", categoryKey=" + categoryKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", valid=" + valid +
                '}';
    }
}
