<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/12
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../../include/header.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default navbar-inverse" role="navigation">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/fore_homePage_list">首页</a>
                        </li>
                        <li>
                            <a href="/page/fore/needLogin/user/updateUser.jsp"> 欢迎您，${foreUser.name}</a>
                        </li>
                        <li>
                            <a href="/fore_foreUser_exitLogin">退出</a>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="/fore_foreProduct_nameList" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="searchProduct" />
                        </div> <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right nav-pills" >
                        <li>
                            <a href="/fore_orderItem_shoppingCar">
                                <span class="badge pull-right">${oiNum}</span>
                                购物车
                            </a>
                        </li>
                        <li>
                            <a href="/fore_order_listOrder">我的订单</a>
                        </li>
                        <li>
                            <a href="/page/admin/user/login.jsp">后台管理登录</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-6 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>用户id</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>创建日期</th>
                    <th>修改日期</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${foreUser.id}</td>
                    <td>${foreUser.name}</td>
                    <td>${foreUser.password}</td>
                    <td>${foreUser.gmtCreate}</td>
                    <td>${foreUser.gmtModified}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-md-6 column">
            <form role="form" action="/fore_foreUser_update">
                <div class="form-group">
                    <label for="password1">输入修改的密码:</label>
                    <input type="password" class="form-control" id="password1" name="password" />
                </div>
                <div class="form-group">
                    <label for="password2">再次输入的密码:</label>
                    <input type="password" class="form-control" id="password2" />
                    <span id="inspect" style="display: none;color: red">两次密码不一致!</span>
                </div>
                <button type="submit" class="btn btn-default" id="submit" disabled="disabled">修改</button>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    $("#password2").change(function () {
        let password1 = $("#password1").val();
        let password2 = $("#password2").val();
        if (password1 != password2){
            $("#inspect").show();
            $("#submit").attr("disabled",true);
        }else {
            $("#inspect").hide();
            $("#submit").attr("disabled",false)
        }
    })
</script>
</html>
