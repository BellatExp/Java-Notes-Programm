package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static void printMenu()
    {
        System.out.print( "Choose option:\n" +
                "1. Add note\n" +
                "2. Delete note\n" +
                "3. Edit note\n" +
                "4. View notes using filters\n" +
                "5. Search by \"Content\"\n" +
                "6. Exit (Notes will be saved in file)\n" +
                "----> ");
    }

    public static void readUserNotes(User user) throws Exception// reading and parsing users notes from file
    {
        try{
            String[] sMas;
            Pattern patt = Pattern.compile("(\\d\\d-\\d\\d-\\d+)\\s\\'(\\D+)?\\'\\s\\'(\\D+)\\'\\s?(\\#\\D+)?"); // RegExp

            FileReader freader = new FileReader("src/main/resources/notes/Notes.txt");
            BufferedReader bufreader = new BufferedReader(freader);

            String line;

            while((line = bufreader.readLine()) != null)
             {
                // System.out.println(line);

                 Matcher matcher = patt.matcher(line);
                 if (matcher.find())
                  {
                    user.addNote(new Note(matcher.group(2),matcher.group(3),matcher.group(4),Utility.stringToDate(matcher.group(1))));
                  }
             }
            bufreader.close();
            freader.close();

            for (Note bot : user.getNotes())
                System.out.println(bot.toString());

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeUsersNotes(ArrayList<Note> notes) { // re-writing usersNotes

        // '2022-04-06 ' Test-1 #

        try {
            FileWriter fwriter = new FileWriter("src/main/resources/notes/Notes.txt",false); // false - to overwrite;

            String prepNote;

            for (Note nt: notes) // preparing every note to write
             {
                 prepNote = "";
                 prepNote = prepNote.concat(nt.getDate().toString()) + " "; // date DD-MM-YYYY
                 prepNote = "'" + prepNote.concat(nt.getName()) + "' "; // 'name'
                 prepNote = prepNote.concat(nt.getText()) + " "; // text
                 prepNote = prepNote.concat(nt.tagsToString()); // tags - #gdfg
                 fwriter.write(prepNote);
             }

            fwriter.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean checkTagsString(String str)
    {
        if (str.isEmpty()) {
            return true;
         }
        else {
            Pattern patt = Pattern.compile("(#[\\w]+)+"); // RegExp
            Matcher matcher = patt.matcher(str);

            if (matcher.find())
             {
              if (matcher.group().equals(str)) return true;
             }
             return false;
         }
    }

    public static LocalDate stringToDate(String str)  // forming Date from String
    {
        String[] dat;
        dat = str.split("-");

        LocalDate locDate;
        locDate = LocalDate.of(Integer.parseInt(dat[2]),Integer.parseInt (dat[1]),Integer.parseInt (dat[0]));

        return locDate;
    }
}

