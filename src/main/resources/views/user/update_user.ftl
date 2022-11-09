<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="id" value="${(userManage.id)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="trueName" id="trueName" value="${(userManage.trueName)!}" placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">个人邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="email" value="${(userManage.email)!}"
                   id="email" placeholder="请输入个人邮箱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号码</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" name="phone" id="phone" value="${(userManage.phone)!}"
                   lay-verify="required" placeholder="请输入手机号码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户零钱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="balance" id="balance"  value="${(userManage.balance)!}" placeholder="请输入用户零钱">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateUser">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/user/add_update.js"></script>
</body>
</html>