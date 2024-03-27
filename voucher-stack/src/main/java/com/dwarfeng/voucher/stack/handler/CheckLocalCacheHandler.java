package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;
import com.dwarfeng.voucher.stack.struct.CheckInfo;

/**
 * 检查用本地缓存处理器。
 *
 * <p>
 * 数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。
 *
 * <p>
 * 该处理器线程安全。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckLocalCacheHandler extends LocalCacheHandler<StringIdKey, CheckInfo> {
}
