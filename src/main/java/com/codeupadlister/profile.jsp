<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Welcome, ${sessionScope.user.username}!</h1>
    <p>Your email is: ${sessionScope.user.email}!</p>
    <div>
        <br>
        <h1>Here are your ads:</h1>
        <c:forEach var="ad" items="${ads}">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h2 class="card-title">${ad.title}</h2>
                    <p class="card-text">${ad.description}</p>
                    <div>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/ad?id=${ad.id}" role="button">View Ad</a>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/ads/update?id=${ad.id}" role="button">Update Ad</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
