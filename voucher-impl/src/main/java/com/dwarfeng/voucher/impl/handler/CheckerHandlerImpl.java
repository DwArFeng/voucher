package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.bean.dto.*;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.exception.CheckerException;
import com.dwarfeng.voucher.stack.exception.CheckerExecutionException;
import com.dwarfeng.voucher.stack.exception.UnsupportedCheckerTypeException;
import com.dwarfeng.voucher.stack.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.*;

@Component
public class CheckerHandlerImpl implements CheckerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckerHandlerImpl.class);

    private final VoucherCategoryVariableOperateHandler voucherCategoryVariableOperateHandler;
    private final VoucherVariableOperateHandler voucherVariableOperateHandler;
    private final VoucherOperateHandler voucherOperateHandler;

    private final List<CheckerMaker> checkerMakers;

    private final InternalCheckerContext checkerContext = new InternalCheckerContext();

    public CheckerHandlerImpl(
            VoucherCategoryVariableOperateHandler voucherCategoryVariableOperateHandler,
            VoucherVariableOperateHandler voucherVariableOperateHandler,
            VoucherOperateHandler voucherOperateHandler,
            List<CheckerMaker> checkerMakers
    ) {
        this.voucherCategoryVariableOperateHandler = voucherCategoryVariableOperateHandler;
        this.voucherVariableOperateHandler = voucherVariableOperateHandler;
        this.voucherOperateHandler = voucherOperateHandler;
        this.checkerMakers = Optional.ofNullable(checkerMakers).orElse(Collections.emptyList());
    }

    @Override
    public Checker make(String type, String param) throws CheckerException {
        try {
            // 生成检查器。
            LOGGER.debug("通过检查器信息构建新的检查器...");
            CheckerMaker checkerMaker = checkerMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedCheckerTypeException(type));
            Checker checker = checkerMaker.makeChecker(type, param);
            LOGGER.debug("检查器构建成功!");
            checker.init(checkerContext);
            LOGGER.debug("检查器初始化成功!");
            LOGGER.debug("检查器: {}", checker);
            return checker;
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throw new CheckerException(e);
        }
    }

    private class InternalCheckerContext implements Checker.Context {

        @Nullable
        @Override
        public String inspectVoucherCategoryVariable(StringIdKey voucherCategoryKey, String variableId)
                throws CheckerException {
            try {
                // 构建 VoucherCategoryVariableInspectInfo。
                VoucherCategoryVariableInspectInfo inspectInfo = new VoucherCategoryVariableInspectInfo(
                        voucherCategoryKey, variableId
                );
                // 调用操作处理器执行查看方法，并获取变量。
                VoucherCategoryVariable variable = voucherCategoryVariableOperateHandler.inspect(inspectInfo);
                // 返回变量的值。
                return Optional.ofNullable(variable).map(VoucherCategoryVariable::getValue).orElse(null);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Nullable
        @Override
        public String inspectVoucherCategoryVariableOrDefault(
                StringIdKey voucherCategoryKey, String variableId, String defaultValue
        ) throws CheckerException {
            try {
                // 构建 VoucherCategoryVariableInspectInfo。
                VoucherCategoryVariableInspectInfo inspectInfo = new VoucherCategoryVariableInspectInfo(
                        voucherCategoryKey, variableId
                );
                // 调用操作处理器执行查看方法，并获取变量。
                VoucherCategoryVariable variable = voucherCategoryVariableOperateHandler.inspect(inspectInfo);
                // 返回变量的值。
                return Optional.ofNullable(variable).map(VoucherCategoryVariable::getValue).orElse(defaultValue);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Nullable
        @Override
        public String inspectVoucherVariable(LongIdKey voucherKey, String variableId) throws CheckerException {
            try {
                // 构建 VoucherVariableInspectInfo。
                VoucherVariableInspectInfo inspectInfo = new VoucherVariableInspectInfo(voucherKey, variableId);
                // 调用操作处理器执行查看方法，并获取变量。
                VoucherVariable variable = voucherVariableOperateHandler.inspect(inspectInfo);
                // 返回变量的值。
                return Optional.ofNullable(variable).map(VoucherVariable::getValue).orElse(null);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Nullable
        @Override
        public String inspectVoucherVariableOrDefault(LongIdKey voucherKey, String variableId, String defaultValue)
                throws CheckerException {
            try {
                // 构建 VoucherVariableInspectInfo。
                VoucherVariableInspectInfo inspectInfo = new VoucherVariableInspectInfo(voucherKey, variableId);
                // 调用操作处理器执行查看方法，并获取变量。
                VoucherVariable variable = voucherVariableOperateHandler.inspect(inspectInfo);
                // 返回变量的值。
                return Optional.ofNullable(variable).map(VoucherVariable::getValue).orElse(defaultValue);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Override
        public void putVoucherVariable(LongIdKey voucherKey, String variableId, String value) throws CheckerException {
            try {
                // 构建 VoucherVariablePutInfo。
                VoucherVariablePutInfo putInfo = new VoucherVariablePutInfo(voucherKey, variableId, value);
                // 调用操作处理器执行放入方法。
                voucherVariableOperateHandler.put(putInfo);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Override
        public void batchPutVoucherVariable(LongIdKey voucherKey, Map<String, String> valueMap) throws CheckerException {
            try {
                // 构建 VoucherVariablePutInfo 列表。
                List<VoucherVariablePutInfo> putInfoList = new ArrayList<>(valueMap.size());
                for (Map.Entry<String, String> entry : valueMap.entrySet()) {
                    putInfoList.add(new VoucherVariablePutInfo(voucherKey, entry.getKey(), entry.getValue()));
                }
                // 调用操作处理器执行批量放入方法。
                voucherVariableOperateHandler.batchPut(putInfoList);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Override
        public void removeVoucherVariable(LongIdKey voucherKey, String variableId) throws CheckerException {
            try {
                // 构建 VoucherVariableRemoveInfo。
                VoucherVariableRemoveInfo removeInfo = new VoucherVariableRemoveInfo(voucherKey, variableId);
                // 调用操作处理器执行放入方法。
                voucherVariableOperateHandler.remove(removeInfo);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }

        @Override
        public void invalidVoucher(LongIdKey voucherKey) throws CheckerException {
            try {
                // 构建 VoucherInvalidInfo。
                VoucherInvalidInfo invalidInfo = new VoucherInvalidInfo(voucherKey);
                // 调用操作处理器执行放入方法。
                voucherOperateHandler.invalid(invalidInfo);
            } catch (CheckerExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new CheckerExecutionException(e);
            }
        }
    }
}
