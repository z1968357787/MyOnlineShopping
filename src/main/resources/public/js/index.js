layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(login)', function(data){
        //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        /*发送ajax请求，发送用户姓名与密码，执行用户登录操作*/
        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            success:function (result){ //success是回调函数，用于返回后端发来的数据
                console.log(result)
                if(result.code==200){
                    //判断记住密码是否被选中，如果选中，则设置cookie 7天生效

                    //设置用户为登录状态
                    layer.msg("登陆成功!",function (){

                        if($("#rememberMe").prop("checked")){
                            $.cookie("userIdStr",result.result.userIdStr,{expires:7});
                            $.cookie("userName",result.result.userName,{expires:7});
                            $.cookie("trueName",result.result.trueName,{expires:7});
                        }else {
                            $.cookie("userIdStr",result.result.userIdStr);
                            $.cookie("userName",result.result.userName);
                            $.cookie("trueName",result.result.trueName);
                        }
                        window.location.href=ctx+"/main";
                    })
                }else {
                    layer.msg(result.msg, {icon:5})
                }
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    
});