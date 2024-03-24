package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import com.dwarfeng.voucher.stack.bean.dto.*;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.handler.VoucherOperateHandler;
import com.dwarfeng.voucher.stack.service.VoucherMaintainService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VoucherOperateHandlerImpl implements VoucherOperateHandler {

    private final VoucherMaintainService voucherMaintainService;

    private final HandlerValidator handlerValidator;

    private final KeyGenerator<LongIdKey> longIdKeyGenerator;

    public VoucherOperateHandlerImpl(
            VoucherMaintainService voucherMaintainService,
            HandlerValidator handlerValidator,
            KeyGenerator<LongIdKey> longIdKeyGenerator
    ) {
        this.voucherMaintainService = voucherMaintainService;
        this.handlerValidator = handlerValidator;
        this.longIdKeyGenerator = longIdKeyGenerator;
    }

    @Override
    public LongIdKey create(VoucherCreateInfo createInfo) throws HandlerException {
        try {
            // 展开参数。
            StringIdKey categoryKey = createInfo.getCategoryKey();
            LongIdKey voucherKey = createInfo.getVoucherKey();
            String content = createInfo.getContent();
            String remark = createInfo.getRemark();

            // 确认凭证类型存在。
            handlerValidator.makeSureVoucherCategoryExists(categoryKey);
            // 如果 voucherKey 不为 null，则确认凭证不存在。
            if (Objects.nonNull(voucherKey)) {
                handlerValidator.makeSureVoucherNotExists(voucherKey);
            }

            // 根据参数以及业务逻辑生成凭证。
            if (Objects.isNull(voucherKey)) {
                voucherKey = longIdKeyGenerator.generate();
            }
            Voucher voucher = new Voucher(voucherKey, categoryKey, content, remark, true);

            // 插入凭证。
            voucherMaintainService.insert(voucher);

            // 返回凭证的主键。
            return voucherKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public VoucherInspectResult inspect(VoucherInspectInfo inspectInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = inspectInfo.getVoucherKey();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);
            // 确认凭证有效。
            handlerValidator.makeSureVoucherValid(voucherKey);

            // 调用维护服务获取凭证。
            Voucher voucher = voucherMaintainService.get(voucherKey);

            // 返回凭证查看结果。
            String content = voucher.getContent();
            String remark = voucher.getRemark();
            return new VoucherInspectResult(voucherKey, content, remark);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void invalid(VoucherInvalidInfo invalidInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = invalidInfo.getVoucherKey();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);
            // 确认凭证有效。
            handlerValidator.makeSureVoucherValid(voucherKey);

            // 作废凭证。
            Voucher voucher = voucherMaintainService.get(voucherKey);
            voucher.setValid(false);

            // 调用维护服务更新凭证。
            voucherMaintainService.update(voucher);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void remove(VoucherRemoveInfo removeInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = removeInfo.getVoucherKey();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);

            // 删除凭证。
            voucherMaintainService.delete(voucherKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
