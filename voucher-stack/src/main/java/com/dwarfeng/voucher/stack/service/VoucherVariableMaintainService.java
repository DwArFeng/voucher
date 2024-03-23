package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;

/**
 * 凭证变量维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherVariableMaintainService extends BatchCrudService<VoucherVariableKey, VoucherVariable>,
        EntireLookupService<VoucherVariable>, PresetLookupService<VoucherVariable> {

    String CHILD_FOR_VOUCHER = "child_for_voucher";
    String CHILD_FOR_VOUCHER_VARIABLE_ID_ASC = "child_for_voucher_variable_id_asc";
}
