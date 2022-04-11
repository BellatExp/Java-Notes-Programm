package org.example;

import java.util.ArrayList;

/**
 * Класс {@code User} представляет пользователя с логином, паролем и списком личных заметок.
 *
 * @author BellatExp (GitHub)
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
     * Список заметок
     */
    private ArrayList<Note> notes;

    /**
     *  Создаёт нового пользвоателя с логином login и паролем password. Список заметок инициализуется пустым списком.
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
     * Создаёт нового пользвоателя с логином login, паролем password и списком заметок notes.
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
     * Заменяет название заметки с указанными индекском на новое. Значение передаваемого индекса проверяется.
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
     * Заменяет текст заметки с указанными индекском на новое. Значение передаваемого индекса проверяется.
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
     * Заменяет список хештегов с указанными индекском на новое. Новые хештеги передаются в формате строки.
     *   Значение передаваемого индекса проверяется.
     *
     * @param newTags Новые хештеги в формате строки
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
     * Добавляет указаннную заметку в список заметко пользователя
     *
     * @param note Заметка, которая будет добавлена в список
     */
    public void addNote(Note note) {
        this.notes.add(note);
    }

    /**
     * Удаляет заметку с указанными индексом из списка заметок пользователя.
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
     * Проверяет существование заметки с указанным индексом.
     *
     * @param x Индекс заметки в списке
     * @return true - заметка с указанным индексом существует; false - заметка с указанным индексом отсутствует;.
     */
    public boolean checkNoteNum(int x)
    {
        if (x < this.notes.size() & x >= 0) return true;
        else return false;
    }

    /**
     * Преобразаует список заметок в строку.
     *
     *
     * @return Список заметок в формате строки
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
