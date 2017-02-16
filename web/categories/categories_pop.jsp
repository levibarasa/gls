<%@page import="com.orig.gls.categories.dao.Category"%>
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
            function getCategoryValue(ths) {
                if (window.opener !== null && !window.opener.closed) {
                    var func = window.opener.document.getElementById("categorytype");
                    func.value = ths.innerHTML; //for innerhtml
                }
                window.close();
            }
        </script>

    </head>
    <div class="header">Action Functions</div>
    <br/>
    <form id="popup">
        <table width="95%" align="center"  style="border:#013567 solid 2px;padding:10px;" border="0">
            <tr>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Category Type </span></th>
                <th bgcolor="#00C3F9" scope="col"><span class="style10">Category Type Code </span></th>
            </tr>
            <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getCategoryValue(this)" id="tname">LOCATION</a></div></td>
                <td><div align="center">LC</div></td>
            </tr>
             <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getCategoryValue(this)" id="tname">REGION</a></div></td>
                <td><div align="center">RG</div></td>
            </tr>
             <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getCategoryValue(this)" id="tname">CURRENCY</a></div></td>
                <td><div align="center">CR</div></td>
            </tr>
             <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getCategoryValue(this)" id="tname">VILLAGE</a></div></td>
                <td><div align="center">VL</div></td>
            </tr>
             <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getCategoryValue(this)" id="tname">STATUS</a></div></td>
                <td><div align="center">ST</div></td>
            </tr>
             <tr style="height:30px; padding:4px;">
                <td><div align="center"><a href="" onclick="getCategoryValue(this)" id="tname">STATUS REASON</a></div></td>
                <td><div align="center">STRC</div></td>
            </tr>
        </table>
    </form>
</html>