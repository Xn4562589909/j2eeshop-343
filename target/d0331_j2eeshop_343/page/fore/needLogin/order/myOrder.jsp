<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/13
  Time: 2:14
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
                ${foreUser.name}的所有订单
            </h3>
            <c:if test="${orders.size()==0}">
                <div style="display: flex;justify-content: center">
                    <p>您还没任何订单，赶快下单购买自己喜欢的商品吧！</p>
                </div>
            </c:if>
            <c:if test="${orders.size()!=0}">
                <div style="display: flex;flex-flow: row wrap;justify-content: space-around;">
                    <c:forEach items="${orders}" var="order" varStatus="st">
                        <div style="display: flex;flex-direction: column;justify-content: space-around;
                        align-items: flex-start;border: black solid 1px;background-color: #fffae8
                        ;width: 300px;margin-bottom: 50px;padding-bottom: 5px">
                            <div style="margin-left: 5px">
                                <p><b>订单编号:</b>${order.orderCode}</p>
                                <span>创建时间:${order.createDate}</span>
                                <p><b>商品信息:</b></p>
                                <c:forEach items="${order.orderItems}" var="orderItem" varStatus="st">
                                    <div style="border-bottom: black dashed 1px;">
                                        <p>
                                            <b>商品名称:</b>
                                            <a href="/fore_foreProduct_show?id=${orderItem.product.id}">
                                                    ${orderItem.product.name}
                                            </a>
                                        </p>
                                        <p><b>商品价格:</b>${orderItem.product.promotePrice}</p>
                                        <p><b>购买数量:</b>${orderItem.number}</p>
                                    </div>
                                </c:forEach>
                                <p><b>商品总数量:</b>${order.totalNumber}</p>
                                <p><b>总计金额:</b>${order.total}</p>
                                <p><b>订单状态:</b>${order.status}</p>
                                <span>更新时间:${order.gmtModified}</span>
                            </div>
                            <div style="display: flex;justify-content: space-between;margin-left: 5px;width: 290px">
                                <c:if test="${order.status eq '待支付'}">
                                    <a href="/fore_order_payView?orderId=${order.id}">
                                        <button type="button" class="btn btn-sm btn-info">前往支付</button>
                                    </a>
                                </c:if>
                                <c:if test="${order.status eq '待评价'}">
                                    <a href="/fore_order_comment?orderId=${order.id}">
                                        <button type="button" class="btn btn-sm btn-info">评价商品</button>
                                    </a>
                                </c:if>
                                <button type="button" class="btn btn-danger btn-sm delete-btn"
                                        name="/fore_order_delete?id=${order.id}" >删除订单</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
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
                    alert("删除成功!")
                }else {
                    alert("删除失败,请重新刷新页面!")
                }
            }
        )
    });
</script>
</html>
