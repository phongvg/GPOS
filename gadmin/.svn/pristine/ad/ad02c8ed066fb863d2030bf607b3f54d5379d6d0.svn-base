<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userForm.title"/></title>
    <meta name="menu" content="userMenu"/>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/system/user_apply_form.js'/>"></script>
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<h4 class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="userForm.title"/></h4>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               	</div>
            </div>
		</div>
		<form:form class="form-validate-jquery" id="userForm" action="${ctx}/system/user/saveRestaurantUser" method="POST" modelAttribute="appUser">
			<div class="card-body">
				<p class="mb-4"><fmt:message key="userForm.title.description"/></p>
				<form:input type="hidden" id="refId" path="id"/>
				<form:hidden path="username"/>
				<input type="hidden" id="refType" value="user"/>
				
				<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="<c:url value="/system/user/form?id=${appUser.id}"/>" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="user.info"/></a></li>
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-file-text2 mr-2"></i><fmt:message key="userForm.restaurant"/></a></li>			
					</ul>
					
				 <fieldset class="mb-3">
					<div class="tab-content">
						<div class="card">
							<div class="card-header header-elements-inline">
							</div>
							<div class="card-body">
								<div class="card-body row">
									<span class="offset-md-2" style="padding-top: 0.5%;"><fmt:message key="search.res"/></span>
									<div class="col-md-4">
										<input class="form-control" type="text" name="keyword" id="search" placeholder="Nhập mã nhà hàng hoặc tên nhà hàng..."/>
									</div>
			  						<div class="col-md-4">
			  							<button type="button" class="btn btn-secondary"  id="btnSearch"><i class="icon-search4"></i><fmt:message key="button.search"/></button>
			  						</div>
								</div>
								<input type="hidden" id="selectedRestaurantCodes" name="selectedRestaurantCodes">
								<div id="tree_container">
									<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>
								</div>
							</div>
						</div>
					</div>					
				</fieldset>
				
				<div class="text-right">
					<a href="<c:url value="/system/user/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<c:choose>
						<c:when test="${appUser.id eq -1 }">
							<button type="button" class="btn btn-primary" data-target="#confirmUpdate" data-toggle="modal"  style="cursor: pointer;"><fmt:message key="button.save"/><i class="icon-paperplane ml-2"></i></button>
							<div class="modal fade" id="confirmUpdate" role="dialog">
								<div class="modal-dialog modal-md">
									<div class="modal-content">
										<div class="modal-header" style="background-color: #0F6CB2; color: #ffffff;">
											<h4 class="modal-title"><fmt:message key="admin.confirm.title"/></h4>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>
										<div class="modal-body text-left">
											<p><fmt:message key="admin.confirm.success"/></p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.back"/></button>
											<button type="submit" id="confirm"  class="btn btn-primary"><fmt:message key="admin.confirm"/></button>
										</div>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-primary"><fmt:message key="button.save"/><i class="icon-paperplane ml-2"></i>
							</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</div>
