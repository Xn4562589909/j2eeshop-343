<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/12
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: #f6e4e4">
<jsp:include page="../../include/header.jsp" />
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
            <h3 class="text-center text-success">
                等待付款
            </h3>
            <hr>
            <h4 class="text-center text-info">
                订单：${order.orderCode}
            </h4>
            <h5 class="text-center">
                订单详情
            </h5>
            <table class="table">
                <thead>
                <tr>
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>数量</th>
                    <th>原价</th>
                    <th>现价</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.orderItems}" var="orderItem" varStatus="st">
                    <tr class="warning">
                        <td><img src="${orderItem.product.images.get(0).url}" width="75px" height="75px"></td>
                        <td>${orderItem.product.name}</td>
                        <td>${orderItem.number}</td>
                        <td>${orderItem.product.originalPrice}</td>
                        <td>${orderItem.product.promotePrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <hr>
            <h4 class="text-center text-info">
                确认支付
            </h4>
            <div style="display: flex;flex-direction: column;align-items: center;">
                <div>
                    <p>商品总数量:${order.totalNumber}</p>
                    <p>应付款${order.total}元</p>
                </div>
                <div>
                    <form role="form" action="/fore_order_update" method="post">
                        <div class="form-group">
                            <input type="hidden" value="${order.id}" class="form-control" name="orderId" />
                        </div>
                        <button type="submit" class="btn btn-default">确定支付</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
