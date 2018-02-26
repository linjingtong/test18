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
                var user_datagrid = $("#user_datagrid");
                var user_dialog = $("#user_dialog");
                var user_form = $("#user_form");
                /*
                 * 初始化数据表格
                 */
                user_datagrid.datagrid({
                    url:"/user/list",
                    fit:true,
                    rownumbers:true,
                    singleSelect:true,
                    striped:true,
                    pagination:true,
                    fitColumns:true,
                    toolbar:'#user_datagrid_tb'
                });
                /*
                 * 初始化新增/编辑对话框
                 */
                user_dialog.dialog({
                    width:300,
                    height:300,
                    closed:true,
                    buttons:'#user_dialog_bt'
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
                        user_form .form('clear');
                        user_dialog.dialog('setTitle', '新增');
                        user_dialog.dialog('open');
                    },
                    toUpdate:function(){
                        var rowData = user_datagrid.datagrid('getSelected');
                        if(!rowData){
                            $.messager.alert("温馨提示", "未选中数据", "error");
                            return;
                        }

                        user_form .form('clear');
                        user_dialog.dialog('setTitle', '编辑');
                        // 处理特殊数据
                        user_form .form('load',rowData);
                        user_dialog.dialog('open');
                    },
                    saveOrUpdate: function () {
                        var id = $('input[name="id"]').val();
                        var url = id ? '/user/update' : '/user/save';

                        user_form .form('submit', {
                            url: url,
                            success:function(data){
                                data = JSON.parse(data);
                                if(!data.success){
                                    $.messager.alert("温馨提示", data.msg, "error");
                                    return;
                                }
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    user_dialog.dialog('close');
                                    user_datagrid.datagrid('reload');
                                });
                            }
                        });
                    },
                    cancel:function () {
                        user_dialog.dialog('close');
                    },
                    remove:function(){
                        var rowData = user_datagrid.datagrid("getSelected");
                        if(!rowData){
                            $.messager.alert("温馨提示","请选择需要删除的数据!","warining");
                            return;
                        }
                        $.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
                            if(yes){
                                $.post("/user/delete?id="+rowData.id,function(data){
                                    if(!data.success){
                                        $.messager.alert("温馨提示",data.msg,"error");
                                        return;

                                    }
                                    $.messager.alert("温馨提示",data.msg,"info",function(){
                                        user_datagrid.datagrid("reload");
                                    });
                                },"json")
                            }
                        });
                    },
                    reload:function(){
                        user_datagrid.datagrid("reload");
                    },
                }
            });


    </script>
</head>
<body>
<!-- 数据表格 -->
<table id="user_datagrid">
    <thead>
    <tr>
        <th data-options="field:'username',width:1,align:'center'">用户名</th>
        <th data-options="field:'password',width:1,align:'center'">密码</th>
        <th data-options="field:'phoneNumber',width:1,align:'center'">电话号码</th>
    </tr>
    </thead>
</table>

<!-- 数据表格CRUD按钮 -->
<div id="user_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="toSave">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="toUpdate">編輯</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="remove">刪除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
</div>

<!-- 新增编辑对话框 -->
<div id="user_dialog">
    <form id="user_form" method="post">
        <table align="center" style="margin-top: 15px;">
            <input type="hidden" name="id">
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td>电话号码</td>
                <td><input type="text" name="phoneNumber"></td>
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