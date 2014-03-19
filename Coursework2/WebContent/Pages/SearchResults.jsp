<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.coursework2.alistair.stores.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.code.jspot.Results" %>
<%@ page import="com.google.code.jspot.Track" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Data" class="com.coursework2.alistair.Beans.Data" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
Object results = request.getAttribute("Search");
if (results != null){
	for (Track track : ((Results<Track>) results).getItems()) {
       out.println("Artist = " + track.getArtistName() + " // Album = " + track.getAlbum().getName() + " // Song Name = " + track.getName() + " // TrackID = " + track.getId() + "<br>");
      }
}
%>
<br>
<input type="button" value="Home" name="Home" onclick="openPage('http://localhost:8080/Coursework2/index.jsp')"/>
<br><br>
<input type="button" value="Log Out" name="Logout" onclick="openPage('http://localhost:8080/Coursework2/Pages/LogOut.jsp')"/>

<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
 </script>

</body>
</html>