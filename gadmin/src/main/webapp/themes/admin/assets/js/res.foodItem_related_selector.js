var fItemCodes = [];
var FoodItemRelatedSelector = {
	init: () => {
   	 	const contextPath = getContext();
   	    $('.select2-related-items').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
   	            type: 'GET',
   	            dataType: 'json',
   	            data: function (params) {
   	                return {
   	                    q: params.term,
   	                };
   	            },
   	            processResults: function (data, params) {
   	                return {
   	                    results: $.map(data, function (item) {
   	                    	var sapCode = '';
   	                    	if(item.sapCode){
   	                    		sapCode = item.sapCode + "-"
   	                    	}
   	                    	return {
   	                            text: sapCode + item.code + ": " + item.name,
   	                            id: item.id + "-" + item.code + "-" + item.name + "-" + sapCode,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
   	    
	   	 $("#btn-seclect-related-items").on("click", (e) => {
    		var value = $(".select2-related-items").val();
    		var selectItem = $('#selectItem').val();
    		console.log("value :" + value );
    		if (value && value.length > 0) {
    			for(i = 0; i < value.length ; i++){
        			var size = $('#tblSelectedFoodItemRelateds tbody tr').length;
        			var arr = value[i].split("-");
        			var itemName = arr[2];
        			var itemCode = arr[1];
        			var itemId = arr[0];
        			var sapCode = '';
        			if(arr[3]){
        				sapCode = arr[3]
        			}
        			if(itemId != null && itemId != selectItem && (!fItemCodes.includes(itemCode) || fItemCodes.length == 0)){
        				fItemCodes.push(itemCode);
        				var newRow = FoodItemRelatedSelector.createRow(size, itemCode, itemName,sapCode);
            			newRow.appendTo('#tblSelectedFoodItemRelateds tbody');	
        			}
        		}
    		}
    		FoodItemRelatedSelector.clearSelect2FoodItemRelated();
	   	 });
	},
		
	createRow: (index, itemCode, itemName,sapCode) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-fi-re'+counter+'">');
        var cols = "";
        
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+sapCode+'</td>';
        cols += '<td>'+itemCode+'<input type="hidden" class="selected-item-code" value="'+itemCode+'"></td>';
        cols += '<td>'+itemName+'<input type="hidden" class="selected-item-name" value="'+itemName+'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodItemRelatedSelector.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	removeRow: (index) => {
		$('#rec-fi-re'+index).remove();
		
    	$('#tblSelectedFoodItemRelateds > tbody > tr').each(function(ind){
    		var counter = ind + 1;
    		$(this).attr("id", "rec-fi-re" + counter);
    		$(this).find('span.no').html(counter);
    		$(this).find('a.delete-record').attr('href', 'javascript:FoodItemRelatedSelector.removeRow('+counter+')');
    	});
		
	},
	
	clear: function() {
		$('.select2-related-items').html("");
		$('#tblSelectedFoodItemRelateds tbody').html("");
	},
	
	clearSelect2FoodItemRelated: function() {
		$('.select2-related-items').html("");
	},
	
	getSelectedFoodItemRelatedCode: function() {
		let selectedFoodItemSize = "";
		var hiddenElements = $("#tblSelectedFoodItemRelateds").find(".selected-item-code");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			selectedFoodItemSize = s.substring(0, s.length - 1);
		}
		return selectedFoodItemSize;
	}, 
	
	getSelectedFoodItemRelatedName: function() {
		let selectedFoodItemSize = "";
		var hiddenElements = $("#tblSelectedFoodItemRelateds").find(".selected-item-name");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			selectedFoodItemSize = s.substring(0, s.length - 1);
		}
		return selectedFoodItemSize;
	}, 
	
	loadExistingFoodItemRelateds: function(coFoodItemId) {
		let coId = "";
		if (coFoodItemId) {
			coId = coFoodItemId;
		}
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/co/get-existing-related',
			method:'GET',
			data:{coId : coId},
			success: function(data) {
				for (i=0; i<data.length; i++) {
					var sapCode = "";
					if(data[i].sapCode){
						sapCode = data[i].sapCode;
					}
					fItemCodes.push(data[i].foodItemCode);
					var newRow = FoodItemRelatedSelector.createRow(i, data[i].foodItemCode, data[i].foodItemName,sapCode);
					newRow.appendTo('#tblSelectedFoodItemRelateds tbody');	
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	},
}