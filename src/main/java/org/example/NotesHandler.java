package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Класс {@code NotesHandler} содержит вспомогательные методы отбора и поиска среди заметок пользователя.
 *
 * @author Lev 'aka' BellatExp (GitHub)
 * @version 1.3
 *
 */

public class NotesHandler { // Auxiliary class for working with Nodes

    /**
     *  Поиск среди записей "по контенту" среди Названия и Текстаи заметки
     *
     * @param notes Список заметок
     * @param content "контент" для поиска
     * @return
     */
    public static ArrayList<Note> contentSearch(ArrayList<Note> notes, String content) //
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


    /**
     *  Фильтрация списка заметок по дате
     *
     * @param notes список заметок
     * @param yy
     * @param mm
     * @param dd
     * @return
     */
    public static String viewFiltering (ArrayList<Note> notes, int yy, int mm, int dd) // filtering by date
    {
        // !! Расширить - поиск по отдельному году, месяц и дню

        // "Водопадный отбор" - Если указан год, отбор по году, затем, если есть месяц по месяцу (из отобранных ранеее), затем по дню

        ArrayList<Note> tmpNotes = new ArrayList<>(notes);

        if (yy != -1)
         {
             // Отбор путём удаления элементов по году из вспомогательного списка

             tmpNotes.removeIf(not -> not.getDate().getYear() != yy);
         }
        if (mm != -1)
         {
             // Отбор путём удаления элементов по месяцу из вспомогательного списка
             tmpNotes.removeIf(not -> not.getDate().getMonthValue() != mm);
         }
        if (dd != -1)
         {
             // Отбор путём удаления элементов по дню из вспомогательного списка
             tmpNotes.removeIf(not -> not.getDate().getDayOfMonth() != dd);
         }

        String result = "";

        for (Note not : tmpNotes)
        {
            result = result.concat(not.toString()); // adding proper note to result
        }


      /*  for (Note not: notes) { // Search in every note

            if (not.getDate().isEqual(date))
            {
                result = result.concat(not.toString()); // adding proper note to result
            }
        }*/

        return result;
    }

    /**
     * Фильтрация заметок по хештегам
     *
     * @param notes список заметок
     * @param tags хештеги в формате строки
     * @return
     */
    public static String viewFiltering (ArrayList<Note> notes, String tags) // filtering by tags
    {
       HashSet<String> preRes = new HashSet<>();

       ArrayList<String> tagslist = Utility.tagsToList(tags);

        for (Note not : notes){
            if (not.anyTag(tagslist)) preRes.add(not.toString());
        }

        // Return hashset?

        return preRes.toString();
    }
}
