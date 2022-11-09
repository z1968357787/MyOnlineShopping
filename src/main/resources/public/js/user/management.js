layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        dropdown=layui.dropdown,
        $ = layui.jquery,
        table = layui.table;

    var  tableIns = table.render({
        id:'userManagementTable',
        elem: '#userManagement', // 表格绑定的ID
        url : ctx + '/user/list', // 访问数据的地址
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
            {field: "id", title:'用户ID',fixed:"true"},
            {field: 'userName', title: '用户名',align:"center"},
            {field: 'trueName', title: '真是姓名',  align:'center'},
            {field: 'email', title: '邮箱', align:'center'},
            {field: 'phone', title: '手机号', align:'center'},
            {field: 'createDate', title: '创建时间',  align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {field: 'balance', title: '零钱', align:'center'},
            {field: 'role', title: '用户角色', align:'center'},
            //{field: 'count', title: '购买数量',edit:'text',align:'center'}
            {title: '操作', templet:'#userManagementListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });




    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('userManagementTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "userName":$("input[name='userName']").val(),
                "trueName": $("input[name='trueName']").val(),
                "role":$("#role").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件

    table.on('toolbar(userManagements)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'add':
                //alert("添加OK");
                openAddOrUpdateUser();
                break;
            case 'del':
                //删除
                deleteUsers(checkStatus.data);
                break;
        };
    });


    /**
     * 删除
     */
    function  deleteUsers(data){
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
                ids.push(data[i].id);
            }
            //ids=1&ids=2
            console.log(ids.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/user/delete",
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
     * @param id
     */
    function openAddOrUpdateUser(id){
        var title="<h3>用户模块-添加</h3>";
        var url=ctx+"/user/addOrUpdateUserPage";
        //判断
        if(id){
            title="<h3>用户模块-更新</h3>";
            url+="?id="+id;
        }

        /*弹出层*/
        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","580px"],
            maxmin:true
        })

    }

    /*绑定行内工具栏*/
    //监听行工具事件
    table.on('tool(userManagements)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/user/delete",
                    data:{"ids":data.id},
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
            openAddOrUpdateUser(data.id);
        }
    });

});