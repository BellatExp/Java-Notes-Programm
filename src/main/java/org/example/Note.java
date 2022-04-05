package org.example;

import java.util.Date;

public class Note {
    private String name;
    private String text;
    private Date date;
    private String hashTags;

    public Note(String name, String text, String tags){
        this.name =  name;
        this.text = text;
        this.date = new Date();
        this.hashTags = tags;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHashTags(String hashTags) {
        this.hashTags = hashTags;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public String getHashTags() {
        return hashTags;
    }

    @Override
     public String toString()
    {
        return "[" + date.toString() + "] " +
                " " + name + " " +
                " " + text + " " +
                " " + hashTags + "\n";
    }

}
