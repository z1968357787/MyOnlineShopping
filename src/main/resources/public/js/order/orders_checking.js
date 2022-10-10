layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#orderList', // 表格绑定的ID
        url : ctx + '/order/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "orderListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "userId", title:'用户号',fixed:"true",hide:'true'},
            {field: 'productId', title: '商品号',align:"center"},
            {field: 'productName', title: '商品名',  align:'center'},
            {field: 'price', title: '单价', align:'center'},
            {field: 'quantity', title: '数量', align:'center'},
            {field: 'subtotal', title: '小计', align:'center'},
            {title: '操作', templet:'#orderListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('orderListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "productName":$("input[name='productName']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件
    table.on('toolbar(orderLists)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'pay':
                //alert("添加OK");
                payOrder(checkStatus.data);
                break;
            case 'del':
                //删除
                deleteOrder(checkStatus.data);
                break;

        };
    });


    /**
     * 删除
     */
    function  deleteOrder(data){
        if(data.length==0){
            layer.msg("请选择要删除的订单?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要删除这些订单吗？",{
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
                temp.productId=data[i].productId;
                list.push(temp);
            }
            //ids=1&ids=2
            console.log(list.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/order/delete_order",
                data : "list="+JSON.stringify(list),
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        tableIns.reload();
                    }else{
                        //删除失败的提醒
                        layer.msg(data.msg);
                    }
                }
            });
        });

    }

    function  payOrder(data){
        if(data.length==0){
            layer.msg("请选择要支付的订单?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要支付这些订单吗？",{
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
                temp.productId=data[i].productId;
                temp.productName=data[i].productName;
                temp.price=data[i].price;
                temp.quantity=data[i].quantity;
                temp.subtotal=data[i].subtotal;
                list.push(temp);
            }
            //ids=1&ids=2
            console.log(list.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/order/pay_cart",
                data : "list="+JSON.stringify(list),
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        tableIns.reload();
                        layui.layer.open({
                            title:"优惠券选择",
                            type:2,
                            content:ctx+"/discountUser/index",
                            dataType:"json",
                            area:["700px","400px"],
                            maxmin:true
                        });
                    }else{
                        //删除失败的提醒
                        layer.msg(data.msg);
                    }
                }
            });
        });

    }

    /**
     * 添加，更新的函数
     * @param userId
     * @param productId
     * @param payDate
     */

    function openAddOrUpdateOrderDialog(userId,productId){
        var title="<h3>订单模块-添加</h3>";
        var url=ctx+"/order/toAddOrUpdatePage";
        //判断

        if(userId!=null&productId!=null){
            title="<h3>订单模块-更新</h3>";
            url+="?userId="+userId+"&productId="+productId;
        }

        /*弹出层*/

        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","200px"],
            maxmin:true
        })

    }




    /*绑定行内工具栏*/
    //监听行工具事件

    table.on('tool(orderLists)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                var list=[];
                var temp={};
                temp.userId=data.userId;
                temp.productId=data.productId;
                list.push(temp);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/order/delete_order",
                    data : "list="+JSON.stringify(list),
                    dataType:"json",
                    success:function (data){
                        if(data.code==200){
                            layer.msg("删除OK");
                            tableIns.reload();
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                });
            });
        } else if(obj.event === 'edit'){
            //传入当前对象的id
            openAddOrUpdateOrderDialog(data.userId,data.productId);
        }
    });


});