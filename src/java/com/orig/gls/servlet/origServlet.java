/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.servlet;

import com.orig.gls.web.category.Categoryw;
import com.orig.gls.web.customer.Customerw;
import com.orig.gls.web.group.Groupw;
import com.orig.gls.web.menu.Menuw;
import com.orig.gls.web.tran.Transactionw;
import com.orig.gls.web.user.Accessw;
import com.orig.gls.web.user.Rolew;
import com.orig.gls.web.user.Userw;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Stanley Mungai
 */
public class origServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog("origlogger");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mod = request.getParameter("MOD");
        String act = request.getParameter("ACT");
        String uploadPath = "";
        switch (mod) {
            case "BOK":
                switch (act) {
                    case "doLog":
                        Accessw.handleLogin(request, response, uploadPath);
                        break;
                    case "Login":
                        Accessw.handleLoginNav(request, response);
                        break;
                    case "dochC":
                        Accessw.handleLoginNav(request, response);
                        break;
                    case "Logout":
                        Accessw.handleLogout(request, response);
                        break;
                    case "menu":
                        Menuw.handleMenuOptions(request, response);
                        break;
                    case "gomrole":
                        Rolew.handleGoRole(request, response);
                        break;
                    case "gomgroup":
                        Groupw.handleGoGroup(request, response);
                        break;
                    case "gomcustomer":
                        Customerw.handleGoCustomer(request, response);
                        break;
                    case "doacustomer":
                        Customerw.handleAddCustomer(request, response);
                        break;
                    case "domodcustomer":
                        Customerw.handleMaintainCustomer(request, response);
                        break;
                    case "domcustomer":
                        Customerw.handleModifyCustomer(request, response);
                        break;
                    case "domgroup":
                        Groupw.handleMaintainGroup(request, response);
                        break;
                    case "domsubgroup":
                        Groupw.handleMaintainSubGroup(request, response);
                        break;
                    case "domcat":
                        Categoryw.handleGoCategory(request, response);
                        break;
                    case "domcatsub":
                        Categoryw.handleMaintainCategory(request, response);
                        break;
                    // User section
                    case "gomuser":
                        Userw.handleGoUser(request, response);
                        break;
                    case "domuser":
                        Userw.handleMaintainUser(request, response);
                        break;
                    case "domoduser":
                        Userw.handleModifyUser(request, response);
                        break;
                    case "gotran":
                        Transactionw.handleGoTransaction(request, response);
                        break;
                    case "dotran":
                        Transactionw.handleMaintainTransaction(request, response);
                        break;
                    case "domodtran":
                        Transactionw.handleModifyTransaction(request, response);
                        break;

                    case "doaddtran":
                        Transactionw.getTranDetails(request, response);
                        break;
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
