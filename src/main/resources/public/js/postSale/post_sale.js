layui.use(['table','layer','laydate'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        laydate=layui.laydate;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#payLogList', // 表格绑定的ID
        url : ctx + '/payLog/list2', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "payLogListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "userId", title:'用户ID',fixed:"true"},
            {field: 'productId', title: '商品ID',align:"center",hide:'true'},
            {field: 'contactId', title: '联系ID',align:"center",hide:'true'},
            {field: 'productName', title: '商品名',  align:'center'},
            {field: 'price', title: '单价', align:'center'},
            {field: 'quantity', title: '数量', align:'center'},
            {field: 'subtotal', title: '小计', align:'center'},
            {field: 'discountTotal', title: '优惠后总额', align:'center'},
            {field: 'discountDescription', title: '优惠方式',  align:'center'},
            {field: 'taxTotal', title: '税后总额', align:'center'},
            {field: 'taxDescription', title: '税金计算描述', align:'center'},
            {field: 'phone', title: '联系电话', align:'center'},
            {field: 'address', title: '联系地址', align:'center'},
            {field: 'contactMan', title: '联系人', align:'center'},
            {field: 'refundReason', title: '退款原因', align:'center'},
            {field: 'state', title: '订单状态', align:'center'},
            {field: 'payDate', title: '支付日期',  align:'center'},
            {title: '操作', templet:'#payLogListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('payLogListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "userId":$("input[name='userId']").val(),
                "productName":$("input[name='productName']").val(),
                "phone":$("input[name='phone']").val(),
                "address":$("input[name='address']").val(),
                "state":$("#state").val(),
                "payDate":$("input[name='payDate']").val(),
                "contactMan":$("input[name='contactMan']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });


    laydate.render({
        elem: '#payDate'
    });

    table.on('toolbar(payLogLists)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data);
        var state;
        switch(obj.event){
            case 'complete':
                //alert("添加OK");
                state='已完成';
                setState(checkStatus.data,state);
                break;
            case 'approve':
                //删除
                state='允许退款';
                setState(checkStatus.data,state);
                break;
            case 'refuse':
                //删除
                state='拒绝退款';
                setState(checkStatus.data,state);
                break;

        };
    });

    function  setState(data,state){
        if(data.length==0){
            layer.msg("请选择要"+state+"的订单?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要"+state+"这些订单吗？",{
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
                temp.contactId=data[i].contactId;
                temp.payDate=data[i].payDate;
                temp.state=state;
                list.push(temp);
            }
            //ids=1&ids=2
            console.log(list.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/payLog/processState",
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


    /**
     * 添加，更新的函数
     * @param userId
     * @param productId
     * @param payDate
     */

    function openRefundDialog(userId,productId,contactId,payDate){
        var title="<h3>订单状态处理</h3>";
        var url=ctx+"/payLog/toProcessStatePage"+"?userId="+userId+"&productId="+productId+"&contactId="+contactId+"&payDate="+payDate;
        //判断

        /*弹出层*/

        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","250px"],
            maxmin:true
        })

    }




    /*绑定行内工具栏*/
    //监听行工具事件

    table.on('tool(payLogLists)', function(obj){
        var data = obj.data;
        if(obj.event === 'refund'){
            //传入当前对象的id
            openRefundDialog(data.userId,data.productId,data.contactId,data.payDate);
        }
    });


});