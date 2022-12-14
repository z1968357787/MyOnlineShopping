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
        url : ctx + '/payLog/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "payLogListTable",
        cols : [[
            {field: "userId", title:'用户ID',fixed:"true",hide:'true'},
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
            {field: 'payMode', title: '支付方式', align:'center'},
            {field: 'state', title: '订单状态', align:'center'},
            {field: 'payDate', title: '支付日期',  align:'center'},
            {field: 'score', title: '订单评分', align:'center'},
            {field: 'evaluation', title: '订单评价',  align:'center'},
            {title: '操作', templet:'#payLogListBar',align:"center", minWidth:180}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('payLogListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "productName":$("input[name='productName']").val(),
                "phone":$("input[name='phone']").val(),
                "address":$("input[name='address']").val(),
                "state":$("#state").val(),
                "payMode":$("#payMode").val(),
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


    /**
     * 添加，更新的函数
     * @param userId
     * @param productId
     * @param payDate
     */

    function openRefundDialog(userId,productId,contactId,payDate){
        var title="<h3>退款申请</h3>";
        var url=ctx+"/payLog/toRefundPage"+"?userId="+userId+"&productId="+productId+"&contactId="+contactId+"&payDate="+payDate;
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

    function openEvaluationDialog(userId,productId,contactId,payDate){
        var title="<h3>订单评价</h3>";
        var url=ctx+"/payLog/toEvaluationPage"+"?userId="+userId+"&productId="+productId+"&contactId="+contactId+"&payDate="+payDate;
        //判断

        /*弹出层*/

        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","280px"],
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
        }else if (obj.event==='evaluation'){
            openEvaluationDialog(data.userId,data.productId,data.contactId,data.payDate)
        }else if(obj.event==='cancel'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                var list=[];
                var temp={};
                temp.userId=data.userId;
                temp.productId=data.productId;
                temp.contactId=data.contactId;
                temp.payDate=data.payDate;
                list.push(temp);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/payLog/deleteEvaluation",
                    data : "list="+JSON.stringify(list),
                    dataType:"json",
                    success:function (data){
                        if(data.code==200){
                            layer.msg("删除OK");
                            tableIns.reload();
                            parent.location.reload()
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                });
            });
        }
    });



});