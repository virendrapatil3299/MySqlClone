<%@page import="java.util.ArrayList"%>
<%@page import="Model.Table"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Base Table</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<style type="text/css">
a{
color:black;
}

body{

display:flex; 
justify-content:center; 
align-content:center;
 
color:black;
}
</style>
</head>
<body >
<% Object list =request.getAttribute("LIST"); %>
<% ArrayList<String> l=(ArrayList) list; %>
<div style="padding-top: 50px" >
<table class="table table-bordered " style="width: 8rem;">
<thead>
<tr>
<% for(String u:l){ %>
<td><h4><a href="table?tname=<%=u%>"><%=u %></a></h4></td>
<%} %>
</tr>
</thead>

</table>
</div>
</body>
</html>