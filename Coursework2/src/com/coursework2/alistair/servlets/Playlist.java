package com.coursework2.alistair.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Cluster;

import com.coursework2.alistair.lib.*;
import com.coursework2.alistair.models.*;

@WebServlet(urlPatterns = { "/Playlist", "/Playlist/*" })
public class Playlist extends HttpServlet {
private static final long serialVersionUID = 1L;
    private Cluster cluster;
    /**
* @see HttpServlet#HttpServlet()
*/
    public Playlist() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
// TODO Auto-generated method stub
cluster = CassandraHosts.getCluster();
}
    
/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Playlists UserPlaylists = new Playlists();
UserPlaylists.setCluster(cluster);
UserPlaylists.CreateDatabase();
boolean DatabaseExists = true;
request.setAttribute("Database", DatabaseExists);
RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
rd.forward(request, response);
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
}

}