package com.dwarfeng.voucher.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 凭证。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Voucher implements Entity<LongIdKey> {

    private static final long serialVersionUID = -7663035813830172808L;

    private LongIdKey key;
    private StringIdKey categoryKey;
    private String content;
    private String remark;
    private boolean valid;

    public Voucher() {
    }

    public Voucher(LongIdKey key, StringIdKey categoryKey, String content, String remark, boolean valid) {
        this.key = key;
        this.categoryKey = categoryKey;
        this.content = content;
        this.remark = remark;
        this.valid = valid;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public StringIdKey getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(StringIdKey categoryKey) {
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
        return "Voucher{" +
                "key=" + key +
                ", categoryKey=" + categoryKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", valid=" + valid +
                '}';
    }
}
