package Languages;

import entity.JavaQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Java {

    static final Scanner scanner = new Scanner(System.in);
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void addNewJavaQuestion() {

        System.out.println("Enter new Java question: ");

        try {
        entityManager.getTransaction().begin();
        JavaQuestionsEntity jqe = new JavaQuestionsEntity();
        jqe.setQuestion(getUserInput());

        jqe.setCorrectAnswers(getTrueOrFalse());
        entityManager.persist(jqe);
        entityManager.getTransaction().commit();

        System.out.println("Question successfully added.");

        } catch (Exception e){
            System.out.println("That question already exists.");
        }

    }

    public static void deleteQuestion() {

        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

        try {
        entityManager.getTransaction().begin();
        JavaQuestionsEntity question = entityManager.find(JavaQuestionsEntity.class, userInput);

        entityManager.remove(question);
        entityManager.getTransaction().commit();

        System.out.println("Question was deleted successfully.");
        } catch (Exception e) {
        System.out.println("Could not find question ID. Nothing was deleted.");
        }
    }

    private static void listAllQuestions() {

        Query query = entityManager.createQuery("SELECT jq FROM JavaQuestionsEntity jq");
        List<JavaQuestionsEntity> list = query.getResultList();

        for (JavaQuestionsEntity javaQuestions : list) {
            System.out.println("ID: " + javaQuestions.getId() + " | " + "Question: " + javaQuestions.getQuestion()
                    + " | " + "Answer: " + javaQuestions.getCorrectAnswers());
            System.out.println("----------------------------------------------------------------------");
        }

    }

    public static void updateQuestion() {

        listAllQuestions();
        System.out.println("Enter the ID of the question you want to update: ");
        int userInput = Integer.parseInt(getUserInput());

        try {

            entityManager.getTransaction().begin();
            JavaQuestionsEntity question = entityManager.find(JavaQuestionsEntity.class, userInput);
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
            System.out.println("ID error. Make sure to edit an existing question.");
        }

    }


    private static String getTrueOrFalse() {
        System.out.println("True or false: ");
        return String.valueOf(scanner.nextLine().toUpperCase().charAt(0));
    }

    private static String getUserInput() {
        return scanner.nextLine();
    }

    public static void javaQuiz() {

        Query query = entityManager.createQuery("SELECT Java FROM JavaQuestionsEntity Java");

        List<JavaQuestionsEntity> result = query.getResultList();

        int score = 0;
        int qCounter = 0;
        for (JavaQuestionsEntity Java : result) {
            System.out.println(Java.getQuestion());
            qCounter++;
            if (getUserInput().equalsIgnoreCase(Java.getCorrectAnswers()))
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
}
