var KdsForm = {
	check: (path, kId) => {
		if (kId) {
			location.href = path + "?kId=" + kId;
		} else {
            bootbox.alert({
                title: 'Thông báo:',
                message: 'Thông tin KDS chưa được tạo !'
            });
		}
	}
}
function check(appliedRestaurantCodes, name) {
	var checked = $('#status').is(':checked');
	if(name != null && name != ''){
		if(appliedRestaurantCodes == 'true' && checked != true) {
			bootbox.confirm({
			    title: "Xác nhận:",
			    message: "Có nhà hàng đang áp dụng danh mục KDS này. Off danh mục này sẽ dẫn đến dữ liệu KDS tương ứng vơi danh mục tại nhà hàng sẽ bị xóa. Bạn có chắc chắn muốn off danh mục này không?",
			    buttons: {
			        cancel: {
			            label: '<i class="fa fa-times"></i> Hủy'
			        },
			        confirm: {
			            label: '<i class="fa fa-check"></i> Thực hiện'
			        }
			    },
			    callback: function (result) {
			        if (result) {
			        	$("#kdsForm").submit();
			        }
			    }
			});
		}else {
			$('#kdsForm').submit();
		}
	}
}
function validate() {
	$('input[type="text"]').on('change', function(){
		var name = $('input[type="text"]').val();
		if(name == null || name == '') {
			$('#nameValid').removeAttr('hidden');
		}else {
			$('#nameValid').attr('hidden','hidden');
		}
	});
}
document.addEventListener('DOMContentLoaded', function() {
	var appliedRestaurantCodes = $('#appliedRestaurantCodes').val();
	validate();
	
	$('#btnSubmit').on('click', function(){
		var name = $('input[type="text"]').val();
		check(appliedRestaurantCodes,name);
	});
});