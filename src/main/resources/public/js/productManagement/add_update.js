layui.use(['form', 'layer','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate=layui.laydate;


    /*添加表单添加信息*/
    form.on("submit(addOrUpdateProduct)",function(data){
       // console.log(data.field);
        //提交的加载层
        var index=layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:true,
            shade:0.8
        });
        //提交数据url
        var url=ctx+"/product_description/save";
        //判断，当前页面的隐藏域有数据，说明做修改操作
        if($("input[name='productId']").val()){
            url=ctx+"/product_description/update";
        }
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

    laydate.render({
        elem: '#listTime'
        ,type: 'datetime'
        ,trigger: 'click'//呼出事件改成click
    });

    $(function(){
        /*发送ajax追加下拉框*/
        /*当前营销机会分配给具体人的ID*/
        var classification= $("input[name='classification']").val();
        $.get(ctx+"/classification/queryAllClassifications",function(data){
            //遍历
            for (var x in data) {
                if(classification==data[x].classification){
                    $("#classification").append("<option value='"+data[x].classification+"' selected>"+data[x].classification+"</option>");
                }else{
                    $("#classification").append("<option value='"+data[x].classification+"'>"+data[x].classification+"</option>");
                }
            }
            //重新渲染select
            layui.form.render("select");
        },"json");


    });

});