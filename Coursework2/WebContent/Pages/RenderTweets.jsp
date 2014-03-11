<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="com.coursework2.alistair.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:useBean id="Log" class="com.coursework2.alistair.Beans.UserLogIn" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tweets</title>
</head>
<body>

<%
out.println("Here are the Tweets<br />");
    
System.out.println("In render");
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
 %>
<p>No Tweet found</p>
<%
}else{
Iterator<TweetStore> iterator;


iterator = lTweet.iterator();
while (iterator.hasNext()){
TweetStore ts = (TweetStore)iterator.next();

%>
<a href="/ac32007examples/Tweet/<%=ts.getUser() %>" ><%=ts.getTweet() %></a><br/><%
}
}
%>
<input type="button" value="Home" name="Home" onclick="openPage('http://localhost:8080/Coursework2/index.jsp')"/>
<br><br>
<input type="button" value="Log Out" name="Logout" onclick="openPage('http://localhost:8080/Coursework2/Pages/LogOut.jsp')"/>

<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
 </script>

</body>
</html>