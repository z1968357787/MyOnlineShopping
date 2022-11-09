<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">

    <input type="hidden" name="id" value="${(userManage.id)!}">
    <#--设置营销人员的ID-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户帐号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="userName" id="userName" placeholder="请输入用户帐号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input"  name="userPwd"
                   id="userPwd" placeholder="请输入用户密码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input" name="repeatPwd"
                   id="repeatPwd" lay-verify="required" placeholder="请输入确认密码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="trueName" id="trueName" placeholder="请输入真实姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">个人邮箱</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="email"
                   id="email" placeholder="请输入个人邮箱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号码</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" name="phone" id="phone"
                   lay-verify="required" placeholder="请输入手机号码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户零钱</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="balance" id="balance" placeholder="请输入用户零钱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户角色</label>
        <div class="layui-input-block">
            <select name="role" id="role">
                <option value="">请选择用户角色</option>
                <option value="普通用户">普通用户</option>
                <option value="管理员">管理员</option>
            </select>
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