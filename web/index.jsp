<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty user }">
    <a href="login.jsp">请登录</a>
</c:if>
<c:if test="${!empty user }">
    <html>
    <head>
            <%--静态包含base标签，css，js文件--%>
        <%@include file="/pages/common/head.jsp" %>
        <title>首页</title>
        <style type="text/css">
            html, body {
                height: 100%;
                width: 100%;
            }

            .wrap {
                min-height: 100%;
                height: auto !important;
                margin-bottom: -20px;
            }

            .footer {
                border-top: 1px solid #e5e5e5;
                color: #777;
                background-color: #f5f5f5;
            }

            a:hover {
                text-decoration-line: none;
            }

            a:link {
                text-decoration-line: none;

            }

            a:visited {
                color: #409ee4;
            }

            .h-pd {
                padding: 0px !important;
            }
        </style>

    </head>
    <body>

    <div class="wrap container col-md-12 h-pd" style="height: 100%">
        <!--左侧菜单栏-->
        <div id="contain" class="col-md-2 h-pd" style="height: 100%;background-color:#304156 ;border-radius: unset">
            <!--树形菜单-->
            <div class="panel-group" id="accordion">
                <!--故障管理-->
                <div class="panel student" style="background-color: #304156; color: #b1cbd0 ">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse1" id="fault-btn">
                                故障管理
                            </a>
                        </h4>
                    </div>
                </div>
                <!--故障类型管理-->
                <div class="panel groupleader" style="background-color: #304156; color: #b1cbd0 ">

                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse2" id="clazz-btn">
                                故障类型管理
                            </a>
                        </h4>
                    </div>
                </div>
                <!--车间管理-->
                <div class="panel teacher" style="background-color: #304156; color: #b1cbd0 ">

                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse3" id="workshop-btn">
                                车间管理
                            </a>
                        </h4>
                    </div>
                </div>
                <!--人员管理-->
                <div class="panel manager" style="background-color: #304156; color: #b1cbd0 ">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#selector"
                               href=".collapse4" id="statistics">可视化数据
                            </a>
                        </h4>
                    </div>
                </div>
            </div>

        </div>
            <%--右侧内容栏--%>
        <div class=" col-md-10 h-pd">
            <!--导航-->
            <nav class="navbar navbar-default" style="margin-bottom: 0px">
                <div class="container-fluid">
                    <div class="navbar-header container row">
                        <div class="navbar-brand col-md-5"><span>当前用户：${user.name}</span></div>
                        <%--退出--%>
                        <div class="navbar-brand col-md-3"><a href="UserServlet?action=logout"
                                                                              style="float: right">
                            退出
                        </a></div>
                        <div class="navbar-brand col-md-3"><span>当前在线人数：${numberCount}</span></div>
                    </div>
                </div>
            </nav>
            <!--标签页-->
            <iframe id="context" class="col-md-12 h-pd" frameborder=0 scrolling='auto' src="welcomePage.html"></iframe>
        </div>
    </div>
    <!--页脚-->
    <div class="footer ">
        <div class="container" style="text-align: center">
            Copyright &copy;2021 2018级软件工程3班 张继德
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            var tabs = $("ul.nav-tabs");

            //设置左侧导航栏高度
            function setcontainh() {
                var Height = document.documentElement.clientHeight;
                $("#contain").css("height", Height - 20 + 'px');
                $("#context").css("height", Height - 20 - $(".navbar-default").height() + 'px')
            }

            //设置iframe.src控制内容栏显示什么内容
            $("#fault-btn").click(function () {
                $("#context").attr("src", "FaultServlet?action=queryFaultByClazz&clazzid=0");
            });
            $("#workshop-btn").click(function (){
                $("#context").attr("src", "WorkshopServlet?action=queryAllWorkshop");
            })
            $("#clazz-btn").click(function (){
                $("#context").attr("src", "ClazzServlet?action=queryAllClazz");
            })
            $("#statistics").click(function (){
                $("#context").attr("src", "pages/statistics.jsp");
            })
            window.onload = setcontainh;
        });
    </script>

    </body>
    </html>
</c:if>