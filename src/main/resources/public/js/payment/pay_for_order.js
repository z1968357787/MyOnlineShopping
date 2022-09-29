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
            {type: "radio", fixed:"center"},
            {field: "contactId", title:'联系id',fixed:"true",align:"center"},
            {field: 'phone', title: '手机号',align:"center"},
            {field: 'address', title: '地址',  align:'center'},
            {field: 'contactMan', title: '联系人姓名', align:'center'},
            {field: 'userId', title: '用户id',  align:'center',hide:'true'},
        ]]
    });

    table.on('toolbar(payment)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        var data=checkStatus.data;
        var title;
        var url;
        switch(obj.event){
            case 'change_btn':
                title="<h3>零钱支付</h3>";
                url=ctx+"/payment/changePayment";
                openPayPage(title,url,data);
                break;
            case 'account_btn':
                title="<h3>银行卡支付</h3>";
                url=ctx+"/payment/accountPayment";
                openPayPage(title,url,data);
                break;
            case 'credit_btn':
                title="<h3>信用卡支付</h3>";
                url=ctx+"/payment/creditPayment";
                openPayPage(title,url,data);
                break;
            case 'add':
                //alert("添加OK");
                openAddOrUpdateContact();
                break;

        };

    });

    function openPayPage(title,url,data){

        if(data.length==0){
            layer.msg("请选择地址", {icon:5})
        }
        else {
            var contactId=data[0].contactId;
            url+="?contactId="+contactId;
            layui.layer.open({
                title:title,
                type:2,
                content:url,
                area:["500px","620px"],
                maxmin:true
            })

        }
    }

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


});