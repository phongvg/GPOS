var ParamComponent = function() {	
	
	var initEventHandler = function(){
		if (typeof bootbox == 'undefined') {
	        console.warn('Warning - bootbox.min.js is not loaded.');
	        return;
	    }
		$('#applySubmit').on('click',function(e){
			 //stop submitting the form to confirm first
            e.preventDefault();
            bootbox.dialog({
			    title: "Xác nhận:",
			    message: "Bạn chọn hình thức ghi đè hay update dữ liệu?",
			    buttons: {
			    	cancel: {
			            label: "Hủy",
			            /*className: 'btn-danger',*/
			            callback: function(){
			                console.log('Custom cancel clicked');
			            }
			        },
			        syncupdate: {
			            label: '<i class="fa fa-check"></i> Update',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                var gpStatus = $('#gpStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                	bootbox.alert({
			                        title: 'Thông báo',
			                        message: 'Không có nhà hàng nào được chọn.'
			                    });
			                }else if(gpStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục GroupParam này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục GroupParam này !'
			                    });
			                } else {
			                	$('#isOverride').val(false);
                                $("#groupParamForm").submit();
                                $('#pleaseWaitDialog').modal();
			                }
			            }
			        },
			        syncfull: {
			            label: '<i class="fa fa-check"></i> Ghi đè',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                var gpStatus = $('#gpStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                	bootbox.alert({
			                        title: 'Thông báo',
			                        message: 'Không có nhà hàng nào được chọn.'
			                    });
			                }else if(gpStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục GroupParam này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục GroupParam này !'
			                    });
			                } else {
			                	$('#isOverride').val(true);
                                $("#groupParamForm").submit();
                                $('#pleaseWaitDialog').modal();
			                }
			            }
			        },
			    },
			});   
		});
	};
   
	var applyToServer = function(){
		if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
		
		$('#applyParamToServer').on('click',function(e){
			 //stop submitting the form to confirm first
            e.preventDefault();
            bootbox.dialog({
			    title: "Xác nhận:",
			    message: "Việc đồng bộ này sẽ gửi dữ liệu xuống các nhà hàng đang có trong hệ thống. Bạn chọn hình thức ghi đè hay update dữ liệu?",
			    buttons: {
			    	cancel: {
			            label: "Hủy",
			            callback: function(){
			                console.log('Custom cancel clicked');
			            }
			        },
			        syncupdate: {
			            label: '<i class="fa fa-check"></i> Update',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                var gpStatus = $('#gpStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                	bootbox.alert({
			                        title: 'Thông báo',
			                        message: 'Không có nhà hàng nào được chọn.'
			                    });
			                }else if(gpStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục GroupParam này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục GroupParam này !'
			                    });
			                } else {
			                	$('#groupParamForm').attr('action', '/sync/catalog/param');
			                	$('#isOverride').val(false);
                                $("#groupParamForm").submit();
                                bootbox.alert({
                                    title: "Thông báo:",
                                    message: "Tiến trình đồng bộ đang được thực hiện. Vui lòng vào phần quản lý đồng bộ để xem thêm.",
                                }); 
			                }
			            }
			        },
			        syncfull: {
			            label: '<i class="fa fa-check"></i> Ghi đè',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                var gpStatus = $('#gpStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                	bootbox.alert({
			                        title: 'Thông báo',
			                        message: 'Không có nhà hàng nào được chọn.'
			                    });
			                }else if(gpStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục GroupParam này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục GroupParam này !'
			                    });
			                } else {
			                	$('#groupParamForm').attr('action', '/sync/catalog/param');
			                	$('#isOverride').val(true);
                                $("#groupParamForm").submit();
                                bootbox.alert({
                                    title: "Thông báo:",
                                    message: "Tiến trình đồng bộ đang được thực hiện. Vui lòng vào phần quản lý đồng bộ để xem thêm.",
                                }); 
			                }
			            }
			        },
			    },
			});
		});
	}; 
	
	return {
        init: function() {
        	applyToServer();
        	initEventHandler();
        }
    }
}();

//Initialize module
//------------------------------

document.addEventListener('DOMContentLoaded', function() {
	ParamComponent.init();
});