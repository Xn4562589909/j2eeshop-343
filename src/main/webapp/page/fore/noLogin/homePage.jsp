<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/7
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Tmall商城，欢迎您！</title>
</head>
<body style="background-color: #fffae8">
<jsp:include page="../include/header.jsp" />
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default navbar-inverse" role="navigation">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
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
                    <form class="navbar-form navbar-left" role="search" action="/fore_foreProduct_nameList" method="post">
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
        <div class="col-md-4 column">
            <ul>
                <c:forEach items="${categories}" var="category" varStatus="st">
                    <li style="font-size: 1.5rem">
                        <a href="/fore_foreProduct_cList?id=${category.id}&sort=price">${category.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-6 column">
            <div class="carousel slide" id="carousel-363894">
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carousel-363894">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-363894">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-363894">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt="" src="../../imgs/homeImgs/tamll4.jpg" width="100%"/>
                    </div>
                    <div class="item">
                        <img alt="" src="../../imgs/homeImgs/tamll5.jpg" width="100%"/>
                    </div>
                    <div class="item">
                        <img alt="" src="../../imgs/homeImgs/tamll6.jpg" width="100%"/>
                    </div>
                </div> <a class="left carousel-control" href="#carousel-363894" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-363894" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
        <div class="col-md-2 column">

        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:forEach items="${categories}" var="category" varStatus="st">
                <h3>${category.name}</h3>
                <br>
                <div style="display: flex;flex-flow: row wrap;justify-content: space-around;align-items: center;">
                    <c:forEach items="${category.products}" var="product" varStatus="st">
                        <div style="height: 350px;width: 180px;flex: 0 0 auto;margin-bottom: 20px;margin-right: 30px">
                            <a href="/fore_foreProduct_show?id=${product.id}" style="text-decoration: none;color: black">
                                <div>
                                    <p>${product.name}</p>
                                    <img src="${product.images.get(0).url}" width="180px" height="180px" >
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
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
