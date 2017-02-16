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
 * ResMappingMod generated by hbm2java
 */
@Entity
@Table(name="RES_MAPPING_MOD"
    ,schema="GLS"
)
public class ResMappingMod  implements java.io.Serializable {


     private int modId;
     private String delFlg;
     private int mapId;
     private String mopId;
     private String mopText;
     private String mopUrl;
     private Date rcreTime;
     private String rcreUser;
     private int resId;
     private String roleName;

    public ResMappingMod() {
    }

    public ResMappingMod(int modId, String delFlg, int mapId, String mopId, String mopText, String mopUrl, Date rcreTime, String rcreUser, int resId, String roleName) {
       this.modId = modId;
       this.delFlg = delFlg;
       this.mapId = mapId;
       this.mopId = mopId;
       this.mopText = mopText;
       this.mopUrl = mopUrl;
       this.rcreTime = rcreTime;
       this.rcreUser = rcreUser;
       this.resId = resId;
       this.roleName = roleName;
    }
   
     @Id 

    
    @Column(name="MOD_ID", unique=true, nullable=false, precision=10, scale=0)
    public int getModId() {
        return this.modId;
    }
    
    public void setModId(int modId) {
        this.modId = modId;
    }

    
    @Column(name="DEL_FLG", nullable=false, length=1)
    public String getDelFlg() {
        return this.delFlg;
    }
    
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    
    @Column(name="MAP_ID", nullable=false, precision=10, scale=0)
    public int getMapId() {
        return this.mapId;
    }
    
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    
    @Column(name="MOP_ID", nullable=false, length=10)
    public String getMopId() {
        return this.mopId;
    }
    
    public void setMopId(String mopId) {
        this.mopId = mopId;
    }

    
    @Column(name="MOP_TEXT", nullable=false, length=60)
    public String getMopText() {
        return this.mopText;
    }
    
    public void setMopText(String mopText) {
        this.mopText = mopText;
    }

    
    @Column(name="MOP_URL", nullable=false, length=100)
    public String getMopUrl() {
        return this.mopUrl;
    }
    
    public void setMopUrl(String mopUrl) {
        this.mopUrl = mopUrl;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="RCRE_TIME", nullable=false, length=7)
    public Date getRcreTime() {
        return this.rcreTime;
    }
    
    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    
    @Column(name="RCRE_USER", nullable=false, length=30)
    public String getRcreUser() {
        return this.rcreUser;
    }
    
    public void setRcreUser(String rcreUser) {
        this.rcreUser = rcreUser;
    }

    
    @Column(name="RES_ID", nullable=false, precision=10, scale=0)
    public int getResId() {
        return this.resId;
    }
    
    public void setResId(int resId) {
        this.resId = resId;
    }

    
    @Column(name="ROLE_NAME", nullable=false, length=50)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }




}


