package com.dwarfeng.voucher.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.sdk.util.Constraints;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_voucher_category")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateVoucherCategory implements Bean {

    private static final long serialVersionUID = 6594776344472919224L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_TYPE, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(cascade = CascadeType.MERGE, targetEntity = HibernateCheckerInfo.class, mappedBy = "voucherCategory")
    private HibernateCheckerInfo checkerInfo;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVoucherCategoryVariable.class, mappedBy = "voucherCategory")
    private Set<HibernateVoucherCategoryVariable> voucherCategoryVariables = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVoucher.class, mappedBy = "voucherCategory")
    private Set<HibernateVoucher> vouchers = new HashSet<>();

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "voucherCategoryDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "voucherCategoryDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    public HibernateVoucherCategory() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateStringIdKey getKey() {
        return new HibernateStringIdKey(stringId);
    }

    public void setKey(StringIdKey key) {
        this.stringId = key.getStringId();
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateCheckerInfo getCheckerInfo() {
        return checkerInfo;
    }

    public void setCheckerInfo(HibernateCheckerInfo checkerInfo) {
        this.checkerInfo = checkerInfo;
    }

    public Set<HibernateVoucherCategoryVariable> getVoucherCategoryVariables() {
        return voucherCategoryVariables;
    }

    public void setVoucherCategoryVariables(Set<HibernateVoucherCategoryVariable> voucherCategoryVariables) {
        this.voucherCategoryVariables = voucherCategoryVariables;
    }

    public Set<HibernateVoucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Set<HibernateVoucher> vouchers) {
        this.vouchers = vouchers;
    }

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "enabled = " + enabled + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "checkerInfo = " + checkerInfo + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
