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
 * SubGrpTableMod generated by hbm2java
 */
@Entity
@Table(name="SUB_GRP_TABLE_MOD"
    ,schema="GLS"
)
public class SubGrpTableMod  implements java.io.Serializable {


     private int modId;
     private String bankId;
     private String branchName;
     private String countryCode;
     private String delFlg;
     private Date firstMeetDate;
     private Date formationDate;
     private int groupId;
     private String lastOper;
     private Date lchgDate;
     private String lchgUserId;
     private int loanAccounts;
     private int maxAllowedMembers;
     private String meetFrequency;
     private String meetPlace;
     private String meetTime;
     private int noOfMembers;
     private Date nxtMeetDate;
     private double outstandingBal;
     private Date rcreTime;
     private String rcreUserId;
     private int savingAccounts;
     private double savingsAmt;
     private int solId;
     private String subGpChair;
     private String subGpRegion;
     private String subGpSecretary;
     private String subGpStatus;
     private String subGpStatusCode;
     private String subGpTreasurer;
     private String subGroupAddress;
     private String subGroupCenter;
     private String subGroupCode;
     private int subGroupId;
     private double subGroupLoans;
     private String subGroupName;
     private String subGroupPhone;
     private String subGroupVillage;
     private String subGrpMgrId;
     private String subGrpRegNo;

