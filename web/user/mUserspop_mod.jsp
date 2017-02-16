<%@page import="com.orig.gls.group.dao.Group"%>
<%@page import="com.orig.gls.categories.dao.Category"%>
<%@page import="com.orig.gls.admin.user.dao.User"%>
<%@page import="com.orig.gls.branch.dao.Branch"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
        ArrayList all = User.getAllVerifiedUsers();
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>

        <script type="text/javascript">
            function getUserValue(ths, acctexpydate, disabledfromdate, disabledtodate, newuserflg, numpasswordatt, numpasswordhist, pwexpydate, pwdhistory, roleid) {
                
        
        if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("usercode");
                    var aed = window.opener.document.getElementById("acctexpydate");
                    var dfd = window.opener.document.getElementById("disabledfromdate");
                    var dtd = window.opener.document.getElementById("disabledtodate");
                    var nuf = window.opener.document.getElementById("newuserflg");
                    var npa = window.opener.document.getElementById("numpasswordatt");
                    var nph = window.opener.document.getElementById("numpasswordhist");
                    var ped = window.opener.document.getElementById("pwexpydate");
                    var pwdh = window.opener.document.getElementById("pwdhistory");
                    var roleid = window.opener.document.getElementById("roleid");
                    func.value = ths.innerHTML; //for innerhtml
                    aed.value = acctexpydate;
                    dfd.value = disabledfromdate;
                    dtd.value = disabledtodate;
                    nuf.value = newuserflg;
                    npa.value = numpasswordatt;
                    nph.value = numpasswordhist;
                    ped.value = pwexpydate;
                    pwdh.value = pwdhistory;
                    roleid.value = roleid;
                    window.close();
                }

            }
        </script>

    </head>
    <div class="header">Select User</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">User Name</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Work Class</span></th>
            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getUserValue(this, '<%=(String) one.get(2)%>', '<%=(String) one.get(3)%>',
                                '<%=(String) one.get(4)%>', '<%=(String) one.get(5)%>', '<%=(String) one.get(6)%>', '<%=(String) one.get(7)%>',
                                '<%=(String) one.get(8)%>', '<%=(String) one.get(9)%>')" id="cname"><%=(String) one.get(0)%></a></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
            </tr>
            <% }%>
        </table>
    </form>
</html>