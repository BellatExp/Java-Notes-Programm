package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class NotesHandler { // Auxiliary class for working with Nodes

    public static ArrayList<Note> contentSearch(ArrayList<Note> notes, String content)
    {
        ArrayList<Note> result = new ArrayList<>();

        for (Note not: notes) { // Search in every note

            if (not.getName().contains(content) | not.getText().contains(content))
             {
                 result.add(not);
             }
        }
        return result;
    }

    public static String viewFiltering (ArrayList<Note> notes, LocalDate date) // filtering by date
    {
        // !! Расширить - поиск по отдельному году, месяц и дню
        String result = "";

        for (Note not: notes) { // Search in every note

            if (not.getDate().isEqual(date))
            {
                result = result.concat(not.toString()); // adding proper note to result
            }
        }

        return "";
    }

    public static String viewFiltering (ArrayList<Note> notes, String tag) // filtering by tag
    {
        HashSet<String> preRes = new HashSet<>();

        for (Note not:notes){
            for (String s: tag.split("#")) {

                if (not.getHashTags().contains(s))
                 {
                     preRes.add(not.toString());
                 }
            }
        }
        return preRes.toString();
    }
}
