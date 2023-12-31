<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title><fmt:message key="webapp.title"/> | <sitemesh:write property='title'/></title>



	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/global_assets/css/icons/icomoon/styles.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/global_assets/css/icons/fontawesome/styles.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/assets/css/bootstrap_limitless.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/assets/css/layout.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/assets/css/components.min.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/themes/admin/assets/css/colors.min.css'/>" rel="stylesheet" type="text/css">
	<%--<link href="<c:url value='/themes/admin/assets/css/restaurant-menu.css' />" rel="stylesheet" type="text/css" /> --%>

	<!-- /global stylesheets -->

	<!-- custom stylesheet -->
	<link href="<c:url value='/themes/admin/assets/css/custom_style.css'/>" rel="stylesheet" type="text/css">
	<link href='<c:url value="/themes/admin/assets/css/spinner.css"></c:url>' rel="stylesheet" type="text/css">
	<!-- custom stylesheet -->

	<!-- Core JS files -->
	<script src="<c:url value='/themes/admin/global_assets/js/main/jquery.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/main/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/loaders/blockui.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/demo_pages/form_floating_labels.js'/>"></script>
	<!-- /core JS files -->

	<!-- Custom JS files -->
	<script src="<c:url value='/themes/admin/assets/js/app.js'/>"></script>
	<script src="<c:url value='/themes/admin/assets/js/custom.js'/>"></script>
	<script type="text/javascript" src='<c:url value="/themes/admin/assets/js/sync_data_spinner.js" />'></script>

	<script>
		function getContext() {
			return '${ctx}';
		}
	</script>
	<!-- /theme JS files -->

	<sitemesh:write property='head'/>
</head>

<body>
  <c:set var="currentMenu" scope="request"><sitemesh:write property='currentMenu'/></c:set>
  <input id="currentMenu" type="hidden" value='<sitemesh:write property="meta.menu" />'/>

	<!-- Main navbar -->
	<%@include file="/themes/admin/common/navbar.jsp"%>
	<!-- /main navbar -->


	<!-- Page content -->
	<div class="page-content">

		<!-- Main sidebar -->
		<%@include file="/themes/admin/common/sidebar.jsp"%>
		<!-- /main sidebar -->


		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Page header -->
			<div class="page-header page-header-light">
				<!--
				<div class="page-header-content header-elements-md-inline">
					<div class="page-title d-flex">
						<h4><i class="icon-arrow-left52 mr-2"></i> <span class="font-weight-semibold">Home</span> - Dashboard</h4>
						<a href="#" class="header-elements-toggle text-default d-md-none"><i class="icon-more"></i></a>
					</div>

					<div class="header-elements d-none">
						<div class="d-flex justify-content-center">
							<a href="#" class="btn btn-link btn-float text-default"><i class="icon-bars-alt text-primary"></i><span>Statistics</span></a>
							<a href="#" class="btn btn-link btn-float text-default"><i class="icon-calculator text-primary"></i> <span>Invoices</span></a>
							<a href="#" class="btn btn-link btn-float text-default"><i class="icon-calendar5 text-primary"></i> <span>Schedule</span></a>
						</div>
					</div>
				</div>


				<div class="breadcrumb-line breadcrumb-line-light header-elements-md-inline">
					<div class="d-flex">
						<div class="breadcrumb">
							<a href="index.html" class="breadcrumb-item"><i class="icon-home2 mr-2"></i> Home</a>
							<span class="breadcrumb-item active">Dashboard</span>
						</div>

						<a href="#" class="header-elements-toggle text-default d-md-none"><i class="icon-more"></i></a>
					</div>

					<div class="header-elements d-none">
						<div class="breadcrumb justify-content-center">
							<a href="#" class="breadcrumb-elements-item">
								<i class="icon-comment-discussion mr-2"></i>
								Support
							</a>

							<div class="breadcrumb-elements-item dropdown p-0">
								<a href="#" class="breadcrumb-elements-item dropdown-toggle" data-toggle="dropdown">
									<i class="icon-gear mr-2"></i>
									Settings
								</a>

								<div class="dropdown-menu dropdown-menu-right">
									<a href="#" class="dropdown-item"><i class="icon-user-lock"></i> Account security</a>
									<a href="#" class="dropdown-item"><i class="icon-statistics"></i> Analytics</a>
									<a href="#" class="dropdown-item"><i class="icon-accessibility"></i> Accessibility</a>
									<div class="dropdown-divider"></div>
									<a href="#" class="dropdown-item"><i class="icon-gear"></i> All settings</a>
								</div>
							</div>
						</div>
					</div>
				</div>-->
			</div>
			<!-- /page header -->

			<!-- Message -->
			<%@include file="/themes/admin/common/messages.jsp"%>
			<!-- /Message -->

			<!-- Content area -->
			<sitemesh:write property='body'/>
			<!-- /content area -->


			<!-- Footer -->
			<%@include file="/themes/admin/common/footer.jsp"%>
			<!-- /footer -->

		</div>
		<!-- /main content -->

	</div>
	<!-- /page content -->

</body>
<script type="text/javascript">
	$(document).ready(function() {
		SyncDataRk7._syncAllDataRk7();
	})
</script>
</html>
