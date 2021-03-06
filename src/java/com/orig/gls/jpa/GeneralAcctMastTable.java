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
 * GeneralAcctMastTable generated by hbm2java
 */
@Entity
@Table(name = "GENERAL_ACCT_MAST_TABLE", schema = "GLS"
)
public class GeneralAcctMastTable implements java.io.Serializable {

    private String acid;
    private String acctCrncyCode;
    private String acctMgrUserId;
    private String acctName;
    private Date acctOpnDate;
    private String acctOwnership;
    private String bankId;
    private double clrBalAmt;
    private int custId;
    private String delFlg;
    private double drwngPower;
    private String entityCreFlg;
    private String foracid;
    private String groupId;
    private Date lastModifiedDate;
    private Date lchgTime;
    private String lchgUserId;
    private double lienAmt;
    private String mappedFlg;
    private Date rcreTime;
    private String rcreUserId;
    private double sanctLim;
    private String schmCode;
    private String schmType;
    private String solId;
    private String subGroupId;

    public GeneralAcctMastTable() {
    }

    public GeneralAcctMastTable(String acid, String acctClassificationFlg, String acctClsFlg, String acctCrncyCode, String acctName, Date acctOpnDate, String acctOwnership, double clrBalAmt, double cumCrAmt, double cumDrAmt, int custId, String delFlg, double drwngPower, String entityCreFlg, String foracid, String frezCode, String glSubHeadCode, Date lastModifiedDate, Date lchgTime, String lchgUserId, double lienAmt, Date rcreTime, String rcreUserId, double sanctLim, String schmCode, String schmType, String solId, String systemOnlyAcctFlg, double wtaxPcnt) {
        this.acid = acid;
        this.acctCrncyCode = acctCrncyCode;
        this.acctName = acctName;
        this.acctOpnDate = acctOpnDate;
        this.acctOwnership = acctOwnership;
        this.clrBalAmt = clrBalAmt;
        this.custId = custId;
        this.delFlg = delFlg;
        this.drwngPower = drwngPower;
        this.entityCreFlg = entityCreFlg;
        this.foracid = foracid;
        this.lastModifiedDate = lastModifiedDate;
        this.lchgTime = lchgTime;
        this.lchgUserId = lchgUserId;
        this.lienAmt = lienAmt;
        this.rcreTime = rcreTime;
        this.rcreUserId = rcreUserId;
        this.sanctLim = sanctLim;
        this.schmCode = schmCode;
        this.schmType = schmType;
        this.solId = solId;
    }

    public GeneralAcctMastTable(String acid, String acctClassificationFlg, Date acctClsDate, String acctClsFlg, String acctCrncyCode, String acctMgrUserId, String acctName, Date acctOpnDate, String acctOwnership, String bankId, double clrBalAmt, double cumCrAmt, double cumDrAmt, int custId, String delFlg, double drwngPower, String entityCreFlg, String foracid, String frezCode, String glSubHeadCode, String groupId, String isLoanAgnstCltrlFlg, Date lastAnyTranDate, Date lastModifiedDate, Date lastTranDateCr, Date lastTranDateDr, Date lchgTime, String lchgUserId, double lienAmt, String mappedFlg, int operativeAcid, Date rcreTime, String rcreUserId, double sanctLim, String schmCode, String schmType, String solId, String subGroupId, String systemOnlyAcctFlg, double wtaxPcnt) {
        this.acid = acid;
        this.acctCrncyCode = acctCrncyCode;
        this.acctMgrUserId = acctMgrUserId;
        this.acctName = acctName;
        this.acctOpnDate = acctOpnDate;
        this.acctOwnership = acctOwnership;
        this.bankId = bankId;
        this.clrBalAmt = clrBalAmt;
        this.custId = custId;
        this.delFlg = delFlg;
        this.drwngPower = drwngPower;
        this.entityCreFlg = entityCreFlg;
        this.foracid = foracid;
        this.groupId = groupId;
        this.lastModifiedDate = lastModifiedDate;
        this.lchgTime = lchgTime;
        this.lchgUserId = lchgUserId;
        this.lienAmt = lienAmt;
        this.mappedFlg = mappedFlg;
        this.rcreTime = rcreTime;
        this.rcreUserId = rcreUserId;
        this.sanctLim = sanctLim;
        this.schmCode = schmCode;
        this.schmType = schmType;
        this.solId = solId;
        this.subGroupId = subGroupId;
    }

    @Id

