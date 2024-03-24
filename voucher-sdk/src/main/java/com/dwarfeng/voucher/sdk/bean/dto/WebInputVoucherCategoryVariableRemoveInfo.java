package com.dwarfeng.voucher.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.voucher.sdk.util.Constraints;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableRemoveInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 凭证类型变量移除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVoucherCategoryVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = 8008064673582870013L;

    public static VoucherCategoryVariableRemoveInfo toStackBean(WebInputVoucherCategoryVariableRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VoucherCategoryVariableRemoveInfo(
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

    public WebInputVoucherCategoryVariableRemoveInfo() {
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
        return "WebInputVoucherCategoryVariableRemoveInfo{" +
                "voucherCategoryKey=" + voucherCategoryKey +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
