<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="java.util.*"%>
<%@page import="com.iwinner.rms.model.UserRole"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String userAction=(String)session.getAttribute("userAction");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<jsp:include page="homepage.jsp" />
<c:choose>
<c:when test='${userAction=="NEW"}'>
<%{ %>
<html>

<head>
<link href="/css/login-box.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#username").focus();
	
	$("#registerMenu").click(function(){
		if($("#username").val()==''){
			alert("UserName is can't be empty ");
			return false;
		}
		console.log($("#username").val());	
		if($("#fullName").val()==''){
			alert("FulName is can't be empty ");
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
		if($("#userRole").val()=='-1'){
			alert("Please Select Role ");
			return false;
		}
		if($("#email").val()==''){
			alert("Email is can't be empty ");
			return false;
		}
		console.log($("#email").val());
		 if($('input[type=checkbox]:checked').length == 0)
		    {
		        alert('Please select Terms & Conditions');
		        return false;
		    }
		return true;
});
	
	$("#resetID").click(function(){
		document.getElementById("username").value="";
		document.getElementById("email").value="";
		document.getElementById("phone").value="";
		document.getElementById("fullName").value="";
		document.getElementById("userRole").value="-1";
		document.getElementById("agree").value="";
		$('#agree').attr('checked', false); // Unchecks it
		//$('#myCheckbox').attr('checked', true); // Checks it
       /*  var val=$('input[type=checkbox]:checked').length;
        alert(val);
        if(val==1){
        	$('#uncheck').show();
        	$('#check').hide();
        } */
		
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
	<form name="newUser" id="newUser" action="newUserC.action" method="POST"
		style="margin-top: 50px; margin-left: 300px">
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
				<td><select style="width: 172px;" name="userRole" id="userRole">
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
			<input type="submit" name="RegisterForm" value='Register' id='registerMenu'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="RESET" value='RESET' id='resetID'>&nbsp;&nbsp;
	</form>
</div>

<%} %></c:when>

<c:when test='${userAction=="SEARCH"}'>
<%{ %>


<H2>SEARCH USER</H2>

<%} %></c:when>


<c:when test='${userAction=="VIEW"}'>
<%{ %>

<H2>VIEW USER</H2>

<%} %></c:when>


<c:when test='${userAction=="DELETE"}'>
<%{ %>

<H2>DELETE USER</H2>

<%} %></c:when>

<c:when test='${userAction=="UPDATE"}'>
<%{ %>

<H2>UPDATE USER</H2>

<%} %></c:when>

</c:choose>

</body>
</html>