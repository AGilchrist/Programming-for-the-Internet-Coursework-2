<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a User</title>
</head>
<body>
<h1>Please enter a username and password</h1>
<form name="CreateUser" method="post" action="CreateUser.jsp">
Username:
<input type="text" name="myUsername" id="myUsername" value="">
<br/>
<br/>
Password:
<input type="password" name="myPassword" id="myPassword" value="">
<br/>
<br/>
<input type="Submit" value="Create Account">
</form>

<%
Log.CreateUserTable();
String user = request.getParameter("myUsername");
String password = request.getParameter("myPassword");
if(request.getParameter("myUsername") != null){
	if(request.getParameter("myPassword") != null){
Log.CreateAccount(request.getParameter("myUsername"), request.getParameter("myPassword"));
response.sendRedirect("http://localhost:8080/Coursework2/index.jsp");
	}
}
%>
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
 
</script>
</body>
</html>