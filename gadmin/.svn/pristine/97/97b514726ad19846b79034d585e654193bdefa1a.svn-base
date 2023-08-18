<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="menu" content="restaurantMenu"/>
<title>Cấu hình CO</title>
	<script src="/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/purify.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/uploaders/fileinput/plugins/sortable.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/uploaders/fileinput/fileinput.min.js"></script>
	<script src="/themes/admin/global_assets/js/demo_pages/uploader_bootstrap.js"></script>
	
	<script src="/themes/admin./global_assets/js/plugins/extensions/jquery_ui/interactions.min.js"></script>
	<script src="/themes/admin/global_assets/js/plugins/forms/selects/select2.min.js"></script>
	<script src="/themes/admin/global_assets/js/demo_pages/form_select2.js"></script>
</head>
<body>
	<div class="content">
	
		<div class="nav-bar">
			<a href='<c:url value="/restaurant/info/${restaurant.id }"/>' class="nav-bar__link">
			<i class="icon-file-text2 mr-2"></i>
			<span>Thông tin nhà hàng</span>
		</a>
		
		<a href='<c:url value="/restaurant/kds/${restaurant.id }"/>' class="nav-bar__link">
			<i class="icon-tree7 mr-2"></i>
			<span>Cấu hình KDS</span>
		</a>
		
		<a href='<c:url value="/restaurant/menu/${restaurant.id }"/>' class="nav-bar__link">
			<i class="icon-hammer-wrench mr-2"></i>
			<span>Cấu hình menu</span>
		</a>
		
		<a href='<c:url value="/restaurant/configCO/${restaurant.id }"/>' class="nav-bar__link  active">
			<i class="icon-clipboard3 mr-2"></i>
			<span>Cấu hình CO</span>
		</a>
		
		<a href='<c:url value="/restaurant/param/${restaurant.id }"/>' class="nav-bar__link">
			<i class="icon-equalizer2 mr-2"></i>
			<span>Cấu hình Param</span>
		</a>
		
		<a href='<c:url value="/restaurant/advanced/${restaurant.id }"/>' class="nav-bar__link">
			<i class="icon-grid4 mr-2"></i>
			<span>Nâng cao</span>
		</a>
		
		<a href='<c:url value="/restaurant/report/${restaurant.id }"/>' class="nav-bar__link">
			<i class="icon-file-stats mr-2"></i>
			<span>Báo cáo</span>
		</a>
		</div>
		
		<div class="res-content">
			<div class="card">
						<div class="card-header header-elements-inline">
							<span class="text-uppercase font-size-lg font-weight-bold">Cấu hình CO</span>
							<div class="header-elements">
								<div class="list-icons">
			                		<a class="list-icons-item" data-action="collapse"></a>
			                	</div>
		                	</div>
						</div>
						
						<div class="card-body">
							<div class="table-responsive">
								<a href="#"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#resCO" data-backdrop="static" data-keyboard="false" style="margin-bottom: 10px">Thêm mới</button></a>
								<table class="table datatable-basic" style="text-align: center;">
									<thead>
										<tr style="background-color: aliceblue">
											<th>STT</th>
											<th>Mã món</th>
											<th>Tên món</th>
											<th>Mô tả</th>
											<th>Tên tiếng anh</th>
											<th>Mô tả tiếng anh</th>
											<th style="width: 15%"></th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td>1</td>
											<td>CO</td>
											<td>CO</td>
											<td>CO</td>
											<td>CO</td>
											<td>CO</td>
											<td>
													<a href="#"><button type="button" class="btn bg-primary-400 btn-icon rounded-round" data-toggle="modal" data-target="#resCO" data-backdrop="static" data-keyboard="false"><i class="icon-pen6"></i></button></a>
													<a href="#"><button type="button" class="btn bg-danger-400 btn-icon rounded-round"><i class="icon-bin2"></i></button></a>
												</td>
										</tr>
										
										
									</tbody>
								</table>
								<!--Modal  -->
									<div class="modal fade" id="resCO" role="dialog">
									<div class="modal-dialog" style="max-width: 1000px;">
									<!-- Modal content-->
										 <div class="modal-content">
											   <div class="modal-header">
												      <button type="button" class="close" data-dismiss="modal">&times;</button>
											   </div>
											   <div class="modal-body">
											         <form action="">
											         	<fieldset class="mb-3">
											         		<div class="form-group">
																	<label class="col-form-label col-lg-5">Mô tả</label>
																	<div class="col-lg">
																		<textarea class="form-control" rows="5" style="width: 100%"></textarea>
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Tên tiếng anh</label>
																	<div class="col-lg">
																		<input type="text" class="form-control">
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Mô tả tiếng anh</label>
																	<div class="col-lg">
																		<input type="text" class="form-control">
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Ảnh đại diện</label>
																	<div class="col-lg">
																		<input type="file" class="file-input" data-fouc>
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Ảnh món ăn</label>
																	<div class="col-lg">
																		<input type="file" class="file-input" multiple="multiple" data-fouc>
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Ghi chú cho món ăn</label>
																	<div class="col-lg">
																		<select data-placeholder="Select a State..." multiple="multiple" class="form-control select" data-fouc>
																		<option></option>
																			<option value="AZ">Arizona</option>
																			<option value="CO">Colorado</option>
																			<option value="ID">Idaho</option>
																			<option value="WY">Wyoming</option>
																			<option value="AL">Alabama</option>
																			<option value="IA">Iowa</option>
																			<option value="KS">Kansas</option>
																			<option value="KY">Kentucky</option>
																	</select>
																	</div>
																</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Món liên quan</label>
																	<div class="col-lg">
																		<select data-placeholder="Select a State..." multiple="multiple" class="form-control select" data-fouc>
																		<option></option>
																			<option value="AZ">Arizona</option>
																			<option value="CO">Colorado</option>
																			<option value="ID">Idaho</option>
																			<option value="WY">Wyoming</option>
																			<option value="AL">Alabama</option>
																			<option value="IA">Iowa</option>
																			<option value="KS">Kansas</option>
																			<option value="KY">Kentucky</option>
																	</select>
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Thuốc tính cho món</label>
																	<div class="col-lg">
																		<select data-placeholder="Select a State..." multiple="multiple" class="form-control select" data-fouc>
																		<option></option>
																			<option value="AZ">Arizona</option>
																			<option value="CO">Colorado</option>
																			<option value="ID">Idaho</option>
																			<option value="WY">Wyoming</option>
																			<option value="AL">Alabama</option>
																			<option value="IA">Iowa</option>
																			<option value="KS">Kansas</option>
																			<option value="KY">Kentucky</option>
																	</select>
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Số lượng tối đa trên 1 lượt</label>
																	<div class="col-lg">
																		<input type="number" class="form-control">
																	</div>
															</div>
															<div class="form-group">
																	<label class="col-form-label col-lg-5">Số lượng tối đa vào bếp</label>
																	<div class="col-lg">
																		<input type="number" class="form-control">
																	</div>
															</div>
											         	</fieldset>
											         	<div class="text-center">
																<button type="submit" class="btn btn-primary">Lưu<i class="icon-paperplane ml-2"></i></button>
																<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
															</div>
											         </form> 
											    </div>
										      </div>
										    </div>
									</div>
								<!--/  -->
							</div>
						</div>
					</div>
		</div>
	</div>
</body>
</html>