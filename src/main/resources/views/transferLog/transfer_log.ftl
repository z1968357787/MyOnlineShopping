<!DOCTYPE html>
<html>
<head>
    <title>零钱转账</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="transferDate" name="transferDate" placeholder="转账日期">
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="cardHolder" class="layui-input searchVal" placeholder="持卡人姓名" />
                </div>

                <div class="layui-input-inline">
                    <select name="transferMode"  id="transferMode">
                        <option value="">转账类型</option>
                        <option value="充值">充值</option>
                        <option value="提现">提现</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="transferLog" class="layui-table"  lay-filter="transferLogs">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="cash_in">
                <i class="layui-icon">&#xe659;</i>
                零钱充值
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="cash_out">
                <i class="layui-icon">&#xe65e;</i>
                零钱提现
            </a>
        </div>

    </script>


    <!--操作
    <script id="saleChanceListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>-->

</form>

<script type="text/javascript" src="${ctx}/js/transferLog/transfer_log.js"></script>
</body>
</html>