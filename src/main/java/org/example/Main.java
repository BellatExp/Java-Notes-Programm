package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    //============= SANDBOX ============
        /*LocalDate ld = LocalDate.now();

        System.out.println(ld.getDayOfMonth());
        System.out.println(ld.getMonthValue());
        System.out.println(ld.getYear());

        LocalDate ent = LocalDate.of(2022,5,4);

        System.out.println(ld.getDayOfMonth());
        System.out.println(ld.getMonthValue());
        System.out.println(ld.getYear());

        System.out.print("Enter date in format DD MM YYYY");

        int d = scanner.nextInt();
        int m = scanner.nextInt();
        int y = scanner.nextInt();

        LocalDate ggg = LocalDate.of(y,m,d);

        System.out.println(ggg.getDayOfMonth());
        System.out.println(ggg.getMonthValue());
        System.out.println(ggg.getYear()); */
    //=====================================

        //  ALARM  - исключения - https://habr.com/ru/post/112042/
        
        // try to use help func 
        // String nulltoempty() // only for strings
        //  return s == null ? "" : s

        User user = new User("Neo","Zion");

        //-- User Authentication

        /**
         *  -!!- Перенести аутентификацию пользователя в отдельный метод + сделать unit-тест на него -!!-
         */

       /* do {
            System.out.print("Enter login: ");

            if (scanner.nextLine().equals(user.getLogin()) == false)
             {
                System.out.print("Error! Incorrect login!\n");
                continue;
             }

            System.out.print("Enter password: ");

            if (scanner.nextLine().equals(user.getPassword()) == false)
             {
                System.out.print("Error! Incorrect password!\n");
             }
            else break;
        } while(true);

        System.out.println("\nAccess granted... Welcome, " + user.getLogin()); */

        // Reading Users's Notes from file
          Utility.readUserNotes(user);

       // Note not = new Note("Job","9:00 - Meeting","#Job");
       // System.out.println(not.toString());

        int option = 0; // option from menu

        do
         {
            Utility.printMenu();
            option = scanner.nextInt();

          switch (option)
          {
              case 1: // Add note
               {
                  String name = "";
                  String txt = "";
                  String tags = "";

                  System.out.println("\nAdding a new note:");
                  System.out.print("Enter note Name; If there is NO name => press Enter... \n --> ");
                  name = name.concat(consoleReader.readLine());

                  do {
                      System.out.print("\nEnter note Text --> ");

                      txt = txt.concat(consoleReader.readLine());

                      System.out.print("\nThe note must have the text!");

                  } while (txt.isEmpty());

                  do {
                      Utility.printTagsMenu();
                      tags = tags.concat(consoleReader.readLine());

                      if (Utility.checkTagsString(tags)) {
                          user.addNote(new Note(name, txt, tags));
                          break;
                       }
                      else System.out.println("Error! Incorrect format of the entered tags!");
                  } while (true);

                  break;
               }

              case 2: // Delete note
               {
                   if (user.getNumberOfNotes() == 0)
                   {
                       System.out.println("\n There is not a single note!");
                       break;
                   }

                  int num;

                  System.out.println("\n Deleting Note:");
                  System.out.println(user.notesToString()); // Show all notes to User

                  do {
                      System.out.print("Enter Number of note to delete -> ");
                      num = scanner.nextInt();

                     if (user.deleteNode(num-1)) break;
                      else
                         System.out.println(" Error! Incorrect number of Note!");
                  } while(true);

                  break; // end case 2 - Delete note
               }

              case 3: // Edit note
               {
                  int endFl = -1;
                  int noteNum;
                  int optionNum;

                  System.out.println("\n Editing Notes:");

                  do {
                      System.out.println(user.notesToString()); // Show all notes to User
                      System.out.print("Enter the Number of note -> ");

                      noteNum = scanner.nextInt();

                      do{
                          System.out.print("Choose option:\n" +
                                  "1. Edit Name\n" +
                                  "2. Edit Text\n" +
                                  "3. Edit hashTags\n" +
                                  "4. Exit\n" +
                                  "----> ");

                          optionNum = scanner.nextInt();

                          switch (optionNum){
                              case 1:{ // Edit name;
                                  System.out.println("Old Name: " + user.getOneNote(noteNum).getName());
                                  System.out.print("Enter new Name -> ");

                                  user.modifyNoteName(consoleReader.readLine(),noteNum);

                                  break;
                              }
                              case 2:{ // Edit Text
                                  System.out.println("Old Text: " + user.getOneNote(noteNum).getText());
                                  System.out.print("Enter new Text -> ");

                                  user.modifyNoteText(consoleReader.readLine(),noteNum);

                                  break;
                              }
                              case 3:{ // Edit hashTags
                                  String tags = "";
                                  System.out.println("Old hashTags: " + user.getOneNote(noteNum).tagsToString());
                                  // Обернуть првоерку и ввод в отдельный метод ?

                                  do {
                                      Utility.printTagsMenu();
                                      tags = consoleReader.readLine();

                                      if (Utility.checkTagsString(tags)) {
                                          break;
                                      }
                                      else System.out.println("Error! Incorrect format of the entered tags!");
                                  } while (true);

                                  user.modifyNoteTags(consoleReader.readLine(),noteNum);

                                  break;
                              }
                              default: System.out.println("Error! Wrong option number!"); break;
                          }
                      } while (optionNum != 4);

                      System.out.print("Do you want to edit another note? 1 - Yes; 0 - No;");
                      endFl = scanner.nextInt();

                  } while (endFl != 0);

                  break; //  end case 3 - Edit note
               }

              case 4: // View notes using filters
               {
                   int mode = -1;

                   System.out.println("Users notes:\n" + user.notesToString());

                   do {
                       System.out.print("Choose a filter for viewing notes:\n " +
                                    "1. Filtering by date\n " +
                                    "2. Filtering by hashTags\n" +
                      //   AHTUNG! - 3. - Both?
                                    "3. Exit\n" +
                                    " ---> ");

                      // String sss = scanner.nextLine();

                       mode = scanner.nextInt();

                       switch (mode)
                       {
                           case 1:{ // filtering by date
                               // !! Расширить - поиск по отдельному году, месяц и дню

                               int yy = -2;
                               int mm = -2;
                               int dd = -2;

                               String str;

                               System.out.print("Enter Year or/and Month or/and Day;\n   If no value => Press Enter...\n");

                               consoleReader.readLine();

                               System.out.print("Year --> ");

                               str = consoleReader.readLine();

                               if (str.isEmpty()) yy = -1;
                                else
                                   yy = Integer.parseInt(str);

                               System.out.print("Month --> ");
                               str = consoleReader.readLine();
                               if (str.isEmpty()) mm = -1;
                               else
                                   mm = Integer.parseInt(str);

                               System.out.print("Day --> ");
                               str = consoleReader.readLine();
                               if (str.isEmpty()) dd = -1;
                               else
                                   dd = Integer.parseInt(str);

                               System.out.println(NotesHandler.viewFiltering(user.getNotes(),yy,mm,dd)); // old

                             //  LocalDate ttt = LocalDate.of(yy,mm,dd);
                             //  System.out.println(NotesHandler.viewFiltering(user.getNotes(),ttt)); // old
                               break;
                           }

                           case 2:{ // filtering by hashTags
                               String tags;

                               do {
                                   System.out.print("\nEnter note hashTags;\n Examples: 1.#work 2.#work_hard 3.#noPain#no_Gain...\n If there are NO hashTags => press Enter \n --> ");
                                   tags = consoleReader.readLine();

                                   if (Utility.checkTagsString(tags)) {
                                       break;
                                   }
                                   else System.out.println("Error! Incorrect format of the entered tags!");
                               } while (true);

                               System.out.println(NotesHandler.viewFiltering(user.getNotes(),tags));

                               break;
                           }
                           case 3: break; //

                           default: System.out.println("Error! Wrong option number!"); break;
                       }
                   } while (mode != 3);

                   break; // case 4 - View notes using filters
               }

              case 5: // Search by "Content" - return ArrayList
               {
                   String content;
                   ArrayList<Note> res = new ArrayList<>();

                   System.out.print("\nEnter the search substring --> ");
                   content = consoleReader.readLine();

                   System.out.println("Search results:");
                   res = NotesHandler.contentSearch(user.getNotes(),content);// Search

                   for (Note not : res)
                    {
                        System.out.println(not.toString());
                    }

                  break;
               }
              case 6: // Exit - save all notes to file
               {
                Utility.writeUsersNotes(user.getNotes()); // Re-Write users notes to file
               }
              default: System.out.println("Error! Wrong option number!"); break;
          }
         } while (option != 6);

        System.out.println("\n Good bye, Mr. Anderson.");
    }
}
