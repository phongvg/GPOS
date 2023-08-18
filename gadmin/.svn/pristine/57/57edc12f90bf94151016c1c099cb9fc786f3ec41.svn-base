<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="menu.title"/></title>
    <meta name="menu" content="${from}Menu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
</head>

<form:form id="menuListForm" modelAttribute="criteria" action="${ctx}/menu/list"  method="post">
<form:hidden path="ownerId"/>
<form:hidden path="ownerType"/>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="menu.title"/></span>
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
					<li class="nav-item"><a href="<c:url value="/${from}/form?id=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.${from}"/></a></li>
					<li class="nav-item"><a href="<c:url value="/menu/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/area/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					<li class="nav-item"><a href="<c:url value="/param/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<c:if test="${from eq 'restaurant'}">
					<li class="nav-item"><a href="<c:url value="/advanced/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
					<li class="nav-item"><a href="<c:url value="/reportMenu/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
					</c:if>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.menu.menu"/></span></div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.menu"/></a></li>
							<li class="nav-item"><a href="<c:url value='/foodGroup/list?oId=${criteria.ownerId}&t=${criteria.ownerType}'/>" class="nav-link"><fmt:message key="tab.menu.foodGroup"/></a></li>
							<li class="nav-item"><a href="<c:url value="/foodItem/list?oId=${criteria.ownerId}&t=${criteria.ownerType}"/>" class="nav-link"><fmt:message key="tab.menu.foodItem"/></a></li>
							<li class="nav-item"><a href="<c:url value='/feature/list?oId=${criteria.ownerId}&t=${criteria.ownerType}'/>" class="nav-link"><fmt:message key="tab.menu.feature"/></a></li>
						</ul>
						<div class="card-body">
							<div class="list-icons" style="float: right">
								<a href="<c:url value='/menu/form?oId=${criteria.ownerId}&t=${criteria.ownerType}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>" style=" margin-top: -25%; margin-bottom: 15%"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
			               	</div>
							<div class="table-responsive">
				               	<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-success">
											<th>#</th>
											<th><fmt:message key="menu.code"/></th>
											<th><fmt:message key="menu.nameVn"/></th>
											<th><fmt:message key="menu.nameEn"/></th>
											<th><fmt:message key="menu.descVn"/></th>
											<th><fmt:message key="menu.descEn"/></th>
											<th><fmt:message key="menu.parent"/></th>
											<th><fmt:message key="menu.foodGroups.selected"/></th>
											<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty page.content}">
											<tr>
												<td colspan="9" class="text-center text-none-data"><fmt:message key="not.data"/></td>
											</tr>
										</c:if>
										<c:forEach items="${page.content}" var="menu" varStatus="cnt">
											<tr>
												<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
												<td>${menu.code}</td>
												<td>${menu.nameVn}</td>
												<td>${menu.nameEn}</td>
												<td>${menu.descVn}</td>
												<td>${menu.descEn}</td>
												<td>${menu.parent.nameVn}</td>
												<td><c:forEach items="${menu.foodGroups}" var="foodGroup">${foodGroup.nameVn},</c:forEach></td>
												<td class="text-left">
													<div class="list-icons">
								                		<a href="<c:url value='/menu/form?id=${menu.id}&oId=${menu.ownerId}&t=${menu.ownerType}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
								                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
														<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																		<h4 class="modal-title"><fmt:message key="confirm.delete"/></h4>
																		<button type="button" class="close" data-dismiss="modal">&times;</button>
																	</div>
																	<div class="modal-body">
																		<p><fmt:message key="confirm.delete.question"/> <span style="color: blue;">${menu.nameVn}</span></p>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																		<a href="<c:url value='/menu/del/${menu.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
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
							</div>
							
							<!-- Pagination -->
					        <jsp:include page="/themes/admin/common/pagination.jsp">
					            <jsp:param value="${page.number}" name="pageNumber"/>
					            <jsp:param value="${page.totalPages}" name="maxPages"/>
					            <jsp:param value="${page.size}" name="size"/>
	            				<jsp:param value="${page.totalElements}" name="totalElements"/>
					        </jsp:include>
							
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
