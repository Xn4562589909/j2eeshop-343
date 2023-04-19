<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/11
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: #f6e4e4">
<jsp:include page="../include/header.jsp"/>
<div class="container" >
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
                    <form class="navbar-form navbar-left" role="search" action="/fore_foreProduct_list" method="post">
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
        <div class="col-md-6 column">
            <img src="${product.images.get(0).url}" class="img-rounded" width="500px" height="500px" />
        </div>
        <div class="col-md-6 column" style="margin-top: 40px;">
            <dl class="dl-horizontal" style="font-size: 2.0rem">
                <dt>
                    商品名称:
                </dt>
                <dd>
                    ${product.name}
                </dd>
                <dt>
                    商品特性:
                </dt>
                <dd>
                    ${product.subTitle}
                </dd>
                <dt>
                    商品原价:
                </dt>
                <dd>
                    ${product.originalPrice}元
                </dd>
                <dt>
                    秒杀价:
                </dt>
                <dd>
                    ${product.promotePrice}元
                </dd>
                <dt>
                    库存:
                </dt>
                <dd>
                    ${product.stock}件
                </dd>
                <dt>
                    销量:
                </dt>
                <dd>
                    月销${product.saleCount}件
                </dd>
            </dl>
            <form role="form" action="/fore_orderItem_directBuy" method="get" style="width: 235px;height: 45px;margin-left: 120px;margin-top: 75px">
                <div class="form-group" >
                    <input type="hidden" class="form-control" value="${product.id}" name="productId" id="productId" />
                </div>
                <div class="form-group" >
                    <label for="productNumber">数量:</label>
                    <input type="number" class="form-control" id="productNumber" value="1" max="${product.stock}" min="1" name="productNumber" />
                </div>
                <button type="button" class="btn btn-lg btn-info" id="joinCar">
                    加入购物车
                </button>
                <button type="submit" class="btn btn-lg btn-primary">直接购买</button>
            </form>
        </div>
    </div>
    <br>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="tabbable" id="tabs-756696">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel-553456" data-toggle="tab">商品属性</a>
                    </li>
                    <li>
                        <a href="#panel-190897" data-toggle="tab">商品评价</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="panel-553456">
                        <div style="display: flex;flex-flow: wrap row;justify-content: space-around;">
                            <c:forEach items="${pvs}" var="pv" varStatus="st">
                                <div style="margin: 20px;font-size: 1.5rem">
                                    <b>${pv.property.name}：</b>${pv.value}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="tab-pane" id="panel-190897">
                        <c:if test="${reviews.size()!=0}">
                            <c:forEach items="${reviews}" var="r" varStatus="st">
                                <div style="margin: 20px;font-size: 1.5rem">
                                    <p><i>${r.createDate}</i></p>
                                    <p><b>${r.nickname}：</b>${r.content}</p>
                                    <hr>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${reviews.size()==0}">
                            <div style="margin: 20px;font-size: 1.5rem">
                                <p>该商品暂无评价</p>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $("#joinCar").click(function () {
        let productNumber = $("#productNumber").val();
        let productId = $("#productId").val();
        let url = "/fore_orderItem_joinCar";
        $.post(
            url,
            {"productId":productId,
            "productNumber":productNumber},
            function (data) {
                if (data=="success"){
                    window.location.reload();
                    alert("添加成功,快去购物车结账吧")
                }else {
                    alert("您还没有登录，请先登录用户!")
                }
            }
        )
    })
</script>
</html>
