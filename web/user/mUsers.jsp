<%@page import="com.orig.gls.utils.App"%>

<%
    String function = (String) session.getAttribute("ufunction");
    boolean isadded = App.isAdd(function);
    boolean isverify = App.isVerify(function);
    boolean isModify = App.isModify(function);
    boolean isDelete = App.isDelete(function);
    boolean isCancel = App.isCancel(function);
    boolean isInquire = App.isInquire(function);
    String ishidden = "";
    String isreadonly = "readonly='true'";
    String ishiddenv = "";
    String ishiddenib = "";
    String isreadonlym = "readonly='true'";
    String isreadonlymcode = "";
    if (isadded) {
        ishidden = "hidden='true'";
        isreadonly = "";
    }
    if (isverify || isDelete || isCancel || isInquire) {
        ishiddenv = "hidden='true'";
    }
    if (isInquire) {
        ishiddenib = "hidden='true'";
    }
    if (isModify) {
        isreadonlym = "";
        isreadonly = "";
        isreadonlymcode = "readonly='true'";
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
    if (${useradded == 'true'}) {
        alert("User added Successfully");
    }
    if (${userexits == 'true'}) {//funull
        alert("ERROR\nUser already exists!");
    }
    if (${fatal == 'true'}) {//funull
        alert("ERROR\nFata Error Occured!");
        
        
    }
    
</script>
<script type="text/javascript">
    var popup;
    function getworkclassValue() {
        popup = window.open("user/wcpop.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;
    }
    
    function getUserCodeValue() {
                var func = document.getElementById("function");
                if (func.value === "VERIFY") {
                    popup = window.open("user/userpop_ver.jsp", "Functions", "width=500,height=400");
                } else if (func.value === "MODIFY" || func.value === "DELETE") {
                    popup = window.open("user/mUserspop_mod.jsp", "Functions", "width=500,height=400");
                } else if (func.value === "CANCEL") {
                    popup = window.open("user/userpop_ver.jsp", "Functions", "width=500,height=400");
                } else if (func.value === "INQUIRE") {
                    popup = window.open("user/userpop_all.jsp", "Functions", "width=500,height=400");
                }
                popup.focus();
                return false;
            }
    function getStoreValue() {
        popup = window.open("user/storepop_all.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;
    }
    function getUsersValue() {
        popup = window.open("user/userpop_all.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;

    }
</script>

<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=domuser" onsubmit=" return validatePasswords(this)">
    <table width="80%" border="0" align="center">
        <tr>
            <th colspan="5" scope="col"><div class="header">
            <div align="left">Add User Details </div>
        </div></th>
        </tr>
        <input type="hidden" name="function" id="function" value="${ufunction}" />
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
                    <input name="username" type="text" id="username" readonly="true"/>
                </label></td>
            <td><a href="" onclick="return getUsersValue()"><img src="images/search.png"></a></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>User password </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="password" type="password"   id="password"/>
                </label></td>
        </tr>
         <tr>
            <td>&nbsp;</td>
            <td>Confirm password </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="confpassword" type="password"   id="confpassword"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>User Role </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="workclass" readonly="true" type="text"   id="workclass"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Sol Id</td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="branch" type="text" readonly="true"  id="branch"/>
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