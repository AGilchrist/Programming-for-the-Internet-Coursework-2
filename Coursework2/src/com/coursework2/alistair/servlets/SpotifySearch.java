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
import javax.sql.DataSource;

import com.google.code.jspot.Spotify;
import com.coursework2.alistair.Beans.Data;
import com.coursework2.alistair.lib.*;


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
Data data = new Data();
try {
Convertors ut = new Convertors();
String args[]=ut.SplitRequestPath(request);
response.setContentType("text/html");
PrintWriter out=null;
//out =	new PrintWriter(response.getOutputStream());
Spotify spotify = new Spotify();

int command;
String searchTrack, searchArtist, searchAlbum;
try{
command =(Integer)CommandsMap.get(args[2]);
}catch(Exception et){
error("Wrong Command",out);
return;
}
try{
	searchTrack = data.getTrack();
	searchArtist = data.getArtist();
	searchAlbum = data.getAlbum();
}catch(Exception et){
error("Bad input",out);
return;	
}
switch (command){
case 1:	{
	    Object results = spotify.searchTrack("track:"+searchTrack);
	    request.setAttribute("Search", results); //Set a bean with the list in it
	    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SearchResults.jsp");
	    rd.forward(request, response);
	    data.reset();
	}
break;
case 2: {
	Object results = spotify.searchTrack("artist:"+searchArtist);
	request.setAttribute("Search", results); //Set a bean with the list in it
    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SearchResults.jsp");
    rd.forward(request, response);
    data.reset();
	}
break;
case 3: {
    Object results = spotify.searchTrack("album:"+searchAlbum);
    request.setAttribute("Search", results); //Set a bean with the list in it
    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SearchResults.jsp");
    rd.forward(request, response);
    data.reset();
	}
break;
default: response.sendRedirect("http://localhost:8080/Coursework2/Pages/SearchResults.jsp");
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