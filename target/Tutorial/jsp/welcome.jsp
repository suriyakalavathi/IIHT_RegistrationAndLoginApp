<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Welcome</title>
</head>
<body>
<table align="center">
  <tr>
    <td><a href="admin">Admin</a></td>
    <td><a href="update">Update</a></td>
    <td><a href="logout">Logout</a></td>
  </tr>
</table>
<table>
  <tr>
    <td>Welcome ${name}!</td>
  </tr>
  <tr>
    <td>TUTORIAL Main Page.</td>
  </tr>
  <tr>
  </tr>
</table>
<table align="center">
  <tr>
    <td style="font-style: italic; color: red;">${message}</td>
  </tr>
</table>
</body>
</html>