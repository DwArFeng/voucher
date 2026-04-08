package com.dwarfeng.voucher.sdk.bean;

import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.sdk.bean.dto.*;
import com.dwarfeng.voucher.sdk.bean.entity.*;
import com.dwarfeng.voucher.sdk.bean.key.FastJsonVoucherCategoryVariableKey;
import com.dwarfeng.voucher.sdk.bean.key.FastJsonVoucherVariableKey;
import com.dwarfeng.voucher.stack.bean.dto.*;
import com.dwarfeng.voucher.stack.bean.entity.*;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface BeanMapper {

    // region Subgrade Key

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // endregion

    // region Voucher Key

    FastJsonVoucherCategoryVariableKey voucherCategoryVariableKeyToFastJson(
            VoucherCategoryVariableKey voucherCategoryVariableKey
    );

    @InheritInverseConfiguration
    VoucherCategoryVariableKey voucherCategoryVariableKeyFromFastJson(
            FastJsonVoucherCategoryVariableKey fastJsonVoucherCategoryVariableKey
    );

    FastJsonVoucherVariableKey voucherVariableKeyToFastJson(VoucherVariableKey voucherVariableKey);

    @InheritInverseConfiguration
    VoucherVariableKey voucherVariableKeyFromFastJson(FastJsonVoucherVariableKey fastJsonVoucherVariableKey);

    // endregion

    // region Voucher Entity

    FastJsonCheckerInfo checkerInfoToFastJson(CheckerInfo checkerInfo);

    @InheritInverseConfiguration
    CheckerInfo checkerInfoFromFastJson(FastJsonCheckerInfo fastJsonCheckerInfo);

    FastJsonCheckerSupport checkerSupportToFastJson(CheckerSupport checkerSupport);

    @InheritInverseConfiguration
    CheckerSupport checkerSupportFromFastJson(FastJsonCheckerSupport fastJsonCheckerSupport);

    FastJsonVoucher voucherToFastJson(Voucher voucher);

    @InheritInverseConfiguration
    Voucher voucherFromFastJson(FastJsonVoucher fastJsonVoucher);

    FastJsonVoucherCategory voucherCategoryToFastJson(VoucherCategory voucherCategory);

    @InheritInverseConfiguration
    VoucherCategory voucherCategoryFromFastJson(FastJsonVoucherCategory fastJsonVoucherCategory);

    FastJsonVoucherCategoryVariable voucherCategoryVariableToFastJson(VoucherCategoryVariable voucherCategoryVariable);

    @InheritInverseConfiguration
    VoucherCategoryVariable voucherCategoryVariableFromFastJson(
            FastJsonVoucherCategoryVariable fastJsonVoucherCategoryVariable
    );

    FastJsonVoucherVariable voucherVariableToFastJson(VoucherVariable voucherVariable);

    @InheritInverseConfiguration
    VoucherVariable voucherVariableFromFastJson(FastJsonVoucherVariable fastJsonVoucherVariable);

    JSFixedFastJsonVoucher voucherToJSFixedFastJson(Voucher voucher);

    @InheritInverseConfiguration
    Voucher voucherFromJSFixedFastJson(JSFixedFastJsonVoucher jSFixedFastJsonVoucher);

    WebInputCheckerInfo checkerInfoToWebInput(CheckerInfo checkerInfo);

    @InheritInverseConfiguration
    CheckerInfo checkerInfoFromWebInput(WebInputCheckerInfo webInputCheckerInfo);

    WebInputVoucherCategory voucherCategoryToWebInput(VoucherCategory voucherCategory);

    @InheritInverseConfiguration
    VoucherCategory voucherCategoryFromWebInput(WebInputVoucherCategory webInputVoucherCategory);

    // endregion

    // region Voucher DTO

    FastJsonVoucherInspectResult voucherInspectResultToFastJson(VoucherInspectResult voucherInspectResult);

    @InheritInverseConfiguration
    VoucherInspectResult voucherInspectResultFromFastJson(FastJsonVoucherInspectResult fastJsonVoucherInspectResult);

    JSFixedFastJsonVoucherInspectResult voucherInspectResultToJSFixedFastJson(
            VoucherInspectResult voucherInspectResult
    );

    @InheritInverseConfiguration
    VoucherInspectResult voucherInspectResultFromJSFixedFastJson(
            JSFixedFastJsonVoucherInspectResult jSFixedFastJsonVoucherInspectResult
    );

    WebInputVoucherCategoryVariableInspectInfo voucherCategoryVariableInspectInfoToWebInput(
            VoucherCategoryVariableInspectInfo voucherCategoryVariableInspectInfo
    );

    @InheritInverseConfiguration
    VoucherCategoryVariableInspectInfo voucherCategoryVariableInspectInfoFromWebInput(
            WebInputVoucherCategoryVariableInspectInfo webInputVoucherCategoryVariableInspectInfo
    );

    WebInputVoucherCategoryVariablePutInfo voucherCategoryVariablePutInfoToWebInput(
            VoucherCategoryVariablePutInfo voucherCategoryVariablePutInfo
    );

    @InheritInverseConfiguration
    VoucherCategoryVariablePutInfo voucherCategoryVariablePutInfoFromWebInput(
            WebInputVoucherCategoryVariablePutInfo webInputVoucherCategoryVariablePutInfo
    );

    WebInputVoucherCategoryVariableRemoveInfo voucherCategoryVariableRemoveInfoToWebInput(
            VoucherCategoryVariableRemoveInfo voucherCategoryVariableRemoveInfo
    );

    @InheritInverseConfiguration
    VoucherCategoryVariableRemoveInfo voucherCategoryVariableRemoveInfoFromWebInput(
            WebInputVoucherCategoryVariableRemoveInfo webInputVoucherCategoryVariableRemoveInfo
    );

    WebInputVoucherCreateInfo voucherCreateInfoToWebInput(VoucherCreateInfo voucherCreateInfo);

    @InheritInverseConfiguration
    VoucherCreateInfo voucherCreateInfoFromWebInput(WebInputVoucherCreateInfo webInputVoucherCreateInfo);

    WebInputVoucherInspectInfo voucherInspectInfoToWebInput(VoucherInspectInfo voucherInspectInfo);

    @InheritInverseConfiguration
    VoucherInspectInfo voucherInspectInfoFromWebInput(WebInputVoucherInspectInfo webInputVoucherInspectInfo);

    WebInputVoucherInvalidInfo voucherInvalidInfoToWebInput(VoucherInvalidInfo voucherInvalidInfo);

    @InheritInverseConfiguration
    VoucherInvalidInfo voucherInvalidInfoFromWebInput(WebInputVoucherInvalidInfo webInputVoucherInvalidInfo);

    WebInputVoucherRemoveInfo voucherRemoveInfoToWebInput(VoucherRemoveInfo voucherRemoveInfo);

    @InheritInverseConfiguration
    VoucherRemoveInfo voucherRemoveInfoFromWebInput(WebInputVoucherRemoveInfo webInputVoucherRemoveInfo);

    // endregion
}
