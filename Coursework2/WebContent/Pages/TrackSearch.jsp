<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:useBean id="Data" class="com.coursework2.alistair.Beans.Data" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Songs</title>
</head>
<body>
<%
if(Log.isLoggedIn()){
    out.println("Hello user " + Log.getUsername() + " <br />");%>
<h3>Please enter a song name in the search box and hit search to see the best matching results</h3>
<p></p>

<form name="SearchTrack" method="post" action="TrackSearch.jsp">
Song Title to search for:
<input type="text" name="myTrack" id="myTrack" value="">
<br><br>
<input type="Submit" value="Search!"> 
<br><br>
</form>
<%  
if(request.getParameter("myTrack") != null){
	response.sendRedirect("http://localhost:8080/Coursework2/SpotifySearch/Track/"+ request.getParameter("myTrack"));
}
else{
	out.println("Please Provide an Song Name to search for");
	}
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