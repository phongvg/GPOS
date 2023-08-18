<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="template.form.title"/></title>
    <meta name="menu" content="templateMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
</head>
<!-- Content area -->
<form:form id="soForm" modelAttribute="criteria" action="${ctx}/so/list?rId=${criteria.ownerId }"  method="post">
<form:hidden path="ownerId"/>
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="template.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="template.form.title.description"/></p>
		 	
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
		
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rId=${criteria.ownerId}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<li class="nav-item"><a href="<c:url value="/menu/form?rId=${criteria.ownerId}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kitchen/form?rId=${criteria.ownerId}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					<li class="nav-item"><a href="<c:url value="/param/list?rId=${criteria.ownerId}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/advanced/list?rId=${criteria.ownerId}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
					<li class="nav-item"><a href="<c:url value="/reportMenu/list?rId=${criteria.ownerId}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
				</ul>
			<div class="tab-content">	
			<!-- CO -->
			<div class="card">
				<div class="card-header">
					<span class="text-uppercase font-size-lg font-weight-bold">Cấu hình CO/SO</span>
				</div>
				
				<div class="card-body">
				<ul class="nav nav-tabs">
						<li class="nav-item"><a href='<c:url value="/co/list?rId=${criteria.ownerId}"/>' class="nav-link">Cấu hình CO</a></li>
						<li class="nav-item"><a href="#" class="nav-link active">Cấu hình SO</a></li>
					</ul>
				</div>
					
				
				<div class="tab-content">
					<div class="tab-pane fade show active" id="basic-justified-tab2">
						<div class="card-header header-elements-inline">
							<span class="text-uppercase font-size-lg font-weight-bold">Cấu hình SO</span>
							<div class="header-elements">
								<div class="list-icons">
									<a href="<c:url value='/co/form?rId=${criteria.ownerId }&type=SO'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
				               	</div>
				        	</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered table-striped" style="text-align: center;">
									<thead>
										<tr class="table-success">
											<th>STT</th>
											<th>Menu</th>
											<th><fmt:message key="co.maximum1turn"></fmt:message></th>
											<th><fmt:message key="co.maximum"></fmt:message></th>
											<th style="width: 15%"></th>
										</tr>
									</thead>
									
									<tbody>
										<c:if test="${empty page.content}">
											<tr>
												<td colspan="5" class="text-center text-none-data"><fmt:message key="not.data"/></td>
											</tr>
										</c:if>
										<c:forEach items="${page.content }" var="deviceDisplay" varStatus="cnt">
											<tr>
												<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
												<td>
													<c:forEach items="${deviceDisplay.menus }" var="menu">
														<c:out value="${menu.nameVn }"/>,&nbsp;
													</c:forEach>
												</td>
												<td>${deviceDisplay.maxPerTransaction }</td>
												<td>${deviceDisplay.maxForKitchen }</td>
												<td>
													<div class="list-icons">
								                		<a href="<c:url value='/co/form?id=${deviceDisplay.id}&rId=${deviceDisplay.ownerId}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
								                		<a href="javascript(0);" data-target="#confirmOrganizationDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='action.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
														<div class="modal fade text-left" id="confirmOrganizationDel_${cnt.index}" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																		<h4 class="modal-title"><fmt:message key="position.confirm.title"/></h4>
																		<button type="button" class="close" data-dismiss="modal">&times;</button>
																	</div>
																	<div class="modal-body">
																		<p><fmt:message key="position.confirm.success"/> <span style="color: blue;">${position.name}?</span></p>
																	</div>
																	<div class="modal-footer">
																		<a href="<c:url value='/co/delete?id=${deviceDisplay.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
																		<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																	</div>
																</div>
															</div>
														</div>
				                					</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<jsp:include page="/themes/admin/common/pagination.jsp">
						            <jsp:param value="${page.number}" name="pageNumber"/>
						            <jsp:param value="${page.totalPages}" name="maxPages"/>
						            <jsp:param value="${page.size}" name="size"/>
	            					<jsp:param value="${page.totalElements}" name="totalElements"/>
						        </jsp:include>
							</div>		
						</div>
					</div>
				</div>
				
			</div>
		</div>							
	</fieldset>
	
</div>

	</div>
	<!-- /basic layout -->

</div>
<!-- /content area -->
</form:form>

