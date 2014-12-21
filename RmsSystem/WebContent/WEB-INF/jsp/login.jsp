<html>
<head>
<title>Login - Streamezzo support</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/dynCalendar.css" type="text/css" media="screen">
<link rel="icon" href="<%=request.getContextPath()%>/images/icon/favicon.ico" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/js/browserSniffer.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/global.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/validation.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/niftycube/niftycube.js"></script>
</head>

<body bgcolor="#FFFFFF" marginwidth="0" marginheight="0" leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" onBeforeUnload="return handleClose()">


<script language="JavaScript">
<!--
function validateForm(f)
{
   
    if (isWhitespace(f.username.value)) {
        alert('Please enter your first name.');
        selectField(f, 'username');
        return false;
    }
    if (isWhitespace(f.passwd.value)) {
        errors[errors.length] = new Option('Password', 'password');
    }
    if (errors.length > 0) {
        return false;
    }
}
//-->
</script>

<!-- <div align="center">
<img alt="Streamezzo Group" title="Streamezzo Group Logo" src="images/company_logo.png" />
<img alt="ChangingWorlds" title="ChangingWorlds Logo" src="images/cw_logo.jpg">
</div>
 -->
<form name="login_form" onSubmit="javascript:return checkFormSubmission(this, 'validateForm');" method="post" action="dailyLogin.do">
<input type="hidden" name="cat" value="login">
<input type="hidden" name="url" value="">
<table align="center" width="900" border="0" cellspacing="0" cellpadding="1" >
  <tr>
    <td>
      <table  width="100%" border="0" cellspacing="0" cellpadding="4">
        <tr>
          <td colspan="2" align="center">
            <!--<h3>Streamezzo support </h3>-->
 <h3>Recrement Process System</h3> 
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<div class="login_table" width="400" align="center">
<table align="center" width="400" border="0" cellspacing="0" cellpadding="1">
  <tr>
    <td>
      <table bgcolor="#FFFFFF" width="100%" border="0" cellspacing="0" cellpadding="4">
        <tr>
          <td colspan="2" bgcolor="#44abd4"><img src="<%=request.getContextPath()%>/images/blank.gif" width="1" height="5"></td>
        </tr>
               <!--    
                 <tr>
          <td colspan="2" align="center" class="error" bgcolor="#44abd4">
            <b>
                          Thank you, you are now logged out of Streamezzo Support.
                        </b>
          </td>
        </tr>
           -->
                <tr>
          <td align="right" width="40%" class="default_white" bgcolor="#44abd4"><b>Email Address:</b></td>
          <td width="60%" bgcolor="#44abd4">
            <input accessKey="e" class="default" type="text" name="username" value="" size="30">
            <img id="error_icon_email" style="visibility: hidden;" src="<%=request.getContextPath()%>/images/icons/error.gif" alt="error condition detected" title="error condition detected" width="1" height="1">          </td>
        </tr>
        <tr>
          <td align="right" width="40%" class="default_white" bgcolor="#44abd4"><b>Password:</b></td>
          <td width="60%" bgcolor="#44abd4">
            <input accessKey="p" class="default" type="password" name="password" size="20" maxlength="32">
            <img id="error_icon_passwd" style="visibility: hidden;" src="<%=request.getContextPath()%>/images/icons/error.gif" alt="error condition detected" title="error condition detected" width="1" height="1">          </td>
        </tr>
        <tr align="center">
          <td colspan="2" bgcolor="#44abd4">
            <input type="submit" name="Submit" value="Login" class="button">
          </td>
        </tr>
        <tr align="center">
          <td colspan="2" class="default_white" bgcolor="#44abd4">
            <a class="white_link" href="forgot.action">I Forgot My Password</a>&nbsp;&nbsp;
            <a class="white_link" href="newUser.action">Signup for an Account</a>          </td>
        </tr>
        <tr>
          <td bgcolor="#44abd4" colspan="2" align="center" class="default_white">
            <b>* Requires support for cookies and javascript in your browser</b>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</div>
</form>

