<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="kds.list.title"/></title>
    <meta name="menu" content="kdsMenu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

	

<!-- Content area -->
<div class="content">
<form:form id="kdsListForm" modelAttribute="criteria" action="${ctx}/kds/catalog-list"  method="post">
	<div class="card">
		<div class="card-body">
			<div class="row">
				<div class="col-md-5 offset-md-2"><form:input class="form-control" type="text" path="name" placeholder="Nhập tên danh mục KDS..."/></div>
	  			<div class="col-md-5"><button type="submit" class="btn btn-secondary" onclick="searchFunction()"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button>
<%-- 	  			<security:authorize access="hasRole('ROLE_IMPORT_EXCEL')"> --%>
<%-- 	  			<a href="#" type="button" class="btn btn-light"> <i class="icon-cloud-download2"></i> <fmt:message key="button.import"/></a> --%>
<%-- 	  			</security:authorize> --%>
<%-- 	  			<security:authorize access="hasRole('ROLE_EXPORT_EXCEL')"> --%>
<%-- 	  			<a href="#" type="button" class="btn btn-light"> <i class="icon-cloud-upload2"></i> <fmt:message key="button.export"/></a> --%>
<%-- 	  			</security:authorize> --%>
	  			</div>
	    	</div>	
    	</div>
    </div>

	<!-- Basic layout-->
	<div class="card">
			<div class="card-header header-elements-inline">
				<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="kds.list.title"/></span>
				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_KDS_ALL,ROLE_KDS_ADD')">
				<div class="header-elements"><div class="list-icons"><a href="<c:url value='/kds/catalog-form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a></div></div>
				</security:authorize>
			</div>
			<div class="card-body"><fmt:message key="kds.list.title.description"/></div>
			<div class="table-responsive">
               	<table class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th class="text-center">#</th>
							<th class="text-center"><fmt:message key="kds.name"/></th>
							<th class="text-center"><fmt:message key="createdDate"/></th>
							<th class="text-center"><fmt:message key="createdBy"/></th>
							<th class="text-center"><fmt:message key="status"/></th>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_KDS_ALL,ROLE_KDS_EDIT')">
							<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
							</security:authorize>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty page.content}">
							<tr>
								<td colspan="6" class="text-center text-none-data"><fmt:message key="not.data"/></td>
							</tr>
						</c:if>
						<c:forEach items="${page.content}" var="kds" varStatus="cnt">
							<tr>
								<td class="text-center"><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
								<td><c:out value="${kds.name}"></c:out></td>
								<td class="text-center"><c:out value="${kds.displayCreatedDate}"></c:out></td>
								<td class="text-center"><c:out value="${kds.createdBy}"></c:out></td>
								<td class="text-center">
									<c:choose>
										<c:when test="${kds.status eq true }">
											<i class="icon-circle2 icon-green" style="color: green;"></i>
										</c:when>
										<c:otherwise>
											<i class="icon-circle2" style="color: red;"></i>
										</c:otherwise>
									</c:choose>
								</td>
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_KDS_ALL,ROLE_KDS_EDIT')">
								<td class="text-center">
									<div class="list-icons">
				                		<a href="<c:url value='/kds/catalog-form?id=${kds.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
<%-- 				                		<a href="javascript(0);" data-target="#confirmstaffDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a> --%>
<%-- 										<div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog"> --%>
<!-- 											<div class="modal-dialog modal-md"> -->
<!-- 												<div class="modal-content"> -->
<%-- 													<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;"><h4 class="modal-title"><fmt:message key="confirm.delete"/></h4><button type="button" class="close" data-dismiss="modal">&times;</button></div> --%>
<%-- 													<div class="modal-body"><p><fmt:message key="confirm.delete.question"/> <span style="color: blue;">${menu.nameVn}</span></p></div> --%>
<%-- 													<div class="modal-footer"><button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.cancel"/></button><a href="<c:url value='/menu/del/${menu.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a></div> --%>
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
				                	</div>
								</td>
								</security:authorize>
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
	</div>
	<!-- /basic layout -->
	</form:form>
</div>
<!-- /content area -->

<script>
	function searchFunction(){
		$('#page').val(0);
	}
</script>