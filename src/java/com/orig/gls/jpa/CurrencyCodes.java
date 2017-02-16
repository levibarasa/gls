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
 * CurrencyCodes generated by hbm2java
 */
@Entity
@Table(name="CURRENCY_CODES"
    ,schema="GLS"
)
public class CurrencyCodes  implements java.io.Serializable {


     private int currencyId;
     private String curerncyName;
     private String currencyCode;
     private String enabledFlg;
     private Date rcreTime;

    public CurrencyCodes() {
    }

    public CurrencyCodes(String curerncyName, String currencyCode, String enabledFlg, Date rcreTime) {
       this.curerncyName = curerncyName;
       this.currencyCode = currencyCode;
       this.enabledFlg = enabledFlg;
       this.rcreTime = rcreTime;
    }
   
     @Id @GeneratedValue

    
    @Column(name="CURRENCY_ID", unique=true, nullable=false, precision=10, scale=0)
    public int getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    
    @Column(name="CURERNCY_NAME", nullable=false, length=50)
    public String getCurerncyName() {
        return this.curerncyName;
    }
    
    public void setCurerncyName(String curerncyName) {
        this.curerncyName = curerncyName;
    }

    
    @Column(name="CURRENCY_CODE", nullable=false, length=3)
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    
    @Column(name="ENABLED_FLG", nullable=false, length=1)
    public String getEnabledFlg() {
        return this.enabledFlg;
    }
    
    public void setEnabledFlg(String enabledFlg) {
        this.enabledFlg = enabledFlg;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="RCRE_TIME", nullable=false, length=7)
    public Date getRcreTime() {
        return this.rcreTime;
    }
    
    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }




}


