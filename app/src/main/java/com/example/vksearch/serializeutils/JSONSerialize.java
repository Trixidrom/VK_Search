package com.example.vksearch.serializeutils;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JSONSerialize {

    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("id")
    private String id;
    @SerializedName("city")
    private JsonObject city;
    @SerializedName("bdate")
    private String bdate;
    @SerializedName("photo_50")
    private String photo_50;

    public String getBdate() {
            if (bdate != null){
                return bdate;
            }else{
                return "Скрыт";
            }
    }

    public String getPhoto_50() {
        return photo_50;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        String str = "";
        try {
            if (city != null){
                str = new JSONObject(city.toString()).getString("title");
            }else{
                str ="Скрыт";
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}
