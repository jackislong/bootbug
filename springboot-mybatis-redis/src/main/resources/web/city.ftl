<!DOCTYPE html>
<html lang="en">
<#assign base=request.contextPath />
<head>
    <link href="${base}/static/layui/css/layui.css" rel="stylesheet">
    <script  src="${base}/static/layui/layui.js"></script>
</head>
<body>


<table id="city" lay-filter="test"></table>

<script>
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#city'
            ,height: 315
            ,url: '${base}/showcity/' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'name', title: '城市名称', width:200}
                ,{field: 'countrycode', title: '国家代码', width:200, sort: true}
                ,{field: 'district', title: '描述', width:200}
                ,{field: 'population', title: '邮编', width: 200}
            ]]
        });

    });
</script>
</body>
</html>