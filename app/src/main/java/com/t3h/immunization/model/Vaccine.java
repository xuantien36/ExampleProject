package com.t3h.immunization.model;
import java.io.Serializable;

public class Vaccine implements Serializable {
    private int image;
    private String nameVaccine;
    private String vaccinationSchedule;
    private String description;

    public Vaccine(int image, String nameVaccine, String vaccinationSchedule, String description) {
        this.image = image;
        this.nameVaccine = nameVaccine;
        this.vaccinationSchedule = vaccinationSchedule;
        this.description = description;
    }
    public int getImage() {
        return image;
    }
    public String getNameVaccine() {
        return nameVaccine;
    }
    public String getVaccinationSchedule() {
        return vaccinationSchedule;
    }
    public String getDescription() {
        return description;
    }
}
