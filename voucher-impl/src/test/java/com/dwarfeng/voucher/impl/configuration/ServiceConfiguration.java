package com.dwarfeng.voucher.impl.configuration;

import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.voucher.impl.service.operation.VoucherCategoryCrudOperation;
import com.dwarfeng.voucher.impl.service.operation.VoucherCrudOperation;
import com.dwarfeng.voucher.stack.bean.entity.*;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import com.dwarfeng.voucher.stack.cache.CheckerInfoCache;
import com.dwarfeng.voucher.stack.cache.CheckerSupportCache;
import com.dwarfeng.voucher.stack.cache.VoucherCategoryVariableCache;
import com.dwarfeng.voucher.stack.cache.VoucherVariableCache;
import com.dwarfeng.voucher.stack.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final GenerateConfiguration generateConfiguration;

    private final CheckerInfoDao checkerInfoDao;
    private final CheckerInfoCache checkerInfoCache;
    private final CheckerSupportDao checkerSupportDao;
    private final CheckerSupportCache checkerSupportCache;
    private final VoucherCrudOperation voucherCrudOperation;
    private final VoucherDao voucherDao;
    private final VoucherCategoryCrudOperation voucherCategoryCrudOperation;
    private final VoucherCategoryDao voucherCategoryDao;
    private final VoucherCategoryVariableDao voucherCategoryVariableDao;
    private final VoucherCategoryVariableCache voucherCategoryVariableCache;
    private final VoucherVariableDao voucherVariableDao;
    private final VoucherVariableCache voucherVariableCache;

    @Value("${cache.timeout.entity.checker_info}")
    private long checkerInfoTimeout;
    @Value("${cache.timeout.entity.checker_support}")
    private long checkerSupportTimeout;
    @Value("${cache.timeout.entity.voucher_category_variable}")
    private long voucherCategoryVariableTimeout;
    @Value("${cache.timeout.entity.voucher_variable}")
    private long voucherVariableTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            CheckerInfoDao checkerInfoDao,
            CheckerInfoCache checkerInfoCache,
            CheckerSupportDao checkerSupportDao,
            CheckerSupportCache checkerSupportCache,
            VoucherCrudOperation voucherCrudOperation,
            VoucherDao voucherDao,
            VoucherCategoryCrudOperation voucherCategoryCrudOperation,
            VoucherCategoryDao voucherCategoryDao,
            VoucherCategoryVariableDao voucherCategoryVariableDao,
            VoucherCategoryVariableCache voucherCategoryVariableCache,
            VoucherVariableDao voucherVariableDao,
            VoucherVariableCache voucherVariableCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.checkerInfoDao = checkerInfoDao;
        this.checkerInfoCache = checkerInfoCache;
        this.checkerSupportDao = checkerSupportDao;
        this.checkerSupportCache = checkerSupportCache;
        this.voucherCrudOperation = voucherCrudOperation;
        this.voucherDao = voucherDao;
        this.voucherCategoryCrudOperation = voucherCategoryCrudOperation;
        this.voucherCategoryDao = voucherCategoryDao;
        this.voucherCategoryVariableDao = voucherCategoryVariableDao;
        this.voucherCategoryVariableCache = voucherCategoryVariableCache;
        this.voucherVariableDao = voucherVariableDao;
        this.voucherVariableCache = voucherVariableCache;
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, CheckerInfo> checkerInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                checkerInfoDao,
                checkerInfoCache,
                new ExceptionKeyGenerator<>(),
                checkerInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<CheckerInfo> checkerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                checkerInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<CheckerInfo> checkerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                checkerInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, CheckerSupport> checkerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                checkerSupportDao,
                checkerSupportCache,
                new ExceptionKeyGenerator<>(),
                checkerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<CheckerSupport> checkerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                checkerSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<CheckerSupport> checkerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                checkerSupportDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Voucher> voucherCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Voucher> voucherDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Voucher> voucherDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, VoucherCategory> voucherCategoryCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCategoryCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VoucherCategory> voucherCategoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCategoryDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VoucherCategory> voucherCategoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCategoryDao
        );
    }

    @Bean
    public GeneralBatchCrudService<VoucherCategoryVariableKey, VoucherCategoryVariable>
    voucherCategoryVariableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCategoryVariableDao,
                voucherCategoryVariableCache,
                new ExceptionKeyGenerator<>(),
                voucherCategoryVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VoucherCategoryVariable> voucherCategoryVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCategoryVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VoucherCategoryVariable> voucherCategoryVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherCategoryVariableDao
        );
    }

    @Bean
    public GeneralBatchCrudService<VoucherVariableKey, VoucherVariable> voucherVariableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherVariableDao,
                voucherVariableCache,
                new ExceptionKeyGenerator<>(),
                voucherVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VoucherVariable> voucherVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VoucherVariable> voucherVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                voucherVariableDao
        );
    }
}
