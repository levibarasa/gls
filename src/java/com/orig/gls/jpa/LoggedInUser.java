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
 * LoggedInUser generated by hbm2java
 */
@Entity
@Table(name="LOGGED_IN_USER"
    ,schema="GLS"
)
public class LoggedInUser  implements java.io.Serializable {


     private int logId;
     private Date loggedInTime;
     private String sessionId;
     private String solId;
     private String userName;

    public LoggedInUser() {
    }

	
    public LoggedInUser(Date loggedInTime, String solId, String userName) {
        this.loggedInTime = loggedInTime;
        this.solId = solId;
        this.userName = userName;
    }
    public LoggedInUser(Date loggedInTime, String sessionId, String solId, String userName) {
       this.loggedInTime = loggedInTime;
       this.sessionId = sessionId;
       this.solId = solId;
       this.userName = userName;
    }
   
     @Id @GeneratedValue

    
    @Column(name="LOG_ID", unique=true, nullable=false, precision=10, scale=0)
    public int getLogId() {
        return this.logId;
    }
    
    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="LOGGED_IN_TIME", nullable=false, length=7)
    public Date getLoggedInTime() {
        return this.loggedInTime;
    }
    
    public void setLoggedInTime(Date loggedInTime) {
        this.loggedInTime = loggedInTime;
    }

    
    @Column(name="SESSION_ID", length=200)
    public String getSessionId() {
        return this.sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    
    @Column(name="SOL_ID", nullable=false, precision=10, scale=0)
    public String getSolId() {
        return this.solId;
    }
    
    public void setSolId(String solId) {
        this.solId = solId;
    }

    
    @Column(name="USER_NAME", nullable=false, length=30)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }




}

