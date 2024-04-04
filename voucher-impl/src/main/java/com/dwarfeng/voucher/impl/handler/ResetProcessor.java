package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.handler.CheckLocalCacheHandler;
import com.dwarfeng.voucher.stack.handler.PushHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final CheckLocalCacheHandler checkLocalCacheHandler;

    private final PushHandler pushHandler;

    public ResetProcessor(
            CheckLocalCacheHandler checkLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.checkLocalCacheHandler = checkLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetCheck() throws HandlerException {
        // 重置检查。
        checkLocalCacheHandler.clear();

        // 推送消息。
        try {
            pushHandler.checkReset();
        } catch (Exception e) {
            LOGGER.warn("推送检查功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }

    @Override
    public String toString() {
        return "ResetProcessor{" +
                "checkLocalCacheHandler=" + checkLocalCacheHandler +
                '}';
    }
}
