
<html>
    <head>
        <%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
                 java.util.*" errorPage="" %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Chama</title>
        <link href="include/admin.css" rel="stylesheet" type="text/css">
        <link href="include/menu.css" rel="stylesheet" type="text/css">
        <link href="include/main.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon" />	
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
        <meta http-equiv="pragma" content="no-cache" />
        <script language="javaScript" type="text/javascript" src="include/sim.js"></script>
        <link rel="stylesheet" type="text/css" href="include/menu.css">
        <%
            String uname = (String) session.getAttribute("uname");
            String classname = (String) session.getAttribute("uwcname");
        %>
        <style type="text/css">
            body
            {
                background-color: #FFFFFF;
                color: #000000;
            }
        </style>
        <script type="text/javascript" src="include/jquery-1.4.2.min.js"></script>
    </head>
    <body>
        <div id="SlideMenu1">
            <div class="menutitle">&nbsp;User Access Menu</div>
            <ul>
                <%if (uname != null) {
                %>
                <li><a href="do?MOD=BOK&ACT=Home">Welcome <%= uname.toUpperCase()%></a>
                <li><a href="do?MOD=BOK&ACT=Logout">Logout</a></li>
                    <%} else {%>
                <li><a href="do?MOD=BOK&ACT=Login">Login</a></li>
                    <%}%>
            </ul>
            <div class="menutitle">&nbsp;</div>	
        </div>
    </body>
</html>