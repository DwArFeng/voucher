package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;
import com.dwarfeng.voucher.stack.handler.VoucherHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * 清理处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class CleanupProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanupProcessor.class);

    private final VoucherHandler voucherHandler;

    public CleanupProcessor(VoucherHandler voucherHandler) {
        this.voucherHandler = voucherHandler;
    }

    /**
     * 立即执行清理作业。
     *
     * @throws Exception 执行清理作业时发生的任何异常。
     */
    void cleanup() throws Exception {
        // 记录日志。
        LOGGER.info("开始执行清理作业...");

        // 使用计时器记录清理作业的执行时间。
        TimeMeasurer tm = new TimeMeasurer();

        // 执行清理作业。
        tm.start();
        CompletableFuture<Void> checkValidFuture = voucherHandler.checkValid();
        checkValidFuture.join();
        CompletableFuture<Void> removeInvalidFuture = voucherHandler.removeInvalid();
        removeInvalidFuture.join();
        tm.stop();

        // 记录日志。
        LOGGER.debug("清理作业执行完成, 用时: {} 毫秒", tm.getTimeMs());
    }

    @Override
    public String toString() {
        return "CleanupProcessor{" +
                "voucherHandler=" + voucherHandler +
                '}';
    }
}
