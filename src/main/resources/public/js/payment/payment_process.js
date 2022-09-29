layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    /*添加表单添加信息*/
    form.on("submit(submitBtn)",function(data){
       // console.log(data.field);
        //提交的加载层
        var index=layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:true,
            shade:0.8
        });
        //提交数据url
        var url=ctx+"/payment/makePayment";
        //发送ajax添加
       $.post(url,data.field,function(result){
           if(result.code==200){
                //添加成功了
               layer.msg("支付成功了");
               //关闭加载层
               layer.close(index);
               //iframe
               layer.closeAll("iframe");
               //重新加载
               //parent.location.reload();
               parent.parent.location.reload()
               if(data.field.isOrder==0){

                   window.parent.location.href=ctx+"/product_description/index";
               }
               else{
                   window.parent.location.href=ctx+"/order/index";
               }

           }else{
               //失败了
               layer.msg(result.msg);
               if(data.field.isOrder==0){
                   window.parent.location.href=ctx+"/product_description/index";
               }
               else{
                   window.parent.location.href=ctx+"/order/index";
               }
           }
       },"json");
        //阻止跳转
        return false;
    });
    /*取消*/
    $("#closeBtn").click(function (){
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
});