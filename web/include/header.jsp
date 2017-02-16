<head>
    <script src="include/jquery-1.11.3.min.js"></script>
    <script src="include/jquery.lettering.js"></script>
    <script src="include/jquery.textillate.js"></script>
    <link href="include/animate.css" rel="stylesheet" type="text/css">
</head>
<script>
    $(function () {
        $('.demo2').textillate({in: {
                effect: 'rotateOutDownRight'
            },
            out: {
                effect: 'bounce'
            },
            loop: true
        });
    });
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="4">
    <tr>
        <td width="200" rowspan="3" bgcolor="#FF9E4D"><a href="#"><img src="images/Logo.png" name="Logo" width="200" height="80" border="0"/></a></td>
        <td width="100%" height="61" bgcolor="#FF9E4D" style="padding-left:20px;font-size:24px; color:#5757D9"><b>Group Lending</b><br />   </td>
    </tr>
    <tr>
        <td align="center" height="27" bgcolor="#5757D9" class="demo2" style="padding-left:20px;color:#FF9E4D"><b>Group Lending System</b></td>
    </tr>
    <tr>
        <td bgcolor="#FF9E4D" style="padding-left:20px;">&nbsp;</td>
    </tr>
</table>

