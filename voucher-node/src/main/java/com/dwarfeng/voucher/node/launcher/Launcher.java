package com.dwarfeng.voucher.node.launcher;

import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.node.handler.LauncherSettingHandler;
import com.dwarfeng.voucher.stack.service.CheckerSupportMaintainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    @SuppressWarnings("Convert2MethodRef")
    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性执行重置。
            mayResetCheckerSupport(ctx);
        });
    }

    private static void mayResetCheckerSupport(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置进行启动。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 判断是否重置检查器支持。
        if (launcherSettingHandler.isResetCheckerSupport()) {
            LOGGER.info("重置检查器支持...");
            CheckerSupportMaintainService maintainService = ctx.getBean(CheckerSupportMaintainService.class);
            try {
                maintainService.reset();
            } catch (ServiceException e) {
                LOGGER.warn("检查器支持重置失败，异常信息如下", e);
            }
        }
    }
}
