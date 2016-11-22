<%--
  Created by IntelliJ IDEA.
  User: alexandershakhov
  Date: 21.11.16
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4>These are posts</h4>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">

            <c:forEach items="${requestScope.posts}" var="post">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <p>${post.body}</p>
                        <a href="posts/${post.postId}">Add Comment</a>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>
