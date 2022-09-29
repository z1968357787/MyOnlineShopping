layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function (){

        //eg1
        layer.confirm('你确定要退出系统吗?', {icon: 3, title:'系统提示'}, function(index){
            //do something

            //关闭询问框
            layer.close(index);

            $.removeCookie("userIdStr",{domain:"localhost",path:"/OnlineShopping"});
            $.removeCookie("userName",{domain:"localhost",path:"/OnlineShopping"});
            $.removeCookie("trueName",{domain:"localhost",path:"/OnlineShopping"});

            //父窗口跳转
            window.parent.location.href=ctx+"/index";
        });
    })
});