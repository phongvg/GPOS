var foodItemCodes = [];
var FoodItemSizeSelector = {
	init: () => {
   	 	const contextPath = getContext();
   	 	var resCode = $("#resCode").val();
   	    $('.select2-size-items').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
   	            type: 'GET',
   	            dataType: 'json',
   	            data: function (params) {
   	                return {
   	                    q: params.term,
   	                 	resCode: resCode
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
   	    
	   	 $("#btn-seclect-size-items").on("click", (e) => {
	 		var value = $(".select2-size-items").val();
	 		var itemNameDisplay = $('#nameDisplay').val();
	 		console.log("value :" + value );
	 		if (value && value.length > 0) {
	 			for(i = 0; i < value.length ; i++){
	     			var size = $('#tblSelectedFoodItemSizes tbody tr').length;
	     			var arr = value[i].split("-");
	     			var itemName = arr[2];
	     			var itemCode = arr[1];
	     			var itemId = arr[0];
	     			var sapCode = '';
        			if(arr[3]){
        				sapCode = arr[3]
        			}
	     			if(itemId != null && itemCode != null && (!foodItemCodes.includes(itemCode) || foodItemCodes.length == 0)){
	     				foodItemCodes.push(itemCode);
	     				var newRow = FoodItemSizeSelector.createRow(size, itemCode, itemName,itemNameDisplay,sapCode);
	         			newRow.appendTo('#tblSelectedFoodItemSizes tbody');	
	     			}
	     		}
	 		}
	 		FoodItemSizeSelector.clearSelect2FoodItemSize();
	   	});
	},
		
	createRow: (index,itemCode, itemName,itemNameDisplay,sapCode) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-fi-si'+counter+'">');
        var cols = "";
        
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+sapCode+'<input id="relatedSapCode'+index+'" type="hidden" name="relatedFoodItems['+index+'].sapCode" value="'+sapCode+'"></td>';
        cols += '<td>'+itemCode+'<input id="relatedCode'+index+'" type="hidden" name="relatedFoodItems['+index+'].foodItemCode" value="'+itemCode+'"></td>';
        cols += '<td>'+itemName+'<input id="relatedName'+index+'" type="hidden" name="relatedFoodItems['+index+'].foodItemName" value="'+itemName+'"></td>';
        cols += '<td>'+itemNameDisplay+'<input id="nameDisplay'+index+'" type="hidden" name="relatedFoodItems['+index+'].nameDisplay" value="'+itemNameDisplay+'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodItemSizeSelector.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	deleteRow: (rowIndex,itemCode, itemName,itemNameDisplay, icon,sapCode) => {
		var index = rowIndex - 1;
        var deleteRow = $('#rec-fi-si'+rowIndex)
        var cols = "";

        var spanIcon = '';
        if (icon) {
        	spanIcon = '<span class="badge bg-warning-300 rounded-circle badge-icon"><i class="'+icon+'"></i></span>';
        }

        cols += '<td><span class="no">'+rowIndex+'</span>&nbsp;'+spanIcon+'</td>';
        cols += '<td>'+sapCode+'<input id="relatedSapCode'+index+'" type="hidden" name="relatedFoodItems['+index+'].sapCode" value=""></td>';
        cols += '<td>'+itemCode+'<input id="relatedCode'+index+'" type="hidden" name="relatedFoodItems['+index+'].foodItemCode" value=""></td>';
        cols += '<td>'+itemName+'<input id="relatedName'+index+'" type="hidden" name="relatedFoodItems['+index+'].foodItemName" value=""></td>';
        cols += '<td>'+itemNameDisplay+'<input id="nameDisplay'+index+'" type="hidden" name="relatedFoodItems['+index+'].nameDisplay" value=""></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+rowIndex+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        deleteRow.html(cols);
	},
	
	removeRow: (rowIndex) => {
    	const index = rowIndex - 1;
		const relatedCode = $("#relatedCode"+index).val();
		const relatedName = $("#relatedName"+index).val();
		const nameDisplay = $("#nameDisplay"+index).val();
		const sapCode = $("#relatedSapCode"+index).val();
		const icon = 'icon-cross2';
		FoodItemSizeSelector.deleteRow(rowIndex, relatedCode, relatedName, nameDisplay,icon,sapCode);
	},
	
	
	clear: function() {
		$('.select2-size-items').html("");
		$('#tblSelectedFoodItemSizes tbody').html("");
	},
	
	clearSelect2FoodItemSize: function() {
		$('.select2-size-items').html("");
		$('#nameDisplay').val("");
	},
	
	getSelectedFoodItemSize: function() {
		let selectedFoodItemSize = "";
		var hiddenElements = $("#tblSelectedFoodItemSizes").find(":hidden");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			selectedFoodItemSize = s.substring(0, s.length - 1);
		}
		return selectedFoodItemSize;
	}, 
	
	loadExistingFoodItemSizes: function(coFoodItemId) {
		let coId = "";
		if (coFoodItemId) {
			coId = coFoodItemId;
		}
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/co/get-existing-size-foodItem',
			method:'GET',
			data:{coId : coId},
			success: function(data) {
				for (i=0; i<data.length; i++) {
					var sapCode = "";
					if(data[i].sapCode){
						sapCode = data[i].sapCode;
					}
					foodItemCodes.push(data[i].foodItemCode);
					var newRow = FoodItemSizeSelector.createRow(i,data[i].foodItemCode, data[i].foodItemName, data[i].nameDisplay,sapCode);
					newRow.appendTo('#tblSelectedFoodItemSizes tbody');	
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	},
}