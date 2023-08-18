function checkErr(fileName){
	var errChart = [" ","à","á","ạ","ả","ã","â","ầ","ấ","ậ","ẩ","ẫ","ă","ằ","ắ","ặ","ẳ","ẵ","è","é","ẹ","ẻ","ẽ","ê","ề","ế","ệ","ể","ễ","ì","í","ị","ỉ","ĩ","ò","ó","ọ","ỏ","õ","ô","ồ","ố","ộ","ổ","ỗ","ơ","ờ","ớ","ợ","ở","ỡ","ù","ú","ụ","ủ","ũ","ư","ừ","ứ","ự","ử","ữ","ỳ","ý","ỵ","ỷ","ỹ","đ"]
	for(var i = 0 ; i < fileName.length ; i++){
		if(errChart.includes(fileName[i])){
			return false;
		}
	}
	return true;
}

var formData;
var foodGroups = [];
var fileNames = [];
var listImageName = [];

function previewFile(avatarUrl){
	// Modal template
        var modalTemplate = '<div class="modal-dialog modal-lg" role="document">\n' +
            '  <div class="modal-content">\n' +
            '    <div class="modal-header align-items-center">\n' +
            '      <h6 class="modal-title">{heading} <small><span class="kv-zoom-title"></span></small></h6>\n' +
            '      <div class="kv-zoom-actions btn-group">{toggleheader}{fullscreen}{borderless}{close}</div>\n' +
            '    </div>\n' +
            '    <div class="modal-body">\n' +
            '      <div class="floating-buttons btn-group"></div>\n' +
            '      <div class="kv-zoom-body file-zoom-content"></div>\n' + '{prev} {next}\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</div>\n';

        // Buttons inside zoom modal
        var previewZoomButtonClasses = {
            toggleheader: 'btn btn-light btn-icon btn-header-toggle btn-sm',
            fullscreen: 'btn btn-light btn-icon btn-sm',
            borderless: 'btn btn-light btn-icon btn-sm',
            close: 'btn btn-light btn-icon btn-sm'
        };

        // Icons inside zoom modal classes
        var previewZoomButtonIcons = {
            prev: '<i class="icon-arrow-left32"></i>',
            next: '<i class="icon-arrow-right32"></i>',
            toggleheader: '<i class="icon-menu-open"></i>',
            fullscreen: '<i class="icon-screen-full"></i>',
            borderless: '<i class="icon-alignment-unalign"></i>',
            close: '<i class="icon-cross2 font-size-base"></i>'
        };

        // File actions
        var fileActionSettings = {
        	maxFileSize: 1000,
            zoomClass: '',
            zoomIcon: '<i class="icon-zoomin3"></i>',
            dragClass: 'p-2',
            dragIcon: '<i class="icon-three-bars"></i>',
            removeClass: '',
            removeErrorClass: 'text-danger',
            removeIcon: '<i class="icon-bin"></i>',
            indicatorNew: '<i class="icon-file-plus text-success"></i>',
            indicatorSuccess: '<i class="icon-checkmark3 file-icon-large text-success"></i>',
            indicatorError: '<i class="icon-cross2 text-danger"></i>',
            indicatorLoading: '<i class="icon-spinner2 spinner text-muted"></i>'
        };


        //
        // Display preview on load
        //
        
        var contextPath = getContext();
        var defaultIcon = $('#defaultIcon').val();
        var avatar;
		var imgName = '';
        
        if (avatarUrl) {
			avatar = avatarUrl;
			var str = avatarUrl.slice(avatarUrl.lastIndexOf("\\") + 1);
			imgName = str.slice(str.lastIndexOf("/") + 1);
        } else {
			avatar = defaultIcon;
		}
        $('.file-thumbnail-footer').html('');
		var avatarName = '<div class="file-footer-caption" title="'+ imgName +'"><div class="file-caption-info">'+ imgName +'</div><div class="file-size-info"> <samp></samp></div></div>';
		$('.file-thumbnail-footer').append(avatarName);
        $('.kv-file-content').html('');
        var img = '<img src="'+avatar+'" class="file-preview-image kv-preview-data" title="" alt="" style="width:auto;height:auto;max-width:100%;max-height:100%;">';
		$('.kv-file-content').append(img);
        //load avatar
        var imgTag = '<img src="' + contextPath + avatar + '"/>';
        
        $('.file-input-overwrite').fileinput({
        	showClose: false,
	        //browseClass: "btn",
	        //removeClass: "btn",
	        showCaption: false,
	        showUpload: false,
	        //browseLabel: '',
		    //removeLabel: '',
	        showRemove : false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 deleteImage"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [avatar],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption : imgName}
            ],
            //defaultPreviewContent: imgTag,
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        }).on('filebatchselected', function(e, files) {
        	formData = new FormData();
        	$.each(files, function (key, value) {
        		if(value != null){
        			formData.append("avatar", value, value.name);
        		}
        	});        
        }); 
}

function checkDuplicateImage(fileName){
	var check = false;
	var duplicate = [];
	if(listImageName != null && listImageName.length > 0){
		for (i=0; i<listImageName.length; i++) {
			if(listImageName[i] == fileName){
				duplicate.push(listImageName[i]);
			}
		}
	}
	if(duplicate.length > 1){
		check = true;
	}
	return check;
}

function delFileName(imageNameOld){
	var idx = listImageName.lastIndexOf(imageNameOld);
	listImageName.splice(idx,1);
}

