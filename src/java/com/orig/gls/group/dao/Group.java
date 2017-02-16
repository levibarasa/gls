/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.group.dao;

import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.GroupsTable;
import com.orig.gls.jpa.GroupsTableMod;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 *
 * @author Stanley Mungai
 */
public class Group {

    private static final Log log = LogFactory.getLog("origlogger");
    private static int groupId;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static boolean groupExists(String groupCode, String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupCode", groupCode));
            cr.add(Restrictions.eq("groupName", groupName));
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

    public static int getGroupId(String groupCode, String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int groupid = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupCode", groupCode));
            cr.add(Restrictions.eq("groupName", groupName));
            groupid = (Integer) cr.setProjection(Projections.property("groupId")).uniqueResult();
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return groupid;
    }

    public static List<GroupsTable> getgroupDetails(int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GroupsTable> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupId", groupId));
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

    public static List<GroupsTableMod> getgroupModDetails(int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GroupsTableMod> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTableMod.class);
            cr.add(Restrictions.eq("groupId", groupId));
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

    public static int addGroupDetails(String bankId, String countryCode, String delFlg, String groupAddress, double groupLoans, String groupName, String groupPhone, String grpMgrId, String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, String groupCode, Date formationDate, String groupCenter, String groupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, int savingAccounts, int solId, String branchName, String meetFrequency) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            GroupsTable group = new GroupsTable(bankId, branchName, countryCode, delFlg, firstMeetDate, formationDate, gpRegion, gpStatus, gpStatusCode, groupAddress, groupCenter, groupCode, groupLoans, groupName, groupPhone, groupVillage, grpMgrId, grpRegNo, lchgDate, lchgUserId, loanAccounts, maxAllowedMembers, maxAllowedSubGrps, meetFrequency, meetPlace, noOfMembers, noOfSubGrps, nxtMeetDate, outstandingBal, rcreTime, rcreUserId, savingAccounts, savingsAmt, solId);
            groupId = (Integer) session.save(group);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return groupId;
    }

    public static ArrayList getAllVerifiedGroups() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GroupsTable> list;
        try {
            tx = session.beginTransaction();
            DetachedCriteria subquery = DetachedCriteria.forClass(GroupsTableMod.class);
            subquery.setProjection(Projections.property("groupId"));
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Subqueries.propertyNotIn("groupId", subquery));
            list = cr.list();
            for (GroupsTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getGroupCode());
                one.add(String.valueOf(group.getGroupId()));
                one.add(group.getGroupName());
                one.add(sdf.format(group.getRcreTime()));
                one.add(group.getRcreUserId());
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getGrpMgrId());//7
                one.add(group.getGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getGpRegion());//10
                one.add(group.getGroupCenter());//11
                one.add(group.getGroupVillage());//12
                one.add(group.getGroupAddress());//13
                one.add(group.getGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                one.add(String.valueOf(group.getMaxAllowedSubGrps()));//20
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getGpChair() != null) {
                    groupchair = group.getGpChair();
                }
                if (group.getGpTreasurer() != null) {
                    grouptre = group.getGpTreasurer();
                }
                if (group.getGpSecretary() != null) {
                    groupsec = group.getGpSecretary();
                }
                one.add(groupchair);//21
                one.add(grouptre);//22
                one.add(groupsec);//23
                one.add(group.getGpStatus());//24
                one.add(group.getGpStatusCode());//25
                one.add(String.valueOf(group.getNoOfMembers()));//26
                one.add(group.getMeetFrequency());//27
                one.add(String.valueOf(group.getSavingAccounts()));//28
                one.add(String.valueOf(group.getSavingsAmt()));//29
                one.add(String.valueOf(group.getLoanAccounts()));//30
                one.add(String.valueOf(group.getOutstandingBal()));//31
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

