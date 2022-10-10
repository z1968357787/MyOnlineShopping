<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="discountId" value="${(discount.discountId)!}">
    <#--设置营销人员的ID-->
    <input type="hidden" name="discountDescription" value="${(discount.discountDescription)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户Id</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userId" id="userId" placeholder="请输入用户Id">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">优惠券张数</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="count"
                   id="count" placeholder="请输入优惠券张数">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="assignDiscount">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/discount/assign_discount.js"></script>
</body>
</html>