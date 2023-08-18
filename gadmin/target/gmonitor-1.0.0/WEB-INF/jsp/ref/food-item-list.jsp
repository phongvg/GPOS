<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foodItem.title"/></title>
    <meta name="menu" content="foodItemMenu"/>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/ref/food_item_list.js'/>"></script>
</head>

<div class="content">
	<form:form id="foodItemForm" action="${ctx}/ref/foodItem/list" method="post" modelAttribute="criteria">
	<!-- \Searching -->
	<div class="card">
	    <div class="card-header bg-navbar text-white header-elements-inline">
               <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
           </div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="foodItem.sapcode"/></label>
				   <input class="form-control" type="text" value="${criteria.sapCode}" name="sapCode" placeholder="Nhập mã sapCode..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="foodItem.code"/></label>
				   <input class="form-control" type="text" value="${criteria.code}" name="code" placeholder="Nhập mã món ăn..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="foodItem.name"/></label>
				   <input class="form-control" value="${criteria.name}" type="text" name="name" placeholder="Nhập tên món ăn..."/>
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
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="foodItem.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FOOD_ALL,ROLE_FOOD_SYNC')">
					<a href="<c:url value='/foodItem/sync-from-rk7'/>" id="btnSyncFromRk7" class="btn btn-sm btn-primary" ><i class="icon-download mr-2"></i><fmt:message key="button.sync.from.server"/></a>
					</security:authorize>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FOOD_ALL,ROLE_FOOD_RES_SYNC')">
						<button type="button" id="btnSyncToRestaurant" class="btn btn-sm btn-primary"><i class="icon-lan mr-2"></i><fmt:message key="button.sync.to.restaurant"/></button>
					</security:authorize>
				</div>
			</div>
		</div>
		<div class="card-body"><fmt:message key="foodItem.title.list.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="foodItem.id"/></th>
						<th><fmt:message key="foodItem.sapcode"/></th>
						<th><fmt:message key="foodItem.code"/></th>
						<th><fmt:message key="foodItem.name"/></th>
						<th><fmt:message key="foodItem.cookMins"/></th>
						<th><fmt:message key="co.status"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="8" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="foodItem" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${foodItem.itemId}"></c:out></td>
							<td><c:out value="${foodItem.sapCode}"></c:out></td>
							<td><c:out value="${foodItem.code}"></c:out></td>
							<td><c:out value="${foodItem.name}"></c:out></td>
							<td><c:out value="${foodItem.cookMins}"></c:out></td>
							<td><c:out value="${foodItem.status}"></c:out></td>
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