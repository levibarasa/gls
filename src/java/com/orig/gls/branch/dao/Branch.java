/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.branch.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.ServiceOutletTable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Stanley Mungai
 */
public class Branch {

    private static final Log log = LogFactory.getLog("origlogger");

    public static ArrayList getAllBranches() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ArrayList arr = new ArrayList();
        List<ServiceOutletTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ServiceOutletTable.class);
            cr.addOrder(Order.asc("solId"));
            list = cr.list();
            for (ServiceOutletTable sol : list) {
                ArrayList one = new ArrayList();
                one.add(String.valueOf(sol.getSolId()));
                one.add(sol.getSolDesc());
                one.add(sol.getBankId());
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
