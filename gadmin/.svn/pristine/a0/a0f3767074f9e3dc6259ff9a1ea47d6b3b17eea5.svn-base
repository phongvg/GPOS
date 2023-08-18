function downRow(index){
	var idRow = "#rec-fi-" + index;
	var tableRow = $(idRow);
	tableRow.insertAfter(tableRow.next());
};

function upRow(index){
	var idRow = "#rec-fi-" + index;
	var tableRow = $(idRow);
	tableRow.insertBefore(tableRow.prev());
 };

var FoodItemSelector = {
	init: () => {
   	 	const contextPath = getContext();
   	 	var cId = $("#coId").val();
   	 	var resCode = $("#resCode").val();
   	    $('.select2-food-items').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
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
   	                    	var sapCode = '';
   	                    	if(item.sapCode){
   	                    		sapCode = item.sapCode + "-"
   	                    	}
   	                        return {
   	                            text: sapCode + item.code + ": " + item.name,
   	                            id: item.id + "-" + item.code + "-" + item.name + "-" + sapCode  ,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
   	    
	   	 $("#btn-seclect-food-item").on("click", (e) => {
    		var value = $(".select2-food-items").val();
    		var idExistings = FoodItemSelector.getSelectedFoodItems();
    		console.log("value :" + idExistings );
    		if (value && value.length > 0) {
    			for(i = 0; i < value.length ; i++){
        			var size = $('#tblSelectedFoodItems tbody tr').length;
        			var arr = value[i].split("-");
        			var foodItemName = arr[2];
        			var foodItemCode = arr[1];
        			var foodItemId = arr[0];
        			var sapCode = '';
        			if(arr[3]){
        				sapCode = arr[3]
        			}
        			if(foodItemId != null && (!idExistings.includes(foodItemId) || idExistings == '')){
        				var newRow = FoodItemSelector.createRow(size, foodItemId, foodItemCode, foodItemName,sapCode);
            			newRow.appendTo('#tblSelectedFoodItems tbody');	
        			}
        		}
    		}
    		FoodItemSelector.clearSelect2FoodItem();
	   	 });
	   	 
	   	 //========= search adultBufferTicket =======//
	   	$('.select2-items-adult').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
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
   	                    	var sapCode = '';
   	                    	if(item.sapCode){
   	                    		sapCode = item.sapCode + "-"
   	                    	}
   	                        return {
   	                        	text: sapCode + item.code + ": " + item.name,
   	                            id: item.code + "-" + item.name,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
	   	 
	    //========= search kidBufferTicket =======//
	   	$('.select2-items-child').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
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
   	                    	var sapCode = '';
   	                    	if(item.sapCode){
   	                    		sapCode = item.sapCode + "-"
   	                    	}
   	                        return {
   	                        	text: sapCode + item.code + ": " + item.name,
   	                            id: item.code + "-" + item.name,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
	  
	   	//========= search Extra FoodItem =======//
	   	$('.select2-items-extra').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
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
   	                    	var sapCode = '';
   	                    	if(item.sapCode){
   	                    		sapCode = item.sapCode + "-"
   	                    	}
   	                        return {
   	                        	text: sapCode + item.code + ": " + item.name,
   	                            id: item.code + "-" + item.name,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
	  
	    //========= search foodItem in kds config cooking =======//
	   	$('.select2-food-item').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/so/load-food-items',
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
   	                    	var sapCode = '';
   	                    	if(item.sapCode){
   	                    		sapCode = item.sapCode + "-"
   	                    	}
   	                        return {
   	                        	text: sapCode + item.code + ": " + item.name,
   	                            id: item.id,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
	   	
	   	
	  //========= search foodItem in coFoodItem Catalog =======//
	   	$('.select2-co-food-item-cata').select2({
   	    	minimumInputLength: 1,
   	    	minimumResultsForSearch: Infinity,
   	    	ajax: {
   	            url: contextPath + '/cag/co/load-food-items',
   	            type: 'GET',
   	            dataType: 'json',
   	            data: function (params) {
   	                return {
   	                    q: params.term,
   	                    cId:cId
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
   	                        	id: item.id,
   	                            data: item
   	                        };
   	                    })
   	                };
   	            }
   	        },
   	    });
	   	
	   	
	  //========= search foodItem in coFoodItem restaurant =======//
	   	$('.select2-co-food-item').select2({
	    	minimumInputLength: 1,
	    	minimumResultsForSearch: Infinity,
	    	ajax: {
	            url: contextPath + '/res/co/load-food-items',
	            type: 'GET',
	            dataType: 'json',
	            data: function (params) {
	                return {
	                    q: params.term,
	                    resCode:resCode
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
	                            id: item.id,
	                            data: item,
	                        };
	                    })
	                };
	            }
	        },
	    });
	},
	
	createRow: (index, foodItemId, foodItemCode, foodItemName,sapCode) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-fi-'+counter+'">');
        var cols = "";
        cols += '<td><span class="no">'+counter+'</span><input type="hidden" class="selected-food-item-id" value="'+foodItemId+'"></td>';
        cols += '<td>';
        cols += '<div class="list-icons">';
        cols += '<a href="#" onclick="upRow('+counter+')" style="color:#d8201c;"><i class="icon-square-up"></i></a>';
        cols += '<a href="#" onclick="downRow('+counter+')" style="color:#d8201c;"><i class="icon-square-down"></i></a>';
        cols += '</td>';
        cols += '<td>'+sapCode+'</td>';
        cols += '<td>'+foodItemCode+'<input type="hidden" class="selected-food-item-code" value="'+ foodItemCode +'"></td>';
        cols += '<td>'+foodItemName+'<input type="hidden" class="selected-food-item-name" value="'+ foodItemName +'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodItemSelector.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        newRow.append(cols);
        return newRow;
	},
	
	removeRow: (index) => {
		$('#rec-fi-'+index).remove();
		
    	$('#tblSelectedFoodItems > tbody > tr').each(function(ind){
    		var counter = ind + 1;
    		$(this).attr("id", "rec-fi-" + counter);
    		$(this).find('span.no').html(counter);
    		$(this).find('a.delete-record').attr('href', 'javascript:FoodItemSelector.removeRow('+counter+')');
    	});
		
	},
	
	loadExistingFoodItem: function(foodGroupId) {
		let fgId = "";
		if (foodGroupId) {
			fgId = foodGroupId;
		}
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/so/get-existing-food-items',
			method:'GET',
			data:{fgId : fgId},
			success: function(data) {
				for (i=0; i<data.length; i++) {
					var sapCode = "";
					if(data[i].sapCode){
						sapCode = data[i].sapCode;
					}
					var newRow = FoodItemSelector.createRow(i, data[i].id, data[i].code, data[i].name,sapCode);
					newRow.appendTo('#tblSelectedFoodItems tbody');	
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	},
	
	clear: function() {
		$('.select2-food-items').html("");
		$('#tblSelectedFoodItems tbody').html("");
	},
	
	clearSelect2FoodItem: function() {
		$('.select2-food-items').html("");
	},
	
	getSelectedFoodItems: function() {
		let selectedFoodItems = "";
		var hiddenElements = $("#tblSelectedFoodItems").find(".selected-food-item-id");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			
			selectedFoodItems = s.substring(0, s.length - 1);
		}
		return selectedFoodItems;
	}, 
	
	getSelectedFICodes: function() {
		let selectedFoodItems = "";
		var hiddenElements = $("#tblSelectedFoodItems").find(".selected-food-item-code");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			
			selectedFoodItems = s.substring(0, s.length - 1);
		}
		return selectedFoodItems;
	}, 
	
	loadFromLocalStorage: function(selectedFoodItems) {
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/so/get-from-localStorage',
			method:'GET',
			data:{selectedFoodItems : selectedFoodItems},
			success: function(data) {
				for (i=0; i<data.length; i++) {
					var sapCode = "";
					if(data[i].sapCode){
						sapCode = data[i].sapCode;
					}
					var newRow = FoodItemSelector.createRow(i, data[i].id, data[i].code, data[i].name,sapCode);
					newRow.appendTo('#tblSelectedFoodItems tbody');	
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	}
	
	
}