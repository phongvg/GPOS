var FormComponent = function() {
	
    var initForm = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }

        if (!$().select2) {
            return;
        }
    	// select2
   	 	$('.select2').select2();
    };
    
    var btnImportExcel = function(){
    	$('#btnImport').on('click', function() {
    		$('#pleaseWaitDialog').modal();
    	});
    }
    
    var btnExportExcel = function(){
    	$('#downloadFileCheck').on('click',function(){
    		$('#modal_export').modal('hide');  
    	});
    }
    
    return {
        init: function() {
        	initForm();
        	btnImportExcel();
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

document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
	ItemComponent.showModal();
});
