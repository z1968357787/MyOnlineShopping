layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    /*添加表单添加信息*/
    form.on("submit(updateClassification)",function(data){
       // console.log(data.field);add_classification.js
        //提交的加载层
        var index=layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:true,
            shade:0.8
        });
        //提交数据url
        var url=ctx+"/classification/update";

        //发送ajax添加
       $.post(url,data.field,function(data){
           if(data.code==200){
                //添加成功了
               layer.msg("操作成功了");
               //关闭加载层
               layer.close(index);
               //iframe
               layer.closeAll("iframe");
               //重新加载
               parent.location.reload();
           }else{
               //失败了
               layer.msg(data.msg);
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

    $(function(){
        /*发送ajax追加下拉框*/
        /*当前营销机会分配给具体人的ID*/
        $.get(ctx+"/classification/queryAllClassifications",function(data){
            //遍历
            for (var x in data) {
                $("#oldClassification").append("<option value='"+data[x].classification+"'>"+data[x].classification+"</option>");
            }
            //重新渲染select
            layui.form.render("select");
        },"json");


    });

});