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
     Users users=(Users)session.getAttribute("usersInfo");
    Integer userId=users.getUserId();
    String username=users.getUsername();
    Timestamp lastModifiedTime=users.getLastModifiedTime();
    Timestamp lastLoginTime=users.getLastLogin();
    String email=users.getEmail();
    String phone=users.getPhone();
    String fullName=users.getFullName();
    String accountStatus=users.getAccountStatus();
    java.util.Date expireDate=users.getExpirationDate();
    String role=users.getRole();
    java.util.Date lastPasswordChangedDate=users.getLastPasswordChangedDate();
   
%>
	<jsp:include page="homepage.jsp" />
<body>
	<h3>
		<p align="center">User Name is = <font color='red'><%=username%> </font>info</p>
	</h3>
	<table cellpadding="10" border="1" style="background-color: #ffffcc;"
		align="center">
		<tr bgcolor='red'>
			<th>ItemInfo</th>
			<th>Descption</th>
		</tr>
		<tr>
			<td>User ID</td>
			<td><%=userId%></td> 
		</tr>
		<tr>
			<td>User Name</td>
			<td><%=username%></td> 
		</tr>

		<tr>
			<td>Email</td>
			<td><%=email%></td> 
		</tr>
		<tr>
			<td>Phone</td>
			<td><%=phone%></td> 
		</tr>
		<tr>
			<td>FullName</td>
				<td><%=fullName%></td> 
		</tr>
		<tr>
			<td>AccountStatus</td>
			<td><%=accountStatus%></td> 
		</tr>
		<tr>
			<td>Expire Date</td>
			<td><%=expireDate%></td> 
		</tr>
		<tr>
			<td>PasswordChangeDate</td>
			<td><%=lastPasswordChangedDate%></td> 
		</tr>
		<tr>
			<td>ROLE</td>
			<td><%=role%></td> 
		</tr>
 
		</tr>
	</table>
</body>
</html>
<%} %>