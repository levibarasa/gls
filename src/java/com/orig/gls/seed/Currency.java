/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.seed;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.CurrencyCodes;
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
public class Currency {

    private static final Log log = LogFactory.getLog(Currency.class);
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY", Locale.getDefault());

    public static ArrayList getEnabledCurrencies() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<CurrencyCodes> currency;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(CurrencyCodes.class);
            cr.add(Restrictions.eq("enabledFlg", "Y"));
            currency = cr.list();
            for (CurrencyCodes curr : currency) {
                ArrayList one = new ArrayList();
                one.add(curr.getCurrencyCode());
                one.add(curr.getCurerncyName());
                one.add(sdf.format(curr.getRcreTime()));
                arr.add(one);
            }
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        }
        return arr;
    }

}
