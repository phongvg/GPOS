var myVar = setInterval(checkOnlineRestaurant, 7200000);
function checkOnlineRestaurant(){
	$.ajax({
		url: getContext() + '/restaurant/check-online',
		contextType: 'application/json',
		method: 'GET',
		success: function(data) {
			for (i=0; i<data.length; i++) {
				var id = "#online_" + data[i].code;
				if(data[i].online != 1){
					$(id).css("color", "red");
				}else{
					$(id).css("color", "green");
				}
			}
		},
		error: function(err) {
			console.log(err)
		}
	});
}

function convertKeyword(text){
	 var text_create = text.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a").replace(/\ /g, '-').replace(/đ/g, "d").replace(/đ/g, "d").replace(/ỳ|ý|ỵ|ỷ|ỹ/g,"y").replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g,"u").replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ.+/g,"o").replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ.+/g, "e").replace(/ì|í|ị|ỉ|ĩ/g,"i");
	 return text_create;
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

    var initFancytree = function() {
        if (!$().fancytree) {
            console.warn('Warning - fancytree_all.min.js is not loaded.');
            return;
        }

        // Hierarchical select
        $('.tree-checkbox-hierarchical').fancytree({
            checkbox: true,
            selectMode: 3,
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
            }
            
        });
    };

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
    //
    // Return objects assigned to module
    //
    
    var uncheckTypeRadio = function(){
    	$("[type='radio']").on('click', function (e) {
    	    var previousValue = $(this).attr('previousValue');
    	    if (previousValue == 'true') {
    	        this.checked = false;
    	        $(this).attr('previousValue', this.checked);
    	    }
    	    else {
    	        this.checked = true;
    	        $(this).attr('previousValue', this.checked);
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
        	initSwitchery();
        	initBootstrapSwitch();
        	initUniform();
        	uncheckTypeRadio();
        	search();
        	cancelSubmit();
        }
    }
}();

// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});