function createFoodGroupButton(resCode,foodGroupId,foodGroupCode,foodGroupName,foodGroupOrder,menuTypeId,menuTypeName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn,foodGroupParentId,selectedFoodItems,arrText,arrValue,avatarUrl,avatarAbsolutePath,imageNameOld,srcImage,soId,foodGroupCodeOld,foodGroupParentName){
	var contextPath = getContext();
	var changed = 1; // 1 : changed
	var foodGroup = {
			foodGroupId: foodGroupId,
			foodGroupCode: foodGroupCode,
			foodGroupName: foodGroupName,
			foodGroupOrder: foodGroupOrder,
			menuTypeId: menuTypeId,
			menuTypeName: menuTypeName,
			foodGroupLevelId: foodGroupLevelId,
			foodGroupLevelName: foodGroupLevelName,
			foodGroupNameEn: foodGroupNameEn,
			foodGroupParentId: foodGroupParentId,
			selectedFoodItems: selectedFoodItems,
			arrText : arrText,
			arrValue : arrValue,
			avatarUrl: avatarUrl,
			avatarAbsolutePath : avatarAbsolutePath,
			imageNameOld : imageNameOld,
			srcImage : srcImage
		}
		console.log('foodGroup' , foodGroup);
		foodGroups.push(foodGroup);
	window.localStorage.setItem("foodGroups",JSON.stringify(foodGroups));
		
		
	if(foodGroupId == "" || foodGroupCode != foodGroupCodeOld){
		$.ajax({
			url: contextPath + '/foodGroup/checkCode',
			contextType: 'application/json',
			method: 'POST',
			data: {
				soId: soId,
				resCode: resCode,
				code: foodGroupCode
			},
			success: function(data) {
				messageCheckCode.style.display = 'block';
				if(data) {
					$("#modal-group-food").modal("hide");
		    		$('#messageCheckCode').html("");
					$.ajax({
						url : contextPath + '/del/foodGroup/avatar',
						method : 'POST',
						data : {avatarAbsolutePath : avatarAbsolutePath,
						    foodGroupId : foodGroupId
						},
						success : function(data){
						},
						error : function(e){
							console.log(e);
						}
					})
					bootbox.alert({
						title: 'Thông báo:',
		                message: 'Mã nhóm món ăn này đã được sử dụng.Vui lòng nhập mã khác!'
		            });
				} else {
					const rowIndex = $("#rowIndex").val();
		    		if (rowIndex) {
		    			const icon = 'icon-pencil3';
		    			FoodGroup.updateRow(rowIndex, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage, icon,changed);
		    		} else {
		    			const icon = 'icon-plus3';
		        		var size = $('#groupFoodTable tbody tr').length;
		        		var newRow = FoodGroup.createRow(size, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage, icon,changed);
		        		newRow.appendTo('#groupFoodTable tbody');
		    		}
		    		
		    		$("#modal-group-food").modal("hide");
		    		$('#messageCheckCode').html("");
				}
			},
			error: function(err) {
				console.log(err)
			}
		});
	} else {
		const rowIndex = $("#rowIndex").val();
		if (rowIndex) {
			const icon = 'icon-pencil3';
			FoodGroup.updateRow(rowIndex, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage, icon,changed);
		} else {
			const icon = 'icon-plus3';
    		var size = $('#groupFoodTable tbody tr').length;
    		var newRow = FoodGroup.createRow(size, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage, icon,changed);
    		newRow.appendTo('#groupFoodTable tbody');
		}
		
		$("#modal-group-food").modal("hide");
		$('#messageCheckCode').html("");
	}
}

