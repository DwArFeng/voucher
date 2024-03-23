package com.dwarfeng.voucher.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.voucher.stack.bean.entity.CheckerInfo;

/**
 * 检查器信息数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerInfoDao extends BatchBaseDao<StringIdKey, CheckerInfo>, EntireLookupDao<CheckerInfo>,
        PresetLookupDao<CheckerInfo> {
}
