<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="param.catalog.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
	
</head>

<!-- Content area -->
<div class="content">
	<form:form id="paramForm" action="${ctx}/param/list?rCode=${criteria.restaurantCode}" method="post" modelAttribute="criteria">
	<form:hidden path="restaurantCode"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="group.param.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="group.param.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<security:authorize access="hasRole('ROLE_RES_CONFIG_VIEW')">
					<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${criteria.restaurantCode}"/>" class="nav-link active"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					</security:authorize>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.param"/></span></div>
							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><fmt:message key="tab.group.param"/></a></li>
								<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.param"/></a></li>
							</ul>
						<div class="card-body">
							<div class="card">
							<div class="card-header header-elements-inline">
								<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="param.title.list"/></span>
							</div>
							<div class="card-body row">
								<div class="col-md-3 offset-md-3"><form:input class="form-control" type="text" path="paramCode" placeholder="Nhập mã tham số..."/></div>
		  						<div class="col-md-5">
		  							<security:authorize access="hasAnyRole('ROLE_RES_CONFIG_EDIT')">
			  							<button type="submit" class="btn btn-secondary" onclick="searchFunction()"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
			  							<a href="<c:url value='/param/form?rCode=${criteria.restaurantCode}'/>" type="button" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
					               	</security:authorize>
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
									                		<a href="<c:url value='/param/form?id=${pa.id}&rCode=${pa.restaurantCode}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
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