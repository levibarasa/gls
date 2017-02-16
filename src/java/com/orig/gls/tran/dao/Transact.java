/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.tran.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.GeneralAcctMastTable;
import com.orig.gls.prop.GlsProp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stanley Mungai
 */
public class Transact {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static ArrayList getAllAccountsMappedtoSubGroup(String groupCode, String subgroupCode) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GeneralAcctMastTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            GlsProp pr = new GlsProp();
            String schm = pr.getDBProperty().getProperty("wholesale.schmecode");
            cr.add(Restrictions.eq("subGroupId", subgroupCode));
            cr.add(Restrictions.eq("groupId", groupCode));
            cr.add(Restrictions.ne("schmCode", schm));
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());//0
                one.add(group.getAcctName());//1
                one.add(group.getGroupId());//2
                one.add(group.getSubGroupId());//3
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

    public static ArrayList getAccountMappedtoSubGroup(String groupCode, String subgroupCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ArrayList arr = new ArrayList();
        List<GeneralAcctMastTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GeneralAcctMastTable.class);
            GlsProp pr = new GlsProp();
            String schm = pr.getDBProperty().getProperty("operative.schmecode");
            cr.add(Restrictions.eq("subGroupId", subgroupCode));
            cr.add(Restrictions.eq("groupId", groupCode));
            cr.add(Restrictions.ne("schmCode", schm));
            list = cr.list();
            for (GeneralAcctMastTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getForacid());//0
                one.add(group.getAcctName());//1
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
