<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
    table,tr,td{
    border:1px solid black;
    width:40%;
    text-align:center;

}
</style>
<body>
<c:forEach var="s" items="${list}">
<table>
<tr>
<td>
<c:out value="{s.gettransactionType()}"></c:out>
</td>
<td>
<c:out value="{s.getamount()}"></c:out>
</td>
</tr>
</table>
</c:forEach>

</body>
</html>