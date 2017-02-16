<%
    String uname = (String) session.getAttribute("username");
    String uid = (String) session.getAttribute("u_id");

%>
<div id="ddblueblockmenu">
    <div class="menutitle">&nbsp;Vehicle Maintenance</div>
    <ul>

        <li><a href="do?MOD=MTN&ACT=Admin">Home</a></li>
            <%            if (uname != null) {
            %>
        <li><a href="do?MOD=MTN&ACT=addproduct">Add Products</a></li>
        <li><a href="do?MOD=MTN&ACT=addCompany">Add Company</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewcomAppl">View Applications</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewVcl">Vehicle Details</a></li>
        <li><a href="do?MOD=MTN&ACT=addCategory">Add Categories</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewDvr">Driver Details</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewFuel">Fuel Expenses</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewOil">Oil Changes</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewRepair">Vehicle Repairs</a></li>
        <li><a href="do?MOD=MTN&ACT=ViewBook">Booking Details</a></li>
        <li><a href="do?MOD=MTN&ACT=logOutad">Logout</a></li>
            <%} else {%>
        <li><a href="do?MOD=MTN&ACT=Loginad">Login Now</a></li>
            <% }%>
    </ul>
    <div class="menutitle">&nbsp;</div>	
</div>
