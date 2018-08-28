<!DOCTYPE html>
<html lang="en">
<#include 'include/header.ftl'/>
<body>
<#include 'include/nav.ftl'/>
<div class="demoTable">
    处理人：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
    </div>
    检测时间：
    <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
        <input type="text" class="layui-input" id="startdate">
    </div>
    ——
    <div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
        <input type="text" class="layui-input" id="enddate">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>
<table id="demo" lay-filter="test"></table>
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

    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
             elem: '#demo'
            ,height: 460
            ,url: '${base}/buglist/' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'bugno', title: 'bug编号', width:100, sort: true, fixed: 'left'}
                ,{field: 'proname', title: '项目名称', width:200}
                ,{field: 'comment', title: '备注', width:200}
                ,{field: 'state', title: '状态', width:200}
                ,{field: 'dealname', title: '处理人', width: 200}
                ,{field: 'grade', title: '等级', width: 200, sort: true}
                ,{field: 'checker', title: '检测人', width: 200, sort: true}
                ,{field: 'detectiontime', title: '检测时间', width: 200,sort: true}
                ,{field: 'updatetime', title: '修改时间', width: 200, sort: true}
            ]],
            id: 'testReload'
        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                var startdate = $('#startdate');
                var enddate = $('#enddate');

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        checker: demoReload.val(),
                        startdate: startdate.val(),
                        enddate: enddate.val()
                    }
                });
            }
        };
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>