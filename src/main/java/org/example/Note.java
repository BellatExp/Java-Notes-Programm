package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Note {

    private String name;
    private String text;
    private LocalDate date;
    private ArrayList<String> hashTags;

    /*public Note(String name, String text, ArrayList<String> tags){ // Constructor for creating a new obj
        this.name = name;
        this.text = text;
        this.date = LocalDate.now();
        this.hashTags = new ArrayList<>();
        this.hashTags.addAll(tags);
    }*/

    public Note(String name, String text, String tags){ // Constructor for creating a new obj
        this.name = name;
        this.text = text;
        this.date = LocalDate.now();
        this.hashTags = new ArrayList<>();
        this.tagsFromString(tags); //
    }

    /*public Note(String name, String text, ArrayList<String> tags, LocalDate date){ // Constructor for creating obj after reading a file
        this.name = name;
        this.text = text;
        this.date = date;
        this.hashTags = new ArrayList<>();
        this.hashTags.addAll(tags);
    }*/

    public Note(String name, String text, String tags, LocalDate date){ // Constructor for creating obj after reading a file
        this.name = name;
        this.text = text;
        this.date = date;
        this.hashTags = new ArrayList<>();
        this.tagsFromString(tags);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHashTags(String str) {
        this.hashTags.clear();
        this.tagsFromString(str);
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

    public ArrayList<String> getHashTags() {
        return hashTags;
    }

    public String tagsToString() {

        if (this.hashTags == null) return "";
        if (this.hashTags.isEmpty()) return "";

        String res = "";
         for (String s:this.hashTags) {
             res = res.concat("#" + s);
         }
      return res;
     }

     private void tagsFromString(String tags) {

        if (tags == null) return;
        if (tags.isEmpty()) return;

        String[] spl = tags.split("\\#");

         for (String s: spl) {
             this.hashTags.add(s);
         }
     }

    @Override
     public String toString() {
        return "[" + date.toString() + "] " +
                " " + name + " " +
                " " + text + " " +
                " " + this.tagsToString();
    }
}
