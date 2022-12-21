package Languages;

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

    public static void addNewJavaScriptQuestion() {

        System.out.println("Enter new JavaScript question: ");

        try {
            entityManager.getTransaction().begin();
            JavascriptQuestionsEntity jsqe = new JavascriptQuestionsEntity();
            jsqe.setQuestion(getUserInput());

            jsqe.setCorrectAnswers(getTrueOrFalse());
            entityManager.persist(jsqe);
            entityManager.getTransaction().commit();

            System.out.println("Question successfully added.");

        } catch (Exception e){
            System.out.println("That question already exists.");
        }
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
        int qCounter = 0;
        for (JavascriptQuestionsEntity javaScript : result) {
            System.out.println(javaScript.getQuestion());
            qCounter++;
            if (getUserInput().equalsIgnoreCase(javaScript.getCorrectAnswers()))
                score++;
        }
        double percent = 100 * (score / (double) qCounter);
        if (percent == 100)
            System.out.println("You had " + String.format("%.0f", percent) + "% correct answers.");
        else
            System.out.println("You had " + String.format("%.2f", percent) + "% correct answers.");

        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");

    }

    public static void deleteQuestion() {

        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

        try {
        entityManager.getTransaction().begin();
        JavascriptQuestionsEntity question = entityManager.find(JavascriptQuestionsEntity.class, userInput);

        entityManager.remove(question);
        entityManager.getTransaction().commit();

        System.out.println("Question was deleted successfully.");
        } catch (Exception e) {
            System.out.println("Could not find question ID. Nothing was deleted.");
        }
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

    public static void updateQuestion() {

        listAllQuestions();
        System.out.println("Enter the ID of the question you want to update: ");
        int userInput = Integer.parseInt(getUserInput());

        try {

            entityManager.getTransaction().begin();
            JavascriptQuestionsEntity question = entityManager.find(JavascriptQuestionsEntity.class, userInput);

            if(question == null) {
                System.out.println("Question ID does not exist");
                return;
            }

            System.out.println("Enter new question: ");
            question.setQuestion(getUserInput());

            question.setCorrectAnswers(getTrueOrFalse());

            entityManager.persist(question);
            entityManager.getTransaction().commit();

            System.out.println("Question successfully updated.\n");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

