package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.bean.entity.CheckerSupport;
import com.dwarfeng.voucher.stack.handler.SupportHandler;
import com.dwarfeng.voucher.stack.service.CheckerSupportMaintainService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SupportHandlerImpl implements SupportHandler {

    private final CheckerSupportMaintainService checkerSupportMaintainService;

    private final List<CheckerSupporter> checkerSupporters;

    public SupportHandlerImpl(
            CheckerSupportMaintainService checkerSupportMaintainService,
            List<CheckerSupporter> checkerSupporters
    ) {
        this.checkerSupportMaintainService = checkerSupportMaintainService;
        this.checkerSupporters = Optional.ofNullable(checkerSupporters).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public void resetChecker() throws HandlerException {
        try {
            doResetChecker();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetChecker() throws Exception {
        List<StringIdKey> checkerKeys = checkerSupportMaintainService.lookupAsList().stream()
                .map(CheckerSupport::getKey).collect(Collectors.toList());
        checkerSupportMaintainService.batchDelete(checkerKeys);
        List<CheckerSupport> checkerSupports = checkerSupporters.stream().map(supporter -> new CheckerSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleParam()
        )).collect(Collectors.toList());
        checkerSupportMaintainService.batchInsert(checkerSupports);
    }
}
