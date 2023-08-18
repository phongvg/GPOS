function checkErr(fileName){
	var errChart = [" ","à","á","ạ","ả","ã","â","ầ","ấ","ậ","ẩ","ẫ","ă","ằ","ắ","ặ","ẳ","ẵ","è","é","ẹ","ẻ","ẽ","ê","ề","ế","ệ","ể","ễ","ì","í","ị","ỉ","ĩ","ò","ó","ọ","ỏ","õ","ô","ồ","ố","ộ","ổ","ỗ","ơ","ờ","ớ","ợ","ở","ỡ","ù","ú","ụ","ủ","ũ","ư","ừ","ứ","ự","ử","ữ","ỳ","ý","ỵ","ỷ","ỹ","đ"]
	for(var i = 0 ; i < fileName.length ; i++){
		if(errChart.includes(fileName[i])){
			return false;
		}
	}
	return true;
}


var CoComponent = function() {
	
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();

	}
	
    // Uniform
    var initUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Default initialization
        $('.form-check-input-styled').uniform();


        //
        // Contextual colors
        //

        // Primary
        $('.form-check-input-styled-primary').uniform({
            wrapperClass: 'border-primary-600 text-primary-800'
        });

        // Danger
        $('.form-check-input-styled-danger').uniform({
            wrapperClass: 'border-danger-600 text-danger-800'
        });

        // Success
        $('.form-check-input-styled-success').uniform({
            wrapperClass: 'border-success-600 text-success-800'
        });

        // Warning
        $('.form-check-input-styled-warning').uniform({
            wrapperClass: 'border-warning-600 text-warning-800'
        });

        // Info
        $('.form-check-input-styled-info').uniform({
            wrapperClass: 'border-info-600 text-info-800'
        });

        // Custom color
        $('.form-check-input-styled-custom').uniform({
            wrapperClass: 'border-indigo-600 text-indigo-800'
        });
        
    };

    // Switchery
    var initSwitchery = function() {
        if (typeof Switchery == 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize multiple switches
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-check-input-switchery'));
        elems.forEach(function(html) {
          var switchery = new Switchery(html);
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
    
    
    //// Bootstrap file upload
    var _componentCoListForm = function() {
        if (!$().fileinput) {
            console.warn('Warning - fileinput.min.js is not loaded.');
            return;
        }
        if (typeof CKEDITOR == 'undefined') {
            console.warn('Warning - ckeditor.js is not loaded.');
            return;
        }
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }
        //
        // Define variables
        //

    	/*// select2
		$('.select-category').select2();
		$('.select-product-type').select2();*/

        // Primary
        $('.form-check-input-styled-primary').uniform({
            wrapperClass: 'border-primary-600 text-primary-800'
        });
        $('.form-check-input-styled-warning').uniform({
            wrapperClass: 'border-warning-600 text-warning-800'
        });
        
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
        
        $(".file-input-preview").fileinput({
            theme: 'fa',
            uploadUrl: "#",
            uploadAsync: false,
            required: true,
            /*minFileCount: 2,
            maxFileCount: 10,*/
            overwriteInitial: false,
            initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
            initialPreviewFileType: 'image', // image is the default and can be overridden in config below
            showUpload: false,
            showRemove: false,
            fileActionSettings: fileActionSettings
        }).on('filebatchselected', function(e, files) {
            formData = new FormData();
            $.each(files, function (key, value) {
                if(value != null){
                    formData.append("photos", value, value.name);
                }
            });
        });
        
        
        $("#btnSubmitUpload").on("click", function() {
        	var model_data = $("#coFormUpload").serializeArray();
        	$.each(model_data,function(key, input){
        		formData.append(input.name,input.value);
        	});
        	if(formData != null){
        		$.ajax({
                    method: 'POST',
                    url: getContext() + "/co/upload",
                    dataType: 'text',
                    cache: false,
                    processData: false, // Important!
                    contentType: false, // Important! I set dataType above as Json
                    data: formData, // Important! The formData should be sent this way and not as a dict.
                    success: function (data) {
                    	console.log(data);
                    	if(data == 'true'){
                    		bootbox.alert({
            				    title: "Thông báo:",
            				    message: "Upload thành công.",
            				});
                    	}else{
                    		bootbox.alert({
            				    title: "Thông báo:",
            				    message: "Upload không thành công.",
            				});
                    	}
                    	$(".file-input-preview").fileinput('reset');
                    	$('#modal_title_co_upload').modal('hide');
                        formData = new FormData();
                    	
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
        	
        });        
    };
    
    
    // check file and size image upload
    var checkFileUpload = function(){
    	$('#photos').on('change',function(){
    		const video = "/video/";
    		var moduleType = $('#moduleType').val();
    		var fileInput =  document.getElementById('photos').files;
    		var name = [];
    		let s = '';
    		if(moduleType == video){
        		var allowedExtensions = ["ogm","wmv","mpg","webm","ogv","mov","asx","mpeg","mp4","m4v","avi","mov","flv"];
        		if(fileInput.length > 5){
        			bootbox.alert({
    				    title: "Thông báo:",
    				    message: "Không thể tải video lên do số lượng file tải lên quá lớn. Chỉ có thể upload tối đa 5 file.",
    				});
        		}else{
        			for(var i = 0 ; i < fileInput.length ; i++){
            			var filePath = fileInput[i].name;
            			var fileF = filePath.slice(filePath.lastIndexOf(".") + 1).toLowerCase();
            			jQuery.inArray(fileF,allowedExtensions);
            			var fileSize = fileInput[i].size/1024/1024;
            			if(fileSize > 100 || (jQuery.inArray(fileF,allowedExtensions)) === -1 || !checkErr(filePath)){
            				name.push(filePath);
            			}
            		}
            		if(name.length > 0){
            			$.each(name, function(idx, item) {
                            s = s + item + " , ";
                        });
                        s = s.substring(0, s.length - 2);
                        bootbox.alert({
                        	title: "Thông báo:",
        				    message: "Không thể tải lên một số tệp video do các tệp không đúng định dạng hoặc có kích thước quá lớn hoặc tên file không đúng định dạng. Các tệp đang bị lỗi: " + s + ".",
        				}); 
                        
                        
            		}else {
            			$('#checkPhotos').val(null);
            		}
        		}
    		}else{
        		var allowedExtensionImgs = ["jpg","jpeg","png","gif","tiff","pjp","pjpeg","jfif","tif","svg","bmp","svgz","webp","ico","xbm","dib"];
        		if(fileInput.length > 10){
        			bootbox.alert({
    				    title: "Thông báo:",
    				    message: "Không thể tải ảnh lên do số lượng file tải lên quá lớn. Chỉ có thể upload tối đa 5 file ảnh.",
    				});
        		}else{
        			for(var j = 0 ; j < fileInput.length ; j++){
            			var fPath = fileInput[j].name;
            			var fileName = fPath.slice(fPath.lastIndexOf(".") + 1).toLowerCase();
            			jQuery.inArray(fileF,allowedExtensionImgs);
            			var fSize = fileInput[j].size/1024/1024;
            			if(fSize > 3 || (jQuery.inArray(fileName,allowedExtensionImgs)) === -1 || !checkErr(fPath)){
            				name.push(fPath);
            			}
            		}
            		if(name.length > 0){
            			$.each(name, function(idx, item) {
                            s = s + item + " , ";
                        });
                        s = s.substring(0, s.length - 2);
                        bootbox.alert({
                        	title: "Thông báo:",
        				    message: "Không thể tải lên một số tệp ảnh do các ảnh không đúng định dạng hoặc có kích thước quá lớn hoặc tên file không đúng định dạng. Các ảnh đang bị lỗi: " + s + ".",
        				}); 
                        
                        
            		}else {
            			$('#checkPhotos').val(null);
            		}
        		}
    		}
    		
    	});
    };
    
    var btnExportExcel = function(){
    	$('#downloadFileCheck').on('click',function(){
    		$('#modal_export').modal('hide');  
    	});
    }
    
    var btnImportUpdate = function(){
    	$('#btnImportUpdate').on('click',function(){
    		$('#pleaseWaitDialog').modal();
    		$('#override').val(false);
			$("#coFormImport").submit();
    	});
    }
    
     var btnImport = function(){
    	$('#btnImport').on('click', function() {
    		$('#pleaseWaitDialog').modal();
    		$('#override').val(true);
			$("#coFormImport").submit();
    	});
    }
    
    //
    // Return objects assigned to module
    //
    return {
        init: function() {
        	initForm();
            initUniform();
            initSwitchery();
            initBootstrapSwitch();
            checkFileUpload();
            _componentCoListForm();
            btnExportExcel();
            btnImportUpdate();
            btnImport();
        }
    }
}();

var ItemComponent = {
		showModal : function(){
			var url = $('#urlDownload').val();
			if(url != null && url != ''){
				$('#modal_export').modal('show');    
			}
		},
}

// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	CoComponent.init();
	ItemComponent.showModal();
});
