<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item "><a href="${base}/showbug">bug列表</a></li>
    <li class="layui-nav-item"><a href="${base}/upload">文件上传</a></li>
    <li class="layui-nav-item"><a href="${base}/bugana">bug分析</a></li>
    <li class="layui-nav-item"><a href="${base}/personana">个人BUG汇总</a></li>
</ul>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        //监听导航点击
        element.on('nav(demo)', function(elem){
        });
    });
    /**
     * 获取指定日期的周的第一天、月的第一天、季的第一天、年的第一天
     * @param date new Date()形式，或是自定义参数的new Date()
     * @returns 返回值为格式化的日期，yy-mm-dd
     */
    //日期格式化，返回值形式为yy-mm-dd
    function timeFormat(date) {
        if (!date || typeof(date) === "string") {
            this.error("参数异常，请检查...");
        }
        var y = date.getFullYear(); //年
        var m = date.getMonth() + 1; //月
        if(m<11){
            m='0'+m;
        }
        var d = date.getDate(); //日
        return y + "-" + m + "-" + d;
    }

    //获取这周的周一
    function getFirstDayOfWeek (date) {
        var weekday = date.getDay()||7; //获取星期几,getDay()返回值是 0（周日） 到 6（周六） 之间的一个整数。0||7为7，即weekday的值为1-7
        date.setDate(date.getDate()-weekday+1);//往前算（weekday-1）天，年份、月份会自动变化
        return timeFormat(date);
    }
</script>