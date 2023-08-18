
var SoComponent = function() {
	
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

    // Bootbox extension
    var initBootbox = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    };
   /* var submitForm = function() {
		$('#btnSubmit').on('click', function(){
			$('#soform').submit();
			$('#pleaseWaitDialog').modal();
		})
	}
*/
    
	var initEventHandler = function() {
		$('#btnSubmit').on('click', function (e) {
            //stop submitting the form to confirm first
            e.preventDefault();

            const soStatus = $("input[name='status']:checked").val();
            if (soStatus) {
            	$("#soform").submit();
            } else {
        		const contextPath = getContext();
        		const soId = $("#soId").val();
        		$.ajax({
        			url: contextPath + '/api/so/check-to-off',
        			method:'GET',
        			data:{soId:soId},
        			success: function(data) {
        				if (data.result) {
        					let s = '';
        					$.each(data.data, function(idx, item) {
        						s = s + item + ", ";
        					});
        					s = s.substring(0, s.length - 2);
        					
        					bootbox.confirm({
            				    title: "Xác nhận:",
            				    message: "Có " + data.size + " nhà hàng đang áp dụng danh mục SO này (Mã nhà hàng: " + s + "). Off danh mục này sẽ dẫn đến dữ liệu SO/CO tương ứng vơi danh mục tại nhà hàng sẽ bị xóa. Bạn có chắc chắn muốn off danh mục này không?",
            				    buttons: {
            				        cancel: {
            				            label: '<i class="fa fa-times"></i> Hủy'
            				        },
            				        confirm: {
            				            label: '<i class="fa fa-check"></i> Thực hiện'
            				        }
            				    },
            				    callback: function (result) {
            				        console.log('This was logged in the callback: ' + result);
            				        if (result) {
            				        	$("#soform").submit();
            				        }
            				    }
            				});        				
        				} else {
        					bootbox.confirm({
            				    title: "Xác nhận:",
            				    message: "Bạn có chắc chắn muốn off danh mục này không?",
            				    buttons: {
            				        cancel: {
            				            label: '<i class="fa fa-times"></i> Hủy'
            				        },
            				        confirm: {
            				            label: '<i class="fa fa-check"></i> Thực hiện'
            				        }
            				    },
            				    callback: function (result) {
            				        console.log('This was logged in the callback: ' + result);
            				        if (result) {
            				        	$("#soform").submit();
            				        }
            				    }
            				});
        				}
        			},
        			error: function (e) {
        				console.log("error: ", e);
        			}
        		});
            }
        });
	};
    
    
    //
    // Return objects assigned to module
    //
    return {
        init: function() {
            initUniform();
            initSwitchery();
          /*  submitForm();*/
            initBootstrapSwitch();
            initBootbox();
            initEventHandler();
        }
    }
}();


var SoForm = {
	check: (path, soId) => {
		if (soId) {
			location.href = path + "?soId=" + soId;
		} else {
            bootbox.alert({
            	title: 'Thông báo:',
                message: 'Thông tin SO chưa được tạo !'
            });
		}
	}
}

// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	SoComponent.init();
});
