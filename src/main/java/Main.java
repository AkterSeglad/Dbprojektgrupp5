import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        while(true){
            printMenu();
            String userInput = scanner.nextLine();

            switch (userInput){
                case "1":
                    javaQuiz();
                    break;
            }
        }

    }

    private static void javaQuiz() {
        String sql = ""
        Query q = entityManager.createQuery()

    }


    public static void printMenu() {
        System.out.println(
               "1  - Java Quiz\n" +
               "2  - JavaScript Quiz\n" +
               "3  - C# Quiz\n" +
               "4  - Python Quiz\n" +
               "5  - ????\n" +
               "6  - ????\n" +
               "0  - EXIT");
    }

}
