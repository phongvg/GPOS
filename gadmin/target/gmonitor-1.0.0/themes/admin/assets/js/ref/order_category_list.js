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
    
    // Đồng bộ dữ liệu xuống server nhà hàng
    var syncToRes = function() {
        $('#btnSyncToRestaurant').on('click', function() {
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
                		location.href = contextPath + "/orderCategory/sync-to-res";
                	}
                }
            });
        });
    };

    // Đồng bộ dữ liệu từ RK7
    var syncFromRk7 = function() {
        $('#btnSyncFromRk7').on('click', function() {
			$('#pleaseWaitDialog').modal();
		});
    };

    var search = function() {
        $('#btnSubmit').on('click', function() {
            $('#page').val(0);
			$('#orderCategoryForm').submit();
        });
    }

    return {
        init: function() {
        	initForm();
            syncToRes();
            syncFromRk7();
            search();
        }
    }
}();


document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});