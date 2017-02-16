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
 * CountryCodes generated by hbm2java
 */
@Entity
@Table(name="COUNTRY_CODES"
    ,schema="GLS"
)
public class CountryCodes  implements java.io.Serializable {


     private String countryCode;
     private int countryId;
     private String countryName;
     private String enabledFlg;
     private Date rcreTime;

    public CountryCodes() {
    }

	
    public CountryCodes(String countryCode, String countryName, String enabledFlg, Date rcreTime) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.enabledFlg = enabledFlg;
        this.rcreTime = rcreTime;
    }
    public CountryCodes(String countryCode, int countryId, String countryName, String enabledFlg, Date rcreTime) {
       this.countryCode = countryCode;
       this.countryId = countryId;
       this.countryName = countryName;
       this.enabledFlg = enabledFlg;
       this.rcreTime = rcreTime;
    }
   
     @Id 

    
    @Column(name="COUNTRY_CODE", unique=true, nullable=false, length=5)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    
    @Column(name="COUNTRY_ID", precision=10, scale=0)
    public int getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    
    @Column(name="COUNTRY_NAME", nullable=false, length=50)
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

