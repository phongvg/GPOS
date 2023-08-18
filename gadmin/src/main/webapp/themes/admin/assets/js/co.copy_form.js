var CoComponent = function(){
	
	var initCopy = function(){
		$('#btnCopy').on('click',function(e){
			 //stop submitting the form to confirm first
            e.preventDefault();
            const contextPath = getContext();
            var selectedRestaurantCodes = $('#selectedResCodes').val();
            var coId = $('#coId').val();
            if(coId && selectedRestaurantCodes == '' || selectedRestaurantCodes == '' ){
            	bootbox.alert({
                    title: "Thông báo:",
                    message: "Bạn chưa chọn nhà hàng. Vui lòng chọn nhà hàng để copy.",
                }); 
            } else if(coId == ''){
            	bootbox.alert({
                    title: "Thông báo:",
                    message: "Nhà hàng này chưa được áp dụng danh mục CO.",
                });
            } else {
            	bootbox.dialog({
    			    title: "Xác nhận:",
    			    message: "Bạn chọn hình thức ghi đè hay update dữ liệu?",
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
			                    $.ajax({
			                        url: contextPath + '/api/res/co/check-so',
			                        method:'GET',
			                        data:{coId:coId,selectedResCode:selectedRestaurantCodes.toString()},
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
			                            	$('#isCopyOverride').val(false);
			                                $("#coFormCopy").submit();
			                                $('#pleaseWaitDialog').modal();
			                            }
			                        },
			                        error: function (e) {
			                            console.log("error: ", e);
			                        }
			                    });
    			            }
    			        },
    			        syncfull: {
    			            label: '<i class="fa fa-check"></i> Ghi đè',
    			            className: 'btn-primary',
    			            callback: function(){
			                    $.ajax({
			                        url: contextPath + '/api/res/co/check-so',
			                        method:'GET',
			                        data:{coId:coId,selectedResCode:selectedRestaurantCodes.toString()},
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
			                            	$('#isCopyOverride').val(true);
			                                $("#coFormCopy").submit();
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
    			});
            }
		});
	};
	
	//Check So before apply
    var initApply = function(){
		$('#btnApply').on('click',function(e){
			var selectedCoId = $('#selectedCoId').val();
			 //stop submitting the form to confirm first
            e.preventDefault();
            bootbox.dialog({
			    title: "Xác nhận:",
			    message: "Bạn chọn hình thức ghi đè hay update dữ liệu?",
			    buttons: {
			    	cancel: {
			            label: "Hủy",
			           /* className: 'btn-danger',*/
			            callback: function(){
			                console.log('Custom cancel clicked');
			            }
			        },
			        syncupdate: {
			            label: '<i class="fa fa-check"></i> Update',
			            className: 'btn-primary',
			            callback: function(){
			            	if(selectedCoId !=''){
			            		$('#isApplyOverride').val(false);
	                            $("#coFormApply").submit();
	                            $('#pleaseWaitDialog').modal();
			            	}else{
			            		bootbox.alert({
			                        title: "Thông báo:",
			                        message: "Vui lòng chọn danh mục CO.",
			                    }); 
			            	}
			            }
			        },
			        syncfull: {
			            label: '<i class="fa fa-check"></i> Ghi đè',
			            className: 'btn-primary',
			            callback: function(){
			            	if(selectedCoId !=''){
			            		$('#isApplyOverride').val(true);
	                            $("#coFormApply").submit();  
	                            $('#pleaseWaitDialog').modal();
			            	}else{
			            		bootbox.alert({
			                        title: "Thông báo:",
			                        message: "Vui lòng chọn danh mục CO.",
			                    }); 
			            	}
			            }
			        },
			    },
			});   
		});
	};
	
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
    
    return {
        init: function() {
            initCopy();
            initApply();
            initForm();
        }
    }
}();


//Initialize module
//------------------------------

document.addEventListener('DOMContentLoaded', function() {
	CoComponent.init();
});
