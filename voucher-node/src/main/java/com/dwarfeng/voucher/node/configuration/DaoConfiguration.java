package com.dwarfeng.voucher.node.configuration;

import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.impl.bean.HibernateMapper;
import com.dwarfeng.voucher.impl.bean.entity.*;
import com.dwarfeng.voucher.impl.bean.key.HibernateVoucherCategoryVariableKey;
import com.dwarfeng.voucher.impl.bean.key.HibernateVoucherVariableKey;
import com.dwarfeng.voucher.impl.dao.preset.*;
import com.dwarfeng.voucher.stack.bean.entity.*;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate hibernateTemplate;

    private final CheckerInfoPresetCriteriaMaker checkerInfoPresetCriteriaMaker;
    private final CheckerSupportPresetCriteriaMaker checkerSupportPresetCriteriaMaker;
    private final VoucherPresetCriteriaMaker voucherPresetCriteriaMaker;
    private final VoucherCategoryPresetCriteriaMaker voucherCategoryPresetCriteriaMaker;
    private final VoucherCategoryVariablePresetCriteriaMaker voucherCategoryVariablePresetCriteriaMaker;
    private final VoucherVariablePresetCriteriaMaker voucherVariablePresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            CheckerInfoPresetCriteriaMaker checkerInfoPresetCriteriaMaker,
            CheckerSupportPresetCriteriaMaker checkerSupportPresetCriteriaMaker,
            VoucherPresetCriteriaMaker voucherPresetCriteriaMaker,
            VoucherCategoryPresetCriteriaMaker voucherCategoryPresetCriteriaMaker,
            VoucherCategoryVariablePresetCriteriaMaker voucherCategoryVariablePresetCriteriaMaker,
            VoucherVariablePresetCriteriaMaker voucherVariablePresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.checkerInfoPresetCriteriaMaker = checkerInfoPresetCriteriaMaker;
        this.checkerSupportPresetCriteriaMaker = checkerSupportPresetCriteriaMaker;
        this.voucherPresetCriteriaMaker = voucherPresetCriteriaMaker;
        this.voucherCategoryPresetCriteriaMaker = voucherCategoryPresetCriteriaMaker;
        this.voucherCategoryVariablePresetCriteriaMaker = voucherCategoryVariablePresetCriteriaMaker;
        this.voucherVariablePresetCriteriaMaker = voucherVariablePresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, CheckerInfo, HibernateCheckerInfo>
    checkerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(CheckerInfo.class, HibernateCheckerInfo.class, HibernateMapper.class),
                HibernateCheckerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<CheckerInfo, HibernateCheckerInfo> checkerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(CheckerInfo.class, HibernateCheckerInfo.class, HibernateMapper.class),
                HibernateCheckerInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<CheckerInfo, HibernateCheckerInfo> checkerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(CheckerInfo.class, HibernateCheckerInfo.class, HibernateMapper.class),
                HibernateCheckerInfo.class,
                checkerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, CheckerSupport, HibernateCheckerSupport>
    checkerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        CheckerSupport.class, HibernateCheckerSupport.class, HibernateMapper.class
                ),
                HibernateCheckerSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<CheckerSupport, HibernateCheckerSupport> checkerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        CheckerSupport.class, HibernateCheckerSupport.class, HibernateMapper.class
                ),
                HibernateCheckerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<CheckerSupport, HibernateCheckerSupport> checkerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        CheckerSupport.class, HibernateCheckerSupport.class, HibernateMapper.class
                ),
                HibernateCheckerSupport.class,
                checkerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Voucher, HibernateVoucher>
    voucherHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Voucher.class, HibernateVoucher.class, HibernateMapper.class),
                HibernateVoucher.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Voucher, HibernateVoucher> voucherHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Voucher.class, HibernateVoucher.class, HibernateMapper.class),
                HibernateVoucher.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Voucher, HibernateVoucher> voucherHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Voucher.class, HibernateVoucher.class, HibernateMapper.class),
                HibernateVoucher.class,
                voucherPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, VoucherCategory, HibernateVoucherCategory>
    voucherCategoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class
                ),
                new MapStructBeanTransformer<>(
                        VoucherCategory.class, HibernateVoucherCategory.class, HibernateMapper.class
                ),
                HibernateVoucherCategory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VoucherCategory, HibernateVoucherCategory>
    voucherCategoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherCategory.class, HibernateVoucherCategory.class, HibernateMapper.class
                ),
                HibernateVoucherCategory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VoucherCategory, HibernateVoucherCategory>
    voucherCategoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherCategory.class, HibernateVoucherCategory.class, HibernateMapper.class
                ),
                HibernateVoucherCategory.class,
                voucherCategoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VoucherCategoryVariableKey, HibernateVoucherCategoryVariableKey,
            VoucherCategoryVariable, HibernateVoucherCategoryVariable> voucherCategoryVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherCategoryVariableKey.class, HibernateVoucherCategoryVariableKey.class, HibernateMapper.class
                ),
                new MapStructBeanTransformer<>(
                        VoucherCategoryVariable.class, HibernateVoucherCategoryVariable.class, HibernateMapper.class
                ),
                HibernateVoucherCategoryVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VoucherCategoryVariable, HibernateVoucherCategoryVariable>
    voucherCategoryVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherCategoryVariable.class, HibernateVoucherCategoryVariable.class, HibernateMapper.class
                ),
                HibernateVoucherCategoryVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VoucherCategoryVariable, HibernateVoucherCategoryVariable>
    voucherCategoryVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherCategoryVariable.class, HibernateVoucherCategoryVariable.class, HibernateMapper.class
                ),
                HibernateVoucherCategoryVariable.class,
                voucherCategoryVariablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VoucherVariableKey, HibernateVoucherVariableKey, VoucherVariable,
            HibernateVoucherVariable> voucherVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherVariableKey.class, HibernateVoucherVariableKey.class, HibernateMapper.class
                ),
                new MapStructBeanTransformer<>(
                        VoucherVariable.class, HibernateVoucherVariable.class, HibernateMapper.class
                ),
                HibernateVoucherVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VoucherVariable, HibernateVoucherVariable>
    voucherVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherVariable.class, HibernateVoucherVariable.class, HibernateMapper.class
                ),
                HibernateVoucherVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VoucherVariable, HibernateVoucherVariable>
    voucherVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VoucherVariable.class, HibernateVoucherVariable.class, HibernateMapper.class
                ),
                HibernateVoucherVariable.class,
                voucherVariablePresetCriteriaMaker
        );
    }
}
