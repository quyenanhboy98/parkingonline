$(document).ready(() => {

    $('.sidebar-toggle').click(() => {
        var paddingPx = $('.content-body').css('padding-left');
        var lineHeight = $('body').css('line-height');
        $('.content-body').css('padding-left', paddingPx === "40px" ? "0px" : "40px");
        $('body').css('line-height', lineHeight === "22px" ? "0px" : "22px");
    });


    $(window).on('resize', function () {
        var win = $(this); //this = window
        if (win.width() < 786) {
            $('.content-body').css('padding-left', '0px');
        }
        if (win.width() > 800) {
            $('.content-body').css('padding-left', '40px');
        }

    });

});


function loadIframe(iframe) {
    document.getElementById('loadSite').src = iframe;
}