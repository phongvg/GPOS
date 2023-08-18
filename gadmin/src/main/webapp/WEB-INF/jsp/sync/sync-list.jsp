<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="sync.latest.list"/></title>
    <meta name="menu" content="syncLatest"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/sync/sync_list.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
</head>


<div class="content">
	<form:form id="syncForm" modelAttribute="criteria" action="${ctx}/sync-latest-data/list" method="post">
	<input class="form-control" type="hidden" value="${criteria.syncFailIds}" id="syncFailIds" name="syncFailIds"/>
	<input class="form-control" type="hidden" value="${criteria.checkAllItem}" id="checkAllItem" name="checkAllItem"/>
	<!-- \Searching -->
	<div class="card">
	    <div class="card-header bg-navbar text-white header-elements-inline">
               <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
           </div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="restaurant.code" /></label>
				   <input class="form-control" type="text" value="${criteria.restaurantCode}" name="restaurantCode" id="restaurantCode" placeholder="Nhập mã nhà hàng..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="restaurant.name" /></label>
				   <input class="form-control" type="text" value="${criteria.restaurantName}" name="restaurantName" id="restaurantName" placeholder="Nhập tên nhà hàng..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="category.status" /></label>
				   <select name="status" id="status" data-placeholder="Lựa chọn trạng thái" class="form-control select2" data-fouc>
						<option value="">&nbsp;</option>
						<option value="Waiting" ${criteria.status eq "Waiting" ? 'selected':''}><fmt:message key='sync.status.Waiting'/></option>
						<option value="Inprocessing" ${criteria.status eq "Inprocessing" ? 'selected':''}><fmt:message key='sync.status.Inprocessing'/></option>
						<option value="Success" ${criteria.status eq "Success" ? 'selected':''}><fmt:message key='sync.status.Success'/></option>
						<option value="Fail" ${criteria.status eq "Fail" ? 'selected':''}><fmt:message key='sync.status.Fail'/></option>
					</select>
				</div>
			</div>
			<div class="row mt-3 text-right">
			    <div class="col-md-12">
                    <button type="button" id="btnSubmit" class="btn btn-secondary"><i class="icon-search4 mr-2"></i><fmt:message key="button.search" /></button>
                    <button type="button" id="btnReset" class="btn btn-secondary"><i class="icon-rotate-ccw3 mr-2"></i><fmt:message key="button.reset.form" /></button>
                </div>
			</div>
		</div>
	</div>
	<!-- /Searching -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="sync.latest.list.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<button type="button" id="btnResetSync" class="btn btn-primary"><i class="icon-rotate-ccw3 mr-2"></i><fmt:message key="button.reset.sync" /></button>
					<button type="button" id="btnDelete" class="btn btn-primary"><i class="icon-trash mr-2"></i><fmt:message key="button.delete" /></button>
               	</div>
        	</div>
		</div>
		<div class="card-body"><fmt:message key="sync.latest.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>
							<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="checkbox" class="form-check-input-styled" id="checkAll" ${criteria.checkAllItem ? 'checked' : '' }>
								</label>
							</div>
						</th>
						<th>#</th>
						<th style="width: 5%"><fmt:message key="sync.res.code"/></th>
						<th style="width: 10%"><fmt:message key="restaurant.name"/></th>
						<th style="width: 10%"><fmt:message key="sync.type.sync"/></th>
						<th style="width: 10%"><fmt:message key="sync.type.data"/></th>
						<th style="width: 10%"><fmt:message key="sync.created.date"/></th>
						<th style="width: 10%"><fmt:message key="sync.start.date"/></th>
						<th style="width: 10%"><fmt:message key="sync.status"/></th>
						<th style="width: 35%"><fmt:message key="sync.result"/></th>
						<th width="5%" class="text-center"><i class="icon-so-open2"></i></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="10" class="text-center text-none-data" ><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="item" varStatus="cnt">
						<tr>
							<td>
								<div class="form-check form-check-inline">
									<c:if test="${item.status == 'Fail'}">
										<c:set var="ids" value="${criteria.syncFailIds}"/>
										<c:set var="id" value="${item.id}"/>
										<c:choose>
									         <c:when test = "${criteria.checkAllItem}">
									           	<label class="form-check-label">
													<input type="checkbox" value="${item.id}" id="checkItem-${item.id}" class="form-check-input-styled checkItem" checked="checked" onclick="selectedCheckBox(${item.id})" />
												</label>
									         </c:when>
									         <c:when test = "${fn:contains(ids, id)}">
									           	<label class="form-check-label">
													<input type="checkbox" value="${item.id}" id="checkItem-${item.id}" class="form-check-input-styled checkItem" checked="checked" onclick="selectedCheckBox(${item.id})" />
												</label>
									         </c:when>
									         <c:otherwise>
									            <label class="form-check-label">
													<input type="checkbox" value="${item.id}" id="checkItem-${item.id}" class="form-check-input-styled checkItem" onclick="selectedCheckBox(${item.id})" />
												</label>
									         </c:otherwise>
								      </c:choose>
									
										<c:set var="theString" value="${criteria.syncFailIds}"/>
									</c:if>
									<c:if test="${item.status != 'Fail'}">
										<label class="uniform-checker disabled">
											<input type="checkbox" id="checkItem-${item.id}" class="form-check-input-styled" disabled="disabled"/>
										</label>
									</c:if>
								</div>
							</td>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${item.restaurantCode}"></c:out></td>
							<td><c:out value="${item.restaurantName}"></c:out></td>
							<td><fmt:message key="sync.type.sync.${item.typeSync}"/></td>
							<td><fmt:message key="sync.type.data.${item.typeData}"/></td>
							<td><c:out value="${item.displayCreatedDate}"></c:out></td>
							<td><c:out value="${item.displaySyncStartDate}"></c:out></td>
							<td><fmt:message key="sync.status.${item.status}"/></td>
							<td><c:out value="${item.result}"></c:out></td>
							<td class="text-left">
								<div class="list-icons">
			                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
									<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
													<h4 class="modal-title"><fmt:message key="confirm.delete.question"/></h4>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
												<div class="modal-body">
													<p><fmt:message key="organization.confirm.success"/> <span style="color: blue;">${item.restaurantCode}</span></p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
													<a href="<c:url value='/sync/del/${item.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
												</div>
											</div>
										</div>
									</div>
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_SYNC_NOW_VIEW')">
										<c:if test="${item.status != 'Inprocessing'}">
											<a href="javascript(0);" data-target="#syncData_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.sync.now'/>"  data-container="body"><i class="icon-rotate-ccw3 mr-2"></i></a>
										</c:if>
									</security:authorize>
									<div class="modal fade" id="syncData_${cnt.index}" role="dialog">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
													<h4 class="modal-title"><fmt:message key="confirm.sync.description"/></h4>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>
												<div class="modal-body">
													<p><fmt:message key="confirm.sync.data.success"/> <span style="color: blue;">${item.restaurantCode}</span></p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
													<a href="<c:url value='/sync-now/${item.id}' />" id="syncData"  class="btn btn-primary"><fmt:message key="button.confirm"/></a>
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
	</form:form>
</div>
