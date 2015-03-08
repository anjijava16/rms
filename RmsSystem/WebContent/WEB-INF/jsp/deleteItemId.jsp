<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ page import="com.iwinner.rms.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <% 
 if(session.getAttribute("userName")==null)
{
	session.invalidate(); %>
	<jsp:forward page="/login.jsp?errorMessage=Your Session expired,Please login again" />
	<% 
}
	else
	{ %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
p#p01 {
	color: blue;
	padding: 5px;
	/* margin:10px; */
}

#centerAdd { /* background-color:black;
   */
	color: blue;
	clear: both;
	text-align: center;
}

#addTitle {
	color: blue;
	text-align: center;
	padding: 5px;
	size: 20
}

.redColor {
	color: red;
}

table.ridge {
	border-collapse: collapse;
	border-width: 1px;
	border-color: brown;
	border-style: ridge;
}
th.text{
	face:tahoma;
	font-size:75%;
	color:brown;
	border-color:black;
}
td.text{
	face:"tahoma";
	font-size:70%;
	border-color:black;
	}
</style>
</head>
<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
</script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#itemId").focus();
	$("#deleteIdSubmit").click(function(){
		if ($("#itemId").val() == '-1') {
			$("#itemId").focus();
			alert("ItemId  can't be empty ");
			return false;
		}		
	});
});
</script>
<%
List<ItemInfo> userList = (List<ItemInfo>) session.getAttribute("listOfItemsInfo");
List<Integer> listOfIds = (List<Integer>) session.getAttribute("listOfIds");
%>
<body>
	<jsp:include page="homepage.jsp" />
	<h2><p align="center"><font color='red'>Delete Items</font> </p> </h2>
	<form action="deleteItemId.action">
    <p align="center">  Select Item Id &nbsp;&nbsp;&nbsp;<select style="width: 250px;" name="itemId" id="itemId">
							<option value="-1">Please Select</option>
							<%
								for (Integer listOf : listOfIds) {
							%>
							<option value="<%=listOf%>"><%=listOf%></option>
							<%
								}
							%>
					</select>
 		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;<input type="submit" name="Submit"  id="deleteIdSubmit"/>
			<br/>
			</p>
			</form>
	<table class=ridge border=1 width="100%">
		<tr>
		    <th class=text>ItemId</th>
			<th class=text>ItemName</th>
			<th class=text>ItemPrice(RM)</th>
			<th class=text>ItemTakenName</th>
			<th class=text>TakenAlongWithPers</th>
			<th class=text>TakenPlace</th>
			<th class=text>TakenDate</th>
			<th class=text>TakenTime</th>
			<th class=text>UpdateTime</th>
			<th class=text>UpdateBy</th>
		</tr>
		<%
			for (int i = 0; i < userList.size(); i++) {
				ItemInfo item = (ItemInfo) userList.get(i);
		%>
		<tr>
		    <td class=text><a href="itemIdInfo.action?itemId=<%=item.getItemId()%>"><%=item.getItemId()%></a></td>
			<td class=text><%=item.getItemName()%></td>
			 <td class=text><%=item.getPrice()%></td>
			<td class=text><%=item.getItemTakenPerson()%></td>
			<td class=text><%=item.getPersonsWith()%></td>
			<td class=text><%=item.getTakingPlace()%></td>
			<td class=text><%=item.getDate()%></td>
			<td class=text><%=item.getTakenTime()%></td>
			<td class=text><%=item.getUpdatedTime()%></td>
			<td class=text><%=item.getUpdatedBy()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
<%} %>