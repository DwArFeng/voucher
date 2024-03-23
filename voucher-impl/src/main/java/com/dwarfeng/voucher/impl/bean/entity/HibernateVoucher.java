package com.dwarfeng.voucher.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.voucher.sdk.util.Constraints;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_voucher")
public class HibernateVoucher implements Bean {

    private static final long serialVersionUID = -1549730153622909066L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "category_id", length = Constraints.LENGTH_STRING_ID)
    private String categoryLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "valid", nullable = false)
    private boolean valid;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateVoucherCategory.class)
    @JoinColumns({ //
            @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateVoucherCategory voucherCategory;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVoucherVariable.class, mappedBy = "voucher")
    private Set<HibernateVoucherVariable> voucherVariables = new HashSet<>();

    public HibernateVoucher() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateStringIdKey getCategoryKey() {
        return Optional.ofNullable(categoryLongId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setCategoryKey(HibernateStringIdKey key) {
        this.categoryLongId = Optional.ofNullable(key).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public void setCategoryLongId(String categoryLongId) {
        this.categoryLongId = categoryLongId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public HibernateVoucherCategory getVoucherCategory() {
        return voucherCategory;
    }

    public void setVoucherCategory(HibernateVoucherCategory voucherCategory) {
        this.voucherCategory = voucherCategory;
    }

    public Set<HibernateVoucherVariable> getVoucherVariables() {
        return voucherVariables;
    }

    public void setVoucherVariables(Set<HibernateVoucherVariable> voucherVariables) {
        this.voucherVariables = voucherVariables;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "categoryLongId = " + categoryLongId + ", " +
                "content = " + content + ", " +
                "remark = " + remark + ", " +
                "valid = " + valid + ", " +
                "voucherCategory = " + voucherCategory + ")";
    }
}
