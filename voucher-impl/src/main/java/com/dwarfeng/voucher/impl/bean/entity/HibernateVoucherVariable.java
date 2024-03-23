package com.dwarfeng.voucher.impl.bean.entity;

import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.impl.bean.key.HibernateVoucherVariableKey;
import com.dwarfeng.voucher.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(HibernateVoucherVariableKey.class)
@Table(name = "tbl_voucher_variable")
public class HibernateVoucherVariable implements Bean {

    private static final long serialVersionUID = -3101376475434882262L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "voucher_id", nullable = false)
    private Long voucherId;

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
    @ManyToOne(targetEntity = HibernateVoucher.class)
    @JoinColumns({ //
            @JoinColumn(name = "voucher_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateVoucher voucher;

    public HibernateVoucherVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateVoucherVariableKey getKey() {
        return new HibernateVoucherVariableKey(voucherId, variableId);
    }

    public void setKey(HibernateVoucherVariableKey key) {
        if (Objects.isNull(key)) {
            this.voucherId = null;
            this.variableId = null;
        } else {
            this.voucherId = key.getVoucherId();
            this.variableId = key.getVariableId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
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

    public HibernateVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(HibernateVoucher voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "voucherId = " + voucherId + ", " +
                "variableId = " + variableId + ", " +
                "value = " + value + ", " +
                "lastUpdatedDate = " + lastUpdatedDate + ", " +
                "voucher = " + voucher + ")";
    }
}
