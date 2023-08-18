
var OrgComponent = function() {
	
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();

	}

    //
    // Return objects assigned to module
    //
    return {
        init: function() {
        	initForm();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	OrgComponent.init();
});
