package com.dwarfeng.voucher.sdk.bean.key.format;

import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;

import java.util.Objects;

/**
 * VoucherCategoryVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryVariableStringKeyFormatter implements StringKeyFormatter<VoucherCategoryVariableKey> {

    private String prefix;

    public VoucherCategoryVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(VoucherCategoryVariableKey key) {
        Objects.requireNonNull(key);
        return String.format("%s%s_%s", prefix, key.getVoucherCategoryId(), key.getVariableId());
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
        return "VoucherCategoryVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
