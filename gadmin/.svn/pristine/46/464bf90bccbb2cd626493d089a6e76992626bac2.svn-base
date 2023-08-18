var FormComponent = function() {

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
					url: contextPath + '/menuType/checkCode',
					contextType: 'application/json',
					method: 'POST',
					data: {
						code: code
					},
					success: function(data) {
						messageCheckCode.style.display = 'block';
						if(data) {
							messageCheckCode.style.color = 'red';
							$('#menuTypeSubmit').attr("disabled","disabled");
							$('#messageCheckCode').html('\u004d\u00e3 \u0063\u006f\u0064\u0065 \u0111\u00e3 \u0074\u1ed3\u006e \u0074\u1ea1\u0069')
						} else {
							console.log(document.getElementById('code').value.length)
							if(document.getElementById('code').value.length != 0){
								messageCheckCode.style.color = 'green';
								$('#menuTypeSubmit').removeAttr('disabled');
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
		$('#menuTypeForm').validate({
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
		        if (element.parents().hasClass('form-check')) {
		            error.appendTo( element.parents('.form-check').parent() );
		        }
		        else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
		            error.appendTo( element.parent() );
		        }
		        else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
		            error.appendTo( element.parent().parent() );
		        }
		        else {
		            error.insertAfter(element);
		        }
		    },
			rules: {
				"code": {
					number : true,
					required: true,
					maxlength: 5,
				},
				"name": {
					required: true,
					maxlength: 256,
				}
			},
			messages: {
				"code": {
					required: "Bắt buộc nhập tên code",
					number : "Code phải là số",
					maxlength: "Hãy nhập tối đa 5 ký tự",
				},
				"name": {
					required: "Bắt buộc nhập tên Group Type",
					maxlength: "Chỉ nhập tối đa 256 ký tự",
				}
			}
		});
	}
	
	return {
        init: function() {
        	checkCode();
        	validateForm();
        }
    }
}();


document.addEventListener('DOMContentLoaded', function() {
    FormComponent.init();
});

