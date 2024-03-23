package com.dwarfeng.voucher.impl.bean.entity;

import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.impl.bean.key.HibernateVoucherCategoryVariableKey;
import com.dwarfeng.voucher.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(HibernateVoucherCategoryVariableKey.class)
@Table(name = "tbl_voucher_category_variable")
public class HibernateVoucherCategoryVariable implements Bean {

    private static final long serialVersionUID = -1014070152688811245L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "voucher_category_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String voucherCategoryId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_VARIABLE_ID, nullable = false)
    private String variableId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateVoucherCategory.class)
    @JoinColumns({ //
            @JoinColumn(name = "voucher_category_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateVoucherCategory voucherCategory;

    public HibernateVoucherCategoryVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateVoucherCategoryVariableKey getKey() {
        return new HibernateVoucherCategoryVariableKey(voucherCategoryId, variableId);
    }

    public void setKey(HibernateVoucherCategoryVariableKey key) {
        if (Objects.isNull(key)) {
            this.voucherCategoryId = null;
            this.variableId = null;
        } else {
            this.voucherCategoryId = key.getVoucherCategoryId();
            this.variableId = key.getVariableId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getVoucherCategoryId() {
        return voucherCategoryId;
    }

    public void setVoucherCategoryId(String voucherCategoryId) {
        this.voucherCategoryId = voucherCategoryId;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public HibernateVoucherCategory getVoucherCategory() {
        return voucherCategory;
    }

    public void setVoucherCategory(HibernateVoucherCategory voucherCategory) {
        this.voucherCategory = voucherCategory;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "voucherCategoryId = " + voucherCategoryId + ", " +
                "variableId = " + variableId + ", " +
                "value = " + value + ", " +
                "lastUpdatedDate = " + lastUpdatedDate + ", " +
                "voucherCategory = " + voucherCategory + ")";
    }
}
