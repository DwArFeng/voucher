package com.dwarfeng.voucher.impl.cache;

import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.voucher.sdk.bean.entity.FastJsonVoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.cache.VoucherCategoryVariableCache;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VoucherCategoryVariableCacheImpl implements VoucherCategoryVariableCache {

    private final RedisBatchBaseCache<VoucherCategoryVariableKey, VoucherCategoryVariable,
            FastJsonVoucherCategoryVariable> voucherCategoryVariableBatchBaseDelegate;

    public VoucherCategoryVariableCacheImpl(
            RedisBatchBaseCache<VoucherCategoryVariableKey, VoucherCategoryVariable, FastJsonVoucherCategoryVariable>
                    voucherCategoryVariableBatchBaseDelegate
    ) {
        this.voucherCategoryVariableBatchBaseDelegate = voucherCategoryVariableBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(VoucherCategoryVariableKey key) throws CacheException {
        return voucherCategoryVariableBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public VoucherCategoryVariable get(VoucherCategoryVariableKey key) throws CacheException {
        return voucherCategoryVariableBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(VoucherCategoryVariable value, long timeout) throws CacheException {
        voucherCategoryVariableBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(VoucherCategoryVariableKey key) throws CacheException {
        voucherCategoryVariableBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        voucherCategoryVariableBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<VoucherCategoryVariableKey> keys) throws CacheException {
        return voucherCategoryVariableBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<VoucherCategoryVariableKey> keys) throws CacheException {
        return voucherCategoryVariableBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<VoucherCategoryVariable> batchGet(@SkipRecord List<VoucherCategoryVariableKey> keys) throws CacheException {
        return voucherCategoryVariableBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<VoucherCategoryVariable> entities, long timeout) throws CacheException {
        voucherCategoryVariableBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<VoucherCategoryVariableKey> keys) throws CacheException {
        voucherCategoryVariableBatchBaseDelegate.batchDelete(keys);
    }
}
