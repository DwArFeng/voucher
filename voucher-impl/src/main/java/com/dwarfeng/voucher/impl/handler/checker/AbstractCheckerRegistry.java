package com.dwarfeng.voucher.impl.handler.checker;

/**
 * 抽象检查器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.voucher.sdk.handler.checker.AbstractCheckerRegistry
 * @since beta-1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractCheckerRegistry extends com.dwarfeng.voucher.sdk.handler.checker.AbstractCheckerRegistry {

    public AbstractCheckerRegistry() {
    }

    public AbstractCheckerRegistry(String checkerType) {
        super(checkerType);
    }
}
