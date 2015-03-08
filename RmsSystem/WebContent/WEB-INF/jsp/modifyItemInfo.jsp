<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@ page import="com.iwinner.rms.model.*"%>
<jsp:include page="homepage.jsp" />
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
</style>
</head>
<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
</script>

<%
	ItemInfo itemInfo = (ItemInfo) session.getAttribute("itemInfo");
	Integer id = itemInfo.getItemId();
	String itemName = itemInfo.getItemName();
	Float itemPrice = itemInfo.getPrice();
	String itemTakenPlace = itemInfo.getTakingPlace();
	String itemTakenWithPersons = itemInfo.getPersonsWith();
	String itemTakenPerson = itemInfo.getUsername();
	java.util.Date itemTakenDate = itemInfo.getDate();
	Timestamp itemTakenTime = itemInfo.getTakenTime();
	String purchasePlace = itemInfo.getPurchasePlace();
	String updateBy = itemInfo.getUpdatedBy();
	Timestamp updatedTime = itemInfo.getUpdatedTime();
%>
<body bgcolor='yellow'>
	<form action="modifyItemInsertion.action" method="post">
		<div id="addTitle">
			<span class="redColor" id="starId"><p style="font-size: 16pt;">Modify
					Item Information</p></span>
		</div>
		<%
			List<String> userList = (List) session.getAttribute("userList");
		%>

		<div id="centerAdd">
			<table align="center">
				<tr>
					<td id="componentIdLable"><span class="redColor" id="starId">*</span>ItemId:</td>
					<td><input style="width: 245px;" type="text" name="itemId"
						value='<%=id%>'></td>
				</tr>
				<tr>
					<td id="componentIdLable"><span class="redColor" id="starId">*</span>ItemName:</td>
					<td><input style="width: 245px;" type="text" name="itemName"
						value='<%=itemName%>'></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">ItemPrice:</td>
					<td><input style="width: 245px;" type="text" name="itemPrice"
						value='<%=itemPrice%>'></td>
				</tr>
				<tr>
					<td id="componentIdLable"><span class="redColor"
						name="itemPersonName">*</span>ItemTakenPersonName:</td>
					<td><select style="width: 250px;" name="itemPersonName">
							<option value="-1">Please Select</option>
							<%
								for (String username : userList) {
							%>
							<option value="<%=username%>"><%=username%></option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">ItemTakenPlace:</td>
					<td><input style="width: 245px;" type="text"
						name="itemTakenPlace" value='<%=itemTakenPlace%>'></td>
				</tr>
				<tr>
					<td id="componentIdLable"><span class="redColor"
						id="personsWith">*</span>PersonsWith:</td>
					<td><input style="width: 245px;" type="text"
						name="personsWith" value='<%=itemTakenWithPersons%>'></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">Update By:</td>
					<td><input style="width: 245px;" type="text" name="updatedBy"
						value='<%=updateBy%>'></td>
				</tr>
								<tr>
					<td id="vas_desc_lable">Taken_Date:</td>
					<td><input style="width: 245px;" type="text" name="takenDate"
						value="dd-MMM-YY"></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">Taken_Time:</td>
					<td><input type="Text" id="demo1" style="width: 230px;" name="takenTime">
						<a href="javascript:NewCal('demo1','ddmmmyyyy',true,24)"><img
							src="images/cal.gif" width="16" height="16" border="0"
							alt="Pick a date"></a>
							</td>
				</tr>
			</table>
			&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;<input type="submit" name="Submit" />
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <input
				type="reset" name="Reset" />
		</div>
	</form>
</body>
</html>
<%} %>