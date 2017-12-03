package com.example.nguyentuan.tracnghiem1.verb;

/**
 * Created by toannq on 6/18/2017.
 */

public class Verb {
    private int ID;
    private String TenThi;
    private String KhangDinh;
    private String PhuDinh;
    private String NghiVan;
    private String NhanBiet;
    private String CachDung;

    public Verb(int ID, String tenThi, String khangDinh, String phuDinh, String nghiVan, String nhanBiet, String cachDung) {
        this.ID = ID;
        TenThi = tenThi;
        KhangDinh = khangDinh;
        PhuDinh = phuDinh;
        NghiVan = nghiVan;
        NhanBiet = nhanBiet;
        CachDung = cachDung;
    }

    public Verb() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenThi() {
        return TenThi;
    }

    public void setTenThi(String tenThi) {
        TenThi = tenThi;
    }

    public String getKhangDinh() {
        return KhangDinh;
    }

    public void setKhangDinh(String khangDinh) {
        KhangDinh = khangDinh;
    }

    public String getPhuDinh() {
        return PhuDinh;
    }

    public void setPhuDinh(String phuDinh) {
        PhuDinh = phuDinh;
    }

    public String getNghiVan() {
        return NghiVan;
    }

    public void setNghiVan(String nghiVan) {
        NghiVan = nghiVan;
    }

    public String getNhanBiet() {
        return NhanBiet;
    }

    public void setNhanBiet(String nhanBiet) {
        NhanBiet = nhanBiet;
    }

    public String getCachDung() {
        return CachDung;
    }

    public void setCachDung(String cachDung) {
        CachDung = cachDung;
    }
}
