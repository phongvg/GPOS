var GroupParamComponent = function(){
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();
   	 	
	   	 if (!$().multiselect) {
	         console.warn('Warning - bootstrap-multiselect.js is not loaded.');
	         return;
	     }
	
	     // Select All and Filtering features
	     $('.multiselect-select-all-filtering').multiselect({
	         includeSelectAllOption: true,
	         enableFiltering: true,
	         enableCaseInsensitiveFiltering: true
	     });

	}
	
	var initApply = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    	
        $("#btnApply").on("click", (e) => {
			bootbox.dialog({
				title: 'Xác nhận',
				message: 'Bạn chọn hình thức ghi đè hay update dữ liệu?',
				buttons: {
					cancel: {
						label: 'Hủy',
						//className: 'btn-danger'
					},
					update: {
						label: 'Update',
						className: 'btn-primary',
						callback: function() {
							$('#isApplyOverride').val(false);
							$('#groupParamFormApply').submit();
							$('#pleaseWaitDialog').modal();
						}
					},
					full: {
						label: 'Ghi đè',
						className: 'btn-primary',
						callback: function() {
							$('#isApplyOverride').val(true);
							$('#groupParamFormApply').submit();
							$('#pleaseWaitDialog').modal();
						}
					},
				},
			});
        });
    }

    var initCopy = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    	
        $("#btnCopy").on("click", (e) => {
        	var resCodes = $('#selectedResCodes').val();
        	console.log("resCodes :" +resCodes);
        	if(resCodes == ''){
        		bootbox.alert({
                    title: "Thông báo:",
                    message: "Bạn chưa chọn nhà hàng. Vui lòng chọn nhà hàng để copy.",
                }); 
        	} else {
        		bootbox.dialog({
    				title: 'Xác nhận',
    				message: 'Bạn chọn hình thức ghi đè hay update dữ liệu?',
    				buttons: {
    					cancel: {
    						label: 'Hủy',
    						//className: 'btn-danger'
    					},
    					update: {
    						label: 'Update',
    						className: 'btn-primary',
    						callback: function() {
    							$('#isCopyOverride').val(false);
    							$('#groupParamFormCopy').submit();
    							$('#pleaseWaitDialog').modal();
    						}
    					},
    					full: {
    						label: 'Ghi đè',
    						className: 'btn-primary',
    						callback: function() {
    							$('#isCopyOverride').val(true);
    							$('#groupParamFormCopy').submit();
    							$('#pleaseWaitDialog').modal();
    						}
    					},
    				},
    			});
        	}
        });
    }
    
    return {
        init: function() {
            initForm();
            initApply();
            initCopy();
            
        }
    }
}();


//Initialize module
//------------------------------

document.addEventListener('DOMContentLoaded', function() {
	GroupParamComponent.init();
});
