package com.dwarfeng.voucher.impl.service.telqos;

import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.voucher.stack.service.CleanupQosService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class CleanupCommand extends CliCommand {

    private static final String COMMAND_OPTION_ONLINE = "online";
    private static final String COMMAND_OPTION_OFFLINE = "offline";
    private static final String COMMAND_OPTION_START = "start";
    private static final String COMMAND_OPTION_STOP = "stop";
    private static final String COMMAND_OPTION_STATUS = "status";
    private static final String COMMAND_OPTION_CLEANUP = "cleanup";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_ONLINE,
            COMMAND_OPTION_OFFLINE,
            COMMAND_OPTION_START,
            COMMAND_OPTION_STOP,
            COMMAND_OPTION_STATUS,
            COMMAND_OPTION_CLEANUP
    };

    private static final String IDENTITY = "cleanup";
    private static final String DESCRIPTION = "清理处理器操作/查看";

    private static final String CMD_LINE_SYNTAX_ONLINE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_ONLINE);
    private static final String CMD_LINE_SYNTAX_OFFLINE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OFFLINE);
    private static final String CMD_LINE_SYNTAX_START = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_START);
    private static final String CMD_LINE_SYNTAX_STOP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_STOP);
    private static final String CMD_LINE_SYNTAX_STATUS = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_STATUS);
    private static final String CMD_LINE_SYNTAX_CLEANUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEANUP);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_ONLINE,
            CMD_LINE_SYNTAX_OFFLINE,
            CMD_LINE_SYNTAX_START,
            CMD_LINE_SYNTAX_STOP,
            CMD_LINE_SYNTAX_STATUS,
            CMD_LINE_SYNTAX_CLEANUP
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final CleanupQosService cleanupQosService;

    public CleanupCommand(CleanupQosService cleanupQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.cleanupQosService = cleanupQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_ONLINE).desc("上线清理处理器").build());
        list.add(Option.builder(COMMAND_OPTION_OFFLINE).desc("下线清理处理器").build());
        list.add(Option.builder(COMMAND_OPTION_START).desc("启动清理处理器").build());
        list.add(Option.builder(COMMAND_OPTION_STOP).desc("停止清理处理器").build());
        list.add(Option.builder(COMMAND_OPTION_STATUS).desc("查看清理处理器状态").build());
        list.add(Option.builder(COMMAND_OPTION_CLEANUP).desc("立即执行清理作业").build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_ONLINE:
                    cleanupQosService.online();
                    context.sendMessage("清理处理器已上线!");
                    break;
                case COMMAND_OPTION_OFFLINE:
                    cleanupQosService.offline();
                    context.sendMessage("清理处理器已下线!");
                    break;
                case COMMAND_OPTION_START:
                    cleanupQosService.start();
                    context.sendMessage("清理处理器已启动!");
                    break;
                case COMMAND_OPTION_STOP:
                    cleanupQosService.stop();
                    context.sendMessage("清理处理器已停止!");
                    break;
                case COMMAND_OPTION_STATUS:
                    printStatus(context);
                    break;
                case COMMAND_OPTION_CLEANUP:
                    cleanupQosService.cleanup();
                    context.sendMessage("清理作业已执行!");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void printStatus(Context context) throws Exception {
        boolean onlineFlag = cleanupQosService.isOnline();
        boolean latchHoldingFlag = cleanupQosService.isLockHolding();
        boolean startedFlag = cleanupQosService.isStarted();
        boolean workingFlag = cleanupQosService.isWorking();

        context.sendMessage(String.format(
                "online: %b, latch holding: %b, started: %b, working: %b.",
                onlineFlag, latchHoldingFlag, startedFlag, workingFlag
        ));
    }
}
