<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="catalog.co.sync"/></title>
    <meta name="menu" content="coMenu"/>
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="catalog.co.sync.title"/></span>
			<div class="header-elements"></div>
		</div>

		<div class="card-body">
			<p class="mb-4"><fmt:message key="co.form.sync.description"/></p>
			<form:form id="coForm" action="${ctx}/co/sync-to-restaurant" method="post" class="form-validate-jquery" >
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="param.legend"/></legend>
					<div class="card">
					<div class="card-header header-elements-inline">
						<h6 class="card-title"><fmt:message key="catalog.co.choose.res"/></h6>
						<div class="header-elements">
							<div class="list-icons">
		                		<a class="list-icons-item" data-action="collapse"></a>
		                		<a class="list-icons-item" data-action="reload"></a>
		                		<a class="list-icons-item" data-action="remove"></a>
		                	</div>
	                	</div>
					</div>
					
					<div class="card-body">
					<p></p>
					
						<div class="card card-body border-left-2 tree-checkbox-hierarchical">
							<ul class="mb-0">
								<li>Tổng công ty
									<ul>
										<c:forEach var="zone" items="${zones}">
											<li>${zone.name}
												<ul>
													<c:forEach var="province" items="${zone.provinces}">
														<li>${province.name}
															<ul>
																<c:forEach var="district" items="${province.districts}" varStatus="cnt">
																	<li>${district.name}
																		<ul>
																			<c:forEach var="districtBrand" items="${district.districtBrands}" varStatus="cntBrand">
																				<li>${districtBrand.brand.name}
																					<ul>
																						<c:forEach var="restaurant" items="${districtBrand.restaurants}">			
																							<li>${restaurant.name}</li>
																						</c:forEach>
																					 </ul>
																				</li>
																			</c:forEach>
																		</ul>
																	</li>
																</c:forEach>
															</ul>
														</li>
													</c:forEach>
												</ul>
											</li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
				</fieldset>
				<div class="d-flex justify-content-end align-items-center">
					<a href="<c:url value="/co/catalog-list"/>" id="back" class="btn btn-light"><i class="icon-point-left "></i>&nbsp;&nbsp;&nbsp;<fmt:message key="button.back"/></a>
					<button type="submit" class="btn btn-primary ml-3"><fmt:message key="button.save"/>&nbsp;&nbsp;&nbsp;<i class="icon-database-insert"></i></button>
				</div>
			</form:form>
		</div>
	</div>
</div>

<!-- script select -->
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	<script src="/themes/admin/global_assets/js/plugins/extensions/jquery_ui/core.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/extensions/jquery_ui/effects.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/extensions/cookie.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/forms/styling/switchery.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/trees/fancytree_all.min.js"></script>
	<script src="/themes/admin/global_assets/js/demo_pages/extra_trees.js"></script>