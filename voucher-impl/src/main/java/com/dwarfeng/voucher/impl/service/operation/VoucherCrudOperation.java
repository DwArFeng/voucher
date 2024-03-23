package com.dwarfeng.voucher.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import com.dwarfeng.voucher.stack.cache.VoucherCache;
import com.dwarfeng.voucher.stack.cache.VoucherVariableCache;
import com.dwarfeng.voucher.stack.dao.VoucherDao;
import com.dwarfeng.voucher.stack.dao.VoucherVariableDao;
import com.dwarfeng.voucher.stack.service.VoucherVariableMaintainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoucherCrudOperation implements BatchCrudOperation<LongIdKey, Voucher> {

    private final VoucherDao voucherDao;
    private final VoucherCache voucherCache;

    private final VoucherVariableDao voucherVariableDao;
    private final VoucherVariableCache voucherVariableCache;

    @Value("${cache.timeout.entity.voucher}")
    private long voucherTimeout;

    public VoucherCrudOperation(
            VoucherDao voucherDao,
            VoucherCache voucherCache,
            VoucherVariableDao voucherVariableDao,
            VoucherVariableCache voucherVariableCache
    ) {
        this.voucherDao = voucherDao;
        this.voucherCache = voucherCache;
        this.voucherVariableDao = voucherVariableDao;
        this.voucherVariableCache = voucherVariableCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return voucherCache.exists(key) || voucherDao.exists(key);
    }

    @Override
    public Voucher get(LongIdKey key) throws Exception {
        if (voucherCache.exists(key)) {
            return voucherCache.get(key);
        } else {
            if (!voucherDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Voucher voucher = voucherDao.get(key);
            voucherCache.push(voucher, voucherTimeout);
            return voucher;
        }
    }

    @Override
    public LongIdKey insert(Voucher voucher) throws Exception {
        voucherCache.push(voucher, voucherTimeout);
        return voucherDao.insert(voucher);
    }

    @Override
    public void update(Voucher voucher) throws Exception {
        voucherCache.push(voucher, voucherTimeout);
        voucherDao.update(voucher);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与凭证相关的凭证变量。
        List<VoucherVariableKey> voucherVariableKeys = voucherVariableDao.lookup(
                VoucherVariableMaintainService.CHILD_FOR_VOUCHER, new Object[]{key}
        ).stream().map(VoucherVariable::getKey).collect(Collectors.toList());
        voucherVariableCache.batchDelete(voucherVariableKeys);
        voucherVariableDao.batchDelete(voucherVariableKeys);

        // 删除凭证自身。
        voucherDao.delete(key);
        voucherCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return voucherCache.allExists(keys) || voucherDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return voucherCache.nonExists(keys) && voucherDao.nonExists(keys);
    }

    @Override
    public List<Voucher> batchGet(List<LongIdKey> keys) throws Exception {
        if (voucherCache.allExists(keys)) {
            return voucherCache.batchGet(keys);
        } else {
            if (!voucherDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Voucher> vouchers = voucherDao.batchGet(keys);
            voucherCache.batchPush(vouchers, voucherTimeout);
            return vouchers;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Voucher> vouchers) throws Exception {
        voucherCache.batchPush(vouchers, voucherTimeout);
        return voucherDao.batchInsert(vouchers);
    }

    @Override
    public void batchUpdate(List<Voucher> vouchers) throws Exception {
        voucherCache.batchPush(vouchers, voucherTimeout);
        voucherDao.batchUpdate(vouchers);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
