package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Класс {@code Note} представляет заметку пользователя.
 *
 * @author BellatExp (GitHub)
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
     * Список хештегов заметки. Каждый хештег хранится в формате '#tag_namе'.
     */
    private ArrayList<String> hashTags;

    /**
     * Создаёт новую пустую заметку. Все поля инициализированы пустыми значениями, кроме поля date - инициализируется текущей датой.
     */
    public Note()
    {
        this.name = "";
        this.text = "";
        this.date = LocalDate.now();
        this.hashTags = new ArrayList<>();
    }

    /**
     * Создаёт новую строку с именем name, текстом text и хештегами tags. Хештеги передаются в формате строки.
     *   Поле date инициализируется текущей датой.
     *
     * @param name Название заметки
     * @param text Текст заметки
     * @param tags Хештеги в формате строки
     */
    public Note(String name, String text, String tags){ // Constructor for creating a new obj
        this();
        if (name != null) this.name = name;
        this.text = text;
        this.hashTags = Utility.tagsToList(tags);
    }
    /* public Note(String name, String text, ArrayList<String> tags){ // Constructor for creating a new obj
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
     * Создаёт новую строку с именем name, текстом text, хештегами tags и датой date. Хештеги передаются в формате строки.
     *
     * @param name Название заметки
     * @param text Текст заметки
     * @param tags Хештеги в формате строки
     * @param date Дата создания заметки
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
     * Преобразует список хештегов в строку
     *
     * @return Строка хештегов заметки
     */
    public String tagsToString() {

        String res = "";

       for(String str:hashTags) res = res.concat(str);

        return res;
     }

    /**
     * Поиск любого хештега из переданного списка среди хештегов заметки.
     *    После нахождения первого подхоящего хештега функция возвращается true.
     *
     * @param tagslist Список хештегов для поиска
     * @return True - какой-либо хештег найден; false - хештеги в заметке не найдены.
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
