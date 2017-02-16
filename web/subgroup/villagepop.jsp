<%@page import="com.orig.gls.categories.dao.Category"%>
<%@page import="com.orig.gls.admin.user.dao.User"%>
<%@page import="com.orig.gls.branch.dao.Branch"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
        ArrayList all = Category.getCategories("VILLAGE");
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">
            function getVillageValue(ths) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("groupvillage");
                    func.value = ths.innerHTML; //for innerhtml
                    window.close();
                }

            }
        </script>

    </head>
    <div class="header">Branches</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Center Name</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Center Code</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Category Type </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created By</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Bank Id</span></th>
            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getVillageValue(this)" id="cname"><%=(String) one.get(2)%></a></div></td>
                <td><div align="center"><%=(String) one.get(0)%></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td><div align="center"><%=(String) one.get(4)%></div></td>
            </tr>
            <% }%>
        </table>
    </form>
</html>