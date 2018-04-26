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

function getDirection(parkID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById("myModel").style.display = "block";
            var url = xhttp.responseText;
            var html = "<embed id='mapembed' src='" + url + "' width=97% height=85% />";
            $("embed").remove("#mapembed");
            $("#map").append(html);
        }
    };
    xhttp.open("POST", "GetDirectionMapAjax?parkID=" + parkID, true);
    xhttp.send();
}

function checkBooking(phone, parkID, duration) {
    var bookTime = document.getElementById("bookingtime").value;
    var parkID = document.getElementById("popupparkid").value;
    var duration = document.getElementById("popupparkduration").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            document.getElementById("myModal").style.display = "block";
            document.getElementById("myModel2").style.display = "none";
            var rs = xhttp.responseText;
            if (rs === "None") {
                document.getElementById("messagebookingpopup").innerText = "Hiện bãi xe không còn chỗ trống. Vui lòng chọn bãi xe khác";
                var input = document.getElementById("buttonbooking");
                if (input !== null)
                    input.style.display = "none";
            } else {
                var tmp = rs.split("|");
                if (tmp.length > 1) {
                    document.getElementById("messagebookingpopup").innerText = "Xác nhận đặt xe vào bãi\nBạn đã đặt xe cho ngày mai\nThời gian đặt xe\n" + tmp[0];
                } else {
                    document.getElementById("messagebookingpopup").innerText = "Xác nhận đặt xe vào bãi\nThời gian đặt xe\n" + tmp[0];
                }
                document.getElementById("buttonbookingpopup").innerHTML = "<form action=\"BookingPark\">"
                        + "<input type=\"hidden\" name=\"phone\" value=\"" + phone + "\"/>"
                        + "<input type=\"hidden\" name=\"parkID\" value=\"" + parkID + "\"/>"
                        + "<input type=\"hidden\" name=\"duration\" value=\"" + duration + "\"/>"
                        + "<input type=\"hidden\" name=\"bookingtime\" value=\"" + tmp[0] + "\"/>"
                        + "<input type=\"submit\" style=\"width: 100px\" class=\"btn btn-default\" value=\"ĐẶT CHỖ\" id=\"buttonbooking\" />"
                        + "</form>";
            }
        }
    };
    xhttp.open("POST", "CheckParkBookingAjax?parkID=" + parkID + "&bookTime=" + bookTime, true);
    xhttp.send();
}

function checkCancelBooking(bookingID, phone, parkID) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var rs = xhttp.responseText;
            if (rs === "NotBooking") {
                document.getElementById("statusbookingpopup").innerText = "LỖI";
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
                document.getElementById("statusbookingpopup").innerText = "XÁC NHẬN";
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
                        + "<input type=\"submit\" style=\"width: 100px\" class=\"btn btn-default\" value=\"HUỶ ĐẶT\" id=\"buttonbooking\" />"
                        + "</form>";
            }
            document.getElementById("myModal").style.display = "block";
        }
    };
    xhttp.open("POST", "CheckCancelBookingAjax?bookingID=" + bookingID, true);
    xhttp.send();
}




