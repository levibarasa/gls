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
            function getFunctionValue(ths) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("meetingfrequency");
                    func.value = ths.innerHTML; //for innerhtml
                }
                window.close();
            }
        </script>

    </head>
    <div class="header">Frequency</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Frequency Code </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Frequency Desc </span></th>
            </tr>

            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getFunctionValue(this)" id="cname">W</a></div></td>
                <td><div align="center">WEEKLY</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getFunctionValue(this)" id="cname">M</a></div></td>
                <td><div align="center">MONTHLY</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getFunctionValue(this)" id="cname">Q</a></div></td>
                <td><div align="center">QUARTERLY</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getFunctionValue(this)" id="cname">H</a></div></td>
                <td><div align="center">HALF-YEARLY</div></td>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getFunctionValue(this)" id="cname">Y</a></div></td>
                <td><div align="center">YEARLY</div></td>
            </tr>
        </table>
    </form>
</html>