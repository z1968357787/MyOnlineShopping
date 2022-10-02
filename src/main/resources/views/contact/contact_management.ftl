<!DOCTYPE html>
<html>
<head>
    <title>营销机会管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="phone"
                           class="layui-input
							searchVal" placeholder="联系电话" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="address" class="layui-input
							searchVal" placeholder="地址" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="contact_man" class="layui-input
							searchVal" placeholder="联系人" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="contactList" class="layui-table"  lay-filter="contacts">
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
    <script id="contactListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/contact/contact_management.js"></script>
</body>
</html>