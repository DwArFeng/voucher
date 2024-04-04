package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.handler.CheckLocalCacheHandler;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class ResetProcessor {

    private final CheckLocalCacheHandler checkLocalCacheHandler;

    public ResetProcessor(CheckLocalCacheHandler checkLocalCacheHandler) {
        this.checkLocalCacheHandler = checkLocalCacheHandler;
    }

    public void resetCheck() throws HandlerException {
        // 重置检查。
        checkLocalCacheHandler.clear();

        // TODO 推送消息。
    }

    @Override
    public String toString() {
        return "ResetProcessor{" +
                "checkLocalCacheHandler=" + checkLocalCacheHandler +
                '}';
    }
}
