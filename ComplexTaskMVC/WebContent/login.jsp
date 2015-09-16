<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="Login?role=user" name="pleaseLogin" method="post" title="Please login here!" >
<b>Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><input type="text" name="login" align="middle" value="<%=request.getParameter("login") %>">
<%if (request.getParameter("login").isEmpty()) {%>
<font color="red">Please, enter login!</font>
<% } %><br>
<b>Password:&nbsp;</b><input type="password" name="password">
<%if (request.getParameter("password").isEmpty()) {%>
<font color="red">Please, enter password!</font>
<% } %><br>
<select name="rules">
<option value="user" selected="selected">User</option>
<option value="mandator">Mandator</option>
<option  value="admin">Administrator</option>
</select>
<input type="submit"><input type="reset"><p>
</form>
</body>
</html>