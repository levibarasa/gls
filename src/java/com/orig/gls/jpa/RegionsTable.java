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
 * RegionsTable generated by hbm2java
 */
@Entity
@Table(name="REGIONS_TABLE"
    ,schema="GLS"
)
public class RegionsTable  implements java.io.Serializable {


     private int regionId;
     private String bankId;
     private String countryCode;
     private String delFlg;
     private Date rcreTime;
     private String rcreUserId;
     private String regionName;

    public RegionsTable() {
    }

    public RegionsTable(String bankId, String countryCode, String delFlg, Date rcreTime, String rcreUserId, String regionName) {
       this.bankId = bankId;
       this.countryCode = countryCode;
       this.delFlg = delFlg;
       this.rcreTime = rcreTime;
       this.rcreUserId = rcreUserId;
       this.regionName = regionName;
    }
   
     @Id @GeneratedValue

    
    @Column(name="REGION_ID", unique=true, nullable=false, precision=10, scale=0)
    public int getRegionId() {
        return this.regionId;
    }
    
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    
    @Column(name="BANK_ID", nullable=false, length=8)
    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
    @Column(name="COUNTRY_CODE", nullable=false, length=2)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    
    @Column(name="DEL_FLG", nullable=false, length=1)
    public String getDelFlg() {
        return this.delFlg;
    }
    
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
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

    
    @Column(name="REGION_NAME", nullable=false, length=30)
    public String getRegionName() {
        return this.regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }




}

