$(document).ready(function() {
    tinymce.init({
        selector: "#mceEditor",
        height: 500,
        theme: "modern",
        mode: "textareas",
        force_br_newlines: false,
        force_p_newlines: false,
        forced_root_block: "",
        paste_data_images: true,
        plugins: [ "advlist autolink lists link image charmap print preview hr anchor pagebreak", "searchreplace wordcount visualblocks visualchars code fullscreen", "insertdatetime media nonbreaking save table contextmenu directionality", "emoticons template paste textcolor colorpicker textpattern" ],
        toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
        toolbar2: "print preview media | forecolor backcolor emoticons",
        image_advtab: true,
        file_picker_callback: function(a, b, c) {
            if ("image" == c.filetype) {
                $("#upload").trigger("click");
                $("#upload").on("change", function() {
                    var b = this.files[0];
                    var c = new FileReader();
                    c.onload = function(b) {
                        a(b.target.result, {
                            alt: ""
                        });
                    };
                    c.readAsDataURL(b);
                });
            }
        },
        templates: [ {
            title: "Test template 1",
            content: "Test 1"
        }, {
            title: "Test template 2",
            content: "Test 2"
        } ]
    });
});