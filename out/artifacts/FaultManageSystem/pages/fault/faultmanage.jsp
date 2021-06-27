<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/25
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/pages/common/head.jsp" %>
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet">
    <script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <title>故障管理</title>
    <style type="text/css">
        .h-pd {
            padding: 0px !important;
        }
    </style>
</head>
<body>

<div class="container">
    <!--搜索以及排序-->
    <form class="form-inline" style="margin-top: 15px">
        <a class="btn btn-primary" href="FaultServlet?action=queryFaultByClazz&clazzid=0">查看全部故障</a>
        <button type="button" class="btn btn-primary addbutton" data-toggle="modal" data-target="#create">
            添加
        </button>
    </form>
    <!--表格显示-->
    <table CLASS="table table-striped table-hover table-bordered" style="text-align: center;margin-top: 10px">
        <tr>
            <th>故障编号</th>
            <th>故障类型</th>
            <th>故障车间</th>
            <th>负责人</th>
            <th>故障时间</th>
            <th>故障详情</th>
            <th>编辑</th>
        </tr>
        <c:forEach items="${pg.pageData}" var="fault">
            <tr>
                <td class="col-md-1">${fault.id}</td>
                <td class="col-md-2">${fault.clazz.name}</td>
                <td class="col-md-1">${fault.workshop.name}</td>
                <td class="col-md-1">${fault.workshop.leader}</td>
                <td class="col-md-2"><fmt:formatDate value="${fault.time.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="col-md-3"
                    style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${fault.detail}</td>
                <td><a class="btn btn-sm btn-success col-md-5 active updatebutton" name="${fault.id}">编辑</a>
                    <a class="btn btn-sm btn-danger col-md-5 col-md-offset-1 active" href="FaultServlet?action=deleteFaultById&id=${fault.id}" name="btn-delete">删除</a></td>
                <input type="hidden" name="mes" id="${fault.id}mes"
                       value="${fault.id}&${fault.clazz.name}&${fault.workshop.name}&<fmt:formatDate
                    value="${fault.time.time}" pattern="yyyy-MM-dd HH:mm:ss"/>&${fault.detail}">
            </tr>
        </c:forEach>


    </table>
    <%--分页导航--%>
    <nav aria-label="Page navigation" style="float:right;position: fixed;bottom: 0px;">
        <ul class="pagination">
            <ul class="pagination">
                <li class="page-item"><a class="page-link"
                                         href="FaultServlet?action=queryFaultByClazz&curPage=${pg.firstpage}&clazzid=${clazzid}">首页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="FaultServlet?action=queryFaultByClazz&curPage=${pg.currentpage-1}&clazzid=${clazzid}">上一页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="FaultServlet?action=queryFaultByClazz&curPage=${pg.currentpage+1}&clazzid=${clazzid}">下一页</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="FaultServlet?action=queryFaultByClazz&curPage=${pg.lastpage}&clazzid=${clazzid}">尾页</a>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加</h4>
            </div>
            <div class="modal-body">
                <!--添加表单-->
                <form action="FaultServlet?action=addFault" class="form-horizontal" id="form-add" method="post">
                    <%--选择故障类型--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障类型</label>
                        <div class="col-sm-8">
                            <select class="form-control " id="clazz-add" name="clazz-add">
                                <c:forEach items="${clazzList}" var="clazz">
                                    <option value="${clazz.id}">${clazz.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--选择车间--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障车间</label>
                        <div class="col-sm-8">
                            <select class="form-control " id="workshop-add" name="workshop-add">
                                <c:forEach items="${workshopList}" var="workshop">
                                    <option value="${workshop.id}">${workshop.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--选择时间--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障日期</label>
                        <!--指定 date标记-->
                        <div class='input-group date col-sm-8' id='datetimepicker-add'>
                            <input type='text' class="form-control" name="datetimepicker-add"/>
                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                        </div>
                    </div>
                    <%--故障详情--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障详情</label>
                        <div class="col-sm-8">
                            <input class="form-control" type="text" id="detail-add" name="detail-add">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">
                <!--修改表单-->
                <form action="FaultServlet?action=updateFault" class="form-horizontal" id="form-edit" method="post">
                    <%--故障id--%>
                    <input type="hidden" id="id-edit" name="id-edit">
                    <%--修改故障类型--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障类型</label>
                        <div class="col-sm-8">
                            <select class="form-control " id="clazz-edit" name="clazz-edit">
                                <c:forEach items="${clazzList}" var="clazz">
                                    <option value="${clazz.id}">${clazz.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--修改车间--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障车间</label>
                        <div class="col-sm-8">
                            <select class="form-control " id="workshop-edit" name="workshop-edit">
                                <c:forEach items="${workshopList}" var="workshop">
                                    <option value="${workshop.id}">${workshop.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--修改时间--%>
                    <div class="form-group">

                        <label class="col-sm-2 control-label">故障日期</label>

                        <!--指定 date标记-->

                        <div class='input-group date col-sm-8' id='datetimepicker-edit'>

                            <input type='text' class="form-control" name="datetimepicker-edit"/>

                            <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>

                        </div>

                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">故障详情</label>
                        <div class="col-sm-8">
                            <input class="form-control" type="text" id="detail-edit" name="detail-edit">
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
    //修改模态框数据
    $(".updatebutton").click(function () {
        var str = $("#" + this.name + "mes").val();
        var strs = str.split("&");
        $("#id-edit").val(strs[0]);
        $("#clazz-edit").find("option").each(function () {
            if ($(this).text() == strs[1]) {
                $(this).attr("selected", "selected");
                flag = true;
            }
        });
        $("#workshop-edit").find("option").each(function () {
            if ($(this).text() == strs[2]) {
                $(this).attr("selected", "selected");
                flag = true;
            }
        });
        $("#detail-edit").val(strs[4]);
        $('#datetimepicker-edit').datetimepicker({
            format: 'YYYY-MM-DD hh:mm:ss',
            locale: moment.locale('zh-cn'),
            defaultDate: strs[3],
        });
        $("#updatewin").modal("show");
    });
    //添加模态框数据
    $(".addbutton").click(function (){
        $('#datetimepicker-add').datetimepicker({
            format: 'YYYY-MM-DD hh:mm:ss',
            locale: moment.locale('zh-cn')
        });
    });
    //修改按钮
    $("#btn-edit").click(function () {
        $("#form-edit").submit();
    });
    //添加按钮
    $("#btn-add").click(function (){
        $("#form-add").submit();
    });
    //删除按钮
    $("a[name='btn-delete']").click(function (){
        var con;
        con=confirm("您确定要删除本条故障吗？")
        return con;
    })
    $(function () {


    });
</script>
</body>
</html>
