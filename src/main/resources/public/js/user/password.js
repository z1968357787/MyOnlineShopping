layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    form.on('submit(saveBtn)', function(data){
        //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        /*发送ajax请求，发送用户姓名与密码，执行用户登录操作*/
        $.ajax({
            type:"post",
            url:ctx+"/user/updatePwd",
            data:{
                oldPassword:data.field.old_password,
                newPassword:data.field.new_password,
                repeatPassword:data.field.again_password
            },
            success:function (result){ //success是回调函数，用于返回后端发来的数据
                console.log(result)
                if(result.code==200){
                    //设置用户为登录状态
                    layer.msg("用户密码修改成功，系统将在3秒后退出!",function (){
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/OnlineShopping"});
                        $.removeCookie("userName",{domain:"localhost",path:"/OnlineShopping"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/OnlineShopping"});

                        //父窗口跳转
                        window.parent.location.href=ctx+"/index";
                    })
                }else {
                    layer.msg(result.msg, {icon:5})
                }
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});