<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户动物记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox">
	<div class="ibox-title">
		<h5>用户动物记录列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content" id="layer-photos-pictures">
	<sys:message content="${message}"/>	
	<!-- 工具栏 和 查询条件-->
	<div class="row">
	<div class="col-sm-12">
		<form:form id="searchForm" modelAttribute="dwAnimal" action="${ctx}/dw/animal/list" method="post" class="form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group pull-left">
				<form:input path="name" placeholder="请输入微信昵称、动物类型、所属账号查询" style="width:300px" htmlEscape="false" maxlength="20" class=" form-control input-sm"/>
		        <button type="button" class="btn btn-sm btn-primary" onclick="search();"> <i class="fa fa-search"></i> 查询</button>
		        <button type="button" class="btn btn-sm btn-primary" onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
			 </div>	
		</form:form>
		<div class="pull-right">
	       	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>		
			</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th class="sort-column fans.avatar">用户头像</th>
				<th class="sort-column fans.nickname">用户昵称</th>
				<th class="sort-column name">动物类型</th>
				<th class="sort-column account">所属账号</th>
				<th class="sort-column price">动物价格</th>
				<th class="sort-column profit">动物利润</th>
				<th class="sort-column startTime">开始日期</th>
				<th class="sort-column endTime">结束日期</th>
				<th class="sort-column createTime">创建时间</th>
				<th class="sort-column remark">备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dwAnimal">
			<tr>
				<td><img src='${dwAnimal.fans.avatar}' width="30px" height="30px"/></td>
				<td>${dwAnimal.fans.nickname}</td>
				<td>${dwAnimal.name}</td>
				<td>${dwAnimal.account}</td>
				<td>${dwAnimal.price}</td>
				<td>${dwAnimal.profit}</td>
				<td><fmt:formatDate value="${dwAnimal.startTime}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${dwAnimal.endTime}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${dwAnimal.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>${dwAnimal.remark}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>