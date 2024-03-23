package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;

/**
 * 凭证类型变量维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryVariableMaintainService extends
        BatchCrudService<VoucherCategoryVariableKey, VoucherCategoryVariable>,
        EntireLookupService<VoucherCategoryVariable>, PresetLookupService<VoucherCategoryVariable> {

    String CHILD_FOR_VOUCHER_CATEGORY = "child_for_voucher_category";
    String CHILD_FOR_VOUCHER_CATEGORY_VARIABLE_ID_ASC = "child_for_voucher_category_variable_id_asc";
}
