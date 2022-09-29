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
            {field: 'phone', title: '联系电话', align:'center'},
            {field: 'address', title: '联系地址', align:'center'},
            {field: 'contactMan', title: '联系人', align:'center'},
            {field: 'payMode', title: '支付方式', align:'center'},
            {field: 'state', title: '订单状态', align:'center'},
            {field: 'payDate', title: '支付日期',  align:'center'},
            {title: '操作', templet:'#payLogListBar',fixed:"right",align:"center", minWidth:150}
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
        var title="<h3>退款模块</h3>";
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