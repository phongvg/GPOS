<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>


<div class="row">
	<div class="col-md-12">
	<!-- Grey header and footer -->
	<div class="card">
		<div class="card-header bg-light d-flex justify-content-between">
			<span class="font-size-sm text-uppercase font-weight-semibold"><fmt:message key="co.info.foodItem"/></span>
			<span class="font-size-sm text-uppercase text-success font-weight-semibold">&nbsp;</span>
		</div>
		<div class="card-body">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-3 text-right" style="margin-top: 5px"><strong><fmt:message key="add.info.foodItem"/>: </strong></div>
					<div class="col-md-5 form-group">
						<input id="infoCoFoodItem" type="text" class="form-control" placeholder="Thêm thông tin cho món ăn"/> 
					</div>
					<div class="col-md-4">
						<button type="button" class="btn bg-primary" id="btn-info-foodItem"><fmt:message key="button.add.info"/></button>
					</div>
				</div>
			</div>
			<div class="table-responsive">
               	<table id="tblSelectedInfoFoodItems" class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th width="5%">#</th>
							<th width="90%"><fmt:message key="foodItem.info"/></th>
							<th width="5%" class="text-center"><i class="icon-so-open2"></i></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="item" varStatus="cnt">
							<tr>
								<td><c:out value="${cnt.count+page.size*page.number}"></c:out></td>
							</tr>
						</c:forEach> 
					</tbody>
				</table>            	
			</div>
      			
		</div>
		
	</div>
	<!-- /grey header and footer -->
	</div>
</div>

<br/>		
        			
        			
        			
        			

