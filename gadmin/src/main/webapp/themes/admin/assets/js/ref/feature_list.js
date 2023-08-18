var FormComponent = function() {
    var initForm = function() {
        if (!$().select2) {
            return;
        }
    	// select2
   	 	$('.select2').select2();
    };

    var search = function() {
        $('#btnSubmit').on('click', function() {
            $('#page').val(0);
			$('#featureForm').submit();
        });
    }

    return {
        init: function() {
        	initForm();
            search();
        }
    }
}();


document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});