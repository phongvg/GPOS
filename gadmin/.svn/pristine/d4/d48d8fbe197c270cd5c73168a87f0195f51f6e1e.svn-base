<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="so.menu.list.title"/></title>
    <meta name="menu" content="soMenu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

<form:form id="foodGroupListForm" modelAttribute="criteria" action="${ctx}/cag/so/foodGroup/list"  method="post">

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="so.menu.list.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="so.menu.list.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="so.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/cag/so/form?id=${criteria.soId}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.so"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/cag/so/apply?soId=${criteria.soId}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
				</ul>
				<div class="tab-content">
					<!--cau hinh so  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.so.menu"/></span></div>
						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="/cag/so/menu/list?soId=${criteria.soId}" class="nav-link"><fmt:message key="tab.so.menu"/></a></li>
							<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="tab.so.foodGroup"/></a></li>
							<li class="nav-item"><a href="<c:url value="/cag/so/foodItem/list?soId=${criteria.soId}"/>" class="nav-link"><fmt:message key="tab.so.foodItem"/></a></li>
						</ul>
						<div class="card-body">
							<%-- <div class="list-icons" style="float: right"><a href="<c:url value='/cag/so/foodGroup/form?soId=${criteria.soId}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>" style=" margin-top: -25%; margin-bottom: 15%"><i class="icon-plus22"></i><fmt:message key="button.add"/></a></div> --%>
							<div class="table-responsive">
				               	<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-success">
											<th>#</th>
											<th><fmt:message key="foodGroup.code"/></th>
											<th><fmt:message key="foodGroup.nameVn"/></th>
											<%--
											<th><fmt:message key="so.nameEn"/></th>
											<th><fmt:message key="so.descVn"/></th>
											<th><fmt:message key="so.descEn"/></th>
											<th><fmt:message key="so.parent"/></th>
											<th><fmt:message key="so.foodGroups.selected"/></th>
											--%>
											<th class="text-center" style="width: 30px;"><i class="icon-so-open2"></i></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty page.content}">
											<tr>
												<td colspan="4" class="text-center text-none-data"><fmt:message key="not.data"/></td>
											</tr>
										</c:if>
										<c:forEach items="${page.content}" var="foodGroup" varStatus="cnt">
											<tr>
												<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
												<td>${foodGroup.code}</td>
												<td>${foodGroup.nameVn}</td>
												<%--
												<td>${so.nameEn}</td>
												<td>${so.descVn}</td>
												<td>${so.descEn}</td>
												<td>${so.parent.nameVn}</td>
												<td><c:forEach items="${so.foodGroups}" var="foodGroup">${foodGroup.nameVn},</c:forEach></td>
												 --%>
												<td class="text-left">
													<div class="list-icons">
								                		<a href="#" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
								                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
														<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																		<h4 class="modal-title"><fmt:message key="confirm.delete"/></h4>
																		<button type="button" class="close" data-dismiss="modal">&times;</button>
																	</div>
																	<div class="modal-body">
																		<p><fmt:message key="confirm.delete.question"/> <span style="color: blue;"></span></p>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																		<a href="<c:url value='/cag/so/menu/del/${soCat.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
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
