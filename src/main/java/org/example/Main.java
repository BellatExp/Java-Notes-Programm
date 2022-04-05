package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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

        // User(s)
        // Creating Users / Reading users from file

        User user = new User("Neo","Zion");

        // Notes
        // Reading notes from file

        //  ALARM  - исключения - https://habr.com/ru/post/112042/

        Note not = new Note("Job","9:00 - Meeting","#Job");

        System.out.println(not.toString());

        String uLogin;
        String pass;

        System.out.print("Enter login: ");

        do {
            uLogin = scanner.next();

            if (uLogin.equals(user.getLogin()) == false)
             {
                 System.out.print("Error! Incorrect login!\n Enter login: ");
             }
            else break;
        } while(true);

        System.out.print("Enter password: ");

        do {
            pass = scanner.next();

            if (pass.equals(user.getPassword()) == false)
            {
                System.out.print("Error! Incorrect password!\n Enter password: ");
            }
            else break;
        } while(true);

        System.out.println("Access granted... Welcome, " + user.getLogin());

        int option = 0;

        do
         {
            printMenu();
            option = scanner.nextInt();

          switch (option)
          {
              case 1: // Add note
               {
                  String name;
                  String txt;
                  String tags;

                  System.out.println("\n Adding a new note:");
                  System.out.print("Enter note Name -> ");
                  name = scanner.next();

                  System.out.print("\nEnter note Text -> ");
                  txt = scanner.next();

                  System.out.print("\nEnter note HashTags; press Enter if there are no tags -> ");

                  // NOTE! - Need check the correctness of tags!

                  tags = scanner.next();

                  user.addNote(new Note(name, txt, tags));
                  break;
               }

              case 2: // Delete note
               {
                  int num;

                  System.out.println("\n Deleting Note:");
                  System.out.println(user.notesToString()); //  Show all notes to User
                  System.out.printf("Enter Number of note -> ");
                  num = scanner.nextInt();

                  user.deleteNode(num-1);
                  break;
               }

              case 3: // Edit note
               {
                  int endFl;
                  int noteNum;
                  int optionNum;

                  System.out.println("\n Editing Notes:");

                  do {
                      System.out.println(user.notesToString()); // Show all notes to User
                      System.out.printf("Enter the Number of note -> ");

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

                                  user.getOneNote(noteNum).setName(scanner.next());
                                  break;
                              }
                              case 2:{ // Edit Text
                                  System.out.println("Old Text: " + user.getOneNote(noteNum).getText());
                                  System.out.print("Enter new Text -> ");

                                  user.getOneNote(noteNum).setText(scanner.next());
                                  break;
                              }
                              case 3:{ // Edit hashTags
                                  System.out.println("Old hashTags: " + user.getOneNote(noteNum).getHashTags());
                                  System.out.print("Enter new hashTags -> ");

                                  user.getOneNote(noteNum).setHashTags(scanner.next());
                                  break;
                              }
                              default: System.out.println("Error! Wrong option number!"); break;
                          }
                      } while (optionNum != 4);

                      System.out.printf("Do you want to edit another note? 1 - yes; 0 - No;");
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

                       mode = scanner.nextInt();

                       switch (mode)
                       {
                           case 1:{ // filtering by date

                               // !! Расширить - поиск по отдельному году, месяц и дню

                               System.out.print("Enter date in format \"DD MM YYYY\"\n --> ");

                               int dd = scanner.nextInt();
                               int mm = scanner.nextInt();
                               int yy = scanner.nextInt();

                               LocalDate ttt = LocalDate.of(yy,mm,dd);

                               System.out.println(NotesHandler.viewFiltering(user.getNotes(),ttt));
                               break;
                           }

                           case 2:{ // filtering by hashTags

                               boolean fl = false;
                               String tags;

                               do {
                                   System.out.print("Enter hashTag(s)\n --> "); // US/UK YYYY-MM-DD ???

                                   // Проверка корректности введённого тэга(ов)

                                    tags = scanner.next();

                               } while (!fl);

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

                   System.out.printf("Еnter the search substring --> ");
                   content = scanner.next();

                   System.out.println("Search results:");
                   res = NotesHandler.contentSearch(user.getNotes(),content);// Search

                  break;
               }
              case 6: // Exit - save all notes to file
               {

               }

              default: System.out.println("Error! Wrong option number!"); break;
          }
         } while (option != 6);

        System.out.println("\n Good bye, Mr. Anderson.");
    }

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
}
