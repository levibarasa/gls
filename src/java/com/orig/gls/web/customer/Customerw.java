package com.orig.gls.web.customer;

import com.orig.gls.bank.dao.Bank;
import com.orig.gls.customer.dao.Customer;
import com.orig.gls.group.dao.Group;
import com.orig.gls.jpa.SolGroupControlTable;
import com.orig.gls.jpa.SubGrpTableMod;
import com.orig.gls.subgroup.dao.SubGroup;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Customerw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("function");
            session.setAttribute("cfunction", function);
            if (function.equalsIgnoreCase("ADD")) {
                session.setAttribute("content_page", "customer/mUnMappedActs.jsp");
            } else if (function.equalsIgnoreCase("VERIFY")) {
                session.setAttribute("content_page", "customer/mCustMod.jsp");
            } else {
                session.setAttribute("content_page", "customer/mOtherActions.jsp");
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleAddCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String account = request.getParameter("actions");
            String mg = request.getParameter("group");
            String smg = request.getParameter("subgroup");
            if (account != null && mg != null && smg != null && !account.equalsIgnoreCase("") && !mg.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                if (Customer.getAccountDetails(account, mg, smg, "A")) {
                    session.setAttribute("msg", "Successfuly mapped account no " + account);
                    session.setAttribute("mapsuc", true);
                } else {
                    session.setAttribute("msg", "Error occured while mapping account no " + account);
                }
            } else {
                session.setAttribute("msg", "Sorry but failed to map account no " + account);
            }
            session.setAttribute("content_page", "customer/mUnMappedActs.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("cverified", false);
        session.setAttribute("cadded", false);
        session.setAttribute("cdeleted", false);
        session.setAttribute("ccancelled", false);
        session.setAttribute("cmodified", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String accountNo = request.getParameter("actions");
            String function = (String) session.getAttribute("cfunction");
            String lastOper;
            switch (function) {
                case "VERIFY":
                    lastOper = Customer.getLastGamModOper(accountNo);
                    System.out.println("Last Operation " + lastOper);
                    switch (lastOper) {
                        case "A":
                            System.out.println("Verification Account submitted " + accountNo);
                            Customer.verifyAccountDetails(accountNo);
                            session.setAttribute("cverified", true);
                            session.setAttribute("content_page", "customer/mCustMod.jsp");
                            break;
                        case "D":
                            Customer.verifyAccountDetails(accountNo);
                            session.setAttribute("cverified", true);
                            session.setAttribute("content_page", "customer/mCustMod.jsp");
                            break;
                        case "M":
                            Customer.verifyAccountDetails(accountNo);
                            session.setAttribute("cverified", true);
                            session.setAttribute("content_page", "customer/mCustMod.jsp");
                            break;
                    }
                    break;
                case "MODIFY":
                    session.setAttribute("account", accountNo);
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
                case "EXIT MEMBER":
                    session.setAttribute("account", accountNo);
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("account", accountNo);
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    // Modify all customer account mappings by logical delete or modify
    public static void handleModifyCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("cverified", false);
        session.setAttribute("cadded", false);
        session.setAttribute("cdeleted", false);
        session.setAttribute("ccancelled", false);
        session.setAttribute("cmodified", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String account = request.getParameter("actions");
            String function = (String) session.getAttribute("cfunction");
            System.out.println("Function selected " + function);
            String mg = request.getParameter("group");
            String smg = request.getParameter("subgroup");
            switch (function) {
                case "MODIFY":
                    if (account != null && mg != null && smg != null && !account.equalsIgnoreCase("") && !mg.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                        if (Customer.getAccountDetails(account, mg, smg, "M")) {
                            session.setAttribute("modsuc", true);
                        } else {
                            session.setAttribute("fatal", true);
                        }
                    } else {
                        session.setAttribute("msg", "Sorry but failed to map account no " + account);
                    }
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
                case "EXIT MEMBER":
                    if (account != null && mg != null && smg != null && !account.equalsIgnoreCase("") && !mg.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                        if (Customer.getAccountDetails(account, mg, smg, "D")) {
                            session.setAttribute("delsuc", true);
                        } else {
                            session.setAttribute("fatal", true);
                        }
                    } else {
                        session.setAttribute("msg", "Sorry but failed to map account no " + account);
                    }
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
    // 

    public static void handleMaintainSubGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("sgexists", false);
        session.setAttribute("fatal", false);
        session.setAttribute("sgadded", false);
        session.setAttribute("sgverified", false);
        session.setAttribute("sgmodified", false);
        session.setAttribute("sgdeleted", false);
        session.setAttribute("sgcancelled", false);
        if ((String) session.getAttribute("uname") != null) {
            String bankId = Bank.getBankId();
            List<SolGroupControlTable> banks = Bank.getBankDetails(bankId);
            String countryCode = "";
            String delFlg = "N";
            for (SolGroupControlTable bank : banks) {
                countryCode = bank.getHomeCntryCode();
            }
            String groupAddress = request.getParameter("groupaddress");
            int groupLoans = Integer.parseInt(request.getParameter("totalloanacs"));
            String groupName = request.getParameter("groupname");
            String subgroupName = request.getParameter("subgroupname");
            String groupPhone = request.getParameter("groupphone");
            String grpMgrId = request.getParameter("acctmgr");
            String grpRegNo = request.getParameter("regnumber");
            Date lchgDate = new Date();
            String lchgUserId = (String) session.getAttribute("uname");
            int maxAllowedMembers = Integer.parseInt(request.getParameter("maxmembers"));
            int noOfMembers = 0;
            double outstandingBal = Double.parseDouble(request.getParameter("totalloanbal"));
            double savingsAmt = Double.parseDouble(request.getParameter("totalsavingsbal"));
            Date rcreTime = new Date();
            String rcreUserId = (String) session.getAttribute("uname");
            String gpRegion = request.getParameter("region");
            String groupCode = request.getParameter("groupcode");
            String subgroupCode = request.getParameter("subgroupcode");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            Date formationDate = new Date();
            String groupCenter = request.getParameter("groupcenter");
            String groupVillage = request.getParameter("groupvillage");
            Date firstMeetDate = new Date();
            Date nxtMeetDate = new Date();
            try {
                formationDate = sdf.parse(request.getParameter("formationdate"));
                firstMeetDate = sdf.parse(request.getParameter("firstmeetingdate"));
                nxtMeetDate = sdf.parse(request.getParameter("nextmeetingdate"));
            } catch (Exception asd) {
                log.debug(asd.getMessage());
            }
            String meetTime = request.getParameter("meetingtime");
            String meetPlace = request.getParameter("meetingplace");
            String gpChair = request.getParameter("chairperson");
            String gpTreasurer = request.getParameter("treasurer");
            String gpSecretary = request.getParameter("secretary");
            String gpStatus = request.getParameter("status");
            String gpStatusCode = request.getParameter("statusreason");
            int loanAccounts = Integer.parseInt(request.getParameter("totalloanacs"));
            int savingAccounts = Integer.parseInt(request.getParameter("totalsavingacs"));
            int solId = Integer.parseInt(request.getParameter("solid"));
            String branchName = request.getParameter("branchname");
            String meetFrequency = request.getParameter("meetingfrequency");
            String function = (String) session.getAttribute("gfunction");
            String lastOper = "";
            int groupId = Group.getGroupId(groupCode, groupName);
            switch (function) {
                case "ADD":
                    if (!SubGroup.subgroupExists(subgroupCode, subgroupName)) {
                        lastOper = "A";
                        if (SubGroup.executeaddSubGroup(bankId, countryCode, delFlg, groupAddress, groupLoans, subgroupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, subgroupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper)) {
                            session.setAttribute("sgadded", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                        } else {
                            session.setAttribute("fatal", true);
                            session.setAttribute("content_page", "subgroup/msubGroup_b.jsp");
                        }
                    } else {
                        session.setAttribute("sgexists", true);
                        session.setAttribute("content_page", "subgroup/msubGroup_b.jsp");
                    }
                    break;
                case "VERIFY":
                    int subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    List<SubGrpTableMod> groups = SubGroup.getsubgroupModDetails(subgroupId);
                    for (SubGrpTableMod group : groups) {
                        lastOper = group.getLastOper();
                    }
                    switch (lastOper) {
                        case "A":
                            SubGroup.verifySubGroup(subgroupId);
                            Group.addSubgroup(groupId, (String) session.getAttribute("uname"));
                            session.setAttribute("sgverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                        case "D":
                            SubGroup.deleteSubGroup(subgroupId, (String) session.getAttribute("uname"));
                            SubGroup.verifySubGroup(subgroupId);
                            Group.removeSubgroup(groupId, (String) session.getAttribute("uname"));
                            session.setAttribute("sgverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                        case "M":
                            SubGroup.modifySubGroup(subgroupId, (String) session.getAttribute("uname"));
                            SubGroup.verifySubGroup(subgroupId);
                            session.setAttribute("sgverified", true);
                            session.setAttribute("content_page", "group/mGroup_a.jsp");
                            break;
                    }
                    break;
                case "MODIFY":
                    subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    lastOper = "M";
                    if (SubGroup.addSubGroupModDetails(subgroupId, bankId, countryCode, delFlg, groupAddress, groupLoans, subgroupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, subgroupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper)) {
                        session.setAttribute("sgmodified", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    } else {
                        session.setAttribute("fatal", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    }
                    break;
                case "DELETE":
                    subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    lastOper = "D";
                    if (SubGroup.addSubGroupModDetails(subgroupId, bankId, countryCode, delFlg, groupAddress, groupLoans, subgroupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, subgroupCode, formationDate, groupCenter, groupVillage, firstMeetDate, nxtMeetDate, meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper)) {
                        session.setAttribute("sgdeleted", true);
                        session.setAttribute("content_page", "group/mGroup_a.jsp");
                    }
                    break;
                case "CANCEL":
                    subgroupId = SubGroup.getsubGroupId(subgroupCode, subgroupName);
                    SubGroup.verifySubGroup(subgroupId);
                    session.setAttribute("sgcancelled", true);
                    session.setAttribute("content_page", "group/mGroup_a.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
