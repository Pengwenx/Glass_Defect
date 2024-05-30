package com.example.demo.entity;

public class Photo {
    private String uploadphoto;
    private String resultphoto;

    public Photo(String number, String number1) {
        setUpload_photo(number);
        setResult_photo(number1);
    }

    public String getResultphoto() {
        return resultphoto;
    }

    public void setUpload_photo(String upload_photo) {
        this.uploadphoto = upload_photo;
    }

    public void setResult_photo(String result_photo) {
        this.resultphoto = result_photo;
    }

    @Override
    public String toString() {
        return "photo{" +
                "upload_photo='" + uploadphoto + '\'' +
                ", result_photo='" + resultphoto + '\'' +
                '}';
    }
}
