function checkApply(resCode) {
	bootbox.dialog({
		title: 'Xác nhận',
		message: 'Nhà hàng đã có dữ liệu, bạn muốn ghi đè hay update lại dữ liệu?',
		buttons: {
			cancel: {
				label: 'Hủy',
//				className: 'btn-danger'
			},
			update: {
				label: 'Update',
				className: 'btn-primary',
				callback: function() {
					$('#isOverrideApply').val(false);
					$('#kdsFormApply').submit();
					$('#pleaseWaitDialog').modal();
				}
			},
			full: {
				label: 'Ghi đè',
				className: 'btn-primary',
					callback: function() {
						$('#isOverrideApply').val(true);
						$('#kdsFormApply').submit();
						$('#pleaseWaitDialog').modal();
					}
			},
		},
	});
}

function checkCopy() {
	var resCodes = $('#resCodes').val();
	if(resCodes != ''){
		bootbox.dialog({
			title: 'Xác nhận',
			message: 'Nhà hàng đã có dữ liệu, bạn muốn ghi đè hay update lại dữ liệu?',
			buttons: {
				cancel: {
					label: 'Hủy',
//					className: 'btn-danger'
				},
				update: {
					label: 'Update',
					className: 'btn-primary',
					callback: function() {
						$('#isOverrideCopy').val(false);
						$('#kdsFormCopy').submit();
						$('#pleaseWaitDialog').modal();
					}
				},
				full: {
					label: 'Ghi đè',
					className: 'btn-primary',
						callback: function() {
							$('#isOverrideCopy').val(true);
							$('#kdsFormCopy').submit();
							$('#pleaseWaitDialog').modal();
						}
				},
			},
		});
	} else {
		bootbox.alert({
            title: "Thông báo:",
            message: "Bạn chưa chọn nhà hàng. Vui lòng chọn nhà hàng để copy.",
        });
	};
}