/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.ParkDTO;
import entity.ParkDistanceDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Yuu
 */
public class DistanceMatrix implements Serializable {

    private static final String USER_AGENT = "Mozilla/5.0";
//    private static final String API_KEY = "AIzaSyB8nz4AoPyn5FeuOSI1wDd0o0cbmu621jc";
//    private static final String API_KEY = "AIzaSyAjGL3TVH731qoxueDHnrhJgaQadM-7wnE";
    private static final String API_KEY = "AIzaSyAJnuF5Nnb7etcUuu1rp4KcinRzIc1_OGU";
    private static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";

    public static List<ParkDistanceDTO> getParkInDistance(String current, String search, List<ParkDTO> listPark, int range) throws IOException {
        List<ParkDistanceDTO> listParkInDistance = getParkInDistance(search, listPark, range);
        List<ParkDistanceDTO> result = new ArrayList<>();

        if (listParkInDistance == null || listParkInDistance.isEmpty()) {
            return result;
        }

        String url = GOOGLE_URL;
        String origins = "?origins=" + current;
        String destinations = "&destinations=";

        for (ParkDistanceDTO park : listParkInDistance) {
            String pos = "|" + String.valueOf(park.getLat()) + "," + String.valueOf(park.getLng());
            destinations += pos;
        }

        url += origins + destinations;

        String response = sendRequestGoogleAPI(url);

        if (response == null) {
            return null;
        }

        JSONObject json = new JSONObject(response);
        String status = json.getString("status");
        if (status.equals("INVALID_REQUEST") || status.equals("OVER_QUERY_LIMIT")) {
            return null;
        }

        JSONArray elements = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements");
        for (int i = 0; i < elements.length(); i++) {
            if (elements.getJSONObject(i).getString("status").equals("NOT_FOUND")) {
                break;
            }
            String distance = elements.getJSONObject(i).getJSONObject("distance").getString("text");
            int distanceVal = elements.getJSONObject(i).getJSONObject("distance").getInt("value");
            String duration = elements.getJSONObject(i).getJSONObject("duration").getString("text");
            duration = duration.replaceAll("hour", "giờ");
            duration = duration.replaceAll("mins", "phút");
            ParkDistanceDTO park = listParkInDistance.get(i);
            ParkDistanceDTO dto = new ParkDistanceDTO();
            dto.setParkID(park.getParkID());
            dto.setParkName(park.getParkName());
            dto.setPrice(park.getPrice());
            dto.setLat(park.getLat());
            dto.setLng(park.getLng());
            dto.setParkAddress(park.getParkAddress());
            dto.setDistance(distance);
            dto.setDistanceVal(distanceVal);
            dto.setDuration(duration);
            dto.setSlot(park.getSlot());
            dto.setEmptySlot(park.getEmptySlot());
            result.add(dto);

        }
        return result;
    }

    public static List<ParkDistanceDTO> getAllParkInDistance(String current, List<ParkDTO> listPark) throws MalformedURLException, IOException {

        List<ParkDistanceDTO> result = new ArrayList<>();

        String url = GOOGLE_URL;
        String origins = "?origins=" + current;
        String destinations = "&destinations=";

        for (ParkDTO park : listPark) {
            String pos = "|" + String.valueOf(park.getLat()) + "," + String.valueOf(park.getLng());
            destinations += pos;
        }

        url += origins + destinations;

        String response = sendRequestGoogleAPI(url);

        if (response == null) {
            return null;
        }

        JSONObject json = new JSONObject(response);
        String status = json.getString("status");
        if (status.equals("INVALID_REQUEST") || status.equals("OVER_QUERY_LIMIT")) {
            return null;
        }

        JSONArray elements = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements");

        for (int i = 0; i < elements.length(); i++) {
            if (elements.getJSONObject(i).getString("status").equals("NOT_FOUND")) {
                break;
            }
            String distance = elements.getJSONObject(i).getJSONObject("distance").getString("text");
            int distanceVal = elements.getJSONObject(i).getJSONObject("distance").getInt("value");
            String duration = elements.getJSONObject(i).getJSONObject("duration").getString("text");
            duration = duration.replaceAll("hour", "giờ");
            duration = duration.replaceAll("mins", "phút");
            ParkDTO park = listPark.get(i);
            ParkDistanceDTO dto = new ParkDistanceDTO();
            dto.setParkID(park.getParkID());
            dto.setParkName(park.getParkName());
            dto.setPrice(park.getPrice());
            dto.setLat(park.getLat());
            dto.setLng(park.getLng());
            dto.setParkAddress(park.getParkAddress());
            dto.setDistance(distance);
            dto.setDistanceVal(distanceVal);
            dto.setDuration(duration);
            dto.setSlot(park.getSlot());
            dto.setEmptySlot(park.getEmptySlot());
            result.add(dto);

        }
        Collections.sort(result, new ParkDistanceDTO.SortByDistance());
        return result;
    }

    public static List<ParkDistanceDTO> getParkInDistance(String current, List<ParkDTO> listPark, int range) throws MalformedURLException, IOException {

        List<ParkDistanceDTO> result = new ArrayList<>();

        String url = GOOGLE_URL;
        String origins = "?origins=" + current;
        String destinations = "&destinations=";

        for (ParkDTO park : listPark) {
            String pos = "|" + String.valueOf(park.getLat()) + "," + String.valueOf(park.getLng());
            destinations += pos;
        }

        url += origins + destinations;

        String response = sendRequestGoogleAPI(url);

        if (response == null) {
            return null;
        }

        JSONObject json = new JSONObject(response);
        String status = json.getString("status");
        if (status.equals("INVALID_REQUEST") || status.equals("OVER_QUERY_LIMIT")) {
            return null;
        }

        JSONArray elements = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements");

        for (int i = 0; i < elements.length(); i++) {
            if (elements.getJSONObject(i).getString("status").equals("NOT_FOUND")) {
                break;
            }
            String distance = elements.getJSONObject(i).getJSONObject("distance").getString("text");
            int distanceVal = elements.getJSONObject(i).getJSONObject("distance").getInt("value");
            String duration = elements.getJSONObject(i).getJSONObject("duration").getString("text");
            duration = duration.replaceAll("hour", "giờ");
            duration = duration.replaceAll("mins", "phút");
            if (distanceVal <= range) {
                ParkDTO park = listPark.get(i);
                ParkDistanceDTO dto = new ParkDistanceDTO();
                dto.setParkID(park.getParkID());
                dto.setParkName(park.getParkName());
                dto.setPrice(park.getPrice());
                dto.setLat(park.getLat());
                dto.setLng(park.getLng());
                dto.setParkAddress(park.getParkAddress());
                dto.setDistance(distance);
                dto.setDistanceVal(distanceVal);
                dto.setDuration(duration);
                dto.setSlot(park.getSlot());
                dto.setEmptySlot(park.getEmptySlot());
                result.add(dto);
            }
        }
        Collections.sort(result, new ParkDistanceDTO.SortByDistance());
        return result;
    }

    private static String sendRequestGoogleAPI(String url) throws MalformedURLException, IOException {

        String key = "&key=" + API_KEY;
        url += key;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setUseCaches(false);

        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        }

        return response.toString();
    }
}
