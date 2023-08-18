<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
	<!-- template Menu -->
	<title><fmt:message key="advanced.form.title"/></title>
    <meta name="menu" content="templateMenu"/>
    
	<!-- script select -->
<%-- 	<script src="<c:url value='/themes/admin/global_assets/js/plugins/extensions/jquery_ui/interactions.min.js'/>"></script> --%>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js'/>"></script>

	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/styling/uniform.min.js'/>"></script>
	<script src="<c:url value='/themes/admin/global_assets/js/plugins/forms/validation/validate.min.js'/>"></script>
	
	<script src="<c:url value='/themes/admin/assets/js/template_form.js'/>"></script>
	<!--  -->
</head>

<!-- Content area -->
<div class="content">
	<!-- Basic layout-->
	<div class="card">
		<div class="card-header header-elements-inline">
			<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="advanced.form.title"/></span>
			<div class="header-elements">
				<div class="list-icons">
               		<a class="list-icons-item" data-action="collapse"></a>
               		<a class="list-icons-item" data-action="reload"></a>
               		<a class="list-icons-item" data-action="remove"></a>
               	</div>
            </div>
		</div>
		
		
		<div class="card-body">
			<p class="mb-4"><fmt:message key="advanced.form.title.description"/></p>
			<form:form id="templateform" action="${ctx}/template/save" method="post">
				<fieldset class="mb-3">
					<legend class="text-uppercase font-size-sm font-weight-bold"><fmt:message key="template.legend"/></legend>
		
					<ul class="nav nav-tabs nav-tabs-bottom nav-justified">
						<li class="nav-item"><a href="<c:url value="/restaurant/form?rCode=${rCode}"/>" class="nav-link"><i class="icon-file-text2 mr-2"></i><fmt:message key="tab.restaurant"/></a></li>
						<li class="nav-item"><a href="<c:url value="/res/so/menu/list?resCode=${rCode}"/>" class="nav-link"><i class="icon-hammer-wrench mr-2"></i><fmt:message key="tab.menu"/></a></li>
						<li class="nav-item"><a href="<c:url value="/co/form?rCode=${rCode}"/>" class="nav-link"><i class="icon-clipboard3 mr-2"></i><fmt:message key="tab.co"/></a></li>
						<li class="nav-item"><a href="<c:url value="/kds/form?rCode=${rCode}"/>" class="nav-link"><i class="icon-tree7 mr-2"></i><fmt:message key="tab.kds"/></a></li>
						<li class="nav-item"><a href="<c:url value="/param/list?rCode=${rCode}"/>" class="nav-link"><i class="icon-equalizer2 mr-2"></i><fmt:message key="tab.param"/></a></li>
						<li class="nav-item"><a href="<c:url value="/restaurant/device?rCode=${rCode}"/>" class="nav-link"><i class="icon-equalizer mr-2"></i><fmt:message key="tab.device"/></a></li>
						<li class="nav-item"><a href="#" class="nav-link active" ><i class="icon-grid4 mr-2"></i><fmt:message key="tab.advance"/></a></li>
						<li class="nav-item"><a href="<c:url value="/reportMenu/list?rCode=${rCode}"/>" class="nav-link"><i class="icon-file-stats mr-2"></i><fmt:message key="tab.report"/></a></li>
					</ul>
			
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-md-5 offset-md-2"><input class="form-control" type="text" placeholder="Nhập mã nhân viên cần unlock..."/></div>
						<div class="col-md-5">
							<a href="#" type="button" class="btn btn-secondary" style="background-color: #2196f3; color: #fff;"><fmt:message key="advanced.unlock.staff"/></a>
							<a href="#" type="button" class="btn btn-secondary" style="background-color: #2196f3; color: #fff;"><fmt:message key="advanced.unlock.order"/></a>
						</div>
					</div>
				</div>			
			</div>
			
			<div class="tab-content">
				<div class="card">
					<div class="card-header header-elements-inline">
						<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="advanced.list.order.open"/></span>
					</div>	
					<div class="card-body">
						<div class="table-reponsive">
							<table class="table table-bordered table-striped" style="text-align: center;">
								<thead>
									<tr class="table-success">
										<th>#</th>
										<th><fmt:message key="advanced.code"/></th>
										<th><fmt:message key="advanced.name.GPO/RK"/></th>
										<th><fmt:message key="advanced.code.order"/></th>
										<th><fmt:message key="advanced.staff"/></th>
										<th><fmt:message key="advanced.quantity.customer"/></th>
										<th><fmt:message key="advanced.time"/></th>
										<th><fmt:message key="advanced.total.price"/></th>
									</tr>
								</thead>
								<tbody>
									<%-- <c:forEach items="" var="" varStatus="cnt"> --%>
										<tr>
											<td>1</td>
											<td>Lock</td>
											<td>lock</td>
											<td>lock</td>
											<td>lock</td>
											<td>lock</td>
											<td><fmt:parseDate value="" pattern="dd/MM/yyyy HH:mm:ss"></fmt:parseDate></td>
											<td><span class="currency">12312312312</span></td>
										</tr>
									<%-- </c:forEach> --%>
								</tbody>
							</table>
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