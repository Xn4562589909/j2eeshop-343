<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/3
  Time: 22:00
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
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:if test="${pts.size()==0}">
                <div style="display: flex;justify-content: center">
                    <p>该分类暂无属性</p>
                </div>
            </c:if>
            <c:if test="${pts.size()!=0}">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>编号</th> <th>属性名称</th> <th>编辑</th> <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pts}" var="pt" varStatus="st">
                        <tr>
                            <td>${pt.id}</td>
                            <td>${pt.name}</td>
                            <td>
                                <a href="/admin_property_edit?id=${pt.id}">
                                    <button type="button" class="btn btn-default btn-primary">编辑</button>
                                </a>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default btn-danger delete-btn"
                                        name="/admin_property_delete?id=${pt.id}">删除</button>

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
            <form class="form-horizontal" role="form" action="/admin_property_add" method="post">
                <div class="form-group">
                    <label for="propertyName" class="col-sm-2 control-label">属性名称:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="propertyName" name="name" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control"  name="cid" value="${cid}" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">添加</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script>

    $('.delete-btn').click(function () {
        //获取后续ajax要发送的url
        let url =$(this).attr("name");
        let that = $(this);
        $.get(
            url,
            function (result) {
                if(result=="success"){
                    that.parent().parent().hide();
                }else {
                    alert("出现错误,请刷新页面！");
                }
            }
        )
    });

</script>
</body>
</html>
