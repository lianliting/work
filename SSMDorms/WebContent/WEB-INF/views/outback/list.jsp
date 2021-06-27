
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
			<select id="search-studentName" name="studentName" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${studentList }" var="student">
            		<option value="${student.studentNumber }">${student.studentName }</option>
            	</c:forEach>
            </select>
			<label>学号:</label> 
			<select id="search-studentNumber" name="studentNumber" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${studentList }" var="student">
            		<option value="${student.studentNumber }">${student.studentNumber }</option>
            	</c:forEach>
            </select>
			<label>时间范围:</label>
			<input id="search-startTime"class="wu-text easyui-datebox" style="width: 100px"> ~ 
			<input id="search-endTime" class="wu-text easyui-datebox"style="width: 100px">
            
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>

<!-- Begin of easyui-dialog -->
<!-- 添加窗口 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">学号:</td>
                <td>
                	<select id="search-studentNumber" name="studentNumber" class="easyui-combobox" panelHeight="auto" style="width:120px">
            			<option value="-1">全部</option>
            			<c:forEach items="${studentList }" var="student">
            				<option value="${student.studentNumber }">${student.studentNumber }</option>
            			</c:forEach>
            		</select>
                </td>
            </tr>
            <tr>
                <td align="right">时间:</td>
                <td>
                <input class="wu-text  easyui-datetimebox"   data-options="required:true,showSeconds:false" value="3/4/2010"  readOnly ">
            </td>
            </tr>
             <tr>
                <td align="right">理由:</td>
                <td>
                <input type="text" name="reason" rows="6" class="wu-text easyui-validatebox" /></td>
            </td>
           <tr>
                <td align="right">备注:</td>
                <td>
                <input type="text" name="notes" rows="6" class="wu-text easyui-validatebox" /></td>
            </td>
            </tr>
          
        </table>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	/* 
	var${user.studentNumber};
	 */
	
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
            title: "添加早出/晚归信息",
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
            	/* $("#edit-studentNumber").combobox('setValue',item.studentNumber);
            	$("#edit-notes").val(item.notes); */
            
            }
        });
	}
	
	// 按键搜索
	$("#search-btn").click(function(){
		var data = {};
		var startTime = $("#search-startTime").datebox('getValue');
		var endTime = $("#search-endTime").datebox('getValue');
		var studentNumber = $("#search-studentNumber").combobox('getValue');
		var studentName = $("#search-studentName").combobox('getValue');
		if(studentName != -1){
			data.studentName = studentName;
		}
		if(studentNumber != -1){
			data.studentNumber=studentNumber;
		}
		if(startTime!=''){
			data.startTime=startTime;
		}
		if(endTime!=''){
			data.endTime=endTime;
		}
		
		$('#data-datagrid').datagrid('reload',data);
		
	});
	
	function add0(m){return m<10?'0'+m:m }
	function format(shijianchuo){
	//时间戳是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
	}
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
			{ field:'studentNumber',title:'学号',width:100,sortable:true},
			{ field:'studentName',title:'姓名',width:100,formatter:function(value,row,index){
				var studentNumber=row.studentNumber;
				var studentList = $("#search-studentName").combobox('getData');
			 for(var i=0;i<studentList.length;i++){
					if(studentNumber == studentList[i].value)return studentList[i].text;
				} 
				return value;
			}}, 
			{ field:'reason',title:'理由',width:100,sortable:true},
			{ field:'notes',title:'备注',width:100,sortable:true},
			{ field:'time',title:'时间',width:200,formatter:function(value,row,index){
				return format(value);
			}},
		]]
	});
</script>