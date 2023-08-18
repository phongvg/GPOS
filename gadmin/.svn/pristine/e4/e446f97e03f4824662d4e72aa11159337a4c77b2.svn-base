<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="template.list.title"/></title>
    <meta name="menu" content="${from}Menu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

	<!-- Content area -->
	<div class="content">
			<!-- Table header styling -->
			<div class="card">
				
				<div class="card-header header-elements-inline">
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="template.list.title"></fmt:message></span>
					<div class="header-elements">
						<div class="list-icons">
							<a href="<c:url value='/template/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
	                		<!-- 
	                		<a class="list-icons-item" data-action="collapse"></a>
	                		<a class="list-icons-item" data-action="reload"></a>
	                		<a class="list-icons-item" data-action="remove"></a>
	                		 -->
	                	</div>
                	</div>
				</div>
 				
				<div class="card-body"><fmt:message key="template.list.title.description"/></div>

				<div class="">
					<table class="table table-bordered table-striped">
						<thead>
							<tr class="table-success text-center">
								<th width="5%">#</th>
								<th width="20%"><fmt:message key="template.name"></fmt:message></th>
								<th width="55%"><fmt:message key="template.description"></fmt:message></th>
								<th width="10%"><fmt:message key="template.locked"></fmt:message></th>
								<th width="10%"class="text-center"><i class="icon-menu-open2"></i></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty templates}">
								<tr>
									<td colspan="5" class="text-center text-none-data"><fmt:message key="not.data"/></td>
								</tr>
							</c:if>
							<c:forEach var="template" items="${templates}" varStatus="cnt">
								<tr>
									<td class="text-center"><c:out value="${cnt.count+page.size*page.number}" /></td>
									<td class="text-center">${template.name}</td>
									<td class="text-center">${template.description}</td>
									<td class="text-center">Release</td>
									<td class="text-center">
										<div class="dropdown">
											<a href="#" class="list-icons-item btn btn-default" data-toggle="dropdown"><i class="icon-menu"></i></a>
											<div class="dropdown-menu dropdown-menu-bottom">
												<a href="<c:url value="/template/form?id=${template.id}"/>" class="dropdown-item"><i class="icon-pencil7"></i> <fmt:message key="button.edit"/></a> 
												<a href='<c:url value="/restaurant/menu/${template.id }"/>' class="dropdown-item"><i class="icon-list3"></i> Cấu hình menu</a>
												<a href='<c:url value="/restaurant/configCO/${template.id }"/>' class="dropdown-item"><i class="icon-clipboard3"></i> Cấu hình menu CO</a>
												<a href='<c:url value="/restaurant/kds/${template.id }"/>' class="dropdown-item"><i class="icon-tree7"></i> Cấu hình KDS</a>
												<a href="<c:url value="/param/form?oId=${templateDto.id}&t=tem"/>" class="dropdown-item"><i class="icon-equalizer2 mr-2"></i>Cấu hình Param</a>
												<a href="<c:url value="/reportMenu/form?oId=${templateDto.id}&t=tem"/>" class="dropdown-item"><i class="icon-file-stats mr-2"></i>Báo cáo</a>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
	            <!-- Pagination -->
	            <jsp:include page="/themes/admin/common/pagination.jsp">
	                <jsp:param value="${page.number}" name="pageNumber"/>
	                <jsp:param value="${page.totalPages}" name="maxPages"/>
	                <jsp:param value="${page.size}" name="size"/>
	            </jsp:include>
				
			</div>
			<!-- /table header styling -->
	</div>
	<!-- /content area -->

<script>
function doSearch() {
	$("#page").val(0);
	$("#size").val(${page.size});
	$element.closest("form").submit();
}
</script>