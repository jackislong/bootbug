<!DOCTYPE html>
<html lang="en">
<#include 'include/header.ftl'/>
<body>
<#include 'include/nav.ftl'/>

<div class="demoTable">
        <label class="layui-form-label">处理人</label>
        <div class="layui-input-inline">
            <select name="modules" lay-verify="required" lay-search="" id="dealname">
            </select>
        </div>
       <button class="layui-btn" onclick="javascript:updatedata();">搜索</button>
</div>
<!-- 图表容器 DOM -->
<div id="container" style="width:800px;height:600px;"></div>
<script>



    var perchart = null;
    $(function () {

        queryAlldealName();


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


    function querydata() {
        var par= "dealname="+$("#dealname").val();
        $.ajax({
            type: "POST",
            url: "${base}/dealnameanalysis",
            data: par,
            success: function(result){
                var cList = result.c;
                var mList = result.m;
                var name = result.dealname;
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
        querydata();
    }


    /**
     * 获取所有项目数据填充项目下拉选
     */
    function   queryAlldealName() {
        var  html ="";
        $.ajax({
            type: "POST",
            url: "${base}/alldealname",
            async:false,
            success: function(result){
                $.each(result,function (i,item) {
                    html += "<option value="+item+">"+item+"</option>";
                })
                $("#dealname").append(html);
            }
        });
    }

</script>
</body>
</html>