package com.coursework2.alistair.Beans;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class UserLogIn {
    boolean loggedIn = false;
    UUID UserID;
    String username;
    Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
   
    public UserLogIn()
    {
       
    }
   
   public boolean isLoggedIn()
   {
       return loggedIn;
   }
  
   public void CreateAccount(String User, String Password)
   {
	   Session session = cluster.connect("UserDetails");
	   UUID PrimaryKey = UUID.randomUUID();
	   PreparedStatement statement = session.prepare("INSERT INTO UserDetails.Users (id, Name, Password) VALUES(?, ?, ?)");
	   session.execute(statement.bind(PrimaryKey, User, Password));
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
		   if(Username.equals(row.getString("Name"))){
					   if(Password.equals(row.getString("Password")))
						   loggedIn = true;
					   		UserID = row.getUUID("id");
					   		username = Username;
				   }
	   }
	   return;
   }

   public void LogOut()
   {
	   if(loggedIn == true)
	  loggedIn = false;
	   else
		   return;
   }
}
