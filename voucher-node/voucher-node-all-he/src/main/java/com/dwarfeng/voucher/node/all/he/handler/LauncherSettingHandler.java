package com.dwarfeng.voucher.node.all.he.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${com.dwarfeng.voucher.launcher.reset_checker_support}")
    private boolean resetCheckerSupport;

    @Value("${com.dwarfeng.voucher.launcher.online_cleanup_delay}")
    private long onlineCleanupDelay;
    @Value("${com.dwarfeng.voucher.launcher.enable_cleanup_delay}")
    private long enableCleanupDelay;

    @Value("${com.dwarfeng.voucher.launcher.start_reset_delay}")
    private long startResetDelay;

    public boolean isResetCheckerSupport() {
        return resetCheckerSupport;
    }

    public long getOnlineCleanupDelay() {
        return onlineCleanupDelay;
    }

    public long getEnableCleanupDelay() {
        return enableCleanupDelay;
    }

    public long getStartResetDelay() {
        return startResetDelay;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetCheckerSupport=" + resetCheckerSupport +
                ", onlineCleanupDelay=" + onlineCleanupDelay +
                ", enableCleanupDelay=" + enableCleanupDelay +
                ", startResetDelay=" + startResetDelay +
                '}';
    }
}
