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
                    <input type="text" name="taxDescription"
                           class="layui-input
							searchVal" placeholder="税金描述" />
                </div>
                <div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="" >状态</option>
                        <option value="已选择" >已选择</option>
                        <option value="未选择" >未选择</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="taxCalculatorList" class="layui-table"  lay-filter="taxCalculatorLists">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="take">
                <i class="layui-icon">&#xe6af;</i>
                一键选择
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="cancel">
                <i class="layui-icon">&#xe69c;</i>
                一键取消
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="taxCalculatorListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="take" lay-event="take">选择</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="cancel">取消</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/taxCalculator/tax_showing.js"></script>
</body>
</html>