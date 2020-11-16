<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>加油记录管理</title>
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
		<h5>加油记录列表 </h5>
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
		<form:form id="searchForm" modelAttribute="vFuel" action="${ctx}/vehicle/fuel/list" method="post" class="form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<div class="form-group pull-left">
				<form:input path="oil" placeholder="请输入微信昵称、燃油标号查询" style="width:300px" htmlEscape="false" maxlength="20" class=" form-control input-sm"/>
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
				<th class="sort-column fans.nickname">用户姓名</th>
				<th class="sort-column cmileage">当前公里数</th>
				<th class="sort-column rest">剩余公里数</th>
				<th class="sort-column price">燃油单价</th>
				<th class="sort-column money">加油金额</th>
				<th class="sort-column oil">燃油标识</th>
				<th class="sort-column time">加油日期</th>
				<th class="sort-column createtime">创建时间</th>
				<th class="sort-column">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vFuel">
			<tr>
				<td><img src='${vFuel.fans.avatar}' width="30px" height="30px"/></td>
				<td>${vFuel.fans.nickname}</td>
				<td>${vFuel.cmileage}</td>
				<td>${vFuel.rest}</td>
				<td>${vFuel.price}</td>
				<td>${vFuel.money}</td>
				<td>${vFuel.oil}</td>
				<td><fmt:formatDate value="${vFuel.time}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${vFuel.createtime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
					<a href="#" onclick="openDialogView('查看加油信息', '${ctx}/vehicle/fuel/form?id=${vFuel.fuelId}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					<!-- <a href="${ctx}/vehicle/fuel/delete?id=${vFuel.fuelId}" onclick="return confirmx('确认要删除该加油记录吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a> -->
				</td>
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