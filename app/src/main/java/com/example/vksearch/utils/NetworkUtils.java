package com.example.vksearch.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = "myLogs";

    public final static String API = "https://api.vk.com";
    public final static String METHOD = "/method/users.search";
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
            //f1ba563ebebba76c216635928986c328a2075135fb5a4617c336c19ff5f71cef876035f1fc850334e9b1a
            //токен с телефона
            //e837b5dbbbba08f82f002e75129531e6e1e693c09e3d06957fb4d08b387d6d8049f28da7b160df373fa46
            //ТТК
            Uri buildUri = Uri.parse(API + METHOD)
                    .buildUpon()
                    .appendQueryParameter(COUNT, "1000")
                    .appendQueryParameter(CITY, city)
                    .appendQueryParameter(PARAMS, searchString)
                    .appendQueryParameter(FIELDS, "city")
                    .appendQueryParameter(VERSION, "5.131")
                    .appendQueryParameter(TOKEN, "f1ba563ebebba76c216635928986c328a2075135fb5a4617c336c19ff5f71cef876035f1fc850334e9b1a")
                    .build();

            URL url = null;

            try {
                url = new URL(buildUri.toString());
                //url = new URL("https://api.vk.com/method/users.get?user_ids=trix2006,highsense&v=5.131&access_token=8bdc1cdb8bdc1cdb8bdc1cdbf48ba412f488bdc8bdc1cdbeb689e3e6df7b004e0b8b490");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
    }

        public static String getResponseFromURL (URL url) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();//открыли соединение
            try{

                InputStream in = urlConnection.getInputStream(); //получили входной поток

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if(hasInput){
                    //здесь прописать добавление строк в эрэйлист

                    return scanner.next();
                }else {
                    return null;
                }
            }finally {
                urlConnection.disconnect();//надо закрыть соединение, файнали выполняется в любом случае
                //Log.d(TAG, url.toString());
            }
        }
}
