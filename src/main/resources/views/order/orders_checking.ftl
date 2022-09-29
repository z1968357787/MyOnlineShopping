<!DOCTYPE html>
<html>
<head>
    <title>查看购物车</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="productName"
                           class="layui-input
							searchVal" placeholder="商品名" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="orderList" class="layui-table"  lay-filter="orderLists">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="pay">
                <i class="layui-icon">&#xe65e;</i>
                支付
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe67e;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="orderListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/order/orders_checking.js"></script>
</body>
</html>