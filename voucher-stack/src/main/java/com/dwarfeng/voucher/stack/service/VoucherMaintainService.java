package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;

/**
 * 凭证维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherMaintainService extends BatchCrudService<LongIdKey, Voucher>, EntireLookupService<Voucher>,
        PresetLookupService<Voucher> {

    String CHILD_FOR_CATEGORY = "child_for_category";
    String VALID = "valid";
    String INVALID = "invalid";
}
