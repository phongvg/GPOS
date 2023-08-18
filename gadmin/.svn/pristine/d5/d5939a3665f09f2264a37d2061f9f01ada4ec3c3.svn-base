var FormComponent = function() {
	
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }
    	// select2
   	 	$('.select2').select2();

	};
	
	/*
	 * Check code bị trùng khi thêm mới
	 */
	var checkCode = function() {
		$('#code').on('keyup', function(e){
			var code = $(this).val();
			var contextPath = getContext();
			var messageCheckCode = document.getElementById('messageCheckCode');
			if(code != ''){
				$.ajax({
					url: contextPath + '/feature/checkCode',
					contextType: 'application/json',
					method: 'POST',
					data: {
						code: code
					},
					success: function(data) {
						messageCheckCode.style.display = 'block';
						if(data) {
							messageCheckCode.style.color = 'red';
							$('#btnSubmit').attr("disabled","disabled");
							$('#messageCheckCode').html('\u004d\u00e3 \u0063\u006f\u0064\u0065 \u0111\u00e3 \u0074\u1ed3\u006e \u0074\u1ea1\u0069')
						} else {
							console.log(document.getElementById('code').value.length)
							if(document.getElementById('code').value.length != 0){
								messageCheckCode.style.color = 'green';
								$('#btnSubmit').removeAttr('disabled');
								$('#messageCheckCode').html('\u004d\u00e3 \u0063\u006f\u0064\u0065 \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
							} else {
								messageCheckCode.style.color = 'red';
								$('#btnSubmit').attr("disabled","disabled");
								$('#messageCheckCode').html('\u004d\u00e3 \u0063\u006f\u0064\u0065 \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
							}
							
						}
					},
					error: function(err) {
						console.log(err)
					}
				});
			} else {
				messageCheckCode.style.color = 'red';
				$('#messageCheckCode').html('\u0059\u00ea\u0075 \u0063\u1ea7\u0075 \u006e\u0068\u1ead\u0070 \u006d\u00e3 \u0063\u006f\u0064\u0065')
			}
		});
	};
	
	var validateForm = function(){
		$('#featureForm').validate({
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
				"code": {
					required: true,
					maxlength: 50,
				},
				"nameVn": {
					required: true,
					maxlength: 512,
				},
				"nameEn": {
					required :true,
					maxlength: 512,
				}
			},
			messages: {
				"code": {
					required: "Bắt buộc nhập mã nhóm feature",
					maxlength: "Hãy nhập tối đa 50 ký tự",
				},
				"nameVn": {
					required: "Bắt buộc nhập tên tiếng Việt",
					maxlength: "Chỉ nhập tối đa 512 ký tự",
				},
				"nameEn": {
					required: "Bắt buộc nhập tên tiếng Anh",
					maxlength: "Chỉ nhập tối đa 512 ký tự",
				}
			}
		});
	}
	
    return {
        init: function() {
        	initForm();
        	checkCode();
        	validateForm();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});
