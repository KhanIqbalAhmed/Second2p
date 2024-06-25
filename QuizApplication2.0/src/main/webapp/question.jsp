 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Application</title>
</head>
<body>
    <h1>${question.question}</h1>
    <form action="quiz" method="post">
        <c:forEach var="option" items="${question.options}" varStatus="status">
            <input type="radio" name="answer" value="${status.index}" required> ${option}<br>
        </c:forEach>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
 