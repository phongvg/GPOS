/* ------------------------------------------------------------------------------
 *
 *  # Sale Form
 *
 * ---------------------------------------------------------------------------- */


// Setup module
// ------------------------------

var SaleForm = function() {

    //
    // Setup module components
    //
    
    // Handle event in form
    var _componentFormEvent = function() {

    	function createRowContent(index) {
    		var counter = index + 1;
            var newRow = $('<tr id="rec-'+counter+'">');
            var cols = "";
            
            cols += '<td class="text-center"><span class="no">'+counter+'</span></td>';
            cols += '<td class="text-center"><span class="level">'+counter+'</span><input type="hidden" id="saleLevel" name="sales['+index+'].level" value="'+counter+'"></td>';
            cols += '<td><input type="text" class="form-control form-control-sm number" name="sales['+index+'].quantity" value="" required="required"></td>';
            cols += '<td><input type="text" class="form-control form-control-sm" name="sales['+index+'].unit" value="kg" readonly="readonly"></td>';
            cols += '<td><input type="text" class="form-control form-control-sm currency" name="sales['+index+'].curPrice" value="" required="required"></td>';
            cols += '<td><input type="text" class="form-control form-control-sm currency" name="sales['+index+'].oldPrice" value="" required="required"></td>';
            cols += '<td><div class="list-icons"><a href="#" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" onclick="removeRecord('+counter+')"><i class="icon-trash"></i></a></div></td>';
            
            newRow.append(cols);
            return newRow;
    	}
    	
    	$('a.add-record').on('click', (e) => {
    	    var size = $('#saleTable tbody tr').length;
    		console.log("size: " + size);
    		var newRow = createRowContent(size);
    		newRow.appendTo('#saleTable tbody');
    		formatNumberComponent.initAutoNumeric();
    	});
    };
    
    //
    // Return objects assigned to module
    //
    return {
        init: function() {
            _componentFormEvent();
    	}
    }
}();



// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	SaleForm.init();

});

function removeRecord(index) {
	var didConfirm = confirm("Are you sure you want to delete");
    if (didConfirm == true) {
    	console.log('=> '+index);
    	$('#rec-' + index).remove();
      
    	//regnerate index number on table
    	$('#saleTable > tbody > tr').each(function(ind){
    		$(this).find('span.no').html(ind + 1);
    		$(this).find('span.level').html(ind + 1);
    		$(this).find('input#saleLevel').val(ind + 1);
    	});
    	return true;
    } else {
    	return false;
    }
}
