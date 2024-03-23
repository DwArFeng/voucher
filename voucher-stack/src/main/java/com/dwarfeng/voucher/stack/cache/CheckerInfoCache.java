package com.dwarfeng.voucher.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.voucher.stack.bean.entity.CheckerInfo;

/**
 * 检查器信息缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerInfoCache extends BatchBaseCache<StringIdKey, CheckerInfo> {
}
