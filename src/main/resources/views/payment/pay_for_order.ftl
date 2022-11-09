<!DOCTYPE html>
<html>
<head>
    <title>支付订单</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label">总支付金额</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" lay-verify="required"
                               name="sumTotal" id="sumTotal"  value="${(moneyModel.sumTotal)!}元" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label">优惠后金额</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" lay-verify="required"
                               name="discountTotal" id="discountTotal"  value="${(moneyModel.discountTotal)!}元" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label">税后金额</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" lay-verify="required"
                               name="taxTotal" id="taxTotal"  value="${(moneyModel.taxTotal)!}元" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <label class="layui-form-label">零钱剩余</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" lay-verify="required"
                               name="balance" id="balance"  value="${(user.balance)!}元" readonly="readonly">
                    </div>
                </div>
            </div>
        </form>

    </blockquote>

    <!-- 数据表格 -->
    <table id="contactList" class="layui-table"  lay-filter="payment">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="change_btn">
                <i class="layui-icon">&#xe65e;</i>
                零钱支付
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="account_btn">
                <i class="layui-icon">&#xe659;</i>
                银行卡支付
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="credit_btn">
                <i class="layui-icon">&#xe67a;</i>
                信用卡支付
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加联系方式
            </a>
        </div>
    </script>


    <!--操作
    <script id="saleChanceListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>-->

</form>
<script type="text/javascript" src="${ctx}/js/payment/pay_for_order.js"></script>

</body>
</html>