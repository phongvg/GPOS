var ItemComponent = function() {
    // Select2
	var initSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();
	};
    
    var initModal = function() {
    	FoodItemSelector.init();
    }
    
 // Validate form
	var validateForm = function(){
		$('#kdsConfigCookingForm').validate({
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
				"foodItem.id": {
					required: true,
				},
				"kdsPlace.id": {
					required: true,
				},
				"singleCookingTime": {
					number : true,
				},
				"multiCookingTime": {
					number : true,
				},
			},
			messages: {
				"kdsPlace.id": {
					required: "Không được bỏ trống trường này",
				},
				"singleCookingTime": {
					number : "Sing Cooking Time bắt buộc phải là số",
				},
				"multiCookingTime": {
					number : "Multi Cooking Time bắt buộc phải là số",
				},
				"foodItem.id":{
					required: "Không được bỏ trống trường này",
				},
			}
		});
	}

    // Return objects assigned to module
    //
	
    return {
        init: function() {
        	initSelect2();
        	initModal();
        	validateForm();
        }
    }
}();

var FoodItem = {
		load : () =>{
			var id = $('#id').val();
			if(id != null && id != ''){
				$.ajax({
					url: getContext() + '/api/kdsConfigCooking/load-item',
					data : {id : id
					},
					method:'GET',
					success: function(data) {
						var item = "";
						item = "<option></option>";
						if(data.code != null && data.code != ''){
							item = "<option value=" + data.id + ">" + data.code + " - " + data.name +"</option>";
						}
						$(".select2-food-item").html(item);
					},
					error: function (e) {
						console.log("error: ",e);
					}
				});
			}
			
		}
	}
// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	ItemComponent.init();
	FoodItem.load();
});

