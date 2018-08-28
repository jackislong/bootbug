<!DOCTYPE html>
<html lang="en">
<#include 'include/header.ftl'/>
<body>
<#include 'include/nav.ftl'/>

<div class="demoTable">
    检测时间：
    <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
        <input type="text" class="layui-input" id="startdate">
    </div>
    ——
    <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
        <input type="text" class="layui-input" id="enddate">
    </div>
    <button class="layui-btn" onclick="javascript:updatedata();">搜索</button>
</div>
<!-- 图表容器 DOM -->
<div id="container" style="width: 600px;height:400px;"></div>
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


    var chart = null;
    $(function () {
        chart = Highcharts.chart('container', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                events:{
                    load : queryall
//                    redraw:none
                }
            },
            title: {
                text: 'BUG分析'
            },
            tooltip: {
                headerFormat: '{series.name}<br>',
                pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    states: {
                        hover: {
                            enabled: false
                        }
                    },
                    slicedOffset: 20,         // 突出间距
                    point: {                  // 每个扇区是数据点对象，所以事件应该写在 point 下面
                        events: {
                            // 鼠标滑过是，突出当前扇区
                            mouseOver: function() {
                                this.slice();
                            },
                            // 鼠标移出时，收回突出显示
                            mouseOut: function() {
                                this.slice();
                            },
                            // 默认是点击突出，这里屏蔽掉
                            click: function() {
                                return false;
                            }
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'bug分析',
                data: [
                ]
            }]
        });
    })


    var updata = new Array();
    function queryall() {
        var par= "startdate="+$("#startdate").val()+"&"+"enddate ="+ $("#enddate").val();
        $.ajax({
            type: "POST",
            url: "${base}/analysis",
            data: par,
            success: function(msg){
                var  ser =  chart.series[0];
                $.each(msg,function (i,n) {
                    ser.addPoint(n);
                    updata.push(n);
                })
            }
        });
    }

    //修改数据
    function updatedata() {
        updata.length=0;//清空数据
        queryall();
        chart.update({
            series:[{
                data:updata
              }
            ]
        });
    }

</script>
</body>
</html>