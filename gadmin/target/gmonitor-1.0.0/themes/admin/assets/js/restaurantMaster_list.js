var restaurantMasterList= function() {
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
                		location.href = contextPath + "/restaurantMaster/sync-to-res";
                	}
                }
            });
        });
        

    };

    //
    // Return objects assigned to module
    //
    return {
        init: function() {
        	initBootbox();
        }
    }
}();

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	restaurantMasterList.init();
});