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
function expanded(){
	  	var tree = $.ui.fancytree.getTree('.tree-checkbox-hierarchical'),
	    filterFunc = $("#branchMode").is(":checked") ? tree.filterBranches : tree.filterNodes,
	    match = $('#search').val();
	  // clear value after search
	  tree.clearFilter();
	  // filter extension
	  var opts = {autoApply: true, autoExpand: true, fuzzy: false, hideExpanders: true,hideExpandedCounter: false, highlight: true,leavesOnly: true,nodata: true,mode: "hide"};
	  var keyword = convertKeyword(match.toLowerCase());
	  filterFunc.call(tree, function(node) {
	      return new RegExp(keyword, "i").test(convertKeyword(node.title.toLowerCase()));
	    }, opts);
}

var FormComponent = function() {
	
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


	
    var initFancytree = function() {
        if (!$().fancytree) {
            console.warn('Warning - fancytree_all.min.js is not loaded.');
            return;
        }

        // Hierarchical select
        $('.tree-checkbox-hierarchical').fancytree({
            checkbox: true,
            selectMode: 3,
            extensions: ["filter"],
        	filter: {
                autoApply: true,   // Re-apply last filter if lazy data is loaded
                autoExpand: true, // Expand all branches that contain matches while filtered
                counter: true,     // Show a badge with number of matching child nodes near parent icons
                fuzzy: false,      // Match single characters in order, e.g. 'fb' will match 'FooBar'
                hideExpandedCounter: true,  // Hide counter badge if parent is expanded
                hideExpanders: false,       // Hide expanders if all child nodes are hidden by filter
                highlight: true,   // Highlight matches by wrapping inside <mark> tags
                leavesOnly: true, // Match end nodes only
                nodata: true,      // Display a 'no data' status node if result is empty
                mode: "dimm"       // Grayout unmatched nodes (pass "hide" to remove unmatched node instead)
              },
            ajax: {
                type: "GET",
                contentType: "application/json",
            },
            source: {
                 url: getContext() + "/res/get-tree/" + $("#refType").val() + "/" + $("#refId").val(),
                 data: {
                    selectedResCodes: $('#selectedRestaurantCodes').val()
                 }
            },
            select: function(event, data) {             
                var selNodes = data.tree.getSelectedNodes();
                var selKeys = $.map(selNodes, function(node) {
                	//alert("[" + node.key + "]: '" + node.title + "'");
                    //return "[" + node.key + "]: '" + node.title + "'";
                	return node.key;
                });
                $("#selectedRestaurantCodes").val(selKeys);
                var selectedResCode = $('.tree-checkbox-hierarchical').fancytree('getTree').getSelectedNodes();
                if(selectedResCode != null && selectedResCode != ""){
                	$("#checkSync").prop('disabled', true);
                } else {
                	$("#checkSync").prop('disabled', false);
                }
            }
            
        });
        
        
    };
    
    var initForm = function() {
        if (!$().select2) {
            console.warn('Warning - select2.js is not loaded.');
            return;
        }

    	// select2
   	 	$('.select2').select2();
   	 	
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

	}
    
    var clickSubmit = function(){
    	if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        };
    	
    	$('#sync-to-res').on('click', function() {
    		var referenceDatas = $('#referenceDatas').val();
    		var checkSync = $('#checkSync').is(":checked");
    		if(checkSync || (referenceDatas != null && referenceDatas != '')){
				bootbox.confirm({
                    title: 'Xác nhận',
                    message: 'Việc đồng bộ này sẽ gửi dữ liệu xuống các nhà hàng đang có trong hệ thống. Bạn chắc chắn muốn thực hiện không?',
                    buttons: {
                        confirm: {
                            label: 'Yes',
                            className: 'btn-primary'
                        },
                        cancel: {
                            label: 'Cancel',
                            className: 'btn-link'
                        }
                    },
                    callback: function (result) {
                    	if (result) {
                    		$("#restaurantForm").submit();
                    		bootbox.alert({
                                title: "Thông báo:",
                                message: "Tiến trình đồng bộ đang được thực hiện. Vui lòng vào phần quản lý đồng bộ để xem thêm.",
                            }); 
                    	}
                    }
                });
    		}else{
    			bootbox.alert({
                    title: "Thông báo:",
                    message: "Bạn chưa chọn dữ liệu tham chiếu. Để đồng bộ thông tin vui lòng chọn.",
                }); 
    		}
        });
    }
    
    
    var syncFileToRes = function(){
    	if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        };
    	
    	$('#sync-to-file-res').on('click', function() {
    		bootbox.confirm({
                title: 'Xác nhận',
                message: 'Việc đồng bộ này sẽ gửi dữ liệu xuống các nhà hàng đang có trong hệ thống. Bạn chắc chắn muốn thực hiện không?',
                buttons: {
                    confirm: {
                        label: 'Yes',
                        className: 'btn-primary'
                    },
                    cancel: {
                        label: 'Cancel',
                        className: 'btn-link'
                    }
                },
                callback: function (result) {
                	if (result) {
                		$("#restaurantForm").submit();
                		bootbox.alert({
                            title: "Thông báo:",
                            message: "Tiến trình đồng bộ đang được thực hiện. Vui lòng vào phần quản lý đồng bộ để xem thêm.",
                        }); 
                	}
                }
            });
        });
    }
    
    //
    // Return objects assigned to module
    //

    var checkedSync = function(){
    	$('#checkSync').on('click',function(){
    		var checkSync = $('#checkSync').is(":checked");
			if(checkSync){
				$('#sttSyncMasterData').val(true);
				$("#search").attr('disabled','disabled');
				$(".tree-checkbox-hierarchical").fancytree({
				    disabled: true, // Disable control
				});
			}else {
				$('#sttSyncMasterData').val(false);
				 $("#search").removeAttr('disabled');
				$(".tree-checkbox-hierarchical").fancytree({
				    disabled: false, // Disable control
				});
			}
    	});
    }
    
    var search = function(){
    	$("#btnSearch").on("click", function(e){
    		var keyword = $('#search').val();
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
	            	 url: getContext() + "/res/get-tree/" + $("#refType").val() + "/" + $("#refId").val() + "?keyword=" + keyword + "&selectedResCodes=" + $("#selectedRestaurantCodes").val()
	            },
	            init: function(event, data) {
	                var selNodes = data.tree.getSelectedNodes();
	                var selKeys = $.map(selNodes, function(node) {
	                	return node.key;
	                });
	                $("#selectedRestaurantCodes").val(selKeys);
	                if(keyword != null && keyword != ''){
	                	expanded();
//	                	$("#tree").fancytree("getTree").visit(function(node) {
//	                		  node.setExpanded(true);
//	                	});
	                }
				},            
	            select: function(event, data) {             
	            	var selNodes = data.tree.getSelectedNodes();
	                var selKeys = $.map(selNodes, function(node) {
	                	return node.key;
	                });
	                $("#selectedRestaurantCodes").val(selKeys);
	            },
	        });	
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
    
    return {
        init: function() {
        	initFancytree();
        	clickSubmit();
        	initForm();	
        	initBootstrapSwitch();
        	initUniform();
        	initSwitchery();
        	checkedSync();
        	search();
        	cancelSubmit();
        	syncFileToRes();
        }
    }
}();

var Sync = {
		checkedSync: () => {
			$('#sttSyncMasterData').val(false);
		},
		
	}
// Initialize module
// ------------------------------



document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
	Sync.checkedSync();
});
