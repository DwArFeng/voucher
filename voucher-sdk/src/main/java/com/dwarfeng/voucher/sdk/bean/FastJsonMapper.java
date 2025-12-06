package com.dwarfeng.voucher.sdk.bean;

import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.sdk.bean.entity.*;
import com.dwarfeng.voucher.sdk.bean.key.FastJsonVoucherCategoryVariableKey;
import com.dwarfeng.voucher.sdk.bean.key.FastJsonVoucherVariableKey;
import com.dwarfeng.voucher.stack.bean.entity.*;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @see BeanMapper
 * @since beta-1.0.0
 * @deprecated 使用 {@link BeanMapper} 代替。
 */
// 基于 MapStruct Processor 生成的实现类还在使用该接口，故忽略相关警告。
@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

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
    VoucherCategoryVariable voucherCategoryVariableFromFastJson(FastJsonVoucherCategoryVariable fastJsonVoucherCategoryVariable);

    FastJsonVoucherVariable voucherVariableToFastJson(VoucherVariable voucherVariable);

    @InheritInverseConfiguration
    VoucherVariable voucherVariableFromFastJson(FastJsonVoucherVariable fastJsonVoucherVariable);
}
