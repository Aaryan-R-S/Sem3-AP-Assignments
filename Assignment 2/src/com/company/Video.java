package com.company;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Video implements Lecture {
    private static SimpleDateFormat _sdf = new SimpleDateFormat("EEE, MMM dd, HH:mm:ss z, yyyy");
    private String _topic;
    private String _filename;   // .mp4
    private Timestamp _timestamp;
    private User _uploadedBy;

    Video(String topic, String filename, User uploadedBy){
        this._topic = topic;
        this._filename = filename;
        this._timestamp = new Timestamp(System.currentTimeMillis());
        this._uploadedBy = uploadedBy;
    }

    @Override
    public void view(){
        System.out.println("Title of video: "+this._topic);
        System.out.println("Video Filename: "+this._filename);
        System.out.println("Date of upload: "+this._sdf.format(this._timestamp));
        System.out.println("Uploaded by: "+this._uploadedBy.getName());
    }
}
