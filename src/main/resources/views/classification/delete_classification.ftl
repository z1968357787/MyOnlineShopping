<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">分类</label>
        <div class="layui-input-block">
            <select name="classification" id="classification">
                <option value="">请选择商品分类</option>
            </select>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="deleteClassification">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/classification/delete_classification.js"></script>
</body>
</html>