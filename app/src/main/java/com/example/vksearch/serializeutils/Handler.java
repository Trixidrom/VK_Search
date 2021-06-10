package com.example.vksearch.serializeutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class Handler {
    private String json;
    public Handler(JSONObject json) {
        this.json = json.toString();
    }

    public JSONSerialize getSerObj(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson mGson = gsonBuilder.create();
        JSONSerialize jsonSerialize = mGson.fromJson(json, JSONSerialize.class);
        return jsonSerialize;
    }
}
