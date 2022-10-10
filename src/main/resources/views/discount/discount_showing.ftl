<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="discountDescription"
                           class="layui-input
							searchVal" placeholder="优惠券名" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="discountList" class="layui-table"  lay-filter="discountLists">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <!--<a class="layui-btn layui-btn-normal addNews_btn" lay-event="take">
                <i class="layui-icon">&#xe65e;</i>
                选择
            </a>-->
        </div>
    </script>


    <!--操作-->
    <script id="discountListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="assign" lay-event="assign">发配</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/discount/discount_showing.js"></script>
</body>
</html>