    public SubGrpTableMod() {
    }

	
    public SubGrpTableMod(int modId, String bankId, String branchName, String countryCode, String delFlg, Date firstMeetDate, Date formationDate, int groupId, String lastOper, Date lchgDate, String lchgUserId, int loanAccounts, int maxAllowedMembers, String meetFrequency, String meetPlace, int noOfMembers, Date nxtMeetDate, double outstandingBal, Date rcreTime, String rcreUserId, int savingAccounts, double savingsAmt, int solId, String subGpRegion, String subGpStatus, String subGpStatusCode, String subGroupAddress, String subGroupCenter, String subGroupCode, int subGroupId, double subGroupLoans, String subGroupName, String subGroupPhone, String subGroupVillage, String subGrpMgrId, String subGrpRegNo) {
        this.modId = modId;
        this.bankId = bankId;
        this.branchName = branchName;
        this.countryCode = countryCode;
        this.delFlg = delFlg;
        this.firstMeetDate = firstMeetDate;
        this.formationDate = formationDate;
        this.groupId = groupId;
        this.lastOper = lastOper;
        this.lchgDate = lchgDate;
        this.lchgUserId = lchgUserId;
        this.loanAccounts = loanAccounts;
        this.maxAllowedMembers = maxAllowedMembers;
        this.meetFrequency = meetFrequency;
        this.meetPlace = meetPlace;
        this.noOfMembers = noOfMembers;
        this.nxtMeetDate = nxtMeetDate;
        this.outstandingBal = outstandingBal;
        this.rcreTime = rcreTime;
        this.rcreUserId = rcreUserId;
        this.savingAccounts = savingAccounts;
        this.savingsAmt = savingsAmt;
        this.solId = solId;
        this.subGpRegion = subGpRegion;
        this.subGpStatus = subGpStatus;
        this.subGpStatusCode = subGpStatusCode;
        this.subGroupAddress = subGroupAddress;
        this.subGroupCenter = subGroupCenter;
        this.subGroupCode = subGroupCode;
        this.subGroupId = subGroupId;
        this.subGroupLoans = subGroupLoans;
        this.subGroupName = subGroupName;
        this.subGroupPhone = subGroupPhone;
        this.subGroupVillage = subGroupVillage;
        this.subGrpMgrId = subGrpMgrId;
        this.subGrpRegNo = subGrpRegNo;
    }
    public SubGrpTableMod(int modId, String bankId, String branchName, String countryCode, String delFlg, Date firstMeetDate, Date formationDate, int groupId, String lastOper, Date lchgDate, String lchgUserId, int loanAccounts, int maxAllowedMembers, String meetFrequency, String meetPlace, String meetTime, int noOfMembers, Date nxtMeetDate, double outstandingBal, Date rcreTime, String rcreUserId, int savingAccounts, double savingsAmt, int solId, String subGpChair, String subGpRegion, String subGpSecretary, String subGpStatus, String subGpStatusCode, String subGpTreasurer, String subGroupAddress, String subGroupCenter, String subGroupCode, int subGroupId, double subGroupLoans, String subGroupName, String subGroupPhone, String subGroupVillage, String subGrpMgrId, String subGrpRegNo) {
       this.modId = modId;
       this.bankId = bankId;
       this.branchName = branchName;
       this.countryCode = countryCode;
       this.delFlg = delFlg;
       this.firstMeetDate = firstMeetDate;
       this.formationDate = formationDate;
       this.groupId = groupId;
       this.lastOper = lastOper;
       this.lchgDate = lchgDate;
       this.lchgUserId = lchgUserId;
       this.loanAccounts = loanAccounts;
       this.maxAllowedMembers = maxAllowedMembers;
       this.meetFrequency = meetFrequency;
       this.meetPlace = meetPlace;
       this.meetTime = meetTime;
       this.noOfMembers = noOfMembers;
       this.nxtMeetDate = nxtMeetDate;
       this.outstandingBal = outstandingBal;
       this.rcreTime = rcreTime;
       this.rcreUserId = rcreUserId;
       this.savingAccounts = savingAccounts;
       this.savingsAmt = savingsAmt;
       this.solId = solId;
       this.subGpChair = subGpChair;
       this.subGpRegion = subGpRegion;
       this.subGpSecretary = subGpSecretary;
       this.subGpStatus = subGpStatus;
       this.subGpStatusCode = subGpStatusCode;
       this.subGpTreasurer = subGpTreasurer;
       this.subGroupAddress = subGroupAddress;
       this.subGroupCenter = subGroupCenter;
       this.subGroupCode = subGroupCode;
       this.subGroupId = subGroupId;
       this.subGroupLoans = subGroupLoans;
       this.subGroupName = subGroupName;
       this.subGroupPhone = subGroupPhone;
       this.subGroupVillage = subGroupVillage;
       this.subGrpMgrId = subGrpMgrId;
       this.subGrpRegNo = subGrpRegNo;
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

    
    @Column(name="BRANCH_NAME", nullable=false, length=50)
    public String getBranchName() {
        return this.branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
    @Column(name="FIRST_MEET_DATE", nullable=false, length=7)
    public Date getFirstMeetDate() {
        return this.firstMeetDate;
    }
    
    public void setFirstMeetDate(Date firstMeetDate) {
        this.firstMeetDate = firstMeetDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FORMATION_DATE", nullable=false, length=7)
    public Date getFormationDate() {
        return this.formationDate;
    }
    
    public void setFormationDate(Date formationDate) {
        this.formationDate = formationDate;
    }

    
    @Column(name="GROUP_ID", nullable=false, precision=10, scale=0)
    public int getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    
    @Column(name="LAST_OPER", nullable=false, length=1)
    public String getLastOper() {
        return this.lastOper;
    }
    
    public void setLastOper(String lastOper) {
        this.lastOper = lastOper;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="LCHG_DATE", nullable=false, length=7)
    public Date getLchgDate() {
        return this.lchgDate;
    }
    
    public void setLchgDate(Date lchgDate) {
        this.lchgDate = lchgDate;
    }

    
    @Column(name="LCHG_USER_ID", nullable=false, length=30)
    public String getLchgUserId() {
        return this.lchgUserId;
    }
    
    public void setLchgUserId(String lchgUserId) {
        this.lchgUserId = lchgUserId;
    }

    
    @Column(name="LOAN_ACCOUNTS", nullable=false, precision=10, scale=0)
    public int getLoanAccounts() {
        return this.loanAccounts;
    }
    
    public void setLoanAccounts(int loanAccounts) {
        this.loanAccounts = loanAccounts;
    }

    
    @Column(name="MAX_ALLOWED_MEMBERS", nullable=false, precision=10, scale=0)
    public int getMaxAllowedMembers() {
        return this.maxAllowedMembers;
    }
    
    public void setMaxAllowedMembers(int maxAllowedMembers) {
        this.maxAllowedMembers = maxAllowedMembers;
    }

    
    @Column(name="MEET_FREQUENCY", nullable=false, length=10)
    public String getMeetFrequency() {
        return this.meetFrequency;
    }
    
    public void setMeetFrequency(String meetFrequency) {
        this.meetFrequency = meetFrequency;
    }

    
    @Column(name="MEET_PLACE", nullable=false, length=50)
    public String getMeetPlace() {
        return this.meetPlace;
    }
    
    public void setMeetPlace(String meetPlace) {
        this.meetPlace = meetPlace;
    }

    
    @Column(name="MEET_TIME", length=20)
    public String getMeetTime() {
        return this.meetTime;
    }
    
    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    
    @Column(name="NO_OF_MEMBERS", nullable=false, precision=10, scale=0)
    public int getNoOfMembers() {
        return this.noOfMembers;
    }
    
    public void setNoOfMembers(int noOfMembers) {
        this.noOfMembers = noOfMembers;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="NXT_MEET_DATE", nullable=false, length=7)
    public Date getNxtMeetDate() {
        return this.nxtMeetDate;
    }
    
    public void setNxtMeetDate(Date nxtMeetDate) {
        this.nxtMeetDate = nxtMeetDate;
    }

    
    @Column(name="OUTSTANDING_BAL", nullable=false, precision=126, scale=0)
    public double getOutstandingBal() {
        return this.outstandingBal;
    }
    
    public void setOutstandingBal(double outstandingBal) {
        this.outstandingBal = outstandingBal;
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

    
    @Column(name="SAVING_ACCOUNTS", nullable=false, precision=10, scale=0)
    public int getSavingAccounts() {
        return this.savingAccounts;
    }
    
    public void setSavingAccounts(int savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    
    @Column(name="SAVINGS_AMT", nullable=false, precision=126, scale=0)
    public double getSavingsAmt() {
        return this.savingsAmt;
    }
    
    public void setSavingsAmt(double savingsAmt) {
        this.savingsAmt = savingsAmt;
    }

    
    @Column(name="SOL_ID", nullable=false, precision=10, scale=0)
    public int getSolId() {
        return this.solId;
    }
    
    public void setSolId(int solId) {
        this.solId = solId;
    }

    
    @Column(name="SUB_GP_CHAIR", length=50)
    public String getSubGpChair() {
        return this.subGpChair;
    }
    
    public void setSubGpChair(String subGpChair) {
        this.subGpChair = subGpChair;
    }

    
    @Column(name="SUB_GP_REGION", nullable=false, length=50)
    public String getSubGpRegion() {
        return this.subGpRegion;
    }
    
    public void setSubGpRegion(String subGpRegion) {
        this.subGpRegion = subGpRegion;
    }

    
    @Column(name="SUB_GP_SECRETARY", length=50)
    public String getSubGpSecretary() {
        return this.subGpSecretary;
    }
    
    public void setSubGpSecretary(String subGpSecretary) {
        this.subGpSecretary = subGpSecretary;
    }

    
    @Column(name="SUB_GP_STATUS", nullable=false, length=20)
    public String getSubGpStatus() {
        return this.subGpStatus;
    }
    
    public void setSubGpStatus(String subGpStatus) {
        this.subGpStatus = subGpStatus;
    }

    
    @Column(name="SUB_GP_STATUS_CODE", nullable=false, length=20)
    public String getSubGpStatusCode() {
        return this.subGpStatusCode;
    }
    
    public void setSubGpStatusCode(String subGpStatusCode) {
        this.subGpStatusCode = subGpStatusCode;
    }

    
    @Column(name="SUB_GP_TREASURER", length=50)
    public String getSubGpTreasurer() {
        return this.subGpTreasurer;
    }
    
    public void setSubGpTreasurer(String subGpTreasurer) {
        this.subGpTreasurer = subGpTreasurer;
    }

    
    @Column(name="SUB_GROUP_ADDRESS", nullable=false, length=50)
    public String getSubGroupAddress() {
        return this.subGroupAddress;
    }
    
    public void setSubGroupAddress(String subGroupAddress) {
        this.subGroupAddress = subGroupAddress;
    }

    
    @Column(name="SUB_GROUP_CENTER", nullable=false, length=50)
    public String getSubGroupCenter() {
        return this.subGroupCenter;
    }
    
    public void setSubGroupCenter(String subGroupCenter) {
        this.subGroupCenter = subGroupCenter;
    }

    
    @Column(name="SUB_GROUP_CODE", nullable=false, length=10)
    public String getSubGroupCode() {
        return this.subGroupCode;
    }
    
    public void setSubGroupCode(String subGroupCode) {
        this.subGroupCode = subGroupCode;
    }

    
    @Column(name="SUB_GROUP_ID", nullable=false, precision=10, scale=0)
    public int getSubGroupId() {
        return this.subGroupId;
    }
    
    public void setSubGroupId(int subGroupId) {
        this.subGroupId = subGroupId;
    }

    
    @Column(name="SUB_GROUP_LOANS", nullable=false, precision=126, scale=0)
    public double getSubGroupLoans() {
        return this.subGroupLoans;
    }
    
    public void setSubGroupLoans(double subGroupLoans) {
        this.subGroupLoans = subGroupLoans;
    }

    
    @Column(name="SUB_GROUP_NAME", nullable=false, length=50)
    public String getSubGroupName() {
        return this.subGroupName;
    }
    
    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    
    @Column(name="SUB_GROUP_PHONE", nullable=false, length=20)
    public String getSubGroupPhone() {
        return this.subGroupPhone;
    }
    
    public void setSubGroupPhone(String subGroupPhone) {
        this.subGroupPhone = subGroupPhone;
    }

    
    @Column(name="SUB_GROUP_VILLAGE", nullable=false, length=50)
    public String getSubGroupVillage() {
        return this.subGroupVillage;
    }
    
    public void setSubGroupVillage(String subGroupVillage) {
        this.subGroupVillage = subGroupVillage;
    }

    
    @Column(name="SUB_GRP_MGR_ID", nullable=false, length=30)
    public String getSubGrpMgrId() {
        return this.subGrpMgrId;
    }
    
    public void setSubGrpMgrId(String subGrpMgrId) {
        this.subGrpMgrId = subGrpMgrId;
    }

    
    @Column(name="SUB_GRP_REG_NO", nullable=false, length=30)
    public String getSubGrpRegNo() {
        return this.subGrpRegNo;
    }
    
    public void setSubGrpRegNo(String subGrpRegNo) {
        this.subGrpRegNo = subGrpRegNo;
    }




}


