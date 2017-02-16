package com.orig.gls.jpa;
// Generated May 25, 2016 10:46:27 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RoleProfileTable generated by hbm2java
 */
@Entity
@Table(name="ROLE_PROFILE_TABLE"
    ,schema="GLS"
)
public class RoleProfileTable  implements java.io.Serializable {


     private int roleId;
     private String bankId;
     private String delFlg;
     private String entityCreFlg;
     private Date lchgTime;
     private String lchgUserId;
     private Date rcreTime;
     private String rcreUserId;
     private String roleDesc;

    public RoleProfileTable() {
    }

    public RoleProfileTable(String bankId, String delFlg, String entityCreFlg, Date lchgTime, String lchgUserId, Date rcreTime, String rcreUserId, String roleDesc) {
       this.bankId = bankId;
       this.delFlg = delFlg;
       this.entityCreFlg = entityCreFlg;
       this.lchgTime = lchgTime;
       this.lchgUserId = lchgUserId;
       this.rcreTime = rcreTime;
       this.rcreUserId = rcreUserId;
       this.roleDesc = roleDesc;
    }
   
     @Id @GeneratedValue

    
    @Column(name="ROLE_ID", unique=true, nullable=false, precision=10, scale=0)
    public int getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    
    @Column(name="BANK_ID", nullable=false, length=8)
    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
    @Column(name="DEL_FLG", nullable=false, length=1)
    public String getDelFlg() {
        return this.delFlg;
    }
    
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    
    @Column(name="ENTITY_CRE_FLG", nullable=false, length=1)
    public String getEntityCreFlg() {
        return this.entityCreFlg;
    }
    
    public void setEntityCreFlg(String entityCreFlg) {
        this.entityCreFlg = entityCreFlg;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="LCHG_TIME", nullable=false, length=7)
    public Date getLchgTime() {
        return this.lchgTime;
    }
    
    public void setLchgTime(Date lchgTime) {
        this.lchgTime = lchgTime;
    }

    
    @Column(name="LCHG_USER_ID", nullable=false, length=30)
    public String getLchgUserId() {
        return this.lchgUserId;
    }
    
    public void setLchgUserId(String lchgUserId) {
        this.lchgUserId = lchgUserId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="RCRE_TIME", nullable=false, length=7)
    public Date getRcreTime() {
        return this.rcreTime;
    }
    
    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    
    @Column(name="RCRE_USER_ID", nullable=false, length=30)
    public String getRcreUserId() {
        return this.rcreUserId;
    }
    
    public void setRcreUserId(String rcreUserId) {
        this.rcreUserId = rcreUserId;
    }

    
    @Column(name="ROLE_DESC", nullable=false, length=30)
    public String getRoleDesc() {
        return this.roleDesc;
    }
    
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }




}


