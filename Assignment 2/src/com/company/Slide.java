package com.company;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Slide implements Lecture{
    private static SimpleDateFormat _sdf = new SimpleDateFormat("EEE, MMM dd, HH:mm:ss z, yyyy");
    private String _topic;
    private int _noOfSlides;
    private ArrayList<String> _content;
    private Timestamp _timestamp;
    private User _uploadedBy;

    Slide(String topic, int noOfSlides, ArrayList<String> content, User uploadedBy){
        this._topic = topic;
        this._noOfSlides = noOfSlides;
        this._content = content;
        this._timestamp = new Timestamp(System.currentTimeMillis());
        this._uploadedBy = uploadedBy;
    }

    @Override
    public void view(){
        System.out.println("Title of Slides: "+this._topic);
        for (int i = 0; i <this._noOfSlides; i++) {
            System.out.println("Slide "+(i+1)+": "+this._content.get(i));
        }
        System.out.println("Number of slides: "+this._noOfSlides);
        System.out.println("Date of upload: "+this._sdf.format(this._timestamp));
        System.out.println("Uploaded by: "+this._uploadedBy.getName());
    }
}
