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

/**
 * Класс {@code Utility} содержит вспомогательные методы для работы с объектами классов User и Note.
 *
 * @author  BellatExp (GitHub)
 * @version 1.3
 */
public class Utility {

    /**
     * Вывод в консоль меню опций для работы с записями.
     */
    public static void printMenu() {
        System.out.print("Choose option:\n" +
                "1. Add note\n" +
                "2. Delete note\n" +
                "3. Edit note\n" +
                "4. View notes using filters\n" +
                "5. Search by \"Content\"\n" +
                "6. Exit (Notes will be saved in file)\n" +
                "----> ");
    }

    /**
     * Вывод в консоль информирующего сообщения для ввода хештегов.
     */
    public static void printTagsMenu() {
         System.out.print("\nEnter note hashTags;\n Examples: 1.#work 2.#work_hard 3.#noPain#no_Gain...\n If there are NO hashTags => press Enter \n --> ");
     }

    /**
     * Аутентификация пользователя user по указанному логину login и паролю password
     *
     * @param user Пользователь
     * @param login Логин
     * @param password Пароль
     * @return true - Аутентификация пройдена. false - неверный логин и/или пароль.
     */
     public static boolean userAuthentication(User user, String login, String password) {
          if (user.getLogin().equals(login) & user.getPassword().equals(password)) {
              return true;
           }
          else return false;
      }

     public static boolean checkLogin(User user, String login) {
         if (user.getLogin().equals(login)) return true;
         else
             return false;
     }
    public static boolean checkPassword(User user, String password) {
        if (user.getPassword().equals(password)) return true;
        else
            return false;
    }

    /**
     * Чтение и парсинг заметок из файла. Считанные заметки сохарняются в список заметок пользователя user.
     *   Парсинг заметок выполняется посредством использования регулярного выражения - (\d+-\d\d-\d\d)\s\'(\D+)?\'\s\'(\D+)\'\s?(\#\D+)?
     *
     * @param user Пользователь
     */
    public static boolean readUserNotes(User user) { // reading and parsing users notes from file
        try{
            String[] sMas;
            Pattern patt = Pattern.compile("(\\d+-\\d\\d-\\d\\d)\\s\\'(\\D+)?\\'\\s\\'(\\D+)\\'\\s?(\\#\\D+)?"); // RegExp

            FileReader freader = new FileReader("src/main/resources/notes/Notes.txt");
            BufferedReader bufreader = new BufferedReader(freader);

            String line;

            while((line = bufreader.readLine()) != null)
             {
                 Matcher matcher = patt.matcher(line);
                 if (matcher.find())
                  {
                    user.addNote(new Note(matcher.group(2),matcher.group(3),matcher.group(4),Utility.stringToDate(matcher.group(1))));
                  }
             }
            bufreader.close();
            freader.close();

            //user.getNotes().forEach(System.out::println);

            return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Запись списка заметок в файл, с предварительным преобразованием заметки к следующему формату: YYYY-MM-DD 'note_name' 'note_text' #tag1#tag2...
     *
     * @param notes Список заметок
     */
    public static boolean writeUsersNotes(ArrayList<Note> notes) { // re-writing usersNotes

        try {
            FileWriter fwriter = new FileWriter("src/main/resources/notes/Notes.txt",false); // false - to overwrite;

            String prepNote;

            for (Note nt: notes) // preparing every note to write
             {
                 prepNote = "";
                 prepNote = prepNote.concat(nt.getDate().toString() + " "); // date DD-MM-YYYY
                 prepNote = prepNote.concat("'" + nt.getName() + "' "); // 'name'
                 prepNote = prepNote.concat("'" + nt.getText()) + "' "; // text
                 prepNote = prepNote.concat(nt.tagsToString() + "\n"); // tags - #gdfg
                 fwriter.write(prepNote);
             }
            fwriter.close();

            return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Проверка корректности введённых пользователем хештегов.
     *
     * @param str Строка с хештегами
     * @return true - хештеги пршли проверку; false - ошибка в формате хештегов
     */
    public static boolean checkTagsString(String str) {
        if (str.isEmpty()) {
            return true;
         }
        else {
            Pattern patt = Pattern.compile("#[\\w]+"); // RegExp
            Matcher matcher = patt.matcher(str);

            String tmp = "";

            while (matcher.find())
             {
                 tmp = tmp.concat(matcher.group());
             }

            return tmp.equals(str);
         }
    }

    /**
     * Формирование объекта LocalDate из строки str
     *
     * @param str Строка с датой в формате YYYY-MM-DD
     * @return Дата
     */
    public static LocalDate stringToDate(String str) { // forming Date from String

        String[] dat;
        dat = str.split("-");

        LocalDate locDate;
        locDate = LocalDate.of(Integer.parseInt(dat[0]),Integer.parseInt(dat[1]),Integer.parseInt(dat[2]));

        return locDate;
    }

    /**
     * Разбор строки хештегов на отдельные хештеги.
     *
     * @param tags Строка с хештегами
     * @return Список хештегов
     */
    public static ArrayList<String> tagsToList(String tags) {
         ArrayList<String> res = new ArrayList<>();

         if (tags == null) return res; // ret empty list

         Pattern patt = Pattern.compile("(#\\w+)"); // RegExp
         Matcher matcher = patt.matcher(tags);

         while (matcher.find())
          {
              res.add(matcher.group());
          }

         return res;
     }
}
