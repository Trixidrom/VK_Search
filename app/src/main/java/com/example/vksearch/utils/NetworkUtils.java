package com.example.vksearch.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public final static String API = "https://api.vk.com";
    public final static String METHOD = "/users.search";
    public final static String COUNT = "count";
    public final static String CITY = "city";
    public final static String FIELDS = "fields";
    public final static String VERSION = "v";
    public final static String TOKEN = "access_token";


    public final static String PARAMS = "q";


        public static URL generateURL(String searchString, String city){
            //https://api.vk.com/method/users.get?user_ids=trix2006,highsense&v=5.21&access_token=8bdc1cdb8bdc1cdb8bdc1cdbf48ba412f488bdc8bdc1cdbeb689e3e6df7b004e0b8b490
            //старый запрос
            //https://api.vk.com/method/users.search?count=1000&q=chumachenko&fields=city&city=167&v=5.131&access_token=39eb9663934d971862adff9d3a55cf3e79ababda19e648e584e47dbe17c7f025bf036daec558d2ca38eec
            //новый
            Uri buildUri = Uri.parse(API + METHOD)
                    .buildUpon()
                    .appendQueryParameter(COUNT, "1000")
                    .appendQueryParameter(CITY, city)
                    .appendQueryParameter(PARAMS, searchString)
                    .appendQueryParameter(FIELDS, "city")
                    .appendQueryParameter(VERSION, "5.131")
                    .appendQueryParameter(TOKEN, "39eb9663934d971862adff9d3a55cf3e79ababda19e648e584e47dbe17c7f025bf036daec558d2ca38eec")
                    .build();

            URL url = null;

            try {
                url = new URL(buildUri.toString());
                System.out.println(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
    }

        public static String getResponseFromURL (URL url) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();//открыли соединение
            try{
                System.out.println("привет");
                InputStream in = urlConnection.getInputStream(); //получили входной поток
                System.out.println(in.toString());
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if(hasInput){
                    //здесь прописать добавление строк в эрэйлист
                    String str = scanner.next();
                    return scanner.next();
                }else {
                    return null;
                }
            }finally {
                urlConnection.disconnect();//надо закрыть соединение, файнали выполняется в любом случае
            }
        }
}
