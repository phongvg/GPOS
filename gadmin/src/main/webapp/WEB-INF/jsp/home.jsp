<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="home.title"/></title>

</head>

			<div class="content">
				<div class="row">
					<div class="card-header header-elements-inline">
						<security:authorize access="hasAnyRole('ROLE_SYNC_DATA, ROLE_ADMIN')">
							<div class="header-elements">
								<div class="list-icons">
 									<a href="<c:url value='/all/sync-from-rk7'/>" id="sync-from-rk7" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>">
										<i class="icon-download mr-2"></i>
										<fmt:message key="button.sync.all.from.server"/>
									</a> &nbsp;
									<a href="<c:url value='/all/sync-to-res'/>" id="sync-to-res" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>">
										<i class="icon-lan"></i>
										<fmt:message key="button.sync.all.to.res"/>
									</a> &nbsp;
<%--									<button type="button" id="sync-all-from-rk7" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>">
										<i id="icon_sync" class="icon-download mr-2"></i>
										<i id="icon_processing" class="icon-spinner spinner mr-2" style="display: none"></i>
										<fmt:message key="button.sync.all.from.server"/>
									</button>  --%>
								</div>
							</div>
						</security:authorize>
					</div>
				</div>
						<!-- Quick stats boxes -->
						<div class="row">
 						<security:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
 							<div class="col-lg-4">

								<!-- Members online -->
								<div class="card bg-teal-700">
									<div class="card-body">
										<div class="d-flex">
											<h3 class="font-weight-semibold mb-0 number">
											<c:choose>
												<c:when test="${not empty countRestaurant}">${countRestaurant}</c:when>
												<c:otherwise><fmt:message key='restaurant.unsync.all'/></c:otherwise>
											</c:choose>

											</h3>
											<!-- <span class="badge bg-teal-800 badge-pill align-self-center ml-auto p-1"> </span> -->
					                	</div>

					                	<div>
						                	<security:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
												<a href="<c:url value='/restaurant/list'/>" class="text-light"><fmt:message key='restaurant.amount'/></a>
											</security:authorize>
											<!-- Check account have role view restaurant -->
					                		<security:authorize access="!hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
											<fmt:message key='restaurant.amount'/>
											</security:authorize>
<!-- 											<div class="font-size-sm opacity-75">489 avg</div> -->
										</div>
									</div>

									<div class="container-fluid">
										<div id="members-online"></div>
									</div>
								</div>
								<!-- /members online -->

							</div>
							<div class="col-lg-4">

								<!-- Members online -->
								<div class="card bg-teal-400">
									<div class="card-body">
										<div class="d-flex">
											<h3 class="font-weight-semibold mb-0 number">
											<c:choose>
												<c:when test="${not empty countRestaurant}">${countRestaurantActive}</c:when>
												<c:otherwise><fmt:message key='restaurant.unsync'/></c:otherwise>
											</c:choose>

											</h3>
											<!-- <span class="badge bg-teal-800 badge-pill align-self-center ml-auto p-1"> </span> -->
					                	</div>

					                	<div>
					                		<security:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
												<a href="<c:url value='/restaurant/list?status=1'/>" class="text-light"><fmt:message key='restaurant.amount.status.active'/></a>
											</security:authorize>
											<!-- Check account have role view restaurant -->
					                		<security:authorize access="!hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
											<fmt:message key='restaurant.amount.status.active'/>
											</security:authorize>

<!-- 											<div class="font-size-sm opacity-75">489 avg</div> -->
										</div>
									</div>

									<div class="container-fluid">
										<div id="members-online"></div>
									</div>
								</div>
								<!-- /members online -->

							</div>
							<div class="col-lg-4">
								<div class="card bg-pink-400">
									<div class="card-body">
										<div class="d-flex">
											<h3 class="font-weight-semibold mb-0 number">
											<c:choose>
												<c:when test="${not empty countRestaurant}">${countRestaurantInactive}</c:when>
												<c:otherwise><fmt:message key='restaurant.unsync'/></c:otherwise>
											</c:choose>

											</h3>
											<!-- <span class="badge bg-teal-800 badge-pill align-self-center ml-auto p-1"> </span> -->
					                	</div>

					                	<div>
					                		<security:authorize access="hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
												<a href="<c:url value='/restaurant/list?status=0'/>" class="text-light"><fmt:message key='restaurant.amount.status.inactive'/></a>
											</security:authorize>
											<!-- Check account have role view restaurant -->
					                		<security:authorize access="!hasAnyRole('ROLE_ADMIN, ROLE_RES_VIEW, ROLE_RES_ALL')">
											<fmt:message key='restaurant.amount.status.inactive'/>
											</security:authorize>

<!-- 											<div class="font-size-sm opacity-75">489 avg</div> -->
										</div>
									</div>

									<div class="container-fluid">
										<div id="members-online"></div>
									</div>
								</div>
							</div>
 						</security:authorize>
						</div>
						<!-- /quick stats boxes -->




				<!-- Dashboard content -->

				<!-- /dashboard content -->

			</div>

<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-body">
        <div class="loading">Loading&#8230;</div>
    </div>
</div>

<script>
	$(document).ready(function() {
		$('#sync-from-rk7').on('click', function() {
			$('#pleaseWaitDialog').modal();
		});
	});
</script>

			