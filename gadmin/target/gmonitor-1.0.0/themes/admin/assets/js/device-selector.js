var DeviceSelected = {
	init: () => {
		$("#btn-add-device").on("click", (e) => {
			var deviceId = $("#deviceId").val();
    		console.log("value :" + deviceId );
    		var size = $('#tblDeviceSelected tbody tr').length;
    		var selectedDevice = DeviceSelected.getDevices();
    		if(deviceId != null && deviceId != ''){
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
        					deviceId: deviceId
        				},
        				success: function(data) {
        					if(data.check){
        						bootbox.alert({
        						    title: "Thông báo:",
        						    message: "Id thiết bị đã được sử dụng cho nhà hàng "+ data.restaurantCode + ". Vui lòng nhập id khác.",
        						});
        					}else{
        						var newRow = DeviceSelected.createRow(size,deviceId);
            					newRow.appendTo('#tblDeviceSelected tbody');	
            					DeviceSelected.clear();
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
		var hiddenElements = $("#tblDeviceSelected").find(".selected-device-id");
		if (hiddenElements.length > 0) {
			$.each(hiddenElements, function (i, obj) {
				selectedDeviceIds.push(obj.value);
			});
		}
		return selectedDeviceIds;
	},
	
	createRow: (index, value) => {
		var counter = index + 1;
        var newRow = $('<tr id="rec-info-'+counter+'">');
        var cols = "";
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>'+value+'<input type="hidden" class="selected-device-id" value="'+ value +'"></td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript:DeviceSelected.removeRow('+counter+')" class="delete-record list-icons-item text-danger-600" title="Remove" data-id="'+counter+'" style="color:#d8201c;"><i class="icon-trash"></i></a>';
        cols += '</td>';
        
        newRow.append(cols);
        return newRow;
	},
	
	removeRow: (index) => {
		$('#rec-info-'+index).remove();
		
    	$('#tblDeviceSelected > tbody > tr').each(function(ind){
    		var counter = ind + 1;
    		$(this).attr("id", "rec-info-" + counter);
    		$(this).find('span.no').html(counter);
    		$(this).find('a.delete-record').attr('href', 'javascript:DeviceSelected.removeRow('+counter+')');
    	});
		
	},
	
	/*loadExistingDeviceId: function(resCode) {
		var contextPath = getContext();
		$.ajax({
			url: contextPath + '/api/device/get-existing-device-id',
			method:'GET',
			data:{resCode : resCode},
			success: function(data) {
				for(i=0; i<data.length; i++) {
					if(data[i].tableKitchenId == null){
						var newRow = DeviceSelected.createRow(i, data[i].deviceId);
						newRow.appendTo('#tblDeviceSelected tbody');	
					}
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
		
	},*/
	
	clear: function() {
		$('#deviceId').val('');
	},
	
	getDeviceIds: function() {
		let selectedDeviceIds = "";
		var hiddenElements = $("#tblDeviceSelected").find(".selected-device-id");
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