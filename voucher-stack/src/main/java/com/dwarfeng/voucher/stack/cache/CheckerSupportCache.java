package com.dwarfeng.voucher.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.voucher.stack.bean.entity.CheckerSupport;

/**
 * 检查器支持缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerSupportCache extends BatchBaseCache<StringIdKey, CheckerSupport> {
}
