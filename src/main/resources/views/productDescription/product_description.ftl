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
                        <option value="" >分类</option>
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
                <i class="layui-icon">&#xe657;</i>
                加入购物车
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="pay">
                <i class="layui-icon">&#xe65e;</i>
                支付
            </a>
        </div>

    </script>


    <!--
    <script id="productDescriptionListBar" type="text/html">
            <div style="width: 160px">

                <input style="width: 50px; height: 38px; border: 2px white; float: left;" type="button" value="-" onclick="reductionOf(this)" />

                <input style="text-align: center; width: 50px; height: 32px; float: left;" type="text" value="1" onblur="checkNumber(this)" />

                <input style="width: 50px; height: 38px; border: 2px white;" type="button" value="+" onclick="add(this)" />

            </div>
    </script>操作-->
</form>

<script type="text/javascript" src="${ctx}/js/productDescription/product_description.js"></script>
</body>
</html>