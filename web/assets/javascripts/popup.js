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

function setPopupValueUser(obj) {
    var id = $(obj).attr('id');
    var value = $(obj).val();
    $('#text-area').val(id);
    $('#text-area2').val(value);
    console.log(value);
    if (value === 'true') {
        $('#popupmessage').text("Bạn có muốn khoá tài khoản này ?");
    } else {
        $('#popupmessage').text("Bạn có muốn mở khoá tài khoản này ?");
    }
}

function setPopupValuePark(obj) {
    var id = $(obj).attr('id');
    var value = $(obj).val();
    $('#text-area').val(id);
    $('#text-area2').val(value);
    console.log(value);
    if (value === 'true') {
        $('#popupmessage').text("Bạn có muốn khoá bãi xe này ?");
    } else {
        $('#popupmessage').text("Bạn có muốn mở khoá bãi xe này ?");
    }
}



function setPopupValueFeedback(obj) {
    var id = $(obj).attr('id');
    var value = $(obj).val();
    var removeButton = document.getElementById("remove-button");
    removeButton.style.display = "block";
    var addrepButton = document.getElementById("addrep-button");
    addrepButton.style.display = "none";
    var texta = document.getElementById("text-area3");
    texta.style.display = "none";
    $('#close-button').attr('class', 'col-md-2');

    $('#text-area').val(id);
    $('#text-area2').text(value);
    $('#popuptitle').text("XÁC NHẬN");
}

function setPopupValueRepFeedback(obj) {
    var id = $(obj).attr('id');
    var value = $(obj).val();
    var addrepButton = document.getElementById("addrep-button");
    addrepButton.style.display = "block";
    var texta = document.getElementById("text-area3");
    texta.style.display = "block";
    var removeButton = document.getElementById("remove-button");
    removeButton.style.display = "none";
    $('#close-button').attr('class', 'col-md-2');

    $('#text-areaReply').val(id);
    $('#popuptitle').text("GỬI HỒI ĐÁP");
}

function setPopupDetailsFeedback(obj) {
    var id = $(obj).attr('id');
    var addrepButton = document.getElementById("addrep-button");
    addrepButton.style.display = "none";
    var removeButton = document.getElementById("remove-button");
    removeButton.style.display = "none";
    var texta = document.getElementById("text-area3");
    texta.style.display = "none";
    $('#close-button').attr('class', 'col-md-12');
    $('#text-area2').text(id);
    $('#popuptitle').text("NỘI DUNG");
}




