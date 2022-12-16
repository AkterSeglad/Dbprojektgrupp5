package Languages;

import entity.JavaQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.SQLException;
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

    public static void deleteQuestion(){
        System.out.println("");

        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

        entityManager.getTransaction().begin();
        JavaQuestionsEntity question = entityManager.find( JavaQuestionsEntity.class, userInput);

        entityManager.remove(question);
        entityManager.getTransaction().commit();

        System.out.println("Question was deleted successfully.");
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

            System.out.println("Enter new question: ");
            question.setQuestion(getUserInput());

            question.setCorrectAnswers(getTrueOrFalse());

            entityManager.persist(question);
            entityManager.getTransaction().commit();

            System.out.println("Question successfully updated.\n");


        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Human error. Make sure to edit an existing question.");
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
        for (JavaQuestionsEntity Java : result) {
            System.out.println(Java.getQuestion());

            if (getUserInput().equals(Java.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }

}
