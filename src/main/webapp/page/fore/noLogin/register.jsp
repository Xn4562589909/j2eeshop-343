<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/10
  Time: 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../include/header.jsp" />
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-success text-center">
                欢迎注册tmall电商平台
            </h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <img alt="300x300" src="../../../imgs/login/login1.jpg" height="300" width="300" class="img-rounded" />
        </div>
        <div class="col-md-4 column">
            <form role="form" action="/fore_foreUser_register" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">注册用户:</label><input type="text" class="form-control" name="username" id="exampleInputEmail1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">注册密码:</label><input type="password" class="form-control" name="password" id="exampleInputPassword1" />
                </div>
                <button type="submit" class="btn btn-default">注册</button>
            </form>
        </div>
        <div class="col-md-4 column">
            <img alt="300x300" src="../../../imgs/login/login2.jpg" width="300" height="300" class="img-rounded" />
        </div>
    </div>
</div>
</body>
</html>
