package com.dwarfeng.voucher.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;

/**
 * 凭证类型缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCategoryCache extends BatchBaseCache<StringIdKey, VoucherCategory> {
}
