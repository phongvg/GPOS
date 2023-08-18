// Phần set lại mật khẩu
setTimeout(function () {$("#pleaseWaitDialog").modal("hide");}, 12000);
function update(userId){
	$('#pleaseWaitDialog').modal();
	var id = userId;
	var pass = '#password_'+ id; 
	var password = $(pass).val();
	var data = {
        "id" : id,
        "password" : password
    };
	$.ajax({
	   url: getContext() + "/system/user/set-password",
	   method: "POST",
	   data: data,
	   success: function(data){
	    	if(data.check){
	    		bootbox.alert({
				    title: "Thông báo:",
				    message: "Thay đổi mật khẩu thành công.",
				});
	    		$(".closeModal").modal("hide"); 
	    		$("#pleaseWaitDialog").modal("hide");
	    		$(pass).html("");
	    	}else{
	    		bootbox.alert({
				    title: "Thông báo:",
				    message: "Thay đổi mật khẩu không thành công.",
				});  
	    		$(".closeModal").modal("hide"); 
	    		$("#pleaseWaitDialog").modal("hide");
	    		$(pass).html("");
	    	}
    	},
	   error : function(e) {
			console.log("ERROR: ", e);
		}
   });
};

var FormComponent = function() {
    var search = function() {
        $('#btnSearch').on('click', function() {
            $('#page').val(0);
            $('#userForm').attr('action', '/system/user/list');
			$('#userForm').submit();
        });
    }
    
    var resetPassword = function() {
        $('#confirmResetPassword').on('click', function() {
            $('#pleaseWaitDialog').modal();
        });
    }
    
    var importExcel = function() {
        $('#btnImport').on('click', function() {
            $('#pleaseWaitDialog').modal();
            $('#userFormImport').attr('action', '/system/user/import');
			$('#userFormImport').submit();
        });
    }
    
    var exportExcel = function(){
	    	$('#downloadFileCheck').on('click',function(){
	    		$('#modal_export').modal('hide');  
	    	});
	 }

    return {
        init: function() {
            search();
            resetPassword();
            importExcel();  
            exportExcel();          
        }
    }
}();

var ItemComponent = {
		showModal : function(){
			var url = $('#urlDownload').val();
			if(url != null && url != ''){
				$('#modal_export').modal('show');    
			}
		},
}

document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
	ItemComponent.showModal();
});