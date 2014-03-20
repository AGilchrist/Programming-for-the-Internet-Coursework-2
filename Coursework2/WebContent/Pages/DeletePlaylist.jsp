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
<title>Insert title here</title>
</head>
<body>

<form action="DeletePlaylist.jsp">
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
<br><br>
<input type="submit" value="Delete">
<br><br>
</form>

<%
if(request.getParameter("Playlist") == null){
	out.println("You must select a playlist to view its contents");
}else{
	Playlist.deletePlaylist(request.getParameter("Playlist"));
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