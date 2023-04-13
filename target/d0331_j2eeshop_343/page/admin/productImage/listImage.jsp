<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/7
  Time: 19:35
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
            <h3 class="text-center text-info">
                ${product.name}
            </h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>编号</th><th>图片</th><th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${piList}" var="pi" varStatus="st">
                    <tr>
                        <td>${pi.id}</td>
                        <td><img src="${pi.url}" height="75px" width="75px"></td>
                        <td>
                            <button type="button" class="btn btn-danger delete-image"
                                    name="/admin_productImage_delete?id=${pi.id}">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" action="/admin_productImage_add" method="post">
                <div class="form-group">
                    <input type="hidden" class="form-control" value="${product.id}" name="pid" />
                </div>
                <div class="form-group">
                    <label for="piUrl">请输入图片url</label><input type="text" class="form-control" id="piUrl" name="piUrl" />
                </div>
                 <button type="submit" class="btn btn-default">添加</button>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    $(".delete-image").click(function () {
       let that = $(this);
       let url = that.attr("name");
       $.get(
           url,
           function (result) {
               if (result=="success"){
                   that.parent().parent().hide();
               }else {
                   alert("出现错误,请重新刷新页面!");
               }
           }
       )
    });
</script>
</html>
