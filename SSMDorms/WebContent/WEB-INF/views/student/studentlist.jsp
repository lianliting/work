<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">

    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>姓名:</label>
            <input id="search-name" class="wu-text" style="width:100px">
            <label>学号:</label>
            <input id="search-number" class="wu-text" style="width:100px">
            <label>所属寝室楼:</label> 
            <select id="search-dorms" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${dormitoryManagementList }" var="dormitoryManagement">
            		<option value="${dormitoryManagement.id }">${dormitoryManagement.dorms }</option>
            	</c:forEach>
            </select>
            <label>寝室号:</label>
            <input id="search-numbers" class="wu-text" style="width:100px">
            <label>性别:</label>
            <select id="search-sex" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="1">男</option>
            	<option value="0">女</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<!-- 添加管理员窗口 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">头像预览:</td>
                <td valign="middle">
                	<img id="preview-photo" style="float:left;" src="/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg" width="100px">
                	<a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">头像:</td>
                <td><input type="text" id="add-photo" name="photo" value="/BaseProjectSSM/resources/admin/easyui/images/admin_photo.jpg" readonly="readonly" class="wu-text " /></td>
            </tr>
            <tr>
                <td width="60" align="right">学号:</td>
                <td><input type="text" name="studentNumber" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写学号'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">姓名:</td>
                <td><input type="text" name="studentName" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写姓名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">性别:</td>
                <td>
                	<select name="sex" class="easyui-combobox" panelHeight="auto" style="width:268px">
            			<option value="1">男</option>
            			<option value="0">女</option>
		            </select>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">所属寝室楼:</td>
                <td>
                	<select name="dormsId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择寝室楼'">
		                <c:forEach items="${dormitoryManagementList }" var="dormitoryManagement">
		                <option value="${dormitoryManagement.id }">${dormitoryManagement.dorms }</option>
		                </c:forEach>
		            </select>
                </td>
            </tr>
             <tr>
                <td width="60" align="right">寝室号:</td>
                <td><input type="text" id="edit-numbers" name="numbers" class="wu-text easyui-validatebox" /></td>
            </tr>
             <tr>
                <td width="60" align="right">电话:</td>
                <td><input type="text" id="edit-tel" name="tel" class="wu-text easyui-validatebox" /></td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">头像预览:</td>
                <td valign="middle">
                	<img id="edit-preview-photo" style="float:left;" src="/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg" width="100px">
                	<a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">头像:</td>
                <td><input type="text" id="edit-photo" name="photo" value="/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg" readonly="readonly" class="wu-text " /></td>
            </tr>
            <tr>
                <td width="60" align="right">学号:</td>
                <td><input type="text" id="edit-studentNumber" name="studentNumber" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写学号'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">姓名:</td>
                <td><input type="text" id="edit-username" name="studentName" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            
             
            <tr>
                <td width="60" align="right">性别:</td>
                <td>
                	<select id="edit-sex" name="sex" class="easyui-combobox" panelHeight="auto" style="width:268px">
            			<option value="1">男</option>
            			<option value="0">女</option>
		            </select>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">所属寝室楼:</td>
                <td>
                	<select id="edit-dormsId" name="dormsId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择寝室楼'">
		                <c:forEach items="${dormitoryManagementList }" var="dormitoryManagement">
		                <option value="${dormitoryManagement.id }">${dormitoryManagement.dorms }</option>
		                </c:forEach>
		            </select>
                </td>
            </tr>
             <tr>
                <td width="60" align="right">寝室号:</td>
                <td><input type="text" id="edit-numbers" name="numbers" class="wu-text easyui-validatebox" /></td>
            </tr>
             <tr>
                <td width="60" align="right">电话:</td>
                <td><input type="text" id="edit-tel" name="tel" class="wu-text easyui-validatebox" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	//上传图片
	function start(){
			var value = $('#p').progressbar('getValue');
			if (value < 100){
				value += Math.floor(Math.random() * 10);
				$('#p').progressbar('setValue', value);
			}else{
				$('#p').progressbar('setValue',0)
			}
	};
	function upload(){
		if($("#photo-file").val() == '')return;
		var formData = new FormData();
		formData.append('photo',document.getElementById('photo-file').files[0]);
		$("#process-dialog").dialog('open');
		var interval = setInterval(start,200);
		$.ajax({
			url:'upload_photo',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if(data.type == 'success'){
					$("#preview-photo").attr('src',data.filepath);
					$("#add-photo").val(data.filepath);
					$("#edit-preview-photo").attr('src',data.filepath);
					$("#edit-photo").val(data.filepath);
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			},
			error:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒","上传失败!","warning");
			}
		});
	}
	
	function uploadPhoto(){
		$("#photo-file").click();
		
	}
	
	
	/**
	*  添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelections');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
				var ids = '';
				for(var i=0;i<item.length;i++){
					ids += item[i].id + ',';
				}
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{ids:ids},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加学生信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            }
        });
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改学生信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#edit-id").val(item.id);
            	$("#edit-preview-photo").attr('src',item.photo);
				$("#edit-photo").val(item.photo);
				$("#edit-studentNumber").val(item.studentNumber);
            	$("#edit-studentName").val(item.studentName);
            	$("#edit-dormsId").combobox('setValue',item.dormsId);
            	$("#edit-sex").combobox('setValue',item.sex);
            	
            	$("#edit-numbers").val(item.numbers);
            	$("#edit-tel").val(item.tel);
            }
        });
	}	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var dormsId = $("#search-dorms").combobox('getValue');
		var sex = $("#search-sex").combobox('getValue')
		var option = {studentName:$("#search-name").val(),studentNumber:$("#search-number").val(),numbers:$("#search-numbers").val()};
		if(dormsId != -1){
			option.dormsId = dormsId;
		}
		if(sex != -1){
			option.sex = sex;
		}
		$('#data-datagrid').datagrid('reload',option);
	});
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:false,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'photo',title:'学生头像',width:100,align:'center',formatter:function(value,row,index){
				var img = '<img src="'+value+'" width="50px" />';
				return img;
			}},
			{ field:'studentNumber',title:'学号',width:100},
			{ field:'studentName',title:'学生姓名',width:100,sortable:true},
			
			{ field:'sex',title:'性别',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '女';
					}
					case 1:{
						return '男';
					}
				}
				return value;
			}},
			{ field:'dormsId',title:'所属寝室楼',width:100,formatter:function(value,row,index){
				var dormitoryManagementList = $("#search-dorms").combobox('getData');
				for(var i=0;i<dormitoryManagementList.length;i++){
					if(value == dormitoryManagementList[i].value)return dormitoryManagementList[i].text;
				}
				return value;
			}},
				{ field:'numbers',title:'寝室号',width:100},
				{ field:'tel',title:'电话',width:200}
			]],
	});
</script>