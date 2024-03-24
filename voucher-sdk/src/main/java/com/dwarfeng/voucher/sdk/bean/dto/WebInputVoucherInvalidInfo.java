package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.stack.bean.dto.VoucherInvalidInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证作废信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherInvalidInfo implements Dto {

    private static final long serialVersionUID = -4143322817322000879L;

    public static VoucherInvalidInfo toStackBean(WebInputVoucherInvalidInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherInvalidInfo(
                    WebInputLongIdKey.toStackBean(webInput.getVoucherKey())
            );
        }
    }

    @JSONField(name = "voucher_key")
    @NotNull
    @Valid
    private WebInputLongIdKey voucherKey;

    public WebInputVoucherInvalidInfo() {
    }

    public WebInputVoucherInvalidInfo(WebInputLongIdKey voucherKey) {
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
        return "WebInputVoucherInvalidInfo{" +
                "voucherKey=" + voucherKey +
                '}';
    }
}
