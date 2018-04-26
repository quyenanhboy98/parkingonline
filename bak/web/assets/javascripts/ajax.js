function getWorkingTimeList() {
    var value = document.getElementById('parkList').value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById('workingTimeList').innerHTML = xhttp.responseText;
        }

    };
    xhttp.open("POST", "GetListWorkingTimeAjax?parkID=" + value, true);
    xhttp.send();
}

function checkCurrentBooking(phone, parkID, duration) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById("myModal").style.display = "block";
            if (xhttp.responseText === "IsBooking") {
                document.getElementById("messagebookingpopup").innerText = "Hiện tại tài khoản đã đặt chỗ đỗ xe. Vui lòng huỷ để đặt mới.";
                document.getElementById("buttonbookingpopup").innerHTML = "<form action=\"GetListBookingOfUser\">"
                        + "<input type=\"submit\" class=\"btn btn-default\" value=\"Xem lịch sử đặt xe\" id=\"buttonbooking\" />"
                        + "</form>";

            } else if (xhttp.responseText === "IsEmpty") {
                document.getElementById("messagebookingpopup").innerText = "Hiện bãi xe không còn chỗ trống. Vui lòng chọn bãi xe khác";
                var input = document.getElementById("buttonbooking");
                if (input !== null)
                    input.style.display = "none";
            } else {
                var time = document.getElementById("bookingtime").value;
                document.getElementById("messagebookingpopup").innerText = "Xác nhận đặt xe vào bãi. Thời gian đặt " + time;
                document.getElementById("buttonbookingpopup").innerHTML = "<form action=\"BookingPark\">"
                        + "<input type=\"hidden\" name=\"phone\" value=\"" + phone + "\"/>"
                        + "<input type=\"hidden\" name=\"parkID\" value=\"" + parkID + "\"/>"
                        + "<input type=\"hidden\" name=\"duration\" value=\"" + duration + "\"/>"
                        + "<input type=\"hidden\" name=\"bookingtime\" value=\"" + time + "\"/>"
                        + "<input type=\"submit\" style=\"width: 100px\" class=\"btn btn-default\" value=\"Đặt chỗ\" id=\"buttonbooking\" />"
                        + "</form>";
            }
        }
    };
    xhttp.open("POST", "CheckCurrentBookingAjax?phone=" + phone + "&parkID=" + parkID, true);
    xhttp.send();
}


function getDirection(bookingID, parkID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var rs = xhttp.responseText;
            if (rs === "NotBooking") {
                document.getElementById("statusbookingpopup").innerText = "Lỗi";
                document.getElementById("messagebooking").innerText = "Lượt đặt đã hết hạn hoặc có lỗi xảy ra. Vui lòng mở lại trang";
                document.getElementById("messagebooking").style.display = "block";
                document.getElementById("map").style.display = "none";
            } else {
                document.getElementById("statusbookingpopup").innerText = "Xem đường đi";
                document.getElementById("messagebooking").style.display = "none";
                document.getElementById("map").style.display = "block";

                var lat = parseFloat(rs.split("|")[0]);
                var lng = parseFloat(rs.split("|")[1]);
                calcRoute(lat, lng);
            }
            document.getElementById("myModal").style.display = "block";

            var input = document.getElementById("buttonbooking");
            if (input !== null) {
                input.style.display = "none";
            }
        }
    };
    xhttp.open("POST", "GetDirectionAjax?parkID=" + parkID + "&bookingID=" + bookingID, true);
    xhttp.send();
}

function checkCancelBooking(bookingID, phone, parkID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var rs = xhttp.responseText;
            if (rs === "NotBooking") {
                document.getElementById("statusbookingpopup").innerText = "Lỗi";
                document.getElementById("messagebooking").innerText = "Lượt đặt đã hết hạn hoặc có lỗi xảy ra. Vui lòng mở lại trang";
                document.getElementById("messagebooking").style.display = "block";
                document.getElementById("map").style.display = "none";
                var input = document.getElementById("buttonbooking");
                if (input !== null) {
                    input.style.display = "none";
                    console.log("input");
                }
            } else {
                var mess;
                document.getElementById("statusbookingpopup").innerText = "Xác nhận";
                document.getElementById("messagebooking").style.display = "block";
                document.getElementById("map").style.display = "none";
                if (rs === "5") {
                    mess = "Tài khoản sẽ bị trừ 5 Point vì thời gian hết hạn đặt còn dưới 30 phút.";
                } else {
                    mess = "Tài khoản sẽ bị trừ 2 Point.";
                }
                document.getElementById("messagebooking").innerText = "Vui lòng xác nhận huỷ đặt xe bãi hiện tại. " + mess;
                document.getElementById("buttonbookingpopup").innerHTML = "<form action=\"CancelBooking\">"
                        + "<input type=\"hidden\" name=\"bookingID\" value=\"" + bookingID + "\"/>"
                        + "<input type=\"hidden\" name=\"parkID\" value=\"" + parkID + "\"/>"
                        + "<input type=\"hidden\" name=\"point\" value=\"" + rs + "\"/>"
                        + "<input type=\"hidden\" name=\"phone\" value=\"" + phone + "\"/>"
                        + "<input type=\"submit\" style=\"width: 100px\" class=\"btn btn-default\" value=\"Huỷ đặt xe\" id=\"buttonbooking\" />"
                        + "</form>";
            }
            document.getElementById("myModal").style.display = "block";
        }
    };
    xhttp.open("POST", "CheckCancelBookingAjax?bookingID=" + bookingID, true);
    xhttp.send();
}

function checkBookingIDCheckout() {
    var bookingID = document.getElementById("bookingID").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var rs = xhttp.responseText;
            if (rs === "NotFound") {
                document.getElementById("phone").value = "";
                document.getElementById("bookTime").value = "";
                document.getElementById("checkinTime").value = "";
                document.getElementById("checkoutTime").value = "";
                document.getElementById("price").value = "";
            } else {
                document.getElementById("phone").value = rs.split("|")[0];
                document.getElementById("bookTime").value = rs.split("|")[1];
                document.getElementById("checkinTime").value = rs.split("|")[2];
                document.getElementById("checkoutTime").value = rs.split("|")[3];
                document.getElementById("price").value = rs.split("|")[4];
            }
        }
    };
    xhttp.open("POST", "CheckBookingIDForCheckoutAjax?bookingID=" + bookingID, true);
    xhttp.send();
}

