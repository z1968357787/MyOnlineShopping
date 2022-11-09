layui.use(['table','layer','laydate'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        laydate=layui.laydate;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#evaluationList', // 表格绑定的ID
        url : ctx + '/evaluation/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "evaluationListTable",
        cols : [[
            {field: "userId", title:'用户ID',fixed:"true"},
            {field: 'userName', title: '用户名',align:"center"},
            {field: 'productId', title: '商品ID',align:"center"},
            {field: 'productName', title: '商品名',  align:'center'},
            {field: 'supplier', title: '供应商', align:'center'},
            {field: 'score', title: '订单评分', align:'center'},
            {field: 'evaluation', title: '订单评价',  align:'center'},
            {field: 'payDate', title: '订单日期',  align:'center'}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('evaluationListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "userName":$("input[name='userName']").val(),
                "productName":$("input[name='productName']").val(),
                "supplier":$("input[name='supplier']").val(),
                "evaluation":$("input[name='evaluation']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });


    laydate.render({
        elem: '#payDate'
    });

});