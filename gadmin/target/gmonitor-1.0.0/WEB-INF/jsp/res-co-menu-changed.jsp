<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="co.menu.changed.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    
    <script src="<c:url value='/themes/admin/assets/js/reset_catalog.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
</head>
<!-- Content area -->
<div class="content">
	<form:form id="coCategoryForm" modelAttribute="criteria" action="${ctx}/res/co/soCategory/list"  method="post">
	<form:hidden path="coId"/>
	<form:hidden path="restaurantCode"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="co.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="co.res.form.title.description"/></p>
			<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="co.legend"/></legend>
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_VIEW')">
					<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
					<li class="nav-item"><a href="<c:url value="/co/form?rCode=${criteria.restaurantCode}"/>" class="nav-link active"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
					<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kitchen.fax"/></a></li>
					<li class="nav-item"><a href="<c:url value="/group-param/form?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
					<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
					<li class="nav-item"><a href="<c:url value="/res/kdsPlace/list?rCode=${criteria.restaurantCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
					</security:authorize>
				</ul>
				<div class="tab-content">
					<!--cau hinh menu  -->
					<div class="card">
						<div class="card-header header-elements-inline"><span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="co.menu.changed.title"/></span></div>
							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="<c:url value='/co/form?rCode=${criteria.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.co"/></a></li>
								<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.soCategory"/></a></li>
								<li class="nav-item"><a href="<c:url value='/coFoodItem/list?cId=${coId}&rCode=${criteria.restaurantCode}&coId=${coId}'/>" class="nav-link"><fmt:message key="tab.co.res.listFoodItem"/></a></li>
								<li class="nav-item"><a href="<c:url value='/restaurant/co-menu/configQrOrder/form?restaurantCode=${criteria.restaurantCode}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
							</ul>
						<div class="card-body">
							<div class="card">
							<div class="card-header header-elements-inline">
								<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="tab.co.menu.changed"/></span>
								<div class="header-elements">
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
									<div class="list-icons">
										<a href="<c:url value="/res/co/soCategory/list?rCode=${criteria.restaurantCode}&cId=${coId}"/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.all.item"/>"><fmt:message key="button.all.item"/></a>
					               		<button type="button" id="resetCoCategory" class="btn btn-primary ml-3"><fmt:message key="button.reset.data"/><i class="icon-database-insert ml-2"></i></button>
					               	</div>
					               	</security:authorize>
								</div>
							</div>
								<div class="table-responsive">
									<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-success text-left">
						           			<th>#</th>
											<th><fmt:message key="orderCategory.name"/></th>
											<th><fmt:message key="menu.nameVn" /></th>
											<th><fmt:message key="menu.nameEn" /></th>
											<th><fmt:message key="menu.descVn" /></th>
											<th><fmt:message key="menu.descEn" /></th>
											<th class="text-center"><fmt:message key="foodItem.image" /></th>
											<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
											<th class="text-center"><i class="icon-so-open2"></i></th>
											</security:authorize>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty page.content}">
										<tr>
											<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
										</tr>
										</c:if>
										<c:forEach items="${page.content}" var="item" varStatus="cnt">
											<tr class="text-left">
							         			<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							         			<td><c:out value="${item.soCategory.orderCategory.code} - ${item.soCategory.orderCategory.name}"></c:out></td>
							         			<td><c:out value="${item.nameVn}"></c:out></td>
							         			<td><c:out value="${item.nameEn}"></c:out></td>
							         			<td><c:out value="${item.descVn}"></c:out></td>
							         			<td><c:out value="${item.descEn}"></c:out></td>
							         			<td style="text-align:center;">
													<c:choose>
													    <c:when test="${empty item.avatarUrl}">
													       	<img alt="" height="150" width="150" title="default_icon.png" src='<c:url value="/themes/admin/global_assets/images/default_icon.png"></c:url>'>
													    </c:when>
													    <c:otherwise>
														     <a class="btn" rel="popover" data-img="<c:url value='${item.avatarUrl}'/>"><img src="<c:url value='${item.avatarUrl}'/>" title="<c:url value='${item.avatarName}'/>" height="150" width="150"></a>
													    </c:otherwise>
													</c:choose>
												</td>
												<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_EDIT')">
							         			<td class="text-center">
							         				<div class="list-icons">
								                		<a href="<c:url value='/res/co/soCategory/form?id=${item.id}&cId=${coId}&rCode=${item.restaurantCode}'/>" class="list-icons-item text-primary-600" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
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
																		<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																		<a href="<c:url value='/coCategory/delete?id=${item.id}&resCode=${item.restaurantCode}'/>" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
																	</div>
																</div>
															</div>
														</div>
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
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>