function delPhoto(aId) {
	soCategoryComponent.delPhoto(aId);
}

function delPhotoRes(aId) {
	soCategoryComponent.delPhotoRes(aId);
}

function delphotoMenu(aId) {
	soCategoryComponent.delphotoMenu(aId);
}

function checkErrorFileName(fileName){
	var regex = /^[a-zA-Z0-9._-]$/;
	for(var i = 0 ; i < fileName.length ; i++){
		if(!regex.test(fileName[i])) {
			return false;
		}
	}
	return true;
}

var fileNameImages = [];
var soCategoryForm = function() {
    var infoSoCategory = function(){
    	$('#selectItem').on('change',function(e){
    		var contextPath = getContext();
        	var itemSelected = $("#selectItem option:selected").val();
    		if(itemSelected != null && itemSelected != ''){
    			$.ajax({
    				url: contextPath + '/co/get-info-soCategory',
    				contextType: 'application/json',
    				method: 'GET',
    				data: {
    					id: itemSelected
    				},
    				success: function(data) {
    					if (data.type == 1) {
    						$('#menu-type-text').val('ACL');	
    					} else {
    						$('#menu-type-text').val('BUF');
    					}
    					$('#menu-type-value').val(data.type);
    					$('#adultBufferTicket').val(data.adultBufferTicket);
    					$('#kidBufferTicket').val(data.kidBufferTicket);
    				},
    				error: function(err) {
    					console.log(err);
    				}
    			});
    		}else{
    			resetText();
    		}
        	
		});
    };

 // Validate form
	var validateForm = function(){
		$('#soCategoryForm').validate({
			ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
		    errorClass: 'validation-invalid-label',
		    successClass: 'validation-valid-label',
		    validClass: 'validation-valid-label',
		    highlight: function(element, errorClass) {
		        $(element).removeClass(errorClass);
		    },
		    unhighlight: function(element, errorClass) {
		        $(element).removeClass(errorClass);
		    },
		    /*success: function(label) {
		        label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
		    },*/

		    // Different components require proper error label placement
		    errorPlacement: function(error, element) {

		        // Unstyled checkboxes, radios
		        if (element.parents().hasClass('form-check')) {
		            error.appendTo( element.parents('.form-check').parent() );
		        }

		        // Input with icons and Select2
		        else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
		            error.appendTo( element.parent() );
		        }

		        // Input group, styled file input
		        else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
		            error.appendTo( element.parent().parent() );
		        }

		        // Other elements
		        else {
		            error.insertAfter(element);
		        }
		    },
		    rules: {
				"descVn": {
					maxlength: 512,
				},
				"soCategory.id":{
					required: true,
				},
				"descEn": {
					maxlength: 512,
				},
				"descJp": {
					maxlength: 512,
				},
				"descKr": {
					maxlength: 512,
				},
				"descCn": {
					maxlength: 512,
				},
				"alacarteLabel": {
					maxlength: 256,
				},
				"buffetLabel": {
					maxlength: 256,
				},
				"drinksLabel": {
					maxlength: 256,
				},
				"alacarteLabelEn": {
					maxlength: 256,
				},
				"buffetLabelEn": {
					maxlength: 256,
				},
				"drinksLabelEn": {
					maxlength: 256,
				},
				"nameVn": {
					maxlength: 256,
				},
				"nameEn": {
					maxlength: 256,
				},
				"averageAmount": {
					number : true,
					maxlength: 15,
				},
				"positionNumber": {
					number : true,
					maxlength: 15,
				}
				
			},
			messages: {
				"descVn": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"descEn": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"descJp": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"descCn": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"descKr": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"soCategory.id":{
					required:"Bắt buộc chọn loại Menu",
				},
				"nameEn": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"nameVn": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"drinksLabelEn": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"buffetLabelEn": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"alacarteLabelEn": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"drinksLabel": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"buffetLabel": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"alacarteLabel": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
				"averageAmount": {
					number : "Số tiền ăn trung bình phải là số",
					maxlength: "Hãy nhập tối đa 15 ký tự",
				},
				"positionNumber": {
					number : "Vị trí hiển thị phải là số",
					maxlength: "Hãy nhập tối đa 15 ký tự",
				}
			}
		});
	}
    
	 var initForm = function() {
	        if (!$().select2) {
	            console.warn('Warning - select2.js is not loaded.');
	            return;
	        }
	    	// select2
	   	 	$('.select2').select2();
		}
	
    var backPage = function(){
    	$('#back').on('click',function(){
    		history.back();
    	})
    };

	var imageFiles = [];
 // Bootstrap file upload
    var _componentCoFoodItemForm = function() {
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

		var formData;
        // Display preview on load
        var contextPath = getContext();
        var defaultIcon = $('#defaultIcon').val();
		var avatarUrl = $('#avatarUrl').val();
        var avatar;
        var avatarName =  '';
        if (avatarUrl) {
        	avatar = avatarUrl;
        	var str = avatarUrl.slice(avatarUrl.lastIndexOf("\\") + 1);
        	avatarName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	avatar = defaultIcon;
		}
        var videoIntroMenuUrl = $('#videoIntroMenuUrl').val();
        var videoIntroMenu;
        var videoIntroMenuName =  '';
        if (videoIntroMenuUrl) {
        	videoIntroMenu = videoIntroMenuUrl;
        	var videoIntroMenu = videoIntroMenuUrl.slice(videoIntroMenuUrl.lastIndexOf("\\") + 1);
        	videoIntroMenuName = videoIntroMenu.slice(videoIntroMenu.lastIndexOf("/") + 1);
        } else {
        	videoIntroMenu = defaultIcon;
		}
        var videoIntroResUrl = $('#videoIntroResUrl').val();
        var videoIntroRes;
        var videoIntroResName =  '';
        if (videoIntroResUrl) {
        	videoIntroRes = videoIntroResUrl;
        	var str = videoIntroResUrl.slice(videoIntroResUrl.lastIndexOf("\\") + 1);
        	videoIntroResName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	videoIntroRes = defaultIcon;
		}
        
        var singleCategoryPhotoUrl = $('#singleCategoryPhotoUrl').val();
        var singleCategoryPhoto;
        var singleCategoryName =  '';
        if (singleCategoryPhotoUrl) {
        	singleCategoryPhoto = singleCategoryPhotoUrl;
        	var str = singleCategoryPhotoUrl.slice(singleCategoryPhotoUrl.lastIndexOf("\\") + 1);
        	singleCategoryName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	singleCategoryPhoto = defaultIcon;
		}
        
        var multiCategoryPhotoUrl = $('#multiCategoryPhotoUrl').val();
        var multiCategoryPhoto;
        var multiCategoryName =  '';
        if (multiCategoryPhotoUrl) {
        	multiCategoryPhoto = multiCategoryPhotoUrl;
        	var str = multiCategoryPhotoUrl.slice(multiCategoryPhotoUrl.lastIndexOf("\\") + 1);
        	multiCategoryName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	multiCategoryPhoto = defaultIcon;
		}
        
        $('#singleCategoryPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delSingleCategory"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [singleCategoryPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: singleCategoryName}
            ],
            //defaultPreviewContent: imgTag,
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        $('#multiCategoryPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delmultiCategory"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [multiCategoryPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: multiCategoryName}
            ],
            //defaultPreviewContent: imgTag,
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        $('.file-input-overwrite').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delAvatar"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [avatar],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: avatarName}
            ],
            //defaultPreviewContent: imgTag,
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        //load videoIntroRes
        $('.file-input-overwrite-videoIntroRes').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delVideoIntroRes"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [videoIntroRes],  
            initialPreviewConfig: [
                {showDrag: false, 
                showRemove: false,
                caption: videoIntroResName,
                type: "video", 
                filetype: "video/mp4",
                url: videoIntroRes}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
      //load videoIntroMenu
        $('.file-input-overwrite-videoIntroMenu').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delVideoIntroMenu"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [videoIntroMenu],  
            initialPreviewConfig: [
                {showDrag: false, 
                showRemove: false,
                caption: videoIntroResName,
                type: "video", 
                filetype: "video/mp4",
                url: videoIntroMenu}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        $('.file-input-preview').fileinput({
	        showClose: false,
	        showCaption: false,
			showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delImage"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [], 
            initialPreviewConfig: [
                {showDrag: false, showRemove: false}
            ],
            initialPreviewAsData: false,
            overwriteInitial: false,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
		});
		$('.file-input-preview-digital-menus').fileinput({
			showClose: false,
			showCaption: false,
			showUpload: false,
			browseIcon: '<i class="icon-file-plus mr-2"></i>',
			uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
			layoutTemplates: {
				icon: '<i class="icon-file-check"></i>',
				modal: modalTemplate
			},
			initialPreview: [],
			initialPreviewConfig: [
				{showDrag: false, showRemove: false}
			],
			initialPreviewAsData: false,
			overwriteInitial: false,
			previewZoomButtonClasses: previewZoomButtonClasses,
			previewZoomButtonIcons: previewZoomButtonIcons,
			fileActionSettings: fileActionSettings
		}).on('filebatchselected', function(e, files) {
			imageFiles = [];
			formData = new FormData();
			$.each(files, function (key, value) {
				if(value != null){
					imageFiles.push(value);
					formData.append("digitalMenuPhotos", value, value.name);
				}
			});
		});

		$("#btnSubmitUpload").on("click", function() {
			var model_data = $("#coCategoryUpload").serializeArray();
			$.each(model_data,function(key, input){
				formData.append(input.name,input.value);
			});
			if(formData != null){
				$.ajax({
					method: 'POST',
					url: getContext() + "/coCategory/upload",
					dataType: 'text',
					cache: false,
					processData: false, // Important!
					contentType: false, // Important! I set dataType above as Json
					data: formData, // Important! The formData should be sent this way and not as a dict.
					success: function (data) {
						if(data){
							bootbox.alert({
								title: "Thông báo:",
								message: "Upload thành công.",
							});
							for (var i = 0; i < imageFiles.length; i++) {
								if (!fileNameImages.includes(imageFiles[i].name) && checkErrorFileName(imageFiles[i].name)) {
									var sizeTable = $('#sizeTable').val();
									var imageUrl = data + imageFiles[i].name;
									var newRowHtml = DigitalMenuSelector.createRow(parseInt(sizeTable), imageUrl, imageFiles[i].name);
									$('#tblDigitalMenu tbody').append(newRowHtml);
									fileNameImages.push(imageFiles[i].name);
								}
							}
							imageFiles = [];
						}else{
							bootbox.alert({
								title: "Thông báo:",
								message: "Upload không thành công.",
							});
						}
					},
					error: function (error) {
						console.log("error");
					}
				});
			} else {
				bootbox.alert({
					title: "Thông báo:",
					message: "Bạn chưa chọn ảnh để tải lên.",
				});
			}
			$(".file-input-preview-digital-menus").fileinput('reset');
			$('#modal_title_co_upload').modal('hide');
			formData = new FormData();
		});

		$('.file-input-preview-digital-menu').fileinput({
			showClose: false,
			showCaption: false,
			showUpload: false,
			browseIcon: '<i class="icon-file-plus mr-2"></i>',
			uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
			layoutTemplates: {
				icon: '<i class="icon-file-check"></i>',
				modal: modalTemplate
			},
			initialPreview: [],
			initialPreviewConfig: [
				{showDrag: false, showRemove: false}
			],
			initialPreviewAsData: false,
			overwriteInitial: false,
			previewZoomButtonClasses: previewZoomButtonClasses,
			previewZoomButtonIcons: previewZoomButtonIcons,
			fileActionSettings: fileActionSettings
		}).on('filebatchselected', function(e, files) {
			imageFiles = [];
			formData = new FormData();
			$.each(files, function (key, value) {
				if(value != null){
					imageFiles.push(value);
					formData.append("avatar", value, value.name);
				}
			});
		});

		$("#btnSubmitUpdatePhoto").on("click", function() {
			var model_data = $("#coCategoryUpdatePhoto").serializeArray();
			$.each(model_data,function(key, input){
				formData.append(input.name,input.value);
			});
			if(formData != null){
				$.ajax({
					method: 'POST',
					url: getContext() + "/coCategory/update-photo",
					dataType: 'text',
					cache: false,
					processData: false, // Important!
					contentType: false, // Important! I set dataType above as Json
					data: formData, // Important! The formData should be sent this way and not as a dict.
					success: function (data) {
						if(data){
							if (!fileNameImages.includes(imageFiles[0].name) && checkErrorFileName(imageFiles[i].name)) {
								var index = $('#indexImage').val();
								var imageUrl = data + imageFiles[0].name;
								$('#digital-menu-url-'+index).val(imageUrl);
								$('#digital-menu-name-'+index).val(imageFiles[0].name);
								$('#digital-menu-filename-'+index).text(imageFiles[0].name);
								var img = document.getElementById("digital-menu-img-" + index);
								img.src = imageUrl;
								fileNameImages.push(imageFiles[0].name);
								bootbox.alert({
									title: "Thông báo:",
									message: "Upload thành công.",
								});
							} else {
								bootbox.alert({
									title: "Thông báo:",
									message: "Upload không thành công. Hiện tại đã tồn tài một ảnh với tên này, vui lòng chọn tên khác.",
								});
							}
							imageFiles = [];
						} else {
							bootbox.alert({
								title: "Thông báo:",
								message: "Upload không thành công.",
							});
						}
					},
					error: function (error) {
						console.log("error");
					}
				});
			} else {
				bootbox.alert({
					title: "Thông báo:",
					message: "Bạn chưa chọn ảnh để tải lên.",
				});
			}
			$(".file-input-preview-digital-menu").fileinput('reset');
			$('#modal_co_upload').modal('hide');
			formData = new FormData();
		});
    };
    
    var deleteAvatar = function(){
    	$('.delAvatar').parent().on('click', function(e){
    		var coCategoryId = $("#coCategoryId").val();
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/soCategory/avatar',
				method : 'POST',
				data : {coCategoryId : coCategoryId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delVideoIntroMenu = function(){
    	$('.delVideoIntroMenu').parent().on('click', function(e){
    		var coCategoryId = $("#coCategoryId").val();
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/soCategory/videoIntroMenu',
				method : 'POST',
				data : {coCategoryId : coCategoryId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delVideoIntroRes = function(){
    	$('.delVideoIntroRes').parent().on('click', function(e){
    		var coCategoryId = $("#coCategoryId").val();
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/soCategory/videoIntroRes',
				method : 'POST',
				data : {coCategoryId : coCategoryId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    }
    
    var soCategoryChange = function(){
    	$('#selectItem').on('change', function(){
    		var coCategoryId = $("#coCategoryId").val();
			var soCategoryId = $("#selectItem").val();
			var resCode = $('#restaurantCode').val();
			$(".listbox-foodGroup-display-items").html("");
			$('.listbox-foodGroup-display-items').bootstrapDualListbox('refresh', true);
			if(soCategoryId != null && soCategoryId != ''){
				$.ajax({
					url: getContext() + '/cag/co/get-foodGroup-display',
					method:'GET',
					data : {coCategoryId : coCategoryId,
						soCategoryId : soCategoryId,
						resCode : resCode},
					success: function(data) {
						let str = "";
						data.forEach((item) => {
							var selected = '';
							if(item.selected){
								selected = 'selected';
							}
							str += "<option value='" + item.code + "'" + selected + ">" + item.code + " : " + item.nameVn + "</option>";
						});
						
						$(".listbox-foodGroup-display-items").html(str);
						$('.listbox-foodGroup-display-items').bootstrapDualListbox('refresh', true);
				        
				        
					},
					error: function (e) {
						console.log("error: ",e);
					}
				});
			}
    	});
    }
 // Dual Listbox
    var initDualListbox = function() {
        if (!$().bootstrapDualListbox) {
            console.warn('Warning - duallistbox.min.js is not loaded.');
            return;
        }
        
        $('.listbox-foodGroup-display-items').bootstrapDualListbox({
            selectorMinimalHeight: 300,
            moveOnSelect:false
        });
        
    };
   
    var delmultiCategory = function(){
    	$('.delmultiCategory').parent().on('click', function(e){
    		var coCategoryId = $("#coCategoryId").val();
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/soCategory/multiCategory',
				method : 'POST',
				data : {coCategoryId : coCategoryId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delSingleCategory = function(){
    	$('.delSingleCategory').parent().on('click', function(e){
    		var coCategoryId = $("#coCategoryId").val();
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/soCategory/singleCategory',
				method : 'POST',
				data : {coCategoryId : coCategoryId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };

	//OffCatalog
    var btnSubmit = function() {
		$('#btnSubmitForm').on('click', function (e) {
			fileNameImages = [];
			$('#tblDigitalMenu tbody').find(".showTr").each(function(index) {
				fileNameImages.push($(this).find('.digital-menu-name').val());
			});
			$('#fileNameImages').val(fileNameImages);
			var itemSelected = $("#selectItem option:selected").text();
			if(itemSelected) {
				let arr = itemSelected.split("-");
				$('#orderCategoryCode').val(arr[0].trim());
			}

			// thông tin hiển thị GROUP
			var listBox= document.getElementById('bootstrap-duallistbox-nonselected-list_listFoodGroupCodes');
			var listBox2 = document.getElementById('bootstrap-duallistbox-selected-list_listFoodGroupCodes');
			var lenghtListNonSelected = listBox.options.length;
			var lenghtListSelected = listBox2.options.length;
			
			let str='';
			if(lenghtListNonSelected != null || lenghtListNonSelected > 0){
				for(var i=0; i<lenghtListNonSelected; i++){
					var value= listBox.options[i].value;
					var text= listBox.options[i].text;
					
					str += "<option value='" + value + "'" + ">" + text + "</option>";
				}
			}
			if(lenghtListSelected !=null || lenghtListSelected > 0){
			for(var i=0; i<lenghtListSelected; i++){
				var value= listBox2.options[i].value;
				var text= listBox2.options[i].text;
				var selected= 'selected';
				str += "<option value='" + value + "'"+ " " + selected + ">" + text + "</option>";
			}
			}
			$(".listbox-foodGroup-display-items").html(str);
			$('.listbox-foodGroup-display-items').bootstrapDualListbox('refresh', true);

			var coCategoryId = $('#coCategoryId').val();
			const coStatus = $('#status').is(':checked');
			if(coCategoryId != null && coStatus !== true){
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
							// $('#soCategoryForm').attr('action', '/cag/co-menu/coCategory/off');
							$("#soCategoryForm").submit();
						}
					}
				});  
				
			} else {
				$("#soCategoryForm").submit();
				$('#pleaseWaitDialog').modal();
			}
            
        });
	};

	// Bootstrap switch
	var initBootstrapSwitch = function() {
		if (!$().bootstrapSwitch) {
			console.warn('Warning - switch.min.js is not loaded.');
			return;
		}

		// Initialize
		$('.form-check-input-switch').bootstrapSwitch();
	};

	var btnPreview = function(){
		$('#btnPreview').on('click', function(e){
			var urlInputs = document.querySelectorAll('.url-image');
			var imageUrls = [];
			// Danh sách các URL hình ảnh
			for (var i = 0; i < urlInputs.length; i++) {
				var urlInput = urlInputs[i];
				var imageUrl = urlInput.value;
				imageUrls.push(imageUrl);
			}
			// Xóa nội dung hiện tại của carousel
			$('#imageCarouselInner').empty();

			// Tạo các mục carousel từ danh sách hình ảnh
			for (var i = 0; i < imageUrls.length; i++) {
				var imageUrl = imageUrls[i];
				var activeClass = i === 0 ? 'active' : '';
				var carouselItem = '<div class="carousel-item ' + activeClass + '">' +
					'<img src="' + imageUrl + '" class="d-block w-100" alt="Ảnh">' +
					'</div>';
				$('#imageCarouselInner').append(carouselItem);
			}
			// Kích hoạt modal
			$('#imageListModal').modal('show');
		});
	};

    return {
        init: function() {
        	initForm();
			infoSoCategory();
			backPage();
			validateForm();
			_componentCoFoodItemForm();
			deleteAvatar();
			initDualListbox();
			soCategoryChange();
			delVideoIntroMenu();
			delVideoIntroRes();
			delSingleCategory();
			delmultiCategory();
			initBootstrapSwitch();
			btnSubmit();
			btnPreview();
        }
    }
}();

var soCategoryComponent = {
		
		initPhotos : function(){
			var coCategoryId = $("#coCategoryId").val();
			
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/soCategory/loadPhotos',
				method : 'GET',
				data : {id : coCategoryId},
				success : function(dataList){
					var s ='';
					dataList.forEach((item, index) => {
						s += '<div class="col-sm-6 col-xl-3">';
						s += '<div class="card">';
						s += '<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="'+ contextPath + item.url +'" title="'+ item.fileName +'" alt=""></div>';
						s += '<div class="card-body">';
						s += '<div class="d-flex align-items-start flex-nowrap">';
						s += '<div class="list-icons list-icons-extended ml-auto"><a class="list-icons-item" onclick="delPhoto('+ item.id +');"><i class="icon-bin top-0"></i></a></div>';				
						s += '</div>';			
						s += '</div>';
						s += '</div>';
						s += '</div>';
						
					});
					
					console.log(s);
					$("#existingPhotos").html(s);
				},
				error : function(e){
					console.log(e);
					console.log("lỗi kĩ thuật");
				}
			})
		},
		
		delPhoto : function(aId) {
            var contextPath = getContext();
            $.ajax({
                url : contextPath + '/soCategory/del/photo',
                method : 'POST',
                data : {aId : aId},
                success : function(res){
                	soCategoryComponent.initPhotos();
                },
                error : function(e){
                    console.log(e);
                }
            })
        },
        
        loadInfoSoCategory : function(){
        	var contextPath = getContext();
        	var itemSelected = $("#selectItem option:selected").val();
    		if(itemSelected != null && itemSelected != ''){
    			$.ajax({
    				url: contextPath + '/co/get-info-soCategory',
    				contextType: 'application/json',
    				method: 'GET',
    				data: {
    					id: itemSelected
    				},
    				success: function(data) {
    					if (data.type == 1) {
    						$('#menu-type-text').val('ALC');	
    					} else if(data.type == 2){
    						$('#menu-type-text').val('BUF');
    					} else {
							$('#menu-type-text').val('SET');
						}
    					$('#menu-type-value').val(data.type);
    					$('#adultBufferTicket').val(data.adultBufferTicket);
    					$('#kidBufferTicket').val(data.kidBufferTicket);
    				},
    				error: function(err) {
    					console.log(err);
    				}
    			});
    		}else{
    			resetText();
    		}
        },
        
        initPhotoRes : function(){
			var coCategoryId = $("#coCategoryId").val();
			
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/soCategory/loadPhotoRes',
				method : 'GET',
				data : {id : coCategoryId},
				success : function(dataList){
					var s ='';
					dataList.forEach((item, index) => {
						s += '<div class="col-sm-6 col-xl-3">';
						s += '<div class="card">';
						s += '<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="'+ contextPath + item.url +'" title="'+ item.fileName +'" alt=""></div>';
						s += '<div class="card-body">';
						s += '<div class="d-flex align-items-start flex-nowrap">';
						s += '<div class="list-icons list-icons-extended ml-auto"><a class="list-icons-item" onclick="delPhotoRes('+ item.id +');"><i class="icon-bin top-0"></i></a></div>';				
						s += '</div>';			
						s += '</div>';
						s += '</div>';
						s += '</div>';
						
					});
					
					console.log(s);
					$("#existingPhotoRes").html(s);
				},
				error : function(e){
					console.log(e);
					console.log("lỗi kĩ thuật");
				}
			})
		},
		
		delPhotoRes : function(aId) {
            var contextPath = getContext();
            $.ajax({
                url : contextPath + '/soCategory/del/photoIntroRes',
                method : 'POST',
                data : {aId : aId},
                success : function(res){
                	soCategoryComponent.initPhotoRes();
                },
                error : function(e){
                    console.log(e);
                }
            })
        },
        
        initPhotoMenus : function(){
			var coCategoryId = $("#coCategoryId").val();
			
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/soCategory/loadPhotoMenus',
				method : 'GET',
				data : {id : coCategoryId},
				success : function(dataList){
					var s ='';
					dataList.forEach((item, index) => {
						s += '<div class="col-sm-6 col-xl-3">';
						s += '<div class="card">';
						s += '<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="'+ contextPath + item.url +'" title="'+ item.fileName +'" alt=""></div>';
						s += '<div class="card-body">';
						s += '<div class="d-flex align-items-start flex-nowrap">';
						s += '<div class="list-icons list-icons-extended ml-auto"><a class="list-icons-item" onclick="delphotoMenu('+ item.id +');"><i class="icon-bin top-0"></i></a></div>';				
						s += '</div>';			
						s += '</div>';
						s += '</div>';
						s += '</div>';
						
					});
					
					console.log(s);
					$("#existingPhotoMenus").html(s);
				},
				error : function(e){
					console.log(e);
					console.log("lỗi kĩ thuật");
				}
			})
		},
		
		delphotoMenu : function(aId) {
            var contextPath = getContext();
            $.ajax({
                url : contextPath + '/soCategory/del/photoIntroMenu',
                method : 'POST',
                data : {aId : aId},
                success : function(res){
                	soCategoryComponent.initPhotoMenus();
                },
                error : function(e){
                    console.log(e);
                }
            })
        },
}

var CoFoodGroupCode = {
		loadCoFoodGroup: () => {
			var coCategoryId = $("#coCategoryId").val();
			var soCategoryId = $("#selectItem").val();
			var resCode = $('#restaurantCode').val();
			$(".listbox-foodGroup-display-items").html("");
			$('.listbox-foodGroup-display-items').bootstrapDualListbox('refresh', true);
			if(soCategoryId != null && soCategoryId != ''){
				$.ajax({
					url: getContext() + '/cag/co/get-foodGroup-display',
					method:'GET',
					data : {coCategoryId : coCategoryId,
						soCategoryId : soCategoryId,
						resCode : resCode},
					success: function(data) {
						let str = "";
						data.forEach((item) => {
							var selected = '';
							if(item.selected){
								selected = 'selected';
							}
							str += "<option value='" + item.code + "'" + selected + ">" + item.code + " : " + item.nameVn + "</option>";
						});
						
						$(".listbox-foodGroup-display-items").html(str);
						$('.listbox-foodGroup-display-items').bootstrapDualListbox('refresh', true);
				        
				        
					},
					error: function (e) {
						console.log("error: ",e);
					}
				});
			}
		},

		getFileNameImages : function() {
			var names = document.querySelectorAll('.digital-menu-name');
			for (var i = 0; i < names.length; i++) {
				fileNameImages.push(names[i].value);
			}
		},
		
	}

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
    soCategoryForm.init();
    CoFoodGroupCode.loadCoFoodGroup();
	CoFoodGroupCode.getFileNameImages();
});