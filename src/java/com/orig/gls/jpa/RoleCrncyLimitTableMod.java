package com.orig.gls.jpa;
// Generated May 25, 2016 10:46:27 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RoleCrncyLimitTableMod generated by hbm2java
 */
@Entity
@Table(name="ROLE_CRNCY_LIMIT_TABLE_MOD"
    ,schema="GLS"
)
public class RoleCrncyLimitTableMod  implements java.io.Serializable {


     private int modId;
     private String bankId;
     private String crncyCode;
     private String delFlg;
     private Date lchgDate;
     private int limitId;
     private Date rcreTime;
     private String roleId;
     private double userCashDrLim;
     private double userIntersolCashDrLimit;
     private double userIntersolXferDrLimit;
     private double userXferDrLim;

    public RoleCrncyLimitTableMod() {
    }

    public RoleCrncyLimitTableMod(int modId, String bankId, String crncyCode, String delFlg, Date lchgDate, int limitId, Date rcreTime, String roleId, double userCashDrLim, double userIntersolCashDrLimit, double userIntersolXferDrLimit, double userXferDrLim) {
       this.modId = modId;
       this.bankId = bankId;
       this.crncyCode = crncyCode;
       this.delFlg = delFlg;
       this.lchgDate = lchgDate;
       this.limitId = limitId;
       this.rcreTime = rcreTime;
       this.roleId = roleId;
       this.userCashDrLim = userCashDrLim;
       this.userIntersolCashDrLimit = userIntersolCashDrLimit;
       this.userIntersolXferDrLimit = userIntersolXferDrLimit;
       this.userXferDrLim = userXferDrLim;
    }
   
     @Id 

    
    @Column(name="MOD_ID", unique=true, nullable=false, precision=10, scale=0)
    public int getModId() {
        return this.modId;
    }
    
    public void setModId(int modId) {
        this.modId = modId;
    }

    
    @Column(name="BANK_ID", nullable=false, length=8)
    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
    @Column(name="CRNCY_CODE", nullable=false, length=3)
    public String getCrncyCode() {
        return this.crncyCode;
    }
    
    public void setCrncyCode(String crncyCode) {
        this.crncyCode = crncyCode;
    }

    
    @Column(name="DEL_FLG", nullable=false, length=1)
    public String getDelFlg() {
        return this.delFlg;
    }
    
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="LCHG_DATE", nullable=false, length=7)
    public Date getLchgDate() {
        return this.lchgDate;
    }
    
    public void setLchgDate(Date lchgDate) {
        this.lchgDate = lchgDate;
    }

    
    @Column(name="LIMIT_ID", nullable=false, precision=10, scale=0)
    public int getLimitId() {
        return this.limitId;
    }
    
    public void setLimitId(int limitId) {
        this.limitId = limitId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="RCRE_TIME", nullable=false, length=7)
    public Date getRcreTime() {
        return this.rcreTime;
    }
    
    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    
    @Column(name="ROLE_ID", nullable=false, length=15)
    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    @Column(name="USER_CASH_DR_LIM", nullable=false, precision=126, scale=0)
    public double getUserCashDrLim() {
        return this.userCashDrLim;
    }
    
    public void setUserCashDrLim(double userCashDrLim) {
        this.userCashDrLim = userCashDrLim;
    }

    
    @Column(name="USER_INTERSOL_CASH_DR_LIMIT", nullable=false, precision=126, scale=0)
    public double getUserIntersolCashDrLimit() {
        return this.userIntersolCashDrLimit;
    }
    
    public void setUserIntersolCashDrLimit(double userIntersolCashDrLimit) {
        this.userIntersolCashDrLimit = userIntersolCashDrLimit;
    }

    
    @Column(name="USER_INTERSOL_XFER_DR_LIMIT", nullable=false, precision=126, scale=0)
    public double getUserIntersolXferDrLimit() {
        return this.userIntersolXferDrLimit;
    }
    
    public void setUserIntersolXferDrLimit(double userIntersolXferDrLimit) {
        this.userIntersolXferDrLimit = userIntersolXferDrLimit;
    }

    
    @Column(name="USER_XFER_DR_LIM", nullable=false, precision=126, scale=0)
    public double getUserXferDrLim() {
        return this.userXferDrLim;
    }
    
    public void setUserXferDrLim(double userXferDrLim) {
        this.userXferDrLim = userXferDrLim;
    }




}

