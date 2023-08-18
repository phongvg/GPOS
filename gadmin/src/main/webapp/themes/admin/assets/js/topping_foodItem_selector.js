var itemCodes = [];
var FoodItemToppingSelector = {
	init: () => {
   	 	const contextPath = getContext();
   	    $('.select2-topping-items').select2({
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
   	                            data: item,
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
   	    
   	    $('.select2-modifier-items').select2({
	    	minimumInputLength: 1,
	    	minimumResultsForSearch: Infinity,
	    	ajax: {
	            url: contextPath + '/cag/load-modifiers',
	            type: 'GET',
	            dataType: 'json',
	            data: function (params) {
	                return {
	                    q: params.term
	                };
	            },
	            processResults: function (data, params) {
	                return {
	                    results: $.map(data, function (item) {
	                        return {
	                            text: item.code + ": " + item.name,
	                            id: item.id + "-" + item.code + "-" + item.name,
	                            data: item
	                        };
	                    })
	                };
	            }
	        },
	    });
   	    
	   	 $("#btn-seclect-topping-items").on("click", (e) => {
    		var foodItemValue = $(".select2-topping-items").val();
    		var modifierValue = $(".select2-modifier-items").val();
    		if (foodItemValue && foodItemValue.length > 0 && modifierValue && modifierValue.length > 0) {
    			var size = $('#tblSelectedFoodItemToppings tbody tr').length;
    			var arrFoodItem = foodItemValue.split("-");
    			var arrModifier = modifierValue.split("-");
    			var itemFoodItemName = arrFoodItem[2];
    			var itemFoodItemCode = arrFoodItem[1];
    			var itemModifierName = arrModifier[2];
    			var itemModifierCode = arrModifier[1];
    			var sapCode = '';
    			if(arrFoodItem[3]){
    				sapCode = arrFoodItem[3]
    			}
    			if(itemFoodItemName != null && itemFoodItemCode != null && itemModifierName != null && itemModifierCode != null && (!itemCodes.includes(itemFoodItemCode	) || itemCodes.length == 0)){
    				itemCodes.push(itemFoodItemCode);
    				var newRow = FoodItemToppingSelector.createRow(size, itemFoodItemCode, itemFoodItemName,itemModifierCode,itemModifierName,sapCode);
        			newRow.appendTo('#tblSelectedFoodItemToppings tbody');	
    			}
    		}
    		FoodItemToppingSelector.clearSelect2FoodItemTopping();
	   	 });
	},
		
	createRow: (index, itemFoodItemCode, itemFoodItemName,itemModifierCode,itemModifierName,sapCode) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-top'+counter+'">');
        var cols = "";
        
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+sapCode+'<input id="toppingFSapCode'+index+'" type="hidden" name="toppingFoodItems['+index+'].sapCode" value="'+sapCode+'"></td>';
        cols += '<td>'+itemFoodItemCode+'<input id="toppingFCode'+index+'" type="hidden" name="toppingFoodItems['+index+'].foodItemCode" value="'+itemFoodItemCode+'"></td>';
        cols += '<td>'+itemFoodItemName+'<input id="toppingFName'+index+'" type="hidden" name="toppingFoodItems['+index+'].foodItemName" value="'+itemFoodItemName+'"></td>';
        cols += '<td>'+itemModifierCode+'<input id="toppingMCode'+index+'" type="hidden" name="toppingFoodItems['+index+'].modifierCode" value="'+itemModifierCode+'"></td>';
        cols += '<td>'+itemModifierName+'<input id="toppingMName'+index+'" type="hidden" name="toppingFoodItems['+index+'].modifierName" value="'+itemModifierName+'"></td>';
        
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodItemToppingSelector.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	deleteRow: (rowIndex,itemFoodItemCode, itemFoodItemName,itemModifierCode,itemModifierName, icon,toppingFSapCode) => {
		var index = rowIndex - 1;
        var deleteRow = $('#rec-top'+rowIndex)
        var cols = "";

        var spanIcon = '';
        if (icon) {
        	spanIcon = '<span class="badge bg-warning-300 rounded-circle badge-icon"><i class="'+icon+'"></i></span>';
        }

        cols += '<td><span class="no">'+rowIndex+'</span>&nbsp;'+spanIcon+'</td>';
        cols += '<td>'+toppingFSapCode+'<input id="toppingFSapCode'+index+'" type="hidden" name="toppingFoodItems['+index+'].sapCode" value=""></td>';
        cols += '<td>'+itemFoodItemCode+'<input id="toppingFCode'+index+'" type="hidden" name="toppingFoodItems['+index+'].foodItemCode" value=""></td>';
        cols += '<td>'+itemFoodItemName+'<input id="toppingFName'+index+'" type="hidden" name="toppingFoodItems['+index+'].foodItemName" value=""></td>';
        cols += '<td>'+itemModifierCode+'<input id="toppingMCode'+index+'" type="hidden" name="toppingFoodItems['+index+'].modifierCode" value=""></td>';
        cols += '<td>'+itemModifierName+'<input id="toppingMName'+index+'" type="hidden" name="toppingFoodItems['+index+'].modifierName" value=""></td>';
        
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+rowIndex+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        deleteRow.html(cols);
	},
	
	removeRow: (rowIndex) => {
    	const index = rowIndex - 1;
		const toppingFCode = $("#toppingFCode"+index).val();
		const toppingFName = $("#toppingFName"+index).val();
		const toppingMCode = $("#toppingMCode"+index).val();
		const toppingMName = $("#toppingMName"+index).val();
		const toppingFSapCode = $("#toppingFSapCode"+index).val();
		const icon = 'icon-cross2';
		FoodItemToppingSelector.deleteRow(rowIndex, toppingFCode, toppingFName, toppingMCode,toppingMName,icon,toppingFSapCode);
	},
	
	clear: function() {
		$('.select2-topping-items').html("");
		$('.select2-modifier-items').html("");
		$('#tblSelectedFoodItemToppings tbody').html("");
	},
	
	clearSelect2FoodItemTopping: function() {
		$('.select2-topping-items').html("");
		$('.select2-modifier-items').html("");
	},
	
	getSelectedFoodItemSize: function() {
		let selectedFoodItemSize = "";
		var hiddenElements = $("#tblSelectedFoodItemToppings").find(":hidden");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			selectedFoodItemSize = s.substring(0, s.length - 1);
		}
		return selectedFoodItemSize;
	}, 
	
	loadExistingFoodItemToppings: function(cfId) {
		let coId = "";
		if (cfId) {
			coId = cfId;
		}
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/get-existing-topping',
			method:'GET',
			data:{coId : coId},
			success: function(data) {
				for (i=0; i<data.length; i++) {
					if(data[i].modifierCode != null && data[i].foodItemCode != null){
						var sapCode = "";
						if(data[i].sapCode){
							sapCode = data[i].sapCode;
						}
						itemCodes.push(data[i].foodItemCode);
						var newRow = FoodItemToppingSelector.createRow(i,data[i].foodItemCode, data[i].foodItemName, data[i].modifierCode, data[i].modifierName,sapCode);
						newRow.appendTo('#tblSelectedFoodItemToppings tbody');	
					}
					
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	},
}