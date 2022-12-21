import Languages.*;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            printMenu();
            String userInput = scanner.nextLine().toUpperCase();

            switch (userInput) {
                case "1":
                    Java.javaQuiz();
                    break;
                case "2":
                    Csharp.CSharpQuiz();
                    break;
                case "3":
                    Javascript.javaScriptQuiz();
                    break;
                case "4":
                    Python.pythonQuiz();
                    break;
                case "5":
                    Cplusplus.cPlusPlusQuiz();
                case "ADMIN":
                    adminMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
            }
        }

    }

    private static void adminMenu() {
        boolean flag = true;
        while(flag) {
            System.out.println("------ ADMIN MENU ------\n" +
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

        System.out.println("---- Add a question ---- ");
        System.out.println("What language? \n1. Java\n2. C#\n3. JavaScript\n4. Python\n5. C++");
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
            case "5":
                Cplusplus.addNewCPlusPlusQuestion();
                break;
        }
    }

    private static void deleteQuestion() {

        System.out.println("---- Delete a question ---- ");
        System.out.println("What language? \n1. Java\n2. C#\n3. JavaScript\n4. Python\n5. C++");

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
            case "5":
                Cplusplus.deleteQuestion();
                break;
        }
    }

    private static void updateQuestion() {

        System.out.println("---- Update a question ---- ");
        System.out.println("What language? \n1. Java\n2. C#\n3. JavaScript\n4. Python\n5. C++\n0. Back");

        switch (getUserInput()) {
            case "1":
                Java.updateQuestion();
                break;
            case "2":
               Csharp.updateQuestion();
                break;
            case "3":
                Javascript.updateQuestion();
                break;
            case "4":
                Python.updateQuestion();
                break;
            case "5":
                Cplusplus.updateQuestion();
                break;
            case "0":
                adminMenu();
                break;
        }
    }

    private static String getUserInput() {
        return scanner.nextLine().toUpperCase();
    }

    public static void printMenu() {
        System.out.println("------ MENU ------\n" +
                        "1  - Java Quiz\n" +
                        "2  - C# Quiz\n" +
                        "3  - JavaScript Quiz\n" +
                        "4  - Python Quiz\n" +
                        "5  - C++ Quiz\n" +
                        "0  - EXIT");
    }
}
