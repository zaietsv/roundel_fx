<%@page import="zaietsv.complextask.mvc.instance.User"%>
<%@page import="java.sql.SQLException"%>
<%@page import="zaietsv.complextask.mvc.instance.Instance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="zaietsv.complextask.mvc.connect.ConnectorTool"%>
<%@page import="zaietsv.complextask.mvc.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator's page</title>
</head>
<body>
Administrator's page
action=<%=request.getParameter("action") %>
id=<%=request.getParameter("id") %>
<%String action = request.getParameter("action");%>
<%long id = Long.parseLong(request.getParameter("id")); %>

<% try (ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");) { %>
<% UserDAO udao = new UserDAO(ct.connect()); %>			
<% ArrayList<Instance> users = udao.readAll(); %>

<table border="1">
<thead></thead>
<tbody><% for (Instance user : users) { %>
<tr>
<% if (action.equals("edit") && id == ((User)user).getId()) { %>
<form name="user_save_<%=((User)user).getId() %>" action="admin.jsp?action=save" method="post">
<table><tr><td><%=((User)user).getId() %></td>
<td><input type="text" name="login" value="<%=((User)user).getLogin() %>"></td>
<td><input type="text" name="password" value="<%=((User)user).getPassword() %>"></td>
<td><input type="text" name="email" value="<%=((User)user).getEmail() %>"></td>
<td><input type="text" name="reg_date" value="<%=((User)user).getReg_date() %>"></td>
<td><input type="submit" value="Save"><input type="submit" value="Save"><input type="reset" value="Cancel"></td></tr>
</table>
</form>

<% } else {%>
<td><%=((User)user).getId() %></td>
<td><%=((User)user).getLogin() %></td>
<td><%=((User)user).getPassword() %></td>
<td><%=((User)user).getEmail() %></td>
<td ><%=((User)user).getReg_date() %></td>
<td align="center" scope="col">
<a href="admin.jsp?action=details&id=<%=((User)user).getId() %>">Details</a>
<a href="admin.jsp?action=edit&id=<%=((User)user).getId() %>">Edit</a>
<div><form name="user_details_<%=((User)user).getId() %>" action="admin.jsp?action=details" method="post">
<input type="hidden" name="id" value="<%=((User)user).getId() %>"><input type="submit" value="Details">
</form></div>
<div><form name="user_edit_<%=((User)user).getId() %>" action="admin.jsp" method="get">
<input type="hidden" name="action" value="edit">
<input type="hidden" name="id" value="<%=((User)user).getId() %>"><input type="submit" value="Edit">
</form></div></td><% } %>
</tr><% } %>
<%} catch (SQLException e) { }%>
</tbody>
</table>

</body>
</html>