var modal = document.getElementById('myModal');


var input = document.getElementById('input-close');
var formcontrol = document.getElementsByClassName('form-control');

$(input).click(() => {
    if (modal !== null)
        modal.style.display = "none";

    for (var i = 0; i < formcontrol.length; i++) {
        formcontrol[i].removeAttribute('disabled', 'disabled');
    }
});


$(function () {
    if ($(modal).is(":visible")) {
        for (var i = 0; i < formcontrol.length; i++) {
            formcontrol[i].setAttribute('disabled', 'disabled');
        }
    }
});

function setPopupValue(obj) {
    var id = $(obj).attr('id');
    var value = $(obj).val();
    $('#text-area').val(id);
    $('#text-area2').val(value);
}
function setPopupValueFeedback(obj) {
    var id = $(obj).attr('id');
    var value = $(obj).val();
    var removeButton = document.getElementById("remove-button");
    removeButton.style.display = "block";
    $('#close-button').attr('class', 'col-md-2');

    $('#text-area').val(id);
    $('#text-area2').text(value);
}
function setPopupDetailsFeedback(obj) {
    var id = $(obj).attr('id');
    var removeButton = document.getElementById("remove-button");
    removeButton.style.display = "none";
    $('#close-button').attr('class', 'col-md-12');
    $('#text-area2').text(id);
}




