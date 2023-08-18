<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="restaurant.list.title"/></title>
    <meta name="menu" content="restaurantMenu"/>
    <script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_childcounter.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/restaurant_list.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
</head>


<div class="content">
<form:form id="restaurantListForm" modelAttribute="criteria" action="${ctx}/restaurant/list"  method="post">
	<input type="hidden" id="refId" value="-1"/>
	<input type="hidden" id="refType" value="res"/>
	
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="form-group col-md-2" style="display: inline-flex;">
							<input class="form-control" type="number" name="code" value="${criteria.code}" style="padding-right: 35px;" placeholder="Nhập mã nhà hàng..."/>
						</div>
						<div class="form-group col-md-2" style="display: inline-flex;">
							<input class="form-control" type="text" name="name" value="${criteria.name}" style="padding-right: 35px;" placeholder="Nhập tên nhà hàng hoặc tổ chức..."/>
						</div>
						<div class="form-group col-md-2" style="display: inline-flex;">
							<input class="form-control" type="text" name="ip" value="${criteria.ip}" style="padding-right: 35px;" placeholder="Nhập ip nhà hàng.."/>
						</div>
						<div class="form-group col-md-2 d-flex mt-1" align="center">
							<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="form-check-input-styled" name="online"  value="1" ${criteria.online eq 1 ? 'checked':'' } id="custom_radio_inline_unchecked" data-fouc>
									<fmt:message key="restaurant.online"/>
								</label>
							</div>
							<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="form-check-input-styled" name="online" value="0" ${criteria.online eq 0 ? 'checked':'' } id="custom_radio_inline_checked" data-fouc>
									<fmt:message key="restaurant.offline"/>
								</label>
							</div>
						</div>
			  			<div class="col-md-3" align="right">
			  				<button style="margin-top: 0;margin-bottom: .625rem;" type="submit" name="search" class="btn btn-secondary" onclick="searchFunction()"> <i class="icon-search4"></i> <fmt:message key="button.search"/></button>
			  			</div>
			  			
			    		<div class="col-md-6">
			    			<div class="form-group">
								<fmt:message key="restaurant.status.sync.master.data"/>
							</div>
			    			<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="checkbox" class="form-check-input-styled" id="checkSyncMasterData" name="checkSyncMasterData" ${criteria.checkSyncMasterData ? 'checked':'' } data-fouc>
									<fmt:message key="status.sync.master.fail"/>
								</label>
							</div>
							<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="checkbox" class="form-check-input-styled" id="checkSyncMasterDataSuccess" name="checkSyncMasterDataSuccess" ${criteria.checkSyncMasterDataSuccess ? 'checked':'' } data-fouc>
									<fmt:message key="status.sync.master.success"/>
								</label>
							</div>
			    		</div>
			    		<div class="col-md-6">
			  				<div class="form-group">
								<fmt:message key="restaurant.status.sync.menu"/>
							</div>
			    			<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="checkbox" class="form-check-input-styled" id="checkSyncMenu" name="checkSyncMenu" ${criteria.checkSyncMenu ? 'checked':'' } data-fouc>
									<fmt:message key="status.sync.menu.fail"/>
								</label>
							</div>
							<div class="form-check form-check-inline">
								<label class="form-check-label">
									<input type="checkbox" class="form-check-input-styled" id="checkSyncMenuSuccess" name="checkSyncMenuSuccess" ${criteria.checkSyncMenuSuccess ? 'checked':'' } data-fouc>
									<fmt:message key="status.sync.menu.success"/>
								</label>
							</div>
			    		</div>
			    		
			  			<div class="col-md-12" align="center">
			    			<a href="#advanced-search" data-toggle="collapse"><fmt:message key="restaurant.search.advanced"/></a>
			    		</div>
			    		<div class="collapse col-md-12" id="advanced-search">
			    			<h6><fmt:message key="restaurant.search.tree"/></h6>
			    			<div class="card-body row">
								<span class="offset-md-2" style="padding-top: 0.5%;"><fmt:message key="search.res"/></span>
								<div class="col-md-4"><input class="form-control" type="text" id="search" placeholder="Nhập mã nhà hàng hoặc tên nhà hàng..."/></div>
		  						<div class="col-md-4">
		  							<button type="button" class="btn btn-secondary"  id="btnSearch"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
		  						</div>
							</div>
							<input type="hidden" id="selectedRestaurantCodes" name="selectedRestaurantCodes" value="${criteria.selectedRestaurantCodes}">
							<div id="tree_container">
								<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>
							</div>
			    		</div>
			    	</div>
		    	</div>
		    </div>

	<%-- <c:url var="urlSubmit" value="/appUser/save"></c:url> --%>
	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="restaurant.list.title"/></span>
			
			<div class="header-elements">
				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_ALL,ROLE_CO_IMPORT_EXCEL')">
				<div class="list-icons">
					<a href="<c:url value='/export/restaurant'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.export"/>"><i class="icon-cloud-upload2"></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.export"/></a>&nbsp;
               	</div>
		  		</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_ALL_SYNC')">
				<div class="list-icons">
					<a href="<c:url value='/res/sync-from-server'/>" id="sync-from-rk7" class="btn btn-sm btn-primary" title="<fmt:message key="button.sync.from.server"/>"><i class="icon-download"></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.sync.from.server"/></a>&nbsp;
               	</div>
               	<div class="list-icons">
					<a href="<c:url value='/restaurant/sync-to-res'/>" id="sync-to-res" class="btn btn-sm btn-primary" title="<fmt:message key="button.sync.to.restaurant"/>"><i class="icon-lan"></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.sync.to.restaurant"/></a>&nbsp;
               	</div>
               	</security:authorize>
               	<div class="list-icons">
					<a href="<c:url value='/res/check-online-restaurant'/>" id="check-online-restaurant" class="btn btn-sm btn-primary" title="<fmt:message key="button.sync.online.restaurant"/>"><i class="icon-lan"></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.sync.online.restaurant"/></a>&nbsp;
               	</div>
               	<%-- 
               	<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_SYNC')">
               		<div class="list-icons"><a href="<c:url value='/res/sync-res-info-to-res'/>" id="sync-to-restaurant" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-lan mr-2"></i><fmt:message key="button.sync.to.restaurant"/></a> &nbsp;</div>
               	</security:authorize>
               	--%>
        	</div>
        	
		</div>
		<div class="card-body"><fmt:message key="restaurant.list.title.description"/></div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped" style="min-height: 300px">
				<thead>
					<!-- <tr class="bg-blue"> -->
					<tr class="table-success">
						<th style="width: 3%">#</th>
						<th style="width: 5%"><fmt:message key="restaurant.code"/></th>
						<th style="width: 12%"><fmt:message key="restaurant.name"/></th>
						<%-- <th><fmt:message key="restaurant.brand"/></th> --%>
						<th style="width: 20%"><fmt:message key="restaurant.address"/></th>
						<th><fmt:message key="restaurant.phone"/></th>
						<%-- <th style="width: 15% word-break: break-all; white-space: normal;"><fmt:message key="restaurant.email"/></th> --%>
						<th style="width: 8%"><fmt:message key="restaurant.ip"/></th>
						<th style="width: 4%"><fmt:message key="restaurant.port"/></th>
						<th style="width: 3%"><fmt:message key="restaurant.version"/></th>
						<th style="width: 5%"><fmt:message key="restaurant.online"/></th>
						<th style="width: 10%"><fmt:message key="restaurant.status.sync.master.data"/></th>
						<th style="width: 10%"><fmt:message key="restaurant.status.sync.menu"/></th>
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_VIEW')">
						<th class="text-center" style="width: 30px;"><i class="icon-menu-open2"></i></th>
						</security:authorize>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty page.content}">
						<tr>
							<td colspan="11" class="text-center text-none-data"><fmt:message key="not.data"/></td>
						</tr>
					</c:if>
					<c:forEach items="${page.content}" var="restaurant" varStatus="cnt">
					<tr>
						<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
						<td><c:out value="${restaurant.code}"/></td>
						<td><c:out value="${restaurant.name}"/></td>
						<%-- <td><c:out value="${restaurant.brand.name}"/></td>  --%>
						<td><c:out value="${restaurant.address}"/></td>
						<td><c:out value="${restaurant.phone}"/></td>
						<%-- <td style="width: 5%; word-break: break-all; white-space: normal;"><c:out value="${restaurant.email}"/></td> --%>
						<td><c:out value="${restaurant.ip}"/></td>
						<td><c:out value="${restaurant.port}"/></td>
						<td><c:out value="${restaurant.version}"/></td>
						<td class="text-center">
							<c:choose>
								<c:when test="${not empty restaurant.online && restaurant.online == 0}">
									<i class="icon-circle2 icon-green" id="online_${restaurant.code}" style="color: red;"></i>
								</c:when>
								<c:when test="${not empty restaurant.online && restaurant.online == 1 }">
									<i class="icon-circle2 icon-green" id="online_${restaurant.code}" style="color: green;"></i>
								</c:when>
								<c:otherwise>
									<i class="icon-circle2" id="online_${restaurant.code}" style="color: red;"></i>
								</c:otherwise>
							</c:choose>
						</td>
						<td><c:out value="${restaurant.syncStatus[0].masterDataSyncStatus}"/></td>
						<td><c:out value="${restaurant.syncStatus[0].businessSyncStatus}"/></td>
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_VIEW')">
						<td class="text-left">
							<div class="dropdown list-icons">
								<a href="#" class="list-icons-item btn btn-default" data-toggle="dropdown"><i class="icon-menu"></i></a>
								<div class="dropdown-menu dropdown-menu-bottom">
									<a href="<c:url value="/restaurant/form?rCode=${restaurant.code}"/>" class="dropdown-item"><i class="icon-pencil7"></i> <fmt:message key="button.edit"/></a>
									<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_ALL,ROLE_RES_CONFIG_VIEW')">
									<a href='<c:url value="/res/so/menu/list?resCode=${restaurant.code }"/>' class="dropdown-item"><i class="icon-list3"></i> <fmt:message key="tab.menu"/></a>
									<a href='<c:url value="/co/form?rCode=${restaurant.code }"/>' class="dropdown-item"><i class="icon-clipboard3"></i><fmt:message key="tab.co"/></a>
									<a href='<c:url value="/kds/form?rCode=${restaurant.code }"/>' class="dropdown-item"><i class="icon-tree7"></i> <fmt:message key="tab.kitchen.fax"/></a>
									<a href="<c:url value="/group-param/form?rCode=${restaurant.code}"/>" class="dropdown-item"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a>
									<a href="<c:url value="/restaurant/device?rCode=${restaurant.code}"/>" class="dropdown-item"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a>
									<a href="<c:url value="/res/kdsPlace/list?rCode=${restaurant.code}"/>" class="dropdown-item"><i class="icon-tree7"></i><fmt:message key="tab.kds"/></a>
									</security:authorize>
									<%-- <a href="<c:url value="/reportMenu/list?rCode=${restaurant.code}"/>" class="dropdown-item"><i class="icon-file-stats mr-2"></i>Báo cáo</a> --%>
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
