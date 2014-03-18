<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:setProperty name="Log" property="*" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a fault</title>
</head>
<body>
<%
if(Log.isLoggedIn()){
    out.println("Hello user " + Log.getUsername() + " <br />");%>
<h3></h3>
<p></p>

<form name="SearchTrack" method="post" action="TrackSearch.jsp">
Song Title to search for
<input type="text" name="myTrack" id="myTrack" value="">
<br><br>
<input type="Submit" value="Update details for Servlet">
<br><br>
</form>
<%  
if(request.getParameter("myTrack") != null){
	out.println("Please click the link below to begin searching <br>");
	out.println("<a href=\"http://localhost:8080/Coursework2/SpotifySearch/Track/" + request.getParameter("myTrack") + "\">Search</a>"); 
}else{out.println("You must fill in a field to be able to search<br>");}
%>   
<br><br>
<input type=button value="Back to Home Page" onclick="openPage('http://localhost:8080/Coursework2/index.jsp')">
<br><br>
<input type="button" value="Log Out" name="Logout" onclick="openPage('http://localhost:8080/Coursework2/Pages/LogOut.jsp')"/><%}
else{
	String redirectURL = "http://localhost:8080/Coursework2/index.jsp";
    response.sendRedirect(redirectURL);}
%>
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script>
</body>
</html>