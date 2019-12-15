package com.example.android.popular_movies_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {

    public static final String ID = "id";
    public static final String ISO_639_1 = "iso_639_1";
    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String SITE = "site";
    public static final String SIZE = "size";
    public static final String TYPE = "type";

    @SerializedName(ID)
    private String id;

    @SerializedName(ISO_639_1)
    private String iso6391;

    @SerializedName(KEY)
    private String key;

    @SerializedName(NAME)
    private String name;

    @SerializedName(SITE)
    private String site;

    @SerializedName(SIZE)
    private int size;

    @SerializedName(TYPE)
    private String type;

    public static String getID() {
        return ID;
    }

    public static String getIso6391() {
        return ISO_639_1;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getKEY() {
        return KEY;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getSITE() {
        return SITE;
    }

    public static String getSIZE() {
        return SIZE;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}