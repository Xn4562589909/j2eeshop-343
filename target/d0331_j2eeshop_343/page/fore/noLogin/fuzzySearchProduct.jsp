<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/12
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: #fffae8">
<jsp:include page="../include/header.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default navbar-inverse" role="navigation">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/fore_homePage_list">首页</a>
                        </li>
                        <c:if test="${empty foreUser.name}">
                            <li>
                                <a href="/page/fore/noLogin/login.jsp">欢迎您，登录</a>
                            </li>
                            <li>
                                <a href="/page/fore/noLogin/register.jsp"> 注册</a>
                            </li>
                        </c:if>
                        <c:if test="${!empty foreUser.name}">
                            <li>
                                <a href="/page/fore/needLogin/user/updateUser.jsp"> 欢迎您，${foreUser.name}</a>
                            </li>
                            <li>
                                <a href="/fore_foreUser_exitLogin">退出</a>
                            </li>
                        </c:if>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="/fore_foreProduct_nameList" method="get">
                        <div class="form-group">
                            <input type="text" class="form-control" name="searchProduct" />
                        </div> <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right nav-pills" >
                        <li>
                            <a href="/fore_orderItem_shoppingCar">
                                <c:if test="${!empty oiNum}">
                                    <span class="badge pull-right">${oiNum}</span>
                                </c:if>
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
            <h3 class="text-success text-left">
                以为您查询到${products.size()}条结果
            </h3>
            <span><a href="/fore_foreProduct_nameList?searchProduct=${searchNameProduct}&sort=price">价格排序</a></span>
            <span><a href="/fore_foreProduct_nameList?searchProduct=${searchNameProduct}&sort=sale">销量排序</a></span>
            <span><a href="/fore_foreProduct_nameList?searchProduct=${searchNameProduct}&sort=reviewCount">评价数量排序</a></span>
            <hr>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div style="display: flex;flex-flow: row wrap;justify-content: space-around;align-items: center;">
                <c:forEach items="${products}" var="product" varStatus="st">
                    <div style="flex: 0 0 auto;margin-top: 35px;width: 250px;margin-left: 35px">
                        <a href="/fore_foreProduct_show?id=${product.id}" style="text-decoration: none;color: black;">
                            <div style="font-size: 1.5rem">
                                <p>${product.name}</p>
                                <img src="${product.images.get(0).url}" width="250px" height="250px" >
                                <p>原价:${product.originalPrice}</p>
                                <p>促销价:${product.promotePrice}</p>
                            </div>
                            <div style="display: flex;justify-content: space-between;">
                                <span>销量:${product.saleCount}</span>
                                <span>评论数:${product.reviewCount}</span>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
