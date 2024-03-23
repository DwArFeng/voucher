package com.dwarfeng.voucher.impl.cache;

import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.voucher.sdk.bean.entity.FastJsonVoucherVariable;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import com.dwarfeng.voucher.stack.cache.VoucherVariableCache;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VoucherVariableCacheImpl implements VoucherVariableCache {

    private final RedisBatchBaseCache<VoucherVariableKey, VoucherVariable, FastJsonVoucherVariable>
            voucherVariableBatchBaseDelegate;

    public VoucherVariableCacheImpl(
            RedisBatchBaseCache<VoucherVariableKey, VoucherVariable, FastJsonVoucherVariable>
                    voucherVariableBatchBaseDelegate
    ) {
        this.voucherVariableBatchBaseDelegate = voucherVariableBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(VoucherVariableKey key) throws CacheException {
        return voucherVariableBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public VoucherVariable get(VoucherVariableKey key) throws CacheException {
        return voucherVariableBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(VoucherVariable value, long timeout) throws CacheException {
        voucherVariableBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(VoucherVariableKey key) throws CacheException {
        voucherVariableBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        voucherVariableBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<VoucherVariableKey> keys) throws CacheException {
        return voucherVariableBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<VoucherVariableKey> keys) throws CacheException {
        return voucherVariableBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<VoucherVariable> batchGet(@SkipRecord List<VoucherVariableKey> keys) throws CacheException {
        return voucherVariableBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<VoucherVariable> entities, long timeout) throws CacheException {
        voucherVariableBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<VoucherVariableKey> keys) throws CacheException {
        voucherVariableBatchBaseDelegate.batchDelete(keys);
    }
}
