function convertKeyword(str) {
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g,"a"); 
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g,"e"); 
    str = str.replace(/ì|í|ị|ỉ|ĩ/g,"i"); 
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g,"o"); 
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g,"u"); 
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g,"y"); 
    str = str.replace(/đ/g,"d");
    str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "A");
    str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "E");
    str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "I");
    str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "O");
    str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "U");
    str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "Y");
    str = str.replace(/Đ/g, "D");
    // Some system encode vietnamese combining accent as individual utf-8 characters
    str = str.replace(/\u0300|\u0301|\u0303|\u0309|\u0323/g, ""); // ̀ ́ ̃ ̉ ̣  huyền, sắc, ngã, hỏi, nặng
    str = str.replace(/\u02C6|\u0306|\u031B/g, ""); // ˆ ̆ ̛  Â, Ê, Ă, Ơ, Ư
    // Remove extra spaces
    str = str.replace(/ + /g," ");
    str = str.trim();
    // Remove punctuations
    str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'|\"|\&|\#|\[|\]|~|\$|_|`|-|{|}|\||\\/g," ");
    return str;
}

function expanded(match){
	  	var tree = $.ui.fancytree.getTree('.tree-checkbox-hierarchical'),
	    filterFunc = $("#branchMode").is(":checked") ? tree.filterBranches : tree.filterNodes;
	  // clear value after search
	  tree.clearFilter();
	  // filter extension
	  var opts = {autoApply: true, autoExpand: true, fuzzy: false, hideExpanders: true,hideExpandedCounter: false, highlight: true,leavesOnly: true,nodata: true,mode: "hide"};
	  var keyword = convertKeyword(match.toLowerCase());
	  filterFunc.call(tree, function(node) {
	      return new RegExp(keyword, "i").test(convertKeyword(node.title.toLowerCase()));
	    }, opts);
}
var soFormComponent = function() {
    var initFancytree = function() {
        if (!$().fancytree) {
            console.warn('Warning - fancytree_all.min.js is not loaded.');
            return;
        }

        // Hierarchical select
        $('.tree-checkbox-hierarchical').fancytree({
        	activeVisible: true,
        	autoScroll: true,
            checkbox: true,
            selectMode: 3,
            ajax: {
                type: "GET",
                contentType: "application/json"
            },
            source: {
                 url: getContext() + "/cag/so/get-tree/"
            },
            init: function(event, data) {
                var selNodes = data.tree.getSelectedNodes();
                var selKeys = $.map(selNodes, function(node) {
                	//alert("[" + node.key + "]: '" + node.title + "'");
                    //return "[" + node.key + "]: '" + node.title + "'";
                	return node.key;
                });
                $("#selectedFoodGroupCodes").val(selKeys);
			},            
            select: function(event, data) {             
            	var selNodes = data.tree.getSelectedNodes();
                var selKeys = $.map(selNodes, function(node) {
                	//alert("[" + node.key + "]: '" + node.title + "'");
                    //return "[" + node.key + "]: '" + node.title + "'";
                	return node.key;
                });
                $("#selectedFoodGroupCodes").val(selKeys);
            },          
        });
    };

    var initApply = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
        $("#submitForm").on("click", (e) => {
        	var selectedFoodItems = FoodItemSelector.getSelectedFICodes();
        	var selectedFoodGroupCodes = $("#selectedFoodGroupCodes").val();
        	if (selectedFoodGroupCodes) {
    			bootbox.dialog({
    				title: 'Xác nhận',
    				message: 'Việc này sẽ thêm tất cả các món ăn đã được cấu hình vào các nhóm món ăn đã chọn. Bạn có muốn tiếp tục không?',
    				buttons: {
    					cancel: {
    						label: 'Hủy',
    					},
    					full: {
    						label: 'Xác nhận',
    						className: 'btn-primary',
    						callback: function() {
    							$('#selectedFoodItemCodes').val(selectedFoodItems);
    							$('#soCategoryForm').attr('action', '/so/save-food-item');
    							$('#soCategoryForm').submit();
    							$('#pleaseWaitDialog').modal();
    						}
    					},
    				},
    			});
        	} else {
                bootbox.alert({
                    title: 'Thông báo',
                    message: 'Không có nhóm món ăn nào được chọn.'
                });
        	}
        });
    }
    
    var initDelete = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
        $("#deleteForm").on("click", (e) => {
        	var selectedFoodGroupCodes = $("#selectedFoodGroupCodes").val();
        	if (selectedFoodGroupCodes) {
    			bootbox.dialog({
    				title: 'Xác nhận',
    				message: 'Việc này sẽ xóa một số món ăn đã được cấu hình vào các nhóm món ăn đã chọn. Bạn có muốn tiếp tục không?',
    				buttons: {
    					cancel: {
    						label: 'Hủy',
    					},
    					full: {
    						label: 'Xác nhận',
    						className: 'btn-primary',
    						callback: function() {
    							$('#soCategoryForm').attr('action', '/so/delete-food-item');
    							$('#soCategoryForm').submit();
    							$('#pleaseWaitDialog').modal();
    						}
    					},
    				},
    			});
        	} else {
                bootbox.alert({
                    title: 'Thông báo',
                    message: 'Không có món ăn nào được chọn.'
                });
        	}
        });
    }
    
