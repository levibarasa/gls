/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.subgroup.dao;

import com.orig.gls.group.dao.Group;
import com.orig.gls.hibernate.HibernateUtil;
import com.orig.gls.jpa.GroupsTable;
import com.orig.gls.jpa.SubGrpTable;
import com.orig.gls.jpa.SubGrpTableMod;
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
public class SubGroup {

    private static final Log log = LogFactory.getLog("origlogger");
    private static int subgroupId;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static boolean subgroupExists(String groupCode, String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Restrictions.eq("subGroupCode", groupCode));
            cr.add(Restrictions.eq("subGroupName", groupName));
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

    public static int getsubGroupId(String groupCode, String groupName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int groupid = 0;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Restrictions.eq("subGroupCode", groupCode));
            cr.add(Restrictions.eq("subGroupName", groupName));
            groupid = (Integer) cr.setProjection(Projections.property("subGroupId")).uniqueResult();
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

    public static List<SubGrpTable> getsubgroupDetails(int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SubGrpTable> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Restrictions.eq("subGroupId", groupId));
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

    public static List<SubGrpTableMod> getsubgroupModDetails(int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SubGrpTableMod> list = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTableMod.class);
            cr.add(Restrictions.eq("subGroupId", groupId));
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

    public static int addsubGroupDetails(String bankId, String countryCode, String delFlg, String subGroupAddress, double subGroupLoans, String subGroupName, String subGroupPhone, String subGrpMgrId, String subGrpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int groupId, int noOfMembers, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String subGpRegion, String subgroupCode, Date formationDate, String subGroupCenter, String subGroupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String subGpChair, String subGpTreasurer, String subGpSecretary, String subGpStatus, String subGpStatusCode, int loanAccounts, int savingAccounts, int solId, String branchName, String meetFrequency) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SubGrpTable group = new SubGrpTable(bankId, branchName, countryCode, delFlg, firstMeetDate, formationDate, groupId, lchgDate, lchgUserId, loanAccounts, maxAllowedMembers, meetFrequency, meetPlace, noOfMembers, nxtMeetDate, outstandingBal, rcreTime, rcreUserId, savingAccounts, savingsAmt, solId, subGpRegion, subGpStatus, subGpStatusCode, subGroupAddress, subGroupCenter, subgroupCode, subGroupLoans, subGroupName, subGroupPhone, subGroupVillage, subGrpMgrId, subGrpRegNo);
            subgroupId = (Integer) session.save(group);
            tx.commit();
        } catch (Exception asd) {
            log.debug(asd.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return subgroupId;
    }

    public static ArrayList getAllVerifiedSubGroups() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SubGrpTable> list;
        try {
            tx = session.beginTransaction();
            DetachedCriteria subquery = DetachedCriteria.forClass(SubGrpTableMod.class);
            subquery.setProjection(Projections.property("subGroupId"));
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Subqueries.propertyNotIn("subGroupId", subquery));
            list = cr.list();
            for (SubGrpTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getSubGroupCode());
                one.add(String.valueOf(group.getSubGroupId()));
                one.add(group.getSubGroupName());
                one.add(sdf.format(group.getRcreTime()));
                one.add(group.getRcreUserId());
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getSubGrpMgrId());//7
                one.add(group.getSubGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getSubGpRegion());//10
                one.add(group.getSubGroupCenter());//11
                one.add(group.getSubGroupVillage());//12
                one.add(group.getSubGroupAddress());//13
                one.add(group.getSubGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getSubGpChair() != null) {
                    groupchair = group.getSubGpChair();
                }
                if (group.getSubGpTreasurer() != null) {
                    grouptre = group.getSubGpTreasurer();
                }
                if (group.getSubGpSecretary() != null) {
                    groupsec = group.getSubGpSecretary();
                }
                one.add(groupchair);//21
                one.add(grouptre);//22
                one.add(groupsec);//23
                one.add(group.getSubGpStatus());//24
                one.add(group.getSubGpStatusCode());//25
                one.add(String.valueOf(group.getNoOfMembers()));//26
                one.add(group.getMeetFrequency());//27
                one.add(String.valueOf(group.getSavingAccounts()));//28
                one.add(String.valueOf(group.getSavingsAmt()));//29
                one.add(String.valueOf(group.getLoanAccounts()));//30
                one.add(String.valueOf(group.getOutstandingBal()));//31
                List<GroupsTable> lgs = Group.getgroupDetails(group.getGroupId());
                String groupcode = "";
                String groupname = "";
                for (GroupsTable lg : lgs) {
                    groupcode = lg.getGroupCode();
                    groupname = lg.getGroupName();
                }
                one.add(groupcode);
                one.add(groupname);
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

    public static ArrayList getAllSubGroups() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SubGrpTable> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            list = cr.list();
            for (SubGrpTable group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getSubGroupCode());//0
                one.add(String.valueOf(group.getSubGroupId()));//1
                one.add(group.getSubGroupName());//2
                one.add(sdf.format(group.getRcreTime()));//3
                one.add(group.getRcreUserId());//4
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getSubGrpMgrId());//7
                one.add(group.getSubGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getSubGpRegion());//10
                one.add(group.getSubGroupCenter());//11
                one.add(group.getSubGroupVillage());//12
                one.add(group.getSubGroupAddress());//13
                one.add(group.getSubGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getSubGpChair() != null) {
                    groupchair = group.getSubGpChair();
                }
                if (group.getSubGpTreasurer() != null) {
                    grouptre = group.getSubGpTreasurer();
                }
                if (group.getSubGpSecretary() != null) {
                    groupsec = group.getSubGpSecretary();
                }
                one.add(groupchair);//20
                one.add(grouptre);//21
                one.add(groupsec);//22
                one.add(group.getSubGpStatus());//23
                one.add(group.getSubGpStatusCode());//24
                one.add(String.valueOf(group.getNoOfMembers()));//25
                one.add(group.getMeetFrequency());//26
                one.add(String.valueOf(group.getSavingAccounts()));//27
                one.add(String.valueOf(group.getSavingsAmt()));//28
                one.add(String.valueOf(group.getLoanAccounts()));//29
                one.add(String.valueOf(group.getOutstandingBal()));//30
                List<GroupsTable> lgs = Group.getgroupDetails(group.getGroupId());
                String groupcode = "";
                String groupname = "";
                for (GroupsTable lg : lgs) {
                    groupcode = lg.getGroupCode();
                    groupname = lg.getGroupName();
                }
                one.add(groupcode);
                one.add(groupname);
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

    public static ArrayList getUnverifiedSubGroups(String username) {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SubGrpTableMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTableMod.class);
            cr.add(Restrictions.ne("rcreUserId", username));
            list = cr.list();
            for (SubGrpTableMod group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getSubGroupCode());//0
                one.add(String.valueOf(group.getSubGroupId()));//1
                one.add(group.getSubGroupName());//2
                one.add(sdf.format(group.getRcreTime()));//3
                one.add(group.getRcreUserId());//4
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getSubGrpMgrId());//7
                one.add(group.getSubGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getSubGpRegion());//10
                one.add(group.getSubGroupCenter());//11
                one.add(group.getSubGroupVillage());//12
                one.add(group.getSubGroupAddress());//13
                one.add(group.getSubGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getSubGpChair() != null) {
                    groupchair = group.getSubGpChair();
                }
                if (group.getSubGpTreasurer() != null) {
                    grouptre = group.getSubGpTreasurer();
                }
                if (group.getSubGpSecretary() != null) {
                    groupsec = group.getSubGpSecretary();
                }
                one.add(groupchair);//20
                one.add(grouptre);//21
                one.add(groupsec);//22
                one.add(group.getSubGpStatus());//23
                one.add(group.getSubGpStatusCode());//24
                one.add(String.valueOf(group.getNoOfMembers()));//25
                one.add(group.getMeetFrequency());//26
                one.add(String.valueOf(group.getSavingAccounts()));//27
                one.add(String.valueOf(group.getSavingsAmt()));//28
                one.add(String.valueOf(group.getLoanAccounts()));//29
                one.add(String.valueOf(group.getOutstandingBal()));//30
                List<GroupsTable> lgs = Group.getgroupDetails(group.getGroupId());
                String groupcode = "";
                String groupname = "";
                for (GroupsTable lg : lgs) {
                    groupcode = lg.getGroupCode();
                    groupname = lg.getGroupName();
                }
                one.add(groupcode);
                one.add(groupname);
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

    public static ArrayList getUnverifiedSubGroups() {
        ArrayList arr = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<SubGrpTableMod> list;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTableMod.class);
            list = cr.list();
            for (SubGrpTableMod group : list) {
                ArrayList one = new ArrayList();
                one.add(group.getSubGroupCode());//0
                one.add(String.valueOf(group.getSubGroupId()));//1
                one.add(group.getSubGroupName());//2
                one.add(sdf.format(group.getRcreTime()));//3
                one.add(group.getRcreUserId());//4
                one.add(String.valueOf(group.getSolId()));//5
                one.add(group.getBranchName());//6
                one.add(group.getSubGrpMgrId());//7
                one.add(group.getSubGrpRegNo());//8
                one.add(sdf.format(group.getFormationDate()));//9
                one.add(group.getSubGpRegion());//10
                one.add(group.getSubGroupCenter());//11
                one.add(group.getSubGroupVillage());//12
                one.add(group.getSubGroupAddress());//13
                one.add(group.getSubGroupPhone());//14
                one.add(sdf.format(group.getFirstMeetDate()));//15
                one.add(sdf.format(group.getNxtMeetDate()));//16
                one.add(group.getMeetTime());//17
                one.add(group.getMeetPlace());//18
                one.add(String.valueOf(group.getMaxAllowedMembers()));//19
                String groupchair = "";
                String grouptre = "";
                String groupsec = "";
                if (group.getSubGpChair() != null) {
                    groupchair = group.getSubGpChair();
                }
                if (group.getSubGpTreasurer() != null) {
                    grouptre = group.getSubGpTreasurer();
                }
                if (group.getSubGpSecretary() != null) {
                    groupsec = group.getSubGpSecretary();
                }
                one.add(groupchair);//20
                one.add(grouptre);//21
                one.add(groupsec);//22
                one.add(group.getSubGpStatus());//23
                one.add(group.getSubGpStatusCode());//24
                one.add(String.valueOf(group.getNoOfMembers()));//25
                one.add(group.getMeetFrequency());//26
                one.add(String.valueOf(group.getSavingAccounts()));//27
                one.add(String.valueOf(group.getSavingsAmt()));//28
                one.add(String.valueOf(group.getLoanAccounts()));//29
                one.add(String.valueOf(group.getOutstandingBal()));//30
                List<GroupsTable> lgs = Group.getgroupDetails(group.getGroupId());
                String groupcode = "";
                String groupname = "";
                for (GroupsTable lg : lgs) {
                    groupcode = lg.getGroupCode();
                    groupname = lg.getGroupName();
                }
                one.add(groupcode);
                one.add(groupname);
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

    public static boolean addSubGroupModDetails(int subGroupId, String bankId, String countryCode, String delFlg, String subGroupAddress, double subGroupLoans, String subGroupName, String subGroupPhone, String subGrpMgrId, String subGrpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int groupId, int noOfMembers, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String subGpRegion, String subgroupCode, Date formationDate, String subGroupCenter, String subGroupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String subGpChair, String subGpTreasurer, String subGpSecretary, String subGpStatus, String subGpStatusCode, int loanAccounts, int savingAccounts, int solId, String branchName, String meetFrequency, String lastOper) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int addmod = 0;
        try {
            tx = session.beginTransaction();

            SubGrpTableMod groups = new SubGrpTableMod(subGroupId, bankId, branchName, countryCode, delFlg, firstMeetDate, formationDate, groupId, lastOper, lchgDate, lchgUserId, loanAccounts, maxAllowedMembers, meetFrequency, meetPlace, noOfMembers, nxtMeetDate, outstandingBal, rcreTime, rcreUserId, savingAccounts, savingsAmt, solId, subGpRegion, subGpStatus, subGpStatusCode, subGroupAddress, subGroupCenter, subgroupCode, subGroupId, subGroupLoans, subGroupName, subGroupPhone, subGroupVillage, subGrpMgrId, subGrpRegNo);
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

    public static boolean executeaddSubGroup(String bankId, String countryCode, String delFlg, String subGroupAddress, double subGroupLoans, String subGroupName, String subGroupPhone, String subGrpMgrId, String subGrpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int groupId, int noOfMembers, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String subGpRegion, String subgroupCode, Date formationDate, String subGroupCenter, String subGroupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String subGpChair, String subGpTreasurer, String subGpSecretary, String subGpStatus, String subGpStatusCode, int loanAccounts, int savingAccounts, int solId, String branchName, String meetFrequency, String lastOper) {
        int subgroupid = addsubGroupDetails(bankId, countryCode, delFlg, subGroupAddress, subGroupLoans, subGroupName, subGroupPhone, subGrpMgrId, subGrpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, subGpRegion, subgroupCode, formationDate, subGroupCenter, subGroupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, subGpChair, subGpTreasurer, subGpSecretary, subGpStatus, subGpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency);
        addSubGroupModDetails(subgroupid, bankId, countryCode, delFlg, subGroupAddress, subGroupLoans, subGroupName, subGroupPhone, subGrpMgrId, subGrpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, subGpRegion, subgroupCode, formationDate, subGroupCenter, subGroupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, subGpChair, subGpTreasurer, subGpSecretary, subGpStatus, subGpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper);
        log.debug("Sub-Group Id: " + subgroupid + " Added Successfully");
        return subgroupid != 0;
    }

    public static void verifySubGroup(int groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTableMod.class);
            cr.add(Restrictions.eq("subGroupId", groupId));
            int count = 0;
            ScrollableResults items = cr.scroll();
            while (items.next()) {
                SubGrpTableMod group = (SubGrpTableMod) items.get(0);
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

    public static void deleteSubGroup(int groupId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Restrictions.eq("subGroupId", groupId));
            SubGrpTable group = (SubGrpTable) cr.uniqueResult();
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

    public static void modifySubGroup(int groupId, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(SubGrpTable.class);
            cr.add(Restrictions.eq("subGroupId", groupId));
            SubGrpTable group = (SubGrpTable) cr.uniqueResult();
            List<SubGrpTableMod> groups = getsubgroupModDetails(groupId);
            for (SubGrpTableMod gr : groups) {
                group.setBranchName(gr.getBranchName());
                group.setFirstMeetDate(gr.getFirstMeetDate());
                group.setFormationDate(gr.getFormationDate());
                group.setSubGpChair(gr.getSubGpChair());
                group.setSubGpRegion(gr.getSubGpRegion());
                group.setSubGpSecretary(gr.getSubGpSecretary());
                group.setSubGpStatus(gr.getSubGpStatus());
                group.setSubGpStatusCode(gr.getSubGpStatusCode());
                group.setSubGpTreasurer(gr.getSubGpTreasurer());
                group.setSubGroupAddress(gr.getSubGroupAddress());
                group.setSubGroupCenter(gr.getSubGroupCenter());
                group.setSubGroupPhone(gr.getSubGroupPhone());
                group.setSubGroupVillage(gr.getSubGroupVillage());
                group.setSubGrpMgrId(gr.getSubGrpMgrId());
                group.setSubGrpRegNo(gr.getSubGrpRegNo());
                group.setLchgDate(new Date());
                group.setLchgUserId(username);
                group.setMaxAllowedMembers(gr.getMaxAllowedMembers());
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
