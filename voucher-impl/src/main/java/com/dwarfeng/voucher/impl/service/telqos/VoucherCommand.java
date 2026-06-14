package com.dwarfeng.voucher.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.voucher.sdk.bean.dto.WebInputVoucherCreateInfo;
import com.dwarfeng.voucher.stack.bean.dto.*;
import com.dwarfeng.voucher.stack.service.VoucherQosService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class VoucherCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "voucher";

    // region 指令选项

    private static final String COMMAND_OPTION_CREATE = "create";
    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_INVALID = "invalid";
    private static final String COMMAND_OPTION_REMOVE = "remove";
    private static final String COMMAND_OPTION_CHECK_VALID = "cv";
    private static final String COMMAND_OPTION_CHECK_VALID_LONG_OPT = "check-valid";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String COMMAND_OPTION_REMOVE_INVALID = "rinv";
    private static final String COMMAND_OPTION_REMOVE_INVALID_LONG_OPT = "remove-invalid";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_CREATE,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_INVALID,
            COMMAND_OPTION_REMOVE,
            COMMAND_OPTION_CHECK_VALID,
            COMMAND_OPTION_REMOVE_INVALID
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final VoucherQosService voucherQosService;

    public VoucherCommand(VoucherQosService voucherQosService) {
        super(IDENTITY);
        this.voucherQosService = voucherQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "凭证操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CREATE) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INVALID) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CHECK_VALID),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_INVALID)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_CREATE).optionalArg(true).hasArg(false).desc("创建凭证").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false).desc("查看凭证").build());
        list.add(Option.builder(COMMAND_OPTION_INVALID).optionalArg(true).hasArg(false).desc("作废凭证").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE).optionalArg(true).hasArg(false).desc("删除凭证").build());
        list.add(Option.builder(COMMAND_OPTION_CHECK_VALID).longOpt(COMMAND_OPTION_CHECK_VALID_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("对服务中所有有效的凭证执行检查").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE_INVALID).longOpt(COMMAND_OPTION_REMOVE_INVALID_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("移除服务中所有作废的凭证").build());
        list.add(Option.builder(COMMAND_SUB_OPTION_JSON).hasArg(true).type(String.class).desc("JSON 字符串").build());
        list.add(Option.builder(COMMAND_SUB_OPTION_JSON_FILE).longOpt(COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT)
                .hasArg(true).type(File.class).desc("JSON 文件").build());
        return list;
    }

    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        switch (pair.getLeft()) {
            case COMMAND_OPTION_CREATE:
                handleCreate(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT:
                handleInspect(context, cmd);
                break;
            case COMMAND_OPTION_INVALID:
                handleInvalid(context, cmd);
                break;
            case COMMAND_OPTION_REMOVE:
                handleRemove(context, cmd);
                break;
            case COMMAND_OPTION_CHECK_VALID:
                handleCheckValid(context);
                break;
            case COMMAND_OPTION_REMOVE_INVALID:
                handleRemoveInvalid(context);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleCreate(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        VoucherCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VoucherCreateInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputVoucherCreateInfo.toStackBean(
                    JSON.parseObject(json, WebInputVoucherCreateInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VoucherCreateInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputVoucherCreateInfo.toStackBean(
                        JSON.parseObject(in, WebInputVoucherCreateInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 创建凭证。
        LongIdKey voucherKey = voucherQosService.create(info);

        // 输出结果。
        context.sendMessage("voucherKey: " + voucherKey);
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        VoucherInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VoucherInspectInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = JSON.parseObject(json, VoucherInspectInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VoucherInspectInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, VoucherInspectInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查看凭证。
        VoucherInspectResult result = voucherQosService.inspect(info);

        // 输出结果。
        context.sendMessage("voucherKey: " + result.getVoucherKey());
        context.sendMessage("content: " + result.getContent());
        context.sendMessage("remark: " + result.getRemark());
    }

    private void handleInvalid(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        VoucherInvalidInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VoucherInvalidInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = JSON.parseObject(json, VoucherInvalidInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VoucherInvalidInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, VoucherInvalidInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 作废凭证。
        voucherQosService.invalid(info);

        // 输出结果。
        context.sendMessage("作废成功");
    }

    private void handleRemove(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        VoucherRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VoucherRemoveInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = JSON.parseObject(json, VoucherRemoveInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VoucherRemoveInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, VoucherRemoveInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 删除凭证。
        voucherQosService.remove(info);

        // 输出结果。
        context.sendMessage("删除成功");
    }

    private void handleCheckValid(CommandExecutor.Context context) throws Exception {
        // 检查有效的凭证。
        voucherQosService.checkValid().join();
        // 输出结果。
        context.sendMessage("操作成功，已检查所有有效的凭证");
    }

    private void handleRemoveInvalid(CommandExecutor.Context context) throws Exception {
        // 移除无效的凭证。
        voucherQosService.removeInvalid().join();
        // 输出结果。
        context.sendMessage("操作成功，已移除所有无效的凭证");
    }
}
