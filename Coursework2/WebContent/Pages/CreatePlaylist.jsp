<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<jsp:useBean id="Playlist" class="com.coursework2.alistair.Beans.CreatePlaylist" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form name="CreatePlaylist" method="post" action="CreatePlaylist.jsp">
Name for Playlist:
<input type="text" name="myPlaylistName" id="myPlaylistName" value="">
<br><br>
<input type="Submit" value="Create Playlist!">
<br><br>
</form>

<%  
if(request.getParameter("myPlaylistName") != null){
 	Playlist.setUsername(Log.getUsername());
 	Playlist.setPlaylistName(request.getParameter("myPlaylistName"));
 	Playlist.CreatePlaylist();
	if(Playlist.getPlaylistExists() == false){
	response.sendRedirect("http://localhost:8080/Coursework2/index.jsp");
	}
	else {
		out.println("<br> Unfortunately a playlist with that name already exists");
		Playlist.setPlaylistExists(false);
	}
}
else{
	out.println("Please Provide an Name for your Playlist");
}
%> 

</body>
</html>