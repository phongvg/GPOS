<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foodGroup.title"/></title>
    <meta name="menu" content="userMenu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

<div class="content">
	<form:form id="foodGroupListForm" modelAttribute="criteria" action="${ctx}/ref/foodGroup/list" method="post">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-md-5 offset-md-2"><input class="form-control" type="text" name="nameVn" placeholder="Nhập tên nhóm món ăn..."/></div>
		  			<div class="col-md-5">
		  				<button type="submit" class="btn btn-secondary"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button>
		  				<button type="submit" class="btn btn-light"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></button>
		  				<button type="submit" class="btn btn-light"> <i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></button>
		  			</div>
		    	</div>	
	    	</div>
	    </div>
	
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="foodGroup.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<a href="/ref/foodGroup/form" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
               	</div>
        	</div>
		</div>
		<div class="card-body"><fmt:message key="foodGroup.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="foodGroup.code"/></th>
						<th><fmt:message key="foodGroup.nameVn"/></th>
						<th><fmt:message key="foodGroup.nameEn"/></th>
						<th><fmt:message key="foodGroup.parent"/></th>					
						<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="foodGroup" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${foodGroup.code}"></c:out></td>
							<td><c:out value="${foodGroup.nameVn}"></c:out></td>
							<td><c:out value="${foodGroup.nameEn}"></c:out></td>
							<td><c:out value="${foodGroup.parent.nameVn}"></c:out></td>
							<td class="text-left">
								<div class="list-icons">
									<a href="<c:url value='/ref/foodGroup/form?id=${foodGroup.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
			                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
									<a href="javascript(0);" data-target="#modal_default_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.foodGroup.foodItem.list'/>"  data-container="body"><i class="icon-eye" style="color: black;"></i></a>
									<!-- modal -->
									<div id="modal_default_${cnt.index}" role="dialog" class="modal fade" tabindex="-1">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title"><fmt:message key="foodGroup.foodItem.list"/></h5>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
					
												<div class="modal-body">
													<h6 class="font-weight-semibold"><fmt:message key="foodItem.title.list.description"/></h6>
													<div class="table-responsive">
													<table class="table table-bordered table-striped">
														<thead>
															<tr class="table-success">
																<th>#</th>
																<th><fmt:message key="foodItem.nameVn"/></th>
																<th><fmt:message key="foodItem.nameEn"/></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="foodItem" items="${foodGroup.foodItems }" varStatus="stt">
																<tr>
																	<td><c:out value="${stt.count }"></c:out></td>
																	<td>${ foodItem.nameVn }</td>
																	<td>${ foodItem.nameEn }</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													</div>
												</div>
					
												<div class="modal-footer">
													<button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
									
									<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
													<h4 class="modal-title"><fmt:message key="confirm.delete"/></h4>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
												<div class="modal-body">
													<p><fmt:message key="confirm.delete.question"/> <span style="color: blue;">${foodGroup.nameVn}</span></p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
													<a href="<c:url value='/foodGroup/del/${foodGroup.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
												</div>
											</div>
										</div>
									</div>
									<!-- /modal -->
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
	</form:form>
</div>