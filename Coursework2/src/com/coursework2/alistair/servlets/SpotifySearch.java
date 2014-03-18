package com.coursework2.alistair.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.code.jspot.Results;
import com.google.code.jspot.Spotify;
import com.google.code.jspot.Track;
import com.coursework2.alistair.Beans.*;
import com.coursework2.alistair.lib.*;
import com.coursework2.alistair.models.*;


/**
* Servlet implementation class Math
*/
@WebServlet(
urlPatterns = {
"/SpotifySearch/*"
},
initParams = {
@WebInitParam(name = "data-source", value = "jdbc/Faultdb")
})
public class SpotifySearch extends HttpServlet {
private static final long serialVersionUID = 1L;
private HashMap CommandsMap = new HashMap();
private DataSource _ds = null;

/**
* @see HttpServlet#HttpServlet()
*/
    public SpotifySearch() {
        super();
        // TODO Auto-generated constructor stub
   
        // TODO Auto-generated constructor stub
        CommandsMap.put("Track",1);
        CommandsMap.put("Artist",2);
        CommandsMap.put("Album",3);
}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
}
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PreparedStatement pstmt = null;
//URL url = new URL ("https://ws.spotify.com/search/1/track?q=kaizers+orchestra");

try {
Convertors ut = new Convertors();
String args[]=ut.SplitRequestPath(request);
response.setContentType("text/html");
PrintWriter out=null;
out =	new PrintWriter(response.getOutputStream());

int command;
String search;
try{
command =(Integer)CommandsMap.get(args[2]);
}catch(Exception et){
error("Wrong Command",out);
return;
}
try{
	search = args[3];
}catch(Exception et){
error("Bad input",out);
return;	
}
switch (command){
case 1:	{
	Spotify spotify = new Spotify();
    Object results = spotify.searchTrack("track:"+search);
    for (Track track : ((Results<Track>) results).getItems()) {
         System.out.printf("Song Name = %s // Artist = %s // Album = %s\n",
              track.getName(), track.getArtistName(), track.getAlbum().getName());
    }
	}
break;
case 2: {
	Spotify spotify = new Spotify();
    Object results = spotify.searchTrack("artist:"+search);
    for (Track track : ((Results<Track>) results).getItems()) {
         System.out.printf("Artist = %s // Album = %s // Song Name = %s\n",
                track.getArtistName(), track.getAlbum().getName(), track.getName());
       }
	}
break;
case 3: {
	Spotify spotify = new Spotify();
    Object results = spotify.searchTrack("album:"+search);
    for (Track track : ((Results<Track>) results).getItems()) {
         System.out.printf("Album = %s // Artist = %s // Song Name = %s\n",
                 track.getAlbum().getName(), track.getArtistName(), track.getName());
       }
	}
break;
default: error("No such search criteria",out);
}
}
catch (Exception et) {
return;
}
response.sendRedirect("http://localhost:8080/Coursework2/index.jsp");
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