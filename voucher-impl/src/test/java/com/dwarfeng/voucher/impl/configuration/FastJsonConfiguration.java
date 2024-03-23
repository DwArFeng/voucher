package com.dwarfeng.voucher.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.voucher.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        ParserConfig.getGlobalInstance().addAccept(FastJsonCheckerInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonCheckerSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVoucher.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVoucherCategory.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVoucherCategoryVariable.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVoucherVariable.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
