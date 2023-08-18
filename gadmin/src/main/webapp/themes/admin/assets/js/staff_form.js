
var StaffComponent = function() {
	
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
    
	 // check User existing
	 var checkUsername = function() {
			$('#username').on('keyup', function(e){
				var username = $('#username').val();
				var contextPath = getContext();
				var messageCheckCode = document.getElementById('messageCheckCode');
				if(username != ''){
					$.ajax({
						url: contextPath + '/user/form/checkUsername',
						contextType: 'application/json',
						method: 'GET',
						data: {
							username: username
						},
						success: function(data) {
							messageCheckCode.style.display = 'block';
							if(data) {
								messageCheckCode.style.color = 'red';
								$('#submitForm').attr("disabled","disabled");
								$('#messageCheckCode').html('\u0054\u00ea\u006e \u0111\u0103\u006e\u0067 \u006e\u0068\u1ead\u0070 \u006e\u00e0\u0079 \u0111\u00e3 \u0111\u01b0\u1ee3\u0063 \u0073\u1eed \u0064\u1ee5\u006e\u0067\u002e')
							}else{
								console.log(document.getElementById('username').value.length)
								if(document.getElementById('username').value.length != 0){
									messageCheckCode.style.color = 'green';
									$('#submitForm').removeAttr('disabled');
									$('#messageCheckCode').html('\u0054\u00ea\u006e \u0111\u0103\u006e\u0067 \u006e\u0068\u1ead\u0070 \u0068\u1ee3\u0070 \u006c\u1ec7')
								} else {
									messageCheckCode.style.color = 'red';
									$('#submitForm').attr("disabled","disabled");
									$('#messageCheckCode').html('\u004b\u0068\u00f4\u006e\u0067 \u0111\u01b0\u1ee3\u0063 \u0062\u1ecf \u0074\u0072\u1ed1\u006e\u0067 \u0074\u0072\u01b0\u1edd\u006e\u0067 \u006e\u00e0\u0079')
								}
							}
						},
						error: function(err) {
							console.log(err)
						}
					});
				} else {
				}
			});
		};
		//
		// Validate form
		var validateForm = function(){
			$('#userForm').validate({
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
			    /*success: function(label) {
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
					"username": {
						required: true,
						maxlength: 256,
					},
					"staff.fullname": {
						required: true,
						maxlength: 256,
					},
					"staff.email": {
						required: true,
						maxlength: 256,
					},
				},
				messages: {
					"username": {
						required: "Không được bỏ trống trường này",
						maxlength: "Chỉ nhập tối đa 256 kí tự",
					},
					"staff.fullname": {
						required: "Không được bỏ trống trường này",
						maxlength: "Chỉ nhập tối đa 256 kí tự",
					},
					"staff.email": {
						required: "Không được bỏ trống trường này",
						maxlength: "Chỉ nhập tối đa 256 kí tự",
					},
				}
			});
		}
	 

    return {
        init: function() {
        	initForm();
            initUniform();
            initSwitchery();
            initBootstrapSwitch();
            validateForm();
            checkUsername();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	StaffComponent.init();
});
