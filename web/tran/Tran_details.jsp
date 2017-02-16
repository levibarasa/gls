<link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.tran.dao.Transact" errorPage="" %>
<%
    Transact frd = new Transact();
    String group = (String) session.getAttribute("groupCode");
    String subgroup = (String) session.getAttribute("subgroupCode");
    ArrayList all = frd.getAllAccountsMappedtoSubGroup(group, subgroup);
    ArrayList groupAct = frd.getAccountMappedtoSubGroup(group, subgroup);
    int size = all.size();
    int siz = groupAct.size();
%>
<style type="text/css">
    <!--
    .style10 {color: #000000; font-weight: bold; font-size: 12px; }
    .style11 {color: #E49C7C}
    .button {color: #FF9E4D}
    -->
</style>
<script type="text/javascript">
    if (${uverified == 'true'}) {
        alert("Record Verified Successfully");
    }
</script>
<div class="header">Available loan Accounts Mapped to sub group</div>
<br/>
<br/>
<form method="post" action="do?MOD=BOK&ACT=domuser">
    <%            for (int j = 0; j < siz; j++) {
            ArrayList ones = (ArrayList) all.get(j);
    %>
    <table width="95%" align="center"  style="border:#5757D9 solid 2px;padding:10px;" border="1">
        <tr>
            <td>Sub Group Code</td>
            <td><input type="text" name="subgroup" onkeyup="this.value = this.value.toUpperCase();" value="<%=subgroup%>" id="subgroup" readonly="true" /></td>
            <td>Group Code</td>
            <td><input type="text" name="group" onkeyup="this.value = this.value.toUpperCase();" value="<%=group%>" id="group" readonly="true" /></td>
        </tr>
        <tr>
            <td>Sub Group Account No</td>
            <td><input type="text" name="subgroupName" onkeyup="this.value = this.value.toUpperCase();" value="<%=(String) ones.get(0)%>" id="subgroupNo" readonly="true" /></td>
            <td>Sub Group Account Name</td>
            <td><input type="text" name="subgroupName" onkeyup="this.value = this.value.toUpperCase();" value="<%=(String) ones.get(1)%>" id="subgroupName" readonly="true" /></td>
        </tr>
        <tr>
            <th bgcolor="#5757D9" scope="col" style="width: 15%"><span class="style10">Sub Group Account No </span></th>
            <th bgcolor="#5757D9" scope="col" style="width: 15%"><span class="style10">Recipient Account No </span></th>
            <th bgcolor="#5757D9" scope="col" style="width: 25%"><span class="style10"> Recipient Account Name</span></th>
            <th bgcolor="#5757D9" scope="col" style="width: 15%"><span class="style10">Transaction Amount</span></th>
            <th bgcolor="#5757D9" scope="col" style="width: 10%"><span class="style10">Action</span></th>
        </tr>        <% for (int i = 0; i < size; i++) {
                ArrayList one = (ArrayList) all.get(i);%>
        <tr style="height:30px; padding:4px;">
            <td><div align="center"><%=(String) ones.get(0)%></div></td>
            <td><div align="center"><%=(String) one.get(0)%></div></td>
            <td><div align="center"><%=(String) one.get(1)%></div></td>
            <td><input type="text" name="tranAmt" onkeyup="this.value = this.value.toUpperCase();" value="" id="tranAmt"/></td>
            <td>
                <input type="hidden" name="actions" value="<%=(String) one.get(2)%>" />
                <input type="checkbox" name="selectedAct" value="Select" />
            </td>
        </tr>
        <% }
            }%>
    </table>
</form>