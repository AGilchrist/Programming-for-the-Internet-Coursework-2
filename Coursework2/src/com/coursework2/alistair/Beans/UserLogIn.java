package com.coursework2.alistair.Beans;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class UserLogIn {
    String username = "User1";
    String password = "Music";
    boolean loggedIn = false;
    Cluster cluster;
   
    public UserLogIn()
    {
       
    }
   
   public boolean isLoggedIn()
   {
       return loggedIn;
   }
  
  
   public String getUsername()
    {
      return username;
    }
  
   public void setUsername(String Username)
   {
     username = Username;
   }
  
    public  String getPassword()
    {
      return password;
    }
  
   public void setPassword(String Password)
   {
     password = Password;
   }
   
   public void CreateAccount(String User, String Password)
   {
	   Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	   Session session = cluster.connect("UserDetails");
	   UUID PrimaryKey = UUID.randomUUID();
	   PreparedStatement statement = session.prepare("INSERT INTO UserDetails.Users (id, name, password) VALUES(?, ?, ?)");
	   session.execute(statement.bind(PrimaryKey, User, Password));
	   session.close();
	   return;
   }
  
   public void LogIn(String Username, String Password)
   {
	   if(Username == null)
		  return;
	   if(Password == null)
		   return;
	   else if(Username.equals(username)){
		   if(Password.equals(password))
			   loggedIn = true;}
   }
   
   public void LogOut()
   {
	   if(loggedIn == true)
	  loggedIn = false;
	   else
		   return;
   }
}
