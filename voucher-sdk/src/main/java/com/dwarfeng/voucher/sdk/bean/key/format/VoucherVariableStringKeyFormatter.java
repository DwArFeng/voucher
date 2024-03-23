package com.dwarfeng.voucher.sdk.bean.key.format;

import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;

import java.util.Objects;

/**
 * VoucherVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherVariableStringKeyFormatter implements StringKeyFormatter<VoucherVariableKey> {

    private String prefix;

    public VoucherVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(VoucherVariableKey key) {
        Objects.requireNonNull(key);
        return String.format("%s%s_%s", prefix, key.getVoucherId(), key.getVariableId());
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "VoucherVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
