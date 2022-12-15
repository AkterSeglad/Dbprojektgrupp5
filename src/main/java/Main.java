import entity.CsharpQuestionsEntity;
import entity.JavaQuestionsEntity;
import entity.JavascriptQuestionsEntity;
import entity.PythonQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        boolean quit = false;

        while(!quit){
            printMenu();
            String userInput = scanner.nextLine();

            switch (userInput){
                case "1":
                    javaQuiz();
                    break;
                case "2":
                    javaScriptQuiz();
                    break;
                case "3":
                    CSharpQuiz();
                    break;
                case "4":
                    pythonQuiz();
                    break;
                case "0":
                    System.exit(0);
            }
        }

    }

    private static void javaQuiz() {

        Query query = entityManager.createQuery("SELECT Java FROM JavaQuestionsEntity Java");

        List<JavaQuestionsEntity> result = query.getResultList();

        int score = 0;
        for(JavaQuestionsEntity Java:result) {
            System.out.println(Java.getQuestion());

            if (getUserInput().equals(Java.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }

    private static void javaScriptQuiz(){

        Query query = entityManager.createQuery("SELECT javaScript FROM JavascriptQuestionsEntity javaScript");

        List<JavascriptQuestionsEntity> result = query.getResultList();

        int score = 0;
        for(JavascriptQuestionsEntity javaScript:result) {
            System.out.println(javaScript.getQuestion());

            if (getUserInput().equals(javaScript.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");

    }

    private static void CSharpQuiz(){

        Query query = entityManager.createQuery("SELECT cSharp FROM CsharpQuestionsEntity cSharp");

        List<CsharpQuestionsEntity> result = query.getResultList();

        int score = 0;
        for(CsharpQuestionsEntity cSharp:result) {
            System.out.println(cSharp.getQuestion());

            if (getUserInput().equals(cSharp.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }

    private static void pythonQuiz() {

        Query query = entityManager.createQuery("SELECT python FROM PythonQuestionsEntity python");

        List<PythonQuestionsEntity> result = query.getResultList();

        int score = 0;
        for(PythonQuestionsEntity python:result) {
            System.out.println(python.getQuestion());

            if (getUserInput().equals(python.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }

    private static String getUserInput(){
        return scanner.nextLine().toUpperCase();
    }

    public static void printMenu() {
        System.out.println(
               "1  - Java Quiz\n" +
               "2  - JavaScript Quiz\n" +
               "3  - C# Quiz\n" +
               "4  - Python Quiz\n" +
               "0  - EXIT");
    }

}
