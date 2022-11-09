<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="userId" value="${(userId)!}">
    <#--设置营销人员的ID-->
    <input type="hidden" name="contactId" value="${(contactId)!}">
    <input type="hidden" name="productId" value="${(productId)!}">
    <input type="hidden" name="payDate" value="${(payDate)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">订单评分</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="score" id="score" placeholder="请输入订单评分">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">订单评价</label>
        <div class="layui-input-block">
            <textarea type="text" id="evaluation" name="evaluation" placeholder="请输入订单评价" class="layui-textarea"></textarea>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addEvaluation">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/payLog/paylog_evaluation.js"></script>
</body>
</html>