<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.js"></script>
</head>
<body>
<%
if(Log.isLoggedIn()){
    out.println("Hello please select the action you want to carry out <br><br>");%>
<form action="#">
<select name="URL" onchange="window.location.href= this.form.URL.options[this.form.URL.selectedIndex].value">
<option> </option>
<option value="http://localhost:8080/Coursework2/Pages/CreatePlaylist.jsp">Create a Playlist</option>
<option value="http://localhost:8080/Coursework2/Pages/ViewPlaylist.jsp">View the contents of your Playlists</option>
<option value="http://localhost:8080/Coursework2/Pages/DeletePlaylist.jsp">Delete a Playlist</option>
<option value="http://localhost:8080/Coursework2/Pages/RearangePlaylist.jsp">Rearange the song order of a Playlist</option>
<option value="http://localhost:8080/Coursework2/Pages/TrackSearch.jsp">Search Spotify for songs</option>
<option value="http://localhost:8080/Coursework2/Pages/ArtistSearch.jsp">Search Spotify for artists</option>
<option value="http://localhost:8080/Coursework2/Pages/AlbumSearch.jsp">Search Spotify for albums</option>
</select>
<br><br>
</form>
<input type="button" value="Log Out" name="Logout" onclick="openPage('http://localhost:8080/Coursework2/Pages/LogOut.jsp')"/><%}

else{
out.println("Please log in or create an account<br />");%>
<br>
    <input type="button" value="Sign Up" name="SignUp" onclick="openPage('http://localhost:8080/Coursework2/Pages/CreateUser.jsp')"/>
    <br><br>
    <input type="button" value="Log In" name="Login" onclick="openPage('http://localhost:8080/Coursework2/Pages/LogIn.jsp')"/><%}%>

<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
 </script>

</body>
</html>