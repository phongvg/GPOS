<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="orderCategory.list.title"/></title>
    <meta name="menu" content="orderCategoryMenu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    
    <script src="<c:url value='/themes/admin/assets/js/orderCategory_list.js'/>"></script>
</head>

<div class="content">
	<form:form id="orderCategoryListForm" modelAttribute="criteria" action="${ctx}/orderCategory/list"  method="post">
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-md-3 offset-md-1"><input class="form-control" type="text" value="${criteria.code}" name="code" placeholder="Nhập mã OrderCategory..."/></div>
				<div class="col-md-3"><input class="form-control" type="text" value="${criteria.name}" name="nameVn" placeholder="Nhập tên OrderCategory..."/></div>
				<div class="form-group col-md-3">
					<select name="status" id="selectItem" data-placeholder="Lựa chọn trạng thái" class="form-control select-search" data-fouc>
						<option value="">&nbsp;</option>
						<option value="0" ${criteria.status eq 0 ? 'selected':''} ><fmt:message key='currencyRate.status.0'/></option>
						<option value="1" ${criteria.status eq 1 ? 'selected':''}><fmt:message key='currencyRate.status.1'/></option>
						<option value="2" ${criteria.status eq 2 ? 'selected':''}><fmt:message key='currencyRate.status.2'/></option>
						<option value="3" ${criteria.status eq 3 ? 'selected':''}><fmt:message key='currencyRate.status.3'/></option>
					</select>
				</div>
	  			<div class="col-md-2">
	  				<button type="submit" class="btn btn-secondary" onclick="searchFunction()"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button>
	  			</div>
	    	</div>	
		</div>
	</div>
	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="orderCategory.list.title"/></span>
			
			<div class="header-elements">
				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERCATE_ALL,ROLE_ORDERCATE_SYNC')">
				<div class="list-icons">
					<a href="<c:url value='/orderCategory/sync-from-rk7'/>" id="sync-from-rk7" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-download mr-2"></i><fmt:message key="button.sync.from.server"/></a> &nbsp; 
				</div>
				</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERCATE_ALL,ROLE_ORDERCATE_RES_SYNC')">
				<div class="list-icons"><button type="button" id="sync-to-restaurant" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-lan mr-2"></i><fmt:message key="button.sync.to.restaurant"/></button> &nbsp;
				</div> 
				</security:authorize>
			</div>
			
		</div>
		<div class="card-body"><fmt:message key="orderCategory.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="orderCategory.code"/></th>
						<th><fmt:message key="orderCategory.nameVn"/></th>
<%-- 						<th><fmt:message key="orderCategory.nameEn"/></th> --%>
						<th><fmt:message key="orderCategory.status"/></th>
						<%-- <security:authorize access="hasRole('ROLE_ORDERCATE_EDIT')">
							<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
						</security:authorize> --%>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="orderCategory" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td>${orderCategory.code}</td>
							<td>${orderCategory.name}</td>
<%-- 							<td>${orderCategory.nameEn}</td> --%>
							<td class="text-center"><span class="badge ${(orderCategory.status eq 3) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="orderCategory.status.${orderCategory.status}"/></span></td>
							<%-- <security:authorize access="hasRole('ROLE_ORDERCATE_EDIT')">			
								<td class="text-left">
									<div class="list-icons">
										<a href="javascript(0);" data-target="#modal_default_${cnt.index}" class="list-icons-item" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.foodGroup.foodItem.list'/>"  data-container="body"><i class="icon-eye"></i></a>
										<a href="<c:url value='/orderCategory/form?id=${orderCategory.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
									</div>
								</td>
							</security:authorize> --%>
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

<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_select2.js'/>"></script>

<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>

<script>
	function searchFunction(){
		$('#page').val(0);
	}
	$(document).ready(function() {
		$('#sync-from-rk7').on('click', function() {
			$('#pleaseWaitDialog').modal();
		});
	});
</script>