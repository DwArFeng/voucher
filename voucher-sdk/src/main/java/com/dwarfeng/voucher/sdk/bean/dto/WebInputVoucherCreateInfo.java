package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.sdk.util.Constraints;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherCreateInfo implements Dto {

    private static final long serialVersionUID = 7561659325677021434L;

    public static VoucherCreateInfo toStackBean(WebInputVoucherCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherCreateInfo(
                    WebInputStringIdKey.toStackBean(webInput.getCategoryKey()),
                    WebInputLongIdKey.toStackBean(webInput.getVoucherKey()),
                    webInput.getContent(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "category_key")
    @NotNull
    @Valid
    private WebInputStringIdKey categoryKey;

    @JSONField(name = "voucher_key")
    @NotNull
    @Valid
    private WebInputLongIdKey voucherKey;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputVoucherCreateInfo() {
    }

    public WebInputVoucherCreateInfo(
            WebInputStringIdKey categoryKey, WebInputLongIdKey voucherKey, String content, String remark
    ) {
        this.categoryKey = categoryKey;
        this.voucherKey = voucherKey;
        this.content = content;
        this.remark = remark;
    }

    public WebInputStringIdKey getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(WebInputStringIdKey categoryKey) {
        this.categoryKey = categoryKey;
    }

    public WebInputLongIdKey getVoucherKey() {
        return voucherKey;
    }

    public void setVoucherKey(WebInputLongIdKey voucherKey) {
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
        return "WebInputVoucherCreateInfo{" +
                "categoryKey=" + categoryKey +
                ", voucherKey=" + voucherKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
