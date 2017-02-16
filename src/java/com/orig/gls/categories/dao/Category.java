/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.categories.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.Categories;
import java.util.ArrayList;
import java.util.Date;
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
public class Category {

    private static final Log log = LogFactory.getLog("origlogger");

    public static boolean categoryExists(String categorycode, String categorytype) {
        long count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Categories.class);
            cr.add(Restrictions.eq("categorycode", categorycode));
            cr.add(Restrictions.eq("categorytype", categorytype));
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

    public static void addCategories(String bankId, String categorycode, String categorytype, String categoryvalue, Date bodatecreated, String bocreatedby, Date bodatemodified, String bomodifiedby) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Categories cat = new Categories(bankId, bocreatedby, bodatecreated, bodatemodified, bomodifiedby, categorycode, categorytype, categoryvalue);
            session.save(cat);
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

    public static List<Categories> getcategoriesDetails(String categorycode, String categorytype) {
        List<Categories> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Categories.class);
            cr.add(Restrictions.eq("categorycode", categorycode));
            cr.add(Restrictions.eq("categorytype", categorytype));
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

    public static ArrayList getCategories(String statuscategory) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Categories> cat;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Categories.class);
            cr.add(Restrictions.eq("categorytype", statuscategory));
            cat = cr.list();
            for (Categories cats : cat) {
                ArrayList one = new ArrayList();
                one.add(cats.getCategorycode());
                one.add(cats.getCategorytype());
                one.add(cats.getCategoryvalue());
                one.add(cats.getBocreatedby());
                one.add(cats.getBankId());
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
}
