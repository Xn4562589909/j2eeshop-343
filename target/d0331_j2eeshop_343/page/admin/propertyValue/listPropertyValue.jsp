<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 54640
  Date: 2023/4/4
  Time: 2:23
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
            <h3 class="text-center text-info">
                ${product.name}
            </h3>
        </div>
    </div>
    <div style="display: flex;flex-flow: row wrap;">
        <c:forEach items="${pvs}" var="pv" varStatus="st" >
            <div style="flex: 0,0,20%;margin: 10px">
                <label for="${pv.id}">${pv.property.name}</label><br>
                <input type="text" class="form-control propertyValue" value="${pv.value}"
                       name="/admin_propertyValue_update?id=${pv.id}" id="${pv.id}" />
            </div>
        </c:forEach>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:if test="${pts.size()!=0}">
                <form role="form" action="/admin_propertyValue_add" method="post">
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="pid" value="${product.id}" />
                    </div>
                    <div class="form-group">
                        <label for="property">添加属性:</label>
                        <select class="form-control" id="property" name="ptName">
                            <c:forEach items="${pts}" var="pt" varStatus="st" >
                                <option>${pt.id}-${pt.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="pv">设置属性值:</label>
                        <input type="text" class="form-control" name="value" id="pv" />
                    </div>
                    <button type="submit" class="btn btn-default">添加</button>
                </form>
            </c:if>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:if test="${pvs.size()!=0}">
                <form role="form" action="/admin_propertyValue_delete" method="post">
                    <div class="form-group">
                        <label for="pt">选择删除属性:</label>
                        <select class="form-control" id="pt" name="pvName">
                            <c:forEach items="${pvs}" var="pv" varStatus="st" >
                                <option>${pv.id}-${pv.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">删除</button>
                </form>
            </c:if>
        </div>
    </div>
</div>

</body>
<script>
    $(".propertyValue").change(function () {
        let that = $(this);
        let val = that.val();
        let url = that.attr('name')+"&value="+val;
        $.post(
            url,
            function (result) {
                if (result=="success"){
                    that.css("border","1px solid lightgreen");
                }else {
                    alert("出现错误,请重新刷新页面!")
                }
            }
        )
    });
</script>
</html>
