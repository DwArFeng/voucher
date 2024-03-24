package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableInspectInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariablePutInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableRemoveInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.handler.VoucherCategoryVariableOperateHandler;
import com.dwarfeng.voucher.stack.service.VoucherCategoryVariableMaintainService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class VoucherCategoryVariableOperateHandlerImpl implements VoucherCategoryVariableOperateHandler {

    private final VoucherCategoryVariableMaintainService voucherCategoryVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public VoucherCategoryVariableOperateHandlerImpl(
            VoucherCategoryVariableMaintainService voucherCategoryVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.voucherCategoryVariableMaintainService = voucherCategoryVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public VoucherCategoryVariable inspect(VoucherCategoryVariableInspectInfo inspectInfo) throws HandlerException {
        try {
            // 展开参数。
            StringIdKey voucherCategoryKey = inspectInfo.getVoucherCategoryKey();
            String variableId = inspectInfo.getVariableId();

            // 确认凭证类型存在。
            handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);

            // 调用维护服务获取凭证类型变量。
            return voucherCategoryVariableMaintainService.getIfExists(
                    new VoucherCategoryVariableKey(voucherCategoryKey.getStringId(), variableId)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void put(VoucherCategoryVariablePutInfo putInfo) throws HandlerException {
        try {
            // 展开参数。
            StringIdKey voucherCategoryKey = putInfo.getVoucherCategoryKey();
            String variableId = putInfo.getVariableId();
            String value = putInfo.getValue();

            // 确认凭证类型存在。
            handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);

            // 根据参数以及业务逻辑生成凭证类型变量。
            VoucherCategoryVariable variableValue = new VoucherCategoryVariable(
                    new VoucherCategoryVariableKey(voucherCategoryKey.getStringId(), variableId), value, new Date()
            );

            // 调用维护服务插入或更新凭证类型变量。
            voucherCategoryVariableMaintainService.insertOrUpdate(variableValue);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void remove(VoucherCategoryVariableRemoveInfo removeInfo) throws HandlerException {
        try {
            // 展开参数。
            StringIdKey voucherCategoryKey = removeInfo.getVoucherCategoryKey();
            String variableId = removeInfo.getVariableId();

            // 确认凭证类型存在。
            handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);

            // 调用维护服务删除凭证类型变量。
            voucherCategoryVariableMaintainService.deleteIfExists(
                    new VoucherCategoryVariableKey(voucherCategoryKey.getStringId(), variableId)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
