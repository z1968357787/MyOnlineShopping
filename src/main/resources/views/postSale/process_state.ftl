<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

    <input type="hidden" name="userId" value="${(userId)!}">
    <#--设置营销人员的ID-->
    <input type="hidden" name="contactId" value="${(contactId)!}">
    <input type="hidden" name="productId" value="${(productId)!}">
    <input type="hidden" name="payDate" value="${(payDate)!}">
    <#-- 设置营销机会的ID -->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">分类</label>
        <div class="layui-input-block">
            <select name="state" id="state">
                <option value="">请设置订单状态</option>
                <option value="已完成">已完成</option>
                <option value="允许退款">允许退款</option>
                <option value="拒绝退款">拒绝退款</option>
            </select>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="postSale">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/postSale/process_state.js"></script>
</body>
</html>