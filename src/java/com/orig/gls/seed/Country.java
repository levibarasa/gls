/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.seed;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.CountryCodes;
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
public class Country {

    private static final Log log = LogFactory.getLog(Country.class);
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY", Locale.getDefault());

    public static ArrayList getEnabledCountries() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<CountryCodes> country;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(CountryCodes.class);
            cr.add(Restrictions.eq("enabledFlg", "Y"));
            country = cr.list();
            for (CountryCodes count : country) {
                ArrayList one = new ArrayList();
                one.add(count.getCountryCode());
                one.add(count.getCountryName());
                one.add(sdf.format(count.getRcreTime()));
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
