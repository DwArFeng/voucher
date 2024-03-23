package com.dwarfeng.voucher.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;

/**
 * 凭证类型变量数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryVariableDao extends BatchBaseDao<VoucherCategoryVariableKey, VoucherCategoryVariable>,
        EntireLookupDao<VoucherCategoryVariable>, PresetLookupDao<VoucherCategoryVariable> {
}
