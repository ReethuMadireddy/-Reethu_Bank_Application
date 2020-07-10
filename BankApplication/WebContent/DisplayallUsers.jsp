<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
    table, tr, td
                    {
                         border: 1px solid black;
                         width : 40%;
                        text-align: center;
                    }
​    
    </style>   

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="s" items="${list}">
                        <table>
                            <tr>
                                <td>
                                    <h5><c:out value="${s.getId()}"></c:out></h5>
                                </td>
                                <td>
                                    <h5><c:out value="${s.getPassword()}"></c:out></h5>
                                </td>
                                 <td>
                                    <h5><c:out value="${s.getName()}"></c:out></h5>
                                </td> 
                                 <td>
                                    <h5><c:out value="${s.getAccno()}"></c:out></h5>
                                </td>   
                                 <td>
                                    <h5><c:out value="${s.getBalance()}"></c:out></h5>
                                </td>   
                            </tr>
                        </table>
                    </c:forEach>       



</body>
</html>