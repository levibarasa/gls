<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.customer.dao.Customer, com.orig.gls.utils.App" errorPage="" %>
<%
    Customer frd = new Customer();
    ArrayList all = frd.getAllUnMappedAccountMod();
    int size = all.size();
%>

<%
    String function = (String) session.getAttribute("cfunction");
    boolean isverify = App.isVerify(function);
    boolean isModify = App.isModify(function);
    boolean isDelete = App.isDelete(function);
    boolean isCancel = App.isCancel(function);
    boolean isInquire = App.isInquire(function);
    String ishidden = "";
    String label = "";
    String ishiddenv = "";
    String ishiddenib = "";
    String isreadonlym = "readonly='true'";
    String isreadonlymcode = "";
    if (isverify || isDelete || isCancel || isInquire) {
        ishiddenv = "hidden='true'";
    }
    if (isInquire) {
        label = "INQUIRE";
        ishiddenib = "hidden='true'";
    }
    if (isModify) {
        label = "MODIFY";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }
    if (isverify) {
        label = "VERIFY";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }
    if (isDelete) {
        label = "DELETE";
        isreadonlym = "";
        isreadonlymcode = "readonly='true'";
    }

%>

<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    -->
</style>

<script type="text/javascript">
    if (${cverified == 'true'}) {
        alert("Record Verified Successfully");
    }
</script>
<script type="text/javascript">
    var popup;
    function getGroupCode() {
        popup = window.open("customer/grouppop_all.jsp", "Functions", "width=500,height=400");
        popup.focus();
        return false;
    }

    function getSubGroup() {
        popup = window.open("customer/subgrouppop_all.jsp", "Functions", "width=500,height=400");
        popup.focus();
        return false;
    }
</script>
<div class="header">Unverified Mapping and Modification</div>
<br/>
<br/>
<form method="post" action="do?MOD=BOK&ACT=domodcustomer">
    <table width="95%" align="center"  style="border:#5757D9 solid 2px;padding:10px;" border="1">
        <tr>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Account Number </span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Account Name </span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Account Open Date</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Account Branch Id</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Scheme Type</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Scheme Code</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Group Id</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Sub Group Id</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Status</span></th>
            <th bgcolor="#5757D9" scope="col"><span class="style10">Action</span></th>
        </tr>
        <%            for (int i = 0; i < size; i++) {
                ArrayList one = (ArrayList) all.get(i);
        %>
        <tr style="height:30px; padding:4px;">
            <td><div align="center"><%=(String) one.get(0)%></div></td>
            <td><div align="center"><%=(String) one.get(1)%></div></td>
            <td><div align="center"><%=(java.util.Date) one.get(2)%></div></td>
            <td><div align="center"><%=(String) one.get(3)%></div></td>
            <td><div align="center"><%=(String) one.get(4)%></div></td>
            <td><div align="center"><%=(String) one.get(5)%></div></td>
            <td><div align="center"><%=(String) one.get(6)%></div></td>
            <td><div align="center"><%=(String) one.get(7)%></div></td>
            <td><div align="center"><%=(String) one.get(8)%></div></td>
            <td>
                <input type="hidden" name="actions" value="<%=(String) one.get(0)%>" />
                <input type="hidden" name="acts" value="<%=label%>" />
                <input type="submit" name="btn" value="<%=label%>" class="style10" />
            </td>
        </tr>
        <% }%>
    </table>
</form>