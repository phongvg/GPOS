var GroupComponent = function () {
	
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

    return {
        initComponents: function() {
            onCheckedAll();
            initUniform();
            initSwitchery();
            initBootstrapSwitch();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
	GroupComponent.initComponents();
});
