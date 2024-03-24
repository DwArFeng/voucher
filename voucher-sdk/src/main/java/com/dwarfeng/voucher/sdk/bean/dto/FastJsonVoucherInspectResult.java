package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.stack.bean.dto.VoucherInspectResult;

import java.util.Objects;

/**
 * FastJson 凭证查看结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVoucherInspectResult implements Dto {

    private static final long serialVersionUID = -2931928326275214145L;

    public static FastJsonVoucherInspectResult of(VoucherInspectResult voucherInspectResult) {
        if (Objects.isNull(voucherInspectResult)) {
            return null;
        } else {
            return new FastJsonVoucherInspectResult(
                    FastJsonLongIdKey.of(voucherInspectResult.getVoucherKey()),
                    voucherInspectResult.getContent(),
                    voucherInspectResult.getRemark()
            );
        }
    }

    @JSONField(name = "voucher_key", ordinal = 1)
    private FastJsonLongIdKey voucherKey;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonVoucherInspectResult() {
    }

    public FastJsonVoucherInspectResult(FastJsonLongIdKey voucherKey, String content, String remark) {
        this.voucherKey = voucherKey;
        this.content = content;
        this.remark = remark;
    }

    public FastJsonLongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(FastJsonLongIdKey voucherKey) {
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
        return "FastJsonVoucherInspectResult{" +
                "voucherKey=" + voucherKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
