package com.t3h.immunization.model;

import java.io.Serializable;

public class VaccinationBook implements Serializable {
    private  String time;
    private String tenMui;
    private String tenVacxin;
    private  String ngayTiem;
    private String conLai;
    private int trangThai;
    private String vaccinationSchedule;
    private String description;

    public VaccinationBook(String time, String tenMui, String tenVacxin, String ngayTiem, String conLai,
                           int trangThai,String vaccinationSchedule,String description) {
        this.time = time;
        this.tenMui = tenMui;
        this.tenVacxin = tenVacxin;
        this.ngayTiem = ngayTiem;
        this.conLai = conLai;
        this.trangThai = trangThai;
        this.vaccinationSchedule=vaccinationSchedule;
        this.description=description;

    }

    public String getVaccinationSchedule() {
        return vaccinationSchedule;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTenMui() {
        return tenMui;
    }

    public void setTenMui(String tenMui) {
        this.tenMui = tenMui;
    }

    public String getTenVacxin() {
        return tenVacxin;
    }

    public void setTenVacxin(String tenVacxin) {
        this.tenVacxin = tenVacxin;
    }

    public String getNgayTiem() {
        return ngayTiem;
    }

    public void setNgayTiem(String ngayTiem) {
        this.ngayTiem = ngayTiem;
    }

    public String getConLai() {
        return conLai;
    }

    public void setConLai(String conLai) {
        this.conLai = conLai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
