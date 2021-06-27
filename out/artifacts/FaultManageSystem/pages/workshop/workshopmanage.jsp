<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/26
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp"%>
    <title>车间管理</title>
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
        <a class="btn btn-primary" href="WorkshopServlet?action=queryAllWorkshop">查看所有车间</a>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#create">
            添加
        </button>
    </form>
    <!--表格显示-->
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>车间编号</th>
            <th>车间名字</th>
            <th>车间主任</th>
            <th>编辑</th>
        </tr>
        <c:forEach items="${pg.pageData}" var="workshop">
            <tr>
                <td class="col-md-3">${workshop.id}</td>
                <td class="col-md-3">${workshop.name}</td>
                <td class="col-md-3">${workshop.leader}</td>
                <td ><a class="btn btn-sm btn-success col-md-2 col-md-offset-2 active updatebutton" name="${workshop.id}">编辑</a>
                    <a class="btn btn-sm btn-danger col-md-2 col-md-offset-2 active" href="WorkshopServlet?action=deleteWorkshop&id=${workshop.id}" name="btn-delete">删除</a></td>
                <input type="hidden" value="${workshop.name}&${workshop.leader}" name="${workshop.id}mes" id="${workshop.id}mes">
            </tr>
        </c:forEach>


    </table>
    <%--分页导航--%>
    <nav aria-label="Page navigation" style="float:right;position: fixed;bottom: 0px;">
        <ul class="pagination">
            <ul class="pagination">
                <li class="page-item"><a class="page-link"
                                         href="WorkshopServlet?action=queryAllWorkshop&curPage=${pg.firstpage}">首页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="WorkshopServlet?action=queryAllWorkshop&curPage=${pg.currentpage-1}">上一页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="WorkshopServlet?action=queryAllWorkshop&curPage=${pg.currentpage+1}">下一页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="WorkshopServlet?action=queryAllWorkshop&curPage=${pg.lastpage}">尾页</a>
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
                <form action="WorkshopServlet?action=addWorkshop" class="form-horizontal" id="form-add" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">车间名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" id="name-add" name="name-add" placeholder="请输入车间名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">车间主任</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" id="leader-add" name="leader-add" placeholder="请输入车间主任名字">
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
                <form action="WorkshopServlet?action=updateWorkshop" class="form-horizontal" id="form-edit" method="post">
                    <input name="id-edit" id="id-edit" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">车间名</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" id="name-edit" name="name-edit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">车间主任</label>
                        <div class="col-sm-7">
                            <input class="form-control" type="" id="leader-edit" name="leader-edit">
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
        var str=$("#"+this.name+"mes").val();
        var strs=str.split("&");
        $("#id-edit").val(this.name);
        $("#name-edit").val(strs[0]);
        $("#leader-edit").val(strs[1])
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
        con=confirm("您确定要删除本车间吗？")
        return con;
    })
</script>
</body>
</html>
