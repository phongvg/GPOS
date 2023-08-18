<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>


<div class="row">
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-3 text-right" style="margin-top: 5px"><strong><fmt:message key="foodGroup.foodItem.select"/>: </strong></div>
			<div class="col-md-5 form-group">
				<select data-fouc class="select2-food-items" multiple data-placeholder="Chọn món ăn">
					<option></option>
				</select>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn bg-primary" id="btn-seclect-food-item"><fmt:message key="button.select"/></button>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="table-responsive" style="height:300px">
              	<table id="tblSelectedFoodItems" class="table table-bordered table-striped">
				<thead>
					<tr class="table-success">
						<th width="5%">#</th>
						<th width="5%"><fmt:message key="item.change.sort"/></th>
						<th width="20%"><fmt:message key="foodItem.sapcode"/></th>
						<th width="30%"><fmt:message key="foodItem.code"/></th>
						<th width="35%"><fmt:message key="foodItem.name"/></th>
						<th width="5%" class="text-center"><i class="icon-so-open2"></i></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
<br/>

<%-- 
<div class="row">
	<div class="col-md-5">
		<div class="row">
			<div class="col-md-12"><div class="row"><div class="col-md-9"><input type="text" id="" value="" class="form-control"/></div><div class="col-md-3"><a href="#"><i class="icon-search4"></i>Search</a></div></div></div>
			<div class="col-md-12">
				<div class="table-responsive"  style="height:300px">
	               	<table id="tblFoodItems" class="table table-bordered table-striped">
						<thead>
							<tr class="table-success">
								<th>#</th>
								<th><fmt:message key="foodItem.code"/></th>
								<th><fmt:message key="foodItem.name"/></th>
							</tr>
						</thead>
						<tbody style="height:300px">
							<tr>
								<td><input type="checkbox"></td>
								<td>111</td>
								<td>Bun bo</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
		</div>
	</div>
	<div class="col-md-2">
		<div class="row">
			<div class="col-md-12">&nbsp;</div>
			<div class="col-md-5"><button type="button" class="btn btn-link" data-dismiss="modal"><i class="icon-arrow-right16 mr-3 icon-2x"></i></button></div>
			<div class="col-md-2">&nbsp;</div>
			<div class="col-md-5"><button type="button" class="btn btn-link" data-dismiss="modal"><i class="icon-arrow-left16 mr-3 icon-2x"></i></button></div>
		</div>
	</div>
	<div class="col-md-5">
		<div class="row">
			<div class="col-md-12"><div class="row"><div class="col-md-9"><input type="text" id="" value="" class="form-control"/></div><div class="col-md-3"><a href="#"><i class="icon-search4"></i>Search</a></div></div></div>
			<div class="col-md-12">
				<div class="table-responsive">
	               	<table id="" class="table table-bordered table-striped">
						<thead>
							<tr class="table-success">
								<th>#</th>
								<th><fmt:message key="foodItem.code"/></th>
								<th><fmt:message key="foodItem.name"/></th>
							</tr>
						</thead>
						<tbody  style="height:300px">
						</tbody>
					</table>            	
				</div>
			</div>
		</div>
	</div>
</div>
--%>        			
        			
        			
        			
        			