    @Column(name = "ACID", unique = true, nullable = false, length = 20)
    public String getAcid() {
        return this.acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    @Column(name = "ACCT_CRNCY_CODE", nullable = false, length = 3)
    public String getAcctCrncyCode() {
        return this.acctCrncyCode;
    }

    public void setAcctCrncyCode(String acctCrncyCode) {
        this.acctCrncyCode = acctCrncyCode;
    }

    @Column(name = "ACCT_MGR_USER_ID", length = 15)
    public String getAcctMgrUserId() {
        return this.acctMgrUserId;
    }

    public void setAcctMgrUserId(String acctMgrUserId) {
        this.acctMgrUserId = acctMgrUserId;
    }

    @Column(name = "ACCT_NAME", nullable = false, length = 80)
    public String getAcctName() {
        return this.acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ACCT_OPN_DATE", nullable = false, length = 7)
    public Date getAcctOpnDate() {
        return this.acctOpnDate;
    }

    public void setAcctOpnDate(Date acctOpnDate) {
        this.acctOpnDate = acctOpnDate;
    }

    @Column(name = "ACCT_OWNERSHIP", nullable = false, length = 1)
    public String getAcctOwnership() {
        return this.acctOwnership;
    }

    public void setAcctOwnership(String acctOwnership) {
        this.acctOwnership = acctOwnership;
    }

    @Column(name = "BANK_ID", length = 8)
    public String getBankId() {
        return this.bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Column(name = "CLR_BAL_AMT", nullable = false, precision = 126, scale = 0)
    public double getClrBalAmt() {
        return this.clrBalAmt;
    }

    public void setClrBalAmt(double clrBalAmt) {
        this.clrBalAmt = clrBalAmt;
    }

    @Column(name = "CUST_ID", nullable = false, precision = 10, scale = 0)
    public int getCustId() {
        return this.custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    @Column(name = "DEL_FLG", nullable = false, length = 1)
    public String getDelFlg() {
        return this.delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    @Column(name = "DRWNG_POWER", nullable = false, precision = 126, scale = 0)
    public double getDrwngPower() {
        return this.drwngPower;
    }

    public void setDrwngPower(double drwngPower) {
        this.drwngPower = drwngPower;
    }

    @Column(name = "ENTITY_CRE_FLG", nullable = false, length = 1)
    public String getEntityCreFlg() {
        return this.entityCreFlg;
    }

    public void setEntityCreFlg(String entityCreFlg) {
        this.entityCreFlg = entityCreFlg;
    }

    @Column(name = "FORACID", nullable = false, length = 16)
    public String getForacid() {
        return this.foracid;
    }

    public void setForacid(String foracid) {
        this.foracid = foracid;
    }

    @Column(name = "GROUP_CODE", length = 10)
    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MODIFIED_DATE", nullable = false, length = 7)
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LCHG_TIME", nullable = false, length = 7)
    public Date getLchgTime() {
        return this.lchgTime;
    }

    public void setLchgTime(Date lchgTime) {
        this.lchgTime = lchgTime;
    }

    @Column(name = "LCHG_USER_ID", nullable = false, length = 15)
    public String getLchgUserId() {
        return this.lchgUserId;
    }

    public void setLchgUserId(String lchgUserId) {
        this.lchgUserId = lchgUserId;
    }

    @Column(name = "LIEN_AMT", nullable = false, precision = 126, scale = 0)
    public double getLienAmt() {
        return this.lienAmt;
    }

    public void setLienAmt(double lienAmt) {
        this.lienAmt = lienAmt;
    }

    @Column(name = "MAPPED_FLG", length = 2)
    public String getMappedFlg() {
        return this.mappedFlg;
    }

    public void setMappedFlg(String mappedFlg) {
        this.mappedFlg = mappedFlg;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "RCRE_TIME", nullable = false, length = 7)
    public Date getRcreTime() {
        return this.rcreTime;
    }

    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    @Column(name = "RCRE_USER_ID", nullable = false, length = 15)
    public String getRcreUserId() {
        return this.rcreUserId;
    }

    public void setRcreUserId(String rcreUserId) {
        this.rcreUserId = rcreUserId;
    }

    @Column(name = "SANCT_LIM", nullable = false, precision = 126, scale = 0)
    public double getSanctLim() {
        return this.sanctLim;
    }

    public void setSanctLim(double sanctLim) {
        this.sanctLim = sanctLim;
    }

    @Column(name = "SCHM_CODE", nullable = false, length = 5)
    public String getSchmCode() {
        return this.schmCode;
    }

    public void setSchmCode(String schmCode) {
        this.schmCode = schmCode;
    }

    @Column(name = "SCHM_TYPE", nullable = false, length = 3)
    public String getSchmType() {
        return this.schmType;
    }

    public void setSchmType(String schmType) {
        this.schmType = schmType;
    }

    @Column(name = "SOL_ID", nullable = false, length = 8)
    public String getSolId() {
        return this.solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    @Column(name = "SUB_GROUP_CODE", length = 10)
    public String getSubGroupId() {
        return this.subGroupId;
    }

    public void setSubGroupId(String subGroupId) {
        this.subGroupId = subGroupId;
    }

}
