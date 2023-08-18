var CoComponent = function() {	
	
	var initEventHandler = function(){
		$('#coApplySubmit').on('click',function(e){
			 //stop submitting the form to confirm first
            e.preventDefault();
            const contextPath = getContext();
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
			                var coStatus = $('#coStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                    $("#coform").submit();
			                    $('#pleaseWaitDialog').modal();
			                }else if(coStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục CO này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục CO này !'
			                    });
			                } else {
			                    var refId = $('#refId').val();
			                    $.ajax({
			                        url: contextPath + '/api/co/check-so',
			                        method:'GET',
			                        data:{refId:refId,
			                            selectedRestaurantCodes:selectedRestaurantCodes.toString()
			                        },
			                        success: function(data) {
			                            if (data.result) {
			                                let s = '';
			                                $.each(data.data, function(idx, item) {
			                                    s = s + item + ", ";
			                                });
			                                s = s.substring(0, s.length - 2);
			                                
			                                bootbox.alert({
			                                    title: "Thông báo:",
			                                    message: "Nhà hàng "+ s +" chưa áp dụng danh mục SO chứa danh mục CO này, bạn cần đồng bộ danh mục SO trước.",
			                                });                     
			                            }else{
			                            	$('#coform').attr('action', '/co/apply-to-res');
			                            	$('#isOverride').val(false);
			                                $("#coform").submit();
			                                $('#pleaseWaitDialog').modal();
			                            }
			                        },
			                        error: function (e) {
			                            console.log("error: ", e);
			                        }
			                    });
			                }
			            }
			        },
			        syncfull: {
			            label: '<i class="fa fa-check"></i> Ghi đè',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                var coStatus = $('#coStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                    $("#coform").submit();
			                    $('#pleaseWaitDialog').modal();
			                }else if(coStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục CO này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục CO này.'
			                    });
			                } else {
			                    var refId = $('#refId').val();
			                    $.ajax({
			                        url: contextPath + '/api/co/check-so',
			                        method:'GET',
			                        data:{refId:refId,
			                            selectedRestaurantCodes:selectedRestaurantCodes.toString()
			                        },
			                        success: function(data) {
			                            if (data.result) {
			                                let s = '';
			                                $.each(data.data, function(idx, item) {
			                                    s = s + item + ", ";
			                                });
			                                s = s.substring(0, s.length - 2);
			                                
			                                bootbox.alert({
			                                    title: "Thông báo:",
			                                    message: "Nhà hàng "+ s +" chưa áp dụng danh mục SO chứa danh mục CO này, bạn cần đồng bộ danh mục SO trước.",
			                                });                     
			                            }else{
			                            	$('#coform').attr('action', '/co/apply-to-res');
			                            	$('#isOverride').val(true);
			                                $("#coform").submit();
			                                $('#pleaseWaitDialog').modal();
			                            }
			                        },
			                        error: function (e) {
			                            console.log("error: ", e);
			                        }
			                    });
			                }
			            }
			        },
			    },
			});   
		});
	};
   
	var applyToServer = function(){
		$('#applyCoToServer').on('click',function(e){
			 //stop submitting the form to confirm first
            e.preventDefault();
            const contextPath = getContext();
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
			                var coStatus = $('#coStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                	bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Bạn chưa chọn nhà hàng để đồng bộ!'
			                    });
			                }else if(coStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục CO này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục CO này !'
			                    });
			                } else {
			                    var refId = $('#refId').val();
			                    $.ajax({
			                        url: contextPath + '/api/co/check-so',
			                        method:'GET',
			                        data:{refId:refId,
			                            selectedRestaurantCodes:selectedRestaurantCodes.toString()
			                        },
			                        success: function(data) {
			                            if (data.result) {
			                                let s = '';
			                                $.each(data.data, function(idx, item) {
			                                    s = s + item + ", ";
			                                });
			                                s = s.substring(0, s.length - 2);
			                                
			                                bootbox.alert({
			                                    title: "Thông báo:",
			                                    message: "Nhà hàng "+ s +" chưa áp dụng danh mục SO chứa danh mục CO này, bạn cần đồng bộ danh mục SO trước.",
			                                });                     
			                            }else{
			                            	$('#coform').attr('action', '/sync/catalog/co');
			                            	$('#isOverride').val(false);
			                                $("#coform").submit();
			                                bootbox.alert({
			                                    title: "Thông báo:",
			                                    message: "Tiến trình đồng bộ đang được thực hiện. Vui lòng vào phần quản lý đồng bộ để xem thêm.",
			                                }); 
			                            }
			                        },
			                        error: function (e) {
			                            console.log("error: ", e);
			                        }
			                    });
			                }
			            }
			        },
			        syncfull: {
			            label: '<i class="fa fa-check"></i> Ghi đè',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                var coStatus = $('#coStatus').val();
			                var selectedRestaurantCodes = $('#selectedRestaurantCodes').val();
			                if (selectedRestaurantCodes == null) {
			                	bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Bạn chưa chọn nhà hàng để đồng bộ!'
			                    });
			                }else if(coStatus !="true"){
			                    bootbox.alert({
			                        title: 'Thông báo:',
			                        message: 'Danh mục CO này đang tắt để đồng bộ xuống nhà hàng bạn cần bật lại danh mục CO này.'
			                    });
			                } else {
			                    var refId = $('#refId').val();
			                    $.ajax({
			                        url: contextPath + '/api/co/check-so',
			                        method:'GET',
			                        data:{refId:refId,
			                            selectedRestaurantCodes:selectedRestaurantCodes.toString()
			                        },
			                        success: function(data) {
			                            if (data.result) {
			                                let s = '';
			                                $.each(data.data, function(idx, item) {
			                                    s = s + item + ", ";
			                                });
			                                s = s.substring(0, s.length - 2);
			                                
			                                bootbox.alert({
			                                    title: "Thông báo:",
			                                    message: "Nhà hàng "+ s +" chưa áp dụng danh mục SO chứa danh mục CO này, bạn cần đồng bộ danh mục SO trước.",
			                                });                     
			                            }else{
			                            	$('#coform').attr('action', '/sync/catalog/co');
		    					        	$('#isOverride').val(true);
		                                    $("#coform").submit();
		                                    bootbox.alert({
		                                        title: "Thông báo:",
		                                        message: "Tiến trình đồng bộ đang được thực hiện. Vui lòng vào phần quản lý đồng bộ để xem thêm.",
		                                    }); 
			                            }
			                        },
			                        error: function (e) {
			                            console.log("error: ", e);
			                        }
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
        	initEventHandler();
        	applyToServer();
        }
    }
}();

//Initialize module
//------------------------------

document.addEventListener('DOMContentLoaded', function() {
	CoComponent.init();
});