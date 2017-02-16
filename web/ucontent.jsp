<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,
         java.util.*" errorPage="" %>
<%
    //ArrayList arr = Product.featuredFour();
%>
<style type="text/css">
    .image {
        overflow: hidden;
        transition-duration: 0.8s;
        transition-property: transform;
    }
    .image:hover {
        transform: rotate(360deg);
        -webkit-transform: rotate(360deg);
    }
</style>
<script type="text/javascript">
    if (${nonexistencemenu == 'true'}) {
        alert("ERROR\nMenu Option Does Not Exist");
    }
    if (${rolemenunone == 'true'}) {
        alert("ERROR\nAccess is denied");
    }
</script>

<table width="100%" border="0">
    <tr>
        <th colspan="2" style="line-height:20px; padding:10px;" scope="col"><div class="header">Group Loans System</div></th>
</tr>

<tr>
    <th width="72%" >
<div align="left" style="padding:10px;line-height:20px;">Simbank GLS is a product of Simba Technology Ltd developed to ensure flawless movement of credit for Individual and sub-lending Groups.</div></th>

<th width="28%" rowspan="2" scope="col"><img class="image"  src="images/globe.png" width="245" height="245" /><br/><img src="images/group.png" width="225" height="165" /></th>
</tr>

<tr>
    <td><div align="left" style="padding:10px;line-height:20px;">
            <p><a href="#"><strong>About the Company</strong></a> </p>
            <p><br/>
               Simba technology Ltd was established in 1996 to be a leading system integrator in Africa. Simba has presence in Kenya, Tanzania, Nigeria and Uganda and employs more than 150 qualified and dedicated professionals. We provide Enterprise solutions, Consultancy, Training to Government and private sector. Our expertise and resource base is enhanced by our Value Added Alliances with renowned companies like Infosys Technologies Ltd, Newgen, Nucleus, Agile and ACI Worldwide to name a few.
            </p>
        </div></td>
</tr>
</table>
