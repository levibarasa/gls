<%@ page import="java.sql.*,java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
    if (${catexists == 'true'}) {
        alert("Category already Exists");
    }

</script>
<script type="text/javascript">
    var popup;
    function getCategoriesValue() {
        popup = window.open("categories/categories_pop.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;
    }
    function getFunction() {
        popup = window.open("categories/function.jsp", "Locations", "width=500,height=400");
        popup.focus();
        return false;
    }
</script>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=domcatsub">
    <table width="80%" border="0" align="left">
        <tr>
            <th colspan="8" align="left" scope="col"><div class="header">
            <div align="left">Categories Maintenance</div>
        </div></th>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Function </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="function" readonly="true" value="${catfunc}" type="text" id="function"/>
                </label></td>
            <td>&nbsp;</td>
            <td>Category Type </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="categorytype" readonly="true" value="${categorytype}" type="text"  id="categorytype"/>
                </label></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Category Value </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="categoryvalue" onkeyup="this.value = this.value.toUpperCase();"   type="text" id="categoryvalue"/>
                </label></td>
            <td>&nbsp;</td>
            <td>Category Code </td>
            <td style="color: red">*</td>
            <td><label>
                    <input name="categorycode" onkeyup="this.value = this.value.toUpperCase();" maxlength="5"  type="text"  id="categorycode"/>
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><label>
                    <input name="Submit" type="submit" class="redButton" onclick="MM_validateForm('function', '', 'R', 'categorytype', '', 'R', 'categoryvalue', '', 'R', 'categorycode', '', 'R');
                            return document.MM_returnValue" value="Submit" />
                </label></td>
        </tr>
    </table>
</form>
<hr width="600px" style="background:#00C3F9; border:#00C3F9 solid 2px;"/>