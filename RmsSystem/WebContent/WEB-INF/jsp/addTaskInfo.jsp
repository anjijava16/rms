<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("userName") == null) {
		session.invalidate();
%>
<jsp:forward
	page="/login.jsp?errorMessage=Your Session expired,Please login again" />
<%
	} else {
%>

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
</style>
</head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#taskName").focus();
		$("#itemIDSubmit").click(function() {
			if ($("#taskName").val() == '') {
				alert("TaskName is can't be empty ");
				return false;
			}
			if ($("#itemPrice").val() == '') {
				$("#itemPrice").focus();
				alert("ItemPrice is can't be empty ");
				return false;
			}
			var itemPr = $("#itemPrice").val();
			if (!$.isNumeric(itemPr)) {
				$("#itemPrice").focus();
				alert("ItemPrice is can't be Alpha ");
				return false;
			}
			if ($("#itemTakenPersonName").val() == '-1') {
				$("#itemTakenPersonName").focus();
				alert("ItemTakenPersonName is can't be empty ");
				return false;
			}
			if ($("#ItemTakenPlace").val() == '') {
				$("#ItemTakenPlace").focus();
				alert("ItemTakenPlace is can't be empty ");
				return false;
			}
			if ($("#personWith").val() == '') {
				$("#personWith").focus();
				alert("PersonWith is can't be empty ");
				return false;
			}
			if ($("#takenDateID").val() == 'dd-MMM-YY') {
				$("#takenDateID").focus();
				alert("Please enter TakenDate");
				return false;
			}
			if ($("#takenTimeID").val() == '') {
				$("#takenTimeID").focus();
				alert("Please enter TakenTime");
				return false;
			}
			return true;
		});
	});
</script>
<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
	
</script>

<%
	Integer taskId = (Integer) request.getAttribute("taskId");
%>
<body>
	<jsp:include page="homepage.jsp" />
	<form action="addTaskInfo.do" method="post">
		<div id="addTitle">
			<span class="redColor" id="starId"><p style="font-size: 16pt;">Add
					Task Information</p></span>
		</div>

		<div id="centerAdd">
			<table align="center">
				<tr>
					<td id="componentIdLable"><span class="redColor" id="starId">*</span>TaskID:</td>
					<td><input style="width: 245px;" type="text" id="itemName"
						name="itemName" value="<%=taskId%>"></td>
				</tr>
				<tr>
					<td id="componentIdLable"><span class="redColor" id="starId">*</span>TaskName:</td>
					<td><input style="width: 245px;" type="text" name="taskName"
						id="taskName"></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">TaskPriporty:</td>
					<td><select style="width: 250px;" name="itemPersonName"
						id="itemTakenPersonName">
							<option value="-1">Please Select</option>
							<option value="URGENT">URGENT</option>
							<option value="HIGH">HIGH</option>
							<option value="MEDIUM">MEDIUM</option>
							<option value="MINOR">MINOR</option>
							<option value="LOW">LOW</option>
					</select></td>
				</tr>
				
				<tr>
					<td id="vas_desc_lable">TaskType:</td>
					<td><select style="width: 250px;" name="itemPersonName"
						id="itemTakenPersonName">
							<option value="-1">Please Select</option>
							<option value="OFFICE">OFFICE</option>
							<option value="PERSONAL">PERSONAL</option>
							<option value="TECH">TECH</option>
					</select></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">TaskDate:</td>
					<td><input style="width: 245px;" type="text" name="takenDate"
						value="dd-MMM-YY" id="takenDateID"></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">TaskComments:</td>

					<td><textarea style="width: 100%; height: 100%; border: none">
                  </textarea></td>
				</tr>
				<tr>
					<td id="vas_desc_lable">TaskTime:</td>
					<td><input type="Text" id="demo1" style="width: 230px;"
						name="takenTime" id="takenTimeID"> <a
						href="javascript:NewCal('demo1','ddmmmyyyy',true,24)"><img
							src="images/cal.gif" width="16" height="16" border="0"
							alt="Pick a date"></a></td>
				</tr>
			</table>
			&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;<input type="submit" name="Submit"
				id="itemIDSubmit" /> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp; <input type="reset" name="Reset" />
		</div>
	</form>
	<%
		String message = (String) request.getAttribute("addItem");
			if (message != null) {
				out.println("<h3><font color='red'>" + message
						+ "</font><h3>");
			}
	%>
</body>
</html>
<%
	}
%>
