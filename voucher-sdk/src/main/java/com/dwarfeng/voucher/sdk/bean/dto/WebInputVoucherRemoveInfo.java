package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.stack.bean.dto.VoucherRemoveInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherRemoveInfo implements Dto {

    private static final long serialVersionUID = 4637114481117356857L;

    public static VoucherRemoveInfo toStackBean(WebInputVoucherRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getVoucherKey())
            );
        }
    }

    @JSONField(name = "voucher_key")
    @NotNull
    @Valid
    private WebInputLongIdKey voucherKey;

    public WebInputVoucherRemoveInfo() {
    }

    public WebInputVoucherRemoveInfo(WebInputLongIdKey voucherKey) {
        this.voucherKey = voucherKey;
    }

    public WebInputLongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(WebInputLongIdKey voucherKey) {
        this.voucherKey = voucherKey;
    }

    @Override
    public String toString() {
        return "WebInputVoucherRemoveInfo{" +
                "voucherKey=" + voucherKey +
                '}';
    }
}
