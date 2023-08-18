var DeviceInTableSelected = {
	init: () => {
		$("#btn-add-device-in-table").on("click", (e) => {
			var deviceId = $("#deviceIdInTable").val();
			var tableKitchen = $('#table-kitchen').val();
			var tableKitchenText = $('#table-kitchen option:selected').text();
    		console.log("value :" + deviceId );
    		var size = $('#tblDeviceInTableSelected tbody tr').length;
    		var selectedDevice = DeviceInTableSelected.getDevices();
    		var selectedTable = DeviceInTableSelected.getTables();
    		if(deviceId != null && deviceId != ''){
    			if(tableKitchen != null && tableKitchen != ''){
    				if((jQuery.inArray(tableKitchen,selectedTable)) !== -1){
    					bootbox.alert({
    					    title: "Thông báo:",
    					    message: "Bàn này đã được thêm thiết bị. Vui lòng chọn bàn khác để thêm thiết bị.",
    					});
    				}else{
    					if((jQuery.inArray(deviceId,selectedDevice)) !== -1){
            				bootbox.alert({
        					    title: "Thông báo:",
        					    message: "Id thiết bị đang được sử dụng cho nhà hàng. Vui lòng nhập id khác.",
        					});
            			}else{
            				$.ajax({
                				url: getContext() + '/device/check-deviceId',
                				contextType: 'application/json',
                				method: 'GET',
                				data: {
                					deviceId: deviceId,
                					tableId: tableKitchen
                				},
                				success: function(data) {
                					if(data.check){
                						bootbox.alert({
                						    title: "Thông báo:",
                						    message: "Id thiết bị đã được sử dụng cho nhà hàng "+ data.restaurantCode + ". Vui lòng nhập id khác.",
                						});
                					}else{
                						var newRow = DeviceInTableSelected.createRow(size,tableKitchenText,tableKitchen,deviceId);
                    					newRow.appendTo('#tblDeviceInTableSelected tbody');	
                    					DeviceInTableSelected.clear();
                					}
                					
                				},
                				error: function(err) {
                					console.log(err);
                					bootbox.alert({
            						    title: "Thông báo:",
            						    message: "Id thiết bị phải là số.",
            						});
                				}
                			});
            			}
    				}
    				
    			}else{
    				bootbox.alert({
					    title: "Thông báo:",
					    message: "Chưa chọn bàn để thêm thiết bị. Vui lòng chọn bàn để thêm thiết bị cho bàn.",
					});
    			}
    		}else{
    			bootbox.alert({
				    title: "Thông báo:",
				    message: "Bạn chưa nhập id cho thiết bị. Vui lòng nhập id.",
				});  
    		}
	   	 });
		
	},
	
	getDevices: function() {
		var selectedDeviceIds = [];
		var hiddenElements = $("#tblDeviceInTableSelected").find(".selected-table-device-id");
		if (hiddenElements.length > 0) {
			$.each(hiddenElements, function (i, obj) {
				selectedDeviceIds.push(obj.value);
			});
		}
		return selectedDeviceIds;
	},
	
	getTables: function() {
		let selectedTableIds = [];
		var hiddenElements = $("#tblDeviceInTableSelected").find(".selected-table-id");
		if (hiddenElements.length > 0) {
			$.each(hiddenElements, function (i, obj) {
				selectedTableIds.push(obj.value);
			});
			
		}
		return selectedTableIds;
	}, 
	
	createRow: (index,tableText ,tableId, deviceId) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-table-'+counter+'">');
        var cols = "";
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+tableText+'<input type="hidden" class="selected-table-id" value="'+ tableId +'"></td>';
        cols += '<td>'+deviceId+'<input type="hidden" class="selected-table-device-id" value="'+ deviceId +'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:DeviceInTableSelected.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	removeRow: (index) => {
		$('#rec-table-'+index).remove();
		
    	$('#tblDeviceInTableSelected > tbody > tr').each(function(ind){
    		var counter = ind + 1;
    		$(this).attr("id", "rec-table-" + counter);
    		$(this).find('span.no').html(counter);
    		$(this).find('a.delete-record').attr('href', 'javascript:DeviceInTableSelected.removeRow('+counter+')');
    	});
		
	},
	
	/*loadExistingDeviceInTable: function(resCode) {
		var contextPath = getContext();
		if(coFoodItemId){
			$.ajax({
				url: contextPath + '/api/device/get-existing-device-id',
				method:'GET',
				data:{resCode : resCode},
				success: function(data) {
					for(i=0; i<data.length; i++) {
						if(data[i].tableKitchenId != null){
							var newRow = DeviceInTableSelected.createRow(i,data[i].tableKitchenId, data[i].deviceId);
							newRow.appendTo('#tblDeviceInTableSelected tbody');	
						}
					}
				},
				error: function (e) {
					console.log("error: ",e);
				}
			});
		}
		
	},*/
	
	clear: function() {
		$('#deviceIdInTable').val('');
	},
	
	getTableIds: function() {
		let selectedTableIds = "";
		var hiddenElements = $("#tblDeviceInTableSelected").find(".selected-table-id");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			
			selectedTableIds = s.substring(0, s.length - 1);
		}
		return selectedTableIds;
	}, 
	
	getDeviceIds: function() {
		let selectedDeviceIds = "";
		var hiddenElements = $("#tblDeviceInTableSelected").find(".selected-table-device-id");
		if (hiddenElements.length > 0) {
			var s ='';
			$.each(hiddenElements, function (i, obj) {
				s += obj.value + ",";
			});
			
			selectedDeviceIds = s.substring(0, s.length - 1);
		}
		return selectedDeviceIds;
	}, 
	
}