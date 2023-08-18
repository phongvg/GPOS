
var FormComponent = function() {
	
    var clickSubmit = function(){
        $('#sync-to-restaurant').on('click',function(e){
			 //stop submitting the form to confirm first
          e.preventDefault();
          const contextPath = getContext();
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
			                console.log('Custom button clicked');
			                bootbox.confirm({
			                    title: 'Xác nhận',
			                    message: 'Việc đồng bộ này sẽ gửi dữ liệu xuống các nhà hàng đang có trong hệ thống. Bạn chắc chắn muốn thực hiện không?',
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
			                    		$('#isOverride').val(false);
			                    		$("#restaurantform").submit();
			                        	$('#pleaseWaitDialog').modal();
			                    	}
			                    }
			                });
			            }
			        },
			        syncfull: {
			            label: '<i class="fa fa-check"></i> Ghi đè',
			            className: 'btn-primary',
			            callback: function(){
			                console.log('Custom button clicked');
			                bootbox.confirm({
			                    title: 'Xác nhận',
			                    message: 'Việc đồng bộ này sẽ gửi dữ liệu xuống các nhà hàng đang có trong hệ thống. Bạn chắc chắn muốn thực hiện không?',
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
			                    		$('#isOverride').val(true);
			                    		$("#restaurantform").submit();
			                        	$('#pleaseWaitDialog').modal();
			                    	}
			                    }
			                });
			            }
			        },
			    },
			});   
		});
    }
    
	var bootboxForm = function(){
		if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        };
	}
	
    //
    // Return objects assigned to module
    //

    // Uniform
    var initUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Default initialization
        $('.form-check-input-styled').uniform();


        //
        // Contextual colors
        //

        // Primary
        $('.form-check-input-styled-primary').uniform({
            wrapperClass: 'border-primary-600 text-primary-800'
        });

        // Danger
        $('.form-check-input-styled-danger').uniform({
            wrapperClass: 'border-danger-600 text-danger-800'
        });

        // Success
        $('.form-check-input-styled-success').uniform({
            wrapperClass: 'border-success-600 text-success-800'
        });

        // Warning
        $('.form-check-input-styled-warning').uniform({
            wrapperClass: 'border-warning-600 text-warning-800'
        });

        // Info
        $('.form-check-input-styled-info').uniform({
            wrapperClass: 'border-info-600 text-info-800'
        });

        // Custom color
        $('.form-check-input-styled-custom').uniform({
            wrapperClass: 'border-indigo-600 text-indigo-800'
        });
    };

    // Switchery
    var initSwitchery = function() {
        if (typeof Switchery == 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize multiple switches
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-check-input-switchery'));
        elems.forEach(function(html) {
          var switchery = new Switchery(html);
        });
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
    
    var checkedSync = function(){
    	$('#checkSync').on('click',function(){
    		var checkSync = $('#checkSync').is(":checked");
			if(checkSync){
				$('#checkSyncMenu').val(true);
			}else {
				$('#checkSyncMenu').val(false);
			}
    	});
    }

	var logoutUserBtn = function(){
        $('#logout-user').on('click',function(e){
			e.preventDefault();
			const contextPath = getContext();
			var username = $("#username").val();
			if(username != null && username.trim() != "") {
				bootbox.confirm({
					title: 'Xác nhận',
					message: 'Việc này sẽ gửi dữ liệu xuống nhà hàng nhà. Bạn có chắc chắn muốn logout user: <b>'+username+'</b> không?',
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
							$('#restaurantform').attr('action', '/sync/restaurant/logout-user');
							$("#restaurantform").submit();
							$('#pleaseWaitDialog').modal();
						}
					}
			  });
			} else {
				bootbox.alert({
					title: 'Thông báo:',
					message: 'Vui lòng nhập thông tin user cần logout!'
				});
			}
		});   
    }
    
    return {
        init: function() {
        	clickSubmit();
        	initSwitchery();
        	initBootstrapSwitch();
        	initUniform();
        	checkedSync();
			bootboxForm();
			logoutUserBtn();
        }
    }
}();

// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});
