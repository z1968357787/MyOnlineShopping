<!DOCTYPE html>
<html>
<head>
    <title>订单状态处理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="userId" class="layui-input searchVal" placeholder="用户ID" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="productName" class="layui-input searchVal" placeholder="商品名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="phone" class="layui-input searchVal" placeholder="手机号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="address" class="layui-input searchVal" placeholder="地址" />
                </div>
            </div>
        </form>
        <br>
        <br>
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="contactMan" class="layui-input searchVal" placeholder="联系人" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="payDate" name="payDate" placeholder="支付日期">
                </div>
                <div class="layui-input-inline">
                    <select name="state"  id="state">
                        <option value="">订单状态</option>
                        <option value="未完成">未完成</option>
                        <option value="已完成">已完成</option>
                        <option value="申请退款">申请退款</option>
                        <option value="允许退款">允许退款</option>
                        <option value="拒绝退款">拒绝退款</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="payLogList" class="layui-table"  lay-filter="payLogLists">
    </table>


    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal completeNews_btn" lay-event="complete">
                <i class="layui-icon">&#xe62f;</i>
                一键完成
            </a>
            <a class="layui-btn layui-btn-normal approveNews_btn" lay-event="approve">
                <i class="layui-icon">&#xe6af;</i>
                一键通过
            </a>
            <a class="layui-btn layui-btn-normal refuseNews_btn" lay-event="refuse">
                <i class="layui-icon">&#xe69c;</i>
                一键拒绝
            </a>
        </div>
    </script>



    <!--操作-->
    <script id="payLogListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="refund" lay-event="refund">处理状态</a>
        <!--<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>-->
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/postSale/post_sale.js"></script>
</body>
</html>