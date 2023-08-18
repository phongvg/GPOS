
var ParamComponent = function() {
	
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }
    	// select2
   	 	$('.select2').select2();

	}

    var initBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-check-input-switch').bootstrapSwitch();
    };

	var validateForm = function(){
		$('#paramForm').validate({
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
				"description":{
					maxlength: 512,
				},
				"name":{
					required: true,
					maxlength: 256,
				},
				"paramValue":{
					required: true,
					maxlength: 100000,
				},
				"paramCode":{
					required: true,
					maxlength: 255,
				},
			},
			messages: {
				"name":{
					required: "Không được bỏ trống trường này",
					maxlength: "chỉ nhập tối đa 256 kí tự",
				},
				"description":{
					maxlength: "Mô tả nhập tối đa 512 kí tự",
				},
				"paramValue":{
					required: "Không được để trống mục này",
					maxlength: "Giá trị chỉ được nhập tối đa 1000 kí tự",
				},
				"paramCode":{
					required: "Không được để trống mục này",
					maxlength: "Code chỉ được nhập tối đa 255 kí tự",
				},
			}
		});
	}
	
	
    return {
        init: function() {
        	initForm();
            initBootstrapSwitch();
            validateForm();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	ParamComponent.init();
});
