/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.admin.user.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.LoggedInUser;
import com.orig.gls.jpa.UserCredsTbl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stanley Mungai
 */
public class Access {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY", Locale.getDefault());

    public static boolean userExists(String userName, String userPw) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
            cr.add(Restrictions.eq("userPw", userPw));
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

    public static void changePassword(String userName, String userPw) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
            UserCredsTbl users = (UserCredsTbl) cr.uniqueResult();
            users.setUserPw(userPw);
            users.setNewUserFlg("N");
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void disableUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userId", userId));
            UserCredsTbl users = (UserCredsTbl) cr.uniqueResult();
            users.setDisabledFromDate(new Date());
            users.setDisabledUptoDate(getExpDate(1000));
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void enableUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userId", userId));
            UserCredsTbl users = (UserCredsTbl) cr.uniqueResult();
            users.setDisabledFromDate(getExpDate(1000));
            users.setDisabledUptoDate(getExpDate(1000));
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void logoutUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(LoggedInUser.class);
            cr.add(Restrictions.eq("userName", userName));
            LoggedInUser users = (LoggedInUser) cr.uniqueResult();
            session.delete(users);
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void markWrongLoginAttempt(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
            UserCredsTbl users = (UserCredsTbl) cr.uniqueResult();
            users.setNumPwdAttempts(users.getNumPwdAttempts() + 1);
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void markNotNewUser(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
            UserCredsTbl users = (UserCredsTbl) cr.uniqueResult();
            users.setNewUserFlg("N");
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void markCorrectLoginAttempt(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
            UserCredsTbl users = (UserCredsTbl) cr.uniqueResult();
            users.setNumPwdAttempts(0);
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void loginUser(String userName, String solId, Date loggedInTime, String sessionId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LoggedInUser users = new LoggedInUser(loggedInTime, sessionId, solId, userName);
            session.save(users);
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static boolean userIsLoggedIn(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(LoggedInUser.class);
            cr.add(Restrictions.eq("userName", userName));
            count = (long) cr.setProjection(Projections.rowCount()).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            System.out.println(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return count > 0;
    }

    private static Date getExpDate(int numberOfDays) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
        return cal.getTime();
    }
}
