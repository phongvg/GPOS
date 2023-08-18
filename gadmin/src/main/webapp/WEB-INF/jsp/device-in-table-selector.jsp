<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>


<div class="row">
	<div class="col-md-12">
	<!-- Grey header and footer -->
	<div class="card">
		<div class="card-header bg-light d-flex justify-content-between">
			<span class="font-size-sm text-uppercase font-weight-semibold"><fmt:message key="add.device.in.table"/></span>
			<span class="font-size-sm text-uppercase text-success font-weight-semibold">&nbsp;</span>
		</div>
		<div class="card-body">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label><fmt:message key="hallplan"/><span class="help-block"></span></label>
							<select class="form-control select2" id="hallplan" aria-hidden="true" data-placeholder="Chọn sảnh">
								<option value="">&nbsp;</option>
								<c:forEach items="${hallplans}" var="hallplan">
									<option value="${hallplan.id}">${hallplan.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label><fmt:message key="table.kitchen"/><span class="help-block"></span></label>
							<select class="form-control select2" id="table-kitchen" aria-hidden="true" data-placeholder="Chọn bàn">
								<option value="">&nbsp;</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 text-right" style="margin-top: 5px"><strong><fmt:message key="add.device.in.table"/>: </strong></div>
					<div class="col-md-5 form-group">
						<input id="deviceIdInTable" type="text" class="form-control" placeholder="Thêm id của thiết bị cho bàn"/> 
					</div>
					
					<div class="col-md-4">
						<button type="button" class="btn bg-primary" id="btn-add-device-in-table"><fmt:message key="button.add.info"/></button>
					</div>
				</div>
			</div>
			<div class="table-responsive">
               	<table id="tblDeviceInTableSelected" class="table table-bordered table-striped">
					<thead>
						<tr class="table-success">
							<th width="5%">#</th>
							<th width="45%"><fmt:message key="table.id"/></th>
							<th width="45%"><fmt:message key="device.id.in.table"/></th>
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
        			
        			
        			
        			

