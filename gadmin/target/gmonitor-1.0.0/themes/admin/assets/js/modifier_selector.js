var ModifierSelector = {
	init: () => {
   	 	const contextPath = getContext();
   	    $('.select2-modifiers').select2({
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
   	    
	   	 $("#btn-seclect-modifier").on("click", (e) => {
    		var value = $(".select2-modifiers").val();
    		var type = $('#modifierType').val();
    		var numberOfChili = $('#modifierNumberOfChili').val();
    		if (value && value.length > 0) {
    			for(i = 0; i < value.length ; i++){
        			var arr = value[i].split("-");
        			var modifierName = arr[2];
        			var modifierCode = arr[1];
        			var modifierId = arr[0];
        			if(modifierId != null){
						var index = 0;
						if(parseInt($("#tblSelectedModifiers").find(".index-table").last().val())){
							index = parseInt($("#tblSelectedModifiers").find(".index-table").last().val());
						}
						if(numberOfChili){
							if(parseInt(numberOfChili) > 0 && parseInt(numberOfChili) <= 10){
								var newRow = ModifierSelector.createRow(index, modifierId, modifierCode, modifierName, type, numberOfChili);
            					newRow.appendTo('#tblSelectedModifiers tbody');	
							}
						} else {
							var newRow = ModifierSelector.createRow(index, modifierId, modifierCode, modifierName, type, null);
            				newRow.appendTo('#tblSelectedModifiers tbody');	
						}
        			}
        		}
    		}
    		ModifierSelector.clearSelect2Modifer();
    		$('#modifierNumberOfChili').val("");
	   	 });
	},
		
	createRow: (index, modifierId, modifierCode, modifierName, type, numberOfChili) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-fi-modifier'+counter+'">');
        var cols = "";
        var typeDisplay = "";
        if(type == '0') {
        	typeDisplay = "Loại thường";
		} else {
			typeDisplay = "Loại cay";
		}
		if(numberOfChili == null) {
        	numberOfChili = "";
		}
		
        
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+modifierCode+'<input type="hidden" class="form-control" name="coFoodItemModifiers['+counter+'].modifierId" id="modifierId'+counter+'" value="'+modifierId+'" readonly="readonly"></td>';
        cols += '<td>'+modifierName+'<input type="hidden" class="form-control" name="coFoodItemModifiers['+counter+'].type" id="type'+counter+'" value="'+type+'" readonly="readonly"></td>';
        cols += '<td>'+typeDisplay+'<input type="hidden" class="form-control" name="coFoodItemModifiers['+counter+'].numberOfChili" id="numberOfChili'+counter+'" value="'+numberOfChili+'" readonly="readonly"></td>';
        cols += '<td>'+numberOfChili+'<input type="hidden" class="index-table" value="'+ counter +'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:ModifierSelector.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	removeRow: (index) => {
		$('#rec-fi-modifier'+index).remove();
		
    	$('#tblSelectedModifers > tbody > tr').each(function(ind){
    		var counter = ind + 1;
    		$(this).attr("id", "rec-fi-modifier" + counter);
    		$(this).find('span.no').html(counter);
    		$(this).find('a.delete-record').attr('href', 'javascript:ModifierSelector.removeRow('+counter+')');
    	});
		
	},
	
	clear: function() {
		$('.select2-modifiers').html("");
		$('#tblSelectedModifers tbody').html("");
	},
	
	clearSelect2Modifer: function() {
		$('.select2-modifiers').html("");
	},
	
	getSelectedModifiers: function() {
		let selectedModifiers = "";
		var hiddenElements = $("#tblSelectedModifiers").find(":hidden");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			
			selectedModifiers = s.substring(0, s.length - 1);
		}
		return selectedModifiers;
	}, 
	
	loadExistingModifiers: function(coFoodItemId) {
		let coId = "";
		if (coFoodItemId) {
			coId = coFoodItemId;
		}
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/co/get-existing-modifiers',
			method:'GET',
			data:{coId : coId},
			success: function(data) {
				for (i=0; i<data.length; i++) {
					var numberOfChili = "";
					if(data[i].numberOfChili != null) {
						numberOfChili = data[i].numberOfChili;
					}
					var newRow = ModifierSelector.createRow(i, data[i].modifierId, data[i].modifierCode, data[i].modifierName, data[i].type, numberOfChili);
					newRow.appendTo('#tblSelectedModifiers tbody');	
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	},
}