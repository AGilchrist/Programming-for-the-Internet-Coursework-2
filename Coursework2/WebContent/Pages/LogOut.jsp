<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage = "errorPage.jsp" %>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
        if(Log.isLoggedIn() == true){
        Log.LogOut();
        out.println("You have been logged out<br />");}
        %>
        <br>
        <input type="button" value="Home" name="Home" onclick="openPage('http://localhost:8080/Coursework2/index.jsp')"/>
        
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
 </script>        

    </body>
</html>