// Khai báo các class role
var roles = [{
		all : '#system',
		element : '.system',
	
	},{
		all : '#account',
		element : '.account',
	
	},
	{
		all : '#group1',
		element : '.group1',
	},
	{
		all : '#catalogMenu',
		element : '.catalogMenu',
	},
	{
		all : '#catalogCO',
		element : '.catalogCO',
	},
	{
		all : '#catalogKDS',
		element : '.catalogKDS',
	},
	{
		all : '#catalogParam',
		element : '.catalogParam',
	},
	{
		all : '#reCate',
		element : '.reCate',
	},{
		all : '#reCu',
		element : '.reCu',
	},
	{
		all : '#reFood',
		element : '.reFood',
	},
	{
		all : '#reModiGroup',
		element : '.reModiGroup',
	},
	{
		all : '#reModiScheme',
		element : '.reModiScheme',
	},
	{
		all : '#reModiDetail',
		element : '.reModiDetail',
	},
	{
		all : '#reModifier',
		element : '.reModifier',
	},
	{
		all : '#reOrderCate',
		element : '.reOrderCate',
	},
	{
		all : '#reOrderType',
		element : '.reOrderType',
	},
	{
		all : '#reOrderVoid',
		element : '.reOrderVoid',
	},
	{
		all : '#reCuRate',
		element : '.reCuRate',
	},
	{
		all : '#reHall',
		element : '.reHall',
	},
	{
		all : '#reTable',
		element : '.reTable',
	},
	{
		all : '#reGuest',
		element : '.reGuest',
	},
	{
		all : '#reEmp',
		element : '.reEmp',
	},
	{
		all : '#groupType',
		element : '.groupType',
	},
	{
		all : '#feature',
		element : '.feature',
	},
	{
		all : '#res',
		element : '.res',
	},
	{
		all : '#reHall',
		element : '.reHall',
	}		
];

// function checkbox khi chuyển nhóm quyền
var CheckAll = {
		loads: () => {
			roles.forEach(item => {
				let elementTotal = $(item.element).length;
				let isChecked = 0;
				for (let i = 0 ; i< $(item.element).length; i++) {
					var ele = $(item.element)[i];
					if ($(ele).attr("disabled") == "disabled") {
						isChecked +=1;
					}
				}
				if (elementTotal == isChecked) {
					$(item.all).prop("checked", true);
					$(item.all).prop("disabled", true);
				}
			});
		},
}

