<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="list.FoodItem.in.co.title"/></title>
    <meta name="menu" content="coMenu"/>
    
	<!-- script select -->
</head>
<!-- Content area -->

<div class="content">
	<form:form id="coFoodItemForm" modelAttribute="criteria" action="${ctx}/coFoodItem/catalog-list-foodItem?cId=${criteria.co.id}"  method="post">
	<form:hidden path="co.id"/>
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="catalog.co.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
	
		<div class="card-body">
			<p class="mb-4"><fmt:message key="catalog.co.form.title.description"/></p>
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
					
						<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
							<li class="nav-item"><a href="<c:url value='/co/catalog-form?id=${criteria.co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co"/></a></li>
							<li class="nav-item"><a href="<c:url value='/co/soCategory/list?cId=${criteria.co.id}'/>" class="nav-link"><fmt:message key="tab.soCategory"/></a></li>
							<li class="nav-item"><a href="<c:url value='#'/>" class="nav-link active"><fmt:message key="tab.catalog.co.listFoodItem"/></a></li>
							<li class="nav-item"><a href="<c:url value='/cag/co-menu/configQrOrder/form?coId=${criteria.co.id}'/>" class="nav-link"><fmt:message key="tab.catalog.co.config.qr.order"/></a></li>
							<li class="nav-item"><a href="<c:url value="/co/sync-to-restaurant?cId=${criteria.co.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="tab.so.apply"/></a></li>
						</ul>
					<div class="tab-content">
						<!--danh muc Co  -->
						<div class="card">
							<div class="card-header header-elements-inline">
								<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="list.FoodItem.in.co.title"/></span>
							</div>
							<div class="card-body row">
								<span class="offset-md-1" style="padding-top: 0.5%;">Tìm kiếm món ăn: </span>
								<div class="col-md-2"><form:input class="form-control" type="text" path="foodItem.code" placeholder="Nhập mã món ăn..."/></div>
								<div class="col-md-2"><form:input class="form-control" type="text" path="foodItem.sapCode" placeholder="Nhập mã sapcode..."/></div>
								<div class="col-md-2"><form:input class="form-control" type="text" path="nameVn" placeholder="Nhập tên món ăn..."/></div>
		  						<div class="col-md-3">
		  							<button type="submit" class="btn btn-secondary" onclick="searchFunction()"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
		  							<a href="<c:url value='/coFoodItem/add-foodItem-to-co?cId=${criteria.co.id}'/>" type="button" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
		  						</div>
							</div>
								<div class="table-responsive">
									<table class="table table-bordered table-striped">
									<thead>
										<tr class="table-success text-left">
						           			<th>#</th>
						           			<th><fmt:message key="foodItem.sapcode" /></th>
						           			<th><fmt:message key="foodItem.code" /></th>
						           			<th><fmt:message key="foodItem.nameVn" /></th>
						           			<th><fmt:message key="foodItem.nameEn" /></th>
						           			<th class="text-center"><fmt:message key="foodItem.image" /></th>
						           			<th class="text-center"><i class="icon-menu-open2"></i></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty page.content}">
											<tr>
												<td colspan="8" class="text-center text-none-data"><fmt:message key="not.data"/></td>
											</tr>
										</c:if>
										<c:forEach items="${page.content}" var="coFoodItem" varStatus="cnt">
											<tr class="text-left">
							         			<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							         			<td>${coFoodItem.foodItem.sapCode }</td>
							         			<td>${coFoodItem.foodItem.code }</td>
							         			<td>${coFoodItem.nameVn }</td>
							         			<td>${coFoodItem.nameEn }</td>
							         			<td style="text-align:center;">
													<c:choose>
													    <c:when test="${empty coFoodItem.avatarUrl}">
													       	<img alt="" height="150" width="150" src='<c:url value="/themes/admin/global_assets/images/default_icon.png"></c:url>'>
													    </c:when>
													    <c:otherwise>
														     <a class="btn" rel="popover" data-img="<c:url value='${coFoodItem.avatarUrl}'/>"><img src="<c:url value='${coFoodItem.avatarUrl}'/>" title="<c:url value='${coFoodItem.avatarName}'/>" height="150" width="150"></a>
													    </c:otherwise>
													</c:choose>
												</td>
							         			<td class="text-center">
							         				<div class="list-icons">
								                		<a href="<c:url value='/coFoodItem/add-foodItem-to-co?id=${coFoodItem.id}&cId=${coFoodItem.co.id}'/>" class="list-icons-item text-primary-600" title="<fmt:message key='action.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
						               					<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
														<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
															<div class="modal-dialog modal-md">
																<div class="modal-content">
																	<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
																		<h4 class="modal-title"><fmt:message key="confirm.delete"/></h4>
																		<button type="button" class="close" data-dismiss="modal">&times;</button>
																	</div>
																	<div class="modal-body">
																		<p><fmt:message key="organization.confirm.success"/> <span style="color: blue;">${coFoodItem.nameVn}</span></p>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
																		<a href="<c:url value='/res/coFoodItem/del/${coFoodItem.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
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
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>
<script>
	function searchFunction(){
		$('#page').val(0);
	}
</script>