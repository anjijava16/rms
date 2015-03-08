<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
}

.redColor {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="homepage.jsp" />
	<form action="homeServlet.action" method="post">
		<div id="addTitle">Add Item Information</div>
		<div id="centerAdd">
			<table>
			
				<tr>
					<p id="p01">
					<td id="componentIdLable"><span class="redColor" id="starId">*</span>Component
						id:</td>
					<td><input style="width: 245px;" type="text" id="component_Id"></td>
			</p>
				</tr>
				<tr>
					<td id="vas_desc_lable">VAS Description:</td>
					<td><input style="width: 245px;" type="text" id="vas_desc"></td>
				</tr>

            </table>
				<p id="p01">
				<td>ItemName</td>
				<input type="text" />
				<br />
				</p>
				<p id="p01">
					ItemPrice <input type="text" /><br />
				</p>
				<p id="p01">
					ItemTakenPlace <input type="text" /><br />
				</p>
				<p id="p01">
					ItemTakenPersonName <input type="text" /><br />
				</p>
			</table>
			<input type="submit" name="Submit" />
		</div>
	</form>
</body>
</html>