package com.dwarfeng.voucher.impl.handler.checker;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.exception.CheckerException;
import com.dwarfeng.voucher.stack.exception.CheckerMakeException;
import com.dwarfeng.voucher.stack.handler.Checker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 一次性使用
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class SingleUseCheckerRegistry extends AbstractCheckerRegistry {

    public static final String CHECKER_TYPE = "single_use_checker";

    private final ApplicationContext ctx;

    public SingleUseCheckerRegistry(ApplicationContext ctx) {
        super(CHECKER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "一次性使用检查器";
    }

    @Override
    public String provideDescription() {
        return "一次性使用检查器，到达过期时间或查看一次后失效。";
    }

    @Override
    public String provideExampleParam() {
        return JSON.toJSONString(new Config(12450L));
    }

    @Override
    public Checker makeChecker(String type, String param) throws CheckerException {
        try {
            Config config = JSON.parseObject(param, Config.class);
            return ctx.getBean(SingleUserChecker.class, config);
        } catch (Exception e) {
            throw new CheckerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "SingleUseCheckerRegistry{" +
                "ctx=" + ctx +
                ", checkerType='" + checkerType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class SingleUserChecker extends AbstractChecker {

        private static final String VARIABLE_ID_EXPIRE_TIMESTAMP = "expire_timestamp";

        private final Config config;

        public SingleUserChecker(Config config) {
            this.config = config;
        }

        @Override
        public void afterVoucherInitialize(StringIdKey voucherCategoryKey, LongIdKey voucherKey)
                throws CheckerException {
            try {
                // 计算过期日期对应的时间戳文本，如果永不过期，则时间戳文本为空字符串。
                String expireTimestampString;
                long expireDateOffset = config.getExpireDateOffset();
                if (expireDateOffset <= 0) {
                    expireTimestampString = StringUtils.EMPTY;
                } else {
                    expireTimestampString = String.valueOf(System.currentTimeMillis() + expireDateOffset);
                }
                // 设置变量。
                context.putVoucherVariable(voucherKey, VARIABLE_ID_EXPIRE_TIMESTAMP, expireTimestampString);
            } catch (CheckerException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerException(e);
            }
        }

        @Override
        public void doVoucherValidCheck(StringIdKey voucherCategoryKey, LongIdKey voucherKey)
                throws CheckerException {
            try {
                // 检查是否过期，如果过期，则标记为失效。
                invalidIfExpired(voucherKey);
            } catch (CheckerException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerException(e);
            }
        }

        @Override
        public void afterVoucherInspectSucceed(StringIdKey voucherCategoryKey, LongIdKey voucherKey)
                throws CheckerException {
            try {
                // 因为凭据是一次性使用的，所以查看成功后直接标记为失效。
                context.invalidVoucher(voucherKey);
            } catch (CheckerException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerException(e);
            }
        }

        @Override
        public void afterVoucherInspectFailed(StringIdKey voucherCategoryKey, LongIdKey voucherKey)
                throws CheckerException {
            try {
                // 检查是否过期，如果过期，则标记为失效。
                invalidIfExpired(voucherKey);
            } catch (CheckerException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerException(e);
            }
        }

        private void invalidIfExpired(LongIdKey voucherKey) throws Exception {
            // 获取过期时间戳文本。
            String expireTimestampString = context.inspectVoucherVariableOrDefault(
                    voucherKey, VARIABLE_ID_EXPIRE_TIMESTAMP, StringUtils.EMPTY
            );
            // 如果永不过期，则直接返回。
            if (StringUtils.isEmpty(expireTimestampString)) {
                return;
            }
            // 否则，获取时间戳，与系统时间比较，如果过期，则标记为失效。
            long expireTimestamp = Long.parseLong(expireTimestampString);
            if (expireTimestamp <= System.currentTimeMillis()) {
                context.invalidVoucher(voucherKey);
            }
        }

        @Override
        public String toString() {
            return "SingleUserChecker{" +
                    "config=" + config +
                    ", context=" + context +
                    '}';
        }
    }

    public static class Config implements Bean {

        private static final long serialVersionUID = -8290411224374108720L;

        @JSONField(name = "expire_date_offset", ordinal = 1)
        private long expireDateOffset;

        @JSONField(name = "#expire_date_offset", ordinal = 2, deserialize = false)
        private String expireDateOffsetRem = "过期时间偏移量, 单位为毫秒. " +
                "生成凭证时, 凭证的过期时间 = 系统时间 + 过期时间偏移量. " +
                "偏移量小于等于 0 时, 代表凭证永不过期, 只有查看后才能使凭证失效.";

        public Config() {
        }

        public Config(long expireDateOffset) {
            this.expireDateOffset = expireDateOffset;
        }

        public long getExpireDateOffset() {
            return expireDateOffset;
        }

        public void setExpireDateOffset(long expireDateOffset) {
            this.expireDateOffset = expireDateOffset;
        }

        public String getExpireDateOffsetRem() {
            return expireDateOffsetRem;
        }

        public void setExpireDateOffsetRem(String expireDateOffsetRem) {
            this.expireDateOffsetRem = expireDateOffsetRem;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "expireDateOffset=" + expireDateOffset +
                    ", expireDateOffsetRem='" + expireDateOffsetRem + '\'' +
                    '}';
        }
    }
}
