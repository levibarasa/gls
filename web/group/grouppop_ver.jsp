<%@page import="com.orig.gls.group.dao.Group"%>
<%@page import="com.orig.gls.categories.dao.Category"%>
<%@page import="com.orig.gls.admin.user.dao.User"%>
<%@page import="com.orig.gls.branch.dao.Branch"%>
<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <%
        ArrayList all = Group.getUnverifiedGroups((String) session.getAttribute("uname"));
        int size = all.size();
    %>	
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>

        <script type="text/javascript">
            function getGroupValue(ths, grpname, solid, brname, grmgr, regno, fordat,
                    regi, center, vill, addr, pho, firmdate, nxtmdate, mtime, mtplace,
                    alwdm, alwdgs, grpchair, grptre, grpsec, gpstat, gpstatcd, nomem, mefreq,
                    saac, saacbal, lac, lacbal) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("groupcode");
                    var grpn = window.opener.document.getElementById("groupname");
                    var sol = window.opener.document.getElementById("solid");
                    var br = window.opener.document.getElementById("branchname");
                    var mgr = window.opener.document.getElementById("acctmgr");
                    var reg = window.opener.document.getElementById("regnumber");
                    var formd = window.opener.document.getElementById("formationdate");
                    var region = window.opener.document.getElementById("region");
                    var cen = window.opener.document.getElementById("groupcenter");
                    var villa = window.opener.document.getElementById("groupvillage");
                    var addre = window.opener.document.getElementById("groupaddress");
                    var phon = window.opener.document.getElementById("groupphone");
                    var fstdate = window.opener.document.getElementById("firstmeetingdate");
                    var nxtdate = window.opener.document.getElementById("nextmeetingdate");
                    var metime = window.opener.document.getElementById("meetingtime");
                    var meplace = window.opener.document.getElementById("meetingplace");
                    var alowedm = window.opener.document.getElementById("maxmembers");
                    var alowedgs = window.opener.document.getElementById("maxsgroups");
                    var grpch = window.opener.document.getElementById("chairperson");
                    var grptr = window.opener.document.getElementById("treasurer");
                    var grpsecr = window.opener.document.getElementById("secretary");
                    var grpstat = window.opener.document.getElementById("status");
                    var grpstatcd = window.opener.document.getElementById("statusreason");
                    var noofme = window.opener.document.getElementById("totalmembers");
                    var metfreq = window.opener.document.getElementById("meetingfrequency");
                    var svacs = window.opener.document.getElementById("totalsavingacs");
                    var svacsbal = window.opener.document.getElementById("totalsavingsbal");
                    var loacs = window.opener.document.getElementById("totalloanacs");
                    var loacsbal = window.opener.document.getElementById("totalloanbal");
                    func.value = ths.innerHTML; //for innerhtml
                    grpn.value = grpname;
                    sol.value = solid;
                    br.value = brname;
                    mgr.value = grmgr;
                    reg.value = regno;
                    formd.value = fordat;
                    region.value = regi;
                    cen.value = center;
                    villa.value = vill;
                    addre.value = addr;
                    phon.value = pho;
                    fstdate.value = firmdate;
                    nxtdate.value = nxtmdate;
                    metime.value = mtime;
                    meplace.value = mtplace;
                    alowedm.value = alwdm;
                    alowedgs.value = alwdgs;
                    grpch.value = grpchair;
                    grptr.value = grptre;
                    grpsecr.value = grpsec;
                    grpstat.value = gpstat;
                    grpstatcd.value = gpstatcd;
                    noofme.value = nomem;
                    metfreq.value = mefreq;
                    svacs.value = saac;
                    svacsbal.value = saacbal;
                    loacs.value = lac;
                    loacsbal.value = lacbal;
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
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Code</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Id</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Name</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created Date</span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Created By</span></th>
            </tr>
            <%
                for (int i = 0; i < size; i++) {
                    ArrayList one = (ArrayList) all.get(i);
            %>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getGroupValue(this, '<%=(String) one.get(2)%>', '<%=(String) one.get(5)%>',
                                '<%=(String) one.get(6)%>', '<%=(String) one.get(7)%>', '<%=(String) one.get(8)%>', '<%=(String) one.get(9)%>',
                                '<%=(String) one.get(10)%>', '<%=(String) one.get(11)%>', '<%=(String) one.get(12)%>', '<%=(String) one.get(13)%>',
                                '<%=(String) one.get(14)%>', '<%=(String) one.get(15)%>', '<%=(String) one.get(16)%>', '<%=(String) one.get(17)%>',
                                '<%=(String) one.get(18)%>', '<%=(String) one.get(19)%>', '<%=(String) one.get(20)%>', '<%=(String) one.get(21)%>',
                                '<%=(String) one.get(22)%>', '<%=(String) one.get(23)%>', '<%=(String) one.get(24)%>', '<%=(String) one.get(25)%>',
                                '<%=(String) one.get(26)%>', '<%=(String) one.get(27)%>', '<%=(String) one.get(28)%>', '<%=(String) one.get(29)%>',
                                '<%=(String) one.get(30)%>','<%=(String) one.get(31)%>')" id="cname"><%=(String) one.get(0)%></a></div></td>
                <td><div align="center"><%=(String) one.get(1)%></div></td>
                <td><div align="center"><%=(String) one.get(2)%></div></td>
                <td><div align="center"><%=(String) one.get(3)%></div></td>
                <td><div align="center"><%=(String) one.get(4)%></div></td>
            </tr>
            <% }%>
        </table>
    </form>
</html>