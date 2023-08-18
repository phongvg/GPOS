var SoComponent = function() {
	
    // Select2 examples
    var initSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        }

        // Select with search
        $('.select2').select2();
    };

    //
    // Return objects assigned to module
    //
    
    var btnExportExcel = function(){
    	$('#downloadFileCheck').on('click',function(){
    		$('#modal_export').modal('hide');  
    	});
    }
    
    return {
        init: function() {
        	initSelect2();
        	btnExportExcel();
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
	SoComponent.init();
	ItemComponent.showModal();
});
