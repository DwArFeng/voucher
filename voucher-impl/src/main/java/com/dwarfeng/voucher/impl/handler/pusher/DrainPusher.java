package com.dwarfeng.voucher.impl.handler.pusher;

import org.springframework.stereotype.Component;

/**
 * 简单的丢弃掉所有信息的推送器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class DrainPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "drain";

    public DrainPusher() {
        super(PUSHER_TYPE);
    }

    @Override
    public void checkReset() {
    }

    @Override
    public String toString() {
        return "DrainPusher{" +
                "pusherType='" + pusherType + '\'' +
                '}';
    }
}
