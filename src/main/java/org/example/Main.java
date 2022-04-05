package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // User(s)
        // Creating Users / Reading users from file

        User user = new User("Neo","Zion");

        // Notes
        // Reading notes from file

        Note not = new Note("Job","9:00 - Meeting","#Job");

        System.out.println(not.toString());

        Scanner scanner = new Scanner(System.in);
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

        System.out.println("Access granted... Welcome " + user.getLogin());

        int option = 0;

        while (option != 6)
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

                  System.out.println("\n Adding note:");
                  System.out.print("Enter note Name -> ");
                  name = scanner.next();

                  System.out.print("\nEnter note Text -> ");
                  txt = scanner.next();

                  System.out.print("\nEnter note HashTags; press Enter if there are no tags -> ");
                  tags = scanner.next();

                  user.addNote(new Note(name, txt, tags));
                  break;
               }

              case 2: // Delete note
               {
                  int num;
                  int i = 1;

                  System.out.println("\n Deleting note:");

                  System.out.println(user.notestoString()); //  Show all notes to User

                  System.out.printf("Enter Number of note -> ");
                  num = scanner.nextInt();

                  user.deleteNode(num);
                  break;
               }

              case 3: // Edit note
               {
                  int num;
                  int i = 1;

                  System.out.println("\n Editing note:");

                  System.out.println(user.notestoString()); // Show all notes to User

                  System.out.printf("Enter Number of note -> ");
                  num = scanner.nextInt();

                   System.out.println( "Choose option:" +
                           "1. Add note\n" +
                           "2. Delete note\n" +
                           "3. Edit note\n" +
                           "4. View notes using filters\n" +
                           "5. Search by \"Content\"\n" +
                           "6. Exit (Notes will be saved in file)" +
                           " -> ");


                  break;
               }

              case 4: // View notes using filters
               {

                   break;
               }

              case 5: // Search by "Content"

                  break;

              default: System.out.println("Error! Wrong option number!"); break;
          }
         }

        System.out.println("\n Good bye, Mr. Anderson.");
    }

    public static void printMenu()
    {
        System.out.println( "Choose option:" +
                            "1. Add note\n" +
                            "2. Delete note\n" +
                            "3. Edit note\n" +
                            "4. View notes using filters\n" +
                            "5. Search by \"Content\"\n" +
                            "6. Exit (Notes will be saved in file)" +
                            " -> ");
    }
}
