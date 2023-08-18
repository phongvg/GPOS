var FoodItemInfo = {
	init: () => {
	   	 $("#btn-info-foodItem").on("click", (e) => {
    		var value = $("#infoCoFoodItem").val();
    		console.log("value :" + value );
    		var size = $('#tblSelectedInfoFoodItems tbody tr').length;
    		var newRow = FoodItemInfo.createRow(size,value);
			newRow.appendTo('#tblSelectedInfoFoodItems tbody');	
			FoodItemInfo.clearInfoFoodItem();
	   	 });
	   	 
	},
	
	createRow: (index, value) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-info-'+counter+'">');
        var cols = "";
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+value+'<input type="hidden" class="selected-info-food-item" value="'+ value +'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:FoodItemInfo.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	removeRow: (index) => {
		$('#rec-info-'+index).remove();
		
    	$('#tblSelectedInfoFoodItems > tbody > tr').each(function(ind){
    		var counter = ind + 1;
    		$(this).attr("id", "rec-info-" + counter);
    		$(this).find('span.no').html(counter);
    		$(this).find('a.delete-record').attr('href', 'javascript:FoodItemInfo.removeRow('+counter+')');
    	});
		
	},
	
	loadExistingInfoFoodItems: function(coFoodItemId) {
		var contextPath = getContext();
		if(coFoodItemId){
			$.ajax({
				url: contextPath + '/api/co/get-existing-info-foodItem',
				method:'GET',
				data:{coId : coFoodItemId},
				success: function(data) {
					for (i=0; i<data.length; i++) {
						var newRow = FoodItemInfo.createRow(i, data[i]);
						newRow.appendTo('#tblSelectedInfoFoodItems tbody');	
					}
				},
				error: function (e) {
					console.log("error: ",e);
				}
			});
		}
		
	},
	
	clear: function() {
		$('#infoCoFoodItem').val('');
		$('#tblSelectedInfoFoodItems tbody').html("");
	},
	
	clearInfoFoodItem: function() {
		$('#infoCoFoodItem').val('');
	},
	
	getInfoFoodItems: function() {
		let selectedInfoFoodItems = "";
		var hiddenElements = $("#tblSelectedInfoFoodItems").find(".selected-info-food-item");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + "&&";
			});
			
			selectedInfoFoodItems = s.substring(0, s.length - 2);
		}
		return selectedInfoFoodItems;
	}, 
	
}