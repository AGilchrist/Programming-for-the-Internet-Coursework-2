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

import com.coursework2.alistair.Beans.CreatePlaylist;
import com.coursework2.alistair.lib.*;


/**
* Servlet implementation class Math
*/
@WebServlet(
urlPatterns = {
"/StreamSpotify/*"
},
initParams = {
@WebInitParam(name = "data-source", value = "jdbc/Faultdb")
})
public class StreamSpotify extends HttpServlet {
private static final long serialVersionUID = 1L;
private HashMap CommandsMap = new HashMap();

/**
* @see HttpServlet#HttpServlet()
*/
    public StreamSpotify() {
        super();
        // TODO Auto-generated constructor stub
   
        // TODO Auto-generated constructor stub
        CommandsMap.put("Playlist",1);
        CommandsMap.put("Track",2);
}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
}
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
CreatePlaylist Playlist = new CreatePlaylist();
try {
Convertors ut = new Convertors();
String args[]=ut.SplitRequestPath(request);
response.setContentType("text/html");
PrintWriter out=null;

int command;
String playlistname, trackname;

try{
command =(Integer)CommandsMap.get(args[2]);
}catch(Exception et){
error("Wrong Command",out);
return;
}
try{
}catch(Exception et){
error("Bad input",out);
return;	
}
playlistname = args[3];
switch (command){
case 1:	{
	//Start streaming of playlist
	request.setAttribute("PlaylistStream", playlistname); //Set a bean with the list in it
    RequestDispatcher rd = request.getRequestDispatcher("/Pages/Stream.jsp");
    rd.forward(request, response);
	}
break;
case 2: {
	//Start streaming of track
	trackname = args[4];
	request.setAttribute("PlaylistStream", playlistname); //Set a bean with the list in it
	request.setAttribute("TrackStream", trackname); //Set a bean with the list in it
    RequestDispatcher rd = request.getRequestDispatcher("/Pages/Stream.jsp");
    rd.forward(request, response);
	}
break;
default: response.sendRedirect("http://localhost:8080/Coursework2/index.jsp");
}
}
catch (Exception et) {
return;
}
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