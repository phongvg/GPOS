function checkErr(fileName){
	var errChart = [" ","à","á","ạ","ả","ã","â","ầ","ấ","ậ","ẩ","ẫ","ă","ằ","ắ","ặ","ẳ","ẵ","è","é","ẹ","ẻ","ẽ","ê","ề","ế","ệ","ể","ễ","ì","í","ị","ỉ","ĩ","ò","ó","ọ","ỏ","õ","ô","ồ","ố","ộ","ổ","ỗ","ơ","ờ","ớ","ợ","ở","ỡ","ù","ú","ụ","ủ","ũ","ư","ừ","ứ","ự","ử","ữ","ỳ","ý","ỵ","ỷ","ỹ","đ"]
	for(var i = 0 ; i < fileName.length ; i++){
		if(errChart.includes(fileName[i])){
			return false;
		}
	}
	return true;
}
var coFoodItemForm = function() {
	var _componentCheckCode = function() {
		$('#codeFeature').on('keyup', function(e){
			var code = $('#codeFeature').val();
			var contextPath = getContext();
			var messageCheckCode = document.getElementById('messageCheckCode');
			if(code != ''){
				$.ajax({
					url: contextPath + '/feature/checkCode',
					contextType: 'application/json',
					method: 'POST',
					data: {
						code: code
					},
					success: function(data) {
						messageCheckCode.style.display = 'block';
						if(data) {
							messageCheckCode.style.color = 'red';
							$('#btnSubmit').attr("disabled","disabled");
							$('#messageCheckCode').html('\u004d\u00e3 \u0063\u006f\u0064\u0065 \u0111\u00e3 \u0074\u1ed3\u006e \u0074\u1ea1\u0069')
						} else {
							console.log(document.getElementById('codeFeature').value.length)
							if(document.getElementById('codeFeature').value.length != 0){
								messageCheckCode.style.color = 'green';
								$('#btnSubmit').removeAttr('disabled');
								$('#messageCheckCode').html('\u004d\u00e3 \u0063\u006f\u0064\u0065 \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
							} else {
								messageCheckCode.style.color = 'red';
								$('#btnSubmit').attr("disabled","disabled");
								$('#messageCheckCode').html('\u0059\u00ea\u0075 \u0063\u1ea7\u0075 \u006e\u0068\u1ead\u0070 \u006d\u00e3 \u0063\u006f\u0064\u0065')
							}
							
						}
					},
					error: function(err) {
						console.log(err)
					}
				});
			} else {
				messageCheckCode.style.color = 'red';
				$('#messageCheckCode').html('\u0059\u00ea\u0075 \u0063\u1ea7\u0075 \u006e\u0068\u1ead\u0070 \u006d\u00e3 \u0063\u006f\u0064\u0065')
			}
		});
	};
	//
	// Validate form
	var validateForm = function(){
		$('#coFoodItemForm').validate({
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
				"kalo": {
					number : true,
					required: true,
					maxlength: 5,
					minlength: 1
				},
				"maxPerTransaction": {
					number : true,
					required: true,
					maxlength: 5,
					minlength: 1
				},
				"maxForKitchen": {
					number : true,
					required: true,
					maxlength: 5,
					minlength: 1
				},
				"foodItem.id":{
					required: true
				},
				"nameEn": {
					maxlength: 512,
				},
				"descVn": {
					maxlength: 512,
				},
				"descEn": {
					maxlength: 512,
				},
				"price": {
					number : true,
					required: true,
					maxlength: 10,
				},
				"positionNumber": {
					number : true,
					required: true,
					maxlength: 5,
				},
				"numberOfPeopleEat": {
					number : true,
					required: true,
					maxlength: 5,
				},
				"bufferLabel": {
					maxlength: 256,
				},
			},
			messages: {
				"kalo": {
					number : "Kalo phải là số",
					required: "Bắt buộc nhập kalo",
					maxlength: "Hãy nhập tối đa 5 ký tự"
				},
				"maxPerTransaction": {
					number : "Số lượng tối đa trên 1 lượt phải là số",
					required: "Bắt buộc nhập số lượng tối đa trên 1 lượt",
					maxlength: "Hãy nhập tối đa 5 ký tự"
				},
				"maxForKitchen": {
					number : "Số lượng tối đa vào bếp phải là số",
					required: "Bắt buộc nhập số lượng tối đa vào bếp",
					maxlength: "Hãy nhập tối đa 5 ký tự"
				},
				"foodItem.id":{
					required:"Bắt buộc chọn món ăn",
				},
				"price": {
					number : "Giá món ăn phải là số",
					required: "Bắt buộc nhập giá",
					maxlength: "Hãy nhập tối đa 5 ký tự",
				},
				"descVn": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"descEn": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"nameEn": {
					maxlength: "Hãy nhập tối đa 512 ký tự",
				},
				"positionNumber": {
					number : "Vị trí của món phải là số",
					required: "Bắt buộc nhập vị trí của món",
					maxlength: "Hãy nhập tối đa 5 ký tự"
				},
				"numberOfPeopleEat": {
					number : "Thông tin số người ăn của món phải lại số",
					required: "Bắt buộc nhập thông tin số người ăn của món phải lại số",
					maxlength: "Hãy nhập tối đa 5 ký tự"
				},
				"bufferLabel": {
					maxlength: "Hãy nhập tối đa 256 ký tự",
				},
			}
		});
	}

    // Dual Listbox
    var initDualListbox = function() {
        if (!$().bootstrapDualListbox) {
            console.warn('Warning - duallistbox.min.js is not loaded.');
            return;
        }
        
        $('.listbox-related-items').bootstrapDualListbox({
            selectorMinimalHeight: 300,
            moveOnSelect:false
        });
        
        $('.listbox-modifier-items').bootstrapDualListbox({
            selectorMinimalHeight: 300,
            moveOnSelect:false
        });
        
        $('.listbox-feature-items').bootstrapDualListbox({
            selectorMinimalHeight: 300,
            moveOnSelect:false
        });
        
    };
    
	// Bootstrap file upload
    var _componentInputPreviewForm = function() {
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

        var contextPath = getContext();
        var defaultIcon = $('#defaultIcon').val();
		var avatarUrl = $('#avatarUrl').val();
		var halfPhotoUrl = $('#halfPhotoUrl').val();
		var toppingPhotoUrl = $('#toppingPhotoUrl').val();
		var groupPhotoUrl = $('#groupPhotoUrl').val();
		var groupHiddenPhotoUrl = $('#groupHiddenPhotoUrl').val();
		var horizontalPhotoUrl = $('#horizontalPhotoUrl').val();
		var verticalPhotoUrl = $('#verticalPhotoUrl').val();
		var quarterPhotoUrl = $('#quarterPhotoUrl').val();
		var drinkPhotoUrl = $('#drinkPhotoUrl').val();
		var qrOrderPhotoUrl = $('#qrOrderPhotoUrl').val();
		
        var avatar;
        var halfPhoto;
        var toppingPhoto;
        var groupPhoto;
        var groupHiddenPhoto;
        var horizontalPhoto;
        var verticalPhoto;
        var quarterPhoto;
        var drinkPhoto;
		var qrOrderPhoto;
        
        
        var avatarName =  '';
        var halfPhotoName =  '';
        var toppingPhotoName =  '';
        var groupPhotoName =  '';
        var groupHiddenPhotoName =  '';
        var horizontalPhotoName =  '';
        var verticalPhotoName =  '';
        var quarterPhotoName =  '';
        var drinkPhotoName =  '';
		var qrOrderPhotoName =  '';
        
        if (avatarUrl) {
        	avatar = avatarUrl;
        	var str = avatarUrl.slice(avatarUrl.lastIndexOf("\\") + 1);
        	avatarName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	avatar = defaultIcon;
		}
        
        if (quarterPhotoUrl) {
        	quarterPhoto = quarterPhotoUrl;
        	var str = quarterPhotoUrl.slice(quarterPhotoUrl.lastIndexOf("\\") + 1);
        	quarterPhotoName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	quarterPhoto = defaultIcon;
		}
        
        if (halfPhotoUrl) {
        	halfPhoto = halfPhotoUrl;
        	var half = halfPhotoUrl.slice(halfPhotoUrl.lastIndexOf("\\") + 1);
        	halfPhotoName = half.slice(half.lastIndexOf("/") + 1);
        } else {
        	halfPhoto = defaultIcon;
		}
        
        if (toppingPhotoUrl) {
        	toppingPhoto = toppingPhotoUrl;
        	var topping = toppingPhotoUrl.slice(toppingPhotoUrl.lastIndexOf("\\") + 1);
        	toppingPhotoName = topping.slice(topping.lastIndexOf("/") + 1);
        } else {
        	toppingPhoto = defaultIcon;
		}
        
        if (groupPhotoUrl) {
        	groupPhoto = groupPhotoUrl;
        	var str = groupPhotoUrl.slice(groupPhotoUrl.lastIndexOf("\\") + 1);
        	groupPhotoName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	groupPhoto = defaultIcon;
		}
        
        if (groupHiddenPhotoUrl) {
        	groupHiddenPhoto = groupHiddenPhotoUrl;
        	var groupHidden = groupHiddenPhotoUrl.slice(groupHiddenPhotoUrl.lastIndexOf("\\") + 1);
        	groupHiddenPhotoName = groupHidden.slice(groupHidden.lastIndexOf("/") + 1);
        } else {
        	groupHiddenPhoto = defaultIcon;
		}
        
        if (horizontalPhotoUrl) {
        	horizontalPhoto = horizontalPhotoUrl;
        	var horizontal = horizontalPhotoUrl.slice(horizontalPhotoUrl.lastIndexOf("\\") + 1);
        	horizontalPhotoName = horizontal.slice(horizontal.lastIndexOf("/") + 1);
        } else {
        	horizontalPhoto = defaultIcon;
		}

        if (verticalPhotoUrl) {
        	verticalPhoto = verticalPhotoUrl;
        	var vertical = verticalPhotoUrl.slice(verticalPhotoUrl.lastIndexOf("\\") + 1);
        	verticalPhotoName = vertical.slice(vertical.lastIndexOf("/") + 1);
        } else {
        	verticalPhoto = defaultIcon;
		}
        
        if (drinkPhotoUrl) {
        	drinkPhoto = drinkPhotoUrl;
        	var drink = drinkPhotoUrl.slice(drinkPhotoUrl.lastIndexOf("\\") + 1);
        	drinkPhotoName = drink.slice(drink.lastIndexOf("/") + 1);
        } else {
        	drinkPhoto = defaultIcon;
		}

		if (qrOrderPhotoUrl) {
        	qrOrderPhoto = qrOrderPhotoUrl;
        	var qrOrder = qrOrderPhotoUrl.slice(qrOrderPhotoUrl.lastIndexOf("\\") + 1);
        	qrOrderPhotoName = qrOrder.slice(qrOrder.lastIndexOf("/") + 1);
        } else {
        	qrOrderPhoto = defaultIcon;
		}

		//load QR-Order
        $('#qrOrderPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delQrOrderPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [qrOrderPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: qrOrderPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        //load quarterPhoto
        $('#drinkPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delDrink"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [drinkPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: drinkPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        $('#quarterPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delQuarter"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [quarterPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: quarterPhotoName}
            ],
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
                icon: '<i class="icon-file-check" id="icon-file001"></i>',
                modal: modalTemplate
            },
            initialPreview: [avatar],  
            initialPreviewConfig: [
            	{showDrag: false, showRemove: false, caption: avatarName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        //load halfPhoto
        $('#halfPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delHalfPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [halfPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: halfPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
      //load toppingPhoto
        $('#toppingPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delToppingPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [toppingPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: toppingPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
      //load groupPhoto
        $('#groupPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delGroupPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [groupPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: groupPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
      //load groupHiddenPhoto
        $('#groupHiddenPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delGroupHiddenPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [groupHiddenPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: groupHiddenPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
      //load horizontalPhoto
        $('#horizontalPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delHorizontalPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [horizontalPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: horizontalPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
      //load verticalPhoto
        $('#verticalPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delVerticalPhoto"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [verticalPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false,caption: verticalPhotoName}
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
	        //browseLabel: '',
		    //removeLabel: '',
			//removeClass: "btn",
			//browseClass: "btn",
						            
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
    };
    
    
    var deleteAvatar = function(){
    	$('.delAvatar').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/avatar',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
//					loadInputPreviewForm();
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
	};
    
    var deleteQuarter = function(){
    	$('.delQuarter').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/quarter',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };

	var deleteQrOrder = function(){
    	$('.delQrOrderPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/qrOrderPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delHalfPhoto = function(){
    	$('.delHalfPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/halfPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delToppingPhoto = function(){
    	$('.delToppingPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/toppingPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delGroupPhoto = function(){
    	$('.delGroupPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/groupPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delGroupHiddenPhoto = function(){
    	$('.delGroupHiddenPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/groupHiddenPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delHorizontalPhoto = function(){
    	$('.delHorizontalPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/horizontalPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delVerticalPhoto = function(){
    	$('.delVerticalPhoto').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/verticalPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var delDrinkPhoto = function(){
    	$('.delDrink').parent().on('click', function(e){
    		var coFoodItemId = $("#coFoodItemId").val();
    		var contextPath = getContext();
			$.ajax({
				url : contextPath + '/del/drinkPhoto',
				method : 'POST',
				data : {coFoodItemId : coFoodItemId},
				success : function(data){
					
				},
				error : function(e){
					console.log(e);
				}
			})
    	});
    };
    
    var backPage = function(){
    	$('#back').on('click',function(){
    		history.back();
    	})
    };
    
    var modalSubmit = function(){
        $("#btnSubmit").on('click',function(){
        	var html = '';
        	$("#featureForm").validate({
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
        			"code": {
        				required: true,
        				maxlength: 20,
        				minlength: 1
        			},
        			"nameVn": {
        				required: true,
        				maxlength: 50,
        				minlength: 1
        			},
        			"nameEn": {
        				required: true,
        				maxlength: 50,
        				minlength: 1
        			}
        		},
        		messages: {
        			"code": {
        				required: "Bắt buộc nhập code",
        				maxlength: "Hãy nhập tối đa 20 ký tự"
        			},
        			"nameVn": {
        				required: "Bắt buộc nhập tên tiếng Việt",
        				maxlength: "Hãy nhập tối đa 50 ký tự"
        			},
        			"nameEn": {
        				required: "Bắt buộc nhập tên tiếng Anh",
        				maxlength: "Hãy nhập tối đa 50 ký tự"
        			}
        		},
   
                submitHandler: function (form){
                    var code = $('#codeFeature').val();
                    var nameVn = $('#nameVn').val();
                    var nameEn = $('#nameEN').val();
                    var status = $('#status').val();
                    var contextPath = getContext();
                	var data = {
                            "code" : code,
                            "nameVn" : nameVn,
                            "nameEn" : nameEn,
                            "status" : status,
                    };
            		  $.ajax({
            	          url: contextPath + '/api-feature/save',
            	          method:'POST',
            	          data : data,
            	          success: function(data) {   
            	        	if(data.checkCode){
                                console.log("data.checkCode" + data.checkCode);
	        	        		$("#selectedFeatures").append( '<option value="'+data.id+'">'+data.code+'</option>' );
	        					$('.listbox-feature-items').bootstrapDualListbox('refresh', true);
		        	            $("#modal_form_vertical").modal("hide");
		        	            resetModal();
            	        	}else{
            	        		bootbox.alert({
            					    title: "Thông báo:",
            					    message: "Mã Feature này đã được sử dụng.Vui lòng nhập mã Feature khác.",
            					});  
            	        		$("#modal_form_vertical").modal("hide");
		        	            resetModal();
            	        	}
            	          	
            	          },
            	          error: function (e) {
            	              console.log("error: ",e);
            	          },
            	      })
                    }
            	})
            });
    }
    
    var initSelector = function() {
    	ModifierSelector.init();
   	 	FoodItemRelatedSelector.init();
   	 	FoodItemInfo.init();
   	 	FoodItemSizeSelector.init();
   	 	FoodItemToppingSelector.init();
   	 	FoodItemSelector.init();
    }
    
    var deleteExtra = function(){
		$('#deleteExtra').on('click', function(){
			$(".select2-items-extra").html("");
		})
	}
    
    var submitForm = function(){
    	$('#btnSubmitForm').on('click', function(){
    		var infoFoodItem = FoodItemInfo.getInfoFoodItems();
    		var selectedRelatedFICodes = FoodItemRelatedSelector.getSelectedFoodItemRelatedCode();
    		var selectedRelatedFINames = FoodItemRelatedSelector.getSelectedFoodItemRelatedName();
    		$('#infoFoodItem').val(infoFoodItem);
    		$('#relatedFCodes').val(selectedRelatedFICodes);
    		$('#relatedFNames').val(selectedRelatedFINames);
			$('#coFoodItemForm').submit();
		})
    }
    
    var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }
    	// select2
   	 	$('.select2').select2();
	}
    
    var delKaloGroup = function(){
		$('#delKaloGroup').click(function() {
		    $("#kaloGroupId").val(null).trigger("change"); 
		});
	}
    
    return {
        init: function() {
        	initForm();
            modalSubmit();
            initDualListbox();
            _componentCheckCode();
            _componentInputPreviewForm();
            backPage();
            validateForm();
            initSelector();
            submitForm();
            deleteAvatar();
            delVerticalPhoto();
            delHorizontalPhoto();
            delGroupHiddenPhoto();
            delGroupPhoto();
            delToppingPhoto();
            delHalfPhoto();
            deleteQuarter();
            deleteExtra();
            delKaloGroup();
            delDrinkPhoto();
			deleteQrOrder();
        }
    }
}();

var coFoodItemComponent = {
		initPhotos : function(){
			var coFoodItemId = $("#coFoodItemId").val();
			var contextPath = getContext();
			$.ajax({
				url : contextPath + '/coFoodItem/loadPhotos',
				method : 'GET',
				data : {id : coFoodItemId},
				success : function(dataList){
					var s ='';
					dataList.forEach((item, index) => {
						s += '<div class="col-sm-6 col-xl-3">';
						s += '<div class="card">';
						s += '<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="'+ contextPath + item.url +'" alt=""></div>';
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
				url : contextPath + '/del/photo',
				method : 'POST',
				data : {aId : aId},
				success : function(res){
					coFoodItemComponent.initPhotos();
				},
				error : function(e){
					console.log(e);
				}
			})
		}
}


var Modifier = {
		loadExistingModifiers: () => {
			var coId = $("#coFoodItemId").val();
			ModifierSelector.loadExistingModifiers(coId);
		},
		loadExistingFoodItemRelateds: () => {
			var coId = $("#coFoodItemId").val();
			FoodItemRelatedSelector.loadExistingFoodItemRelateds(coId);
		},
		loadExistingFoodItemSizes: () => {
			var coId = $("#coFoodItemId").val();
			FoodItemSizeSelector.loadExistingFoodItemSizes(coId);
		},
		loadExistingInfoFoodItems: () => {
			var coId = $("#coFoodItemId").val();
			FoodItemInfo.loadExistingInfoFoodItems(coId);
		},
		loadExistingToppings: () => {
			var coFoodItemId = $("#coFoodItemId").val();
			FoodItemToppingSelector.loadExistingFoodItemToppings(coFoodItemId);
		},
		
		loadExtraFoodItem : () =>{
			var coFId = $("#coFoodItemId").val();
			if(coFId != null && coFId != ''){
				$.ajax({
					url: getContext() + '/api/load-extraFoodItem',
					data : {coFId : coFId
					},
					method:'GET',
					success: function(data) {
						var extra = "";
						extra = "<option></option>";
						if(data.extraFoodItem != null && data.extraFoodItem != ''){
							extra = "<option value=" + data.extraFoodItem + ">" + data.extraFoodItem + "</option>";
						}
						$(".select2-items-extra").html(extra);
					},
					error: function (e) {
						console.log("error: ",e);
					}
				});
			}
			
		},
		
		loadFoodItem : () =>{
			var coFId = $("#coFoodItemId").val();
			if(coFId != null && coFId != ''){
				$.ajax({
					url: getContext() + '/api/load-foodItem-in-cfi',
					data : {coFId : coFId
					},
					method:'GET',
					success: function(data) {
						var sapCode = '';
                    	if(data.sapCode){
                    		sapCode = data.sapCode + "-"
                    	}
						var fi = "";
						fi = "<option></option>";
						if(data.id != null && data.id != ''){
							fi = "<option value=" + data.id + ">" + sapCode + data.code + ": " + data.name + "</option>";
						}
						$(".select2-co-food-item-cata").html(fi);
					},
					error: function (e) {
						console.log("error: ",e);
					}
				});
			}
			
		},
		
		loadFeatures: () => {
			var coFoodItemId = $("#coFoodItemId").val();
			$(".listbox-feature-items").html("");
			$('.listbox-feature-items').bootstrapDualListbox('refresh', true);
			$.ajax({
				url: getContext() + '/cag/co/get-features',
				method:'GET',
				data : {id : coFoodItemId},
				success: function(data) {
					let str = "";
					data.forEach((item) => {
						var selected = '';
						if(item.selected){
							selected = 'selected';
						}
						str += "<option value='" + item.id + "'" + selected + ">" + item.code + "</option>";
					});
					
					$(".listbox-feature-items").html(str);
					$('.listbox-feature-items').bootstrapDualListbox('refresh', true);
			        
			        
				},
				error: function (e) {
					console.log("error: ",e);
				}
			});
		},
		
	}


// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	coFoodItemForm.init();
	Modifier.loadFeatures();
	Modifier.loadExistingModifiers();
	Modifier.loadExistingFoodItemRelateds();
	Modifier.loadExistingInfoFoodItems();
	Modifier.loadExistingFoodItemSizes();
	Modifier.loadExistingToppings();
	Modifier.loadExtraFoodItem();
	Modifier.loadFoodItem();
});