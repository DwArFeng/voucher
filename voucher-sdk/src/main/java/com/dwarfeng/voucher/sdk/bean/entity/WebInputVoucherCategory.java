package com.dwarfeng.voucher.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.sdk.util.Constraints;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证类型。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherCategory implements Bean {

    private static final long serialVersionUID = 8629558868272664026L;

    public static VoucherCategory toStackBean(WebInputVoucherCategory webInputVoucherCategory) {
        if (Objects.isNull(webInputVoucherCategory)) {
            return null;
        } else {
            return new VoucherCategory(
                    WebInputStringIdKey.toStackBean(webInputVoucherCategory.getKey()),
                    webInputVoucherCategory.isEnabled(),
                    webInputVoucherCategory.getName(),
                    webInputVoucherCategory.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @NotNull
    @Valid
    private WebInputStringIdKey key;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputVoucherCategory() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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
        return "WebInputVoucherCategory{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
