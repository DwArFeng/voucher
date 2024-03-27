package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.Generator;
import com.dwarfeng.voucher.stack.bean.dto.*;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.exception.InvalidVoucherException;
import com.dwarfeng.voucher.stack.handler.CheckLocalCacheHandler;
import com.dwarfeng.voucher.stack.handler.Checker;
import com.dwarfeng.voucher.stack.handler.VoucherHandler;
import com.dwarfeng.voucher.stack.handler.VoucherOperateHandler;
import com.dwarfeng.voucher.stack.service.VoucherMaintainService;
import com.dwarfeng.voucher.stack.struct.CheckInfo;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Component
public class VoucherHandlerImpl implements VoucherHandler {

    private final VoucherMaintainService voucherMaintainService;

    private final CheckLocalCacheHandler checkLocalCacheHandler;
    private final VoucherOperateHandler voucherOperateHandler;

    private final VoucherMutexProcessor voucherMutexProcessor;

    private final Generator<LongIdKey> longIdKeyGenerator;

    private final ThreadPoolTaskExecutor executor;

    private final HandlerValidator handlerValidator;

    public VoucherHandlerImpl(
            VoucherMaintainService voucherMaintainService,
            CheckLocalCacheHandler checkLocalCacheHandler,
            VoucherOperateHandler voucherOperateHandler,
            VoucherMutexProcessor voucherMutexProcessor,
            Generator<LongIdKey> longIdKeyGenerator,
            ThreadPoolTaskExecutor executor,
            HandlerValidator handlerValidator
    ) {
        this.voucherMaintainService = voucherMaintainService;
        this.checkLocalCacheHandler = checkLocalCacheHandler;
        this.voucherOperateHandler = voucherOperateHandler;
        this.voucherMutexProcessor = voucherMutexProcessor;
        this.longIdKeyGenerator = longIdKeyGenerator;
        this.executor = executor;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey create(VoucherCreateInfo createInfo) throws HandlerException {
        try {
            // 展开参数。
            StringIdKey voucherCategoryKey = createInfo.getCategoryKey();
            LongIdKey voucherKey = createInfo.getVoucherKey();

            // 确认凭证类型存在。
            handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);
            // 确认凭证类型使能。
            handlerValidator.makeSureVoucherCategoryEnabled(voucherCategoryKey);
            // 确认凭证类型对应的检查器信息存在。
            handlerValidator.makeSureCheckerInfoExists(voucherCategoryKey);

            // 获取凭证类型对应的检查器。
            CheckInfo checkInfo = checkLocalCacheHandler.get(voucherCategoryKey);
            Checker checker = checkInfo.getChecker();

            // 如果凭证主键为 null，则生成凭证主键。
            if (Objects.isNull(voucherKey)) {
                voucherKey = longIdKeyGenerator.generate();
            }

            // 在分布式锁中执行生成任务，并返回结果。
            LongIdKey finalVoucherKey = voucherKey;
            return voucherMutexProcessor.executeReturnInMutexThrowable(
                    voucherKey, () -> this.createTask(checker, voucherCategoryKey, finalVoucherKey, createInfo)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private LongIdKey createTask(
            Checker checker, StringIdKey voucherCategoryKey, LongIdKey voucherKey, VoucherCreateInfo createInfo
    ) throws Exception {
        // 展开参数。
        String content = createInfo.getContent();
        String remark = createInfo.getRemark();

        // 构造凭证创建信息。
        VoucherCreateInfo voucherCreateInfo = new VoucherCreateInfo(voucherCategoryKey, voucherKey, content, remark);

        // 调用操作处理器，创建凭证。
        voucherKey = voucherOperateHandler.create(voucherCreateInfo);

        // 调用检查器方法，初始化凭证。
        checker.afterVoucherInitialize(voucherCategoryKey, voucherKey);

        // 返回凭证主键。
        return voucherKey;
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
            // 确认凭证对应的凭证类型存在。
            Voucher voucher = voucherMaintainService.get(voucherKey);
            StringIdKey voucherCategoryKey = voucher.getCategoryKey();
            handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);
            // 确认凭证类型使能。
            handlerValidator.makeSureVoucherCategoryEnabled(voucherCategoryKey);
            // 确认凭证类型对应的检查器信息存在。
            handlerValidator.makeSureCheckerInfoExists(voucherCategoryKey);

            // 获取凭证类型对应的检查器。
            CheckInfo checkInfo = checkLocalCacheHandler.get(voucherCategoryKey);
            Checker checker = checkInfo.getChecker();

            // 在分布式锁中执行查看任务，并返回结果。
            return voucherMutexProcessor.executeReturnInMutexThrowable(
                    voucherKey, () -> this.inspectTask(checker, voucherCategoryKey, voucherKey, inspectInfo)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private VoucherInspectResult inspectTask(
            Checker checker, StringIdKey voucherCategoryKey, LongIdKey voucherKey, VoucherInspectInfo inspectInfo
    ) throws Exception {
        // 调用检查器方法，检查凭证。
        checker.doVoucherValidCheck(voucherCategoryKey, voucherKey);

        // 获取最新的凭证实体。
        Voucher voucher = voucherMaintainService.getIfExists(voucherKey);

        // 如果凭证为 null 或无效，则抛出相应异常。
        if (Objects.isNull(voucher) || !voucher.isValid()) {
            // 调用检查器相应方法。
            checker.afterVoucherInspectFailed(voucherCategoryKey, voucherKey);
            // 抛出 InvalidVoucherException。
            throw new InvalidVoucherException(voucherKey);
        }

        // 调用操作处理器，查看凭证。
        VoucherInspectResult voucherInspectResult;
        try {
            voucherInspectResult = voucherOperateHandler.inspect(inspectInfo);
        } catch (Exception e) {
            // 调用检查器相应方法。
            checker.afterVoucherInspectFailed(voucherCategoryKey, voucherKey);
            // 抛出异常。
            throw e;
        }

        // 调用检查器相应方法。
        checker.afterVoucherInspectSucceed(voucherCategoryKey, voucherKey);

        // 返回结果。
        return voucherInspectResult;
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

            // 在分布式锁中执行作废任务。
            voucherMutexProcessor.executeInMutexThrowable(
                    voucherKey, () -> this.invalidTask(invalidInfo)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void invalidTask(VoucherInvalidInfo invalidInfo) throws Exception {
        // 调用操作处理器，作废凭证。
        voucherOperateHandler.invalid(invalidInfo);
    }

    @Override
    public void remove(VoucherRemoveInfo removeInfo) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey voucherKey = removeInfo.getVoucherKey();

            // 确认凭证存在。
            handlerValidator.makeSureVoucherExists(voucherKey);

            // 在分布式锁中执行删除任务。
            voucherMutexProcessor.executeInMutexThrowable(
                    voucherKey, () -> this.removeTask(removeInfo)
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void removeTask(VoucherRemoveInfo removeInfo) throws Exception {
        // 调用操作处理器，删除凭证。
        voucherOperateHandler.remove(removeInfo);
    }

    @Override
    public CompletableFuture<Void> checkValid() throws HandlerException {
        try {
            return CompletableFuture.runAsync(this::checkValid0, executor);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void checkValid0() throws CompletionException {
        try {
            // 获取所有有效的凭证。
            List<Voucher> validVouchers = voucherMaintainService.lookupAsList(
                    VoucherMaintainService.VALID, new Object[0]
            );
            // 遍历所有有效的凭证，检查其有效性，并将无效的凭证加入到列表中。
            for (Voucher voucher : validVouchers) {
                checkSingleVoucherValid(voucher);
            }
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    private void checkSingleVoucherValid(Voucher voucher) throws Exception {
        // 展开参数。
        LongIdKey voucherKey = voucher.getKey();
        StringIdKey voucherCategoryKey = voucher.getCategoryKey();

        // 确认凭证对应的凭证类型存在。
        handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);
        // 确认凭证类型使能。
        handlerValidator.makeSureVoucherCategoryEnabled(voucherCategoryKey);
        // 确认凭证类型对应的检查器信息存在。
        handlerValidator.makeSureCheckerInfoExists(voucherCategoryKey);

        // 获取凭证类型对应的检查器。
        CheckInfo checkInfo = checkLocalCacheHandler.get(voucherCategoryKey);
        Checker checker = checkInfo.getChecker();

        // 在分布式锁中执行检查任务。
        voucherMutexProcessor.executeInMutexThrowable(
                voucherKey, () -> this.checkSingleVoucherValidTask(checker, voucherCategoryKey, voucherKey)
        );
    }

    private void checkSingleVoucherValidTask(Checker checker, StringIdKey voucherCategoryKey, LongIdKey voucherKey)
            throws Exception {
        // 调用检查器方法，检查凭证。
        checker.doVoucherValidCheck(voucherCategoryKey, voucherKey);
    }

    @Override
    public CompletableFuture<Void> removeInvalid() throws HandlerException {
        try {
            return CompletableFuture.runAsync(this::removeInvalid0, executor);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void removeInvalid0() throws CompletionException {
        try {
            // 获取所有无效的凭证。
            List<Voucher> invalidVouchers = voucherMaintainService.lookupAsList(
                    VoucherMaintainService.INVALID, new Object[0]
            );
            // 遍历所有无效的凭证，删除凭证。
            for (Voucher voucher : invalidVouchers) {
                removeSingleInvalid(voucher);
            }
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    private void removeSingleInvalid(Voucher voucher) throws Exception {
        // 展开参数。
        LongIdKey voucherKey = voucher.getKey();
        StringIdKey voucherCategoryKey = voucher.getCategoryKey();

        // 确认凭证对应的凭证类型存在。
        handlerValidator.makeSureVoucherCategoryExists(voucherCategoryKey);

        // 在分布式锁中执行删除任务。
        voucherMutexProcessor.executeInMutexThrowable(
                voucherKey, () -> this.removeSingleInvalidTask(voucherKey)
        );
    }

    private void removeSingleInvalidTask(LongIdKey voucherKey) throws Exception {
        // 删除凭证。
        voucherOperateHandler.remove(new VoucherRemoveInfo(voucherKey));
    }
}
