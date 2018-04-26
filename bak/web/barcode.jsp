<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <title>BARCODE</title>
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/stylesheets/barcode.css" />

    </head>

    <body>
        <div class="container">
            <div class="box-area">
                <div class="box-header">
                    <h1>PHONE BARCODE</h1>
                </div>
                <div class="box-body">
                    <img src="GetBarCode?code=${param.code}"/>
                </div>
            </div>
        </div>

    </body>
    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>

    </html>