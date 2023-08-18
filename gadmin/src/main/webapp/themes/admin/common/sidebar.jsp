<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

		<div class="sidebar sidebar-light sidebar-main sidebar-expand-md">
			<!-- Sidebar mobile toggler -->
			<div class="sidebar-mobile-toggler text-center">
				<a href="#" class="sidebar-mobile-main-toggle"><i class="icon-arrow-left8"></i></a>
				Navigation
				<a href="#" class="sidebar-mobile-expand"><i class="icon-screen-full"></i><i class="icon-screen-normal"></i></a>
			</div>
			<!-- /sidebar mobile toggler -->


			<!-- Sidebar content -->
			<div class="sidebar-content">

				<!-- User menu -->
				<div class="sidebar-user">
					<div class="card-body">
						<div class="media">
							<div class="mr-3"><a href="#"><img src="<c:url value='/themes/admin/global_assets/images/placeholders/placeholder.jpg'/>" width="38" height="38" class="rounded-circle" alt=""></a></div>
							<div class="media-body">
								<div class="media-title font-weight-semibold"><%-- <fmt:message key="label.hello"/> ${USERNAME} --%>Hello, ${pageContext.request.remoteUser}</div>
								<div class="font-size-xs opacity-50"><i class="icon-pin font-size-sm"></i> &nbsp;<!-- Santa Ana, CA --></div>
							</div>
							<div class="ml-3 align-self-center"><a href="#" class="text-white"><i class="icon-cog3"></i></a></div>
						</div>
					</div>
				</div>
				<!-- /user menu -->

				<!-- Main navigation -->
				<div class="card card-sidebar-mobile">
					<ul class="nav nav-sidebar" data-nav-type="accordion">

						<!-- Main -->
						<li class="nav-item-header"><div class="text-uppercase font-size-xs line-height-xs">Main</div> <i class="icon-menu" title="Main"></i></li>
						<li class="nav-item"><a href="<c:url value='/'/>" class="nav-link active"><i class="icon-home4"></i><span><fmt:message key="menu.home"/></span></a></li>
						
						<!-- System Manager -->
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUP_VIEW,ROLE_SYS_PARAM_VIEW,ROLE_ACC_VIEW,ROLE_ACC_ALL,ROLE_GROUP_ALL,ROLE_SYS_ALL')">
						<li class="nav-item nav-item-submenu">
							<a href="#" class="nav-link sub-menu-title"><i class="icon-cog3"></i> <span><fmt:message key="menu.system"/></span></a>
							<ul class="nav nav-group-sub" data-submenu-title="<fmt:message key="menu.system"/>">
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_SYS_PARAM_VIEW,ROLE_SYS_ALL')">
 								<li class="nav-item"><a menu="parameterMenu" href="<c:url value='/system/systemParameter/list'/>" class="nav-link"><fmt:message key="menu.system.parameter"/></a></li>
 								</security:authorize>
 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUP_VIEW,ROLE_GROUP_ALL')">
 								<li class="nav-item"><a menu="groupMenu" href="<c:url value='/system/group/list'/>" class="nav-link"><fmt:message key="menu.group"/></a></li>
 								</security:authorize>
 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ACC_VIEW,ROLE_ACC_ALL')">
								<li class="nav-item"><a menu="userMenu" href="<c:url value='/system/user/list'/>" class="nav-link"><fmt:message key="menu.user.man"/></a></li>
								</security:authorize>						
							</ul>
						</li>
						</security:authorize>
						<!-- /System Manager -->
						
						<!-- Reference Data -->
						<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CATEGORY_ALL','ROLE_CURRENCY_ALL','ROLE_FOOD_ALL','ROLE_MODIGROUP_ALL','ROLE_MODISCHEME_ALL','ROLE_MODIDETAIL_ALL','ROLE_MODIFIER_ALL','ROLE_ORDERCATE_ALL','ROLE_ORDERTYPE_ALL','ROLE_ORDERVOID_ALL','ROLE_CURATE_ALL','ROLE_HALL_ALL','ROLE_TABLEKITCHEN_ALL','ROLE_GROUPTYPE_ALL','ROLE_FEATURE_ALL','ROLE_CATEGORY_VIEW','ROLE_CURRENCY_VIEW','ROLE_FOOD_VIEW','ROLE_MODIGROUP_VIEW','ROLE_MODISCHEME_VIEW','ROLE_MODIDETAIL_VIEW','ROLE_MODIFIER_VIEW','ROLE_ORDERCATE_VIEW','ROLE_ORDERTYPE_VIEW','ROLE_ORDERVOID_VIEW','ROLE_CURATE_VIEW','ROLE_HALL_VIEW','ROLE_TABLEKITCHEN_VIEW','ROLE_GROUPTYPE_VIEW','ROLE_FEATURE_VIEW')">
						<li class="nav-item nav-item-submenu">
							<a menu="templateMenu" href="#" class="nav-link"><i class="icon-grid3"></i><span><fmt:message key="menu.reference"/></span></a>
							<ul class="nav nav-group-sub" data-submenu-title="<fmt:message key="menu.user.man"/>">
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CATEGORY_VIEW,ROLE_CATEGORY_ALL')">
									<li class="nav-item"><a menu="categoryMenu" href="<c:url value='/ref/category/list'/>" class="nav-link"><fmt:message key="menu.category"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CURRENCY_VIEW,ROLE_CURRENCY_ALL')">
									<li class="nav-item"><a menu="currencyMenu" href="<c:url value='/ref/currency/list'/>" class="nav-link"><fmt:message key="menu.currency"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FOOD_VIEW,ROLE_FOOD_ALL')">
									<li class="nav-item"><a menu="foodItemMenu" href="<c:url value="/ref/foodItem/list"/>" class="nav-link"><fmt:message key="menu.foodItem"/></a></li>
								</security:authorize>
							
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MODIGROUP_VIEW,ROLE_MODIGROUP_ALL')">
									<li class="nav-item"><a menu="modiGroupMenu" href="<c:url value='/ref/modiGroup/list'/>" class="nav-link"><fmt:message key="menu.modiGroup"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MODISCHEME_VIEW,ROLE_MODISCHEME_ALL')">
									<li class="nav-item"><a menu="modiSchemeMenu" href="<c:url value='/ref/modiScheme/list'/>" class="nav-link"><fmt:message key="menu.modiScheme"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MODIDETAIL_VIEW,ROLE_MODIDETAIL_ALL')">
									<li class="nav-item"><a menu="modiSchemeDetailMenu" href="<c:url value='/ref/modiSchemeDetail/list'/>" class="nav-link"><fmt:message key="menu.modiSchemeDetail"/></a></li>
								</security:authorize>
							
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MODIFIER_VIEW,ROLE_MODIFIER_ALL')">
									<li class="nav-item"><a menu="modifierMenu" href="<c:url value="/ref/modifier/list"/>" class="nav-link"><fmt:message key="menu.modifier"/></a></li>							
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERCATE_VIEW,ROLE_ORDERCATE_ALL')">
									<li class="nav-item"><a menu="orderCategoryMenu" href="<c:url value='/ref/orderCategory/list'/>" class="nav-link"><fmt:message key="menu.orderCategory"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERTYPE_VIEW,ROLE_ORDERTYPE_ALL')">
									<li class="nav-item"><a menu="orderTypeMenu" href="<c:url value='/ref/orderType/list'/>" class="nav-link"><fmt:message key="menu.orderType"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_ORDERVOID_VIEW,ROLE_ORDERVOID_ALL')">
									<li class="nav-item"><a menu="orderVoidMenu" href="<c:url value='/ref/orderVoid/list'/>" class="nav-link"><fmt:message key="menu.orderVoid"/></a></li>
								</security:authorize>
					
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CURATE_VIEW,ROLE_CURATE_ALL')">
									<li class="nav-item"><a menu="currencyRateMenu" href="<c:url value='/ref/currencyRate/list'/>" class="nav-link"><fmt:message key="menu.currencyRate"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_HALL_VIEW,ROLE_HALL_ALL')">
									<li class="nav-item"><a menu="hallPlanMenu" href="<c:url value='/ref/hallPlan/list'/>" class="nav-link"><fmt:message key="menu.hallPlan"/></a></li>
								</security:authorize>
								
 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_TABLEKITCHEN_VIEW,ROLE_TABLEKITCHEN_ALL')"> 
								<li class="nav-item"><a menu="tableKitchenMenu" href="<c:url value='/ref/tableKitchen/list'/>" class="nav-link"><fmt:message key="menu.tableKitchen"/></a></li>
 								</security:authorize>
							
 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GUESTTYPE_VIEW,ROLE_GUESTTYPE_ALL')">  
								<li class="nav-item"><a menu="guestTypeMenu" href="<c:url value='/ref/guestType/list'/>" class="nav-link"><fmt:message key="menu.guestType"/></a></li>
 								</security:authorize> 
							
 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_EMPLOYEEMENU_VIEW,ROLE_EMPLOYEEMENU_ALL')"> 
								<li class="nav-item"><a menu="employeeMenu" href="<c:url value='/ref/employee/list'/>" class="nav-link"><fmt:message key="menu.employee"/></a></li>
 								</security:authorize> 
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GROUPTYPE_VIEW,ROLE_GROUPTYPE_ALL')">
									<li class="nav-item"><a menu="menuTypeMenu" href="<c:url value='/ref/menuType/list'/>" class="nav-link"><fmt:message key="menu.menuType"/></a></li>
								</security:authorize>
								
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_FEATURE_VIEW,ROLE_FEATURE_ALL')">
									<li class="nav-item"><a menu="featureMenu" href="<c:url value='/ref/feature/list'/>" class="nav-link"><fmt:message key="menu.feature"/></a></li>
								</security:authorize>

 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GUESTTYPE_VIEW,ROLE_GUESTTYPE_ALL')">  
								<li class="nav-item"><a menu="restaurantMasterMenu" href="<c:url value='/ref/restaurantMaster/list'/>" class="nav-link"><fmt:message key="menu.restaurantMaster"/></a></li>
 								</security:authorize> 
 								
 								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_GUESTTYPE_VIEW,ROLE_GUESTTYPE_ALL')">  
								<li class="nav-item"><a menu="kaloGroupMenu" href="<c:url value='/ref/kaloGroup/list'/>" class="nav-link"><fmt:message key="menu.kaloGroup"/></a></li>
 								</security:authorize>
								
							</ul>
						</li>
						</security:authorize>
						<!-- /Reference Data -->
						
						<!-- Catalog -->
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_ALL,ROLE_CO_ALL,ROLE_KDS_ALL,ROLE_PARAM_ALL,ROLE_MENU_VIEW,ROLE_CO_VIEW,ROLE_KDS_VIEW,ROLE_PARAM_VIEW')">
						<li class="nav-item nav-item-submenu">
							<a menu="templateMenu" href="#" class="nav-link"><i class="icon-list2"></i><span><fmt:message key="menu.template"/></span></a>
							<ul class="nav nav-group-sub" data-submenu-title="<fmt:message key="menu.user.man"/>">
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_MENU_VIEW,ROLE_MENU_ALL')">
								<li class="nav-item"><a menu="soMenu" href="<c:url value="/cag/so/list"/>" class="nav-link"><fmt:message key="menu.list.menu"/></a></li>
								</security:authorize>
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CO_VIEW,ROLE_CO_ALL')">
								<li class="nav-item"><a menu="coMenu" href="<c:url value="/co/catalog-list"/>" class="nav-link"><fmt:message key="menu.list.co"/></a></li>
								</security:authorize>
								<%-- <security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_KDS_VIEW,ROLE_KDS_ALL')">
								<li class="nav-item"><a menu="kdsMenu" href="<c:url value="/kds/catalog-list"/>" class="nav-link"><fmt:message key="menu.list.kds"/></a></li>
								</security:authorize> --%>
								<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_PARAM_VIEW,ROLE_PARAM_ALL')">
								<li class="nav-item"><a menu="paramMenu" href="<c:url value="/cag/group-param/list"/>" class="nav-link"><fmt:message key="menu.list.param"/></a></li>
								</security:authorize>
							</ul>
						</li>
						</security:authorize>
						<!-- /Catalog -->
						
						<!-- Brand Management -->
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_BRAND_VIEW,ROLE_RES_ALL')">
						<li class="nav-item nav-item"><a menu="brandMenu" href="<c:url value='/brand/list'/>" class="nav-link"><i class="icon-price-tags2"></i> <span><fmt:message key="menu.brand"/></span></a></li>
						</security:authorize>
						<!-- /Brand Management -->
						
						<!-- Restaurant Management -->
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_TAG_VIEW,ROLE_RES_ALL')">
						<li class="nav-item nav-item"><a menu="restaurantMenu" href="<c:url value='/restaurant/list'/>" class="nav-link"><i class="icon-calendar2"></i> <span><fmt:message key="menu.restaurant"/></span></a></li>
						</security:authorize>
						<!-- /Restaurant Management -->
						
						<!-- Audit Log -->
						<li class="nav-item nav-item"><a menu="auditLogMenu" href="<c:url value='/audit-log/list'/>" class="nav-link"><i class="icon-file-text"></i> <span><fmt:message key="audit.log.title"/></span></a></li>
						<!-- Audit Log -->
						
						<!-- Sync History -->
						<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_RES_TAG_VIEW,ROLE_RES_ALL')">
						<li class="nav-item nav-item-submenu">
							<a menu="templateMenu" href="#" class="nav-link"><i class="icon-history"></i><span><fmt:message key="sync.template"/></span></a>
							<ul class="nav nav-group-sub" data-submenu-title="<fmt:message key="menu.user.man"/>">
								<li class="nav-item"><a menu="syncLatest" href="<c:url value="/sync-latest-data/list"/>" class="nav-link"><fmt:message key="sync.latest.list"/></a></li>
								<li class="nav-item"><a menu="syncHistory" href="<c:url value="/sync-history/list"/>" class="nav-link"><fmt:message key="sync.history.list"/></a></li>
								<li class="nav-item"><a menu="syncAttachment" href="<c:url value="/sync/attchment-history"/>" class="nav-link"><fmt:message key="sync.attachment"/></a></li>
							</ul>
						</li>
						</security:authorize>
						<!-- /Sync History -->
						<!-- /main -->
						
						
				<li class="nav-item nav-item" style="bottom: 0px; position: relative;"><a onclick="changeIcon()" href="#" class="cnavbar-nav-link sidebar-control sidebar-main-toggle nav-link"><i class="fa fa-angle-double-left" id="collapse"></i><span><fmt:message key="menu.collapse"/></span></a></li>
						
					</ul>
				</div>
				<!-- /main navigation -->

			</div>
			<!-- /sidebar content -->
			
		</div>
<script>
	function changeIcon() {
		if($('#collapse').hasClass("fa-angle-double-left")) {
			$('i.fa-angle-double-left').replaceWith("<i class='fa fa-angle-double-right' id='collapse'></i>");
			
		} else {
			$('i.fa-angle-double-right').replaceWith("<i class='fa fa-angle-double-left' id='collapse'></i>");
		}
		
	}
</script>