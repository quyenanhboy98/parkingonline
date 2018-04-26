/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author Yuu
 */
public class Direction implements Serializable {

//    private static final String API_KEY = "AIzaSyB8nz4AoPyn5FeuOSI1wDd0o0cbmu621jc";
//    private static final String API_KEY = "AIzaSyAjGL3TVH731qoxueDHnrhJgaQadM-7wnE";
    private static final String API_KEY = "AIzaSyAJnuF5Nnb7etcUuu1rp4KcinRzIc1_OGU";
    private static final String GOOGLE_URL = "https://www.google.com.vn/maps/embed/v1/directions?language=vi&key=" + API_KEY;

    public static String getDirection(String origin, String destination) {
        return GOOGLE_URL + "&origin=" + origin + "&destination=" + destination;
    }
}
