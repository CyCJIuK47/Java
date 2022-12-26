<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
    <table>
      <tr>
        <th>Name</th>
        <th>Login</th>
      </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.getName()}</td>
                <td>${user.getLogin()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>