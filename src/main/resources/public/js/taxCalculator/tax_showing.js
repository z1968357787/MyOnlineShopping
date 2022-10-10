layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#taxCalculatorList', // 表格绑定的ID
        url : ctx + '/taxCalculator/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "taxCalculatorListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: 'taxId', title: '税金计算机号',align:"center"},
            {field: 'taxDescription', title: '税金描述',  align:'center'},
            {field: 'state', title: '选择状态', align:'center'},
            {title: '操作', templet:'#taxCalculatorListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });


    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('taxCalculatorListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "taxDescription":$("input[name='taxDescription']").val(),
                "state":$("#state").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件
    table.on('toolbar(taxCalculatorLists)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data);
        var url;
        switch(obj.event){
            case 'take':
                url=ctx+"/taxCalculator/take";
                break;
            case 'cancel':
                url=ctx+"/taxCalculator/cancel";
                break;
        };
        takeTaxCalculator(checkStatus.data,url);
    });

    function  takeTaxCalculator(data,url){
        if(data.length==0){
            layer.msg("请选择税金计算器?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要选择此操作吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var ids=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                ids.push(data[i].taxId);
            }
            //ids=1&ids=2
            console.log(ids.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:url,
                data:{"ids":ids.toString()},
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        layer.msg("设置OK");
                        tableIns.reload();
                    }else{
                        //删除失败的提醒
                        layer.msg(data.msg);
                    }
                }
            });
        });
    }

    table.on('tool(taxCalculatorLists)', function(obj){
        var data = obj.data;
        //console.log(obj)
        var url;
        if(obj.event === 'take'){
            url=ctx+"/taxCalculator/take";
        } else if(obj.event === 'cancel'){
            url=ctx+"/taxCalculator/cancel";
        }
        layer.confirm('确定要选择此操作吗?', function(index){
            //关闭弹出层
            layer.close(index);
            //发送ajax删除
            $.ajax({
                type:"post",
                url:url,
                data:{"ids":data.taxId},
                success:function (data){
                    if(data.code==200){
                        layer.msg("设置OK");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
    });


});