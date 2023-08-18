
var itemForm = function() {
	var _componentCheckCode = function() {
		$('#code').on('keyup', function(e){
			var code = $('#code').val();
			var contextPath = getContext();
			var resCode = $('#restaurantCode').val();
			var messageCheckCode = document.getElementById('messageCheckCode');
			if(code != ''){
				$.ajax({
					url: contextPath + '/kdsPlace/checkCode',
					contextType: 'application/json',
					method: 'POST',
					data: {
						code: code,
						resCode: resCode
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
								$('#messageCheckCode').html('\u0059\u00ea\u0075 \u0063\u1ea7\u0075 \u006e\u0068\u1ead\u0070 \u006d\u00e3 \u0063\u006f\u0064\u0065')
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
	//
	// Validate form
	var validateForm = function(){
		$('#kdsPlaceForm').validate({
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
					maxlength: 100
				},
				"code": {
					required: true,
					maxlength: 100
				},
			},
			messages: {
				"name": {
					required: "Không được bỏ trống trường này",
					maxlength: "Chỉ nhập tối đa 100 kí tự",
				},
				"code": {
					required: "Không được bỏ trống trường này",
					maxlength: "Chỉ nhập tối đa 100 kí tự",
				},
				
			}
		});
	}
    //

    
    
    // Dual Listbox
    var initDualListbox = function() {
        if (!$().bootstrapDualListbox) {
            console.warn('Warning - duallistbox.min.js is not loaded.');
            return;
        }
        
        $('.listbox-hallplan-items').bootstrapDualListbox({
            selectorMinimalHeight: 300,
            moveOnSelect:false
        });
        
    };
    
    
    var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }
    	// select2
   	 	$('.select2').select2();
	}
    
    return {
        init: function() {
        	initForm();
            initDualListbox();
            _componentCheckCode();
            validateForm();
        }
    }
}();

var item = {
		loadHallplans: () => {
			var resCode = $("#restaurantCode").val();
			var id = $("#id").val();
			$(".listbox-hallplan-items").html("");
			$('.listbox-hallplan-items').bootstrapDualListbox('refresh', true);
			$.ajax({
				url: getContext() + '/res/kdsPlace/get-hallplans',
				method:'GET',
				data : {
					id: id,
					resCode: resCode
				},
				success: function(data) {
					let str = "";
					data.forEach((item) => {
						var selected = '';
						if(item.selected){
							selected = 'selected';
						}
						str += "<option value='" + item.id + "'" + selected + ">" + item.name + "</option>";
					});
					
					$(".listbox-hallplan-items").html(str);
					$('.listbox-hallplan-items').bootstrapDualListbox('refresh', true);
			        
			        
				},
				error: function (e) {
					console.log("error: ",e);
				}
			});
		},
		
	}


// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	itemForm.init();
	item.loadHallplans();
});