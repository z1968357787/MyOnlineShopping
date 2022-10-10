<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="productId" value="${(product.productId)!}">
    <input type="hidden" name="classification" value="${(product.classification)!}">
    <#--设置营销人员的ID-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="productName" id="productName"  value="${(product.productName)!}" placeholder="请输入商品名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品描述</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="productDescription" id="productDescription"  value="${(product.productDescription)!}" placeholder="请输入商品描述">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">单价</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="price" id="price"  value="${(product.price)!}" placeholder="请输入商品单价">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">分类</label>
        <div class="layui-input-block">
            <select name="classification" id="classification">
                <option value="">请选择商品分类</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">供应商</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="supplier" id="supplier"
                   lay-verify="required"  value="${(product.supplier)!}" placeholder="请输入供应商">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上架时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="listTime" name="listTime" value="${(product.listTime)!}" placeholder="请选择上架时间">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">库存数量</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" name="stock" id="stock"
                   lay-verify="required"  value="${(product.stock)!}" placeholder="请输入商品库存">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateProduct">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/productManagement/add_update.js"></script>
</body>
</html>