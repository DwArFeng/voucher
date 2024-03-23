package com.dwarfeng.voucher.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.cache.CheckerInfoCache;
import com.dwarfeng.voucher.stack.cache.VoucherCategoryCache;
import com.dwarfeng.voucher.stack.cache.VoucherCategoryVariableCache;
import com.dwarfeng.voucher.stack.dao.CheckerInfoDao;
import com.dwarfeng.voucher.stack.dao.VoucherCategoryDao;
import com.dwarfeng.voucher.stack.dao.VoucherCategoryVariableDao;
import com.dwarfeng.voucher.stack.dao.VoucherDao;
import com.dwarfeng.voucher.stack.service.VoucherCategoryVariableMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherMaintainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoucherCategoryCrudOperation implements BatchCrudOperation<StringIdKey, VoucherCategory> {

    private final VoucherCategoryDao voucherCategoryDao;
    private final VoucherCategoryCache voucherCategoryCache;

    private final CheckerInfoDao checkerInfoDao;
    private final CheckerInfoCache checkerInfoCache;

    private final VoucherCategoryVariableDao voucherCategoryVariableDao;
    private final VoucherCategoryVariableCache voucherCategoryVariableCache;

    private final VoucherCrudOperation voucherCrudOperation;
    private final VoucherDao voucherDao;

    @Value("${cache.timeout.entity.voucher_category}")
    private long voucherCategoryTimeout;

    public VoucherCategoryCrudOperation(
            VoucherCategoryDao voucherCategoryDao,
            VoucherCategoryCache voucherCategoryCache,
            CheckerInfoDao checkerInfoDao,
            CheckerInfoCache checkerInfoCache,
            VoucherCategoryVariableDao voucherCategoryVariableDao,
            VoucherCategoryVariableCache voucherCategoryVariableCache,
            VoucherCrudOperation voucherCrudOperation,
            VoucherDao voucherDao
    ) {
        this.voucherCategoryDao = voucherCategoryDao;
        this.voucherCategoryCache = voucherCategoryCache;
        this.checkerInfoDao = checkerInfoDao;
        this.checkerInfoCache = checkerInfoCache;
        this.voucherCategoryVariableDao = voucherCategoryVariableDao;
        this.voucherCategoryVariableCache = voucherCategoryVariableCache;
        this.voucherCrudOperation = voucherCrudOperation;
        this.voucherDao = voucherDao;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return voucherCategoryCache.exists(key) || voucherCategoryDao.exists(key);
    }

    @Override
    public VoucherCategory get(StringIdKey key) throws Exception {
        if (voucherCategoryCache.exists(key)) {
            return voucherCategoryCache.get(key);
        } else {
            if (!voucherCategoryDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            VoucherCategory voucherCategory = voucherCategoryDao.get(key);
            voucherCategoryCache.push(voucherCategory, voucherCategoryTimeout);
            return voucherCategory;
        }
    }

    @Override
    public StringIdKey insert(VoucherCategory voucherCategory) throws Exception {
        voucherCategoryCache.push(voucherCategory, voucherCategoryTimeout);
        return voucherCategoryDao.insert(voucherCategory);
    }

    @Override
    public void update(VoucherCategory voucherCategory) throws Exception {
        voucherCategoryCache.push(voucherCategory, voucherCategoryTimeout);
        voucherCategoryDao.update(voucherCategory);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与凭证类型相关的检查器信息。
        if (checkerInfoDao.exists(key)) {
            checkerInfoCache.delete(key);
            checkerInfoDao.delete(key);
        }

        // 删除与凭证类型相关的凭证类型变量。
        List<VoucherCategoryVariableKey> voucherCategoryVariableKeys = voucherCategoryVariableDao.lookup(
                VoucherCategoryVariableMaintainService.CHILD_FOR_VOUCHER_CATEGORY, new Object[]{key}
        ).stream().map(VoucherCategoryVariable::getKey).collect(Collectors.toList());
        voucherCategoryVariableCache.batchDelete(voucherCategoryVariableKeys);
        voucherCategoryVariableDao.batchDelete(voucherCategoryVariableKeys);

        // 删除与凭证类型相关的凭证。
        List<LongIdKey> voucherKeys = voucherDao.lookup(
                VoucherMaintainService.CHILD_FOR_CATEGORY, new Object[]{key}
        ).stream().map(Voucher::getKey).collect(Collectors.toList());
        voucherCrudOperation.batchDelete(voucherKeys);

        // 删除凭证类型自身。
        voucherCategoryDao.delete(key);
        voucherCategoryCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return voucherCategoryCache.allExists(keys) || voucherCategoryDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return voucherCategoryCache.nonExists(keys) && voucherCategoryDao.nonExists(keys);
    }

    @Override
    public List<VoucherCategory> batchGet(List<StringIdKey> keys) throws Exception {
        if (voucherCategoryCache.allExists(keys)) {
            return voucherCategoryCache.batchGet(keys);
        } else {
            if (!voucherCategoryDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<VoucherCategory> voucherCategories = voucherCategoryDao.batchGet(keys);
            voucherCategoryCache.batchPush(voucherCategories, voucherCategoryTimeout);
            return voucherCategories;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<VoucherCategory> voucherCategories) throws Exception {
        List<StringIdKey> keys = new ArrayList<>(voucherCategories.size());
        for (VoucherCategory voucherCategory : voucherCategories) {
            keys.add(insert(voucherCategory));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<VoucherCategory> voucherCategories) throws Exception {
        for (VoucherCategory voucherCategory : voucherCategories) {
            update(voucherCategory);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
