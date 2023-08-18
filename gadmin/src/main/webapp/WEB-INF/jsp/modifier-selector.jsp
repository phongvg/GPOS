<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>


<div class="row">
	<div class="col-md-12">
	<!-- Grey header and footer -->
	<div class="card">
		<div class="card-header bg-light d-flex justify-content-between">
			<span class="font-size-sm text-uppercase font-weight-semibold"><fmt:message key="co.select.modifier"/></span>
			<span class="font-size-sm text-uppercase text-success font-weight-semibold">&nbsp;</span>
		</div>
		<div class="card-body">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-1 text-right" style="margin-top: 5px"><strong><fmt:message key="modifier.select"/>: </strong></div>
					<div class="col-md-3 form-group">
						<select data-fouc class="select2-modifiers" multiple data-placeholder="Chọn modifier">
							<option></option>
						</select>
					</div>
					<div class="col-md-1 text-right" style="margin-top: 5px"><strong><fmt:message key="modifier.type"/>: </strong></div>
					<div class="col-md-2 form-group">
						<select class="form-control select2 type" id="modifierType" required="required">
							<c:forEach items="${coFoodItemModifierTypes}" var="item">
								<option value="${item}"><fmt:message key="coFoodItemModifierType.${item}"/></option>
							</c:forEach>
        				</select>
					</div>
					<div class="col-md-1 text-right" style="margin-top: 5px"><strong><fmt:message key="modifier.numberOfChili"/>: </strong></div>
					<div class="col-md-2 form-group">
						<input type="number" id="modifierNumberOfChili" class="form-control" maxlength="2" placeholder="vui lòng nhập giá trị từ 1 -> 10"/>
					</div>
					<div class="col-md-1">
						<button type="button" class="btn bg-primary" id="btn-seclect-modifier"><fmt:message key="button.select"/></button>
					</div>
				</div>
			</div>
			<div class="table-responsive">
               	<table id="tblSelectedModifiers" class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th width="5%">#</th>
							<th width="20%"><fmt:message key="modifier.code"/></th>
							<th width="20%"><fmt:message key="modifier.nameVn"/></th>
							<th width="20%"><fmt:message key="modifier.type"/></th>
							<th width="20%"><fmt:message key="modifier.numberOfChili"/></th>
							<th width="5%" class="text-center"><i class="icon-so-open2"></i></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="soCat" varStatus="cnt">
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
        			
        			
        			
        			

