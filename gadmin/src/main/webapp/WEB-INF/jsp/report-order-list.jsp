<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title>><fmt:message key="report.form.title"/></title>
    <meta name="menu" content="templateMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	
		<script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/daterangepicker.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/anytime.min.js'/>"></script>
		<script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/pickadate/picker.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/pickers/pickadate/picker.date.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
</head>

<div class="content">
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="report"/></span>
			<div class="header-elements">
				<div class="list-icons">
	              		<a class="list-icons-item" data-action="collapse"></a>
	            </div>
            </div>
		</div>
		
		<div class="card-body">
			<p class="mb-4"><fmt:message key="report.form.title.description"/></p>
			<form:form id="templateform" action="${ctx}/template/save" method="post">
				
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="report.legend"/></legend>
		
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${rCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${rCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${rCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${rCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						<li class="nav-item"><a href="<c:url value="/param/list?rCode=${rCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${rCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="<c:url value="/advanced/list?rCode=${rCode}"/>" class="nav-link" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="#" class="nav-link active"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
					</ul>
					
				<div class="tab-content">
					<div class="card">
						<div class="card-header">
							<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="report"/></span>
						</div>
						<div class="card-body">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${rCode}"/>" class="nav-link" ><fmt:message key="report.menu"/></a></li>
								<li class="nav-item"><a href="#" class="nav-link active"><fmt:message key="report.order"/></a></li>
							</ul>
							
							
							<div class="tab-content">
								<!-- Báo cáo menu -->
								<div class="tab-pane fade show active" id="basic-tab1">
									<h6 class="card-title"><fmt:message key="report.order"/></h6>
									
									<!-- table -->
								<div class="card">
									<div class="card-body">
										<div class="row">
											<h6 class="col-md-2" style="margin-top: 5px"><fmt:message key="report.fromdate"/></h6>
											<input class="form-control col-md-2" type="date" placeholder="Enter keyword" style="margin-bottom: 5px">		
										</div>
										<div class="row">
											<h6 class="col-md-2" style="margin-top: 5px;"><fmt:message key="report.todate"/></h6>
											<input class="form-control col-md-2" type="date" placeholder="Enter keyword" style="margin-bottom: 5px">
										</div>
										<div class="row" style="margin-bottom: 5px; margin-top: 5px">
											<h6 class="col-md-2" style="margin-top: 5px;"><fmt:message key="report.type"/></h6>
											<select class="form-control col-md-3">
												<option><fmt:message key="order"/></option>
												<option><fmt:message key="order.detail"/></option>
											</select>
											<div style="margin-left: 20px;">
												<button class="btn btn-secondary" type="button" style="margin-left: 20px; margin-bottom: 5px"><fmt:message key="report.search"/></button>			
												<button class="btn btn-light" type="button" style="margin-left: 20px; margin-bottom: 5px"> <i class="icon-cloud-download2"></i> <fmt:message key="report.export"/></button>
											</div>
										</div>
					       			</div>
								</div>
							<div class="table-responsive">
				               	<table class="table table-bordered table-striped" style="text-align: center;">
				               		<thead>
				                		<tr class="table-success">
				                			<th>STT</th>
				                			<th><fmt:message key="report.code.menu"/></th>
				                			<th><fmt:message key="report.name.menu"/></th>
				                			<th><fmt:message key="report.code.foodItem"/></th>
				                			<th>T<fmt:message key="report.name.foodItem.GPOS"/></th>
				                			<th><fmt:message key="report.name.foodItem.so"/></th>
				                			<th><fmt:message key="report.name.foodItem.co"/></th>
										</tr>
									</thead>
				               		<tbody>
				               			<%-- <c:forEach items="" var="" varStatus="cnt"> --%>
				                			<tr>
				                				<td>a</td>
					                			<td>a</td>
					                			<td>a</td>
					                			<td>a</td>
					                			<td>a</td>
					                			<td>a</td>
					                			<td>a</td>
				               				</tr>
				               			<%-- </c:forEach> --%>
				               		</tbody>
				               	</table>
							</div>
									<!-- /table -->
								</div>
							</div>
						</div>
					</div>
				</div>
					
			 	</fieldset>
				<%-- <div class="d-flex justify-content-end align-items-center">
					<button type="reset" class="btn btn-light" id="reset"><fmt:message key="button.reset"/> <i class="icon-reload-alt ml-2"></i></button>
					<button type="submit" id="templateSubmit" class="btn btn-primary ml-3"><fmt:message key="button.save"/> <i class="icon-paperplane ml-2"></i></button>
				</div> --%>
			</form:form>
		</div>
	</div>
</div>
