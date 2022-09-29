<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

    <input type="hidden" name="contactId" value="${(contact.contactId)!}">
    <input type="hidden" name="phone" value="${(contact.phone)!}">
    <input type="hidden" name="address" value="${(contact.address)!}">
    <input type="hidden" name="contactMan" value="${(contact.contactMan)!}">
    <input type="hidden" name="isOrder" value="${(isOrder)!}">
    <input type="hidden" name="payMode" value="account">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">银行卡账号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userName" id="userName" placeholder="请输入银行卡账号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">银行卡密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input " lay-verify="required"
                   name="userPwd" id="userPwd" placeholder="请输入银行卡密码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="submitBtn">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/payment/payment_process.js"></script>
</body>
</html>