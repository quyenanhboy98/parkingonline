$(function () {
    $("#datepicker").datetimepicker({
        ignoreReadonly: true,
        format: 'YYYY-MM-DD'
    });
    $("#fromdate").datetimepicker({
        ignoreReadonly: true,
        format: 'YYYY-MM-DD'
    });
    $("#todate").datetimepicker({
        ignoreReadonly: true,
        format: 'YYYY-MM-DD'
    });
});

$(function () {
    $('#timestart').datetimepicker({
        ignoreReadonly: true,
        format: '[] HH:mm'
    });

    $('#timeend').datetimepicker({
        ignoreReadonly: true,
        format: '[] HH:mm'
    });
});

$(function () {
    $('#bookingtime').datetimepicker({
        ignoreReadonly: true,
        defaultDate: moment(),
        format: '[] HH:mm'
    });
});


