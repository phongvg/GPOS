var FormComponent = function() {
    var search = function() {
        $('#btnSubmit').on('click', function() {
            $('#page').val(0);
            $('#paramForm').attr('action', '/cag/group-param/param-list');
			$('#paramForm').submit();
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