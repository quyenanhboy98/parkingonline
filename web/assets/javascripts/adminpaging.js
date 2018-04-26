$(document).ready(function () {
    $('#example').DataTable({
        "pageLength": 10
    });

    var localHeightContainer = $('#containner').height() - $('.table').height();
    $('.dataTables_paginate').on('click', function () {
        var heightTable = $('.table').height();
        $('#containner').height(localHeightContainer + heightTable + 100);
    });


    $('.dataTables_paginate').on('click', function () {
        $('html,body').animate({
            scrollTop: 0
        }, 0,
        );
    });

});