<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="sync.history.list"/></title>
    <meta name="menu" content="syncHistory"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/sync_history.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	
</head>


<div class="content">
	<form:form id="syncHistoryListForm" modelAttribute="criteria" action="${ctx}/sync-history/list"  method="post">
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="form-group col-md-5">
					<input class="form-control" type="text" value="${criteria.restaurantCode}" name="restaurantCode" placeholder="Nhập mã nhà hàng..."/>
				</div>
				<div class="col-md-5">
	    			<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="checkbox" class="form-check-input-styled" id="checkSyncDataFail" name="checkSyncDataFail" ${criteria.checkSyncDataFail ? 'checked':'' } data-fouc>
							<fmt:message key="sync.status.fail"/>
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="checkbox" class="form-check-input-styled" id="checkSyncDataSuccess" name="checkSyncDataSuccess" ${criteria.checkSyncDataSuccess ? 'checked':'' } data-fouc>
							<fmt:message key="sync.statusr.success"/>
						</label>
					</div>
	    		</div>
	  			<div class="col-md-2">
	  				<button type="submit" class="btn btn-secondary" onclick="searchFunction()"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button>
	  			</div>
	    	</div>
    	</div>
    </div>
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="sync.history.list.title"/></span>
		</div>
		<div class="card-body"><fmt:message key="sync.history.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th style="width: 5%"><fmt:message key="sync.res.code"/></th>
						<th style="width: 10%"><fmt:message key="restaurant.name"/></th>
						<th style="width: 10%"><fmt:message key="sync.type.sync"/></th>
						<th style="width: 10%"><fmt:message key="sync.type.data"/></th>
						<th style="width: 10%"><fmt:message key="sync.created.date"/></th>
						<th style="width: 10%"><fmt:message key="sync.start.date"/></th>
						<th style="width: 10%"><fmt:message key="sync.end.date"/></th>
						<th style="width: 5%"><fmt:message key="sync.status"/></th>
						<th style="width: 30%"><fmt:message key="sync.result"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="7" class="text-center text-none-data" ><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="item" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${item.restaurantCode}"></c:out></td>
							<td><c:out value="${item.restaurantName}"></c:out></td>
							<td><fmt:message key="sync.type.sync.${item.typeSync}"/></td>
							<td><fmt:message key="sync.type.data.${item.typeData}"/></td>
							<td><c:out value="${item.displayCreatedDate}"></c:out></td>
							<td><c:out value="${item.displaySyncStartDate}"></c:out></td>
							<td><c:out value="${item.displaySyncEndDate}"></c:out></td>
							<td><fmt:message key="sync.status.${item.status}"/></td>
							<td><c:out value="${item.result}"></c:out></td>
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

<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>

<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>


<script>
	function searchFunction(){
		$('#page').val(0);
	}
</script>
