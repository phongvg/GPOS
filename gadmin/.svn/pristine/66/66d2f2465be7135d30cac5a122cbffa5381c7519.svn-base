<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="sync.latest.list"/></title>
    <meta name="menu" content="syncLatest"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/category_list.js'/>"></script>
</head>


<div class="content">
	<form:form id="syncLatestDataListForm" modelAttribute="criteria" action="${ctx}/sync-latest-data/list"  method="post">
	<%-- <div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-md-3 offset-md-1"><input class="form-control" type="text" value="${criteria.code}" name="code" placeholder="Nhập mã category..."/></div>
				<div class="col-md-3"><input class="form-control" type="text" value="${criteria.name}" name="name" placeholder="Nhập tên category..."/></div>
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
    </div> --%>
	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="sync.latest.list.title"/></span>
		</div>
		<div class="card-body"><fmt:message key="sync.latest.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th style="width: 5%"><fmt:message key="sync.res.code"/></th>
						<th style="width: 10%"><fmt:message key="restaurant.name"/></th>
						<th style="width: 10%"><fmt:message key="sync.type.sync"/></th>
						<th style="width: 10%"><fmt:message key="sync.type.data"/></th>
						<th style="width: 15%"><fmt:message key="sync.created.date"/></th>
						<th style="width: 10%"><fmt:message key="sync.status"/></th>
						<th style="width: 35%"><fmt:message key="sync.result"/></th>
						<th  width="5%" class="text-center"><i class="icon-so-open2"></i></th>
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
	$(document).ready(function() {
		$('#sync-from-rk7').on('click', function() {
			$('#pleaseWaitDialog').modal();
		});
	});
</script>
