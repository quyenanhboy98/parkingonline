$(document).ready(function () {
    $('#example').DataTable({
        "pageLength": 10

    });

    var localHeightContainer = $('#container').height() - $('.table').height();
    $('.dataTables_paginate').on('click', function () {
        var heightTable = $('.table').height();

        $('#container').height(localHeightContainer + heightTable + 100);
    });

    $('.dataTables_paginate').on('click', function () {
        $('html,body').animate({
            scrollTop: 0
        },
            0, function () {
                console.log('finished animation!!');
            });
    });
});

// $(window).resize(function () {

//     var HeightContainer = $('#container').height() - $('.table').height();
//     var heightTable = $('.table').height();
//     localHeightContainer = $('#container').height();
//     $('#container').height(HeightContainer + heightTable + 80);
// });
