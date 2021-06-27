<%--
  Created by IntelliJ IDEA.
  User: deyi
  Date: 2021/6/26
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
    <%@include file="common/head.jsp"%>
    <title>数据统计</title>
</head>
<body>
<div id="main" style="width: 600px; height: 400px;"></div>

<script type="text/javascript">
    var myChart=echarts.init(document.getElementById("main"));

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画


    var names = [];    //班级数组（实际用来盛放X轴坐标值）
    var nums = [];    //人数数组（实际用来盛放Y坐标值）

    //图表的配置
    var option =
        {
            //图表的标题
            title : {
                text : '故障类型统计'
            },

            //图示。 legend的data与series的name 两者必须同时存在，且数量和文字内容必须一致。
            legend : { data: ['故障类型个数'] },

            //X轴的设置。 type的取值默认为'category'，表示 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。
            xAxis : {
                type : 'category',
                data : names
            },

            //Y轴的设置。
            yAxis : {
                type: 'value'
            },

            //提示框组件
            tooltip : {
                trigger : 'axis',
                axisPointer : {
                    type : 'cross',
                    crossStyle : {
                        color : 'blue'
                    },
                    lineStyle : {
                        color : 'blue'
                    }
                }
            },

            //工具栏
            toolbox : {
                emphasis : {},
                show : true,
                showTitle : true,
                feature : {
                    saveAsImage : {},// 保存为图片
                    restore : {},// 还原
                    dataView : {},// 数据视图
                    magicType : {
                        type : ['line', 'bar']
                    },
                    // 数据缩放
                    dataZoom : {}
                }
            },

            //每个series都是显示在同一个界面上的一类图表
            series : [
                {
                    name:'故障类型个数',
                    type : 'bar',
                    data: nums
                }
            ]
        }

    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "http://localhost:8080/FaultManageSystem/FaultServlet",    //请求发送到TestServlet处
        data : { action : "FaultCount" },
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {

                //横坐标赋值
                for(var i=0;i<result.length;i++){
                    names.push(result[i].cname);
                }

                //纵坐标series赋值
                for(var i=0;i<result.length;i++){
                    nums.push(result[i].count);
                }

                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption(option);
            }
        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })

</script>
</body>
</html>
