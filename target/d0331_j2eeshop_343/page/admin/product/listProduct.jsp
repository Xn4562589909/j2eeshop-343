<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/3
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../include/header.jsp" />
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:if test="${products.size()==0}">
                <div style="display: flex;justify-content: center;">
                    <p>该分类下没有商品，赶快添加商品吧！</p>
                </div>
            </c:if>
            <c:if test="${products.size()!=0}">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>商品图片</th><th>商品名称</th><th>小标题</th><th>原价</th><th>促销价</th><th>库存</th>
                        <th>创建时间</th><th>编辑</th><th>删除</th><th>图片管理</th><th>属性值设置</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${products}" var="p" varStatus="st" >
                        <tr>
                            <c:if test="${p.images.size()==0}" >
                                <td>
                                    <img src="../../../imgs/product/暂无图片.png" height="70" width="70">
                                </td>
                            </c:if>
                            <c:if test="${p.images.size()!=0}" >
                                <td>
                                    <img src="${p.images.get(0).url}" height="70" width="70">
                                </td>
                            </c:if>
                            <td>${p.name}</td><td>${p.subTitle}</td><td>${p.originalPrice}</td>
                            <td>${p.promotePrice}</td><td>${p.stock}</td><td>${p.createDate}</td>
                            <td>
                                <a href="/admin_product_edit?id=${p.id}">
                                    <button type="button" class="btn btn-default btn-primary">编辑</button>
                                </a>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default btn-danger delete-btn"
                                        name="/admin_product_delete?id=${p.id}" >删除</button>
                            </td>
                            <td>
                                <a href="/admin_productImage_list?id=${p.id}">
                                    <button type="button" class="btn btn-default btn-info">管理图片</button>
                                </a>
                            </td>
                            <td>
                                <a href="/admin_propertyValue_list?id=${p.id}">
                                    <button type="button" class="btn btn-default btn-info">设置属性值</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" action="/admin_product_add" method="post">
                <div class="form-group">
                    <input type="hidden" class="form-control" name="cid" value="${cid}" />
                </div>
                <div class="form-group">
                    <label for="productName">商品名称:</label><input type="text" class="form-control" name="name" id="productName" />
                </div>
                <div class="form-group">
                    <label for="subTitle">小标题:</label><input type="text" class="form-control" name="subTitle" id="subTitle" />
                </div>
                <div class="form-group">
                    <label for="originPrice">原价:</label><input type="text" class="form-control" name="originalPrice" id="originPrice" />
                </div>
                <div class="form-group">
                    <label for="promotePrice">促销价:</label><input type="text" class="form-control" name="promotePrice" id="promotePrice" />
                </div>
                <div class="form-group">
                    <label for="stock">库存:</label><input type="text" class="form-control" name="stock" id="stock" />
                </div>
                <button type="submit" class="btn btn-default">添加</button>
            </form>
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
            function (result) {
                if (result == "success"){
                    that.parent().parent().hide();
                }else {
                    alert("出现错误,请刷新页面!")
                }
            }
        )
    });
</script>
</html>
