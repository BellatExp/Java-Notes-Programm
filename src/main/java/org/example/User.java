package org.example;

import java.util.ArrayList;

public class User {

    private String login;
    private String password;

    private ArrayList<Note> notes; // Users notes

    public User(String login, String password) {

        this.login = login;
        this.password = password;

        notes = new ArrayList<>();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public int getNumberOfNotes(){
        return this.notes.size();
    }

    public Note getOneNote(int number) {
        return this.notes.get(number);
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void deleteNode(int x) {
        this.notes.remove(x);
    }

    public String notesToString() //
     {
         int i = 1;
         String res = "";
         for (Note tmp : notes) { // Show all notes to User

            res = res.concat(Integer.toString(i) + ". " + tmp.toString());
             i++;
         }
         return res;
     }

}
