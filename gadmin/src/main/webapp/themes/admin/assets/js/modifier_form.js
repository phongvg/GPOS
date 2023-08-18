var ModifierComponent = function() {
	
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();

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

    //
    // Return objects assigned to module
    //
    
    // Bootbox
    var initBootbox = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }

        // Confirmation dialog
        $('#sync-to-restaurant').on('click', function() {
            bootbox.confirm({
                title: 'Xác nhận',
                message: 'Việc đồng bộ này sẽ gửi dữ liệu xuống toàn bộ các nhà hàng đang có trong hệ thống. Bạn chắc chắn muốn thực hiện không?',
                buttons: {
                    confirm: {
                        label: 'Yes',
                        className: 'btn-primary'
                    },
                    cancel: {
                        label: 'Cancel',
                        className: 'btn-link'
                    }
                },
                callback: function (result) {
                	if (result) {
                		const contextPath = getContext();
                		location.href = contextPath + "/modifier/sync-to-res";
                	}
                }
            });
        });
        

    };
    return {
        init: function() {
        	initForm();
        	initBootstrapSwitch();
        	initBootbox();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	ModifierComponent.init();
});