var UserComponent = function() {
	
    // Get các quyền có trong nhóm quyền và tự động checkbox
	var getRolesByAppGroupId = function(){
		$('#groupIds').on('change',function(){
		 	var contextPath = getContext();
        	var appGroupId = $("#groupIds option:selected").val();
        	$('input[type=checkbox]:disabled').prop('checked', false);
        	$('input[type=checkbox]').removeAttr('disabled');	
    		if(appGroupId != null && appGroupId != ''){
    			$.ajax({
    				url: contextPath + '/system/user/get-roles',
    				contextType: 'application/json',
    				method: 'GET',
    				data: {
    					appGroupId : appGroupId
    				},
    				success: function(data) {
    					data.forEach((item) => {
    						var roleid = "#role" + item.id;
    						$(roleid).prop("checked", true);
    						$(roleid).prop("disabled", true);
    						CheckAll.loads();
    					});
    				},
    				error: function(err) {
    					console.log(err);
    				}
    			});
    		}
		 });
	};

	// Kiểm tra xem username đã tồn tại chưa
	var checkUsernameExisting = function() {
		$('#username').on('keyup', function(e){
			var username = $('#username').val();
			var contextPath = getContext();
			var messageCheckCode = document.getElementById('messageCheckCode');
			if(username != ''){
				$.ajax({
					url: contextPath + '/system/user/checkUsername',
					contextType: 'application/json',
					method: 'GET',
					data: {
						username: username
					},
					success: function(data) {
						messageCheckCode.style.display = 'block';
						if(data) {
							messageCheckCode.style.color = 'red';
							$('#submitForm').attr("disabled","disabled");
							$('#messageCheckCode').html('\u0054\u00ea\u006e \u0111\u0103\u006e\u0067 \u006e\u0068\u1ead\u0070 \u006e\u00e0\u0079 \u0111\u00e3 \u0111\u01b0\u1ee3\u0063 \u0073\u1eed \u0064\u1ee5\u006e\u0067\u002e')
						}else{
							console.log(document.getElementById('username').value.length)
							if(document.getElementById('username').value.length != 0){
								messageCheckCode.style.color = 'green';
								$('#submitForm').removeAttr('disabled');
								$('#messageCheckCode').html('\u0054\u00ea\u006e \u0111\u0103\u006e\u0067 \u006e\u0068\u1ead\u0070 \u0068\u1ee3\u0070 \u006c\u1ec7')
							} else {
								messageCheckCode.style.color = 'red';
								$('#submitForm').attr("disabled","disabled");
								$('#messageCheckCode').html('\u004b\u0068\u00f4\u006e\u0067 \u0111\u01b0\u1ee3\u0063 \u0062\u1ecf \u0074\u0072\u1ed1\u006e\u0067 \u0074\u0072\u01b0\u1edd\u006e\u0067 \u006e\u00e0\u0079')
							}
						}
					},
					error: function(err) {
						console.log(err)
					}
				});
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
	}

    var initBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-check-input-switch').bootstrapSwitch();
    };
		
	var validateForm = function(){
		$('#userForm').validate({
			ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
		    errorClass: 'validation-invalid-label',
		    successClass: 'validation-valid-label',
		    validClass: 'validation-valid-label',
		    highlight: function(element, errorClass) {
		        $(element).removeClass(errorClass);
		    },
		    unhighlight: function(element, errorClass) {
		        $(element).removeClass(errorClass);
		    },
		    
		    // Different components require proper error label placement
		    errorPlacement: function(error, element) {

		        // Unstyled checkboxes, radios
		        if (element.parents().hasClass('form-check')) {
		            error.appendTo( element.parents('.form-check').parent() );
		        }

		        // Input with icons and Select2
		        else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
		            error.appendTo( element.parent() );
		        }

		        // Input group, styled file input
		        else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
		            error.appendTo( element.parent().parent() );
		        }

		        // Other elements
		        else {
		            error.insertAfter(element);
		        }
		    },
			rules: {
				"username": {
					required: true,
					maxlength: 256,
				},
				"staff.fullname": {
					required: true,
					maxlength: 256,
				},
				"staff.email": {
					required: true,
					maxlength: 256,
				},
			},
			messages: {
				"username": {
					required: "Không được bỏ trống trường này",
					maxlength: "Chỉ nhập tối đa 256 kí tự",
				},
				"staff.fullname": {
					required: "Không được bỏ trống trường này",
					maxlength: "Chỉ nhập tối đa 256 kí tự",
				},
				"staff.email": {
					required: "Không được bỏ trống trường này",
					maxlength: "Chỉ nhập tối đa 256 kí tự",
				},
			}
		});
	}
	  
    return {
        init: function() {
        	getRolesByAppGroupId();
            validateForm();
            checkUsernameExisting();
			initForm();
			initBootstrapSwitch();

        }
    }
}();



var UserForm = {
	check: (path, Id) => {
		if (Id) {
			location.href = path + "?Id=" + Id;
		} else {
            bootbox.alert({
            	title: 'Thông báo:',
                message: 'Thông tin User chưa được tạo !'
            });
		}
	},
}


//load Role
var Role = {
		loads: () => {
			var contextPath = getContext();
        	var appGroupId = $("#groupIds option:selected").val();
    		if(appGroupId != null && appGroupId != ''){
    			$.ajax({
    				url: contextPath + '/system/user/get-roles',
    				contextType: 'application/json',
    				method: 'GET',
    				data: {
    					appGroupId : appGroupId
    				},
    				success: function(data) {
    					data.forEach((item) => {
    						var roleid = "#role" + item.id;
    						$(roleid).prop("checked", true);
    						$(roleid).prop("disabled", true);
    						CheckAll.loads();
    					});
    				},
    				error: function(err) {
    					console.log(err);
    				}
    			});
    		}else{
    		} 
		},
		
	}
	
// Initialize module
document.addEventListener('DOMContentLoaded', function() {
	UserComponent.init();
	Role.loads();
});
