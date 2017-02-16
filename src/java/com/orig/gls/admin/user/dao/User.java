package com.orig.gls.admin.user.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.RoleProfileTable;
import com.orig.gls.jpa.UserCredsTbl;
import com.orig.gls.jpa.UserCredsTblMod;
import com.orig.gls.security.Encode;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.orig.gls.prop.GlsProp;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

public class User {

    private static final Log log = LogFactory.getLog("origlogger");
    private static int userId;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static boolean userExists(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
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

    public static int getUserId(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int userid = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userName", userName));
            userid = (Integer) cr.setProjection(Projections.property("userId")).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return userid;
    }

    public static int getRoleId(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int userid = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(RoleProfileTable.class);
            cr.add(Restrictions.eq("roleDesc", userName));
            userid = (Integer) cr.setProjection(Projections.property("roleId")).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return userid;
    }

    public static List<UserCredsTbl> getuserDetails(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserCredsTbl> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userId", userId));
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

    public static List<UserCredsTblMod> getuserModDetails(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserCredsTblMod> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTblMod.class);
            cr.add(Restrictions.eq("userId", userId));
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

    public static int addUserDetails(String userName, String roleId, String userPw, int numPwdHistory, String pwdHistory, int numPwdAttempts, String newUserFlg, Date disabledFromDate, Date disabledUptoDate, Date pwExpyDate, Date acctExpyDate, int acctInactiveDays, Date lastAccessTime, String rcreUserId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int role = getRoleId(roleId);
        try {
            String userN = userName.trim();
            String passwD = userPw.trim();
            tx = session.beginTransaction();
            UserCredsTbl user = new UserCredsTbl(acctExpyDate, acctInactiveDays, disabledFromDate, disabledUptoDate, lastAccessTime, newUserFlg, numPwdAttempts, numPwdHistory, pwExpyDate, pwdHistory, role, userN, EncodeUserPassword(userN, passwD), "U", rcreUserId);
            userId = (Integer) session.save(user);
            tx.commit();
            addUserModDetails(userId, userN, roleId, passwD, numPwdHistory, pwdHistory, numPwdAttempts, newUserFlg, disabledFromDate, disabledUptoDate, pwExpyDate, acctExpyDate, acctInactiveDays, lastAccessTime, "A", rcreUserId);
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

    public static ArrayList getAllVerifiedUsers() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserCredsTbl> list;
        try {
            tx = session.beginTransaction();
            DetachedCriteria subquery = DetachedCriteria.forClass(UserCredsTblMod.class);
            subquery.setProjection(Projections.property("userId"));
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Subqueries.propertyNotIn("userId", subquery));
            list = cr.list();

            for (UserCredsTbl user : list) {
                ArrayList one = new ArrayList();
                one.add(user.getUserName());
                one.add(user.getUserId());
                one.add(user.getRoleId());
                one.add(user.getUserPw());
                one.add(user.getNumPwdHistory());
                one.add(user.getNumPwdAttempts());//6
                one.add(user.getNewUserFlg());//7
                one.add(sdf.format(user.getDisabledFromDate()));
                one.add(sdf.format(user.getDisabledUptoDate()));
                one.add(sdf.format(user.getPwExpyDate()));
                one.add(sdf.format(user.getAcctExpyDate()));
                one.add(user.getAcctInactiveDays());//10
                one.add(sdf.format(user.getLastAccessTime()));
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

    public static ArrayList getAllUsers(String uname) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserCredsTbl> list;
        System.out.println("Logged user is " + uname);
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.ne("userName", uname));
            cr.add(Restrictions.eq("userStatus", "A"));
            list = cr.list();
            for (UserCredsTbl user : list) {
                ArrayList one = new ArrayList();
                one.add(user.getUserName());
                one.add(user.getRoleId());
                one.add(String.valueOf(user.getUserId()));
                one.add(user.getUserPw());
                one.add(user.getNumPwdHistory());
                one.add(user.getNumPwdAttempts());//6
                one.add(user.getNewUserFlg());//7
                one.add(sdf.format(user.getDisabledFromDate()));
                one.add(sdf.format(user.getDisabledUptoDate()));
                one.add(sdf.format(user.getPwExpyDate()));
                one.add(sdf.format(user.getAcctExpyDate()));
                one.add(user.getAcctInactiveDays());//10
                one.add(sdf.format(user.getLastAccessTime()));
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

    public static ArrayList getUnverifiedUsers(String username) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserCredsTblMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTblMod.class);
            cr.add(Restrictions.ne("rcreUserId", username));
            list = cr.list();
            for (UserCredsTblMod user : list) {
                ArrayList one = new ArrayList();
                one.add(user.getUserName());
                one.add(user.getRoleId());
                one.add(String.valueOf(user.getUserId()));
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

    public static ArrayList getUnverifiedUsers() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserCredsTblMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTblMod.class);
            list = cr.list();
            for (UserCredsTblMod user : list) {
                ArrayList one = new ArrayList();
                one.add(user.getUserName());
                one.add(String.valueOf(user.getUserId()));
                one.add(user.getRoleId());
                one.add(user.getUserPw());
                one.add(user.getNumPwdHistory());
                one.add(user.getNumPwdAttempts());//6
                one.add(user.getNewUserFlg());//7
                one.add(sdf.format(user.getDisabledFromDate()));
                one.add(sdf.format(user.getDisabledUptoDate()));
                one.add(sdf.format(user.getPwExpyDate()));
                one.add(sdf.format(user.getAcctExpyDate()));
                one.add(user.getAcctInactiveDays());//10
                one.add(sdf.format(user.getLastAccessTime()));
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

    public static ArrayList getUserDetails(String in) {
        ArrayList arr = new ArrayList();
        int userid = Integer.parseInt(in);
        System.out.println("Parsed user Id " + userid);
        List<UserCredsTbl> list = getuserDetails(userid);
        for (UserCredsTbl user : list) {
            ArrayList one = new ArrayList();
            one.add(user.getUserName());
            one.add(user.getRoleId());
            one.add(user.getNewUserFlg());//7
            one.add(user.getUserStatus());
            one.add(sdf.format(user.getDisabledFromDate()));
            one.add(sdf.format(user.getDisabledUptoDate()));
            one.add(sdf.format(user.getPwExpyDate()));
            one.add(sdf.format(user.getAcctExpyDate()));
            one.add(sdf.format(user.getLastAccessTime()));

            arr.add(one);
        }
        return arr;
    }

    public static String lastOper(String in) {
        int userid = Integer.parseInt(in);
        String res = "";
        List<UserCredsTblMod> list = getuserModDetails(userid);
        for (UserCredsTblMod user : list) {
            res = user.getLastOper();
        }
        return res;
    }

    public static boolean addUserModDetails(int userId, String userName, String roleId, String userPw, int numPwdHistory, String pwdHistory, int numPwdAttempts, String newUserFlg, Date disabledFromDate, Date disabledUptoDate, Date pwExpyDate, Date acctExpyDate, int acctInactiveDays, Date lastAccessTime, String lastOper, String rcreUserId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int addmod = 0;
        int role = getRoleId(roleId);
        try {
            tx = session.beginTransaction();
            UserCredsTblMod users = new UserCredsTblMod(acctExpyDate, acctInactiveDays, disabledFromDate, disabledUptoDate, lastAccessTime, lastOper, newUserFlg, numPwdAttempts, numPwdHistory, pwExpyDate, pwdHistory, role, userId, userName, EncodeUserPassword(userName, userPw), rcreUserId);
            addmod = (Integer) session.save(users);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return addmod != 0;
    }

    public static boolean executeaddUser(int userId, String userName, String roleId, String userPw, int numPwdHistory, String pwdHistory, int numPwdAttempts, String newUserFlg, Date disabledFromDate, Date disabledUptoDate, Date pwExpyDate, Date acctExpyDate, int acctInactiveDays, Date lastAccessTime, String rcreUserId) {
        int userid = addUserDetails(userName, roleId, userPw, numPwdHistory, pwdHistory, numPwdAttempts, newUserFlg, disabledFromDate, disabledUptoDate, pwExpyDate, acctExpyDate, acctInactiveDays, lastAccessTime, rcreUserId);
        addUserModDetails(userId, userName, roleId, userPw, numPwdHistory, pwdHistory, numPwdAttempts, newUserFlg, disabledFromDate, disabledUptoDate, pwExpyDate, acctExpyDate, acctInactiveDays, lastAccessTime, "A", rcreUserId);
        log.debug("User Id: " + userid + " Added Successfully");
        return userid != 0;
    }

    public static void verifyUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTblMod.class);
            cr.add(Restrictions.eq("userId", userId));
            int count = 0;
            ScrollableResults items = cr.scroll();
            while (items.next()) {
                UserCredsTblMod user = (UserCredsTblMod) items.get(0);
                session.delete(user);
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

    public static ArrayList getUsersList() {
        ArrayList arr = new ArrayList();
        GlsProp pr = new GlsProp();
        String uploadFilepath = pr.getDBProperty().getProperty("user.file");
        String line;
        try {
            FileInputStream fs = new FileInputStream(uploadFilepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String[] split;
            while ((line = br.readLine()) != null) {
                split = line.split("\\|");
                ArrayList one = new ArrayList();
                one.add(split[0]);
                one.add(split[1]);
                one.add(split[2]);
                arr.add(one);
            }
            fs.close();
			br.close();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
        }
        return arr;
    }

    public static void deleteUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userId", userId));
            UserCredsTbl user = (UserCredsTbl) cr.uniqueResult();
            user.setUserStatus("D");
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

    public static void modifyUser(int userId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userId", userId));
            UserCredsTbl user = (UserCredsTbl) cr.uniqueResult();
            List<UserCredsTblMod> users = getuserModDetails(userId);
            for (UserCredsTblMod us : users) {
                user.setUserName(us.getUserName());
                user.setRoleId(us.getRoleId());
                user.setNumPwdHistory(us.getNumPwdHistory());
                user.setPwdHistory(us.getPwdHistory());
                user.setNumPwdAttempts(us.getNumPwdAttempts());
                user.setNewUserFlg(us.getNewUserFlg());
                user.setDisabledFromDate(us.getDisabledFromDate());
                user.setDisabledUptoDate(us.getDisabledUptoDate());
                user.setPwExpyDate(us.getPwExpyDate());
                user.setAcctExpyDate(us.getAcctExpyDate());
                user.setAcctInactiveDays(us.getAcctInactiveDays());
                user.setLastAccessTime(us.getLastAccessTime());
                user.setLchgUserId(username);
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

    public static void markUserUnverified(int userName, String userStatus) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTbl.class);
            cr.add(Restrictions.eq("userId", userName));
            UserCredsTbl user = (UserCredsTbl) cr.uniqueResult();
            user.setUserStatus(userStatus);
            user.setNewUserFlg("Y");
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

    public static boolean userExistsInMod(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(UserCredsTblMod.class);
            cr.add(Restrictions.eq("userName", userName));
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
    static Encode enc;

    public static String generateUserKey(String username, String password) {
        return username.substring(0, 2) + password.substring(0, 4);
    }

    public static String generateUserIV(String username, String password) {
        return username.substring(0, 3) + password.substring(0, 5);
    }

    public static String EncodeUserPassword(String username, String password) {
        enc = new Encode(generateUserKey(username, password), generateUserIV(username, password));
        return enc.encrypt(password);
    }

    public String DecodeUserPassword(String username, String password, String encpass) {
        enc = new Encode(generateUserKey(username, password), generateUserIV(username, password));
        return enc.decrypt(encpass);
    }

}
