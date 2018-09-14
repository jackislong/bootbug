<!DOCTYPE html>
<html lang="en">
<#include 'include/header.ftl'/>
<body>
<#include 'include/nav.ftl'/>

<div class="demoTable">
    处理人：
    <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
        <input type="text" class="layui-input" id="dealname">
    </div>
    <button class="layui-btn" onclick="javascript:updatedata();">搜索</button>
</div>
<!-- 图表容器 DOM -->
<div id="container" style="width:800px;height:600px;"></div>
<!-- 引入 highcharts.js -->
<script>

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#startdate',//指定元素
        });
    });

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#enddate',//指定元素
        });
    });


    var perchart = null;
    $(function () {
        perchart = Highcharts.chart('container', {
            chart: {
                type: 'column',
                events:{
                    load : querydata
                }
            },
            title: {
                text: ''
            },
            xAxis: {
                categories: []
            },
            credits: {
                enabled: true
            },
            plotOptions: {
                column: {
                    // 关于柱状图数据标签的详细配置参考：https://api.hcharts.cn/highcharts#plotOptions.column.dataLabels
                    dataLabels: {
                        enabled: true,
                        // verticalAlign: 'top', // 竖直对齐方式，默认是 center
                        inside: true
                    }
                }
            },
            series: [{
                name: '',
                data:  []
            }]
        });
    })


    var updata = new Array();
    function querydata() {
        var par= "dealname="+$("#dealname").val();
        $.ajax({
            type: "POST",
            url: "${base}/personanalysis",
            data: par,
            success: function(result){
                var cList = result.c;
                var mList = result.m;
                var name = result.name;
                console.log(cList);
                perchart.update({
                    xAxis: {
                        categories: mList
                    },
                    series:[{
                        name:name,
                        data:cList
                      }]
                });
            }
        });
    }

    //修改数据
    function updatedata() {
//        updata.length=0;//清空数据
        querydata();
//        chart.update({
//            series:[{
//                data:updata
//              }
//            ]
//        });
    }

</script>
</body>
</html>