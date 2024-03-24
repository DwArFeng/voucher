package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.sdk.util.Constraints;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableInspectInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证类型变量查看信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherCategoryVariableInspectInfo implements Dto {

    private static final long serialVersionUID = -8139333152357417119L;

    public static VoucherCategoryVariableInspectInfo toStackBean(WebInputVoucherCategoryVariableInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherCategoryVariableInspectInfo(
                    WebInputStringIdKey.toStackBean(webInput.getVoucherCategoryKey()),
                    webInput.getVariableId()
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

    public WebInputVoucherCategoryVariableInspectInfo() {
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

    @Override
    public String toString() {
        return "WebInputVoucherCategoryVariableInspectInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
