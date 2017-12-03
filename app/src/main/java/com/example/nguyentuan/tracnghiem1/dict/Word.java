package com.example.nguyentuan.tracnghiem1.dict;

/**
 * Created by toannq on 6/19/2017.
 */

public class Word {
    private int ID;
    private String EngLish;
    private String VietNam;
    private String Kind;

    public Word(int ID, String engLish, String vietNam, String kind) {
        this.ID = ID;
        EngLish = engLish;
        VietNam = vietNam;
        Kind = kind;
    }

    public Word() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEngLish() {
        return EngLish;
    }

    public void setEngLish(String engLish) {
        EngLish = engLish;
    }

    public String getVietNam() {
        return VietNam;
    }

    public void setVietNam(String vietNam) {
        VietNam = vietNam;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }
}