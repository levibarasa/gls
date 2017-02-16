package com.orig.gls.customer.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.GeneralAcctMastTable;
import com.orig.gls.jpa.GeneralAcctMastTableMod;
import com.orig.gls.jpa.GroupsTable;
import com.orig.gls.jpa.SubGrpTable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Customer {

    private static final Log log = LogFactory.getLog("origlogger");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static ArrayList getUnMappedAccounts() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            cr.add(Restrictions.isNull("groupId"));
            cr.add(Restrictions.isNull("subGroupId"));
            cr.add(Restrictions.eq("mappedFlg", "N"));
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());
                one.add(group.getAcctName());
                one.add(String.valueOf(group.getAcctOpnDate()));
                one.add(group.getSolId());
                one.add(group.getSchmType());
                one.add(group.getSchmCode());//5
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    // Get unmapped account details using account no
    public static ArrayList getUnMappedAccountDetails(String foracid) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            cr.add(Restrictions.isNull("groupId"));
            cr.add(Restrictions.isNull("subGroupId"));
            cr.add(Restrictions.eq("foracid", foracid));
            cr.add(Restrictions.eq("mappedFlg", "N"));
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());
                one.add(group.getAcctName());
                one.add(group.getAcctOpnDate());
                one.add(group.getSolId());
                one.add(group.getSchmType());
                one.add(group.getSchmCode());//5
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getUnMappedAccountDetailsMod(String foracid) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        System.out.println(" Verification Account No " + foracid);
        List<GeneralAcctMastTableMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTableMod.class);
            cr.add(Restrictions.eq("foracid", foracid));
            list = cr.list();
            for (GeneralAcctMastTableMod group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());
                one.add(group.getAcctName());
                one.add(group.getAcctOpnDate());
                one.add(group.getSolId());
                one.add(group.getSchmType());
                one.add(group.getSchmCode());
                one.add(group.getGroupCode());
                one.add(group.getSubGroupCode());
                one.add(getGroupName(group.getGroupCode()));
                one.add(getSubGroupName(group.getSubGroupCode()));//5
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllUnMappedAccountMod() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTableMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTableMod.class);
            list = cr.list();
            for (GeneralAcctMastTableMod group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());
                one.add(group.getAcctName());
                one.add(group.getAcctOpnDate());
                one.add(group.getSolId());
                one.add(group.getSchmType());
                one.add(group.getSchmCode());
                one.add(group.getGroupCode());
                one.add(group.getSubGroupCode());
                one.add(getGroupName(group.getGroupCode()));
                String status = group.getLastOper();
                String st;
                if (status.equalsIgnoreCase("A")) {
                    st = "ADDED";
                } else if (status.equalsIgnoreCase("M")) {
                    st = "MODIFIED";
                } else {
                    st = "DELETED";
                }
                one.add(st);
                one.add(getSubGroupName(group.getSubGroupCode()));//5
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllVerfiedAccounts() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());
                one.add(group.getAcctName());
                one.add(group.getAcctOpnDate());
                one.add(group.getSolId());
                one.add(group.getSchmType());
                one.add(group.getSchmCode());
                one.add(group.getGroupId());
                one.add(group.getSubGroupId());
                one.add(getGroupName(group.getGroupId()));
                one.add(getSubGroupName(group.getSubGroupId()));//5
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    // get data to insert into mod table
    public static boolean getAccountDetails(String foracid, String groupId, String subGroupId, String lastOper) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTable> list;
        int n = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            cr.add(Restrictions.eq("foracid", foracid));
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                Random k = new Random();
                int modId = k.nextInt(1000000);
                String acid = group.getAcid();
                String entryCrFlg = group.getEntityCreFlg();
                String delFlg = group.getDelFlg();
                String solId = group.getSolId();
                String acctName = group.getAcctName();
                int custId = group.getCustId();
                String acctOwnership = group.getAcctOwnership();
                String schmCode = group.getSchmCode();
                Date acctOpnDate = group.getAcctOpnDate();
                double clrBalAmt = group.getClrBalAmt();
                double drwngPower = group.getDrwngPower();
                double sanctLim = group.getSanctLim();
                String lchgUserId = group.getLchgUserId();
                Date lchgTime = group.getLchgTime();
                String rcreUserId = group.getRcreUserId();
                Date rcreTime = group.getRcreTime();
                String acctCrncyCode = group.getAcctCrncyCode();
                double lienAmt = group.getLienAmt();
                String acctMgrUserId = group.getAcctMgrUserId();
                String schmType = group.getSchmType();
                Date lastModifiedDate = group.getLastModifiedDate();
                String bankId = group.getBankId();
                switch (lastOper) {
                    case "A":
                        n = addGamModDetails(modId, acid, entryCrFlg, delFlg, solId, foracid, acctName, custId, acctOwnership, schmCode, acctOpnDate, clrBalAmt, drwngPower, sanctLim, lchgUserId, lchgTime, rcreUserId, rcreTime, acctCrncyCode, lienAmt, acctMgrUserId, schmType, lastModifiedDate, bankId, lastOper, groupId, subGroupId);
                        markAccountAsMappedinGam(foracid);
                        break;
                    case "M":
                        n = addGamModDetails(modId, acid, entryCrFlg, delFlg, solId, foracid, acctName, custId, acctOwnership, schmCode, acctOpnDate, clrBalAmt, drwngPower, sanctLim, lchgUserId, lchgTime, rcreUserId, rcreTime, acctCrncyCode, lienAmt, acctMgrUserId, schmType, lastModifiedDate, bankId, lastOper, groupId, subGroupId);
                        break;
                    case "D":
                        n = addGamModDetails(modId, acid, entryCrFlg, delFlg, solId, foracid, acctName, custId, acctOwnership, schmCode, acctOpnDate, clrBalAmt, drwngPower, sanctLim, lchgUserId, lchgTime, rcreUserId, rcreTime, acctCrncyCode, lienAmt, acctMgrUserId, schmType, lastModifiedDate, bankId, lastOper, groupId, subGroupId);
                        break;
                }
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return n > 0;
    }
    // Add the account mapping to table accounts

    public static int addGamModDetails(int modId, String acid, String entityCreFlg, String delFlg, String solId, String foracid, String acctName, int custId, String acctOwnership, String schmCode, Date acctOpnDate, double clrBalAmt, double drwngPower, double sanctLim, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String acctCrncyCode, double lienAmt, String acctMgrUserId, String schmType, Date lastModifiedDate, String bankId, String lastOper, String groupCode, String subGroupCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int groupIds = 0;
        try {
            tx = session.beginTransaction();
            GeneralAcctMastTableMod group = new GeneralAcctMastTableMod(modId, acctCrncyCode, acctMgrUserId, acctName, acctOpnDate, acctOwnership, acid, bankId, clrBalAmt, custId, delFlg, drwngPower, entityCreFlg, foracid, groupCode, lastModifiedDate, lastOper, lchgTime, lchgUserId, lienAmt, rcreTime, rcreUserId, sanctLim, schmCode, schmType, solId, subGroupCode);
            groupIds = (Integer) session.save(group);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return groupIds;
    }

    public static String getLastGamModOper(String foracid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String groupIds = "";
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTableMod.class);
            cr.add(Restrictions.eq("foracid", foracid));
            GeneralAcctMastTableMod group = (GeneralAcctMastTableMod) cr.uniqueResult();
            groupIds = group.getLastOper();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return groupIds;
    }

    static int groupId;

    // Update GAM  Mapped flag AND WAIT FOR APPROVAL
    public static void markAccountAsMappedinGam(String foracid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            cr.add(Restrictions.eq("foracid", foracid));
            GeneralAcctMastTable group = (GeneralAcctMastTable) cr.uniqueResult();
            group.setMappedFlg("Y");
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    // Add mapping to the database
//    public static boolean executeAddAccountMapping(int accountid, String bankId, String custFirstName, String custLastName, String gender, int birthDay, int birthMonth, int birthYear, Date custDob, String email, int groupid, int subGroupid, Date bodatecreated, int bocreatedby, Date bodatemodified, int bomodifiedby, int owneduserid, String preferredphone, Date startdate, String natIdCardNum, String crncyCode, int primarySolId, String entityCreFlag, String salutationCode, String sectorCode, String subsectorCode, String statusCode, String addressLine1, int regionId, String country) {
//   //     int modId = addAccountDetails(accountid, bankId, custFirstName, custLastName, gender, birthDay, birthMonth, birthYear, custDob, email, groupid, subGroupid, bodatecreated, bocreatedby, bodatemodified, bomodifiedby, owneduserid, preferredphone, startdate, natIdCardNum, crncyCode, primarySolId, entityCreFlag, salutationCode, sectorCode, subsectorCode, statusCode, addressLine1, regionId, country);
//     //   addAccountModDetails(modId, accountid, bankId, custFirstName, custLastName, gender, birthDay, birthMonth, birthYear, custDob, email, groupid, subGroupid, bodatecreated, bocreatedby, bodatemodified, bomodifiedby, owneduserid, preferredphone, startdate, natIdCardNum, crncyCode, primarySolId, entityCreFlag, salutationCode, sectorCode, subsectorCode, statusCode, addressLine1, regionId, country);
//        markAccountAsMappedinGam(String.valueOf(accountid), groupid, String.valueOf(subGroupid));
//        log.debug("Account Id : " + accountid + " Added Successfully");
//        return modId != 0;
//    }
    // Get Group name
    public static String getGroupName(String groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String userId = "NOT AVAILABLE";
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupCode", groupId));
            userId = (String) cr.setProjection(Projections.property("groupName")).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return userId;
    }

    // Get subgroup name
    public static String getSubGroupName(String subGroupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String userId = "";
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Restrictions.eq("subGroupCode", subGroupId));
            userId = (String) cr.setProjection(Projections.property("subGroupName")).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            userId = "NOT AVAILABLE";
        } finally {
            session.close();
        }
        return userId;
    }

    // Verify and delete from mod table
    public static void verifyAccountDetails(String foracid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
//        List<GeneralAcctMastTableMod> list = getAccountModDetails(foracid);
//        for (GeneralAcctMastTableMod group : list) {
//            int groupIds = group.getModId();
//            String subGrp = group.getSubGroupCode();
//            System.out.println("Group Code " + groupIds);
//        }
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTableMod.class);
            cr.add(Restrictions.eq("foracid", foracid));
            System.out.println("Mod Foracid is " + foracid);
            String groups = (String) cr.setProjection(Projections.property("groupCode")).uniqueResult();
            String subGroupId = (String) cr.setProjection(Projections.property("subGroupCode")).uniqueResult();
            System.out.println("Mod Group code is " + groups);
            System.out.println("Mod sub Group code is " + subGroupId);
            completeVerification(foracid, groups, subGroupId);
            deleteAfterVerification(foracid);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<GeneralAcctMastTableMod> getAccountModDetails(String foracid) {
        List<GeneralAcctMastTableMod> acct = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTableMod.class);
            cr.add(Restrictions.eq("foracid", foracid));
            acct = cr.list();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return acct;
    }

    // Current Account Details
    public static ArrayList<GeneralAcctMastTable> getAccountDetails(String foracid) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            cr.add(Restrictions.eq("foracid", foracid));
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());
                one.add(group.getAcctName());
                one.add(group.getAcctOpnDate());
                one.add(group.getSolId());
                one.add(group.getSchmType());
                one.add(group.getSchmCode());
                one.add(group.getGroupId());
                one.add(group.getSubGroupId());
                one.add(getGroupName(group.getGroupId()));
                one.add(getSubGroupName(group.getSubGroupId()));//5
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    // Update GAM with verified details
    public static void completeVerification(String foracid, String groupId, String subGroupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            cr.add(Restrictions.eq("foracid", foracid));
            GeneralAcctMastTable group = (GeneralAcctMastTable) cr.uniqueResult();
            group.setGroupId(groupId);
            group.setSubGroupId(subGroupId);
            session.update(group);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    // delete mod record
    public static void deleteAfterVerification(String foracid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTableMod.class);
            cr.add(Restrictions.eq("foracid", foracid));
            ScrollableResults items = cr.scroll();
            int count = 0;
            while (items.next()) {
                GeneralAcctMastTableMod group = (GeneralAcctMastTableMod) items.get(0);
                session.delete(group);
                if (++count % 100 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
