package com.dwarfeng.voucher.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;

/**
 * 检查器信息数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherVariableDao extends BatchBaseDao<VoucherVariableKey, VoucherVariable>,
        EntireLookupDao<VoucherVariable>, PresetLookupDao<VoucherVariable> {
}
