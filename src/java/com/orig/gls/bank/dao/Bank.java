package com.orig.gls.bank.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.SolGroupControlTable;
import com.orig.gls.jpa.SolGroupControlTableMod;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Bank {

    private static final Log log = LogFactory.getLog("origlogger");
    private static int solgroupId;

    public static boolean bankEnityExists() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SolGroupControlTable.class);
            count = (long) cr.setProjection(Projections.rowCount()).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return count > 0;
    }

    public static String getBankId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        String bankid = "";
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SolGroupControlTable.class);
            bankid = (String) cr.setProjection(Projections.property("bankId")).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return bankid;
    }

    public static List<SolGroupControlTable> getBankDetails(String bankId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SolGroupControlTable> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SolGroupControlTable.class);
            cr.add(Restrictions.eq("bankId", bankId));
            list = cr.list();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return list;
    }

    public static int addbankEntity(String delFlg, String groupDesc, int defNumOfDaysInMth, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String homeCntryCode, Date dbStatDate, String homeCrncyCode, int numOfFutureDaysAllowed, int numOfBackDaysAllowed, String dcClsFlg, Date dcClsDate, String bankId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            SolGroupControlTable gct = new SolGroupControlTable(bankId, dbStatDate, dcClsDate, dcClsFlg, defNumOfDaysInMth, delFlg, groupDesc, homeCntryCode, homeCrncyCode, lchgTime, lchgUserId, numOfBackDaysAllowed, numOfFutureDaysAllowed, rcreTime, rcreUserId);
            solgroupId = (Integer) session.save(gct);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return solgroupId;
    }

    public static void addbankEntityMod(int solGroupId, String delFlg, String groupDesc, int defNumOfDaysInMth, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String homeCntryCode, Date dbStatDate, String homeCrncyCode, int numOfFutureDaysAllowed, int numOfBackDaysAllowed, String dcClsFlg, Date dcClsDate, String bankId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            SolGroupControlTableMod gct = new SolGroupControlTableMod(bankId, dbStatDate, dcClsDate, dcClsFlg, defNumOfDaysInMth, delFlg, groupDesc, homeCntryCode, homeCrncyCode, lchgTime, lchgUserId, numOfBackDaysAllowed, numOfFutureDaysAllowed, rcreTime, rcreUserId, solGroupId);
            session.save(gct);
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

    public static void executeaddEntity(String delFlg, String groupDesc, int defNumOfDaysInMth, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String homeCntryCode, Date dbStatDate, String homeCrncyCode, int numOfFutureDaysAllowed, int numOfBackDaysAllowed, String dcClsFlg, Date dcClsDate, String bankId) {
        int solgroupIds = addbankEntity(delFlg, groupDesc, defNumOfDaysInMth, lchgUserId, lchgTime, rcreUserId, rcreTime, homeCntryCode, dbStatDate, homeCrncyCode, numOfFutureDaysAllowed, numOfBackDaysAllowed, dcClsFlg, dcClsDate, bankId);
        addbankEntityMod(solgroupIds, delFlg, groupDesc, defNumOfDaysInMth, lchgUserId, lchgTime, rcreUserId, rcreTime, homeCntryCode, dbStatDate, homeCrncyCode, numOfFutureDaysAllowed, numOfBackDaysAllowed, dcClsFlg, dcClsDate, bankId);
        log.debug("Bank Entity: " + solgroupIds + " Added Successfully");
    }
}
