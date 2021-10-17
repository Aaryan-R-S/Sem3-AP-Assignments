package com.company;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {
    private static SimpleDateFormat _sdf = new SimpleDateFormat("EEE, MMM dd, HH:mm:ss z, yyyy");
    private String _content;
    private Timestamp _timestamp;
    private User _uploadedBy;

    Comment(String content, User uploadedBy){
        this._content = content;
        this._timestamp = new Timestamp(System.currentTimeMillis());
        this._uploadedBy = uploadedBy;
    }

    public void view(){
        System.out.println(this._content+" - " +this._uploadedBy.getName());
        System.out.println(this._sdf.format(this._timestamp));
    }
}
