package com.example.nguyentuan.tracnghiem1.word;

/**
 * Created by toannq on 6/23/2017.
 */

public class TuVung {
    private int ID;
    private String TiengAnh;
    private String TiengViet;
    private String Image;
    private String Topic;
    private int NumQues;

    public TuVung() {
    }

    public TuVung(int ID, String tiengAnh, String tiengViet, String image, String topic, int numQues) {
        this.ID = ID;
        TiengAnh = tiengAnh;
        TiengViet = tiengViet;
        Image = image;
        Topic = topic;
        NumQues = numQues;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTiengAnh() {
        return TiengAnh;
    }

    public void setTiengAnh(String tiengAnh) {
        TiengAnh = tiengAnh;
    }

    public String getTiengViet() {
        return TiengViet;
    }

    public void setTiengViet(String tiengViet) {
        TiengViet = tiengViet;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public int getNumQues() {
        return NumQues;
    }

    public void setNumQues(int numQues) {
        NumQues = numQues;
    }
}
