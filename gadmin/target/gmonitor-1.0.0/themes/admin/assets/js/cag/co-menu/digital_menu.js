var DigitalMenuSelector = {
    downRow: (index) => {
        var idRow = "#rec-digital-menu-" + index;
        var tableRow = $(idRow);
        tableRow.insertAfter(tableRow.next());
        $('#tblDigitalMenu tbody').find(".showTr").each(function(index) {
            $(this).find('span.no').html(index+1);
        });
    },

    upRow(index){
        var idRow = "#rec-digital-menu-" + index;
        var tableRow = $(idRow);
        tableRow.insertBefore(tableRow.prev());
        $('#tblDigitalMenu tbody').find(".showTr").each(function(index) {
            $(this).find('span.no').html(index+1);
        });
    },

    updateImage(index){
        $('#indexImage').val(index);
        $('#modal_co_upload').modal('show');
    },

    removeRow: function(index){
        bootbox.confirm({
            title: "Xác nhận:",
            message: "Bạn có chắc chắn muốn xoá ảnh này không?",
            buttons: {
                confirm: {
                    label: 'Xác nhận',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'Từ chối',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result){
                    var name = $('#digital-menu-name-'+index).val();
                    $('#rec-digital-menu-'+index).removeClass("showTr").addClass("hideTr");
                    $('#url-image-'+index).removeClass("url-image").addClass("url-image-delete");
                    $('#digital-menu-status-'+index).val("DELETE");
                    $('#rec-digital-menu-'+index).hide();
                    $('#tblDigitalMenu tbody').find(".showTr").each(function(index) {
                        $(this).find('span.no').html(index+1);
                    });
                    fileNameImages = fileNameImages.filter(function(item) {
                        return item !== name;
                    });
                }
            }
        });
    },

    createRow: (index, urlImg, name) => {
        var row0 = document.getElementById("row-no-data");
        if (row0) {
            row0.remove();
        }

        var counter = index + 1;
        var newRow = $('<tr class="showTr" id="rec-digital-menu-'+counter+'">');
        var cols = "";
        cols += '<td><span class="no">'+counter+'</span></td>';
        cols += '<td>';
        cols += '<a><img id="digital-menu-img-'+counter+'" src="'+urlImg+'" height="50" width="50"></a>';
        cols += '<input type="hidden" class="url-image" id="url-image-'+counter+'" value="'+urlImg+'">';
        cols += '<input type="hidden" id="digital-menu-url-'+counter+'" name="digitalMenus['+ counter +'].url" value="'+urlImg+'">';
        cols += '<input type="hidden" id="digital-menu-status-'+ counter +'" name="digitalMenus['+ counter +'].status" value="NEW">';
        cols += '<input type="hidden" id="digital-menu-id-'+ counter +'" name="digitalMenus['+ counter +'].id">';
        cols += '<input type="hidden" class="digital-menu-name" id="digital-menu-name-'+ counter +'" name="digitalMenus['+ counter +'].name" value="'+name+'">';
        cols += '<input type="hidden" id="digital-menu-orderNumber-'+ counter +'" name="digitalMenus['+ counter +'].orderNumber" value="'+ counter+'">';
        cols += '</td>';
        cols += '<td id="digital-menu-filename-'+counter+'">'+name+'</td>';
        cols += '<td class="text-center">';
        cols += '<div class="list-icons">';
        cols += '<a href="javascript(0);" data-target="#preview_'+ counter +'" class="list-icons-item text-danger-600" data-toggle="modal"  style="cursor: pointer;"  data-container="body"><i class="fa fa-eye mr-2 fa-2x"></i></a>';
        cols += '<a href="javascript:DigitalMenuSelector.updateImage('+counter+')"><i class="fa fa-photo mr-2 fa-2x"></i></a>';
        cols += '<a href="javascript:DigitalMenuSelector.removeRow('+counter+')"><i class="fa fa-trash mr-2 fa-2x"></i></a>';
        cols += '<a href="javascript:DigitalMenuSelector.upRow('+counter+')"><i class="fa fa-toggle-up mr-2 fa-2x"></i></a>';
        cols += '<a href="javascript:DigitalMenuSelector.downRow('+counter+')"><i class="fa fa-toggle-down mr-2 fa-2x"></i></a>';
        //modal preview image
        cols += '<div class="modal fade" id="preview_'+ counter +'" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">';
        cols += '<div class="modal-dialog modal-lg" role="document">';
        cols += '<div class="modal-content">';
        cols += '<div class="modal-header">';
        cols += '<h5 class="modal-title" id="imageModalLabel">Preview</h5>';
        cols += '<button type="button" class="close" data-dismiss="modal" aria-label="Đóng"><span aria-hidden="true">&times;</span></button>';
        cols += '</div>';
        cols += '<div class="modal-body">';
        cols += '<img src="'+ urlImg +'" class="img-responsive" style="max-width: 100%; max-height: 100%;">';
        cols += '</div>';
        cols += '</div>';
        cols += '</div>';
        cols += '</div>';
        cols += '</td>';
        newRow.append(cols);
        $('#sizeTable').val(counter);
        return newRow;
    },
}