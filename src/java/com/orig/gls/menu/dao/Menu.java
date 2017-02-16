/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.menu.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.ResMapping;
import com.orig.gls.jpa.ResMappingMod;
import com.orig.gls.jpa.Resources;
import java.util.List;
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
public class Menu {

    private static final Log log = LogFactory.getLog("origlogger");

    public static boolean menuExists(String mopId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Resources.class);
            cr.add(Restrictions.eq("mopId", mopId));
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

    public static List<Resources> getResourceDetails(String mopId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Resources> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Resources.class);
            cr.add(Restrictions.eq("mopId", mopId));
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

    public static boolean roleMenuIsUnverified(String roleName, String mopId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ResMappingMod.class);
            cr.add(Restrictions.eq("roleName", roleName));
            cr.add(Restrictions.eq("mopId", mopId));
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

    public static boolean roleHasMenu(String roleName, String mopId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ResMapping.class);
            cr.add(Restrictions.eq("roleName", roleName));
            cr.add(Restrictions.eq("mopId", mopId));
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
}
