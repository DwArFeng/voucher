package com.dwarfeng.voucher.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_checker_support}")
    private boolean resetCheckerSupport;

    public boolean isResetCheckerSupport() {
        return resetCheckerSupport;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetCheckerSupport=" + resetCheckerSupport +
                '}';
    }
}
