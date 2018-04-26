$(document).ready(function () {
    $('#example').DataTable({
        "pageLength": 10

    });

    // var localHeightContainer = $('#container').height() - $('.table').height();
    var bodyboxHeight = $('.body-box').height();
    // alert('before : ' + bodyboxHeight);
    var tableHeight = $('.table').height();
    $('.body-box').height(bodyboxHeight + tableHeight);
    var heightAfterExe = $('.body-box').height() - tableHeight;
    // alert('after : ' + heightAfterExe);
    // alert('container' + $('#container').height());
    var localContainer = $('#container').height();
    var extraContainer = $('#container').height(localContainer + tableHeight);
    $('#container').height(localContainer + tableHeight);
    // alert('after container' + $('#container').height());
    var originContainer = $('#container').height() - tableHeight;
    // alert('container pate : '+ originContainer);
    $('.dataTables_paginate').on('click', function () {

        var bodyboxHeight = heightAfterExe;
        // alert('in function : ' + bodyboxHeight);
        var heightTable = $('.table').height();
        // alert('in function container ' + originContainer);

        $('.body-box').height(bodyboxHeight + heightTable - 20);
        $('#container').height(originContainer + heightTable);
    });

    $('.dataTables_paginate').on('click', function () {
        $('html,body').animate({
            scrollTop: 0
        }, 0);
    });
});

// $(window).resize(function () {

//     var HeightContainer = $('#container').height() - $('.table').height();
//     var heightTable = $('.table').height();
//     localHeightContainer = $('#container').height();
//     $('#container').height(HeightContainer + heightTable + 80);
// });
