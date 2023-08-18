
var KdsApplyComponent = function() {

    var initApply = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    	
        $("#btnKdsApply").on("click", (e) => {
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
							$('#isOverride').val(false);
							$('#kdsApplyForm').submit();
							$('#pleaseWaitDialog').modal();
						}
					},
					full: {
						label: 'Ghi đè',
						className: 'btn-primary',
						callback: function() {
							$('#isOverride').val(true);
							$('#kdsApplyForm').submit();
							$('#pleaseWaitDialog').modal();
						}
					},
				},
			});
        });
    }

    // Return objects assigned to module
    return {
        init: function() {
        	initApply();
        }
    }
}();

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	KdsApplyComponent.init();
});
