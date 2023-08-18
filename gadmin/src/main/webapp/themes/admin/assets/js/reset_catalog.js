var CoComponent = function() {	
	
	var resetCFI = function(){
		$('#resetCFI').on('click',function(e){
			bootbox.dialog({
				title: 'Xác nhận',
				message: 'Việc này sẽ reset lại dữ liệu mà nhà hàng đã sửa. Bạn có muốn tiếp tục không?',
				buttons: {
					cancel: {
						label: 'Hủy',
					},
					full: {
						label: 'Xác nhận',
						className: 'btn-primary',
						callback: function() {
							$('#coFoodItemForm').attr('action', '/res/coFoodItem/reset');
				            $("#coFoodItemForm").submit();
				            $('#pleaseWaitDialog').modal();
						}
					},
				},
			});
			
		});
	}
	
	var resetCoCategory = function(){
		$('#resetCoCategory').on('click',function(e){
			bootbox.dialog({
				title: 'Xác nhận',
				message: 'Việc này sẽ reset lại dữ liệu mà nhà hàng đã sửa. Bạn có muốn tiếp tục không?',
				buttons: {
					cancel: {
						label: 'Hủy',
					},
					full: {
						label: 'Xác nhận',
						className: 'btn-primary',
						callback: function() {
							$('#coCategoryForm').attr('action', '/res/coCategory/reset');
				            $("#coCategoryForm").submit();
				            $('#pleaseWaitDialog').modal();
						}
					},
				},
			});
			
		});
	}
	
	var resetSoCategory = function(){
		$('#resetSoCategory').on('click',function(e){
			bootbox.dialog({
				title: 'Xác nhận',
				message: 'Việc này sẽ reset lại dữ liệu mà nhà hàng đã sửa. Bạn có muốn tiếp tục không?',
				buttons: {
					cancel: {
						label: 'Hủy',
					},
					full: {
						label: 'Xác nhận',
						className: 'btn-primary',
						callback: function() {
							$('#menuListForm').attr('action', '/res/so/menu/reset');
				            $("#menuListForm").submit();
				            $('#pleaseWaitDialog').modal();
						}
					},
				},
			});
			
		});
	}
	
	
	return {
        init: function() {
        	resetCFI();
        	resetCoCategory();
        	resetSoCategory();
        }
    }
}();

//Initialize module
//------------------------------

document.addEventListener('DOMContentLoaded', function() {
	CoComponent.init();
});