var SoMenuComponent = function() {
	
	var checkAvatar = function(){
		$('#avatar').on('change',function(){
			var fileInput =  document.getElementById('avatar').files; 
			var name = [];
			var allowedExtensions = ["jpg","jpeg","png","gif","tiff","pjp","pjpeg","jfif","tif","svg","bmp","svgz","webp","ico","xbm","dib"];
			
			var filePath = fileInput[0].name;
			var fileF = filePath.slice(filePath.lastIndexOf(".") + 1).toLowerCase();
			var fileSize = fileInput[0].size/1024/1024;
			var screenHeight = screen.height;
			if(fileSize > 3 || (jQuery.inArray(fileF,allowedExtensions)) === -1 || !checkErr(filePath)){
				name.push(filePath);
			}
			if (jQuery.isEmptyObject(name) === false) {
				bootbox.alert({
				    title: "Thông báo:",
				    message: "Ảnh tải lên không đúng định dạng hoặc kích thước tải lên quá lớn hoặc tên ảnh không đúng định dạng. Vui lòng tải ảnh có dung lượng dưới 3Mb",
				});
				$('#modal-full-overflow').css('height', screenHeight*0.9 + 'px');
				$('#modal-overflow').css('overflow', 'auto');
				$('#modal-overflow').css('height', screenHeight*0.7 + 'px');
				
		    }else{
		    	console.log("OK");
			}
		});
	};
	
	var deleteImage = function(){
		$('#removeFile').on('click', function(event) {
			var foodGroupId = $("#foodGroupId").val();
			var avatarAbsolutePath = $("#avatarAbsolutePath").val();
			console.log('deleteAvatar');
			var imageNameOld = $("#imageNameOld").val();
			var contextPath = getContext();
			if(imageNameOld != null && imageNameOld != '' && !checkDuplicateImage(imageNameOld)){
				console.log('index' ,listImageName);
				$.ajax({
					url : contextPath + '/del/foodGroup/avatar',
					method : 'POST',
					data : {avatarAbsolutePath : avatarAbsolutePath,
						    foodGroupId : foodGroupId
					},
					success : function(){
						delFileName(imageNameOld);
						previewFile(null);
						$("#imageNameOld").val('');
						$("#avatarUrl").val('');
						$("#avatarAbsolutePath").val('');
						$("#srcImage").val('');
						$('#avatar').val(null);
					},
					error : function(e){
						console.log(e);
					}
				});
			} else {
				delFileName(imageNameOld);
				previewFile(null);
				$("#imageNameOld").val('');
				$("#avatarUrl").val('');
				$("#avatarAbsolutePath").val('');
				$("#srcImage").val('');
				$('#avatar').val(null);
			}
		});
	}
	
    // Select2
	var initSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();
	};
    
    var initModal = function() {
    	
    	FoodItemSelector.init();
    	
    	$("#createFoodGroupButton").on("click", (e) => {
    		var soId = $('#soId').val();
			var resCode = $('#restaurantCode').val();
    		const foodGroupId = $("#foodGroupId").val();
    		const foodGroupCode = $("#foodGroupCode").val();
    		const foodGroupCodeOld = $("#foodGroupCodeOld").val();
    		//alert("foodGroupCode: " + foodGroupCode);
    		const foodGroupName = $("#foodGroupName").val();
    		//alert("foodGroupName: " + foodGroupName);
    		const foodGroupOrder = $("#foodGroupOrder").val();
    		//alert("foodGroupOrder: " + foodGroupOrder);
    		
    		const menuTypeId = $("#foodGroupMenuType").find(":selected").val();
    		//alert("menuTypeId: " + menuTypeId);
    		const menuTypeName = $("#foodGroupMenuType").find(":selected").text();
    		//alert("menuTypeName: " + menuTypeName);
    		
    		const foodGroupParentId = $("#foodGroupParent").find(":selected").val();
    		//alert("foodGroupParentId: " + foodGroupParentId);
    		const foodGroupParentName = $("#foodGroupParent").find(":selected").text();
    		//alert("foodGroupParentName: " + foodGroupParentName);
    		
    		const foodGroupLevelId = $("#level").find(":selected").val();
    		const foodGroupLevelName = $("#level").find(":selected").text();
    		const foodGroupNameEn = $("#foodGroupNameEn").val();
    		
    		const selectedFoodItems = FoodItemSelector.getSelectedFoodItems();
    		
    		//list menuType Name;
    		var arrText = [];    
    		$("#foodGroupMenuType option").each(function () {    
    			arrText.push($(this).text());    
    		});  
    		//list menuType Id;
    		var arrValue = [];    
    		$("#foodGroupMenuType option").each(function () {    
    			arrValue.push($(this).val());    
    		}); 
    		var contextPath = getContext();
    		var attPath = $("#attachmentPath").val();
     		var attachmentPath = attPath.substring(0,attPath.length - 1);
     		var attContextPath = $("#attachmentContextPath").val();
     		var attachmentContextPath = attContextPath.substring(0,attContextPath.length - 1);
     		var moduleType = "/menu/";
     		
     		var avatarUrl = $("#avatarUrl").val();
     		var avatarAbsolutePath = $("#avatarAbsolutePath").val();
     		var srcImage = $("#srcImage").val();;
     		var imageNameOld = $("#imageNameOld").val();
     		var fileName = '';
    		 var fileInput =  document.getElementById('avatar').files;
    		 if(fileInput != null && fileInput.length > 0){
    			fileName = fileInput[0].name;
    		 }
     		
     		var absolutePath;
     		if(imageNameOld != null && imageNameOld != ''){
     			absolutePath = attachmentPath + moduleType + imageNameOld;
     		}else {
     			absolutePath = '';
     		}

    		if(fileName != null && fileName != '' && imageNameOld != fileName){
     			$.ajax({
                    method: 'POST',
                    url: contextPath + "/foodGroup/upload",
                    dataType: 'text',
                    cache: false,
                    processData: false, // Important!
                    contentType: false, // Important! I set dataType above as Json
                    data: formData, // Important! The formData should be sent this way and not as a dict.
                    success: function (data) {
                    	if(data == 'true'){
    						listImageName.push(fileName);
                    		avatarUrl = attachmentContextPath.concat(moduleType).concat(fileName);
                    		avatarAbsolutePath = attachmentPath.concat(moduleType).concat(fileName);
    						srcImage = moduleType.concat(fileName);
    						console.log('index' ,listImageName);
                    		if(imageNameOld != null && imageNameOld != '' && !checkDuplicateImage(imageNameOld)){
    							console.log('index' ,listImageName);
                    			$.ajax({
        							url : contextPath + '/del/foodGroup/avatar',
        							method : 'POST',
        							data : {avatarAbsolutePath : absolutePath,
        								    foodGroupId : foodGroupId
        							},
        							success : function(){
    									delFileName(imageNameOld);
        							},
        							error : function(e){
        								console.log(e);
        							}
        						});
                    		}
    						/* imageNameOld = fileName; */
    						$("#imageNameOld").val(fileName);
                    		bootbox.alert({
            				    title: "Thông báo:",
            				    message: "Upload ảnh cho nhóm món ăn thành công.",
            				});
                    		createFoodGroupButton(resCode,foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupLevelId, foodGroupLevelName, foodGroupNameEn, foodGroupParentId, selectedFoodItems, arrText, arrValue, avatarUrl, avatarAbsolutePath, fileName, srcImage,soId,foodGroupCodeOld,foodGroupParentName);
                    	}else{
                    		bootbox.alert({
            				    title: "Thông báo:",
            				    message: "Upload ảnh cho nhóm món ăn không thành công.",
            				});
                    	}
                    	formData = new FormData();
                    },
                    error: function (ex) {
    					console.log(ex);
                    	console.log("error");
                    }
                });
     		} else {
    			 createFoodGroupButton(resCode,foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupLevelId, foodGroupLevelName, foodGroupNameEn, foodGroupParentId, selectedFoodItems, arrText, arrValue, avatarUrl, avatarAbsolutePath, imageNameOld, srcImage,soId,foodGroupCodeOld,foodGroupParentName);
    		}
    	});
    	
    	$('#modal-group-food').on('hidden.bs.modal', function(e) {
    		$('#modal-group-food').find(':input').each(function() {
			    switch (this.type) {
			        case 'password':
			        case 'select':
			        case 'text':
			        case 'hidden':
			        case 'textarea':
			            $(this).val('');
			            break;
			        case 'checkbox':
			        case 'radio':
			            this.checked = false;
			    }
    		});
    		
    		FoodItemSelector.clear();
    	});    	
    }
    
    // Check code foodGroup
    var checkCodeFoodGroup = function() {
		$('#foodGroupCode').on('keyup', function(e){
			var soId = $('#soId').val();
			var resCode = $('#restaurantCode').val();
			var code = $('#foodGroupCode').val();
			var contextPath = getContext();
			var messageCheckCode = document.getElementById('messageCheckCode');
			if(code != ''){
				$.ajax({
					url: contextPath + '/foodGroup/checkCode',
					contextType: 'application/json',
					method: 'POST',
					data: {
						soId: soId,
						resCode: resCode,
						code: code
					},
					success: function(data) {
						messageCheckCode.style.display = 'block';
						if(data) {
							messageCheckCode.style.color = 'red';
							$('#createFoodGroupButton').attr("disabled","disabled");
							$('#messageCheckCode').html('\u004d\u00e3 \u006e\u0068\u00f3\u006d \u006d\u00f3\u006e \u0103\u006e \u0111\u00e3 \u0074\u1ed3\u006e \u0074\u1ea1\u0069\u002e')
						} else {
							console.log(document.getElementById('foodGroupCode').value.length)
							if(document.getElementById('foodGroupCode').value.length != 0){
								messageCheckCode.style.color = 'green';
								$('#createFoodGroupButton').removeAttr('disabled');
								$('#messageCheckCode').html('\u004d\u00e3 \u006e\u0068\u00f3\u006d \u006d\u00f3\u006e \u0103\u006e \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u002e')
							} else {
								messageCheckCode.style.color = 'red';
								$('#createFoodGroupButton').attr("disabled","disabled");
								$('#messageCheckCode').html('\u0059\u00ea\u0075 \u0063\u1ea7\u0075 \u006e\u0068\u1ead\u0070 \u006d\u00e3 \u006e\u0068\u00f3\u006d \u006d\u00f3\u006e \u0103\u006e\u002e')
							}
							
						}
					},
					error: function(err) {
						console.log(err)
					}
				});
			} else {
				$('#messageCheckCode').html("");
			}
		});
	};
	//
	// delete message checkCode when close modal
	var closeModal = function(){
		$('#closeModal').on('click', function(){
			$('#messageCheckCode').html("");
		})
	}
	
	var submitForm = function() {
		$('#btnSubmit').on('click', function(){
			localStorage.clear();
			$('#menuForm').submit();
		})
	}
    //
    // Return objects assigned to module
    //
	
	//delete deleteChild && delete deleteChild
	var deleteChild = function(){
		$('#deleteChild').on('click', function(){
			$(".select2-items-child").html("");
			/*$(".select2-items-child").select2("val", "");
			$('.select2-items-child').val(null).trigger('change');*/
		})
	}
	
	var deleteAdult = function(){
		$('#deleteAdult').on('click', function(){
			$(".select2-items-adult").html("");
			/*$(".select2-items-adult").select2("val", "");*/
		})
	}

	var submitForm = function() {
		$('#btnSubmit').on('click', function(){
			var soCategoryId = $('#soCategoryId').val();
			const soCategoryStatus = $('#status').is(':checked');
			if(soCategoryId != null && soCategoryStatus !== true){
				bootbox.confirm({
					title: "Xác nhận:",
					message: "Việc off menu sẽ gửi dữ liệu xuống server nhà hàng. Bạn có chắc chắn muốn off danh mục này không?",
					buttons: {
						cancel: {
							label: '<i class="fa fa-times"></i> Hủy'
						},
						confirm: {
							label: '<i class="fa fa-check"></i> Thực hiện'
						}
					},
					callback: function (result) {
						console.log('This was logged in the callback: ' + result);
						if (result) {
							localStorage.clear();
							// $('#menuForm').attr('action', '/cag/so-menu/soCategory/off');
							$("#menuForm").submit();
							$('#pleaseWaitDialog').modal();
						}
					}
				});  
			} else {
				localStorage.clear();
				$('#menuForm').submit();
				$('#pleaseWaitDialog').modal();
			}
		})
	}

	// Bootstrap switch
	var initBootstrapSwitch = function() {
		if (!$().bootstrapSwitch) {
			console.warn('Warning - switch.min.js is not loaded.');
			return;
		}

		// Initialize
		$('.form-check-input-switch').bootstrapSwitch();
	};

    return {
        init: function() {
        	initSelect2();
        	initModal();
        	checkCodeFoodGroup();
        	closeModal();
        	deleteChild();
        	deleteAdult();
        	submitForm();
        	checkAvatar();
        	deleteImage();
			initBootstrapSwitch();
        }
    }
}();


