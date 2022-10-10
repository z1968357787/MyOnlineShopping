layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#discountUserList', // 表格绑定的ID
        url : ctx + '/discountUser/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "discountUserListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "userId", title:'用户号',fixed:"true",hide:'true'},
            {field: 'discountId', title: '优惠券号',align:"center"},
            {field: 'discountDescription', title: '优惠方式',  align:'center'},
            {field: 'count', title: '数量', align:'center'}
            //{title: '操作', templet:'#orderListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('discountUserListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "discountDescription":$("input[name='discountDescription']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件
    table.on('toolbar(discountUserLists)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'take':
                //alert("添加OK");
                takeDiscount(checkStatus.data);
                break;
        };
    });

    function  takeDiscount(data){
        /*if(data.length==0){
            layer.msg("请选择要支付的订单?");
            return ;
        }*/

        //发送ajax删除
        layer.confirm("你确定要选择此优惠券吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var list=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                var temp={};
                temp.userId=data[i].userId;
                temp.discountId=data[i].discountId;
                temp.discountDescription=data[i].discountDescription;
                temp.count=data[i].count;
                list.push(temp);
            }
            var index=layer.msg("数据提交中，请稍后...",{
                icon:16,
                time:true,
                shade:0.8
            });
            $.ajax({
                type:"post",
                url:ctx+"/discountUser/takeDiscounts",
                data : "list="+JSON.stringify(list),
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        tableIns.reload();
                        layer.msg("选择成功了");
                        layer.close(index);
                        //iframe
                        layer.closeAll("iframe");
                        window.parent.location.href=ctx+"/payment/toPayPage";
                    }else{
                        //删除失败的提醒
                        layer.msg(data.msg);
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                    }
                }
            });
        });

    }


});