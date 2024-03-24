package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.sdk.util.Constraints;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariablePutInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证类型变量设置信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherCategoryVariablePutInfo implements Dto {

    private static final long serialVersionUID = 2840437658544190282L;

    public static VoucherCategoryVariablePutInfo toStackBean(WebInputVoucherCategoryVariablePutInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherCategoryVariablePutInfo(
                    WebInputStringIdKey.toStackBean(webInput.getVoucherCategoryKey()),
                    webInput.getVariableId(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "voucher_category_key")
    @NotNull
    @Valid
    private WebInputStringIdKey voucherCategoryKey;

    @JSONField(name = "variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_VARIABLE_ID)
    private String variableId;

    @JSONField(name = "value")
    private String value;

    public WebInputVoucherCategoryVariablePutInfo() {
    }

    public WebInputStringIdKey getVoucherCategoryKey() {
        return voucherCategoryKey;
    }

    public void setVoucherCategoryKey(WebInputStringIdKey voucherCategoryKey) {
        this.voucherCategoryKey = voucherCategoryKey;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputVoucherCategoryVariablePutInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", variableId='" + variableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
