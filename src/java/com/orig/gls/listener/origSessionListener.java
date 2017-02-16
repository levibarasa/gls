/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.listener;

import com.orig.gls.admin.user.dao.Access;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Stanley Mungai
 */
public class origSessionListener implements HttpSessionListener {

    private static int totalActiveSessions;
    private static final Map<String, HttpSession> sessions = new HashMap();

    public static int getTotalActiveSession() {
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        totalActiveSessions += 1;
        sessions.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        totalActiveSessions -= 1;
        HttpSession sess = se.getSession();
        String username = (String) sess.getAttribute("uname");
        Access.logoutUser(username);
        sessions.remove(se.getSession().getId());
        sess.setAttribute("content_page", "login.jsp");
    }

    public static boolean invalidate(String sessionId) {
        HttpSession session = (HttpSession) sessions.get(sessionId);
        if (session != null) {
            session.invalidate();
            return true;
        }
        return false;
    }
}
