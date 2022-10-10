layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#discountList', // 表格绑定的ID
        url : ctx + '/discount/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "discountListTable",
        cols : [[
            //{type: "checkbox", fixed:"center"},
            //{field: "userId", title:'用户号',fixed:"true",hide:'true'},
            {field: 'discountId', title: '优惠券号',align:"center"},
            {field: 'discountDescription', title: '优惠方式',  align:'center'},
            //{field: 'count', title: '数量', align:'center'}
            {title: '操作', templet:'#discountListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('discountListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "discountDescription":$("input[name='discountDescription']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    table.on('tool(discountLists)', function(obj){
        var data = obj.data;
        //console.log(obj)
        //传入当前对象的id
        assignDiscount(data.discountId,data.discountDescription);

    });

    function assignDiscount(discountId,discountDescription){
        var title="<h3>优惠券发配</h3>";
        var url=ctx+"/discount/assignDiscountPage"+"?discountId="+discountId+"&discountDescription="+discountDescription;
        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","320px"],
            maxmin:true
        })

    }


});