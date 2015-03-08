<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*" %>
<%@ page import="com.iwinner.rms.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%
	ItemInfo itemInfo = (ItemInfo) session.getAttribute("itemInfoForID");
   Integer id=itemInfo.getItemId();
   String itemName=itemInfo.getItemName();
   Float itemPrice=itemInfo.getPrice();
   String itemTakenPlace=itemInfo.getTakingPlace();
   String itemTakenWithPersons=itemInfo.getPersonsWith();
   String itemTakenPerson=itemInfo.getUsername();
   java.util.Date itemTakenDate=itemInfo.getDate();
   Timestamp itemTakenTime=itemInfo.getTakenTime();
   String purchasePlace=itemInfo.getPurchasePlace();
   String updateBy=itemInfo.getUpdatedBy();
   Timestamp updatedTime=itemInfo.getUpdatedTime();
   String comments=itemInfo.getComments();
   
%>
	<jsp:include page="homepage.jsp" />
<body>
	<h3>
		<p align="center">Item Id <%=id%> information</p>
	</h3>
	<table cellpadding="10" border="1" style="background-color: #ffffcc;"
		align="center">
		<tr bgcolor='red'>
			<th>ItemInfo</th>
			<th>Descption</th>
		</tr>
		<tr>
			<td>Item Id</td>
			<td><%=id%></td> 
		</tr>
		<tr>
			<td>Item Name</td>
			<td><%=itemName%></td> 
		</tr>

		<tr>
			<td>Item Price</td>
			<td><%=itemPrice%></td> 
		</tr>
		<tr>
			<td>ITEM_TAKEN_NAME</td>
			<td><%=itemTakenPerson%></td> 
		</tr>
		<tr>
			<td>TakingPlace</td>
				<td><%=purchasePlace%></td> 
		</tr>
		<tr>
			<td>TakenAlongWithPersons</td>
			<td><%=itemTakenWithPersons%></td> 
		</tr>
		<tr>
			<td>TAKEN_DATE</td>
			<td><%=itemTakenDate%></td> 
		</tr>
		<tr>
			<td>TAKEN_TIME</td>
			<td><%=itemTakenTime%></td> 
		</tr>
		<tr>
			<td>PURCHASE PLACE</td>
			<td><%=purchasePlace%></td> 
		</tr>
		<tr>
			<td>UPDATED_BY</td>
			<td><%=updateBy%></td> 
		</tr>
		<tr>
			<td>UPDATED_TIME</td>
			<td><%=updatedTime%></td> 
		</tr>
			<tr>
			<td>Comments</td>
			<td><%=comments%></td> 
		</tr>
	</table>
</body>
</html>
<%} %>