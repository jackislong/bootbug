<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item "><a href="${base}/showbug">bug列表</a></li>
    <li class="layui-nav-item"><a href="${base}/upload">文件上传</a></li>
    <li class="layui-nav-item"><a href="${base}/bugana">bug分析</a></li>
</ul>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        //监听导航点击
        element.on('nav(demo)', function(elem){
        });
    });
</script>