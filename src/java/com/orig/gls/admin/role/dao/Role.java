/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.admin.role.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.RoleProfileTable;
import com.orig.gls.jpa.RoleProfileTableMod;
import com.orig.gls.utils.App;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stanley Mungai
 */
public class Role {

    private static final Log log = LogFactory.getLog(App.LOGGER);

    /**
     * @return the roleId
     */
    public static int getRoleId() {
        return roleId;
    }

    /**
     * @param aRoleId the roleId to set
     */
    public static void setRoleId(int aRoleId) {
        roleId = aRoleId;
    }
    static SimpleDateFormat sdf = new SimpleDateFormat(App.DATEFORMAT, Locale.getDefault());
    private static int roleId;

    public static boolean roleExists(String roleDesc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTable.class);
            cr.add(Restrictions.eq("roleDesc", roleDesc));
            count = (long) cr.setProjection(Projections.rowCount()).uniqueResult();
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return count > 0;
    }

    public static ArrayList getAllRoles() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<RoleProfileTable> profiles;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTable.class);
            profiles = cr.list();
            for (RoleProfileTable prof : profiles) {
                ArrayList one = new ArrayList();
                one.add(prof.getRoleDesc());
                one.add(sdf.format(prof.getRcreTime()));
                one.add(prof.getRcreUserId());
                one.add(prof.getBankId());
                arr.add(one);
            }
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    public static ArrayList getUnverifiedRoles() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<RoleProfileTableMod> profiles;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTableMod.class);
            profiles = cr.list();
            for (RoleProfileTableMod prof : profiles) {
                ArrayList one = new ArrayList();
                one.add(prof.getRoleDesc());
                one.add(sdf.format(prof.getRcreTime()));
                one.add(prof.getRcreUserId());
                one.add(prof.getBankId());
                arr.add(one);
            }
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return arr;
    }

    public static void deleteModRole(int roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTableMod.class);
            cr.add(Restrictions.eq("roleId", roleId));
            RoleProfileTableMod prof = (RoleProfileTableMod) cr.uniqueResult();
            session.delete(prof);
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static int addRole(String roleDesc, String entityCreFlg, String delFlg, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String bankId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            RoleProfileTable profiles = new RoleProfileTable(bankId, delFlg, entityCreFlg, lchgTime, lchgUserId, rcreTime, rcreUserId, roleDesc);
            roleId = (Integer) session.save(profiles);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return roleId;
    }

    public static void addRoleMod(int roleId, String roleDesc, String entityCreFlg, String delFlg, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String bankId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            RoleProfileTableMod profiles = new RoleProfileTableMod(bankId, delFlg, entityCreFlg, lchgTime, lchgUserId, rcreTime, rcreUserId, roleDesc, roleId);
            session.save(profiles);
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

    public static void executeAddRole(String roleDesc, String entityCreFlg, String delFlg, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime, String bankId) {
        int role = addRole(roleDesc, entityCreFlg, delFlg, lchgUserId, lchgTime, rcreUserId, rcreTime, bankId);
        addRoleMod(role, roleDesc, entityCreFlg, delFlg, lchgUserId, lchgTime, rcreUserId, rcreTime, bankId);
        log.debug("Role Id: " + role + " Added Successfully");
    }

    public static List<RoleProfileTable> getProfileDetails(int roleId) {
        List<RoleProfileTable> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTable.class);
            cr.add(Restrictions.eq("roleId", roleId));
            list = cr.list();
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return list;

    }

    public static List<RoleProfileTableMod> getModProfileDetails(int roleId) {
        List<RoleProfileTableMod> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTableMod.class);
            cr.add(Restrictions.eq("roleId", roleId));
            list = cr.list();
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return list;

    }

    public static int getRoleId(String roleDesc) {
        int roleid = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTable.class);
            cr.add(Restrictions.eq("roleDesc", roleDesc));
            roleid = (Integer) cr.setProjection(Projections.property("roleId")).uniqueResult();
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return roleid;

    }

    public static boolean roleIsModified(String roleDesc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTableMod.class);
            cr.add(Restrictions.eq("roleDesc", roleDesc));
            count = (long) cr.setProjection(Projections.rowCount()).uniqueResult();
            tx.commit();
        } catch (HibernateException asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return count > 0;
    }
}
