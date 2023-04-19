<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/12
  Time: 19:41
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
            <h3 class="text-center text-success">
                确认订单
            </h3>
            <hr>
            <h4 class="text-center text-info">
                商品信息
            </h4>
            <table class="table">
                <thead>
                <tr>
                    <th>商品图片</th>
                    <th>商品名称</th>
                    <th>原价</th>
                    <th>现价</th>
                    <th>数量</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${!empty orderItems}" >
                    <c:forEach items="${orderItems}" var="orderItem" varStatus="st">
                        <tr class="warning">
                            <td><img src="${orderItem.product.images.get(0).url}" width="75px" height="75px"></td>
                            <td>${orderItem.product.name}</td>
                            <td>${orderItem.product.originalPrice}</td>
                            <td>${orderItem.product.promotePrice}</td>
                            <td>${orderItem.number}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty orderItems}" >
                    <tr class="warning">
                        <td><img src="${product.images.get(0).url}" width="75px" height="75px"></td>
                        <td>${product.name}</td>
                        <td>${product.originalPrice}</td>
                        <td>${product.promotePrice}</td>
                        <td>${productNum}</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <hr>
            <h4 class="text-center text-info">
                填写收货信息
            </h4>
            <c:if test="${empty orderItems}" >
                <form role="form" action="/fore_order_add" method="post">
                    <div class="form-group">
                        <input type="hidden" class="form-control" value="${product.id}" name="pid" />
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" value="${productNum}" name="pNum" />
                    </div>
                    <div class="form-group">
                        <label for="address">收货地址:</label>
                        <input type="text" class="form-control" id="address" name="address" />
                    </div>
                    <div class="form-group">
                        <label for="post">邮政编码:</label>
                        <input type="text" class="form-control" id="post" name="post" />
                    </div>
                    <div class="form-group">
                        <label for="receiver">收件人:</label>
                        <input type="text" class="form-control" id="receiver" name="receiver" />
                    </div>
                    <div class="form-group">
                        <label for="mobile">联系电话:</label>
                        <input type="text" class="form-control" id="mobile" name="mobile" />
                    </div>
                    <div class="form-group">
                        <label for="userMessage">备注:</label>
                        <input type="text" class="form-control" id="userMessage" name="userMessage" />
                    </div>
                    <button type="submit" class="btn btn-default">确认提交</button>
                </form>
            </c:if>
            <c:if test="${!empty orderItems}" >
                <form role="form" action="/fore_order_addByCar" method="post">
                    <div class="form-group">
                        <input type="hidden" class="form-control" value="${orderItems.get(0).order.id}" name="oid" />
                    </div>
                    <div class="form-group">
                        <label for="address1">收货地址:</label>
                        <input type="text" class="form-control" id="address1" name="address" />
                    </div>
                    <div class="form-group">
                        <label for="post1">邮政编码:</label>
                        <input type="text" class="form-control" id="post1" name="post" />
                    </div>
                    <div class="form-group">
                        <label for="receiver1">收件人:</label>
                        <input type="text" class="form-control" id="receiver1" name="receiver" />
                    </div>
                    <div class="form-group">
                        <label for="mobile1">联系电话:</label>
                        <input type="text" class="form-control" id="mobile1" name="mobile" />
                    </div>
                    <div class="form-group">
                        <label for="userMessage1">备注:</label>
                        <input type="text" class="form-control" id="userMessage1" name="userMessage" />
                    </div>
                    <button type="submit" class="btn btn-default">确认提交</button>
                </form>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
