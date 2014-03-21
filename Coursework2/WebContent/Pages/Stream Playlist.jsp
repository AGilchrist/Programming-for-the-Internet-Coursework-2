<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.datastax.driver.core.ResultSet" %>
<%@ page import="com.datastax.driver.core.Row" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:useBean id="Playlist" class="com.coursework2.alistair.Beans.CreatePlaylist" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stream a Playlist</title>
</head>
<body>

<%
String playlist;
if(Log.isLoggedIn()){
    out.println("Hello user " + Log.getUsername() + " <br />");%>
<h3>Please select a playlist to stream and hit the button</h3>

<form action="Stream Playlist.jsp">
<select name="Playlist">

<%
Playlist.setUsername(Log.getUsername());
ResultSet rs = Playlist.getPlaylists();
if(rs != null){
	for (Row row : rs) {
		%>
			<option value="<%=row.getString("PlaylistName")%>"><%=row.getString("PlaylistName")%></option>
		<%
	}
	%>
	</select>
	<br><br>
	<select name = "type">
	<option> </option>
	<option value = "Playlist">Playlist</option>
	<option value = "Track">Track</option>
	</select>
	<br><br>
	<input type="submit" value="Select Playlist">
	<br><br>
	</form>
	
	<%
	if(request.getParameter("Playlist") != null){
		Playlist.setPlaylistName(request.getParameter("Playlist"));
		if(request.getParameter("type").equals("Playlist")){
			out.println("<a href=\"http://localhost:8080/Coursework2/StreamSpotify/Playlist/" + Log.getUsername() + "/" + request.getParameter("Playlist") + "/" + "\">Stream Playlist</a>"); 
		}
		else if(request.getParameter("type").equals("Track")){
			%>
			<form action="Stream Playlist.jsp">
			<select name="Song">

			<%
			rs = Playlist.getSongs(request.getParameter("Playlist"));
			for (Row row : rs) {
				%>
					<option value="<%=row.getString("TrackTitle")%>"><%=row.getString("TrackTitle")%></option>
				<%
			}
			%>
			</select>
			<br><br>
			<input type="submit" value="Select Song">
			<br><br>
			</form>
			<%
		}
		else{
			System.out.println("Please indicate whether you would like to stream the playlist or the a single song");
		}
	}else{
		if(request.getParameter("Song") == null){
			out.println("You must select a song to be ablt to Stream it");
			}else{
				playlist = Playlist.getPlaylistName();
				out.println("<a href=\"http://localhost:8080/Coursework2/StreamSpotify/Track/" + Log.getUsername() + "/" + playlist + "/" + request.getParameter("Song") + "/" + "\">Stream Song</a>"); 
			}
	}
}else{
	out.println("You must of created at least one playlist to be able to Stream it");
}
}else{
	%>
	<h1>You must be logged into an account to access this feature</h1>
	<%
}
%>

<br><br>
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