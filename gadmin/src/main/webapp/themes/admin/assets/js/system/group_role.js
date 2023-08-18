var FormComponent = function() {

	var onCheckedAll = function() {

		var listRoles = [{
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
		// ------------------------------
		listRoles.forEach(item => {
			let elementTotal = $(item.element).length;
			let isChecked = 0;
			for (let i = 0 ; i< $(item.element).length; i++) {
				var ele = $(item.element)[i];
				if ($(ele).attr("checked") == "checked") {
					isChecked +=1;
				}
			}
			
			if (elementTotal == isChecked) {
				$(item.all).prop("checked", true);
			}
			else {
				$(item.all).prop("checked", false);
			}
		});
		//---------------check select group -----------------
		
		// ------------------------------
		listRoles.forEach(item =>{
			$(item.all).change(function() {
		        if (this.checked) {
		            $(item.element).each(function() {
		                this.checked=true;
		            });
		        } else {
		            $(item.element).each(function() {
		                this.checked=false;
		            });
		        }
		    });
			
			$(item.element).click(function () {
		        if ($(this).is(":checked")) {
		            var isAllChecked = 0;

		            $(item.element).each(function() {
		                if (!this.checked)
		                    isAllChecked = 1;
		            });

		            if (isAllChecked == 0) {
		                $(item.all).prop("checked", true);
		            }     
		        }
		        else {
		            $(item.all).prop("checked", false);
		        }
		    });
		});	
	}
	

	return {
		init: function() {
			onCheckedAll();
		}
	}
}();
	
document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});