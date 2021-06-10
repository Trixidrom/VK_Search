package com.example.vksearch.serializeutils;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    private String cId;
    @SerializedName("title")
    private String cTitle;

    public String getcId() {
        return cId;
    }

    public String getcTitle() {
        return cTitle;
    }
}