<html>
    <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
             java.util.*" errorPage="" %>
    <head>
        <style type="text/css">
            <!--
            .style10 {color: #013567; font-weight: bold; font-size: 14px; }
            -->
        </style>
        <script type="text/javascript">
            function getActionValue(ths) {
                alert(ths);
                var t = ths.innerHTML; //for innerhtml
                window.opener.document.form1.function.value = t;
                window.close();
            }
        </script>

    </head>
    <div class="header">Action Functions</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Function Name </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Function Code </span></th>
            </tr>

            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getActionValue(this);" id="cadd">ADD</a></div></td>
                <td><div align="center">A</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getActionValue(this);" id="cmodify">MODIFY</a></div></td>
                <td><div align="center">M</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getActionValue(this);" id="cverify">VERIFY</a></div></td>
                <td><div align="center">V</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getActionValue(this);" id="cinquire">INQUIRE</a></div></td>
                <td><div align="center">I</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getActionValue(this);" id="ccancel">CANCEL</a></div></td>
                <td><div align="center">C</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getActionValue(this);" id="cdelete">DELETE</a></div></td>
                <td><div align="center">D</div></td>
            </tr>
        </table>
    </form>
</html>