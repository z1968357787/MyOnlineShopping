<!DOCTYPE html>
<html>
<head>
    <title>用户信息管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="userName"
                           class="layui-input
							searchVal" placeholder="用户名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="trueName" class="layui-input
							searchVal" placeholder="真实姓名" />
                </div>
                <div class="layui-input-inline">
                    <select name="role"  id="role">
                        <option value="">用户角色</option>
                        <option value="普通用户">普通用户</option>
                        <option value="管理员">管理员</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="userManagement" class="layui-table"  lay-filter="userManagements">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe624;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe67e;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="userManagementListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/user/management.js"></script>
</body>
</html>