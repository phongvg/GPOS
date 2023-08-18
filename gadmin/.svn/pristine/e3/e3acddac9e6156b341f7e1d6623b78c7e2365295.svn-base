var FormComponent = function() {
    var initBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - switch.min.js is not loaded.');
            return;
        }
        $('.form-check-input-switch').bootstrapSwitch();
    };

    var initBootbox = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }
    };
    
    return {
        init: function() {
            initBootstrapSwitch();
            initBootbox();
        }
    }
}();

var GroupParamForm = {
	check: (path, gpId) => {
		if (gpId) {
			location.href = path + "?groupParamId=" + gpId;
		} else {
            bootbox.alert({
            	title: 'Thông báo:',
                message: 'Thông tin nhóm param chưa được tạo!'
            });
		}
	}
}

// Initialize module
// ------------------------------
document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});
