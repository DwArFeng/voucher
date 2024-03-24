package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.stack.bean.dto.VoucherInspectInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证查看信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherInspectInfo implements Dto {

    private static final long serialVersionUID = -268911525181348949L;

    public static VoucherInspectInfo toStackBean(WebInputVoucherInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherInspectInfo(
                    WebInputLongIdKey.toStackBean(webInput.getVoucherKey())
            );
        }
    }

    @JSONField(name = "voucher_key")
    @NotNull
    @Valid
    private WebInputLongIdKey voucherKey;

    public WebInputVoucherInspectInfo() {
    }

    public WebInputVoucherInspectInfo(WebInputLongIdKey voucherKey) {
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
        return "WebInputVoucherInspectInfo{" +
                "voucherKey=" + voucherKey +
                '}';
    }
}
