var ItemFormComponent = function() {
    var btnImportExcel = function(){
    	$('#btnImport').on('click', function() {
    		$('#pleaseWaitDialog').modal();
            $('#printGroupImport').attr('action', '/import/printGroup');
			$('#printGroupImport').submit();
    	});
    }
    
    return {
        init: function() {
        	btnImportExcel();
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
	ItemFormComponent.init();
	ItemComponent.showModal();
});
