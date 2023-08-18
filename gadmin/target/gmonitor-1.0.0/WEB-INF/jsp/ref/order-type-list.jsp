<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderType.list.title"/></title>
    <meta name="menu" content="orderTypeMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/ref/order_type_list.js'/>"></script>
</head>

<div class="content">
	<form:form id="orderTypeForm" modelAttribute="criteria" action="${ctx}/ref/orderType/list"  method="post">
	<!-- \Searching -->
	<div class="card">
	    <div class="card-header bg-navbar text-white header-elements-inline">
               <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
           </div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="orderType.code"/></label>
				   <input class="form-control" type="text" value="${criteria.code}" name="code" placeholder="Nhập mã orderType..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="currency.nameVn"/></label>
				   <input class="form-control" type="text" value="${criteria.name}" name="name" placeholder="Nhập tên orderType..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="currencyRate.status"/></label>
				   <select name="status" id="selectItem" data-placeholder="Lựa chọn trạng thái" class="form-control select2" data-fouc>
						<option value="">&nbsp;</option>
						<option value="0" ${criteria.status eq 0 ? 'selected':''} ><fmt:message key='currencyRate.status.0'/></option>
						<option value="1" ${criteria.status eq 1 ? 'selected':''}><fmt:message key='currencyRate.status.1'/></option>
						<option value="2" ${criteria.status eq 2 ? 'selected':''}><fmt:message key='currencyRate.status.2'/></option>
						<option value="3" ${criteria.status eq 3 ? 'selected':''}><fmt:message key='currencyRate.status.3'/></option>
					</select>
				</div>
			</div>
			<div class="row mt-3 text-right">
			    <div class="col-md-12">
                    <button type="button" id="btnSubmit" class="btn btn-secondary"><i class="icon-search4"></i><fmt:message key="button.search" /></button>
                </div>
			</div>
		</div>
	</div>
	<!-- /Searching -->
    
	<!-- table -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="orderType.list.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERTYPE_ALL,ROLE_ORDERTYPE_SYNC')">
						<a href="<c:url value='/orderType/sync-from-rk7'/>" id="btnSyncFromRk7" class="btn btn-sm btn-primary mr-2" ><i class="icon-download mr-2"></i><fmt:message key="button.sync.from.server"/></a>
					</security:authorize>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERTYPE_ALL,ROLE_ORDERTYPE_RES_SYNC')">
						<button type="button" id="btnSyncToRestaurant" class="btn btn-sm btn-primary"><i class="icon-lan"></i><fmt:message key="button.sync.to.restaurant"/></button>
					</security:authorize>
				</div>
			</div>
		</div>
		<div class="card-body"><fmt:message key="orderType.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="orderType.code"/></th>
						<th><fmt:message key="currency.nameVn"/></th>
						<th><fmt:message key="currency.status"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="4" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="orderType" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${orderType.code}"></c:out></td>
							<td><c:out value="${orderType.name}"></c:out></td>
							<td class="text-center"><c:if test="${orderType.status ne null}"><span class="badge ${(orderType.status eq 3) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="orderType.status.${orderType.status}"/></span></c:if></td>
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