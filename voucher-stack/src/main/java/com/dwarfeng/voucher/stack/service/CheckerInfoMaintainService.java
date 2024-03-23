package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.voucher.stack.bean.entity.CheckerInfo;

/**
 * 检查器信息维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerInfoMaintainService extends BatchCrudService<StringIdKey, CheckerInfo>,
        EntireLookupService<CheckerInfo>, PresetLookupService<CheckerInfo> {
}
