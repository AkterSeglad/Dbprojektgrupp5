package Languages;

import entity.JavaQuestionsEntity;
import entity.PythonQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class Python {

    static final Scanner scanner = new Scanner(System.in);
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void addNewPythonQuestion(){

        System.out.println("Enter a Python question");
        entityManager.getTransaction().begin();
        PythonQuestionsEntity pqe = new PythonQuestionsEntity();
        pqe.setQuestion(getUserInput());

        pqe.setCorrectAnswers(getTrueOrFalse());
        entityManager.persist(pqe);
        entityManager.getTransaction().commit();

        System.out.println("Question successfully added.");
    }

    public static void pythonQuiz(){
        Query query = entityManager.createQuery("SELECT Python FROM PythonQuestionsEntity  Python");

        List<PythonQuestionsEntity> result = query.getResultList();

        int score = 0;
        for (PythonQuestionsEntity python : result) {
            System.out.println(python.getQuestion());

            if (getUserInput().equals(python.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }


    private static String getTrueOrFalse() {
        System.out.println("True or false: ");
        return String.valueOf(scanner.nextLine().toUpperCase().charAt(0));
    }

    private static String getUserInput() {
        return scanner.nextLine();
    }

    public static void deleteQuestion(){
        System.out.println("");

        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

        entityManager.getTransaction().begin();
        PythonQuestionsEntity question = entityManager.find( PythonQuestionsEntity.class, userInput);

        entityManager.remove(question);
        entityManager.getTransaction().commit();

        System.out.println("Question was deleted successfully.");
    }

    private static void listAllQuestions() {

        Query query = entityManager.createQuery("SELECT pq FROM PythonQuestionsEntity pq");
        List<PythonQuestionsEntity> list = query.getResultList();

        for (PythonQuestionsEntity pythonQuestions : list) {
            System.out.println("ID: " + pythonQuestions.getId() + " | " + "Question: " + pythonQuestions.getQuestion()
                    + " | " + "Answer: " + pythonQuestions.getCorrectAnswers());
            System.out.println("----------------------------------------------------------------------");
        }

    }

}
