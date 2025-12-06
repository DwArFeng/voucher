package com.dwarfeng.voucher.impl.handler.resetter;

import com.dwarfeng.voucher.sdk.handler.resetter.AbstractResetter;
import org.springframework.stereotype.Component;

/**
 * 永远不执行重置的重置器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class NeverResetter extends AbstractResetter {

    @Override
    protected void doStart() {
    }

    @Override
    protected void doStop() {
    }

    @Override
    public String toString() {
        return "NeverResetter{}";
    }
}
