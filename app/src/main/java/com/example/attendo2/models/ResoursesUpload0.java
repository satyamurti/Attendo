package com.example.attendo2.models;

public class ResoursesUpload0 {
    private String mName;
    private String mImageUrl;

    public ResoursesUpload0() {
        //empty constructor needed
    }

    public ResoursesUpload0(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }


        mName = name;
        mImageUrl = imageUrl;


    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public String getName() {
        return mName;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
    public void setName(String name) {
        mName = name;
    }

}
