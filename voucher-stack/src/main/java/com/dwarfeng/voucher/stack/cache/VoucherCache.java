package com.dwarfeng.voucher.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;

/**
 * 凭证缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherCache extends BatchBaseCache<LongIdKey, Voucher> {
}
