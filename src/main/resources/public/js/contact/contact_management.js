layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#contactList', // 表格绑定的ID
        url : ctx + '/contact/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "contactListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "contactId", title:'联系id',fixed:"true",align:"center"},
            {field: 'phone', title: '手机号',align:"center"},
            {field: 'address', title: '地址',  align:'center'},
            {field: 'contactMan', title: '联系人姓名', align:'center'},
            {field: 'userId', title: '用户id',  align:'center',hide:'true'},
            {title: '操作', templet:'#contactListBar',fixed:"right",align:"center", minWidth:150}
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

    /**
     * 格式化开发状态
     *  0 - 未开发
     *  1 - 开发中
     *  2 - 开发成功
     *  3 - 开发失败
     * @param value
     * @returns {string}
     */

    /*选择元素绑定事件*/

    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('contactListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "phone":$("input[name='phone']").val(),
                "address": $("input[name='address']").val(),
                "contactMan": $("input[name='contact_man']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件
    table.on('toolbar(contacts)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'add':
                //alert("添加OK");
                openAddOrUpdateContact();
                break;
            case 'del':
                //删除
                deleteContacts(checkStatus.data);
                break;

        };
    });


    /**
     * 删除
     */
    function  deleteContacts(data){
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
                ids.push(data[i].contactId);
            }
            //ids=1&ids=2
            console.log(ids.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/contact/delete",
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
     * @param contactId
     */
    function openAddOrUpdateContact(contactId){
        var title="<h3>联系方式模块-添加</h3>";
        var url=ctx+"/contact/addOrUpdateContactPage";
        //判断
        if(contactId){
            title="<h3>联系方式模块-更新</h3>";
            url+="?contactId="+contactId;
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
    table.on('tool(contacts)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/contact/delete",
                    data:{"ids":data.contactId},
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
            openAddOrUpdateContact(data.contactId);
        }
    });


});