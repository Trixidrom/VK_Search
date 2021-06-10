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
