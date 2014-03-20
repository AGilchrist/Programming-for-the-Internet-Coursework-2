package com.coursework2.alistair.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.jspot.Spotify;
import com.coursework2.alistair.Beans.CreatePlaylist;
import com.coursework2.alistair.lib.*;


/**
* Servlet implementation class Math
*/
@WebServlet(
urlPatterns = {
"/AddSong/*"
},
initParams = {
@WebInitParam(name = "data-source", value = "jdbc/Faultdb")
})
public class AddSong extends HttpServlet {
private static final long serialVersionUID = 1L;
private HashMap CommandsMap = new HashMap();

/**
* @see HttpServlet#HttpServlet()
*/
    public AddSong() {
        super();
}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
}
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
CreatePlaylist play = new CreatePlaylist();
try {
Convertors ut = new Convertors();
String args[]=ut.SplitRequestPath(request);
response.setContentType("text/html");
PrintWriter out=null;

String username, playlistname, id, track, artist, album;
int playlistpos;
try{
}catch(Exception et){
error("Wrong Command",out);
return;
}
try{
}catch(Exception et){
error("Bad input",out);
return;	
}
username = args[2];
playlistname = args[3];
playlistpos = Integer.parseInt(args[4]);
id = args[5];
track = args[6];
artist = args[7];
album = args[8];
	
play.setUsername(username);
play.setPlaylistName(playlistname);
play.setPlaylistPos(playlistpos);
play.setSongID(id);
play.setTitle(track);
play.setArtist(artist);
play.setAlbum(album);
play.AddSong();


}
catch (Exception et) {
return;
}
response.sendRedirect("http://localhost:8080/Coursework2/Pages/SearchResults.jsp");
}

private void error(String mess, PrintWriter out){
out.println("<h1>You have a an error in your input</h1>");
out.println("<h2>"+mess+"</h2>");
out.close();
return;
}


/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
}

}