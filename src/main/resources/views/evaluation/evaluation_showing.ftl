<!DOCTYPE html>
<html>
<head>
    <title>查看历史订单</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="userName" class="layui-input searchVal" placeholder="用户名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="productName" class="layui-input searchVal" placeholder="商品名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="supplier" class="layui-input searchVal" placeholder="供应商" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="evaluation" class="layui-input searchVal" placeholder="订单评价" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="evaluationList" class="layui-table"  lay-filter="evaluationLists">
    </table>


</form>

<script type="text/javascript" src="${ctx}/js/evaluation/evaluation_showing.js"></script>
</body>
</html>