var FoodGroup = {
	createRow: (index, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage,icon,enableDeleted,changed) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-'+counter+'">');
        var cols = "";

        var spanIcon = '';
        if (icon) {
        	spanIcon = '<span class="badge bg-success-300 rounded-circle badge-icon"><i class="'+icon+'"></i></span>';
        }
        
        if(typeof(changed) == "undefined"){
        	changed = 1;
        }
        
        cols += '<td><span class="no">'+counter+'</span>&nbsp;'+spanIcon+'<input id="foodGroupId'+index+'" class="foodGroupId" type="hidden" name="foodGroups['+index+'].id" value="'+foodGroupId+'"></td>';
        cols += '<td>'+foodGroupCode+'<input id="foodGroupCode'+index+'" type="hidden" name="foodGroups['+index+'].code" value="'+foodGroupCode+'"></td>';
        cols += '<td>'+foodGroupName+'<input id="foodGroupName'+index+'" type="hidden" name="foodGroups['+index+'].nameVn" value="'+foodGroupName+'"></td>';
        cols += '<td>'+foodGroupNameEn+'<input id="foodGroupNameEn'+index+'" type="hidden" name="foodGroups['+index+'].nameEn" value="'+foodGroupNameEn+'"></td>';
        cols += '<td>'+menuTypeName+'<input id="menuTypeId'+index+'" type="hidden" name="foodGroups['+index+'].menuType.id" value="'+menuTypeId+'"></td>';
        cols += '<td>'+foodGroupOrder+'<input id="foodGroupOrder'+index+'" type="hidden" name="foodGroups['+index+'].groupOrder" value="'+foodGroupOrder+'"><input type="hidden" class="foodGroupChanged" id="foodGroupChanged'+index+'" name="foodGroups['+index+'].changed" value="'+changed+'"></td>';
        cols += '<td>'+foodGroupParentName+'<input id="foodGroupParentId'+index+'" class="foodGroupParentId" type="hidden" name="foodGroups['+index+'].parent.id" value="'+foodGroupParentId+'"><input id="srcImage'+index+'" type="hidden" name="foodGroups['+index+'].srcImage" value="'+srcImage+'"></td>';
        cols += '<td>'+foodGroupLevelName+'<input id="foodGroupLevelId'+index+'" type="hidden" name="foodGroups['+index+'].level" value="'+foodGroupLevelId+'"><input id="absolutePath'+index+'" type="hidden" name="foodGroups['+index+'].absolutePath" value="'+avatarAbsolutePath+'"></td>';
        cols += '<td class="text-left"><input type="hidden" name="updatedFoodGroups" value="'+foodGroupId+'"><input type="hidden" name="foodGroups['+index+'].selectedFoodItems" value="'+selectedFoodItems+'"><input type="hidden" name="foodGroups['+index+'].checked" value="true">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodGroup.showForm(\''+foodGroupCode+'\','+counter+');" class="list-icons-item text-primary-600" data-popup="tooltip" data-container="body"><i class="icon-pencil7"></i></a>';
        cols += '<a href="javascript:FoodGroup.removeRow('+counter+','+enableDeleted+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},

	updateRow: (rowIndex, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage, icon,changed) => {
		var index = rowIndex - 1;
        var updatedRow = $('#rec-'+rowIndex)
        var cols = "";

        var spanIcon = '';
        if (icon) {
        	spanIcon = '<span class="badge bg-success-300 rounded-circle badge-icon"><i class="'+icon+'"></i></span>';
        }
        
        if(typeof(changed) == "undefined"){
        	changed = 1;
        }

        cols += '<td><span class="no">'+rowIndex+'</span>&nbsp;'+spanIcon+'<input id="foodGroupId'+index+'" type="hidden" class="foodGroupId" name="foodGroups['+index+'].id" value="'+foodGroupId+'"></td>';
        cols += '<td>'+foodGroupCode+'<input id="foodGroupCode'+index+'" type="hidden" name="foodGroups['+index+'].code" value="'+foodGroupCode+'"></td>';
        cols += '<td>'+foodGroupName+'<input id="foodGroupName'+index+'" type="hidden" name="foodGroups['+index+'].nameVn" value="'+foodGroupName+'"></td>';
        cols += '<td>'+foodGroupNameEn+'<input id="foodGroupNameEn'+index+'" type="hidden" name="foodGroups['+index+'].nameEn" value="'+foodGroupNameEn+'"></td>';
        cols += '<td>'+menuTypeName+'<input id="menuTypeId'+index+'" type="hidden" name="foodGroups['+index+'].menuType.id" value="'+menuTypeId+'"></td>';
        cols += '<td>'+foodGroupOrder+'<input id="foodGroupOrder'+index+'" type="hidden" name="foodGroups['+index+'].groupOrder" value="'+foodGroupOrder+'"><input type="hidden" class="foodGroupChanged" id="foodGroupChanged'+index+'" name="foodGroups['+index+'].changed" value="'+changed+'"></td>';
        cols += '<td>'+foodGroupParentName+'<input id="foodGroupParentId'+index+'" class="foodGroupParentId" type="hidden" name="foodGroups['+index+'].parent.id" value="'+foodGroupParentId+'"><input id="srcImage'+index+'" type="hidden" name="foodGroups['+index+'].srcImage" value="'+srcImage+'"></td>';
        cols += '<td>'+foodGroupLevelName+'<input id="foodGroupLevelId'+index+'" type="hidden" name="foodGroups['+index+'].level" value="'+foodGroupLevelId+'"><input id="absolutePath'+index+'" type="hidden" name="foodGroups['+index+'].absolutePath" value="'+avatarAbsolutePath+'"></td>';
        cols += '<td class="text-left"><input type="hidden" name="updatedFoodGroups" value="'+foodGroupId+'"><input type="hidden" name="foodGroups['+index+'].selectedFoodItems" value="'+selectedFoodItems+'"><input type="hidden" name="foodGroups['+index+'].checked" value="true">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodGroup.showForm(\''+foodGroupCode+'\','+rowIndex+');" class="list-icons-item text-primary-600" data-popup="tooltip" data-container="body"><i class="icon-pencil7"></i></a>';
        cols += '<a href="#" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+rowIndex+'" onclick="FoodGroup.removeRow('+rowIndex+')" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        updatedRow.html(cols);
	},

	deleteRow: (rowIndex, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn,avatarAbsolutePath,srcImage, icon,changed) => {
		var index = rowIndex - 1;
        var deleteRow = $('#rec-'+rowIndex)
        var cols = "";

        var spanIcon = '';
        if (icon) {
        	spanIcon = '<span class="badge bg-warning-300 rounded-circle badge-icon"><i class="'+icon+'"></i></span>';
        }

        cols += '<td><span class="no">'+rowIndex+'</span>&nbsp;'+spanIcon+'<input id="foodGroupId'+index+'" type="hidden" name="foodGroups['+index+'].id" value="'+foodGroupId+'"></td>';
        cols += '<td>'+foodGroupCode+'<input id="foodGroupCode'+index+'" type="hidden" name="foodGroups['+index+'].code" value="'+foodGroupCode+'"></td>';
        cols += '<td>'+foodGroupName+'<input id="foodGroupName'+index+'" type="hidden" name="foodGroups['+index+'].nameVn" value="'+foodGroupName+'"></td>';
        cols += '<td>'+foodGroupNameEn+'<input id="foodGroupNameEn'+index+'" type="hidden" name="foodGroups['+index+'].nameEn" value="'+foodGroupNameEn+'"></td>';
        cols += '<td>'+menuTypeName+'<input id="menuTypeId'+index+'" type="hidden" name="foodGroups['+index+'].menuType.id" value="'+menuTypeId+'"></td>';
        cols += '<td>'+foodGroupOrder+'<input id="foodGroupOrder'+index+'" type="hidden" name="foodGroups['+index+'].groupOrder" value="'+foodGroupOrder+'"><input type="hidden" class="foodGroupChanged" id="foodGroupChanged'+index+'" name="foodGroups['+index+'].changed" value="'+changed+'"></td>';
        cols += '<td>'+foodGroupParentName+'<input id="foodGroupParentId'+index+'" type="hidden" name="foodGroups['+index+'].parent.id" value="'+foodGroupParentId+'"><input id="srcImage'+index+'" type="hidden" name="foodGroups['+index+'].srcImage" value="'+srcImage+'"></td>';
        cols += '<td>'+foodGroupLevelName+'<input id="foodGroupLevelId'+index+'" type="hidden" name="foodGroups['+index+'].level" value="'+foodGroupLevelId+'"><input id="absolutePath'+index+'" type="hidden" name="foodGroups['+index+'].absolutePath" value="'+avatarAbsolutePath+'"></td>';
        if(foodGroupId){
        	 cols += '<td class="text-left"><input type="hidden" name="deletedFoodGroups" value="'+foodGroupId+'"><input type="hidden" name="foodGroups['+index+'].checked" value="true"></td>';
        } else {
        	 cols += '<td class="text-left"><input type="hidden" name="deletedFoodGroups" value="'+foodGroupId+'"><input type="hidden" name="foodGroups['+index+'].checked" value="false"></td>';
        }
        
        cols += '<div class="list-icons">';
        cols += '<a class="list-icons-item text-primary-600" data-popup="tooltip" data-container="body"><i class="icon-pencil7"></i></a>';
        cols += '<a class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+rowIndex+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        deleteRow.html(cols);
	},
	
	removeRow: (rowIndex,enableDeleted) => {
		var contextPath = getContext();
		if(enableDeleted == false){
			bootbox.alert({
                title: 'Thông báo:',
                message: 'Không thể xóa nhóm món ăn này. Vui lòng xóa nhóm con trước.'
            });
		} else {
			var didConfirm = confirm("Are you sure you want to delete ?");
		    if (didConfirm == true) {
	    		if (rowIndex) {
	    	    	const index = rowIndex - 1;
		    		const foodGroupId = $("#foodGroupId"+index).val();
		    		const foodGroupCode = $("#foodGroupCode"+index).val();
		    		const foodGroupName = $("#foodGroupName"+index).val();
		    		const foodGroupOrder = $("#foodGroupOrder"+index).val();
		    		const menuTypeId = $("#menuTypeId"+index).val();
		    		const menuTypeName = '';
		    		const foodGroupParentId = $("#foodGroupParentId"+index).val();
		    		const foodGroupParentName = '';
		    		
		    		const foodGroupNameEn = $("#foodGroupNameEn"+index).val();
		    		const foodGroupLevelId = $("#foodGroupLevelId"+index).val();
		    		const foodGroupLevelName = '';
		    		const avatarAbsolutePath = $("#absolutePath"+index).val();
		    		const srcImage = $("#srcImage"+index).val();
		    		const changed = 2; // 2 : delete
		    		
	    			const icon = 'icon-cross2';
	    			$.ajax({
						url : contextPath + '/del/foodGroup/avatar',
						method : 'POST',
						data : {avatarAbsolutePath : avatarAbsolutePath,
						    foodGroupId : foodGroupId
						},
						success : function(data){
						},
						error : function(e){
							console.log(e);
						}
					})
	    			FoodGroup.deleteRow(rowIndex, foodGroupId, foodGroupCode, foodGroupName, foodGroupOrder, menuTypeId, menuTypeName, foodGroupParentId, foodGroupParentName,foodGroupLevelId,foodGroupLevelName,foodGroupNameEn,avatarAbsolutePath,srcImage, icon,changed);
	    		}
		    } else {
		    }
		}
	},
	
	loadFoodGroups: function() {
		const contextPath = getContext();
		const soId = $("#soId").val();
		const soCategoryId = $("#soCategoryId").val();
		const restaurantCode = $("#restaurantCode").val();
		var attPath = $("#attachmentPath").val();
		var attachmentPath = attPath.substring(0,attPath.length - 1);
		if (soCategoryId) {
			$.ajax({
				url: contextPath + '/api/so/get-food-groups',
				method:'GET',
				data:{soId:soId, scId:soCategoryId, resCode:restaurantCode},
				success: function(data) {
					for (i=0; i<data.length; i++) {
						let foodGroupParentId = "";
						let foodGroupParentName = "";
						if (data[i].foodGroupParentId && data[i].foodGroupParentName) {
							foodGroupParentId = data[i].foodGroupParentId;
							foodGroupParentName = data[i].foodGroupParentName;
						}
						let foodGroupOrder = "";
						if (data[i].foodGroupOrder) {
							foodGroupOrder = data[i].foodGroupOrder;
						}
						
						let foodGroupNameEn = "";
						if (data[i].foodGroupNameEn) {
							foodGroupNameEn = data[i].foodGroupNameEn;
						}
						
						let foodGroupLevelId = "";
						let foodGroupLevelName ="";
						if (data[i].foodGroupLevel && data[i].foodGroupLevelName) {
							foodGroupLevelId = data[i].foodGroupLevel;
							foodGroupLevelName = data[i].foodGroupLevelName;
						}
						
						let selectedFoodItems = "";
						if (data[i].selectedFoodItems) {
							selectedFoodItems = data[i].selectedFoodItems;
						}
						
						const icon = '';
						var avatarAbsolutePath = "";
						if (data[i].srcImage) {
							avatarAbsolutePath = attachmentPath.concat(data[i].srcImage);
						}
						var srcImage = "";
						if (data[i].srcImage) {
							srcImage = data[i].srcImage;
							listImageName.push(data[i].srcImage.slice(data[i].srcImage.lastIndexOf("/") + 1))
						}
						var changed = "0"; // 1 : changed
						if (data[i].changed) {
							changed = data[i].changed;
						}
						var newRow = FoodGroup.createRow(i, data[i].foodGroupId, data[i].foodGroupCode, data[i].foodGroupName,foodGroupOrder, data[i].menuTypeId, data[i].menuTypeName, foodGroupParentId, foodGroupParentName,data[i].foodGroupLevel,data[i].foodGroupLevelName,foodGroupNameEn, selectedFoodItems,avatarAbsolutePath,srcImage,icon,data[i].enableDeleted,changed);
						newRow.appendTo('#groupFoodTable tbody');	
					}
				},
				error: function (e) {
					console.log("error: ",e);
				}
			});
		}
	},
	
	loadFoodGroupParents: (scId,restaurantCode, foodGroupCode) => {
		var contextPath = getContext();
    	$.ajax({
    		type: "GET",
    		url: contextPath + "/api/so/get-food-group-parents",
    		data: {resCode:restaurantCode, fgCode: foodGroupCode,scId: scId},
    		success: function(data) {
    			let str = '<option value="">&nbsp;</option>';
				data.forEach((item) => {
					if (item.selected) {
						str += "<option value=" + item.id + " selected>" +  item.code + " - " + item.nameVn + "</option>";
					} else {
						str += "<option value=" + item.id + ">" +  item.code + " - " + item.nameVn + "</option>";	
					}
				});
				$("#foodGroupParent").html(str);
    		},
    		error: function(xhr, message, e){ console.log(message)}
    	});
	},
	
	showForm: (foodGroupCode, rowIndex) => {
		const contextPath = getContext();
		const scId = $("#soCategoryId").val();
		const resCode = $("#restaurantCode").val();
		var dataFoodGroups = JSON.parse(localStorage.getItem("foodGroups"));
		var attContextPath = $("#attachmentContextPath").val();
		var attachmentContextPath = attContextPath.substring(0,attContextPath.length - 1);
		var attPath = $("#attachmentPath").val();
		var attachmentPath = attPath.substring(0,attPath.length - 1);
		var foodGroup;
		if(dataFoodGroups) {
			dataFoodGroups.forEach(item => {
				if(item.foodGroupCode == foodGroupCode) {
					foodGroup = item;
				}
			});
		}
		if (foodGroup) {
			$("#foodGroupId").val(foodGroup.foodGroupId);
			$("#foodGroupCode").val(foodGroup.foodGroupCode);
			$("#foodGroupName").val(foodGroup.foodGroupName);
			$("#foodGroupCodeOld").val(foodGroup.foodGroupCode);
			$("#foodGroupOrder").val(foodGroup.foodGroupOrder);
			$("#foodGroupNameEn").val(foodGroup.foodGroupNameEn);
			$("#foodGroupOrder").val(foodGroup.foodGroupOrder);
			$("#level").val(foodGroup.foodGroupLevelId);
			$("#level").val(foodGroup.foodGroupLevelId).trigger('change');
			/*MenuType.renderMenuType(data.menuTypeId, data.menuTypeName);*/
			$("#avatarUrl").val(foodGroup.avatarUrl);
			$("#avatarAbsolutePath").val(foodGroup.avatarAbsolutePath);
			$("#imageNameOld").val(foodGroup.imageNameOld);
			$("#srcImage").val(foodGroup.srcImage);
			if(foodGroup.avatarUrl != null && foodGroup.avatarUrl != ''){
				previewFile(foodGroup.avatarUrl);
			} else {
				previewFile(null);
			}
			
			MenuType.renderMenuTypes(foodGroup.menuTypeId,foodGroup.arrValue,foodGroup.arrText);
			FoodGroup.loadFoodGroupParents(scId,resCode, foodGroup.foodGroupCode);
			FoodItemSelector.loadFromLocalStorage(foodGroup.selectedFoodItems);
		}else if (foodGroupCode) {
        	$.ajax({
        		type: "GET",
        		url: contextPath + "/api/so/get-so-category-food-group",
        		data: {scId:scId, fgCode:foodGroupCode, resCode:resCode},
        		success: function(data){
        			if (data && data.foodGroupId) {
            			$("#foodGroupId").val(data.foodGroupId);
            			$("#foodGroupCode").val(data.foodGroupCode);
            			$("#foodGroupName").val(data.foodGroupName);
            			$("#foodGroupCodeOld").val(data.foodGroupCode);
            			$("#foodGroupNameEn").val(data.foodGroupNameEn);
            			$('#foodGroupOrder').val(data.foodGroupOrder);
            			$("#level").val(data.foodGroupLevel);
            			$("#level").val(data.foodGroupLevel).trigger('change');
            			/*MenuType.renderMenuType(foodGroup.menuTypeId, foodGroup.menuTypeName);*/
            			
            			$("#avatarUrl").val(attachmentContextPath.concat(data.srcImage));
            			$("#avatarAbsolutePath").val(attachmentPath.concat(data.srcImage));
            			if(data.srcImage != null && data.srcImage != ''){
            				$("#imageNameOld").val(data.srcImage.slice(data.srcImage.lastIndexOf("/") + 1));
            			}
            			$("#srcImage").val(data.srcImage);
            			$("#level").val(data.foodGroupLevel);
            			$("#level").val(data.foodGroupLevel).trigger('change');
            			
            			MenuType.renderMenuTypes(data.menuTypeId,data.arrValues,data.arrTexts);
                    	FoodGroup.loadFoodGroupParents(scId,resCode, foodGroupCode);
                    	FoodItemSelector.loadExistingFoodItem(data.foodGroupId);
                    	if(data.srcImage != null && data.srcImage != ''){
                    		previewFile(attachmentContextPath.concat(data.srcImage));
            			} else {
            				previewFile(null);
            			}
        			}
        		},
        		error: function(xhr, message, e){ console.log(message)}
        	});
		} else {
			MenuType.loadMenuTypes();
			FoodGroup.loadFoodGroupParents(scId,resCode);
			previewFile(null);
		}
		
		if (rowIndex) {
			$("#rowIndex").val(rowIndex);
		}
		$("#modal-group-food").modal("show");	
	}
}


