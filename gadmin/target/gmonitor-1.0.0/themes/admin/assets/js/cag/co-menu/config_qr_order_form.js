
var configQrOrderForm = function() {
	
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
		var infoPhotoUrl = $('#infoPhotoUrl').val();
		var logoPhotoUrl = $('#logoPhotoUrl').val();
		var closeOrderPhotoUrl = $('#closeOrderPhotoUrl').val();
	
		
        var infoPhoto;
        var logoPhoto;
        var closeOrderPhoto; 
        
        var infoPhotoName =  '';
        var logoPhotoName =  '';
        var closeOrderPhotoName =  '';
     
        
        if (infoPhotoUrl) {
        	infoPhoto = infoPhotoUrl;
        	var str = infoPhotoUrl.slice(infoPhotoUrl.lastIndexOf("\\") + 1);
        	infoPhotoName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	infoPhoto = defaultIcon;
		}
        
        if (logoPhotoUrl) {
        	logoPhoto = logoPhotoUrl;
        	var str = logoPhotoUrl.slice(logoPhotoUrl.lastIndexOf("\\") + 1);
        	logoPhotoName = str.slice(str.lastIndexOf("/") + 1);
        } else {
        	logoPhoto = defaultIcon;
		}
        
        if (closeOrderPhotoUrl) {
        	closeOrderPhoto = closeOrderPhotoUrl;
        	var half = closeOrderPhotoUrl.slice(closeOrderPhotoUrl.lastIndexOf("\\") + 1);
        	closeOrderPhotoName = half.slice(half.lastIndexOf("/") + 1);
        } else {
        	closeOrderPhoto = defaultIcon;
		}
        
        // ảnh Intro - infoPhoto
        $('#infoPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delInfo"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [infoPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: infoPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        // ảnh Logo - logoPhoto
        $('#logoPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delLogo"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [logoPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false, caption: logoPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        // ảnh cảm ơn - closeOrderPhoto
        $('#closeOrderPhoto').fileinput({
	        showClose: false,
	        showCaption: false,
	        showUpload: false,
            browseIcon: '<i class="icon-file-plus mr-2"></i>',
            uploadIcon: '<i class="icon-file-upload2 mr-2"></i>',
            removeIcon: '<i class="icon-cross2 font-size-base mr-2 delCloseOrder"></i>',
            layoutTemplates: {
                icon: '<i class="icon-file-check"></i>',
                modal: modalTemplate
            },
            initialPreview: [closeOrderPhoto],  
            initialPreviewConfig: [
                {showDrag: false, showRemove: false,caption: closeOrderPhotoName}
            ],
            initialPreviewAsData: true,
            overwriteInitial: true,
            previewZoomButtonClasses: previewZoomButtonClasses,
            previewZoomButtonIcons: previewZoomButtonIcons,
            fileActionSettings: fileActionSettings
        });
        
        
    };
    
    var delInfoPhoto = function(){
    	$('.delInfo').parent().on('click', function(e){
    		var configQrOrderId = $("#configQrOrderId").val();
            var contextPath = getContext();
            if(configQrOrderId != null && configQrOrderId != "") {
	            $.ajax({
	                url : contextPath + '/del/infoPhoto',
	                method : 'POST',
	                data : {configQrOrderId : configQrOrderId},
	                success : function(data){
	                    
	                },
	                error : function(e){
	                    console.log(e);
	                }
	            })
            }
    	});
    };
    
    var delLogoPhoto = function(){
    	$('.delLogo').parent().on('click', function(e){
    		var configQrOrderId = $("#configQrOrderId").val();
            var contextPath = getContext();
            if(configQrOrderId != null && configQrOrderId != "") {
	            $.ajax({
	                url : contextPath + '/del/logoPhoto',
	                method : 'POST',
	                data : {configQrOrderId : configQrOrderId},
	                success : function(data){
	                    
	                },
	                error : function(e){
	                    console.log(e);
	                }
            	})
            }
    	});
    };
    
    var delCloseOrderPhoto = function(){
    	$('.delCloseOrder').parent().on('click', function(e){
    		var configQrOrderId = $("#configQrOrderId").val();
            var contextPath = getContext();
            if(configQrOrderId != null && configQrOrderId != "") {
	            $.ajax({
	                url : contextPath + '/del/closeOrderPhoto',
	                method : 'POST',
	                data : {configQrOrderId : configQrOrderId},
	                success : function(data){
	                    
	                },
	                error : function(e){
	                    console.log(e);
	                }
	            })
            }
    	});
    };
    
    return {
        init: function() {
            _componentInputPreviewForm();
            delInfoPhoto();
            delLogoPhoto();
            delCloseOrderPhoto();
        }
    }
}();

// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	configQrOrderForm.init();
});