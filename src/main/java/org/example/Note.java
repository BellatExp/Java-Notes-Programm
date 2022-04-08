package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Класс {@code Note} представляет заметку пользователя.
 *
 * @author Lev 'aka' BellatExp (GitHub)
 * @version 1.3
 *
 */
public class Note {

    /**
     * Название заметки.
     */
    private String name;

    /**
     * Текст заметки.
     */
    private String text;

    /**
     * Дата заметки создания заметки.
     */
    private LocalDate date;

    /**
     * Список хештегов заметки. Каждый тег хранится в формате '#tag1'.
     */
    private ArrayList<String> hashTags;

    /**
     * Конструктор по умолчанию инициализирует все поля вновь созданного объекта пустыми значениями кроме поля date.
     *   Поле date инициализуется значением текущей даты создания заметки.
     */
    public Note()
    {
        this.name = "";
        this.text = "";
        this.date = LocalDate.now();
        this.hashTags = new ArrayList<>();
    }

    /**
     * Конструктор с параметрами - создаёт новую заметку с переданными значениями.
     *   Хештеги передаются в формате строки.
     *   Поле date инициализуется значением текущей даты создания заметки.
     *
     * @param name Назваание заметки
     * @param text Текст заметки
     * @param tags Теги заметки в строкоовм формате
     */
    public Note(String name, String text, String tags){ // Constructor for creating a new obj
        this();
        if (name != null) this.name = name;
        this.text = text;
        this.hashTags = Utility.tagsToList(tags);
    }
        /*public Note(String name, String text, ArrayList<String> tags){ // Constructor for creating a new obj
        this.name = name;
        this.text = text;
        this.date = LocalDate.now();
        this.hashTags = new ArrayList<>();
        this.hashTags.addAll(tags);
    }*/
    /*public Note(String name, String text, ArrayList<String> tags, LocalDate date){ // Constructor for creating obj after reading a file
        this.name = name;
        this.text = text;
        this.date = date;
        this.hashTags = new ArrayList<>();
        this.hashTags.addAll(tags);
    }*/

    /**
     * Конструктор с параметрами - создаёт новую заметку с переданными значениями.
     *   Хештеги передаются в формате строки.
     *   Поле date инициализуется переданным значением date.
     *
     * @param name Назваание заметки
     * @param text Текст заметки
     * @param tags Теги заметки в строкоовм формате
     * @param date дата создания заметки
     */
    public Note(String name, String text, String tags, LocalDate date){ // Constructor for creating obj after reading a file
        this.name = "";
        if (name != null) this.name = name;
        this.text = text;
        this.date = date;
        this.hashTags = Utility.tagsToList(tags);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHashTags(ArrayList<String> tags) {
        this.hashTags.clear();
        this.hashTags.addAll(tags);
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

    /**
     * Функция пребразования списка хештегов заметки в строку.
     *
     * @return возвращает список хештегов в формате строки
     */
    public String tagsToString() {

        String res = "";

       for(String str:hashTags) res = res.concat(str);

        return res;
     }

    /**
     * Функция поиска любого хештега из переданного списка среди хештегов заметки.
     *   После нахождения первого подхоящего хештега функция возвращается true.
     *
     * @param tagslist список хештегов для поиска
     * @return Результат поиска: true - какой-либо хештег найден; false - в заметке отсутствуют все переданными для поиска хештеги.
     */
     public boolean anyTag(ArrayList<String> tagslist){
        for (String loctag : tagslist)
         {
            if (this.hashTags.contains(loctag)) return true;
         }
        return false;
     }

    /**
     *  Перегруженная функция toString().
     *
     * @return Сфомированная строка, содержащая значения полей объекта.
     */

    @Override
     public String toString() { // all note to string
        return "[" + date.toString() + "] " +
                     name + " " +
                     text + " " +
                     hashTags.toString();
    }
}
