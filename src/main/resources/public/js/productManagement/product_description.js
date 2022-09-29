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
            //{field: 'count', title: '购买数量',edit:'text',align:'center'}
            {title: '操作', templet:'#productDescriptionListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });




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
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'add':
                //alert("添加OK");
                openAddOrUpdateProduct();
                break;
            case 'del':
                //删除
                deleteProducts(checkStatus.data);
                break;
            case 'addClassification':
                var title="<h3>分类模块-添加</h3>";
                var url=ctx+"/classification/toAddClassificationPage";
                openClassificationManagement(title,url);
                break;
            case 'updateClassification':
                var title="<h3>分类模块-更新</h3>";
                var url=ctx+"/classification/toUpdateClassificationPage";
                openClassificationManagement(title,url);
                break;
            case 'deleteClassification':
                var title="<h3>分类模块-删除</h3>";
                var url=ctx+"/classification/toDeleteClassificationPage";
                openClassificationManagement(title,url);
                break;
        };
    });


    /**
     * 删除
     */
    function  deleteProducts(data){
        if(data.length==0){
            layer.msg("请选择要删除的数据?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要删除这些数据吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var ids=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                ids.push(data[i].productId);
            }
            //ids=1&ids=2
            console.log(ids.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/product_description/delete",
                data:{"ids":ids.toString()},
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
     * @param productId
     */
    function openAddOrUpdateProduct(productId){
        var title="<h3>商品模块-添加</h3>";
        var url=ctx+"/product_description/addOrUpdateProductPage";
        //判断
        if(productId){
            title="<h3>商品模块-更新</h3>";
            url+="?productId="+productId;
        }

        /*弹出层*/
        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","620px"],
            maxmin:true
        })

    }




    /*绑定行内工具栏*/
    //监听行工具事件
    table.on('tool(productDescriptions)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/product_description/delete",
                    data:{"ids":data.productId},
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
            openAddOrUpdateProduct(data.productId);
        }
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

    function openClassificationManagement(title,url){
        //判断
        /*弹出层*/
        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","500px"],
            maxmin:true
        })

    }




});