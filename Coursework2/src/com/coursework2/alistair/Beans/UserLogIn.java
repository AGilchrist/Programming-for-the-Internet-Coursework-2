package com.coursework2.alistair.Beans;

public class UserLogIn {
    String username = "User1";
    String password = "Music";
    boolean loggedIn = false;
   
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
  
   public void setUsername(String user)
   {
     username = user;
   }
  
    public  String getPassword()
    {
      return password;
    }
  
   public void setPassword(String pass)
   {
     password = pass;
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
