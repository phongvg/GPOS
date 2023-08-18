var FormComponent = function() {
    var search = function() {
        $('#btnSubmit').on('click', function() {
            $('#page').val(0);
            $('#groupParamForm').attr('action', '/cag/group-param/list');
			$('#groupParamForm').submit();
        });
    }

    return {
        init: function() {
            search();
        }
    }
}();


document.addEventListener('DOMContentLoaded', function() {
	FormComponent.init();
});