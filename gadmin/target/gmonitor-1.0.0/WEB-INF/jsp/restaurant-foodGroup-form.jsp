<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="menu" content="restaurantMenu"/>
<title>Restaurant FoodGroup Form</title>
	<script src="/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js"></script>
	<script src="/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js"></script>
</head>
<body>
	<div class="res-content">
		<div class="card">
			<div class="card-header header-elements-inline" style="background-color: aliceblue; justify-content: center;">
				<c:choose>
					<c:when test="${foodGroup.id eq null }">
						<h1><strong>THÊM MỚI NHÓM MÓN ĂN</strong></h1>
					</c:when>
					<c:otherwise>
						<h1 >CHỈNH SỬA THÔNG TIN NHÓM MÓN ĂN</h1>
					</c:otherwise>
				</c:choose>
				
			</div>
			<div class="card-body">
				<form action="/restaurant/foodGroup/save" method="POST">
					<input value="${restaurantDto.id }" type="hidden" name="idRes">
					<input value="${foodGroup.id }" type="hidden" name="id">
					<c:choose>
						<c:when test="${foodGroup.id eq null }">
							<input value="${restaurantDto.id }" type="hidden" name="ownerId">
						</c:when>
						<c:otherwise>
							<input value="${foodGroup.ownerId }" type="hidden" name="ownerId">
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${foodGroup.id eq null }">
							<input type="hidden" name="ownerType">
						</c:when>
						<c:otherwise>
							<input value="${foodGroup.ownerType }" type="hidden" name="ownerType">
						</c:otherwise>
					</c:choose>
							<div class="form-group col-lg-12">
								<div class="row">
									<div class="col-lg-4">
										<label class="col-form-label">Mã nhóm</label>
										<input class="form-control" value="${foodGroup.code }" name="code"/>
									</div>
									<div class="col-lg-8">
										<label class="col-form-label">Tên nhóm</label>
										<input type="text" class="form-control" value="${foodGroup.nameVn }" name="nameVn"/>
									</div>
								</div>
							</div>
							<div class="form-group col-lg-12">
								<div class="row">
									<div class="col-lg-4">
										<label class="col-form-label">Loại menu</label>
										<input class="form-control" value="${foodGroup.type }" name="type"/>
									</div>
									<div class="col-lg-4">
										<label class="col-form-label">Nhóm cha</label>
										<select class="form-control" name="parent.id">
											<option value="${foodGroup.id }"></option>
			                            	<c:forEach items="${foodGroups }" var="foodGroupParent">
			                            		<c:choose>
			                            			<c:when test="${foodGroup.parent.id eq null }">
			                            				<option value="${foodGroupParent.id }" >${foodGroupParent.nameVn}</option>
			                            			</c:when>
			                            			<c:otherwise>
			                            				<option value="${foodGroupParent.id }" ${foodGroupParent.id eq foodGroup.parent.id ? 'selected' : ''} >${foodGroupParent.nameVn}</option>
			                            			</c:otherwise>
			                            		</c:choose>
			                            		
			                            	</c:forEach>
			                            </select>
										
									</div>
									<div class="col-lg-4">
										<label class="col-form-label">Thứ tự</label>
										<input type="text" class="form-control" value="${foodGroup.level }" name="level"/>
									</div>
								</div>
							</div>
							<div class="form-group col-lg-12">
								<div class="row">
									<c:choose>
										<c:when test="${foodGroup.id eq null }">
											<div class="col-lg-4">
												<label class="col-form-label">Tên tiếng Anh</label>
												<input type="text" class="form-control" value="${foodGroup.nameEn }" name="nameEn"/>
											</div>
										</c:when>
										<c:otherwise>
											<input value="${foodGroup.nameEn }" type="hidden" name="nameEn">
										</c:otherwise>
										
									</c:choose>
								</div>
							</div>
	                        <div>
	                        	<table  class="table datatable-basic" style="text-align: center; margin-bottom: 5px;">
	                        		<thead>
	                        			<tr style="background-color: aliceblue">
	                        				<th>Danh sách món ăn trên mater data</th>
	                        				<th>Danh sách món ăn có trong nhóm</th>
	                        			</tr>
	                        		</thead>
	                        	</table>
	                        	<select multiple="multiple" class="form-control listbox" name="foodItemsTransient" data-fouc>
	                        		<c:forEach items="${foodGroup.foodItems }" var="foodItem">
	                        			<option value="${foodItem.id}" ${foodItem.id eq foodGroup.foodItems.id ? 'selected' : ''}>${foodItem.nameVn}</option>
	                        		</c:forEach>
								</select>
	                        </div>
	          		<div class="text-center">
						<button type="submit" class="btn btn-primary">Lưu<i class="icon-paperplane ml-2"></i></button>
						<a href="#"><button class="btn btn-danger">Huỷ<i class="icon-backspace2 ml-2"></i></button></a>
					</div>
	          </form>
			</div>
		</div>
	</div>
</body>
</html>