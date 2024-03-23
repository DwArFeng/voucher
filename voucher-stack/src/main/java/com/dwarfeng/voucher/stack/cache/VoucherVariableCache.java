package com.dwarfeng.voucher.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;

/**
 * 检查器信息缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VoucherVariableCache extends BatchBaseCache<VoucherVariableKey, VoucherVariable> {
}
