<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="currencyRate.title"/></title>
    <meta name="menu" content="currencyRateMenu"/>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/ref/currency_rate_list.js'/>"></script>
</head>

<div class="content">
	<form:form id="currencyRateForm" action="${ctx}/ref/currencyRate/list" method="post" modelAttribute="criteria">
	<!-- \Searching -->
	<div class="card">
	    <div class="card-header bg-navbar text-white header-elements-inline">
               <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
           </div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6 col-sm-12">
				   <label><fmt:message key="currency.rate"/></label>
				   <input class="form-control" type="number" value="${criteria.rate}" name="rate" placeholder="Nhập rateClass..."/>
				</div>
				<div class="col-md-6 col-sm-12">
				   <label><fmt:message key="currencyRate.status"/></label>
				   <select name="status" id="selectItem" data-placeholder="Lựa chọn trạng thái" class="form-control select2" data-fouc>
						<option value="">&nbsp;</option>
						<option value="rsDeleted" ${criteria.status eq 'rsDeleted' ? 'selected':''}><fmt:message key='currency.status.rsDeleted'/></option>
						<option value="rsDraft" ${criteria.status eq 'rsDraft' ? 'selected':''}><fmt:message key='currency.status.rsDraft'/></option>
						<option value="rsInactive" ${criteria.status eq 'rsInactive' ? 'selected':''}><fmt:message key='currency.status.rsInactive'/></option>
						<option value="rsActive" ${criteria.status eq 'rsActive' ? 'selected':''}><fmt:message key='currency.status.rsActive'/></option>
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
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="currencyRate.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CURATE_ALL,ROLE_CURATE_SYNC')">
						<a href="<c:url value='/currencyRate/sync-from-rk7'/>" id="btnSyncFromRk7" class="btn btn-sm btn-primary"><i class="icon-download mr-2"></i><fmt:message key="button.sync.from.server"/></a> &nbsp; 
					</security:authorize>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CURATE_ALL,ROLE_CURATE_RES_SYNC')">
						<button type="button" id="btnSyncToRestaurant" class="btn btn-sm btn-primary"><i class="icon-lan mr-2"></i><fmt:message key="button.sync.to.restaurant"/></button>
					</security:authorize>
				</div>
			</div>
		</div>
		<div class="card-body"><fmt:message key="currencyRate.title.list.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="currencyRate.ident"/></th>
						<th><fmt:message key="currency.rate"/></th>
						<th><fmt:message key="currencyRate.status"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="3" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="currencyRate" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${currencyRate.itemIdent}"></c:out></td>
							<td><c:out value="${currencyRate.rate}"></c:out></td>
							<td class="text-center"><c:if test="${currencyRate.status ne null}"><span class="badge ${(currencyRate.status eq 'rsActive') ? 'badge-primary' : 'badge-warning'}"><fmt:message key="currency.status.${currencyRate.status}"/></span></c:if></td>
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