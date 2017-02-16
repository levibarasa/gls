<%@ page import="java.sql.*,java.util.*"%>
<%@page import="com.orig.gls.admin.user.dao.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
    int moduserid = (Integer) session.getAttribute("vuserid");
    boolean isChecked = User.userExistsInMod("muserid");
    String checked = "";
    String checkedn = "";
    if (isChecked) {
        checked = "checked='checked'";
    } else {
        checkedn = "checked='checked'";
    }
%>
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
                if ((val = val.value) != "") {
                    if (test.indexOf('isEmail') != -1) {
                        p = val.indexOf('@');
                        if (p < 1 || p == (val.length - 1))
                            errors += '- ' + nm + ' must contain an e-mail address.\n';
                    } else if (test != 'R') {
                        num = parseFloat(val);
                        if (isNaN(val))
                            errors += '- ' + nm + ' must contain a number.\n';
                        if (test.indexOf('inRange') != -1) {
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
    function validatePasswords(theForm) {
        if (theForm.function.selectedIndex < 0)
        {
            alert("Function Must be Selected.");
            theForm.function.focus();
            return false;
        }
        if (theForm.function.selectedIndex === 0)
        {
            alert("Function Must be Selected.");
            theForm.companycity.focus();
            return false;
        }
        return true;
    }
</script>
<script type="text/javascript">
    if (${userexits == 'true'}) {//funull
        alert("ERROR\nUser already exists!");
    }
    if (${funull == 'true'}) {//
        alert("ERROR\nFunction Must be Entered!");
    }
</script>
<script type="text/javascript">
    var popup;
    function getTownValue() {
        popup = window.open("store/townpop.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;
    }
    function getStoreValue() {
        popup = window.open("store/storepop_all.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;
    }
    function getUsersValue() {
        var e = document.getElementById("function");
        var func = e.options[e.selectedIndex].value;
        if (func === "V") {
            popup = window.open("user/userpop_ver.jsp", "Locations", "width=500,height=400");
        } else {
            popup = window.open("user/userpop_all.jsp", "Locations", "width=500,height=400");
        }
    }
</script>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=doveruser">
    <table width="80%" border="0" align="center">
        <tr>
            <th colspan="5" scope="col"><div class="header">
            <div align="left">Verify User Details </div>
        </div></th>
        </tr>
        <tr>
            <td width="20%">&nbsp;</td>
            <td width="27%">&nbsp;</td>
            <td width="2%">&nbsp;</td>
            <td width="31%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>User Name </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="username" value="${veruser}" readonly="true" type="text" id="username"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>User password </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="password" value="${verpass}" type="password" readonly="true"   id="password"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>User Work class </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="workclass" value="${verworkclass}" type="text" readonly="true"   id="workclass"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>User Store </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="userstore" value="${veruserstore}" type="text" readonly="true"   id="userstore"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Free user</td>
            <td style="color: red">*</td>
            <td><label>
                    <input type="radio" name="freeuser" readonly="true" value="Y" <%= checked%> />
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Captive user</td>
            <td style="color: red">*</td>
            <td><label>
                    <input type="radio" readonly="true" name="freeuser" value="N" <%= checkedn%>/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><div align="right">
                    <input type="reset" name="Submit2" value="Reset" class="redButton" />
                </div></td>
            <td>&nbsp;</td>
            <td><label>
                    <input name="Submit" type="submit" class="redButton" onclick="MM_validateForm('username', '', 'R', 'password', '', 'R', 'workclass', '', 'R', 'userstore', '', 'R');
                            return document.MM_returnValue;
                            validatePasswords(this)" value="Go" />
                </label></td>
            <td>&nbsp;</td>
        </tr>
    </table>
</form>
<hr width="600px" style="background:#00C3F9; border:#00C3F9 solid 2px;"/>