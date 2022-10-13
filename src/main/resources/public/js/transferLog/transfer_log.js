layui.use(['table','layer','laydate'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        dropdown=layui.dropdown,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate;
    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        id:'transferLogTable',
        elem: '#transferLog', // 表格绑定的ID
        url : ctx + '/transferLog/list', // 访问数据的地址
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
            {field: "userId", title:'用户id',fixed:"true",hide:"true"},
            {field: 'accountNumber', title: '银行卡号',align:"center"},
            {field: 'cardHolder', title: '持卡人',  align:'center'},
            {field: 'money', title: '转账金额',  align:'center'},
            {field: 'transferMode', title: '转账方式', align:'center'},
            {field: 'transferDate', title: '转账时间', align:'center'}
        ]]
    });

    laydate.render({
        elem: '#transferDate'
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
        table.reload('transferLogTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "transferDate":$("input[name='transferDate']").val(),
                "transferMode":$("#classification").val(),
                "cardHolder":$("input[name='cardHolder']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件

    table.on('toolbar(transferLogs)', function(obj){
        //var checkStatus = table.checkStatus(obj.config.id);
        var select;
        switch(obj.event){
            case 'cash_in':
                select=0;
                break;
            case 'cash_out':
                select=1;
                break;
        };
        openTransferPage(select);
    });

    function openTransferPage(select){
        var title;
        var transferMode
        var url=ctx+"/transferLog/openTransferPage";
        //判断
        if(select==0){
            title="<h3>零钱充值</h3>";
            transferMode='充值';
        }else {
            title="<h3>零钱提现</h3>";
            transferMode='提现';
        }
        url+="?transferMode="+transferMode;

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