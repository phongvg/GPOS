var kitchenTypeForm = function() {
	// Validate form
	var validateForm = function(){
		$('#kitchenTypeForm').validate({
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
				"name": {
					required: true,
					maxlength: 100,
				}
			},
			messages: {
				"name": {
					required: "Bắt buộc nhập tên kiểu bếp",
					maxlength: "Hãy nhập tối đa 100 ký tự",
				}
			}
		});
	}
	
	return {
        init: function() {
        	validateForm();
        }
    }
}();
document.addEventListener('DOMContentLoaded', function() {
	kitchenTypeForm.init();
});

