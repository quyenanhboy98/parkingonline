function loadLoginPage() {
    $("#login-area").css('padding-top', '6%');
    $(".login-box").css('opacity', 1);
    $(".login-box").css('transition', "2s");
    $("#login-area").css('transition-duration', '2s');
}

function loadForgotPassPage() {
    return loadLoginPage();
}

function loadRegisterPage() {
    $("#login-area").css('padding-top', '0.5%');
    $(".login-box").css('opacity', 1);
    $(".login-box").css('transition', "2s");
    $("#login-area").css('transition-duration', '2s');
}