    public static ArrayList getAllGroups() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GroupsTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            list = cr.list();
            for (GroupsTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getGroupCode());
                one.add(String.valueOf(group.getGroupId()));
                one.add(group.getGroupName());
                one.add(sdf.format(group.getRcreTime()));
                one.add(group.getRcreUserId());
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getGrpMgrId());//7
                one.add(group.getGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getGpRegion());//10
                one.add(group.getGroupCenter());//11
                one.add(group.getGroupVillage());//12
                one.add(group.getGroupAddress());//13
                one.add(group.getGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                one.add(String.valueOf(group.getMaxAllowedSubGrps()));//20
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getGpChair() != null) {
                    groupchair = group.getGpChair();
                }
                if (group.getGpTreasurer() != null) {
                    grouptre = group.getGpTreasurer();
                }
                if (group.getGpSecretary() != null) {
                    groupsec = group.getGpSecretary();
                }
                one.add(groupchair);//21
                one.add(grouptre);//22
                one.add(groupsec);//23
                one.add(group.getGpStatus());//24
                one.add(group.getGpStatusCode());//25
                one.add(String.valueOf(group.getNoOfMembers()));//26
                one.add(group.getMeetFrequency());//27
                one.add(String.valueOf(group.getSavingAccounts()));//28
                one.add(String.valueOf(group.getSavingsAmt()));//29
                one.add(String.valueOf(group.getLoanAccounts()));//30
                one.add(String.valueOf(group.getOutstandingBal()));//31
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

    public static ArrayList getUnverifiedGroups(String username) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GroupsTableMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTableMod.class);
            cr.add(Restrictions.ne("rcreUserId", username));
            list = cr.list();
            for (GroupsTableMod group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getGroupCode());
                one.add(String.valueOf(group.getGroupId()));
                one.add(group.getGroupName());
                one.add(sdf.format(group.getRcreTime()));
                one.add(group.getRcreUserId());
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getGrpMgrId());//7
                one.add(group.getGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getGpRegion());//10
                one.add(group.getGroupCenter());//11
                one.add(group.getGroupVillage());//12
                one.add(group.getGroupAddress());//13
                one.add(group.getGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                one.add(String.valueOf(group.getMaxAllowedSubGrps()));//20
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getGpChair() != null) {
                    groupchair = group.getGpChair();
                }
                if (group.getGpTreasurer() != null) {
                    grouptre = group.getGpTreasurer();
                }
                if (group.getGpSecretary() != null) {
                    groupsec = group.getGpSecretary();
                }
                one.add(groupchair);//21
                one.add(grouptre);//22
                one.add(groupsec);//23
                one.add(group.getGpStatus());//24
                one.add(group.getGpStatusCode());//25
                one.add(String.valueOf(group.getNoOfMembers()));//26
                one.add(group.getMeetFrequency());//27
                one.add(String.valueOf(group.getSavingAccounts()));//28
                one.add(String.valueOf(group.getSavingsAmt()));//29
                one.add(String.valueOf(group.getLoanAccounts()));//30
                one.add(String.valueOf(group.getOutstandingBal()));//31
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

    public static ArrayList getUnverifiedGroups() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<GroupsTableMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTableMod.class);
            list = cr.list();
            for (GroupsTableMod group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getGroupCode());
                one.add(String.valueOf(group.getGroupId()));
                one.add(group.getGroupName());
                one.add(sdf.format(group.getRcreTime()));
                one.add(group.getRcreUserId());
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getGrpMgrId());//7
                one.add(group.getGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getGpRegion());//10
                one.add(group.getGroupCenter());//11
                one.add(group.getGroupVillage());//12
                one.add(group.getGroupAddress());//13
                one.add(group.getGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                one.add(String.valueOf(group.getMaxAllowedSubGrps()));//20
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getGpChair() != null) {
                    groupchair = group.getGpChair();
                }
                if (group.getGpTreasurer() != null) {
                    grouptre = group.getGpTreasurer();
                }
                if (group.getGpSecretary() != null) {
                    groupsec = group.getGpSecretary();
                }
                one.add(groupchair);//21
                one.add(grouptre);//22
                one.add(groupsec);//23
                one.add(group.getGpStatus());//24
                one.add(group.getGpStatusCode());//25
                one.add(String.valueOf(group.getNoOfMembers()));//26
                one.add(group.getMeetFrequency());//27
                one.add(String.valueOf(group.getSavingAccounts()));//28
                one.add(String.valueOf(group.getSavingsAmt()));//29
                one.add(String.valueOf(group.getLoanAccounts()));//30
                one.add(String.valueOf(group.getOutstandingBal()));//31
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

    public static boolean addGroupModDetails(int groupId, String bankId, String countryCode, String delFlg, String groupAddress, double groupLoans, String groupName, String groupPhone, String grpMgrId, String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, String groupCode, Date formationDate, String groupCenter, String groupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, int savingAccounts, int solId, String branchName, String meetFrequency, String lastOper) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int addmod = 0;
        try {
            tx = session.beginTransaction();

            GroupsTableMod groups = new GroupsTableMod(groupId, bankId, branchName, countryCode, delFlg, firstMeetDate, formationDate, gpRegion, gpStatus, gpStatusCode, groupAddress, groupCenter, groupCode, groupId, groupLoans, groupName, groupPhone, groupVillage, grpMgrId, grpRegNo, lastOper, lchgDate, lchgUserId, loanAccounts, maxAllowedMembers, maxAllowedSubGrps, meetFrequency, meetPlace, noOfMembers, noOfSubGrps, nxtMeetDate, outstandingBal, rcreTime, rcreUserId, savingAccounts, savingsAmt, solId);
            addmod = (Integer) session.save(groups);
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

    public static boolean executeaddGroup(String bankId, String countryCode, String delFlg, String groupAddress, double groupLoans, String groupName, String groupPhone, String grpMgrId, String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, String groupCode, Date formationDate, String groupCenter, String groupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, int savingAccounts, int solId, String branchName, String meetFrequency, String lastOper) {
        int groupid = addGroupDetails(bankId, countryCode, delFlg, groupAddress, groupLoans, groupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps, noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, groupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency);
        addGroupModDetails(groupid, bankId, countryCode, delFlg, groupAddress, groupLoans, groupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps, noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, groupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper);
        log.debug("Group Id: " + groupid + " Added Successfully");
        return groupid != 0;
    }

    public static void verifyGroup(int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTableMod.class);
            cr.add(Restrictions.eq("groupId", groupId));
            int count = 0;
            ScrollableResults items = cr.scroll();
            while (items.next()) {
                GroupsTableMod group = (GroupsTableMod) items.get(0);
                session.delete(group);
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

    public static void deleteGroup(int groupId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupId", groupId));
            GroupsTable group = (GroupsTable) cr.uniqueResult();
            group.setDelFlg("Y");
            group.setLchgUserId(username);
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

    public static void addSubgroup(int groupId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupId", groupId));
            GroupsTable group = (GroupsTable) cr.uniqueResult();
            group.setNoOfSubGrps(group.getNoOfSubGrps() + 1);
            group.setLchgUserId(username);
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

    public static void removeSubgroup(int groupId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupId", groupId));
            GroupsTable group = (GroupsTable) cr.uniqueResult();
            group.setNoOfSubGrps(group.getNoOfSubGrps() - 1);
            group.setLchgUserId(username);
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

    public static void modifyGroup(int groupId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(GroupsTable.class);
            cr.add(Restrictions.eq("groupId", groupId));
            GroupsTable group = (GroupsTable) cr.uniqueResult();
            List<GroupsTableMod> groups = getgroupModDetails(groupId);
            for (GroupsTableMod gr : groups) {
                group.setBranchName(gr.getBranchName());
                group.setFirstMeetDate(gr.getFirstMeetDate());
                group.setFormationDate(gr.getFormationDate());
                group.setGpChair(gr.getGpChair());
                group.setGpRegion(gr.getGpRegion());
                group.setGpSecretary(gr.getGpSecretary());
                group.setGpStatus(gr.getGpStatus());
                group.setGpStatusCode(gr.getGpStatusCode());
                group.setGpTreasurer(gr.getGpTreasurer());
                group.setGroupAddress(gr.getGroupAddress());
                group.setGroupCenter(gr.getGroupCenter());
                group.setGroupPhone(gr.getGroupPhone());
                group.setGroupVillage(gr.getGroupVillage());
                group.setGrpMgrId(gr.getGrpMgrId());
                group.setGrpRegNo(gr.getGrpRegNo());
                group.setLchgDate(new Date());
                group.setLchgUserId(username);
                group.setMaxAllowedMembers(gr.getMaxAllowedMembers());
                group.setMaxAllowedSubGrps(gr.getMaxAllowedSubGrps());
                group.setMeetFrequency(gr.getMeetFrequency());
                group.setMeetPlace(gr.getMeetPlace());
                group.setMeetTime(gr.getMeetTime());
                group.setNxtMeetDate(gr.getNxtMeetDate());
                group.setSolId(gr.getSolId());
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
}
