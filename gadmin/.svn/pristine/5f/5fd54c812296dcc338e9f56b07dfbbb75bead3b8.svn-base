<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/themes/common/taglibs.jsp"%>

<html>
<head>
<meta name="menu" content="restaurantMenu"/>
<title>Restaurant Menu Form</title>
	<script src="/themes/admin/global_assets/js/plugins/forms/inputs/duallistbox/duallistbox.min.js"></script>
	<script src="/themes/admin/global_assets/js/demo_pages/form_dual_listboxes.js"></script>
</head>
<body>
	<div class="res-content">
		<div class="card">
			<div class="card-header header-elements-inline" style="background-color: aliceblue; justify-content: center;">
				<c:choose>
					<c:when test="${menu.id eq null }">
						<h1><strong>THÊM MỚI MENU</strong></h1>
					</c:when>
					<c:otherwise>
						<h1 >CHỈNH SỬA THÔNG TIN MENU</h1>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body]">
				<form action="/restaurant/menu/save" method="POST" modelAttribut="menuDto">
					<input type="hidden" name="idRes" value="${restaurantDto.id }">
					<input value="${menu.id }" type="hidden" name="id">
					<c:choose>
						<c:when test="${menu.id eq null }">
							<input value="${restaurantDto.id }" type="hidden" name="ownerId">
						</c:when>
						<c:otherwise>
							<input value="${menu.ownerId }" type="hidden" name="ownerId">
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${menu.id eq null }">
							<input type="hidden" name="ownerType">
						</c:when>
						<c:otherwise>
							<input value="${menu.ownerType }" type="hidden" name="ownerType">
						</c:otherwise>
					</c:choose>
					
					<div class="form-group col-lg-12">
						<div class="row">
							<div class="col-lg-4">
								<label class="col-form-label">Mã loại menu</label>
								<input class="form-control" value="${menu.code }" name="code"/>
							</div>
							<div class="col-lg-4">
								<label class="col-form-label">Tên loại menu</label>
								<input type="text" class="form-control" value="${menu.nameVn }" name="nameVn"/>
							</div>
							<div class="col-lg-4">
								<label class="col-form-label">Loại menu cha</label>
								<select class="form-control" name="parent.id">
									<option></option>
	                            	 <c:forEach items="${menus }" var="menuParent">
	                            		<c:choose>
	                            			<c:when test="${menu.parent.id eq null }">
	                            				<option value="${menuParent.id }">${menuParent.nameVn}</option>
	                            			</c:when>
	                            			<c:otherwise>
	                            				<option value="${menuParent.id }" ${menuParent.id eq menu.parent.id ? 'selected' : ''  }>${menuParent.nameVn}</option>
	                            			</c:otherwise>
	                            		</c:choose>
	                            	</c:forEach>
	                            </select>
							</div>
						</div>
					</div>
					<div class="form-group col-lg-12">
						<div class="row">
							<div class="col-lg-4">
								<label class="col-form-label">Vé Buffe người lớn </label>
								<input class="form-control" value="${menu.adultTicket }" name="adultTicket"/> 
							</div>
							<div class="col-lg-4">
								<label class="col-form-label">Vé Buffe trẻ em</label>
								 <input class="form-control" value="${menu.childTicket }" name="childTicket"/> 
							</div>
							<c:choose>
								<c:when test="${menu.id eq null }">
									<div class="col-lg-4">
										<label class="col-form-label">Tên tiếng Anh</label>
										 <input class="form-control" name="nameEn"/> 
									</div>
								</c:when>
								<c:otherwise>
									<input value="${menu.nameEn }" type="hidden" name="nameEn">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group col-lg-12">
						<div class="row">
							
								<c:choose>
									<c:when test="${menu.id eq null }">
										<div class="col-lg-4">
											<label class="col-form-label">Mô tả tiếng Anh</label>
											 <textarea class="form-control" name="descEn" style="border-radius: 10px; height: 50px"></textarea>
										</div>
									</c:when>
									<c:otherwise>
											<input value="${menu.descEn }" type="hidden" name="descEn">
									</c:otherwise>
								</c:choose>
							
								<c:choose>
									<c:when test="${menu.id eq null }">
										<div class="col-lg-4">
											<label class="col-form-label">Mô tả tiếng Việt</label>
											 <textarea class="form-control" name="descVn" style="border-radius: 10px; height: 50px"></textarea> 
										</div>
									</c:when>
									<c:otherwise>
											<input value="${menu.descVn }" type="hidden" name="descVn">
									</c:otherwise>
								</c:choose>
						</div>
					</div>
					<div>
                       	<table  class="table datatable-basic" style="text-align: center; margin-bottom: 5px;">
                       		<thead>
                       			<tr style="background-color: aliceblue">
                       				<th>Danh sách nhóm món ăn trên mater data</th>
                       				<th>Danh sách nhóm món ăn trong menu</th>
                       			</tr>
                       		</thead>
                       	</table>
                       	<select name="foodGroupTransient" multiple="multiple" class="form-control listbox" data-fouc="" tabindex="-1" aria-hidden="true">
                       		<c:forEach items="${menu.foodGroups }" var="foodGroup">
                       			<option value="${foodGroup.id}" selected>${foodGroup.nameVn}</option>
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