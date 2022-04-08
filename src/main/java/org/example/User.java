package org.example;

import java.util.ArrayList;

/**
 * Класс {@code User} представляет пользователя.
 *
 * @author Lev 'aka' BellatExp (GitHub)
 * @version 1.3
 */
public class User {

    /**
     * Логин
     */
    private String login;

    /**
     * Пароль
     */
    private String password;

    /**
     * Список заметок пользователя
     */
    private ArrayList<Note> notes;

    /**
     * Конструктор с параметрами - создаёт нового пользвоателя с указанными логином и паролем.
     *   Список заметок пользователя создаётся пустым.
     *
     * @param login Логин
     * @param password Пароль
     */
    public User(String login, String password) {

        this.login = login;
        this.password = password;
        this.notes = new ArrayList<>(); //
    }

    /**
     * Конструктор с параметрами - создаёт нового пользвоателя с указанными логином, паролем и списком заметок.
     *
     * @param login Логин
     * @param password Пароль
     * @param notes Список заметок
     */
    public User(String login, String password, ArrayList<Note> notes){
        this(login,password);
        this.notes.addAll(notes);
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


    /**
     * Изменение названия заметки с переданным индексом на новое.
     *   Выполняется проверка переданного индекса.
     *
     * @param newName Новое имя
     * @param noteNum Индекс изменяемой заметки
     * @return true - изменение выполнено успешно; false - заметка с переданным индексом отсутствует;
     */
    public boolean modifyNoteName(String newName, int noteNum)
     {
         if (checkNoteNum(noteNum)){
             this.notes.get(noteNum).setName(newName);
             return true;
         }
         else return false;
     }

    /**
     * Изменение текста заметки с переданным индексом на новый.
     *   Выполняется проверка переданного индекса.
     *
     * @param newText Новый текст
     * @param noteNum Индекс изменяемой заметки
     * @return true - изменение выполнено успешно; false - заметка с переданным индексом отсутствует;
     */
    public boolean modifyNoteText(String newText, int noteNum)
     {
         if (checkNoteNum(noteNum)){
             this.notes.get(noteNum).setText(newText);
             return true;
         }
         else return false;
     }

    /**
     * Изменение списка хештегов заметки с переданным индексом на новый. Новые хештеги передаются в формате строки.
     *   Выполняется проверка переданного индекса.
     *
     * @param newTags Новые теги в строковом формате
     * @param noteNum Индекс изменяемой заметки
     * @return true - изменение выполнено успешно; false - заметка с переданным индексом отсутствует;
     */
    public boolean modifyNoteTags(String newTags, int noteNum)
     {
         if (checkNoteNum(noteNum)){
             this.notes.get(noteNum).setHashTags(Utility.tagsToList(newTags));
             return true;
         }
         else return false;
     }

    /**
     * Мето добавления одной заметки в список заметок пользваотеля.
     * @param note добавляемая заметок
     */
    public void addNote(Note note) {
        this.notes.add(note);
    }

    /**
     *
     * @param x индекс удалямой заметки
     * @return true - заметка успещно удалена; false - заметка с переданным индексом отсутствует;
     */
    public boolean deleteNode(int x) {
        if (checkNoteNum(x)) {
            this.notes.remove(x);
            return true;
        }
        else return false;
    }

    /**
     * Метод проверки существования переданного индекса в списке заметок.
     * @param x Индекс заметки в списке
     * @return true - индекс существует; false - заметка с переданным индексом отсутствует;.
     */
    public boolean checkNoteNum(int x)
    {
        if (x < this.notes.size() & x >= 0) return true;
        else return false;
    }

    /**
     * Метд преобразования списка заметок пользваотеля в единую строку.
     *
     * @return Все заметки из списка в формате строки
     */
    public String notesToString() //
     {
         int i = 1;
         String res = "";
         for (Note tmp : notes) { // Show all notes to User

            res = res.concat(Integer.toString(i) + ". " + tmp.toString() + "\n");
             i++;
         }
         return res;
     }
}
