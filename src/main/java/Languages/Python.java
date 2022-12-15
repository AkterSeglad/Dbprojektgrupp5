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
}
