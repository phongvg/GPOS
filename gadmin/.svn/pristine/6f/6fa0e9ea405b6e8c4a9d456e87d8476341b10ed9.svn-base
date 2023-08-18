
var AuditLogForm = function() {
    
	 // Daterange picker
    var _componentDaterange = function() {
        if (!$().daterangepicker) {
            console.warn('Warning - daterangepicker.js is not loaded.');
            return;
        }
        // Single picker
//        $('.daterange-single').daterangepicker({ 
//            singleDatePicker: true
//        });
        
        // Basic initialization
        $('.daterange-basic').daterangepicker({
            applyClass: 'bg-slate-600',
            cancelClass: 'btn-light',
            locale: {
                format: 'DD/MM/YYYY'
            }
        });
    };
    //
    // Return objects assigned to module
    //
    return {
        init: function() {
        	_componentDaterange();
        }
    }
}();

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	AuditLogForm.init();
});
