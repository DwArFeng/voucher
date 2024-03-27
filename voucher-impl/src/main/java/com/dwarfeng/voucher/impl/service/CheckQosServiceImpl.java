package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.voucher.stack.handler.CheckLocalCacheHandler;
import com.dwarfeng.voucher.stack.service.CheckQosService;
import com.dwarfeng.voucher.stack.struct.CheckInfo;
import org.springframework.stereotype.Service;

@Service
public class CheckQosServiceImpl implements CheckQosService {

    private final CheckLocalCacheHandler checkLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public CheckQosServiceImpl(CheckLocalCacheHandler checkLocalCacheHandler, ServiceExceptionMapper sem) {
        this.checkLocalCacheHandler = checkLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public CheckInfo getCheckInfo(StringIdKey voucherCategoryKey) throws ServiceException {
        try {
            return checkLocalCacheHandler.get(voucherCategoryKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定凭证类型的检查信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            checkLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
