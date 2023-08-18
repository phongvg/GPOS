/* ------------------------------------------------------------------------------
 *
 *  # Transaction Form
 *
 * ---------------------------------------------------------------------------- */


// Setup module
// ------------------------------

var TransactionForm = function() {
	
	//check code transaction
	var _componentCheckCode = function() {
		$('#code').on('change', function(e){
			var code = $(this).val();
			var contextPath = getContext();
			var messageCheckCode = document.getElementById('messageCheckCode');
			
			$.ajax({
				url: contextPath + '/admin/trans/checkCode',
				contextType: 'application/json',
				method: 'POST',
				data: {
					code: code
				},
				success: function(data) {
					messageCheckCode.style.display = 'block';
					if(data) {
						messageCheckCode.style.color = 'red';
						$('#transactionSubmit').attr("disabled","disabled");
						$('#messageCheckCode').html('\u004d\u00e3 \u0067\u0069\u0061\u006f \u0064\u1ecb\u0063\u0068 \u0111\u00e3 \u0074\u1ed3\u006e \u0074\u1ea1\u0069\u0021')
					} else {
						console.log(document.getElementById('code').value.length)
						if(document.getElementById('code').value.length != 0){
							messageCheckCode.style.color = 'green';
							$('#transactionSubmit').removeAttr('disabled');
							$('#messageCheckCode').html('\u004d\u00e3 \u0067\u0069\u0061\u006f \u0064\u1ecb\u0063\u0068 \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
						} else {
							messageCheckCode.style.color = 'red';
							$('#transactionSubmit').attr("disabled","disabled");
							$('#messageCheckCode').html('\u004d\u00e3 \u0067\u0069\u0061\u006f \u0064\u1ecb\u0063\u0068 \u006b\u0068\u00f4\u006e\u0067 \u0070\u0068\u00f9 \u0074\u0068\u00ed\u0063\u0068 \u0068\u1ee3\u0070\u0021')
						}
						
					}
					
				},
				error: function(err) {
					console.log(err)
				}
			});
		});
	};


    //
    // Setup module components
    //

    // Select2
    var _componentSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        };

        // Basic example
        $('.form-control-select2').select2();


        //
        // Select with icons
        //

        // Format icon
        function iconFormat(icon) {
            var originalOption = icon.element;
            if (!icon.id) { return icon.text; }
            var $icon = "<i class='icon-" + $(icon.element).data('icon') + "'></i>" + icon.text;

            return $icon;
        }

        // Initialize with options
        $('.form-control-select2-icons').select2({
            templateResult: iconFormat,
            minimumResultsForSearch: Infinity,
            templateSelection: iconFormat,
            escapeMarkup: function(m) { return m; }
        });
    };

    // Uniform
    var _componentUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-styled').uniform({
            fileButtonClass: 'action btn bg-pink-400'
        });
    };


    // Pickadate
    var _componentPickadate = function() {
        if (!$().pickadate) {
            console.warn('Warning - picker.js and/or picker.date.js is not loaded.');
            return;
        }

        $('.pickadate').pickadate({
        	format: 'dd/mm/yyyy'
        });

    };
    
    // Handle event in form
    var _componentFormEvent = function() {
    	$('#transactionTypeImport').on('click', () => {
    		$("#agentSelectBox").css("display", "none");
    	});
    	
    	$('#transactionTypeExport').on('click', () => {
    		$("#agentSelectBox").css("display", "block");
    	});
    	
    	$('#categorySelectBox').on('change', () => {
    		var categoryId = $("#categorySelectBox").val();
    		var loadProURL = $("#loadProURL").val() + categoryId;
    		$.ajax({
    			url: loadProURL,
    			method: 'GET',
    			//data: {cId : categoryId},
    			success: function(data) {
    				var s ='';
                  	for(i=0; i<data.length; i++) {
                  		s += '<option value=' + data[i].id + '>' + data[i].name + '</option>';
                    }
                  	console.log(s);
                  	$("#productSelectBox").html(s);
    			},
    			error: function(e) {
    				console.log(e);
    			}
    		})
    	});
    	
    	
    	
    	
    	
    	function createRowContent(index, productId, productName, quantity, price, note) {
    		var counter = index + 1;
            var newRow = $('<tr id="rec-'+counter+'">');
            var cols = "";

            cols += '<td class="text-center"><span class="no">'+counter+'</span><input type="hidden" name="transactionDetails['+index+'].product.id" value="'+productId+'"></td>';
            cols += '<td class="text-left">'+productName+'<input type="hidden" name="transactionDetails['+index+'].note" value="'+note+'"></td>';
            cols += '<td class="text-right">'+quantity+'<input type="hidden" class="quantity" name="transactionDetails['+index+'].quantityTotal" value="'+quantity+'"></td>';
            cols += '<td class="text-right">'+Math.round(price/quantity)+'</td>';
            cols += '<td class="text-right">'+price+'<input type="hidden" class="price" name="transactionDetails['+index+'].purchasePriceTotal" value="'+price+'"></td>';
            cols += '<td class="text-center"><div class="list-icons"><a class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" onclick="removeRecord('+counter+')" style="color:#d8201c;"><i class="icon-trash"></i></a></div></td>';
            newRow.append(cols);
            
            return newRow;
    	}
    	
    	$('a.add-record').on('click', (e) => {
    		productId = $('#productSelectBox').val();
    		console.log('productId: ' + productId);
    		productName = $('#productSelectBox option:selected').text();
    		console.log('productName: ' + productName);
    		
    		quantity = ($('#quantityInput').val()).replace(/,/g, "");
    		console.log('quantity: ' + quantity);
    		price = ($('#priceInput').val()).replace(/,/g, "");
    		console.log('price: ' + price);
    		note = $('#noteTextarea').val();
    		console.log('note: ' + note);
    		
    	    var size = $('#transactionDetailTable tbody tr').length;
    		console.log("size: " + size);
    		var newRow = createRowContent(size, productId, productName, quantity, price, note);
    		newRow.appendTo('#transactionDetailTable tbody');
    		
    		// calculate total
    		var quantity = 0;
    		var purchasePrice = 0;
        	$('#transactionDetailTable > tbody > tr').each(function(ind){
        		console.log('ind: ' + ind);
        		console.log($(this).attr('id'));
        		
        		
        		quantity += Number($(this).find('.quantity').val());
        		console.log('-> quantity: ' + quantity);
        		
        		purchasePrice += Number($(this).find('.price').val());
        		console.log('-> purchasePrice: ' + purchasePrice);
        		
        		$(this).find('span.no').html(ind + 1);
        	});
        	
        	console.log('quantity: ' + quantity);
        	console.log('purchasePrice: ' + purchasePrice);
        	
        	$('span#quantityTotal').html(quantity);
        	$('input[name="quantityTotal"]').val(quantity);
        	$('span#purchasePriceTotal').html(purchasePrice);
        	$('input[name="purchasePriceTotal"]').val(purchasePrice);
    	});
    	
    	/*
    	$('table.product-list').on('click', '.delete-record', (e) => {
    		var didConfirm = confirm("Are you sure you want to delete");
    	    if (didConfirm == true) {
    	    	console.log('--> ' + $(this).html());
    	        var inx = $('.delete-record').index(this);
    	        console.log('--> ' + inx);
    	    	
    	    	
    	    	//var id = $(this).attr('data-id');
    	    	//alert('id: ' + id);
    	    	//$('#rec-' + id).remove();
    	      
    	    	//regnerate index number on table
    	    	$('#transactionDetailTable > tbody > tr').each(function(index){
    	    		$(this).find('span.no').html(index + 1);
    	    	});
    	    	return true;
    	    } else {
    	    	return false;
    	    }
    	});
    	*/
    };
    
    
    
    //
    // Return objects assigned to module
    //
    return {
        init: function() {
        	_componentCheckCode();
            _componentSelect2();
            _componentUniform();
            _componentPickadate();
            _componentFormEvent();
        }
    }
}();



// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
    TransactionForm.init();

});

function removeRecord(index) {
	var didConfirm = confirm("Are you sure you want to delete");
    if (didConfirm == true) {
    	console.log('=> '+index);
    	$('#rec-' + index).remove();
      
    	//regnerate index number on table
    	$('#transactionDetailTable > tbody > tr').each(function(ind){
    		$(this).find('span.no').html(ind + 1);
    	});
    	return true;
    } else {
    	return false;
    }
	
	

}
