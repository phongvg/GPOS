<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="brand.title.list"/></title>
    <meta name="menu" content="brandMenu"/>
    
	<link href="<c:url value='/libs/bootstrap3-editable/css/bootstrap-editable.css'/>" rel="stylesheet"/>
	<script src="<c:url value='/libs/bootstrap3-editable/js/bootstrap-editable.js' />"></script>
	<script src="<c:url value='/themes/admin/assets/css/custom_style.css'/>"></script>
	<link href="<c:url value='/libs/select2-4.0.3/css/select2.css'/>"/>
	<script src="<c:url value='/libs/select2-4.0.3/js/select2.full.js'/>"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.0/css/all.css" integrity="sha384-aOkxzJ5uQz7WBObEZcHvV5JvRW3TUc2rNPA7pe3AwnsUohiw1Vj2Rgx2KSOkF5+h" crossorigin="anonymous">
	
	<!-- Custom Css -->
	<style>
		.icon-action{
			padding : 5px;
		}
		.icon-pencil7{
			color:blue;
		}
		.icon-trash{
			color:red;
		}
		.btn-search{
			padding-top: 30px;
			text-align: right;
		}
	</style>
</head>


	<!-- Content area -->
	<div class="content">
			<!-- Table header styling -->
			<div class="card">
				
				<div class="card-header header-elements-inline">
					<span class="text-uppercase font-size-lg font-weight-bold"><fmt:message key="brand.list.title"></fmt:message></span>
					<%--
					<div class="header-elements">
						<div class="list-icons">
							<a href="<c:url value='/admin/agt/form'/>" class="btn btn-sm btn-primary" title="<fmt:message key="button.add"/>"><i class="icon-plus22"></i><fmt:message key="button.add"/></a>
	                		<!-- 
	                		<a class="list-icons-item" data-action="collapse"></a>
	                		<a class="list-icons-item" data-action="reload"></a>
	                		<a class="list-icons-item" data-action="remove"></a>
	                		 -->
	                	</div>
                	</div>
                	--%>	
				</div>
 				
				<div class="card-body">
					<fmt:message key="brand.list.title.description"/>
				</div>

				<div class="">
					<table class="table table-bordered table-striped">
						<thead>
							<tr class="table-success text-center">
								<th width="10%">#</th>
								<th><fmt:message key="brand.name"></fmt:message></th>
<%-- 								<th width="10%"><fmt:message key="brand.status"></fmt:message></th> --%>
<!-- 								<th width="10%"class="text-center"><i class="icon-menu-open2"></i></th> -->
							</tr>
						</thead>
						<tbody>
						<c:if test="${empty brands}">
							<tr>
								<td colspan="2" class="text-center text-none-data"><fmt:message key="not.data"/></td>
							</tr>
						</c:if>
						<c:forEach var="brand" items="${brands}" varStatus="cnt">
							<tr>
								<td class="text-center"><c:out value="${cnt.count+page.size*page.number}" /></td>
								<td class="text-center">${brand.name}</td>
<%-- 								<td class="text-center">${brand.displayOrder }</td> --%>
<!-- 								<td class="text-center"> -->
<!-- 										<div class="dropdown"> -->
<!-- 											<a href="#" class="list-icons-item btn btn-default" data-toggle="dropdown"> -->
<!-- 												<i class="icon-menu"></i> -->
<!-- 											</a> -->
<!-- 											<div class="dropdown-menu dropdown-menu-bottom"> -->
<!-- 												<a href="#" class="dropdown-item"><i class="icon-sync"></i>Đồng bộ</a>  -->
<%-- 												<a href='<c:url value="/restaurant/info/${brand.id }"/>' class="dropdown-item"><i class="icon-clipboard3"></i> Thông tin nhà hàng</a> --%>
<%-- 												<a href='<c:url value="/restaurant/kds/${brand.id }"/>' class="dropdown-item"><i class="icon-tree7"></i> Cấu hình KDS</a> --%>
<%-- 												<a href='<c:url value="/restaurant/menu/${brand.id }"/>' class="dropdown-item"><i class="icon-hammer-wrench"></i> Cấu hình menu</a> --%>
<%-- 												<a href='<c:url value="/restaurant/configCO/${brand.id }"/>' class="dropdown-item"><i class="icon-clipboard3"></i> Cấu hình menu CO</a> --%>
<!-- 												<a href= "#" class="dropdown-item"><i class="icon-list-unordered"></i> Cấu hình chức năng</a> -->
<%-- 												<a href='<c:url value="/restaurant/param/${brand.id }"/>' class="dropdown-item"><i class="icon-equalizer2"></i> Cấu hình param</a> --%>
<%-- 												<a href='<c:url value="/restaurant/advanced/${brand.id }"/>' class="dropdown-item"><i class="icon-grid4"></i> Nâng cao</a> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 								</td> -->
								
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				
	            <!-- Pagination -->
<%-- 	            <jsp:include page="/themes/admin/common/pagination.jsp"> --%>
<%-- 	                <jsp:param value="${page.number}" name="pageNumber"/> --%>
<%-- 	                <jsp:param value="${page.totalPages}" name="maxPages"/> --%>
<%-- 	                <jsp:param value="${page.size}" name="size"/> --%>
<%-- 	            </jsp:include> --%>
				
			</div>
			<!-- /table header styling -->
	</div>
	<!-- /content area -->

<script>
$(document).ready(function() {
	$('.brandStatus').editable({
		mode: 'inline',
		source: [
           {id: 1, text: 'Hiệu lực'},
           {id: 0, text: 'Không hiệu lực'}
        ],
        select2: {
        	width: '135',
          	multiple: false
        }
    });
	
});

function doSearch() {
	$("#page").val(0);
	$("#size").val(${page.size});
	$element.closest("form").submit();
}
</script>