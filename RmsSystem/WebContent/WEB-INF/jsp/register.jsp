<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.iwinner.rms.model.UserRole"%>
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
<link href="/css/login-box.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="http://code.jquery.com/jquery-2.1.3.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	alert("Test");
	$("#username").focus();
	$("#registerMenu").submit(function(){
		
		if($("#username").val()==''){
			alert("UserName is can't be empty ");
			return false;
		}
		if($("#password").val()==''){
			alert("Password is can't be empty ");
			return false;
		}

		if($("#confirmpassword").val()==''){
			alert("Confirm Password is can't be empty ");
			return false;
		}

		if($("#phone").val()==''){
			alert("Phone is can't be empty ");
			return false;
		}

		if($("#email").val()==''){
			alert("Email is can't be empty ");
			return false;
		}
		$("#newUser").submit();
		return true;
	
});
});
</script>
<script type="text/javascript">
	function check(value) {
		var xmlhttp;
		var url = "./findUser.do";
		url = url + "?username=" + value;

		if (window.XMLHttpRequest)

			xmlhttp = new XMLHttpRequest();
		else
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

		xmlhttp.onreadystatechange = stateChangeObject
		xmlhttp.open("POST", url, true);
		xmlhttp.send();
		function stateChangeObject() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var showdata = xmlhttp.responseText;
				alert(showdata);
				document.getElementById("usernameError").innerHTML = xmlhttp.responseText;
			}
     
		}
	}
</script>
</head>
<%
List<UserRole> listOfRoles = (List<UserRole>) session.getAttribute("userRolL");
%>
<div style="padding: 10px 0 0 100px;">
	<form name="newUser" id="newUser"  action="newUser.action" method="POST"
		style="margin-top: 200px; margin-left: 300px">
		<h2>
			<p style="color: blue">New User Registration</p>
		</h2>
		<table>
			<tr>
				<td>Username :</td>
				<td><input type="text" name="username" id="username"
					maxlength="100" onchange="check(this.value)" /><i
					id="usernameError"></i></td>
			</tr>
			<tr>
				<td>Password</td>

				<td><input type="password" name="password" id="password"
					maxlength="16" /></td>
			</tr>
			<tr>
				<td>ConfirmPassword</td>
				<td><input type="password" name="confirmpassword"
					maxlength="16" id="confirmpassword" /> <br /></td>
			</tr>
			
			<tr>
				<td>Full Name</td>
				<td><input type="text" name="fullName"
					maxlength="16" id="fullName" /> <br /></td>
			</tr>
			<tr>
				<td>Phone :</td>
				<td><input type="text" name="phone" maxlength="100" id="phone" />
					<br /></td>
			</tr>
			<tr>
				<td>Email ID:</td>
				<td><input type="text" name="email" id="email" maxlength="100" />
					<br /></td>
			</tr>
			<tr>
				<td>ROLE:</td>
				<td><select style="width: 145px;" name="userRole" id="userRole">
						<option value="-1">Please Select</option>
						<%
							for (UserRole userRole : listOfRoles) {
						%>
						<option value="<%=userRole.getId()%>"><%=userRole.getRoleName()%></option>

						<%
							}
						%>
				</select></td>
			</tr>
		</table>
		<p>
			<input type="checkbox" id="agree" /> Terms & Conditions <br />
			
			 <input	type="submit" name="RegisterForm" value="Registerr" id="registerMenu">&nbsp;&nbsp;<!--  <input
				type="submit" name="RegisterForm" value='Back' />&nbsp;&nbsp; -->
	</form>
</div>
</html>
<%} %>