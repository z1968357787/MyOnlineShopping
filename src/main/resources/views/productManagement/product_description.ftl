<!DOCTYPE html>
<html>
<head>
    <title>商品信息</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="productName"
                           class="layui-input
							searchVal" placeholder="商品名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="productDescription" class="layui-input
							searchVal" placeholder="商品描述" />
                </div>
                <div class="layui-input-inline">
                    <select name="classification"  id="classification">
                        <option value="">分类</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="supplier" class="layui-input
							searchVal" placeholder="供应商" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="productDescription" class="layui-table"  lay-filter="productDescriptions">
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
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="addClassification">
                <i class="layui-icon">&#xe61f;</i>
                添加分类
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="updateClassification">
                <i class="layui-icon">&#xe642;</i>
                更新分类
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="deleteClassification">
                <i class="layui-icon">&#x1007;</i>
                删除分类
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="productDescriptionListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/productManagement/product_description.js"></script>
</body>
</html>