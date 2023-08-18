function deleteSoCategory(scId){
	$.ajax({
		url: getContext() + '/api/so/check-so-category-before-del',
		method:'GET',
		data:{
			scId:scId
		},
		success: function(data) {
			if(data) {
				bootbox.confirm({
				    title: "Xác nhận:",
				    message: "Xóa Category này sẽ dẫn đến dữ liệu của Co của nhà hàng này. Bạn có chắc chắn muốn xóa không?",
				    buttons: {
				        cancel: {
				            label: '<i class="fa fa-times"></i> Hủy'
				        },
				        confirm: {
				            label: '<i class="fa fa-check"></i> Thực hiện'
				        }
				    },
				    callback: function (result) {
				        console.log('This was logged in the callback: ' + result);
				        if (result) {
				        	var href = "/so/menu/delete?scId=" + scId;
				        	location.href = href;
				        }
				    }
				});
			} else {
				bootbox.confirm({
				    title: "Xác nhận:",
				    message: "Bạn có chắc chắn muốn xóa không?",
				    buttons: {
				        cancel: {
				            label: '<i class="fa fa-times"></i> Hủy'
				        },
				        confirm: {
				            label: '<i class="fa fa-check"></i> Thực hiện'
				        }
				    },
				    callback: function (result) {
				        console.log('This was logged in the callback: ' + result);
				        if (result) {
				        	var href = "/so/menu/delete?scId=" + scId;
				        	location.href = href;
				        }
				    }
				});
			}
		},
		error: function (e) {
			console.log("error: ", e);
		}
	});
}
var SoListComponent = function() {
	
    var initFancytree = function() {
        if (!$().fancytree) {
            console.warn('Warning - fancytree_all.min.js is not loaded.');
            return;
        }
    };

    // Select2
	var initSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();
	};

	
    // Multiselect
    var initMultiselect = function() {
        if (!$().multiselect) {
            console.warn('Warning - bootstrap-multiselect.js is not loaded.');
            return;
        }

        // Select All and Filtering features
        $('.multiselect-select-all-filtering').multiselect({
            includeSelectAllOption: true,
            enableFiltering: true,
            enableCaseInsensitiveFiltering: true
        });
    };
	
    var initApply = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    	
        $("#btnApplySo").on("click", (e) => {
			bootbox.dialog({
				title: 'Xác nhận',
				message: 'Bạn chọn hình thức ghi đè hay update dữ liệu?',
				buttons: {
					cancel: {
						label: 'Hủy',
						//className: 'btn-danger'
					},
					update: {
						label: 'Update',
						className: 'btn-primary',
						callback: function() {
							$('#isApplyOverride').val(false);
							$('#applySoForm').submit();
							$('#pleaseWaitDialog').modal();
						}
					},
					full: {
						label: 'Ghi đè',
						className: 'btn-primary',
						callback: function() {
							$('#isApplyOverride').val(true);
							$('#applySoForm').submit();
							$('#pleaseWaitDialog').modal();
						}
					},
				},
			});
        });
    }

    var initCopy = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    	
        $("#btnCopyToOthers").on("click", (e) => {
        	var resCodes = $('#resCodeSelected').val();
        	console.log("resCodes :" +resCodes);
        	if(resCodes == ''){
        		bootbox.alert({
                    title: "Thông báo:",
                    message: "Bạn chưa chọn nhà hàng. Vui lòng chọn nhà hàng để copy.",
                }); 
        	} else {
        		bootbox.dialog({
    				title: 'Xác nhận',
    				message: 'Bạn chọn hình thức ghi đè hay update dữ liệu?',
    				buttons: {
    					cancel: {
    						label: 'Hủy',
    						//className: 'btn-danger'
    					},
    					update: {
    						label: 'Update',
    						className: 'btn-primary',
    						callback: function() {
    							$('#isCopyOverride').val(false);
    							$('#copyToOthersForm').submit();
    							$('#pleaseWaitDialog').modal();
    						}
    					},
    					full: {
    						label: 'Ghi đè',
    						className: 'btn-primary',
    						callback: function() {
    							$('#isCopyOverride').val(true);
    							$('#copyToOthersForm').submit();
    							$('#pleaseWaitDialog').modal();
    						}
    					},
    				},
    			});
        	}
        });
    }
    
    // Return objects assigned to module
    return {
        init: function() {
        	initFancytree();
        	initSelect2();
        	initMultiselect();
        	initApply();
        	initCopy();
        }
    }
}();

var SoMenu = {
	loadTree: (soCategoryId, restaurantCode) => {
        $('#foodGroupItems-'+soCategoryId).fancytree({
            checkbox: false,
            selectMode: 3,
            ajax: {
                type: "GET",
                contentType: "application/json"
            },
            source: {
                 url: getContext() + "/api/so/get-menu-group-items?scId=" + soCategoryId + "&resCode=" + restaurantCode
            },
            select: function(event, data) {             
                var selNodes = data.tree.getSelectedNodes();
                var selKeys = $.map(selNodes, function(node) {
                	//alert("[" + node.key + "]: '" + node.title + "'");
                    //return "[" + node.key + "]: '" + node.title + "'";
                	return node.key;
                });
                //$("#selectedRestaurantCodes").val(selKeys);
            }
        });
	}
}

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	SoListComponent.init();
});
