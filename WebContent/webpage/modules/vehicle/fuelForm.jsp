<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>加油记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
		//图片展示
		layui.use('layer', function(){ 
		    var $ = layui.jquery, layer = layui.layer;
		 	layer.photos({
			  photos: '#layer-photos-pictures'
		 	});  
		});
	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="vFuel" action="${ctx}/vehicle/fuel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody id="layer-photos-pictures">
		  	 <tr>
			 	 <td class="active"><label class="pull-right"><font color="red">* </font>用户头像：</label></td>
		         <td><img src="${vFuel.fans.avatar}" width="80%" height='112%'/></td>
		         <td class="active"><label class="pull-right"><font color="red">* </font>用户昵称：</label></td>
		         <td><form:input path="fans.nickname" htmlEscape="false" maxlength="30" class="form-control required"/></td>		 	        		        
		      </tr>				        		     						        		  		     
			  <tr>
			 	 <td class="active"><label class="pull-right"><font color="red">* </font>当前公里：</label></td>
		         <td><form:input path="cmileage" htmlEscape="false" maxlength="7" class="form-control required"/></td>
		         <td class="active"><label class="pull-right"><font color="red">* </font>剩余公里：</label></td>
		         <td><form:input path="rest" htmlEscape="false" maxlength="7"  class="form-control required"/></td>	        		         
		      </tr>
		      <tr>
		      	 <td class="active"><label class="pull-right"><font color="red">* </font>加油金额：</label></td>
		         <td><form:input path="money" htmlEscape="false" maxlength="10" class="form-control required"/></td>
			 	 <td class="active"><label class="pull-right"><font color="red">* </font>燃油单价：</label></td>
		         <td><form:input path="price" htmlEscape="false" maxlength="10" class="form-control required"/></td>        
		      </tr>	
		      <tr>
		      	 <td class="active"><label class="pull-right"><font color="red">* </font>加油日期：</label></td>
		         <td><input id="time" name="time" type="text" maxlength="50" readOnly class="form-control"
							value="<fmt:formatDate value="${vFuel.time}" pattern="yyyy-MM-dd"/>"/></td>
			 	 <td class="active"><label class="pull-right"><font color="red">* </font>燃油标号：</label></td>
		         <td><form:input path="oil" htmlEscape="false" maxlength="15" class="form-control required"/></td>        
		      </tr>
		   </tbody>
		</table>
	</form:form>
</body>
</html>