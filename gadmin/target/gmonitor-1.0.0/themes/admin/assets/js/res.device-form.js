var deviceComponent= function() {
	var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();

	}
	
    // Uniform
    var initUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Default initialization
        $('.form-check-input-styled').uniform();


        //
        // Contextual colors
        //

        // Primary
        $('.form-check-input-styled-primary').uniform({
            wrapperClass: 'border-primary-600 text-primary-800'
        });

        // Danger
        $('.form-check-input-styled-danger').uniform({
            wrapperClass: 'border-danger-600 text-danger-800'
        });

        // Success
        $('.form-check-input-styled-success').uniform({
            wrapperClass: 'border-success-600 text-success-800'
        });

        // Warning
        $('.form-check-input-styled-warning').uniform({
            wrapperClass: 'border-warning-600 text-warning-800'
        });

        // Info
        $('.form-check-input-styled-info').uniform({
            wrapperClass: 'border-info-600 text-info-800'
        });

        // Custom color
        $('.form-check-input-styled-custom').uniform({
            wrapperClass: 'border-indigo-600 text-indigo-800'
        });
    };

    // Switchery
    var initSwitchery = function() {
        if (typeof Switchery == 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize multiple switches
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-check-input-switchery'));
        elems.forEach(function(html) {
          var switchery = new Switchery(html);
        });
    };

    // Bootstrap switch
    var initBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-check-input-switch').bootstrapSwitch();
    };

    // Bootbox extension
    var initBootbox = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    };
    
    
    var initSelector = function() {
    	DeviceSelected.init();
    	DeviceInTableSelected.init();
    }

    var submitForm = function(){
    	$('#btnSubmitForm').on('click', function(){
    		var restaurantDeviceSelected = DeviceSelected.getDeviceIds();
    		var tableIdSelected = DeviceInTableSelected.getTableIds();
    		var deviceInTableSelected = DeviceInTableSelected.getDeviceIds();
    		$('#restaurantDeviceSelected').val(restaurantDeviceSelected);
    		$('#tableIdSelected').val(tableIdSelected);
    		$('#deviceInTableSelected').val(deviceInTableSelected);
			$('#deviceForm').submit();
		})
    }
    
    var changeHallPlan = function(){
    	$("#hallplan").on('change',function(){
    		var hallplanId = $("#hallplan").val();
    		$("#table-kitchen").html("");
    		$.ajax({
    			url: getContext() + '/api/device/load-table',
    			method:'GET',
    			data : {hallplanId : hallplanId},
    			success: function(data) {
    				let str = "";
    				data.forEach((item) => {
    					str += "<option value='" + item.id + "'>" + item.code + " - " + item.name + "</option>";
    				});
    				$("#table-kitchen").html(str);
    			},
    			error: function (e) {
    				console.log("error: ",e);
    			}
    		});
    	})
    }
    
    return {
        init: function() {
        	initBootbox();
        	initBootstrapSwitch();
        	initForm();
        	initUniform();
        	initSwitchery();
        	submitForm();
        	initSelector();
        	changeHallPlan();
        }
    }
}();


var Device = {
	loadExistingDevice: () => {
		var resCode = $("#resCode").val();
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
					}else{
						var newRowTable = DeviceInTableSelected.createRow(i,data[i].tableKitchenText,data[i].tableKitchenId, data[i].deviceId);
						newRowTable.appendTo('#tblDeviceInTableSelected tbody');	
					}
				}
			},
			error: function (e) {
				console.log("error: ",e);
			}
		});
	},
	
}

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	deviceComponent.init();
	Device.loadExistingDevice();
});