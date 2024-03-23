package com.dwarfeng.voucher.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;

/**
 * 凭证数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherDao extends BatchBaseDao<LongIdKey, Voucher>, EntireLookupDao<Voucher>,
        PresetLookupDao<Voucher> {
}
