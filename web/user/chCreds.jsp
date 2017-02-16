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
            var ok_password = /^(?=.*[_$@.])(?=.*[^_$@.])[\w$@.]{7,15}$/;
            var password = theForm.Password.value;
            if (!ok_password.test(password)) {
                alert("Password must be:\n1. Minimum 8  maximum 15 character\n2. Alphanumeric  Characters\n3.Must Have Special Characters\n4.Cannot Be repeated\n5.Must be significatly Different from previous 24 Password");
                theForm.Password.focus();
                return false;
            }
            if (theForm.Password.value !== theForm.Password1.value)
            {
                alert("Passwords Do Not Match");
                theForm.Password1.focus();
                return false;
            }
            return true;
        }
    </script>
    <script type="text/javascript">
        if (${wroldpass == 'true'}) {
            alert("ERROR\nOld Password for ${chpassuname} is not correct");
        }
        if (${pwdexpy == 'true'}) {
            alert("ERROR\n Password for ${expyuname} is Expired");
        }
    </script>
</head>
<h2 style="font: bold 90% 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;font-size: 16px">System Users Maintenance</h2>
<form id="form1" name="form1" method="post" action="do?MOD=BOK&ACT=dochC" onsubmit="return validatePasswords(form1)">
    <table width="70%" border="0" align="center" cellpadding="5" cellspacing="2">
        <tr>
            <th colspan="5" scope="col"><div class="header">&nbsp;User Maintenance</div></th>
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
            <td>User Name</td>
            <td>:</td>
            <td><label>
                    ${chpassuname}
                </label></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Old Password</td>
            <td>:</td>
            <td><label>
                    <input name="Passwordold" type="password" id="Passwordold" class="textboxes" />
                </label></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>New 
                Password</td>
            <td>:</td>
            <td><label>
                    <input name="Password" type="password" id="Password"  class="textboxes"/>
                </label></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>Confirm</td>
            <td>:</td>
            <td><label>
                    <input name="Password1" type="password" id="Password1" class="textboxes" />
                </label></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><label>

                    <div align="right">
                        <input type="reset" name="Submit2" value="Reset"   class="redButton"/>
                    </div>
                </label></td>
            <td>&nbsp;</td>
            <td><label>
                    <input name="Submit" class="redButton" type="submit" onclick="validatePasswords(form1);
                            MM_validateForm('Username', '', 'R', 'Passwordold', '', 'R', 'Password', '', 'R', 'Password1', '', 'R');
                            return document.MM_returnValue;" value="Change Password" />
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
            <td colspan="5"><div class="header">&nbsp;</div></td>
        </tr>
    </table>
</form>
