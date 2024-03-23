package com.dwarfeng.voucher.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.stack.bean.entity.CheckerSupport;

import java.util.Objects;

/**
 * FastJson 检查器支持。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonCheckerSupport implements Bean {

    private static final long serialVersionUID = 5289064426297236852L;

    public static FastJsonCheckerSupport of(CheckerSupport checkerSupport) {
        if (Objects.isNull(checkerSupport)) {
            return null;
        } else {
            return new FastJsonCheckerSupport(
                    FastJsonStringIdKey.of(checkerSupport.getKey()),
                    checkerSupport.getLabel(),
                    checkerSupport.getDescription(),
                    checkerSupport.getExampleParam()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "description", ordinal = 3)
    private String description;

    @JSONField(name = "example_param", ordinal = 4)
    private String exampleParam;

    public FastJsonCheckerSupport() {
    }

    public FastJsonCheckerSupport(FastJsonStringIdKey key, String label, String description, String exampleParam) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleParam = exampleParam;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
    }

    @Override
    public String toString() {
        return "FastJsonCheckerSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleParam='" + exampleParam + '\'' +
                '}';
    }
}
