<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.coursework2.alistair.stores.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.code.jspot.Results" %>
<%@ page import="com.google.code.jspot.Track" %>
<%@ page import="com.datastax.driver.core.ResultSet" %>
<%@ page import="com.datastax.driver.core.Row" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:useBean id="Data" class="com.coursework2.alistair.Beans.Data" scope="session" />
<jsp:useBean id="Playlist" class="com.coursework2.alistair.Beans.CreatePlaylist" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action="SearchResults.jsp">
<select name="Playlist">

<%
Playlist.setUsername(Log.getUsername());
ResultSet rs = Playlist.getPlaylists();
for (Row row : rs) {
	%>
		<option value="<%=row.getString("PlaylistName")%>"><%=row.getString("PlaylistName")%></option>
	<%
}
%>
</select>
<br>
<input type="submit" value="Submit">
<br><br>
</form>

<%
Object results = request.getAttribute("Search");
int SongCount;
Playlist.setUsername(Log.getUsername());
Playlist.setPlaylistName(request.getParameter("Playlist"));
if(request.getParameter("Playlist") == null){
	if(results == null){
		results = Playlist.getResults();
	}
	if (results != null){
		Playlist.setResults(results);
		for (Track track : ((Results<Track>) results).getItems()) {
		       out.println("Song Name = " + track.getName() + " // Artist = " + track.getArtistName() + " // Album = " + track.getAlbum().getName());
		       %>
		       <br>
		       <%
		}
	}
}
else {
	SongCount = (Playlist.getSongCount())+1;
	if(results == null){
		results = Playlist.getResults();
	}
	if (results != null){
	for (Track track : ((Results<Track>) results).getItems()) {
	       out.println("Song Name = " + track.getName().replaceAll("/", "") + " // Artist = " + track.getArtistName() + " // Album = " + track.getAlbum().getName());
	       out.println("<a href=\"http://localhost:8080/Coursework2/AddSong/" + Log.getUsername() + "/" + Playlist.getPlaylistName() + "/" + SongCount + "/" + track.getId() + "/" + track.getName().replaceAll("/", ",") + "/" + track.getArtistName() + "/" + track.getAlbum().getName() + "/" + "\">Search</a>"); 
	       %><br><%
	       }
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