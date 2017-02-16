/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.web.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Stanley Mungai
 */
public class Rolew {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("nonexistencemenu", false);
        session.setAttribute("rolemenunone", false);
        if ((String) session.getAttribute("uname") != null) {
            session.setAttribute("content_page", "role/mWorkclass_b.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
