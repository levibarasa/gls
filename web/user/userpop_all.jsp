
<%@page import="com.orig.gls.admin.user.dao.User"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
        ArrayList all = User.getUsersList();
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">     if (${succ == 'true'}) {
                alert("Action taken Successfully");
            }
        </script>
        <script type="text/javascript">
            function getUsersValue(ths, role, branch) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("username");
                    var roleid = window.opener.document.getElementById("workclass");
                    var solid = window.opener.document.getElementById("branch");
                    func.value = ths.innerHTML; //for innerhtml
                    roleid.value = role;
                    solid.value = branch;
                }
                window.close();
            }
        </script>

    </head>
    <div class="header">View User Details</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">User Name </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">User Role </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">User Branch </span></th>
            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getUsersValue(this, '<%=(String) one.get(1)%>', '<%=(String) one.get(2)%>')" id="cname"><%=(String) one.get(0)%></a></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
            </tr>

            <% }%>
        </table>
    </form>
</html>