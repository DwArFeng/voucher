package com.dwarfeng.voucher.impl.bean;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.impl.bean.entity.*;
import com.dwarfeng.voucher.impl.bean.key.HibernateVoucherCategoryVariableKey;
import com.dwarfeng.voucher.impl.bean.key.HibernateVoucherVariableKey;
import com.dwarfeng.voucher.stack.bean.entity.*;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>impl</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    // -----------------------------------------------------------Voucher Key-----------------------------------------------------------
    HibernateVoucherCategoryVariableKey voucherCategoryVariableKeyToHibernate(
            VoucherCategoryVariableKey voucherCategoryVariableKey
    );

    @InheritInverseConfiguration
    VoucherCategoryVariableKey voucherCategoryVariableKeyFromHibernate(
            HibernateVoucherCategoryVariableKey hibernateVoucherCategoryVariableKey
    );

    HibernateVoucherVariableKey voucherVariableKeyToHibernate(VoucherVariableKey voucherVariableKey);

    @InheritInverseConfiguration
    VoucherVariableKey voucherVariableKeyFromHibernate(HibernateVoucherVariableKey hibernateVoucherVariableKey);

    // -----------------------------------------------------------Voucher Entity-----------------------------------------------------------
    @Mapping(target = "voucherCategory", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    HibernateCheckerInfo checkerInfoToHibernate(CheckerInfo checkerInfo);

    @InheritInverseConfiguration
    CheckerInfo checkerInfoFromHibernate(HibernateCheckerInfo hibernateCheckerInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateCheckerSupport checkerSupportToHibernate(CheckerSupport checkerSupport);

    @InheritInverseConfiguration
    CheckerSupport checkerSupportFromHibernate(HibernateCheckerSupport hibernateCheckerSupport);

    @Mapping(target = "voucherVariables", ignore = true)
    @Mapping(target = "voucherCategory", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "categoryLongId", ignore = true)
    HibernateVoucher voucherToHibernate(Voucher voucher);

    @InheritInverseConfiguration
    Voucher voucherFromHibernate(HibernateVoucher hibernateVoucher);

    @Mapping(target = "vouchers", ignore = true)
    @Mapping(target = "voucherCategoryVariables", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "checkerInfo", ignore = true)
    HibernateVoucherCategory voucherCategoryToHibernate(VoucherCategory voucherCategory);

    @InheritInverseConfiguration
    VoucherCategory voucherCategoryFromHibernate(HibernateVoucherCategory hibernateVoucherCategory);

    @Mapping(target = "voucherCategoryId", ignore = true)
    @Mapping(target = "voucherCategory", ignore = true)
    @Mapping(target = "variableId", ignore = true)
    HibernateVoucherCategoryVariable voucherCategoryVariableToHibernate(
            VoucherCategoryVariable voucherCategoryVariable
    );

    @InheritInverseConfiguration
    VoucherCategoryVariable voucherCategoryVariableFromHibernate(
            HibernateVoucherCategoryVariable hibernateVoucherCategoryVariable
    );

    @Mapping(target = "voucherId", ignore = true)
    @Mapping(target = "voucher", ignore = true)
    @Mapping(target = "variableId", ignore = true)
    HibernateVoucherVariable voucherVariableToHibernate(VoucherVariable voucherVariable);

    @InheritInverseConfiguration
    VoucherVariable voucherVariableFromHibernate(HibernateVoucherVariable hibernateVoucherVariable);
}
