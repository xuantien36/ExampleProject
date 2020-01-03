package com.t3h.immunization.model;

import java.io.Serializable;

public class Statistical implements Serializable {
    private int image;
    private String name;
    private int slchuatiem;
    private int sldatiem;
    private int slbolo;

    public Statistical(int image, String name, int slchuatiem, int sldatiem, int slbolo) {
        this.image = image;
        this.name = name;
        this.slchuatiem = slchuatiem;
        this.sldatiem = sldatiem;
        this.slbolo = slbolo;
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

    public int getSlchuatiem() {
        return slchuatiem;
    }

    public void setSlchuatiem(int slchuatiem) {
        this.slchuatiem = slchuatiem;
    }

    public int getSldatiem() {
        return sldatiem;
    }

    public void setSldatiem(int sldatiem) {
        this.sldatiem = sldatiem;
    }

    public int getSlbolo() {
        return slbolo;
    }

    public void setSlbolo(int slbolo) {
        this.slbolo = slbolo;
    }
}
