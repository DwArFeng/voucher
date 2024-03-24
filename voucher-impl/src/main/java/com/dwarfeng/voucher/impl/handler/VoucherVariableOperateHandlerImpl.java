package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.bean.dto.VoucherVariableInspectInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherVariablePutInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherVariableRemoveInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import com.dwarfeng.voucher.stack.handler.VoucherVariableOperateHandler;
import com.dwarfeng.voucher.stack.service.VoucherVariableMaintainService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VoucherVariableOperateHandlerImpl implements VoucherVariableOperateHandler {

    private final VoucherVariableMaintainService voucherVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public VoucherVariableOperateHandlerImpl(
            VoucherVariableMaintainService voucherVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.voucherVariableMaintainService = voucherVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public VoucherVariable inspect(VoucherVariableInspectInfo inspectInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = inspectInfo.getVoucherKey();
            String variableId = inspectInfo.getVariableId();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);

            // 调用维护服务获取凭证变量。
            return voucherVariableMaintainService.getIfExists(
                    new VoucherVariableKey(voucherKey.getLongId(), variableId)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void put(VoucherVariablePutInfo putInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = putInfo.getVoucherKey();
            String variableId = putInfo.getVariableId();
            String value = putInfo.getValue();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);

            // 根据参数以及业务逻辑生成凭证变量。
            VoucherVariable variableValue = new VoucherVariable(
                    new VoucherVariableKey(voucherKey.getLongId(), variableId), value, new Date()
            );

            // 调用维护服务插入或更新凭证变量。
            voucherVariableMaintainService.insertOrUpdate(variableValue);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void batchPut(List<VoucherVariablePutInfo> putInfos) throws HandlerException {
        try {
            // 遍历 putInfos， 汇聚对应的 voucherKey。
            Set<LongIdKey> voucherKeys = putInfos.stream().map(VoucherVariablePutInfo::getVoucherKey)
                    .collect(Collectors.toSet());

            // 确认凭证存在。
            for (LongIdKey voucherKey : voucherKeys) {
                handlerValidator.makeSureVoucherExists(voucherKey);
            }

            // 根据参数以及业务逻辑生成凭证变量列表。
            Date currentDate = new Date();
            List<VoucherVariable> variableValues = putInfos.stream().map(putInfo -> {
                String variableId = putInfo.getVariableId();
                String value = putInfo.getValue();
                return new VoucherVariable(
                        new VoucherVariableKey(putInfo.getVoucherKey().getLongId(), variableId), value, currentDate
                );
            }).collect(Collectors.toList());

            // 调用维护服务批量插入或更新凭证变量。
            voucherVariableMaintainService.batchInsertOrUpdate(variableValues);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void remove(VoucherVariableRemoveInfo removeInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = removeInfo.getVoucherKey();
            String variableId = removeInfo.getVariableId();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);

            // 调用维护服务删除凭证变量。
            voucherVariableMaintainService.deleteIfExists(new VoucherVariableKey(voucherKey.getLongId(), variableId));
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
