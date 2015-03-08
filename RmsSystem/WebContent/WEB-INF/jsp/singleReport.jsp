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

th.text {
	face: tahoma;
	font-size: 75%;
	color: brown;
	border-color: black;
}

td.text {
	face: "tahoma";
	font-size: 70%;
	border-color: black;
}
</style>
</head>
<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
	
</script>

<%
	List<String> userList = (List) session.getAttribute("userList");
%>
<body>
	<jsp:include page="homepage.jsp" />
	<h2>
		<p align="center">
			<font color='red'>Select  Report</font>
		</p>
	</h2>
	<form action="individualReportDownload.action">
		<p align="center">
		<span class="redColor" name="itemPersonName">*</span>Select User Name:<select style="width: 250px;" name="itemPersonName">
							<option value="-1">Please Select User</option>
							<%
								for (String username : userList) {
							%>
							<option value="<%=username%>"><%=username%></option>
							<%
								}
							%>
					</select> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<input
				type="submit" name="Submit" /> <br />
		</p>
	</form>
</body>
</html>
<%} %>