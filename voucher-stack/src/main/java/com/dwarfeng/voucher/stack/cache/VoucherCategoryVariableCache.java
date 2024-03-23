package com.dwarfeng.voucher.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;

/**
 * 凭证类型变量缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryVariableCache extends BatchBaseCache<VoucherCategoryVariableKey,
        VoucherCategoryVariable> {
}
