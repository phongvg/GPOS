
var CoComponent = function() {
	
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();

	}
	
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


    //
    // Return objects assigned to module
    //
    
 // Bootbox extension
    var initBootbox = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    };
   /* var submitForm = function() {
		$('#btnSubmit').on('click', function(){
			$('#coForm').submit();
			$('#pleaseWaitDialog').modal();
		})
	}*/
    //OffCatalog
    var initEventHandler = function() {
		$('#btnSubmit').on('click', function (e) {
            //stop submitting the form to confirm first
            e.preventDefault();
            var appliedRestaurantCodes = $('#appliedRestaurantCodes').val();
            const coStatus = $('#status').is(':checked');
            if(appliedRestaurantCodes == "true" && coStatus != true){
            	bootbox.confirm({
				    title: "Xác nhận:",
				    message: "Có nhà hàng đang áp dụng danh mục CO này. Off danh mục này sẽ dẫn đến dữ liệu CO tương ứng vơi danh mục tại nhà hàng sẽ bị xóa. Bạn có chắc chắn muốn off danh mục này không?",
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
				        	$("#coForm").submit();
				        }
				    }
				});  
            }else {
            	$("#coForm").submit();
            }
        });
	};

	// Validate form
	var validateForm = function(){
		$('#coForm').validate({
			ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
		    errorClass: 'validation-invalid-label',
		    successClass: 'validation-valid-label',
		    validClass: 'validation-valid-label',
		    highlight: function(element, errorClass) {
		        $(element).removeClass(errorClass);
		    },
		    unhighlight: function(element, errorClass) {
		        $(element).removeClass(errorClass);
		    },
		   /* success: function(label) {
		        label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
		    },*/

		    // Different components require proper error label placement
		    errorPlacement: function(error, element) {

		        // Unstyled checkboxes, radios
		        if (element.parents().hasClass('form-check')) {
		            error.appendTo( element.parents('.form-check').parent() );
		        }

		        // Input with icons and Select2
		        else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
		            error.appendTo( element.parent() );
		        }

		        // Input group, styled file input
		        else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
		            error.appendTo( element.parent().parent() );
		        }

		        // Other elements
		        else {
		            error.insertAfter(element);
		        }
		    },
			rules: {
				"name":{
					required: true,
					maxlength: 256,
					minlength: 1,
				},
				"soId":{
					required: true
				},
			},
			messages: {
				"soId":{
					required:"Bắt buộc chọn một danh mục SO.",
				},
				"name":{
					required:"Bắt buộc nhập tên danh mục.",
					maxlength: "Hãy nhập tối đa 256 ký tự",
					minlength: "Hãy nhập tối đa 1 ký tự"
				}
			}
		});
	}
	
	
    return {
        init: function() {
        	initForm();
            initUniform();
            initSwitchery();
            initBootstrapSwitch();
           /* submitForm();*/
            initBootbox();
            initEventHandler();
            validateForm();
        }
    }
}();


var CoForm = {
	check: (path, coId) => {
		if (coId) {
			location.href = path + "?cId=" + coId;
		} else {
			bootbox.alert({
				title: 'Thông báo:',
                message: 'Thông tin CO chưa được tạo !'
            });
		}
	},

	checkCreated: (path, coId) => {
		if (coId) {
			location.href = path + "?coId=" + coId;
		} else {
			bootbox.alert({
				title: 'Thông báo:',
                message: 'Thông tin CO chưa được tạo !'
            });
		}
	}
}

// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	CoComponent.init();
});
