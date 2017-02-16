package com.orig.gls.web.tran;

import com.orig.gls.admin.user.dao.User;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Transactionw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("tfunction");
            session.setAttribute("uverified", false);
            session.setAttribute("uadded", false);
            session.setAttribute("udeleted", false);
            session.setAttribute("ucancelled", false);
            session.setAttribute("umodified", false);
            session.setAttribute("fatal", false);
            switch (function) {
                case "ADD":
                    session.setAttribute("tfunction", function);
                    session.setAttribute("content_page", "tran/mTran_add.jsp");
                    break;
                case "VERIFY":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mUnVerified.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mOtherAct.jsp");
                    break;
                case "DELETE":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mOtherAct.jsp");
                    break;

                case "INQUIRE":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mOtherAct.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void getTranDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String function = request.getParameter("tfunction");
        if ((String) session.getAttribute("uname") != null) {
            String group = request.getParameter("group");
            String subgroup = request.getParameter("subgroup");
            session.setAttribute("groupCode", group);
            session.setAttribute("subgroupCode", subgroup);
            switch (function) {
                case "ADD":
                    session.setAttribute("content_page", "Tran_details.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("content_page", "Tran_details.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("content_page", "Tran_details.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("uverified", false);
        session.setAttribute("uadded", false);
        session.setAttribute("udeleted", false);
        session.setAttribute("ucancelled", false);
        session.setAttribute("umodified", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String userName = request.getParameter("username");
            String roleId = request.getParameter("workclass");
            String userPw = request.getParameter("password");
            String conPass = request.getParameter("confpassword");
            Date disabledFromDate = new Date();
            Date disabledUptoDate = new Date();
            Date pwExpyDate = new Date();
            Date acctExpyDate = new Date();
            Date lastAccessTime = new Date();
            String function = (String) session.getAttribute("ufunction");
            String userid = request.getParameter("actions");
            System.out.println("user id " + userid);
            String uname = (String) session.getAttribute("uname");
            switch (function) {
                case "ADD":
                    if (!User.userExists(userName)) {
                        if (userPw.equals(conPass)) {
                            if (User.addUserDetails(userName, roleId, userPw, 0, "12", 0, "Y", disabledFromDate, disabledUptoDate, pwExpyDate, acctExpyDate, 0, lastAccessTime, uname) > 0) {
                                session.setAttribute("uadded", true);
                                session.setAttribute("content_page", "user/mUser_a.jsp");
                            } else {
                                session.setAttribute("fatal", true);
                                session.setAttribute("content_page", "user/mUsers.jsp");
                            }
                        } else {
                            session.setAttribute("fatal", true);
                            session.setAttribute("content_page", "user/mUsers.jsp");
                        }
                    } else {
                        session.setAttribute("uexists", true);
                        session.setAttribute("content_page", "user/mUsers.jsp");
                    }
                    break;
                case "VERIFY":
                    int userId = Integer.parseInt(userid);
                    String lastOper = User.lastOper(userid);
                    switch (lastOper) {
                        case "A":
                            User.markUserUnverified(userId, "A");
                            User.verifyUser(userId);
                            session.setAttribute("uverified", true);
                            break;
                        case "D":
                            User.markUserUnverified(userId, "D");
                            User.verifyUser(userId);
                            session.setAttribute("uverified", true);
                            break;
                        case "M":
                            User.modifyUser(userId, (String) session.getAttribute("uname"));
                            User.markUserUnverified(userId, "A");
                            User.verifyUser(userId);
                            session.setAttribute("uverified", true);
                            break;
                    }
                    session.setAttribute("content_page", "user/mUnVerified.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("userid", userid);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
//                    }
                    break;
                case "DELETE":
                    session.setAttribute("userid", userid);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("userid", userid);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
                    break;
                case "CANCEL":
                    session.setAttribute("userid", userid);
                    userId = User.getUserId(userName);
                    User.verifyUser(userId);
                    session.setAttribute("ucancelled", true);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleModifyTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("uverified", false);
        session.setAttribute("uadded", false);
        session.setAttribute("udeleted", false);
        session.setAttribute("ucancelled", false);
        session.setAttribute("umodified", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String userName = request.getParameter("uname");
            String roleId = request.getParameter("roleid");
            String userPw = "DefaultPassword";
            String userid = (String) session.getAttribute("userid");
            int user = Integer.parseInt(userid);
            System.out.println("Disabled from " + request.getParameter("disableFrom"));
            Date disableFrom = converttoDate(request.getParameter("disableFrom"));
            Date disableTo = converttoDate(request.getParameter("disableTo"));
            Date pwExpyDate = converttoDate(request.getParameter("passexdt"));
            Date acctExpyDate = converttoDate(request.getParameter("actexdt"));
            Date lastAccessTime = converttoDate(request.getParameter("lastaccess"));
            String function = (String) session.getAttribute("ufunction");
            System.out.println("user id " + userid);
            String uname = (String) session.getAttribute("uname");
            switch (function) {
                case "MODIFY":
                    if (User.addUserModDetails(user, userName, roleId, User.EncodeUserPassword(userName, userPw), 0, "12", 0, "Y", disableFrom, disableTo, pwExpyDate, acctExpyDate, 0, lastAccessTime, "M", uname)) {
                        User.markUserUnverified(user, "M");
                        session.setAttribute("umodified", true);
                    } else {
                        session.setAttribute("fatal", true);
                    }
                    break;
                case "DELETE":
                    if (User.addUserModDetails(user, userName, roleId, User.EncodeUserPassword(userName, userPw), 0, "12", 0, "Y", disableFrom, disableTo, pwExpyDate, acctExpyDate, 0, lastAccessTime, "D", uname)) {
                        User.markUserUnverified(user, "D");
                        session.setAttribute("udeleted", true);
                    } else {
                        session.setAttribute("fatal", true);
                    }
                    break;
            }
            session.removeAttribute("userid");
            session.setAttribute("content_page", "user/mOtherAct.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        session.removeAttribute("udeleted");
        session.removeAttribute("umodified");
        response.sendRedirect("index.jsp");
    }

    private static Date converttoDate(String in) {
        try {
            DateFormat format = new SimpleDateFormat("DD-MMM-YYYY", Locale.ENGLISH);
            Date date = format.parse(in);
            return date;
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return new Date();
        }
    }
}
