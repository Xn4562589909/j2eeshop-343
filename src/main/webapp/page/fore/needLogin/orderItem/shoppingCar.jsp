<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/13
  Time: 0:28
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
                ${foreUser.name}的购物车
            </h3>
            <c:if test="${orderItems.size()==0}">
                <div style="display: flex;justify-content: center">
                    <p>您的购物车暂无商品，快去添加喜欢的商品吧!</p>
                </div>
            </c:if>
            <c:if test="${orderItems.size()!=0}">
                <table class="table">
                    <thead>
                    <tr>
                        <th>商品图片</th>
                        <th>商品名称</th>
                        <th>原价</th>
                        <th>现价</th>
                        <th>数量</th>
                        <th>选中</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderItems}" var="orderItem" varStatus="st">
                        <tr class="warning">
                            <td>
                                <a href="/fore_foreProduct_show?id=${orderItem.product.id}">
                                    <img src="${orderItem.product.images.get(0).url}" width="75px" height="75px">
                                </a>
                            </td>
                            <td>
                                <a href="/fore_foreProduct_show?id=${orderItem.product.id}">
                                        ${orderItem.product.name}
                                </a>
                            </td>
                            <td>${orderItem.product.originalPrice}</td>
                            <td>${orderItem.product.promotePrice}</td>
                            <td><input type="number" class="form-control" id="productNumber" value="${orderItem.number}"
                                       max="${orderItem.product.stock}" min="1" name="productNumber" /></td>
                            <td><input type="checkbox" /></td>
                            <td>
                                <button type="button" class="btn btn-default btn-danger delete-btn"
                                        name="/fore_orderItem_delete?id=${orderItem.id}" >删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7">
                            <a href="#">
                                <button type="button" class="btn btn-block btn-lg btn-info">付款</button>
                            </a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
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
                    that.parent().parent().hide();
                    alert("删除成功!");
                    window.location.reload();
                }else {
                    alert("删除失败,请重新刷新页面!")
                }
            }
        )
    });
</script>
</html>
