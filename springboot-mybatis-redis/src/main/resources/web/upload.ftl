<!DOCTYPE html>
<html lang="en">
<#include 'include/header.ftl'/>
<body>
<#include 'include/nav.ftl'/>
<button class="layui-btn test" lay-data="{url: '${base}/testuploadimg/', accept: 'file'}">上传文件</button>
<script>
    layui.use('upload', function () {
        var index ;
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '.test', //绑定元素
            size: 1000,
            accept: 'file',
            size:'204800', //kb
            before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                index = layer.load(2); //上传loading
            },
             done: function (r) {
                 layer.close(index);
                 parent.layer.msg(r.msg);
            },
            error: function (r) {
                layer.close(index);
                layer.msg(r.msg);
            }
        });

    });
</script>
</body>
</html>