//    var search = function(){
//    	$("#btnSearch").on("click", function(e){
//    		var keyword = $('#search').val();
//			$("#tree").remove();
//			$("#tree_container").append('<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>');
//			// Hierarchical select
//	        $('.tree-checkbox-hierarchical').fancytree({
//	        	activeVisible: true,
//	        	autoScroll: true,
//	            checkbox: true,
//	            selectMode: 3,
//	            ajax: {
//	                type: "GET",
//	                contentType: "application/json"
//	            },
//	            source: {
//	            	 url: getContext() + "/cag/so/get-tree?keyword=" + keyword + "&selectedCodes=" + $("#selectedFoodGroupCodes").val()
//	            },
//	            init: function(event, data) {
//	                var selNodes = data.tree.getSelectedNodes();
//	                var selKeys = $.map(selNodes, function(node) {
//	                	return node.key;
//	                });
//	                $("#selectedFoodGroupCodes").val(selKeys);
//	                if(keyword != null && keyword != ''){
//	                	expanded();
//	                }
//				},            
//	            select: function(event, data) {             
//	            	var selNodes = data.tree.getSelectedNodes();
//	                var selKeys = $.map(selNodes, function(node) {
//	                	return node.key;
//	                });
//	                $("#selectedFoodGroupCodes").val(selKeys);
//	            },
//	        });	
//	    }).focus();
//    }
    var search = function(){
    	$("#btnSearch").on("click", function(e){
    		var keyword = $('#search').val();
    		var keywordFoodItem = $('#searchFoodItem').val();
    		if(keywordFoodItem){
    			$("#tree").remove();
    			$("#tree_container").append('<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>');
    			// Hierarchical select
    	        $('.tree-checkbox-hierarchical').fancytree({
    	        	activeVisible: true,
    	        	autoScroll: true,
    	            checkbox: true,
    	            selectMode: 3,
    	            ajax: {
    	                type: "GET",
    	                contentType: "application/json"
    	            },
    	            source: {
    	            	 url: getContext() + "/cag/so/get-tree?keyword=" + keyword + "&keywordFoodItem=" + keywordFoodItem + "&selectedCodes=" + $("#selectedFoodGroupCodes").val()
    	            },
    	            init: function(event, data) {
    	                var selNodes = data.tree.getSelectedNodes();
    	                var selKeys = $.map(selNodes, function(node) {
    	                	return node.key;
    	                });
    	                $("#selectedFoodGroupCodes").val(selKeys);
    	                if(keywordFoodItem != null && keywordFoodItem != ''){
    	                	expanded(keywordFoodItem);
    	                }
    				},            
    	            select: function(event, data) {             
    	            	var selNodes = data.tree.getSelectedNodes();
    	                var selKeys = $.map(selNodes, function(node) {
    	                	return node.key;
    	                });
    	                $("#selectedFoodGroupCodes").val(selKeys);
    	            },
    	        });	
    		} else {
    			$("#tree").remove();
    			$("#tree_container").append('<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>');
    			// Hierarchical select
    	        $('.tree-checkbox-hierarchical').fancytree({
    	        	activeVisible: true,
    	        	autoScroll: true,
    	            checkbox: true,
    	            selectMode: 3,
    	            ajax: {
    	                type: "GET",
    	                contentType: "application/json"
    	            },
    	            source: {
    	            	 url: getContext() + "/cag/so/get-tree?keyword=" + keyword + "&selectedCodes=" + $("#selectedFoodGroupCodes").val()
    	            },
    	            init: function(event, data) {
    	                var selNodes = data.tree.getSelectedNodes();
    	                var selKeys = $.map(selNodes, function(node) {
    	                	return node.key;
    	                });
    	                $("#selectedFoodGroupCodes").val(selKeys);
    	                if(keyword != null && keyword != ''){
    	                	/*expanded();*/
    	                	expanded(keyword);
    	                }
    				},            
    	            select: function(event, data) {             
    	            	var selNodes = data.tree.getSelectedNodes();
    	                var selKeys = $.map(selNodes, function(node) {
    	                	return node.key;
    	                });
    	                $("#selectedFoodGroupCodes").val(selKeys);
    	            },
    	        });	
    		}
	    }).focus();
    }
    
    var cancelSubmit = function(){
    	$('#search').keydown(function (e) {
    	    if (e.keyCode == 13) {
    	        var inputs = $(this).parents("form").eq(0).find(":input");
    	        if (inputs[inputs.index(this) + 1] != null) {                    
    	            inputs[inputs.index(this) + 1].focus();
    	        }
    	        e.preventDefault();
    	        return false;
    	    }
    	});
    }
    
 // Select2
	var initSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();
   	 	FoodItemSelector.init();
	};
    
    // Return objects assigned to module
    return {
        init: function() {
        	initFancytree();
        	initApply();
        	initSelect2();
        	search();
        	cancelSubmit();
        	initDelete();
        }
    }
}();

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	soFormComponent.init();
});
