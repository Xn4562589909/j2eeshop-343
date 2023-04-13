<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/14
  Time: 1:26
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
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>商品图片</th><th>商品名称</th><th>评论内容</th><th>评论时间</th><th>编辑</th><th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${reviews}" var="r" varStatus="st">
                    <tr>
                        <td>
                            <a href="/fore_foreProduct_show?id=${r.product.id}">
                                <img src="${r.product.images.get(0).url}" width="75px" height="75px">
                            </a>
                        </td>
                        <td><a href="/fore_foreProduct_show?id=${r.product.id}">${r.product.name}</a></td>
                        <td>${r.content}</td>
                        <td>${r.createDate}</td>
                        <td>
                            <a href="/fore_review_edit?id=${r.id}">
                                <button type="button" class="btn btn-default btn-primary">编辑</button>
                            </a>
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-danger delete-btn"
                                    name="/fore_review_delete?id=${r.id}" >删除</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    $(".delete-btn").click(function () {
        let that = $(this);
        let url = that.attr("name");
        $.get(
            url,
            function (data) {
                if ("success"==data){
                    alert("删除成功!");
                    that.parent().parent().hide();
                }else {
                    alert("删除失败,请重新刷新页面!");
                }
            }
        )
    })
</script>
</html>
