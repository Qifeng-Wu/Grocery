<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>新闻资讯管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox">
	<div class="ibox-title">
		<h5>新闻资讯列表 </h5>
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
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">	
		<form:form id="searchForm" modelAttribute="vNews" action="${ctx}/vehicle/news/" method="post" class="form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->	
			<div class="form-group pull-left">
				<form:input path="title" placeholder="输入标题查询" style="width:300px" htmlEscape="false" maxlength="15"  class=" form-control input-sm"/>
	          	<button type="button" class="btn btn-sm btn-primary" onclick="search();"> <i class="fa fa-search"></i> 查询</button>
	          	<button type="button" class="btn btn-sm btn-primary" onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
			 </div>	
		</form:form>
		<div class="pull-right">
				<table:addRow url="${ctx}/vehicle/news/form" title="添加"></table:addRow><!-- 增加按钮 -->
			    <table:editRow url="${ctx}/vehicle/news/form" title="编辑" id="contentTable"></table:editRow><!-- 编辑按钮 -->
				<table:delRow url="${ctx}/vehicle/news/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
	       	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>		
		</div>
	</div>
	</div>
	
		<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th><input type="checkbox" class="i-checks"></th>
				<th class="sort-column picture">缩略图</th>
				<th class="sort-column title">标题</th>
				<th class="sort-column title">标签</th>
				<th style="width:30%" class="sort-column summary">摘要</th>
				<th class="sort-column state">状态</th>
				<th class="sort-column sort">显示顺序</th>				
				<th class="sort-column">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vNews">
			<tr>
				<td><input type="checkbox" id="${vNews.id}" class="i-checks"></td>
				<td><img src='${vNews.picture}' width="30px" height="30px"/></td>
				<td>${vNews.title}</td>
				<td>${vNews.label}</td>
				<td>${vNews.summary}</td>
				<td>${vNews.state==true?"显示":"隐藏"}</td>
				<td>${vNews.sort}</td>
				<td>
					<a href="#" onclick="openDialogView('查看新闻资讯', '${ctx}/vehicle/news/form?id=${vNews.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
    				<a href="#" onclick="openDialog('修改新闻资讯', '${ctx}/vehicle/news/form?id=${vNews.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
					<a href="${ctx}/vehicle/news/delete?id=${vNews.id}" onclick="return confirmx('确认要删除该新闻资讯吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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