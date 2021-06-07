package com.example.vksearch.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    public final static String API = "https://api.vk.com";
    public final static String METHOD = "/users.search";
    public final static String COUNT = "count";
    public final static String CITY = "city";
    public final static String VERSION = "v";
    public final static String TOKEN = "access_token";


    public final static String PARAMS = "q";


        public static URL generateURL(String searchString, String city){
            Uri buildUri = Uri.parse(API + METHOD)
                    .buildUpon()
                    .appendQueryParameter(COUNT, "1000")
                    .appendQueryParameter(CITY, city)
                    .appendQueryParameter(PARAMS, searchString)
                    .appendQueryParameter(VERSION, "5.131")
                    .appendQueryParameter(TOKEN, "64e5e67eb9c34f3f7db2098cc16808456183db6a106252546db8726cbd760806efca36df09c1a3b507139")
                    .build();

            URL url = null;

            try {
                url = new URL(buildUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
    }
}
