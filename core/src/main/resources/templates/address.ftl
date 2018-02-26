<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>地址管理</title>
    <#include "common/header.ftl"/>
    <script type="text/javascript" >
        $(function(){
            /*
             * 抽取所有需要用得元素.
             */
            var address_datagrid = $("#address_datagrid");
            var address_dialog = $("#address_dialog");
            var address_form = $("#address_form");
            /*
             * 初始化数据表格
             */
            address_datagrid.datagrid({
                url:"/address/list",
                fit:true,
                rownumbers:true,
                singleSelect:true,
                striped:true,
                pagination:true,
                fitColumns:true,
                toolbar:'#address_datagrid_tb',
                columns: [[
                    {
                        field: 'user',
                        title: '用户名',
                        width: 100,
                        align: 'center',
                        formatter: function (value, row, index) {
                            return value ? value.username : "";
                        }
                    },
                    {field: 'address', title: '地址', width: 100, align: 'center'}
                ]]
            });
            /*
             * 初始化新增/编辑对话框
             */
            address_dialog.dialog({
                width:300,
                height:300,
                closed:true,
                buttons:'#address_dialog_bt'
            });
            /*
             * 对页面按钮进行统一监听
             */
            $("a[data-cmd]").on("click",function(){
                var cmd = $(this).data("cmd");
                if(cmd){
                    cmdObj[cmd]();
                }
            });
            /*
             * 所有的操作封装到cmdObj对象中,方便管理
             */
            var cmdObj = {
                toSave : function () {
                    address_form .form('clear');
                    address_dialog.dialog('setTitle', '新增');
                    address_dialog.dialog('open');
                },
                toUpdate:function(){
                    var rowData = address_datagrid.datagrid('getSelected');
                    if(!rowData){
                        $.messager.alert("温馨提示", "未选中数据", "error");
                        return;
                    }

                    address_form .form('clear');
                    address_dialog.dialog('setTitle', '编辑');
                    // 处理特殊数据
                    if (rowData) {
                        rowData['user.id'] = rowData.user.id;
                    }
                    address_form .form('load',rowData);
                    address_dialog.dialog('open');
                },
                saveOrUpdate: function () {
                    var id = $('input[name="id"]').val();
                    var url = id ? '/address/update' : '/address/save';

                    address_form .form('submit', {
                        url: url,
                        success:function(data){
                            data = JSON.parse(data);
                            if(!data.success){
                                $.messager.alert("温馨提示", data.msg, "error");
                                return;
                            }
                            $.messager.alert("温馨提示", data.msg, "info", function () {
                                address_dialog.dialog('close');
                                address_datagrid.datagrid('reload');
                            });
                        }
                    });
                },
                cancel:function () {
                    address_dialog.dialog('close');
                },
                remove:function(){
                    var rowData = address_datagrid.datagrid("getSelected");
                    if(!rowData){
                        $.messager.alert("温馨提示","请选择需要删除的数据!","warining");
                        return;
                    }
                    $.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
                        if(yes){
                            $.post("/address/delete?id="+rowData.id,function(data){
                                if(!data.success){
                                    $.messager.alert("温馨提示",data.msg,"error");
                                    return;

                                }
                                $.messager.alert("温馨提示",data.msg,"info",function(){
                                    address_datagrid.datagrid("reload");
                                });
                            },"json")
                        }
                    });
                },
                reload:function(){
                    address_datagrid.datagrid("reload");
                },
            }
        });

    </script>
</head>
<body>
<!-- 数据表格 -->
<table id="address_datagrid">

</table>

<!-- 数据表格CRUD按钮 -->
<div id="address_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="toSave">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="toUpdate">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
</div>

<!-- 新增编辑对话框 -->
<div id="address_dialog">
    <form id="address_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>用户名</td>
                <td>
                    <input id="safetyMechanism_name" class="easyui-combobox itemInput_style" name="user.id"
                           data-options="valueField:'id',textField:'username',url:'/user/listForAddress', panelHeight:'auto'">
                </td>
            </tr>
            <tr>
                <td>地址</td>
                <td><input type="text" name="address"></td>
            </tr>
        </table>
    </form>
    <div style="margin-top: 15px;margin-left: 85px" >
        <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="saveOrUpdate">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
    </div>
</div>
</body>
</html>