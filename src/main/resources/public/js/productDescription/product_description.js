layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        dropdown=layui.dropdown,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 营销机会列表展示
     */



    var  tableIns = table.render({
        id:'productDescriptionTable',
        elem: '#productDescription', // 表格绑定的ID
        url : ctx + '/product_description/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        //可选分页数量
        limits : [10,15,20,25],
        //默认分页数量
        limit : 10,
        toolbar: "#toolbarDemo",
        //id : "saleChanceListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "productId", title:'商品编号',fixed:"true"},
            {field: 'productName', title: '商品名',align:"center"},
            {field: 'productDescription', title: '商品描述',  align:'center'},
            {field: 'price', title: '单价', align:'center'},
            {field: 'classification', title: '分类', align:'center'},
            {field: 'supplier', title: '供应商',  align:'center'},
            {field: 'listTime', title: '上架时间', align:'center'},
            {field: 'stock', title: '库存', align:'center'},
            {field: 'count', title: '购买数量',edit:'text',align:'center'}
            //{title: '操作', templet:'#productDescriptionListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    /**
     * 格式化分配状态
     *  0 - 未分配
     *  1 - 已分配
     *  其他 - 未知
     * @param state
     * @returns {string}
     */
    /*
    function formatterState(state){
        if(state==0) {
            return "<div style='color: yellow'>未分配</div>";
        } else if(state==1) {
            return "<div style='color: green'>已分配</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }

    /**
     * 格式化开发状态
     *  0 - 未开发
     *  1 - 开发中
     *  2 - 开发成功
     *  3 - 开发失败
     * @param value
     * @returns {string}
     */
    /*
    function formatterDevResult(value){
        if(value == 0) {
            return "<div style='color: yellow'>未开发</div>";
        } else if(value==1) {
            return "<div style='color: #00FF00;'>开发中</div>";
        } else if(value==2) {
            return "<div style='color: #00B83F'>开发成功</div>";
        } else if(value==3) {
            return "<div style='color: red'>开发失败</div>";
        } else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }

    /*选择元素绑定事件*/


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('productDescriptionTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "productName":$("input[name='productName']").val(),
                "productDescription": $("input[name='productDescription']").val(),
                "classification":$("#classification").val(),
                "supplier": $("input[name='supplier']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件

    table.on('toolbar(productDescriptions)', function(obj){
        var checkStatus = table.checkStatus("productDescriptionTable");
        console.log(checkStatus.data)
        switch(obj.event){
            case 'add':
                //alert("添加OK");
                layer.msg("操作成功");
                addOrder(checkStatus.data);
                break;
            case 'pay':
                //删除
                payForOrder(checkStatus.data);
                break;
        };
    });
    /**
     * 删除
     */

    function  payForOrder(data){
        if(data.length==0){
            layer.msg("请选择要购买的商品?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要购买这些商品吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var list=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                var temp={};
                temp.productId=data[i].productId;
                temp.productName=data[i].productName;
                temp.price=data[i].price;
                temp.quantity=data[i].count;
                temp.stock=data[i].stock;
                list.push(temp);
            }
            //发送ajax请求
            $.ajax({
                type:"post",
                url:ctx+"/order/pay_order",
                data : "list="+JSON.stringify(list),
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        layer.msg("操作成功");
                        tableIns.reload();
                        //$.cookie("userIdStr",result.result.userIdStr);
                        //$.cookie("total",result.result.total);

                        window.location.href=ctx+"/payment/toPayPage";
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
     * @param saleChanceId
     */
    /*
    function openAddOrUpdateSaleChanceDialog(saleChanceId){
        var title="<h3>营销模块-添加</h3>";
        var url=ctx+"/sale_chance/addOrUpdateSaleChancePage";
        //判断
        if(saleChanceId){
            title="<h3>营销模块-更新</h3>";
            url+="?saleChanceId="+saleChanceId;
        }

        /*弹出层*/
    /*
        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","620px"],
            maxmin:true
        })

    }*/
    function addOrder(data){
        if(data.length==0){
            layer.msg("请选择要购买的商品?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要购买这些商品吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var list=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                var temp={};
                temp.productId=data[i].productId;
                temp.productName=data[i].productName;
                temp.price=data[i].price;
                temp.quantity=data[i].count;
                temp.stock=data[i].stock;
                list.push(temp);
            }
            //发送ajax请求
            $.ajax({
                type:"post",
                url:ctx+"/order/add_order",
                data : "list="+JSON.stringify(list),
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        //tableIns.reload();
                        parent.location.reload();
                    }else{
                        //删除失败的提醒
                        layer.msg(data.msg);
                    }
                }
            });
        });
    }

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

    /*绑定行内工具栏*/
    //监听行工具事件

    /*table.on('tool(productDescriptions)', function(obj){

    });*/





});