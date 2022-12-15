package Languages;

import entity.JavaQuestionsEntity;
import entity.JavascriptQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class Javascript {

    static final Scanner scanner = new Scanner(System.in);
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void addNewJavaScriptQuestion(){

        System.out.println("Enter a JavaScript question");
        entityManager.getTransaction().begin();
        JavascriptQuestionsEntity jsqe = new JavascriptQuestionsEntity();
        jsqe.setQuestion(getUserInput());

        jsqe.setCorrectAnswers(getTrueOrFalse());
        entityManager.persist(jsqe);
        entityManager.getTransaction().commit();

        System.out.println("Question successfully added.");
    }

    private static String getTrueOrFalse() {
        System.out.println("True or false: ");
        return String.valueOf(scanner.nextLine().toUpperCase().charAt(0));
    }

    private static String getUserInput() {
        return scanner.nextLine();
    }

    public static void javaScriptQuiz() {

        Query query = entityManager.createQuery("SELECT javaScript FROM JavascriptQuestionsEntity javaScript");

        List<JavascriptQuestionsEntity> result = query.getResultList();

        int score = 0;
        for (JavascriptQuestionsEntity javaScript : result) {
            System.out.println(javaScript.getQuestion());

            if (getUserInput().equals(javaScript.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");

    }
    public static void deleteQuestion(){
        System.out.println("");

        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

        entityManager.getTransaction().begin();
        JavascriptQuestionsEntity question = entityManager.find( JavascriptQuestionsEntity.class, userInput);

        entityManager.remove(question);
        entityManager.getTransaction().commit();

        System.out.println("Question was deleted successfully.");
    }

    private static void listAllQuestions() {

        Query query = entityManager.createQuery("SELECT jsq FROM JavascriptQuestionsEntity jsq");
        List<JavascriptQuestionsEntity> list = query.getResultList();

        for (JavascriptQuestionsEntity javascriptQuestions : list) {
            System.out.println("ID: " + javascriptQuestions.getId() + " | " + "Question: " + javascriptQuestions.getQuestion()
                    + " | " + "Answer: " + javascriptQuestions.getCorrectAnswers());
            System.out.println("----------------------------------------------------------------------");
        }

    }

}

