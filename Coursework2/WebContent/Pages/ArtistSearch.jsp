<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:useBean id="Data" class="com.coursework2.alistair.Beans.Data" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Artists</title>
</head>
<body>
<%
if(Log.isLoggedIn()){
    out.println("Hello user " + Log.getUsername() + " <br />");%>
<h3>Please enter the name of an artist in the search box and hit search</h3>
<p></p>

<form name="SearchArtist" method="post" action="ArtistSearch.jsp">
Artist to search for
<input type="text" name="myArtist" id="myArtist" value="">
<br><br>
<input type="Submit" value="Search!">
<br><br>
</form>

<%  
if(request.getParameter("myArtist") != null){
	response.sendRedirect("http://localhost:8080/Coursework2/SpotifySearch/Artist/"+ request.getParameter("myArtist"));
}
else{out.println("You must provide an Artist Name to search by<br>");}
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