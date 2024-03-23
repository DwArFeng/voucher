package com.dwarfeng.voucher.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.voucher.stack.bean.entity.CheckerSupport;

/**
 * 检查器支持数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerSupportDao extends BatchBaseDao<StringIdKey, CheckerSupport>, EntireLookupDao<CheckerSupport>,
        PresetLookupDao<CheckerSupport> {
}
