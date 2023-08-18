<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<html>
<head>
<meta name="menu" content="restaurantMenu"/>
<title>Cấu hình menu</title>
</head>
<body>
	<!--nav-bar menu  -->
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
		
		<a href='<c:url value="/restaurant/menu/${restaurant.id }"/>' class="nav-bar__link  active">
			<i class="icon-hammer-wrench mr-2"></i>
			<span>Cấu hình menu</span>
		</a>
		
		<a href='<c:url value="/restaurant/configCO/${restaurant.id }"/>' class="nav-bar__link">
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
			<!--cau hinh menu  -->
			<div class="card">
						<div class="card-header header-elements-inline">
							<span class="text-uppercase font-size-lg font-weight-bold">Cấu hình menu</span>
							<div class="header-elements">
								<div class="list-icons">
			                		<a class="list-icons-item" data-action="collapse"></a>
			                	</div>
		                	</div>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<form action="/restaurant/menu/form" method="get" >
									<input hidden="hidden" name="resId" value="${restaurant.id }"/>
									<input hidden="hidden" name="id" value="${menu.id }"/>
									<button type="submit" class="btn btn-primary" style="margin-bottom: 10px">Thêm mới</button>
								</form>
								<table class="table datatable-basic" style="text-align: center;">
									<thead>
										<tr style="background-color: aliceblue">
											<th>STT</th>
											<th>Mã loại menu</th>
											<th>Tên loại menu</th>
											<th>Loại menu cha</th>
											<th>Vé buffet người lớn</th>
											<th>Vé buffet trẻ em</th>
											<th style="width: 15%"></th>
										</tr>
									</thead>
									
									<tbody>
										 <c:forEach items="${menus}" var="menu" varStatus="cout">
											<tr>
												<td>${cout.index + 1}</td>
												<td>${menu.code }</td>
												<td>${menu.nameVn }</td>
												<c:choose>
													<c:when test="${menu.parent.id != null }">
														<td>${menu.parent.nameVn}</td>
													</c:when>
													<c:otherwise>
														<td></td>
													</c:otherwise>
												</c:choose>
												<td>${menu.adultTicket }</td>
												<td>${menu.childTicket }</td>
												<td><!-- <button type="button" class="btn bg-primary-400 btn-icon rounded-round modalMenu" data-toggle="modal" data-target="#modalMenu" data-backdrop="static" data-keyboard="false"><i class="icon-eye2"></i></button> -->
													<form action="/restaurant/menu/form" method="get" style="display: inline-flex;" >
														<input hidden="hidden" name="resId" value="${restaurant.id }"/>
														<input hidden="hidden" name="id" value="${menu.id }"/>
														<button type="submit" class="btn bg-primary-400 btn-icon rounded-round"><i class="icon-pen6"></i></button>
													</form>
													<a href="/restaurant/menu/del/${menu.id }" onclick="return alertCancel(this.href)"><button type="button" class="btn bg-danger-400 btn-icon rounded-round"><i class="icon-bin2"></i></button></a>
												</td>
											</tr>
										</c:forEach> 
									</tbody>
								</table>
									<!--modal menu  -->
										<%-- <div class="modal fade" id="modalMenu" role="dialog" style="size: 150%">
										    <div class="modal-dialog" style="max-width: 1000px;">
											      <div class="modal-content">
											        <div class="modal-header" style="text-align: center; background-color: aliceblue ">
											        		<button type="button" class="close" data-dismiss="modal">&times;</button>
											        </div>
											        <div class="modal-body">
											          <form action="/restaurant/menu/save" method="POST">
											          		<fieldset class="mb-3">
											          			<div class="form-group">
											          				<label class="col-form-label col-lg-5">Mã loại menu</label>
																	<div class="col-lg">
																		<input class="form-control" id="codeMenu"/>
																	</div>
																</div>
																<div class="form-group">
																	<label class="col-form-label col-lg-5">Tên loại menu</label>
																	<div class="col-lg">
																		<input type="text" class="form-control" id="nameMenu">
																	</div>
																</div>
																<div class="form-group">
										                        	<label class="col-form-label col-lg-5">Loại menu cha</label>
										                        	<div class="col-lg">
											                            <select class="form-control" id="menuType">
											                            	<c:forEach items="${menuTypes }" var="menuType">
											                            		<option value="opt2" selected="${menuType.id eq menu.menuType.id}">${menuType.name}</option>
											                            	</c:forEach>
											                            </select>
										                            </div>
										                        </div>
										                        <div class="form-group">
										                        	<label class="col-form-label col-lg-5">Vé buffet người lớn</label>
										                        	<div class="col-lg">
											                            <select class="form-control">
											                                <option value=""></option>
											                            </select>
										                            </div>
										                        </div>
										                        <div class="form-group">
										                        	<label class="col-form-label col-lg-5">Vé buffet trẻ em</label>
										                        	<div class="col-lg">
											                            <select class="form-control">
											                                <option value=""></option>
											                            </select>
										                            </div>
										                        </div>
										                        <div>
										                        	<table  class="table datatable-basic" style="text-align: center; margin-bottom: 5px;">
										                        		<thead>
										                        			<tr style="background-color: aliceblue">
										                        				<th>Nhóm nhóm món ăn mater data</th>
										                        				<th>Danh sách nhóm món ăn có trong menu</th>
										                        			</tr>
										                        		</thead>
										                        	</table>
										                        	<select multiple="multiple" class="form-control listbox" data-fouc>
																		<option value="option1" selected>Classical mechanics</option>
																		<option value="option2">Electromagnetism</option>
																		<option value="option4">Relativity</option>
																		<option value="option5" selected>Quantum mechanics</option>
																		<option value="option7">Astrophysics</option>
																		<option value="option8" selected>Biophysics</option>
																	</select>
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
										 </div> --%>
								<!--/modal menu  -->
							</div>
						</div>
					</div>
			<!--/Cau hinh menu  -->
				
			<!--Cau hinh nhom mon an  -->
			<div class="card">
				<div class="card-header header-elements-inline">
					<h6 class="card-title">Cấu hình nhóm món ăn</h6>
					<div class="header-elements">
						<div class="list-icons">
		                	<a class="list-icons-item" data-action="collapse"></a>
		                </div>
	                </div>
				</div>
					
				<div class="card-body">
					<form action="/restaurant/foodGroup/form" method="get" >
						<input hidden="hidden" name="resId" value="${restaurant.id }"/>
						<input hidden="hidden" name="id" value="${foodGroup.id }"/>
						<button type="submit" class="btn btn-primary">Thêm mới</button>
					</form>
					<table class="table datatable-basic" style="text-align: center;">
						<thead>
							<tr style="background-color: aliceblue">
								<th>STT</th>
								<th>Mã nhóm</th>
								<th>Tên nhóm</th>
								<th>Loại menu</th>
								<th>Nhóm cha</th>
								<th>Thứ tự</th>
								<th style="width: 15%"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${foodGroups }" var="foodGroup" varStatus="cout">
								<tr>
									<td>${cout.index +1 }</td>
									<td>${foodGroup.code }</td>
									<td>${foodGroup.nameVn }</td>
									<td>${foodGroup.type }</td>
									<c:choose>
										<c:when test="${foodGroup.parent.id != null }">
											<td>${foodGroup.parent.nameVn}</td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									<td>${foodGroup.level }</td>
									<td>
										<%-- <button type="button" class="btn bg-primary-400 btn-icon rounded-round" data-toggle="modal" data-target="#modalFood" data-backdrop="static" data-keyboard="false"><i class="icon-eye2"></i></button>
										<a href="/restaurant/foodGroup/form?id=${foodGroup.id }" class="btn bg-primary-400 btn-icon rounded-round"><i class="icon-pen6"></i></a> --%>
										<form action="/restaurant/foodGroup/form" method="get" style="display: inline-flex;">
											<input hidden="hidden" name="resId" value="${restaurant.id }"/>
											<input hidden="hidden" name="id" value="${foodGroup.id }"/>
											<button type="submit" class="btn bg-primary-400 btn-icon rounded-round"><i class="icon-pen6"></i></button>
										</form>
										<a href="/restaurant/foodGroup/del/${foodGroup.id }" onclick="return alertCancel(this.href)"><button type="button" class="btn bg-danger-400 btn-icon rounded-round"><i class="icon-bin2"></i></button></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<!--Modal FoodGroup  -->
						<!-- <div class="modal fade" id="modalFoodGroup" role="dialog" style="size: 150%">
								    <div class="modal-dialog" style="max-width: 1000px;">
									      <div class="modal-content">
									        <div class="modal-header" style="text-align: center; background-color: aliceblue ">
									        	<button type="button" class="close" data-dismiss="modal">&times;</button>
									        </div>
									        <div class="modal-body">
									          <form action="" method="">
									          		<fieldset class="mb-3">
									          			<div class="form-group">
															<label class="col-form-label col-lg-5">Mã nhóm</label>
															<div class="col-lg">
																<input type="text" class="form-control">
															</div>
														</div>
														<div class="form-group">
															<label class="col-form-label col-lg-5">Tên nhóm</label>
															<div class="col-lg">
																<input type="text" class="form-control">
															</div>
														</div>
														<div class="form-group">
								                        	<label class="col-form-label col-lg-5">Loại menu</label>
								                        	<div class="col-lg">
									                            <select class="form-control">
									                                <option value=""></option>
									                            </select>
								                            </div>
								                        </div>
								                        <div class="form-group">
								                        	<label class="col-form-label col-lg-5">Nhóm cha</label>
								                        	<div class="col-lg">
									                            <select class="form-control">
									                                <option value=""></option>
									                            </select>
								                            </div>
								                        </div>
								                        <div class="form-group">
															<label class="col-form-label col-lg-5">Thứ tự</label>
															<div class="col-lg">
																<input type="text" class="form-control">
															</div>
														</div>
														<div class="form-group">
															<table class="table datatable-basic" style="text-align: center; margin-bottom: 10px;">
																	<thead>
																		<tr style="background-color: aliceblue">
																			<th>Nhóm món ăn mater data</th>
																			<th>Danh sách món ăn có trong nhóm</th>
																		</tr>
																	</thead>
												        	</table>
															<select multiple="multiple" class="form-control listbox" data-fouc>
																<option value="" selected></option>
															</select>
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
								 </div> -->
					<!--/Modal FoodGroup  -->
					
					<!--Modal FoodGroupView  -->
						<div class="modal fade" id="modalFood" role="dialog">
								    <div class="modal-dialog" style="max-width: 1000px">
									      <div class="modal-content">
									        <div class="modal-header" style="text-align: center; background-color: aliceblue ">
										        	<button type="button" class="close" data-dismiss="modal">&times;</button>
									        </div>
									        <div class="modal-body">
								          			<div class="form-group">
														<label class="col-form-label col-lg-5">Mã nhóm</label>
														<div class="col-lg">
															<input type="text" class="form-control" disabled>
														</div>
													</div>
													<div class="form-group">
														<label class="col-form-label col-lg-5">Tên nhóm</label>
														<div class="col-lg">
															<input type="text" class="form-control" disabled>
														</div>
													</div>
													<div class="form-group">
							                        	<label class="col-form-label col-lg-5">Loại menu</label>
							                        	<div class="col-lg">
								                            <input type="text" class="form-control" disabled>
							                            </div>
							                        </div>
							                        <div class="form-group">
							                        	<label class="col-form-label col-lg-5">Nhóm cha</label>
							                        	<div class="col-lg">
								                            <input type="text" class="form-control" disabled>
							                            </div>
							                        </div>
							                        <div class="form-group">
														<label class="col-form-label col-lg-5">Thứ tự</label>
														<div class="col-lg">
															<input type="text" class="form-control" disabled>
														</div>
													</div>
													<div class="form-group">
														<table class="table datatable-basic" style="text-align: center; margin-bottom: 10px;">
																<thead>
																	<tr style="background-color: aliceblue">
																		<th>STT</th>
																		<th>Danh sách món ăn trong menu</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>a</td>
																		<td>a</td>
																	</tr>
																</tbody>
											        	</table>
													</div>
									        </div>
									        <div class="modal-footer">
									          	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									        </div>
									      </div>
								    </div>
								 </div>
					<!--/Modal Food Group View  -->
					
				</div>
			</div>
			<!--/Cau hinh mon an  -->
			</div>
		</div>
	<script type="text/javascript">
	function alertCancel(link) {
		var userselection = confirm("Bạn có chắc chắn muốn xóa");
		if(!userselection) {
			return false;
		} else {
			window.location.href = link;
		}
	 }
	</script>
</body>
</html>