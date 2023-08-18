<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="feature.list.title"/></title>
    <meta name="menu" content="featureMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
    <script src="<c:url value='/themes/admin/assets/js/ref/feature_list.js'/>"></script>
</head>


<div class="content">
	<form:form id="featureForm" modelAttribute="criteria" action="${ctx}/ref/feature/list"  method="post">
	<!-- \Searching -->
	<div class="card">
	    <div class="card-header bg-navbar text-white header-elements-inline">
               <h6 class="card-title text-navbar"><fmt:message key="label.search" /></h6>
           </div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="feature.code"/></label>
				   <input class="form-control" type="text" value="${criteria.code}" name="code" placeholder="Nhập mã feature..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="feature.nameVn"/></label>
				   <input class="form-control" type="text" value="${criteria.nameVn}" name="nameVn" placeholder="Nhập tên feature..."/>
				</div>
				<div class="col-md-4 col-sm-12">
				   <label><fmt:message key="currencyRate.status"/></label>
				   <select name="status" id="selectItem" data-placeholder="Lựa chọn trạng thái" class="form-control select2" data-fouc>
						<option value="">&nbsp;</option>
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
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="feature.list.title"/></span>
			<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FEATURE_ALL,ROLE_FEATURE_ADD')">
			<div class="header-elements">
				<div class="list-icons">
					<a href="<c:url value='/ref/feature/form'/>" id="sync-from-rk7" class="btn btn-sm btn-primary"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
               	</div>
        	</div>
       	 	</security:authorize>
		</div>
		<div class="card-body"><fmt:message key="feature.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th>#</th>
						<th><fmt:message key="feature.code"/></th>
						<th><fmt:message key="feature.nameVn"/></th>
						<th><fmt:message key="feature.nameEn"/></th>
						<th><fmt:message key="feature.status"/></th>
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FEATURE_ALL,ROLE_FEATURE_EDIT')">
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
					<c:forEach items="${page.content}" var="feature" varStatus="cnt">
						<tr>
							<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							<td><c:out value="${feature.code}"></c:out></td>
							<td><c:out value="${feature.nameVn}"></c:out></td>
							<td><c:out value="${feature.nameEn}"></c:out></td>
							<td class="text-center"><c:if test="${feature.status ne null}"><span class="badge ${(feature.status eq 3) ? 'badge-primary' : 'badge-warning'}"><fmt:message key="feature.status.${feature.status}"/></span></c:if></td>
							<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FEATURE_ALL,ROLE_FEATURE_EDIT')">
							<td class="text-left">
								<div class="list-icons">
									<a href="<c:url value='/ref/feature/form?id=${feature.id}'/>" class="list-icons-item text-primary-600" data-popup="tooltip" title="<fmt:message key='button.edit'/>" data-container="body"><i class="icon-pencil7"></i></a>
									<a href="javascript(0);" data-target="#confirmDel_${cnt.index}" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;" title="<fmt:message key='button.delete'/>"  data-container="body"><i class="icon-trash"></i></a>
									<div class="modal fade" id="confirmDel_${cnt.index}" role="dialog">
										<div class="modal-dialog modal-md">
											<div class="modal-content">
												<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;"><h4 class="modal-title"><fmt:message key="confirm.delete"/></h4><button type="button" class="close" data-dismiss="modal">&times;</button></div>
												<div class="modal-body"><p><fmt:message key="confirm.delete.question"/> <span style="color: blue;"><c:out value="${feature.nameVn}"></c:out></span></p></div>
												<div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel"/></button><a href="<c:url value='/ref/feature/del/${feature.id}' />" id="confirm"  class="btn btn-primary"><fmt:message key="button.confirm"/></a></div>
											</div>
										</div>
									</div>
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
        <!-- /Pagination -->
	</div>
	</form:form>
</div>