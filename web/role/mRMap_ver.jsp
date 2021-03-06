<%@page import="com.orig.stock.admin.Modules"%>
<%@page import="com.orig.stock.dao.Resources"%>
<%@ page import="java.sql.*,java.util.*"%>
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
    <%
        ArrayList adminarray = Resources.getResources(Modules.getADMINISTRATION());
        int adminsize = adminarray.size();

        ArrayList setuparray = Resources.getResources(Modules.getBUSINESS_SETUP());
        int setupsize = setuparray.size();

        ArrayList productarray = Resources.getResources(Modules.getPRODUCT_LISTING());
        int productsize = productarray.size();

        ArrayList orderarray = Resources.getResources(Modules.getPURCHASE_ORDERS());
        int ordersize = orderarray.size();

        ArrayList supplyarray = Resources.getResources(Modules.getINVENTORY_SUPPLIES());
        int supplysize = supplyarray.size();

        ArrayList requestarray = Resources.getResources(Modules.getINVENTORY_REQUETS());
        int requestsize = requestarray.size();

        ArrayList deliveryarray = Resources.getResources(Modules.getINVENTORY_DELIVERIES());
        int deliverysize = deliveryarray.size();

        ArrayList invoicearray = Resources.getResources(Modules.getINVOICES());
        int invoicesize = invoicearray.size();
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
        var popup;
        function getValue() {
            popup = window.open("workclass/wcpop_m.jsp", "Workclasses", "width=500,height=400");
            popup.focus();
            return false;
        }
    </script>
    <script type="text/javascript">
        function validatePasswords(theForm) {
            if (theForm.Username.value.length < 6) {
                alert("Username Cannot be Less than 6characters");
                theForm.Username.focus();
                return false;
            }
            if (theForm.Password.value !== theForm.Password1.value)
            {
                alert("Passwords Do Not Match");
                theForm.Password1.focus();
                return false;
            }
            if (theForm.function.selectedIndex < 0)
            {
                alert("Function Must be Selected.");
                theForm.function.focus();
                return false;
            }
            if (theForm.function.selectedIndex === 0)
            {
                alert("Function Must be Selected.");
                theForm.function.focus();
                return false;
            }
            return true;
        }
    </script>
    <script type="text/javascript">
        if (${funcnull == 'true'}) {
            alert("Function Code Must be Entered");
        }
        if (${dupres == 'true'}) {
            alert("Workclass Already has resources\nPlease Select Modify Instead");
        }
    </script>
</head>
<h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">Work Class Maintenance</h2>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=doverwcrp" onsubmit=" return validatePasswords(this)">
    <table width="70%" border="0" align="center" cellpadding="5" cellspacing="2">
        <tr>
            <th colspan="12" scope="col"><div class="header">&nbsp;Work Class Role Mapping</div></th>
        </tr>
        <tr>
            <td width="20%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
            <td width="20%">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Work Name</td>
            <td style="color: red"></td>
            <td><label>
                    <input name="workclass" type="text" value="${mapclassname}" id="workclass" readonly="true" onkeyup="caps(this)" class="textboxes" />
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Work Class</td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="workc" type="text" value="${mapworkclass}" id="workc" readonly="true" onkeyup="caps(this)" class="textboxes" />
                </label></td>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getADMINISTRATION()%></div></td>
        </tr>

        <tr>
            <%
                for (int i = 0; i < adminsize; i++) {
                    ArrayList one = (ArrayList) adminarray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="admin" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getBUSINESS_SETUP()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < setupsize; i++) {
                    ArrayList one = (ArrayList) setuparray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="setup" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getPRODUCT_LISTING()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < productsize; i++) {
                    ArrayList one = (ArrayList) productarray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="product" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getPURCHASE_ORDERS()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < ordersize; i++) {
                    ArrayList one = (ArrayList) orderarray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="order" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getINVENTORY_SUPPLIES()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < supplysize; i++) {
                    ArrayList one = (ArrayList) supplyarray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="supply" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getINVENTORY_REQUETS()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < requestsize; i++) {
                    ArrayList one = (ArrayList) requestarray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="request" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getINVENTORY_DELIVERIES()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < deliverysize; i++) {
                    ArrayList one = (ArrayList) deliveryarray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="delivery" disabled="true" value="<%= (String) one.get(1)%>" <%=checked%> /></td>
                <% }%>
        </tr>
        <tr>
            <td colspan="12"><div class="header"><%= Modules.getINVOICES()%></div></td>
        </tr>
        <tr>
            <%
                for (int i = 0; i < invoicesize; i++) {
                    ArrayList one = (ArrayList) invoicearray.get(i);
                    boolean isChecked = Resources.workClassHasResources((String) session.getAttribute("mapclassname"), (Integer) session.getAttribute("mapworkclass"), (String) one.get(1));
                    String checked = "";
                    if (isChecked) {
                        checked = "checked='checked'";
                    }
            %>
            <td><%= (String) one.get(1)%></td>
            <td><input type="checkbox" name="invoice" disabled="true" value="<%= (String) one.get(1)%>" <%= checked%> /></td>
                <% }%>
        </tr>
    </table>
    <table width="70%" border="0" align="center" cellpadding="5" cellspacing="2">
        <td>&nbsp;</td>
        <td><label>

                <div align="right">
                    <input type="reset" name="Submit2" value="Reset"   class="redButton"/>
                </div>
            </label></td>
        <td>&nbsp;</td>
        <td><label>
                <input name="Submit" class="redButton" type="submit" onclick="MM_validateForm('workclass', '', 'R', 'workc', '', 'R');
                        return document.MM_returnValue;
                        validatePasswords(this)" value="Submit" />
            </label></td>
        <td>&nbsp;</td>
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
