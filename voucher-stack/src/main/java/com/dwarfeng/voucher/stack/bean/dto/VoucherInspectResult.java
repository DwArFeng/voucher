package com.dwarfeng.voucher.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 凭证查看结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherInspectResult implements Dto {

    private static final long serialVersionUID = 3214068554819492760L;

    private LongIdKey voucherKey;
    private String content;
    private String remark;

    public VoucherInspectResult() {
    }

    public VoucherInspectResult(LongIdKey voucherKey, String content, String remark) {
        this.voucherKey = voucherKey;
        this.content = content;
        this.remark = remark;
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
        return "VoucherInspectResult{" +
                "voucherKey=" + voucherKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
