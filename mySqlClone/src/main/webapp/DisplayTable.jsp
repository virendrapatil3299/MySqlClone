<%@page import="java.util.ArrayList"%>
<%@page import="Model.Table"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<style type="text/css">
body{
display:flex; 
justify-content:center; 
align-content:center;
background-color:white; 
color:black;
}
</style>
</head>
<body style="margin-bottom: 20px;">
<% Object list =request.getAttribute("DISPLAYLIST"); %>
<% ArrayList<Table> t1 = (ArrayList) list; %>
<div style="padding-top: 50px" >
<table class="table table-bordered " style="width: 8rem; padding-top: 40px" >
<thead>
<tr>
<% for(Table u:t1){ %>
<th><h4><%=u.getColumnName()%></h4></th>
<%} %>
</tr>
</thead>
<tbody>
<tr>
<% for(Table u:t1){ %>
<td><h4><%=u.getDefaults()%></h4></td>
<%} %>
</tr>
</tbody>

</table>
</div>
</body>
</html>