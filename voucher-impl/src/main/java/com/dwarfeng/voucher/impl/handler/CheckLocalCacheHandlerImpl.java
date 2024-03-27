package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.bean.entity.CheckerInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.handler.CheckLocalCacheHandler;
import com.dwarfeng.voucher.stack.handler.Checker;
import com.dwarfeng.voucher.stack.handler.CheckerHandler;
import com.dwarfeng.voucher.stack.service.CheckerInfoMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherCategoryMaintainService;
import com.dwarfeng.voucher.stack.struct.CheckInfo;
import org.springframework.stereotype.Component;

@Component
public class CheckLocalCacheHandlerImpl implements CheckLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, CheckInfo> handler;

    public CheckLocalCacheHandlerImpl(CheckerFetcher checkerFetcher) {
        handler = new GeneralLocalCacheHandler<>(checkerFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(StringIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public CheckInfo get(StringIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(StringIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() throws HandlerException {
        handler.clear();
    }

    @Component
    public static class CheckerFetcher implements Fetcher<StringIdKey, CheckInfo> {

        private final VoucherCategoryMaintainService voucherCategoryMaintainService;
        private final CheckerInfoMaintainService checkerInfoMaintainService;

        private final CheckerHandler checkerHandler;

        public CheckerFetcher(
                VoucherCategoryMaintainService voucherCategoryMaintainService,
                CheckerInfoMaintainService checkerInfoMaintainService,
                CheckerHandler checkerHandler
        ) {
            this.voucherCategoryMaintainService = voucherCategoryMaintainService;
            this.checkerInfoMaintainService = checkerInfoMaintainService;
            this.checkerHandler = checkerHandler;
        }

        @Override
        public boolean exists(StringIdKey key) throws Exception {
            boolean voucherCategoryExists = voucherCategoryMaintainService.exists(key);
            boolean checkerInfoExists = checkerInfoMaintainService.exists(key);
            return voucherCategoryExists && checkerInfoExists;
        }

        @Override
        public CheckInfo fetch(StringIdKey key) throws Exception {
            VoucherCategory voucherCategory = voucherCategoryMaintainService.get(key);
            CheckerInfo checkerInfo = checkerInfoMaintainService.get(key);
            String checkerInfoType = checkerInfo.getType();
            String checkerInfoParam = checkerInfo.getParam();
            Checker checker = checkerHandler.make(checkerInfoType, checkerInfoParam);
            return new CheckInfo(voucherCategory, checkerInfo, checker);
        }
    }
}
