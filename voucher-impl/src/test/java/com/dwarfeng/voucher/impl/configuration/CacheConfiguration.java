package com.dwarfeng.voucher.impl.configuration;

import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.sdk.bean.FastJsonMapper;
import com.dwarfeng.voucher.sdk.bean.entity.*;
import com.dwarfeng.voucher.sdk.bean.key.format.VoucherCategoryVariableStringKeyFormatter;
import com.dwarfeng.voucher.sdk.bean.key.format.VoucherVariableStringKeyFormatter;
import com.dwarfeng.voucher.stack.bean.entity.*;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.checker_info}")
    private String checkerInfoPrefix;
    @Value("${cache.prefix.entity.checker_support}")
    private String checkerSupportPrefix;
    @Value("${cache.prefix.entity.voucher}")
    private String voucherPrefix;
    @Value("${cache.prefix.entity.voucher_category}")
    private String voucherCategoryPrefix;
    @Value("${cache.prefix.entity.voucher_category_variable}")
    private String voucherCategoryVariablePrefix;
    @Value("${cache.prefix.entity.voucher_variable}")
    private String voucherVariablePrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, CheckerInfo, FastJsonCheckerInfo> checkerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonCheckerInfo>) template,
                new StringIdStringKeyFormatter(checkerInfoPrefix),
                new MapStructBeanTransformer<>(CheckerInfo.class, FastJsonCheckerInfo.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, CheckerSupport, FastJsonCheckerSupport>
    checkerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonCheckerSupport>) template,
                new StringIdStringKeyFormatter(checkerSupportPrefix),
                new MapStructBeanTransformer<>(CheckerSupport.class, FastJsonCheckerSupport.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Voucher, FastJsonVoucher> voucherRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVoucher>) template,
                new LongIdStringKeyFormatter(voucherPrefix),
                new MapStructBeanTransformer<>(Voucher.class, FastJsonVoucher.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, VoucherCategory, FastJsonVoucherCategory>
    voucherCategoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVoucherCategory>) template,
                new StringIdStringKeyFormatter(voucherCategoryPrefix),
                new MapStructBeanTransformer<>(
                        VoucherCategory.class, FastJsonVoucherCategory.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VoucherCategoryVariableKey, VoucherCategoryVariable,
            FastJsonVoucherCategoryVariable> voucherCategoryVariableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVoucherCategoryVariable>) template,
                new VoucherCategoryVariableStringKeyFormatter(voucherCategoryVariablePrefix),
                new MapStructBeanTransformer<>(
                        VoucherCategoryVariable.class, FastJsonVoucherCategoryVariable.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VoucherVariableKey, VoucherVariable, FastJsonVoucherVariable>
    voucherVariableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVoucherVariable>) template,
                new VoucherVariableStringKeyFormatter(voucherVariablePrefix),
                new MapStructBeanTransformer<>(
                        VoucherVariable.class, FastJsonVoucherVariable.class, FastJsonMapper.class
                )
        );
    }
}
