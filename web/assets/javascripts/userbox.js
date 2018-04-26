$(document).ready(() => {
    $('iframe').load(function () {
        $(this).contents().find("body").on('click', function (event) {
            if ($('.userbox').hasClass('open')) {
                $('.userbox').removeClass('open');
            }
        });
    });
});