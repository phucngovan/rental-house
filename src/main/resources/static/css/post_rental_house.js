$(document).ready(function () {
    $('#description').summernote({
        height: ($(window).height() - 300),
        onImageUpload: function (files) {
            for (var i = 0; i < files.length; i++) {
                $.upload('image', files[i]);
            }
        }

    });
    //upload file image in summernote
    $.upload = function (file) {
        var out = new FormData();
        out.append('file', file, file.name);

        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/upload/image_summernote',
            contentType: false,
            cache: false,
            processData: false,
            dataType: 'JSON',
            data: out,
            success: function (r) {
                $('#description').summernote('insertImage', r.url);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error(textStatus + " " + errorThrown);
            }
        });
    };

});
