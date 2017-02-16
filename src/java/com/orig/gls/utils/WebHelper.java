package com.orig.gls.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebHelper {

    /*
     * Redirect user to specified application resource
     */
    public static void redirect(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    /*
     * Get server context path
     */
    public static String getContextPath(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        String contextpath = request.getContextPath();
        return (scheme + "://" + host + ":" + port + contextpath);
    }

    /*
     * check session validity
     */
    public static boolean sessionisValid(HttpServletRequest request) {
        boolean valid;
        HttpSession session = request.getSession(false);
        if (session != null) {
            valid = (Boolean) session.getAttribute("uname") != null;
        } else {
            valid = false;
        }

        return valid;
    }
    public static void expireSession(HttpServletRequest request){
         HttpSession session = request.getSession(false);
         session.setAttribute("content_page", "sessionexp.jsp");
    }

}
