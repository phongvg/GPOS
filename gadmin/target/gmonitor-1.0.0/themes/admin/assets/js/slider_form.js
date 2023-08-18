/* ------------------------------------------------------------------------------
 *
 *  # All process js for category-form.jsp
 *
 * ---------------------------------------------------------------------------- */


// Setup module
// ------------------------------

var SliderModule = function() {

    //
    // Setup module components
    //
    var componentSlider = function() {
        if (!$().fileinput) {
            console.warn('Warning - fileinput.min.js is not loaded.');
            return;
        }
        
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }
        
        //
        // Define variables
        //

    	// select2
   	 	$('.select-order').select2();

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
        	maxFileSize: 500,
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
        var photoUrl = $('#photoUrl').val();
        var photo;
        if (photoUrl) {
        	photo = photoUrl;
        } else {
        	photo = defaultIcon;
        }
        //console.log(defaultIcon);
        var imgTag = '<img src="' + photo + '"/>';
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
            initialPreview: [photo], 
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


    };


    //
    // Return objects assigned to module
    //

    return {
        init: function() {
            componentSlider();
        }
    }
}();


// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
    SliderModule.init();
});
