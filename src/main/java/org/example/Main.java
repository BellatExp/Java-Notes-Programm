package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


/* Краткая инструкция:
  1) При запуске программы введите следующие логин и пароль:
   -- Логин: Neo
   -- Пароль: DejaVu
  2) Выберете желамую опцию в меню;
  3) Следуйте указанным инструкциям;
  4) Повторитре п. 2-3.
 */

public class Main {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Instructions:\n" +
                "1) Enter the following username and password:\n" +
                "-- Login: Neo\n-- Password: DejaVu\n" +
                "2) Select an option from the menu;\n" +
                "3) Follow the instructions;\n" +
                "4) Repeat steps 2-3;\n");

        System.out.println("Wake up, Neo... Wake up... It's me - DejaVu");

        User user = new User("Neo","DejaVu");

        // User Authentication
        // Ver 2.0
        do {
            System.out.print("Enter login: ");

            if (Utility.checkLogin(user,consoleReader.readLine()) == false) {
                System.out.print("Error! Incorrect login!\n");
                continue;
            }

            System.out.print("Enter password: ");

            if (Utility.checkPassword(user,consoleReader.readLine()) == false) {
                System.out.print("Error! Incorrect password!\n");
             }
            else break;

        } while(true);
        // ==============================================================

        // ALT ver 2.0
        /*String login = "";
        String pass = "";


        do {
            System.out.print("Enter login: ");
            login = consoleReader.readLine();

            System.out.print("Enter password: ");
            login = consoleReader.readLine();

            if (Utility.userAuthentication(user,login,pass) == false)
              System.out.print("Error! Incorrect login/password!\n");

        } while (Utility.userAuthentication(user,login,pass) == false);
        // ==============================================================
        */

        System.out.println("\nAccess granted... Welcome back, Mr. Anderson.");

        // Reading Users's Notes from file
          Utility.readUserNotes(user);

        int option = 0; // option from menu

        do
         {
            Utility.printMenu();
            option = scanner.nextInt();

       // Проблема - после метода scanner.nextInt() остаётся \n, => scanner.nextLine() автоматически подхватывает \n
                 // => крайне неудобно считывать целую строку с помощью Scanner'a

          switch (option)
          {
              case 1: // Add note
               {
                  String name = "";
                  String txt = "";
                  String tags = "";

                  System.out.println("\nAdding a new note:");
                  System.out.print("Enter note Name; If there is NO name => press Enter... \n --> ");

                  name = consoleReader.readLine();

                  do {
                      System.out.print("Enter note Text --> ");

                      txt = consoleReader.readLine();

                      System.out.print("\nThe note must have the text!");

                  } while (txt.isEmpty());

                  do {
                      Utility.printTagsMenu();
                      tags = consoleReader.readLine();

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

                                 // consoleReader.readLine();
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

                              case 4: break;

                              default: System.out.println("Error! Wrong option number!"); break;
                          }
                      } while (optionNum != 4);

                         System.out.print("Do you want to edit another note? 1 - Yes; 0 - No; -> ");
                      do{
                          endFl = scanner.nextInt();
                          if ( endFl != 0 & endFl != 1)
                          {
                              System.out.println("Error! Wrong Option!");
                              System.out.print("Do you want to edit another note? 1 - Yes; 0 - No; -> ");
                          }
                          else break;
                      } while (true);

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
                                    "2. Filtering by hashTags\n " +
                                    "3. Exit\n" +
                                    " ---> ");

                       mode = Integer.parseInt(consoleReader.readLine());

                       switch (mode)
                       {
                           case 1:{ // filtering by date; NOTE - Extended ver

                               int yy = -1;
                               int mm = -1;
                               int dd = -1;

                               String str;

                               System.out.print("Enter Year/Month/Day;\n If no YY/MM/DD value => Press Enter...\n");

                               System.out.print("Year --> ");

                               str = consoleReader.readLine();

                               if (!str.isEmpty())
                                   yy = Integer.parseInt(str);

                               System.out.print("Month --> ");
                               str = consoleReader.readLine();
                               if (!str.isEmpty())
                                   mm = Integer.parseInt(str);

                               System.out.print("Day --> ");
                               str = consoleReader.readLine();
                               if (!str.isEmpty())
                                   dd = Integer.parseInt(str);

                               NotesHandler.viewFiltering(user.getNotes(),yy,mm,dd).forEach(System.out::println); // new ver

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

                               NotesHandler.viewFiltering(user.getNotes(),tags).forEach(System.out::println); // new ver

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

                   System.out.print("Enter the search substring --> ");
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
                break;
               }

              default: System.out.println("Error! Wrong option number!"); break;
          }
         } while (option != 6);

        System.out.println("\n Good bye, Mr. Anderson.");
    }
}
