<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {
	margin-left: auto width:   500px;
	background-color: #EEEEEE
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.query-2.1.7.js"></script>
<script>
	$(document).ready(
			function() {
				$("#username").focus();
				$("#login").click(
						function() {
							if ($.trim($("#username").val()) == '') {
								alert("UserName is can't be empty ");
								return false;
							}
							if ($.trim($("#password").val()) == '') {
								alert("Password is Not Empty");
								return false;
							}
/* 							console.log($("#password").val().length)
							if ($("#password").val().length >= 6) {
								alert("Please Enter Above 6 Chaaracters");
								return false;
							}
 */							if ($("#password").val() == 'NULL'
									|| $("#password").val() == 'null') {
								alert("Password Can't be Null or null");
								// alert($(this).val());
								$("#password").focus();
								return false;
							}
						});
			});
</script>
</head>
<body>
	<form name="loginPage" id="loginPage" action="login.do" name="form"
		method="POST" style="margin-top: 200px; margin-left: 300px">
		<h2>
			<p style="color: blue">Registration Management System</p>
		</h2>
		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
		%>
		<%
			if (errorMessage != null) {
		%>
		<div style="color: #FF0000;"><%=errorMessage%></div>
		<%
			}
		%>
		<table>
			<tr>
				<td>User Id</td>
				<td><input type="text" name="username" id="username"
					maxlength="100" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" id="password"
					maxlength="16" /></td>
			</tr>
		</table>
		<input type="submit" name="rmsPortal" id="login" value='Login' />&nbsp;&nbsp;
		<input type="submit" name="rmsPortal" value='New Registration'>&nbsp;&nbsp;
		<input type="submit" name="rmsPortal" value='Forgot Password'>&nbsp;&nbsp;

	</form>
</body>
</html>
