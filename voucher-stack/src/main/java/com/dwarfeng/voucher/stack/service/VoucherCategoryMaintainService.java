package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;

/**
 * 凭证类型维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryMaintainService extends BatchCrudService<StringIdKey, VoucherCategory>,
        EntireLookupService<VoucherCategory>, PresetLookupService<VoucherCategory> {

    String ID_LIKE = "id_like";
    String NAME_LIKE = "name_like";
}
