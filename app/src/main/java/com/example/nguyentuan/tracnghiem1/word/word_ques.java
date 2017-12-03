package com.example.nguyentuan.tracnghiem1.word;

/**
 * Created by toannq on 6/26/2017.
 */

public class word_ques {
    private int ID;
    private String Ques;
    private String TrueAns;
    private int NumQues;
    private String Topic;
    private String Image;
    private String Listen;

    public word_ques(int ID, String ques, String trueAns, int numQues, String topic, String image, String listen) {
        this.ID = ID;
        Ques = ques;
        TrueAns = trueAns;
        NumQues = numQues;
        Topic = topic;
        Image = image;
        Listen = listen;
    }

    public word_ques() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQues() {
        return Ques;
    }

    public void setQues(String ques) {
        Ques = ques;
    }

    public String getTrueAns() {
        return TrueAns;
    }

    public void setTrueAns(String trueAns) {
        TrueAns = trueAns;
    }

    public int getNumQues() {
        return NumQues;
    }

    public void setNumQues(int numQues) {
        NumQues = numQues;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getListen() {
        return Listen;
    }

    public void setListen(String listen) {
        Listen = listen;
    }
}
