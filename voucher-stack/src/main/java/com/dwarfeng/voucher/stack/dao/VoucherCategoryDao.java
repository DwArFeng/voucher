package com.dwarfeng.voucher.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;

/**
 * 凭证类型数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryDao extends BatchBaseDao<StringIdKey, VoucherCategory>,
        EntireLookupDao<VoucherCategory>, PresetLookupDao<VoucherCategory> {
}
