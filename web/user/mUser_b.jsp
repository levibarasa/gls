
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*,com.orig.gls.admin.user.dao.User,com.orig.gls.utils.App" errorPage="" %>
<%
    User frd = new User();
    String account = (String) session.getAttribute("userid");
    ArrayList all = frd.getUserDetails(account);
    int size = all.size();

%>
<%    String function = (String) session.getAttribute("ufunction");
    boolean isCancel = App.isCancel(function);
    boolean isInquire = App.isInquire(function);
    String ishiddenib = "";
    if (isCancel || isInquire) {
        ishiddenib = "hidden='true'";
    }
%>

<html>
    <head>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
        <meta http-equiv="pragma" content="no-cache" />
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
        <script language="javaScript" type="text/javascript" src="include/jquery-1.11.3.min.js"></script>
        <script language="javaScript" type="text/javascript" src="include/jquery-ui.min.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="include/jsDatePick_ltr.min.css">
        <script type="text/javascript" src="include/jsDatePick.min.1.3.js"></script>
        <script type="text/javascript">
            function MM_findObj(n, d) { //v4.01
                var p, i, x;
                if (!d)
                    d = document;
                if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
                    d = parent.frames[n.substring(p + 1)].document;
                    n = n.substring(0, p);
                }
                if (!(x = d[n]) && d.all)
                    x = d.all[n];
                for (i = 0; !x && i < d.forms.length; i++)
                    x = d.forms[i][n];
                for (i = 0; !x && d.layers && i < d.layers.length; i++)
                    x = MM_findObj(n, d.layers[i].document);
                if (!x && d.getElementById)
                    x = d.getElementById(n);
                return x;
            }
            function MM_validateForm() { //v4.0
                var i, p, q, nm, test, num, min, max, errors = '', args = MM_validateForm.arguments;
                for (i = 0; i < (args.length - 2); i += 3) {
                    test = args[i + 2];
                    val = MM_findObj(args[i]);
                    if (val) {
                        nm = val.name;
                        if ((val = val.value) !== "") {
                            if (test.indexOf('isEmail') !== -1) {
                                p = val.indexOf('@');
                                if (p < 1 || p === (val.length - 1))
                                    errors += '- ' + nm + ' must contain an e-mail address.\n';
                            } else if (test !== 'R') {
                                num = parseFloat(val);
                                if (isNaN(val))
                                    errors += '- ' + nm + ' must contain a number.\n';
                                if (test.indexOf('inRange') !== -1) {
                                    p = test.indexOf(':');
                                    min = test.substring(8, p);
                                    max = test.substring(p + 1);
                                    if (num < min || max < num)
                                        errors += '- ' + nm + ' must contain a number between ' + min + ' and ' + max + '.\n';
                                }
                            }
                        } else if (test.charAt(0) === 'R')
                            errors += '- ' + nm + ' is required.\n';
                    }
                }
                if (errors)
                    alert('The following error(s) occurred:\n' + errors);
                document.MM_returnValue = (errors === '');
            }

        </script>
        <script type="text/javascript">
            window.onload = function () {
                new JsDatePick({
                    useMode: 2,
                    target: "disableFrom",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "disableTo",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "passexdt",
                    dateFormat: "%d-%M-%Y"
                });
                new JsDatePick({
                    useMode: 2,
                    target: "actexdt",
                    dateFormat: "%d-%M-%Y"
                });
            };
        </script>
        <script type="text/javascript">
            var popup;
            function getSolValueValue() {
                popup = window.open("customer/solpop.jsp", "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }
            function getGroupCodeValue() {
                popup = window.open("customer/grouppop_all.jsp", "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }

            function getSubGroupCodeValue() {
                popup = window.open("customer/subgrouppop_all.jsp", "Functions", "width=500,height=400");
                popup.focus();
                return false;
            }
        </script>
        <script type="text/javascript">
            if (${udeleted == 'true'}) {
                alert("Success\nUser deleted successful\nawaiting verification");
            }
            if (${fatal == 'true'}) {
                alert("ERROR\nA Fatal Error Has Occured\nPlease Contact System Administrator!");
            }
            if (${umodified == 'true'}) {
                alert("Success\nUser details successfuly modified\nawaiting verification");
            }
        </script>
    </head>
    <body>
        <%            for (int i = 0; i < size; i++) {
                ArrayList one = (ArrayList) all.get(i);
        %>
        <h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">User Maintenance</h2>
        <form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=domoduser" onsubmit=" return validatePasswords(this)">
            <table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">
                <tr>
                    <th colspan="12" align="left" scope="col"><div class="header">&nbsp;User Maintenance</div></th>
                </tr>
                <input type="hidden" name="function" id="function" value="${ufunction}" />
                <tr>
                    <td>Username</td>
                    <td>:</td>
                    <td><input type="text" name="uname" id="uname" value=" <%= (String) one.get(0)%>" readonly="true"/></td>
                    <td></td>
                    <td>Role Id</td>
                    <td>:</td>
                    <td><input type="text" name="roleid" id="roleid" onkeyup="this.value = this.value.toUpperCase();" readonly="true"  value=" <%= (int) one.get(1)%>"/></td>
                </tr>
                <tr>
                    <td>New user?</td>
                    <td>:</td>
                    <td><input type="text" name="newuser" id="newuser" onkeyup="this.value = this.value.toUpperCase();" value=" <%= (String) one.get(2)%>" readonly="true" /></td>
                    <td></td>
                    <td>User Status</td>
                    <td>:</td>
                    <td><input type="text" name="userStatus" id="userStatus" onkeyup="this.value = this.value.toUpperCase();" value=" <%= (String) one.get(3)%>" readonly="readony" /></td>
                </tr>        
                <tr>
                    <td>Disabled From</td>
                    <td>:</td>
                    <td><input type="text" name="disableFrom" onkeyup="this.value = this.value.toUpperCase();" id="disableFrom"  readonly="readonly" value="<%= (String) one.get(4)%>" /></td>
                    <td></td>
                    <td>Disabled To</td>
                    <td>:</td>
                    <td><input type="text" name="disableTo" onkeyup="this.value = this.value.toUpperCase();" id="disableTo"  readonly="readonly" value="<%= (String) one.get(5)%>" /></td>
                </tr>
                <tr>
                    <td>Password expiry date</td>
                    <td>:</td>
                    <td><input type="text" name="passexdt" onkeyup="this.value = this.value.toUpperCase();" id="passexdt"  readonly="readonly" value="<%= (String) one.get(6)%>" /></td>
                    <td></td>
                    <td>Account expiry date</td>
                    <td>:</td>
                    <td><input type="text" name="actexdt" onkeyup="this.value = this.value.toUpperCase();" id="actexdt"  readonly="readonly" value="<%= (String) one.get(7)%>" /></td>
                </tr>
                <tr>
                    <td>Last access date</td>
                    <td>:</td>
                    <td><input type="text" name="lastaccess" onkeyup="this.value = this.value.toUpperCase();" id="lastaccess"  readonly="true" value="<%= (String) one.get(8)%>" /></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="reset" name="Submit2" value="Reset"   class="redButton" <%= ishiddenib%>/></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><label>
                            <input name="Submit" <%= ishiddenib%> class="redButton" type="submit" onclick="MM_validateForm('solid', '', 'R', 'branchname', '', 'R', 'groupcode', '', 'R',
                                            'groupname', '', 'R', 'acctmgr', '', 'R', 'regnumber', '', 'R', 'formationdate', '', 'R', 'region', '', 'R', 'groupcenter', '', 'R',
                                            'groupvillage', '', 'R', 'groupaddress', '', 'R', 'groupphone', '', 'R', 'firstmeetingdate', '', 'R', 'nextmeetingdate', '', 'R',
                                            'meetingtime', '', 'R', 'meetingplace', '', 'R', 'maxmembers', '', 'R', 'maxsgroups', '', 'R', 'status', '', 'R', 'statusreason', '', 'R',
                                            'totalmembers', '', 'R', 'meetingfrequency', '', 'R', 'totalsavingacs', '', 'R', 'totalsavingsbal', '', 'R', 'totalloanacs', '', 'R',
                                            'totalloanbal', '', 'R');
                                    return document.MM_returnValue" value="Submit" />
                        </label></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="12"><div class="header">&nbsp;</div></td>
                </tr>
            </table>
        </form>
        <% }%>
    </body>
</html>