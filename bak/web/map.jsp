<%-- 
    Document   : map
    Created on : Mar 11, 2018, 8:44:05 PM
    Author     : Yuu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

    <head>
        <style>
            html {height: 100%}
            body {height: 100%; margin: 0; padding: 0}
            #map {height: 100%}
        </style>
    </head>

    <body>
        <div id="map"></div>   
    </body>

    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB8nz4AoPyn5FeuOSI1wDd0o0cbmu621jc&callback=initialize">
    </script>

    <script>
        var marker;

        function initialize() {
            directionsDisplay = new google.maps.DirectionsRenderer();

            navigator.geolocation.getCurrentPosition(function (position) {

                var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

                var mapOptions = {
                    zoom: 16,
                    center: pos
                };


                map = new google.maps.Map(document.getElementById('map'), mapOptions);
                directionsDisplay.setMap(map);

                marker = new google.maps.Marker({
                    position: pos,
                    map: map,
                    title: "Your location",
                    animation: google.maps.Animation.DROP
                });
            });
        }
    </script>

</html>
