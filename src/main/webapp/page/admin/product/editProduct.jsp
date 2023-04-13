<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/4
  Time: 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" action="/admin_product_update" method="post">
                <div class="form-group">
                    <input type="hidden" class="form-control" name="id" value="${p.id}" />
                </div>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="cid" value="${p.category.id}" />
                </div>
                <div class="form-group">
                    <label for="productName">商品名称:</label><input type="text" class="form-control" name="name" value="${p.name}" id="productName" />
                </div>
                <div class="form-group">
                    <label for="subTitle">小标题:</label><input type="text" class="form-control" name="subTitle" value="${p.subTitle}" id="subTitle" />
                </div>
                <div class="form-group">
                    <label for="originPrice">原价:</label><input type="text" class="form-control" name="originalPrice" value="${p.originalPrice}" id="originPrice" />
                </div>
                <div class="form-group">
                    <label for="promotePrice">促销价:</label><input type="text" class="form-control" name="promotePrice" value="${p.promotePrice}" id="promotePrice" />
                </div>
                <div class="form-group">
                    <label for="stock">库存:</label><input type="text" class="form-control" name="stock" value="${p.stock}" id="stock" />
                </div>
                <button type="submit" class="btn btn-default">修改</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
