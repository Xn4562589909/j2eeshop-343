<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/13
  Time: 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: aliceblue">
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
                            <a href="#"> 欢迎您，${foreUser.name}</a>
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
            <c:forEach items="${productsComment}" var="product" varStatus="st">
                <h4 class="text-center">${product.name}</h4>
                <form class="form-horizontal" role="form" action="/fore_review_comment" method="get">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="hidden" class="form-control" name="productId" value="${product.id}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="hidden" class="form-control" name="orderId" value="${orderId}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="comment" class="col-sm-2 control-label">您对该商品的评价:</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="3" id="comment" name="comment"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label><input type="checkbox" value="true" name="anonymity" />是否匿名评价</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" id="submitBtn" class="btn btn-info">提交</button>
                        </div>
                    </div>
                </form>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
