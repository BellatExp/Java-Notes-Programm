package org.example;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Класс {@code NotesHandler} содержит вспомогательные функции отбора и поиска заметок пользователя.
 *
 * @author BellatExp (GitHub)
 * @version 1.3
 *
 */

public class NotesHandler { // Auxiliary class for working with Nodes

    /**
     *  Поиск заметок по принципу "содержит" строку content в названии и в тексте заметки
     *
     * @param notes Список заметок
     * @param content "Контент" для поиска
     *
     * @return Список найденных заметок
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
     *  Фильтрация списка заметок notes по отдельным составляющим даты - году year, месяцу month и дню day.
     *
     * @param notes Список заметок
     * @param year Год
     * @param month Месяц
     * @param day День
     * @return Список отобранных заметок
     */
    public static ArrayList<Note> viewFiltering (ArrayList<Note> notes, int year, int month, int day)
    {
        ArrayList<Note> tmpNotes = new ArrayList<>(notes);

        /* "Водопадный" отбор:
         Если указан год -> отбор по году. Затем, если указан месяц -> по месяцу(из отобранных ранее).
          Затем, если указан день -> по дню (из отобранных ранее).
         */
        if (year != -1) {
            tmpNotes.removeIf(not -> not.getDate().getYear() != year); // Отбор по году из вспомогательного списка
         }
        if (month != -1) {
            tmpNotes.removeIf(not -> not.getDate().getMonthValue() != month); // Отбор по месяцу из вспомогательного списка
         }
        if (day != -1) {
            tmpNotes.removeIf(not -> not.getDate().getDayOfMonth() != day); // Отбор по дню из вспомогательного списка
         }

        return tmpNotes;
    }

    /**
     * Фильтрация списка заметок notes по хештегам tags.
     *
     * @param notes список заметок
     * @param tags хештеги в формате строки
     * @return Множество отобранных заметок
     */
    public static HashSet<Note> viewFiltering (ArrayList<Note> notes, String tags) // filtering by tags
    {
       HashSet<Note> preRes = new HashSet<>();

        for (Note not : notes){
            if (not.anyTag(Utility.tagsToList(tags))) preRes.add(not);
        }

        return preRes;

    }
}
