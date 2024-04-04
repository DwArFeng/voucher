package com.dwarfeng.voucher.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_checker_support}")
    private boolean resetCheckerSupport;

    @Value("${launcher.online_cleanup_delay}")
    private long onlineCleanupDelay;
    @Value("${launcher.enable_cleanup_delay}")
    private long enableCleanupDelay;

    public boolean isResetCheckerSupport() {
        return resetCheckerSupport;
    }

    public long getOnlineCleanupDelay() {
        return onlineCleanupDelay;
    }

    public long getEnableCleanupDelay() {
        return enableCleanupDelay;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetCheckerSupport=" + resetCheckerSupport +
                ", onlineCleanupDelay=" + onlineCleanupDelay +
                ", enableCleanupDelay=" + enableCleanupDelay +
                '}';
    }
}
