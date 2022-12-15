import Languages.Csharp;
import Languages.Java;
import Languages.Javascript;
import Languages.Python;
import entity.CsharpQuestionsEntity;
import entity.JavaQuestionsEntity;
import entity.JavascriptQuestionsEntity;
import entity.PythonQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        boolean quit = false;

        while (!quit) {
            printMenu();
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    Java.javaQuiz();
                    break;
                case "2":
                    Javascript.javaScriptQuiz();
                    break;
                case "3":
                    Csharp.CSharpQuiz();
                    break;
                case "4":
                    Python.pythonQuiz();
                    break;
                case "ADMIN":
                    adminMenu();
                    break;
                case "0":
                    System.exit(0);
            }
        }

    }

    private static void adminMenu() {
        boolean flag = true;
        while(flag) {
            System.out.println(
                    "1  - Add question\n" +
                            "2  - Update question\n" +
                            "3  - Delete question\n" +
                            "0  - Back to Main Menu"
            );
            switch (getUserInput()) {
                case "1":
                    addQuestion();
                    break;
                case "2":
                    updateQuestion();
                    break;
                case "3":
                    deleteQuestion();
                    break;
                case "0":
                    System.out.println("Redirecting back to menu..");
                    flag = false;
                    break;
            }
        }
    }

    private static void addQuestion() {

        System.out.println("What language? \n1. Java\n2. C#\n3. JavaScript\n4. Python");
        switch (getUserInput()){
            case "1":
                Java.addNewJavaQuestion();
                break;
            case "2":
                Csharp.addNewCSharpQuestion();
                break;
            case "3":
                Javascript.addNewJavaScriptQuestion();
                break;
            case "4":
                Python.addNewPythonQuestion();
                break;

        }
    }

    private static void deleteQuestion() {

        System.out.println("What language? \n1. Java\n2. C#\n3. JavaScript\n4. Python");
        switch (getUserInput()){
            case "1":
                Java.deleteQuestion();
                break;
            case "2":
                Csharp.deleteQuestion();
                break;
            case "3":
                Javascript.deleteQuestion();
                break;
            case "4":
                Python.deleteQuestion();
                break;

        }
    }

    private static void updateQuestion() {
        //Hur gör vi änna?
        System.out.println("What do you want to update?");
        entityManager.getTransaction().begin();
        //  var bok = entityManager.find( BokEntity.class, getUserInput() );

    }


    private static String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }

    public static void printMenu() {
        System.out.println(
                "1  - Java Quiz\n" +
                        "2  - C# Quiz\n" +
                        "3  - JavaScript Quiz\n" +
                        "4  - Python Quiz\n" +
                        "0  - EXIT");
    }

}
