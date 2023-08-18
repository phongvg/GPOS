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
				    message: "Xóa Category này sẽ dẫn đến dữ liệu của một số CO đang áp dụng danh mục SO này bị ảnh hưởng. Bạn có chắc chắn muốn xóa không?",
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

 // Bootbox extension
    var initBootbox = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    };

    //
    // Return objects assigned to module
    //
    return {
        init: function() {
        	initFancytree();
        	initBootbox();
        }
    }
}();

var SoMenu = {
	loadTree: (soCategoryId) => {
        $('#foodGroupItems-'+soCategoryId).fancytree({
            checkbox: false,
            selectMode: 3,
            ajax: {
                type: "GET",
                contentType: "application/json"
            },
            source: {
                 url: getContext() + "/api/so/get-menu-group-items?scId=" + soCategoryId
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
