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
            function getGroupValue(ths) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("grouptype");
                    func.value = ths.innerHTML; //for innerhtml
                }
                window.close();
            }
        </script>

    </head>
    <div class="header">Categories</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Type </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Group Type Code </span></th>
            </tr>

            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getGroupValue(this)" id="cname">MAIN GROUP</a></div></td>
                <td><div align="center">MG</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getGroupValue(this)" id="cname">SUB-GROUP</a></div></td>
                <td><div align="center">SG</div></td>
            </tr>
        </table>
    </form>
</html>