<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.datastax.driver.core.ResultSet" %>
<%@ page import="com.datastax.driver.core.Row" %>
<%@ page import="Spotify.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:useBean id="Playlist" class="com.coursework2.alistair.Beans.CreatePlaylist" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stream Music</title>
</head>
<body>

<%
if(Log.isLoggedIn()){
    out.println("Hello user " + Log.getUsername() + " <br />");%>
<h3>Please select a playlist and hit the button, then select a song in that playlist and enter a new position, must be a number</h3>

<%
Object playlistname, trackname;
Session music = new Session();
ResultSet rs;
String Trackid;
playlistname = request.getAttribute("PlaylistStream");
trackname = request.getAttribute("TrackStream");
if(playlistname != null)
{
	String PlaylistName = String.valueOf(playlistname);
	if(trackname == null)
	{
		System.out.println("Playlist = " + playlistname);
		rs = Playlist.getFullPlaylist(PlaylistName);
		for (Row row : rs)
		{
			if(row.getInt("PlaylistPos")>0){
				Trackid = row.getString("Trackid");
				%>
				<iframe src=<%="https://embed.spotify.com/?url=" + Trackid%> width="300" height="380" frameborder="0"></iframe>
				<%
			}
		}
	}
	else
	{
		String TrackName = String.valueOf(trackname);
		rs = Playlist.getSongInfo(PlaylistName, TrackName);
		for(Row row : rs){
			Trackid = row.getString("Trackid");
			%>
			<iframe src=<%="https://embed.spotify.com/?url=" + Trackid%> width="300" height="380" frameborder="0"></iframe>
			<%
		}
	}
}else{
	out.println("No music streaming");
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
 
 function set(Trackid)
 {
	 
 }
 </script>

</body>
</html>