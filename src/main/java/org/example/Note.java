package org.example;

import java.time.LocalDate;


public class Note {

    private String name;
    private String text;
    private LocalDate date;
    private String hashTags;

    public Note(String name, String text, String tags){
        this.name =  name;
        this.text = text;
        this.date = LocalDate.now();
        this.hashTags = tags;
    }

    public Note(String name, String text, String tags, LocalDate date){ // Constructor for creating obj after reading a file
        this.name =  name;
        this.text = text;
        this.date = date;
        this.hashTags = tags;
    }

    /*public void setDate(LocalDate date) {
        this.date = date;
    }*/

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

    public LocalDate getDate() {
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
                " " + hashTags;
    }

}
