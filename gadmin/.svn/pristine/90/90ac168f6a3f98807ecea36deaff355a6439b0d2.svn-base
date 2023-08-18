<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userForm.title"/></title>
    <meta name="menu" content="userMenu"/>
    
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/notifications/bootbox.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/system/group_role.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/system/user_form.js'/>"></script>
</head>

<div class="content">
	<c:url var="urlSubmit" value="/system/user/save"></c:url>
	<!-- Start form user -->
	<div class="card">
		<div class="card-header header-elements-inline">
			<h4 class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="userForm.title"/></h4>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               	</div>
            </div>
		</div>
		<form:form class="form-validate-jquery" id="userForm" action="${urlSubmit}" method="POST" modelAttribute="appUser" autocomplete="off">
			<div class="card-body">
				<p class="mb-4"><fmt:message key="userForm.title.description"/></p>
				<form:hidden path="id" id="appUserId"/>
				<form:hidden path="accountExpired"/>
				<form:hidden path="password"/>
				<form:hidden path="confirmPassword"/>
				<form:hidden path="accountLocked"/>
				<form:hidden path="credentialsExpired"/>
				<form:hidden path="modifiedDate"/>
				<form:hidden path="createdDate"/>
				<form:hidden path="userLevel"/>
				<form:hidden path="version"/>
				<form:hidden path="passwordChangedDate"/>
				<form:hidden path="createdBy" />
				<form:hidden path="profileId"/>
				<form:hidden path="staff.id"/>
				<form:hidden path="selectedRestaurantCodes" />
				
				<input type="hidden" id="refId" value="${appUser.id}"/>
				<input type="hidden" id="refType" value="user"/>				
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="brand.legend"/></legend>
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
					<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-file-text2 mr-2"></i><fmt:message key="user.info"/></a></li>
					<li class="nav-item"><a href="javascript:UserForm.check('<c:url value="/system/user/apply"/>',${appUser.id});" class="nav-link"><i class="icon-flip-vertical3 mr-2"></i><fmt:message key="userForm.restaurant"/></a></li>
					</ul>
					<div class="row">
						 <div class="col-sm-5 col-md-4">
							<div class="form-group">
								<label><fmt:message key="userForm.username"/><span class="help-block">*</span></label>
								<c:choose>
									<c:when test="${appUser.id ne null}">
										<form:hidden path="username"/>
										<label class="form-control"><c:out value="${appUser.username}"/></label>
									</c:when>
									<c:otherwise>
										<form:input id="username" path="username" type="text" class="form-control" placeholder="nhập tên đăng nhập"/>
										<div><span id="messageCheckCode" style="display: none;"></span></div>
									</c:otherwise>
								</c:choose>
							</div>
						</div> 
						
						<div class="col-sm-5 col-md-4">
							<div class="form-group">
								<label><fmt:message key="userForm.group"/></label>
								<select class="form-control select2" name="groupIds" id="groupIds">
									<c:if test="${empty appUser.appGroups}">
										<option value="">&nbsp;</option>
									</c:if>
									<c:if test="${not empty appGroups}">
										<c:forEach var="group" items="${appGroups}"> 
											<c:if test="${group.enabled}">
											<option value="${group.id}" ${appUser.appGroups[0].id eq group.id ? 'selected':'' }>${group.name}</option>
											</c:if>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						
						<div class="col-sm-2 col-md-4">
							<div class="form-group form-check form-check-switch form-check-switch-left">
								<c:if test="${(appUser.username) ne (pageContext.request.remoteUser)}">
								<label class="d-flex align-items-center"><fmt:message key="userForm.accountEnabled" /></label>
								<input type="checkbox" class="form-control form-check-input form-check-input-switch" name="accountEnabled" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${appUser.accountEnabled ? 'checked' : ''}>
								</c:if>							
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
							 	<label><fmt:message key="userForm.fullname"/><span class="help-block">*</span></label>
								<form:input id="fullname" path="staff.fullname" type="text" class="form-control"  placeholder="nhập tên đầy đủ"  />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
							 	<label><fmt:message key="userForm.email"/><span class="help-block">*</span></label>
								<form:input id="email" path="staff.email" type="email" class="form-control" placeholder="nhập email" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
							 	<label><fmt:message key="userForm.roles"/></label> 
								<table class="table table-bordered">
									<tr>
										<td>
											<label class="d-block font-weight-semibold">Hệ thống</label>
										</td>
										<td>	
											<div class="container">
											<div class="row">
												<div class="row mb-1 col-md-3">	
													<span class="mr-2">									
														<input type="checkbox" class="" id="system"  >
													</span>
													<label for="system">Tất cả</label>
												</div>
												<c:forEach var="role" items="${roles}">										
													<c:if test="${role.type eq 'System'}">				
														<div class="row mb-1 col-md-3">
															<label><span class="mr-2">
																<c:if test="${role.selected}">
																	<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="system" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="system" />
																</c:if>
															</span>
															${role.description}</label>	
														</div>												
													</c:if>
												</c:forEach>
											</div>
										</div>
										</td>		
									</tr>	
									<tr>
										<td colspan="2"><strong>Quản lý tài khoản</strong></td>
									</tr>	
													
									<tr>
									<td>
										<label class="d-block font-weight-semibold">Tài khoản</label>
									</td>
									<td>	
										<div class="container">
											<div class="row">
												<div class="row mb-1 col-md-3">	
													<span class="mr-2">									
														<input type="checkbox" class="" id="account"  >
													</span>
													<label for="account">Tất cả</label>
												</div>
												<c:forEach var="role" items="${roles}">										
													<c:if test="${role.type eq 'Account'}">				
														<div class="row mb-1 col-md-3">
															<label><span class="mr-2">
																<c:if test="${role.selected}">
																	<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="account" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="account" />
																</c:if>
															</span>
															${role.description}</label>	
														</div>												
													</c:if>
												</c:forEach>
											</div>
										</div>
									</td>		
									</tr>
									<tr>
									<td>
										<label class="d-block font-weight-semibold">Nhóm quyền</label>
									</td>
									<td>	
										<div class="container">
											<div class="row">
												<div class="row mb-1 col-md-3">	
													<span class="mr-2">									
														<input type="checkbox"  id="group1">
													</span>
													<label for="group1">Tất cả</label>
												</div>
												<c:forEach var="role" items="${roles}">										
													<c:if test="${role.type eq 'Group'}">				
														<div class="row mb-1 col-md-3">
															<label><span class="mr-2">
																<c:if test="${role.selected}">
																	<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="group1" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="group1" />
																</c:if>
															</span>
															${role.description}</label>	
														</div>												
													</c:if>
												</c:forEach>
											</div>
										</div>
									</td>		
									</tr>
												
								<tr>
									<td colspan="2"><strong>Quản lý Danh mục</strong></td>
								</tr>
								<tr>
									<td>
										<label class="d-block font-weight-semibold">Menu</label>
									</td>
									<td>	
										<div class="container">
											<div class="row">
												<div class="row mb-1 col-md-3">	
													<span class="mr-2">									
														<input type="checkbox"  id="catalogMenu">
													</span>
													<label  for="catalogMenu">Tất cả</label>
												</div>
												<c:forEach var="role" items="${roles}">										
													<c:if test="${role.type eq 'Catalog-menu'}">				
														<div class="row mb-1 col-md-3">
															<label><span class="mr-2">
																<c:if test="${role.selected}">
																	<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="catalogMenu" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="catalogMenu" />
																</c:if>
															</span>
															${role.description}</label>	
														</div>												
													</c:if>
												</c:forEach>
											</div>
										</div>
									</td>		
									</tr>
											
									<tr>
									<td>
											<label class="d-block font-weight-semibold">CO</label>
									</td>
									<td>	
										<div class="container">
											<div class="row">
												<div class="row mb-1 col-md-3">	
														<span class="mr-2">									
															<input type="checkbox"  id="catalogCO">
														</span>
															<label for="catalogCO" >Tất cả</label>
												</div>
												<c:forEach var="role" items="${roles}">										
													<c:if test="${role.type eq 'Catalog-co'}">				
														<div class="row mb-1 col-md-3">
															<label><span class="mr-2">
																<c:if test="${role.selected}">
																	<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="catalogCO" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="catalogCO" />
																</c:if>
															</span>
															${role.description}</label>	
														</div>												
													</c:if>
												</c:forEach>
											</div>
										</div>
								</td>		
								</tr>					
								<tr>
								<td>
									<label class="d-block font-weight-semibold">KDS</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="catalogKDS">
											</span>
											<label for="catalogKDS">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Catalog-kds'}">				
													<div class="row mb-1 col-md-3">
														<label><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="catalogKDS" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="catalogKDS" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Param</label>
								</td>
								
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="catalogParam">
											</span>
											<label for="catalogParam">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Catalog-param'}">				
													<div class="row mb-1 col-md-3">
														<label><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="catalogParam" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="catalogParam" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
										<td colspan="2"><strong>Quản lý Dữ liệu tham chiếu</strong></td>
								</tr>
								
								<tr>
								
								<td>
									<label class="d-block font-weight-semibold">Category</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reCate">
											</span>
											<label  for="reCate">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-category'}">				
													<div class="row mb-1 col-md-3">
														<label><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reCate" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reCate" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Currency</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reCu">
											</span>
											<label  for="reCu">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-currency'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reCu" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reCu" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Food</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox"  id="reFood">
											</span>
											<label  for="reFood">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-food'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reFood" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reFood" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">ModiGroup</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reModiGroup">
											</span>
											<label  for="reModiGroup">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-modigroup'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reModiGroup" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reModiGroup" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">ModiScheme</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox"  id="reModiScheme">
											</span>
											<label  for="reModiScheme">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-modischeme'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reModiScheme" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reModiScheme" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">ModiDetail</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox"  id="reModiDetail">
											</span>
											<label  for="reModiDetail">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-modidetail'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reModiDetail" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reModiDetail" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Modifier</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reModifier">
											</span>
											<label  for="reModifier">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-modifier'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reModifier" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reModifier" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Order Category</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reOrderCate">
											</span>
											<label  for="reOrderCate">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-ordercate'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reOrderCate" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reOrderCate" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Order Type</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reOrderType">
											</span>
											<label  for="reOrderType">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-ordertype'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reOrderType" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reOrderType" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Order Void</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reOrderVoid">
											</span>
											<label  for="reOrderVoid">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-ordervoid'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reOrderVoid" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reOrderVoid" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Currency Rate</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reCuRate">
											</span>
											<label  for="reCuRate">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-curate'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reCuRate" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reCuRate" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Hall</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reHall">
											</span>
											<label  for="reHall">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-hall'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reHall" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reHall" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Table</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reTable">
											</span>
											<label  for="reTable">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-table'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2 ">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reTable" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reTable" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Guest</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox"  id="reGuest">
											</span>
											<label  for="reGuest">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-guest'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reGuest" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reGuest" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Employee</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="reEmp">
											</span>
											<label  for="reEmp">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-employee'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="reEmp" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="reEmp" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Group Type</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="groupType">
											</span>
											<label  for="groupType">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-grouptype'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="groupType" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="groupType" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Feature</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox" id="feature">
											</span>
											<label  for="feature">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Reference-feature'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="feature" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="feature" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>	
								</tr>
								<tr>
									<td colspan="2"><strong>Quản lý Nhà hàng </strong></td>
								</tr>
											
								<tr>
								<td>
									<label class="d-block font-weight-semibold">Nhà hàng</label>
								</td>
								<td>	
									<div class="container">
										<div class="row">
										<div class="row mb-1 col-md-3">	
											<span class="mr-2">									
												<input type="checkbox"  id="res">
											</span>
											<label  for="res">Tất cả</label>
										</div>
											<c:forEach var="role" items="${roles}">										
												<c:if test="${role.type eq 'Restaurant'}">				
													<div class="row mb-1 col-md-3">
														<label class="form-check-label"><span class="mr-2">
															<c:if test="${role.selected}">
																<form:checkbox path="roleIds" value="${role.id}" id="role${role.id}" class="res" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" id="role${role.id}" class="res" />
															</c:if>
														</span>
														${role.description}</label>	
													</div>												
												</c:if>
											</c:forEach>
										</div>
									</div>
								</td>		
								</tr>					
							</table>
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
											<button type="button" class="btn btn-warning" data-dismiss="modal"><fmt:message key="button.back"/></button>
											<button type="submit" id="confirm"  class="btn btn-primary"><fmt:message key="admin.confirm"/></button>
										</div>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-primary"  id="submitForm"> 
								<fmt:message key="button.save"/>
								<i class="icon-paperplane ml-2"></i>
							</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
	<!-- End form user -->
</div>
<!-- script select -->
