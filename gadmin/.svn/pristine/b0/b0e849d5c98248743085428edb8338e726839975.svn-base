var FormComponent = function() {
    var search = function() {
        $('#btnSearch').on('click', function() {
            $('#page').val(0);
            $('#systemParamForm').attr('action', '/system/systemParameter/list');
			$('#systemParamForm').submit();
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