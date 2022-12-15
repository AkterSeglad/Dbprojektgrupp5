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

    public static void addNewJavaQuestion(){

        System.out.println("Enter a Java question");
        entityManager.getTransaction().begin();
        JavaQuestionsEntity jqe = new JavaQuestionsEntity();
        jqe.setQuestion(getUserInput());

        jqe.setCorrectAnswers(getTrueOrFalse());
        entityManager.persist(jqe);
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

    public static void javaQuiz() {

        Query query = entityManager.createQuery("SELECT Java FROM JavaQuestionsEntity Java");

        List<JavaQuestionsEntity> result = query.getResultList();

        int score = 0;
        for (JavaQuestionsEntity Java : result) {
            System.out.println(Java.getQuestion());

            if (getUserInput().equals(Java.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }
}
