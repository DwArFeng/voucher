package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.voucher.stack.bean.entity.CheckerSupport;

/**
 * 检查器维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerSupportMaintainService extends BatchCrudService<StringIdKey, CheckerSupport>,
        EntireLookupService<CheckerSupport>, PresetLookupService<CheckerSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置驱动器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
