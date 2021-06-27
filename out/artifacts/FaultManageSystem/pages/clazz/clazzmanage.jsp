<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/26
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>故障类型管理</title>
    <style type="text/css">
        .h-pd{
            padding: 0px !important;
        }
    </style>
</head>
<body>

<div class="container">
    <!--搜索以及排序-->
    <form class="form-inline" style="margin-top: 15px">

        <a class="btn btn-primary" href="ClazzServlet?action=queryAllClazz">查看所有故障类型</a>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#create">
            添加
        </button>
    </form>
    <!--表格显示-->
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>故障类型编号</th>
            <th>故障类型</th>
            <th>编辑</th>
        </tr>
        <c:forEach items="${pg.pageData}" var="clazz">
            <tr>
                <td class="col-md-4">${clazz.id}</td>
                <td class="col-md-5">${clazz.name}</td>
                <td ><a class="btn btn-sm btn-success col-md-2 col-md-offset-2 active updatebutton" name="${clazz.id}">编辑</a>
                    <a class="btn btn-sm btn-danger col-md-2 col-md-offset-2 active" href="ClazzServlet?action=deleteClazz&id=${clazz.id}" name="btn-delete">删除</a></td>
                <input type="hidden" value="${clazz.name}" id="${clazz.id}mes">
            </tr>
        </c:forEach>
    </table>
    <%--分页导航--%>
    <nav aria-label="Page navigation" style="float:right;position: fixed;bottom: 0px;">
        <ul class="pagination">
            <ul class="pagination">
                <li class="page-item"><a class="page-link"
                                         href="ClazzServlet?action=queryAllClazz&curPage=${pg.firstpage}">首页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="ClazzServlet?action=queryAllClazz&curPage=${pg.currentpage-1}">上一页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="ClazzServlet?action=queryAllClazz&curPage=${pg.currentpage+1}">下一页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="ClazzServlet?action=queryAllClazz&curPage=${pg.lastpage}">尾页</a>
                </li>
                <li class="page-item"><a class="page-link">当前第${pg.currentpage}/${pg.totalpage}页</a></li>
            </ul>
        </ul>
    </nav>
</div>
<!--模态框-添加-->
<div class="modal fade" id="create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="ClazzServlet?action=addClazz" class="form-horizontal" id="form-add" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障类型</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" name="name-add" id="name-add" placeholder="请输入故障类型名称">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btn-add">添加</button>
            </div>
        </div>
    </div>
</div>
<!--模态框-修改-->
<div class="modal fade" id="updatewin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >修改</h4>
            </div>
            <div class="modal-body">
                <!--修改表单-->
                <form action="ClazzServlet?action=updateClazz" class="form-horizontal" method="post" id="form-edit">
                    <input type="hidden" name="id-edit" id="id-edit">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障类型</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" name="name-edit" id="name-edit">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btn-edit">修改</button>
            </div>
        </div>
    </div>
</div>
<!--JavaScript体-->
<script type="text/javascript">
    $(".updatebutton").click(function (){
        $("#id-edit").val(this.name);
        var str=$("#"+this.name+"mes").val();
        $("#name-edit").val(str)
        $("#updatewin").modal("show");
    });
    $("#btn-edit").click(function (){
       $("#form-edit").submit();
    });
    $("#btn-add").click(function (){
        $("#form-add").submit();
    })
    $("a[name='btn-delete']").click(function (){
        var con;
        con=confirm("您确定要删除本条故障类型吗？")
        return con;
    })
</script>
</body>
</html>
