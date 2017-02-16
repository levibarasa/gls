<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*, com.orig.gls.admin.role.dao.Role" errorPage="" %>
    <%
        ArrayList all = Role.getUnverifiedRoles();
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
            function getValue(ths) {
                var t = ths.innerHTML; //for innerhtml
                window.opener.document.form1.rolename.value = t;
                window.close();
            }
        </script>

    </head>
    <div class="header">View Role Details</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Role Name </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created Date</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created By</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Bank Id </span></th>        

            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getValue(this)" id="cname"><%=(String) one.get(0)%></a></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
            </tr>

            <% }%>
        </table>
    </form>
</html>