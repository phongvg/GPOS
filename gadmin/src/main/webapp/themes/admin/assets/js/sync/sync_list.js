function selectedCheckBox(id) {
    var arrayIds = [];  
    var checkBox = document.getElementById("checkItem-" + id);
    var syncFailIds = $('#syncFailIds').val();
    if(syncFailIds && syncFailIds != "") {
        arrayIds = syncFailIds.split(",");
    }
    if (checkBox.checked == true){
        arrayIds.push(id);
        $('#syncFailIds').val(arrayIds);
    } else {
        arrayIds = arrayIds.filter((item) => item != id);
        $('#syncFailIds').val(arrayIds);
    }
}

var FormComponent = function() {
    var initForm = function() {
        if (typeof bootbox == 'undefined') {
            console.warn('Warning - bootbox.min.js is not loaded.');
            return;
        }

        if (!$().select2) {
            return;
        }
    	// select2
   	 	$('.select2').select2();
    };

    var search = function() {
        $('#btnSubmit').on('click', function() {
            $('#page').val(0);
            $('#syncForm').attr('action', '/sync-latest-data/list');
			$('#syncForm').submit();
        });
    }

    var checkAll = function() {
        $('#checkAll').on('click', function() {
            var checkBox = document.getElementById("checkAll");
            if (checkBox.checked == true){
                var checkboxes = document.getElementsByClassName('checkItem');
                if(checkboxes.length > 0) {
                    for(var i=0; i<checkboxes.length; i++) {    
                        checkboxes[i].closest("span").classList.add('checked');
                    }
                }
                $('#checkAllItem').val(true);
              } else {
                $('#checkAllItem').val(false);
                var checkboxes = document.getElementsByClassName('checkItem');
                if(checkboxes.length > 0) {
                    for(var i=0; i<checkboxes.length; i++) {    
                        checkboxes[i].closest("span").classList.remove('checked');
                    }
                }
            }
        });
    }
    
    var btnDelete = function() {
        $('#btnDelete').on('click', function() {
            $('#syncForm').attr('action', '/sync/delete-all');
			$('#syncForm').submit();
        });
    }

    var btnResetSync = function() {
        $('#btnResetSync').on('click', function() {
            $('#syncForm').attr('action', '/sync/reset-sync-fail');
            $('#syncForm').submit();
        });
    }

    var initUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        $('.form-check-input-styled').uniform();
    };

    var resetForm = function() {
        $('#btnReset').on('click', function() {
            $('#status').val(null).trigger('change');
            $('#restaurantCode').val("");
            $('#restaurantName').val("");
        });
    }
    return {
        init: function() {
        	initUniform();
            initForm();
            search();
            resetForm();
            checkAll();
            btnResetSync();
            btnDelete();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
    FormComponent.init();
});