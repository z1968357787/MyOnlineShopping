layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    /*添加表单添加信息*/
    form.on("submit(postSale)",function(data){
       // console.log(data.field);
        //提交的加载层
        var index=layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:true,
            shade:0.8
        });
        //提交数据url

        var list=[];
        var temp={};
        temp.userId=data.field.userId;
        temp.productId=data.field.productId;
        temp.contactId=data.field.contactId;
        temp.payDate=data.field.payDate;
        temp.state=data.field.state;
        list.push(temp);
        //发送ajax删除
        $.ajax({
            type:"post",
            url:ctx+"/payLog/processState",
            data : "list="+JSON.stringify(list),
            dataType:"json",
            success:function (data){
                if(data.code==200){
                    layer.msg("操作成功了");
                    //关闭加载层
                    layer.close(index);
                    //iframe
                    layer.closeAll("iframe");
                    parent.location.reload();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
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