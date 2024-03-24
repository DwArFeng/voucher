package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 凭证创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCreateInfo implements Dto {

    private static final long serialVersionUID = 6070297113414551516L;

    private StringIdKey categoryKey;
    private LongIdKey voucherKey;
    private String content;
    private String remark;

    public VoucherCreateInfo() {
    }

    public VoucherCreateInfo(StringIdKey categoryKey, LongIdKey voucherKey, String content, String remark) {
        this.categoryKey = categoryKey;
        this.voucherKey = voucherKey;
        this.content = content;
        this.remark = remark;
    }

    public StringIdKey getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(StringIdKey categoryKey) {
        this.categoryKey = categoryKey;
    }

    public LongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(LongIdKey voucherKey) {
        this.voucherKey = voucherKey;
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

    @Override
    public String toString() {
        return "VoucherCreateInfo{" +
                "categoryKey=" + categoryKey +
                ", voucherKey=" + voucherKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
