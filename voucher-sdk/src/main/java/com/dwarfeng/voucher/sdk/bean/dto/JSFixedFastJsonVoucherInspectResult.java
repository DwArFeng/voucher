package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.stack.bean.dto.VoucherInspectResult;

import java.util.Objects;

/**
 * JSFixed FastJson 凭证查看结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonVoucherInspectResult implements Dto {

    private static final long serialVersionUID = 8854114559864732589L;

    public static JSFixedFastJsonVoucherInspectResult of(VoucherInspectResult voucherInspectResult) {
        if (Objects.isNull(voucherInspectResult)) {
            return null;
        } else {
            return new JSFixedFastJsonVoucherInspectResult(
                    JSFixedFastJsonLongIdKey.of(voucherInspectResult.getVoucherKey()),
                    voucherInspectResult.getContent(),
                    voucherInspectResult.getRemark()
            );
        }
    }

    @JSONField(name = "voucher_key", ordinal = 1)
    private JSFixedFastJsonLongIdKey voucherKey;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonVoucherInspectResult() {
    }

    public JSFixedFastJsonVoucherInspectResult(JSFixedFastJsonLongIdKey voucherKey, String content, String remark) {
        this.voucherKey = voucherKey;
        this.content = content;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(JSFixedFastJsonLongIdKey voucherKey) {
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
        return "JSFixedFastJsonVoucherInspectResult{" +
                "voucherKey=" + voucherKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
