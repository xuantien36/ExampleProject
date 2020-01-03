package com.t3h.immunization.model;

import java.io.Serializable;

public class Other implements Serializable {
    private int image;
    private String name;
    private int image_two;

    public Other(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    }

