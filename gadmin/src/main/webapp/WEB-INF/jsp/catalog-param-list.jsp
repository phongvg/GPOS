<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="param.catalog.title"/></title>
    <meta name="menu" content="paramMenu"/>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
</head>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<form:form id="paramForm" action="${ctx}/param/catalog-list" method="post" modelAttribute="criteria">
	<form:hidden path="groupParam.id"/>
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="group.param.form.title"/></span>
			<div class="header-elements"><div class="list-icons"><a class="list-icons-item" data-action="collapse"></a><a class="list-icons-item" data-action="reload"></a><a class="list-icons-item" data-action="remove"></a></div></div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="group.param.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="so.legend"/></legend>
	
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/cag/group-param/form?id=${criteria.groupParam.id}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.group.param"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/param/sync-to-restaurant?gpId=${criteria.groupParam.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
	
				<div class="tab-content">
					<div class="card">
						<div class="card-header header-elements-inline">
							<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="param.catalog.title"/></span>
						</div>
						<div class="card-body row">
							<div class="col-md-4 offset-md-3"><input class="form-control" type="text" name="paramCode" placeholder="Nhập code danh mục param..."/></div>
	  						<div class="col-md-5">
	  							<button type="submit" class="btn btn-secondary" onclick="searchFunction()"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
	  							<a href="<c:url value='/param/catalog-form?gpId=${criteria.groupParam.id}'/>" type="button" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
	  						</div>
						</div>
						
						<div class="table-responsive">
							<table class="table table-bordered table-striped">
								<thead>
									<tr class="table-success text-left">
					           			<th>#</th>
					           			<th><fmt:message key="param.code" /></th>
					           			<th><fmt:message key="param.name" /></th>
					           			<th><fmt:message key="param.value" /></th>
					           			<th><fmt:message key="param.status" /></th>
					           			<th><fmt:message key="param.description" /></th>
					           			<th><fmt:message key="param.type" /></th>
					           			<security:authorize access="hasRole('ROLE_PARAM_EDIT')">
					           			<th><i class="icon-menu-open2"></i></th>
					           			</security:authorize>
									</tr>
								</thead>
								<tbody>
								<c:if test="${empty page.content}">
										<tr>
											<td colspan="7" class="text-center text-none-data"><fmt:message key="not.data"/></td>
										</tr>
									</c:if>
									<c:forEach items="${page.content}" var="pa" varStatus="cnt">
										<tr class="text-center">
						         			<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
						         			<td>${pa.paramCode }</td>
						         			<td>${pa.name }</td>
						         			<td>${pa.paramValue }</td>
							         		<td class="text-center"><span class="badge ${(pa.status) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="param.status.${pa.status}"/></span></td>      
						         			<td>${pa.description }</td>
						         			<td><span class="badge ${(pa.type) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="param.type.${pa.type}"/></span></td>
						         			<security:authorize access="hasRole('ROLE_PARAM_EDIT')">
						         			<td>
						         				<div class="list-icons">
							                		<a href="<c:url value='/param/catalog-form?id=${pa.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
					               				</div>
						         			</td>
						         			</security:authorize>
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
           						<jsp:param value="${page.totalElements}" name="totalElements"/>
					        </jsp:include>
					        <!-- /Pagination -->
					</div>
				</div>					
					
			</fieldset>

			
		</div>
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->
<script>
	function searchFunction(){
		$('#page').val(0);
	}
</script>