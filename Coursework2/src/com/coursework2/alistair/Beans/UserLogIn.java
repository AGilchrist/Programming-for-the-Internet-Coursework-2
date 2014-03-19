package com.coursework2.alistair.Beans;

import java.io.PrintWriter;

import com.coursework2.alistair.models.Playlists;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class UserLogIn {
    boolean loggedIn = false;
    String username;
    PreparedStatement statement;
    Playlists db = new Playlists();
    Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    PrintWriter out;
    boolean nameexists;
   
    public UserLogIn()
    {
    	
    }
   
   public boolean isLoggedIn()
   {
       return loggedIn;
   }
  
   public void CreateAccount(String Username, String Password)
   {
	   db.CreateDatabase();
	   Session session = cluster.connect("UserDetails");
	   statement = session.prepare("SELECT * FROM UserDetails.Users");
	   BoundStatement boundStatement = new BoundStatement(statement);
	   ResultSet rs = session.execute(boundStatement);
	   for (Row row : rs) {
			if(Username.equals(row.getString("UserName"))){
				nameexists = true;
	   		}
	   }
	   if(nameexists != true){
		   statement = session.prepare("INSERT INTO UserDetails.Users (UserName, Password) VALUES(?, ?)");
		   session.execute(statement.bind(Username, Password));
	   }
	   session.close();
   }
  
   public void LogIn(String Username, String Password)
   {
		Session session = cluster.connect("UserDetails");
		PreparedStatement statement = session.prepare("SELECT * FROM UserDetails.Users");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		session.close();
		for (Row row : rs) {
			if(Username.equals(row.getString("UserName"))){
				if(Password.equals(row.getString("Password"))){
					loggedIn = true;
					username = Username;
				}
			}
		}
		return;
   }

   public void LogOut()
   {
		if(loggedIn == true){
			loggedIn = false;
			username = null;
		}
		else
		return;
   }
   
	public String getUsername()
	{
		return username;
	}
	
	public void setNameExists(boolean state)
	{
		nameexists = state;
	}
	
	public boolean getNameExists()
	{
		return nameexists;
	}

}