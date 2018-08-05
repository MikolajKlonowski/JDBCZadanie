import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDao bd = new BookDao();
        int odpowiedz = 0;
        while (0 == odpowiedz) {
            System.out.println("Co chcesz zrobić ?");
            System.out.println("Wybierze:" + "\n" + "1 - dodaj ksziążke " + "\n" + "2 - usuń książke" + "\n"
                    + "3 - zakutalizuj dane ksiązki" + "\n" + "4 - sprawdź dane książki ");
            int wybórUzytkownika = sc.nextInt();
            if (wybórUzytkownika >= 1 && wybórUzytkownika <= 4) {
                switch (wybórUzytkownika) {
                    case 1: {
                        System.out.print("Podaj ");
                        System.out.print("tytuł: ");
                        String title = sc.next();
                        System.out.print("autora: ");
                        String author = sc.next();
                        System.out.print("rok: ");
                        int year = sc.nextInt();
                        System.out.print("isbn numer: ");
                        int isbn = sc.nextInt();
                        Books book = new Books(title, author, year, isbn);
                        bd.librarySave(book);
                        break;
                    }
                    case 2:
                        System.out.println("Podaj isbn:");
                        bd.delete(sc.nextInt());

                        break;
                    case 3:
                        System.out.print("Podaj ");
                        System.out.print("isbn numer: ");
                        int isbn1 = sc.nextInt();
                        System.out.print("tytuł: ");
                        String title1 = sc.next();
                        System.out.print("autora: ");
                        String author1 = sc.next();
                        System.out.println();
                        System.out.print("rok: ");
                        int year1 = sc.nextInt();
                        Books book1 = new Books(title1, author1, year1, isbn1);
                        bd.libraryUpdate(book1);
                        break;
                    case 4:
                        System.out.println("Podaj isbn:");
                        int podanyIsbn = sc.nextInt();
                        Books book3 = bd.libraryRead(podanyIsbn);
                        System.out.println(book3);
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Podano złą liczbe" + "\n" + "Dowidzenia!");
            }

            System.out.println("Czy chcesz wyjść ?" + "\n" + "Y/N");
            sc.nextLine();
            String a = sc.next();
            if (a.equals("Y") || a.equals("y")) {
                odpowiedz = 1;
                bd.close();

            } else {
                odpowiedz = 0;
            }
        }
    }
}
