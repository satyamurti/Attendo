package com.example.attendo2.models;


public class ResourcesUpload {
    private String mName;
    private String mImageUrl;
    private String mUrl;

    public ResourcesUpload() {
        //empty constructor needed
    }

    public ResourcesUpload(String name, String imageUrl, String url) {
        if (name.trim().equals("")) {
            name = "No Name";
        }


        mName = name;
        mImageUrl = imageUrl;
        mUrl = url;

    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setName(String name) {
        mName = name;
    }

}