var MenuType = {
	renderMenuType: (id, name) => {
		var menuType = '';
		if (id && name) {
	    	menuType = "<option value=" + id + ">" + name + "</option>";
		} else {
			menuType = "<option>&nbsp;</option>";
		}
		$("#foodGroupMenuType").html(menuType);
	},
	
	renderMenuTypes: (id,ids,names) => {
		var menuType = '';
		
		for (i = 0; i < ids.length; i++) {
		  if(ids[i] == id){
			  menuType += "<option value=" + ids[i] + " selected>" + names[i] + "</option>";
		  }else{
			  menuType += "<option value=" + ids[i] + ">" + names[i] + "</option>";
		  }
		}
		$("#foodGroupMenuType").html(menuType);
	},
	
	loadMenuTypes : () => {
		$.ajax({
			url: getContext() + '/api/so/get-menu-types',
			method:'GET',
			success: function(data) {
				var opt = "";
				$.each(data, function (idx, item) {
					opt += "<option value=" + item.id + ">" + item.name + "</option>";
				});
				$("#foodGroupMenuType").html(opt);
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	}
}

var TicketBuffet = {
		load : () =>{
			var soCategoryId = $('#soCategoryId').val();
			if(soCategoryId != null && soCategoryId != ''){
				$.ajax({
					url: getContext() + '/api/so/load-ticketBuffet',
					data : {soCategoryId : soCategoryId
					},
					method:'GET',
					success: function(data) {
						var adult = "";
						var child = "";
						adult = "<option></option>";
						child = "<option></option>";
						if(data.adultBufferTicket != null && data.adultBufferTicket != ''){
							adult = "<option value=" + data.adultBufferTicket + ">" + data.adultBufferTicket + "</option>";
						}
						if(data.kidBufferTicket != null && data.kidBufferTicket != ''){
							child = "<option value=" + data.kidBufferTicket + ">" + data.kidBufferTicket + "</option>";
						}
						$(".select2-items-adult").html(adult);
						
						$(".select2-items-child").html(child);
					},
					error: function (e) {
						console.log("error: ",e);
					}
				});
			}
			
		}
	}
// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	SoMenuComponent.init();
	FoodGroup.loadFoodGroups();
	TicketBuffet.load();
});

