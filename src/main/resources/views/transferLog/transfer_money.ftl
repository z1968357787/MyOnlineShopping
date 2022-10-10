<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#--设置营销人员的ID-->
    <input type="hidden" name="transferMode" value="${(transferMode)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户帐号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userName" id="userName"  value="${(user.userName)!}" placeholder="请输入用户帐号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input"  name="userPwd"
                   id="userPwd" value="${(user.userPwd)!}" placeholder="请输入用户密码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">银行卡号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="accountNumber" id="accountNumber" lay-verify="required" placeholder="请输入银行卡账号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">银行卡密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input" name="accountPassword" id="accountPassword" lay-verify="required" placeholder="银行卡密码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">转账金额</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="money" id="money"  value="${(contact.phone)!}" placeholder="请输入转账金额">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="transferMoney">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/transferLog/transfer_money.js"></script>
</body>
</html>