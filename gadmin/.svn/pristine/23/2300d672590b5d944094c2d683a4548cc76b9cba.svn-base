<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="feature.title.${from}"/></title>
    <meta name="menu" content="${from}Menu"/>
    <script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
</head>

<form:form id="featureListForm" modelAttribute="criteria" action="${ctx}/feature/list?t=${criteria.ownerType }"  method="post">
<form:hidden path="ownerType"/>
<div class="content">
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-md-7 offset-md-2"><input class="form-control" type="text" name="nameVn" placeholder="Nhập tên ghi chú tiếng việt..."/></div>
		  			<div class="col-md-2"><button type="submit" class="btn btn-secondary"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button></div>
		    	</div>	
	    	</div>
	    </div>
	<!-- Start form user -->
	<div class="card">
		
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="feature.title.list.${from}"/></span>
			<c:if test="${criteria.ownerType eq 'fea'}">
			<div class="header-elements">
				<div class="list-icons">
					<a href="<c:url value='/feature/form?t=${criteria.ownerType}'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
               	</div>
        	</div>
			</c:if>
		</div>
		<div class="card-body"><fmt:message key="feature.form.title.description.${from}"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<!-- <tr class="bg-blue"> -->
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="feature.code"/></th>
						<th><fmt:message key="feature.nameVn"/></th>
						<th><fmt:message key="feature.nameEn"/></th>
						<th><fmt:message key="feature.status"/></th>
						<%-- <th><fmt:message key="feature.status"/></th> --%>
						<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="6" class="text-center font-weight-bold text-danger"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="feature" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td>${feature.code}</td>
							<td>${feature.nameVn}</td>
							<td>${feature.nameEn}</td>
							<td class="text-center"><span class="badge ${(feature.status eq true) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="feature.status.${feature.status}"/></span></td>
							<td class="text-left">
								<div class="list-icons">
									<%-- <a href="javascript(0);" data-target="#modal_default_${cnt.index}" class="list-icons-item" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.modifier.detail'/>"  data-container="body"><i class="icon-eye"></i></a> --%>
								<c:if test="${criteria.ownerType eq 'fea'}">
									<a href="<c:url value='/feature/form?t=${feature.ownerType}&id=${feature.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
								</c:if>
								<!-- modal -->
									<%-- <div class="modal fade" id="confirmstaffDel_${cnt.index}" role="dialog">
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
									</div> --%>
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
</div>
</form:form>