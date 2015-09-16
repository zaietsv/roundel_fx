<%@page import="zaietsv.complextask.mvc.install.MusicUsersSchemaInstaller"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type;" content="text/html; charset=UTF-8;">
<title>Install A Music Users Database</title>
</head>
<body>
Schema: '<%=MusicUsersSchemaInstaller.dbName%>'<br>
<%if (MusicUsersSchemaInstaller.connect()){%>
<textarea rows="8" cols="120" readonly="readonly" title="databaseInfo"><%=MusicUsersSchemaInstaller.databaseInfo()%></textarea>
<br>Schema: '<%=MusicUsersSchemaInstaller.dbName%>'
<form action="InstallServlet" method="get">
<input type="submit" name="action" value="<%if (MusicUsersSchemaInstaller.isInstalled()) {%>uninstall<%} else {%>install<%}%>">
</form>
<%} else {%>Unable to connect to database<%}%>
</body>
</html>