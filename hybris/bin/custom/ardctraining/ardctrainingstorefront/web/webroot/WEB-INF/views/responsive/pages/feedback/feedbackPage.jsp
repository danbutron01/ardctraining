
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Feedback</title>
</head>
<body>
  <div>
    <form:form action="/ardctrainingstorefront/feedback/save" method="POST" modelAttribute="messageForm" id="messageForm">



            <td><form:label path="subject">Subject</form:label></td>
            <td><form:input path="subject"/></td>

                        <td><form:label path="message">Message</form:label></td>
                        <td><form:input path="message"/></td>

      <button type="submit" class="btn btn-block btn-primary">
        Send
      </button>
    </form:form>

<table>
  <tr>
    <th>subject</th>
    <th>message</th>
    <th>submitted Date</th>
    <th>status</th>
  </tr>

    <c:forEach items="${data}" var="item" >
        <tr>
            <td>${item.subject}</td>
            <td>${item.message}</td>
            <td>${item.submittedDate}</td>
            <td>${item.status}</td>
         </tr>
    </c:forEach>


</table>





  </div>
</body>
</html>