<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="sync.attachment"/></title>
    <meta name="menu" content="syncAttachment"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/sync_history.js'/>"></script>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	
</head>


<div class="content">
	<form:form id="syncAttachmentForm" modelAttribute="criteria" action="${ctx}/sync/attchment-history"  method="post">
    <div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-md-5 offset-md-2"><input class="form-control" type="text" value="${criteria.restaurantCode}" name="restaurantCode" placeholder="Nhập mã nhà hàng..."/></div>
	  			<div class="col-md-5"><button type="submit" class="btn btn-secondary" onclick="searchFunction()"><i class="icon-search4"></i><fmt:message key="button.search"/></button></div>
	    	</div>	
    	</div>
    </div>
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="sync.attachment"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_ADD')">
              			<a href="<c:url value='/sync/attachment'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.sync.server.res"/>"><fmt:message key="button.sync.server.res"/><i class="icon-database-insert ml-2"></i></a>
              			<button type="button" id="syncFileToRes" class="btn btn-primary ml-3"><fmt:message key="button.sync.all.file.to.server.res"/><i class="icon-database-insert ml-2"></i></button>
              		</security:authorize>
               	</div>
        	</div>
		</div>
		<div class="card-body"><fmt:message key="sync.attachment.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th style="width: 10%"><fmt:message key="sync.res.code"/></th>
						<th style="width: 10%"><fmt:message key="restaurant.name"/></th>
						<th style="width: 10%"><fmt:message key="sync.item.code"/></th>
						<th style="width: 15%"><fmt:message key="sync.type.data"/></th>
						<th style="width: 15%"><fmt:message key="sync.type.attachment"/></th>
						<th style="width: 35%" class="text-center"><fmt:message key="foodItem.image" /></th>
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
							<td><c:out value="${item.moduleCode}"></c:out></td>
							<td><c:out value="${item.moduleType}"></c:out></td>
							<td><c:out value="${item.functionType}"></c:out></td>
							<td style="text-align:center;">
								<c:choose>
								    <c:when test="${empty item.url}">
								       	<img alt="" height="150" width="150" title="default_icon.png" src='<c:url value="/themes/admin/global_assets/images/default_icon.png"></c:url>'>
								    </c:when>
								    <c:otherwise>
									     <a class="btn" rel="popover" data-img="<c:url value='${item.url}'/>"><img src="<c:url value='${item.url}'/>" title="<c:url value='${item.fileName}'/>" height="150" width="150"></a>
								    </c:otherwise>
								</c:choose>
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
</script>
