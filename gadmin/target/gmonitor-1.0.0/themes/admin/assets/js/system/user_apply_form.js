var FormComponent = function() {
    var initFancytree = function() {
        if (!$().fancytree) {
            console.warn('Warning - fancytree_all.min.js is not loaded.');
            return;
        }
        
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
            	 url: getContext() + "/res/get-tree/" + $("#refType").val() + "/" + $("#refId").val() + "?keyword=" + $('#search').val()
            },
            init: function(event, data) {
                var selNodes = data.tree.getSelectedNodes();
                var selKeys = $.map(selNodes, function(node) {
                	return node.key;
                });
                $("#selectedRestaurantCodes").val(selKeys);
                console.log("selKeys :" + $("#selectedRestaurantCodes").val());
			},            
            select: function(event, data) {             
            	var selNodes = data.tree.getSelectedNodes();
                var selKeys = $.map(selNodes, function(node) {
                	return node.key;
                });
                $("#selectedRestaurantCodes").val(selKeys);
            }, 
        });
    };

    
    var search = function(){
    	$("#btnSearch").on("click", function(e){
    		var keyword = $('#search').val();
			$("#tree").remove();
			$("#tree_container").append('<div class="tree-checkbox-hierarchical card card-body border-left-danger border-left-2" id="tree"></div>');
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
        	search();
        	cancelSubmit();
        }
    }
}();

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

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});