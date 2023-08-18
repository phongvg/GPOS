var PrintGroupComponent = function() {
    // Dual Listbox
    var initDualListbox = function() {
        if (!$().bootstrapDualListbox) {
            console.warn('Warning - duallistbox.min.js is not loaded.');
            return;
        }
        
        $('.listbox-food-items').bootstrapDualListbox({
            selectorMinimalHeight: 300,
            moveOnSelect:false
        });
        
    };
    
    var submitForm = function() {
    	$('#btnSubmit').on('click', function() {
    		const selectedFoodItems = FoodItemSelector.getSelectedFoodItems();
    		$('#foodItemIds').val(selectedFoodItems);
    		$('#printGroupForm').submit();
    	})
    }
    
    return {
        init: function() {
        	initDualListbox();
        	submitForm();
        	FoodItemSelector.init();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
	PrintGroupComponent.init();
});