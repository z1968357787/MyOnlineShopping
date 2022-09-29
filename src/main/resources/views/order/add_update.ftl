<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="userId" value="${(order.userId)!}">
    <#--设置营销人员的ID-->
    <input type="hidden" name="productId" value="${(order.productId)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">数量</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="quantity" id="quantity"  value="${(order.quantity)!}" placeholder="请输入商品数量">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateOrder">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/order/add_update.js"></script>
</body>
</html>