/* ------------------------------------------------------------------------------
 *
 *  # All process js for product-form.jsp
 *
 * ---------------------------------------------------------------------------- */


// Setup module
// ------------------------------

var ProductForm = function() {

	var _componentCheckProductCode = function() {
		$('#code').on('change', function() {
			var code = $(this).val();
			var contextPath = getContext();
			var messageCheckCode = document.getElementById('messageCheckCode');
			
			$.ajax({
				url: contextPath + "/api/pro/checkCode",
				method: 'POST',
				contextType: 'application/json',
				data: {
					code: code
				},
				success: function(data) {
					messageCheckCode.style.display = 'block';
					if (data) {
						messageCheckCode.style.color = 'red';
						$('#productSubmit').attr("disabled", "disabled");
						$('#messageCheckCode').html('\u004d\u00e3 \u0073\u1ea3\u006e \u0070\u0068\u1ea9\u006d \u0111\u00e3 \u0074\u1ed3\u006e \u0074\u1ea1\u0069\u002e')
					} else {
						console.log(document.getElementById('code').value.length)
						if (document.getElementById('code').value.length != 0) {
							messageCheckCode.style.color = 'green';
							$('#productSubmit').removeAttr('disabled');
							$('#messageCheckCode').html('\u004d\u00e3 \u0073\u1ea3\u006e \u0070\u0068\u1ea9\u006d \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
						} else {
							messageCheckCode.style.color = 'red';
							$('#productSubmit').attr("disabled", "disabled");
							$('#messageCheckCode').html('\u004d\u00e3 \u0073\u1ea3\u006e \u0070\u0068\u1ea9\u006d \u006b\u0068\u00f4\u006e\u0067 \u0070\u0068\u00f9 \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
						}
						
					}
				},
				error: function(err) {
					console.log(err)
				}
			});
		});
	};
    //
    // Setup module components
    //

    // Bootstrap file upload
    var _componentProductForm = function() {
        if (!$().fileinput) {
            console.warn('Warning - fileinput.min.js is not loaded.');
            return;
        }
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
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

    	// select2
		$('.select-category').select2();
		$('.select-product-type').select2();

        // Primary
        $('.form-check-input-styled-primary').uniform({
            wrapperClass: 'border-primary-600 text-primary-800'
        });
        $('.form-check-input-styled-warning').uniform({
            wrapperClass: 'border-warning-600 text-warning-800'
        });
        
   	 	// ckeditor
        /*CKEDITOR.replace('mceEditor', {
            height: 400,
            extraPlugins: 'forms'
        });*/
   	 	
   	 	
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
        var defaultIcon = $('#defaultIcon').val();
		var avatarUrl = $('#avatarUrl').val();
        var avatar;
        if (avatarUrl) {
        	avatar = avatarUrl;
        } else {
        	avatar = defaultIcon;
		}
        //console.log(defaultIcon);
        var imgTag = '<img src="' + avatar + '"/>';
        $('.file-input-overwrite').fileinput({
	        showClose: false,
	        //browseClass: "btn",
	        //removeClass: "btn",
	        showCaption: false,
	        showUpload: false,
	        //browseLabel: '',
		    //removeLabel: '',
            
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [avatar],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false}
            ],
            //defaultPreviewContent: imgTag,
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
            removeIcon: '<i class="icon-cross2 font-size-base mr-2"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [], 
            initialPreviewConfig: [
                {showDrag: false, showRemove: false}
            ],
            //defaultPreviewContent: imgTag,
            initialPreviewAsData: false,
            overwriteInitial: false,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
		});
    };

    //
    // Return objects assigned to module
    //
    return {
        init: function() {
			_componentProductForm();
			_componentCheckProductCode();
        }
    }
}();


var productComponent = {
	initPhotos: function() {
		var productId = $("#productId").val();
		
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/admin/photos/load',
			method: 'GET',
			data: {pId : productId},
			success: function(dataList) {
				var s ='';
				dataList.forEach((item, index) => {
					s += '<div class="col-sm-6 col-xl-3">';
					s += '<div class="card">';
					s += '<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="'+ item.url +'" alt=""></div>';
					s += '<div class="card-body">';
					s += '<div class="d-flex align-items-start flex-nowrap">';
					s += '<div class="list-icons list-icons-extended ml-auto"><a class="list-icons-item" onclick="delPhoto('+ item.id +');"><i class="icon-bin top-0"></i></a></div>';				
					s += '</div>';			
					s += '</div>';
					s += '</div>';
					s += '</div>';
				});
				
				/*
				for(i=0; i<data.length; i++) {
					s += '<div class="col-sm-6 col-xl-3">';
					s += '<div class="card">';
					s += '<div class="card-img-actions mx-1 mt-1"><img class="card-img img-fluid" src="../../../../global_assets/images/placeholders/placeholder.jpg" alt=""></div>';
					s += '<div class="card-body">';
					s += '<div class="d-flex align-items-start flex-nowrap">';
					s += '<div class="list-icons list-icons-extended ml-auto"><a href="#" class="list-icons-item"><i class="icon-bin top-0"></i></a></div>';				
					s += '</div>';			
					s += '</div>';
					s += '</div>';
					s += '</div>';
				}
				*/

				console.log(s);
				$("#existingPhotos").html(s);
			},
			error: function(e) {
				console.log(e);
			}
		})
	},

	delPhoto: function(aId) {
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/admin/photo/del',
			method: 'POST',
			data: {aId : aId},
			success: function(res) {
				productComponent.initPhotos();
			},
			error: function(e) {
				console.log(e);
			}
		})
	}
}



// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
    ProductForm.init();
});
