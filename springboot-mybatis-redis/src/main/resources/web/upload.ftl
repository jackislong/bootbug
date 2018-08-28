<!DOCTYPE html>
<html lang="en">
<#include 'include/header.ftl'/>
<body>
<#include 'include/nav.ftl'/>
<button class="layui-btn test" lay-data="{url: '${base}/testuploadimg/', accept: 'file'}">上传文件</button>
<script>
    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '.test', //绑定元素
            size: 1000,
            accept: 'file',
            size:'204800', //kb
             done: function (r) {
                 parent.layer.msg(r.msg);
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });

    });
</script>
</body>
</html>