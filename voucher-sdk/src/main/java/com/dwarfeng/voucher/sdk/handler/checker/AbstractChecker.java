package com.dwarfeng.voucher.sdk.handler.checker;

import com.dwarfeng.voucher.stack.handler.Checker;

/**
 * 检查器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public abstract class AbstractChecker implements Checker {

    protected Context context;

    public AbstractChecker() {
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractChecker{" +
                "context=" + context +
                '}';
    }
}
