<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="group.form.title"/></title>
    <meta name="menu" content="groupMenu"/>
	<link href="<c:url 	value='/themes/admin/assets/css/components.min.css'/>" rel="stylesheet" type="text/css">
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/switch.min.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/system/group_form.js'/>"></script>	
	<script src="<c:url value='/themes/admin/assets/js/system/groupRole.js'/>"></script>	
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="group.form.title"/></span>
			<div class="header-elements"></div>
		</div>
		<div class="card-body ">
			<p class="mb-4"><fmt:message key="group.form.title.description"/></p>
			<form:form class="form-validate-jquery" action="${ctx}/system/group/save" method="POST" modelAttribute="group">
				<form:hidden path="id"/>
				<form:hidden path="systemGroup"/>
				
				<fieldset class="mb-3">
				<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="brand.legend"/></legend>
					<div class="row">
						<div class="col-md-3 form-group">
							<label><fmt:message key="group.name" /> <span class="help-block">*</span></label>
						 	<form:input type="text" class="form-control" path="name" required="required" maxlength="100"/>
						</div>
						<div class="col-md-6 form-group">
							<label><fmt:message key="group.description" /></label> 
							<form:input type="text" class="form-control" path="description" maxlength="255"/>
						</div>
						<div class="col-md-3 form-group form-check form-check-switch form-check-switch-left">
							<label class="d-flex align-items-center"><fmt:message key="group.enabled" /></label>
							<input type="checkbox" class="form-control form-check-input form-check-input-switch" name="enabled" value="true" data-on-text="On" data-off-text="Off" data-on-color="success" data-off-color="default" ${!group.enabled ? '' : 'checked'}>
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
															<input type="checkbox" id="system"> 
														</span>
															<label for="system">Tất cả</label>
													</div>
													<c:forEach var="role" items="${roles}">										
													<c:if test="${role.type eq 'System'}">				
														<div class="row mb-1 col-md-3">
															<label><span class="mr-2">
																<c:if test="${role.selected}">
																	<form:checkbox path="roleIds"  value="${role.id}" class="system" checked="checked" />
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds"  value="${role.id}" class="system" />
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
																	<form:checkbox path="roleIds" value="${role.id}" class="account" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" class="account" />
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
																	<form:checkbox path="roleIds" value="${role.id}" class="group1" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" class="group1" />
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
																	<form:checkbox path="roleIds" value="${role.id}" class="catalogMenu" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" class="catalogMenu" />
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
																	<form:checkbox path="roleIds" value="${role.id}" class="catalogCO" checked="checked"/>
																</c:if>
																<c:if test="${!role.selected}">
																	<form:checkbox path="roleIds" value="${role.id }" class="catalogCO" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="catalogKDS" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="catalogKDS" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="catalogParam" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="catalogParam" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reCate" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reCate" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reCu" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reCu" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reFood" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reFood" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reModiGroup" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reModiGroup" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reModiScheme" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reModiScheme" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reModiDetail" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reModiDetail" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reModifier" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reModifier" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reOrderCate" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reOrderCate" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reOrderType" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reOrderType" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reOrderVoid" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reOrderVoid" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reCuRate" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reCuRate" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reHall" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reHall" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reTable" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reTable" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reGuest" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reGuest" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="reEmp" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="reEmp" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="groupType" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="groupType" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="feature" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="feature" />
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
																<form:checkbox path="roleIds" value="${role.id}" class="res" checked="checked"/>
															</c:if>
															<c:if test="${!role.selected}">
																<form:checkbox path="roleIds" value="${role.id }" class="res" />
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
					
					<div class="text-right">
						<a href="<c:url value="/system/group/list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
						<button type="submit" class="btn bg-blue"><fmt:message key="button.save" />&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
					</div>
				</fieldset>
			</form:form>
		</div>
	</div>
</div>
