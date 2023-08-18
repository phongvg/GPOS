<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="param.catalog.title" /></title>
    <meta name="menu" content="paramMenu"/>
    <script src="<c:url value='/themes/admin/assets/js/cag/group-param/param_list.js'/>"></script>
</head>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<form:form id="paramForm" action="${ctx}/cag/group-param/param-list" method="post" modelAttribute="criteria">
	<form:hidden path="groupParamId"/>
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
					<li class="nav-item"><a href="<c:url value="/cag/group-param/form?id=${criteria.groupParamId}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.group.param"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/cag/group-param/sync-to-restaurant?groupParamId=${criteria.groupParamId}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
	
				<div class="tab-content">
                    
					<div class="card">
						<div class="card-header header-elements-inline">
							<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="param.catalog.title"/></span>
						</div>
                        <!-- \Searching -->
                        <div class="card m-2">
                            <div class="card-header bg-navbar text-white header-elements-inline">
                                <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12 col-sm-12">
                                        <label><fmt:message key="so.name"/></label>
                                        <input class="form-control" type="text" value="${criteria.paramCode}" name="paramCode" placeholder="Nhập mã tham số..."/>
                                    </div>
                                </div>
                                <div class="row mt-3 text-right">
                                    <div class="col-md-12">
                                        <button type="button" id="btnSubmit" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search" /></button>
                                        <a href="<c:url value='/cag/group-param/param-form?groupParamId=${criteria.groupParamId}'/>" type="button" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Searching -->
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
							                		<a href="<c:url value='/cag/group-param/param-form?id=${pa.id}&groupParamId=${criteria.groupParamId}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
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