<%--
  Created by IntelliJ IDEA.
  User: imran
  Date: 1/1/18
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Role</title>

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>

</head>
<body>
<h2>Roles</h2>
<table width="100%" border="1 px solid">
    <thead>
     <th>Name</th>
     <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${roles}" var="role">
        <tr>
            <td><c:out value="${role.name}"/></td>
            <td>
                <a href="roles?edit=<c:out value='${role.id}' />">Edit</a> |
                <a href="settings-permission?roleId=<c:out value='${role.id}' />">Add Permission</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="/roles" method="post">
    <div>
        <input type="text" name="role">
        <button type="submit">Add Roles</button>
    </div>

</form>
</body>
</html>

<%--
<select name="role">
    <option>Please select</option>
    <c:forEach var="role" items="${roles}">
        <option value="${role.key}">${role.value}</option>
    </c:forEach>
</select>--%>
