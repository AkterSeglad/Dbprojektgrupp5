package Languages;

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

        try {

            System.out.println("Enter new Python question: ");
            entityManager.getTransaction().begin();
            PythonQuestionsEntity pqe = new PythonQuestionsEntity();
            pqe.setQuestion(getUserInput());

            pqe.setCorrectAnswers(getTrueOrFalse());
            entityManager.persist(pqe);
            entityManager.getTransaction().commit();

            System.out.println("Question successfully added.");
        }
        catch (Exception e){
            System.out.println("That question already exists.");
        }
    }

    public static void pythonQuiz(){
        Query query = entityManager.createQuery("SELECT Python FROM PythonQuestionsEntity  Python");

        List<PythonQuestionsEntity> result = query.getResultList();

        int score = 0;
        int qCounter = 0;
        for (PythonQuestionsEntity python : result) {
            System.out.println(python.getQuestion());
            qCounter++;
            if (getUserInput().equalsIgnoreCase(python.getCorrectAnswers()))
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


    private static String getTrueOrFalse() {
        System.out.println("True or false: ");
        return String.valueOf(scanner.nextLine().toUpperCase().charAt(0));
    }

    public static void updateQuestion() {

        listAllQuestions();
        System.out.println("Enter the ID of the question you want to update: ");
        int userInput = Integer.parseInt(getUserInput());

        try {

            entityManager.getTransaction().begin();
            PythonQuestionsEntity question = entityManager.find(PythonQuestionsEntity.class, userInput);
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


        } catch (Exception e){
            e.printStackTrace();

        }

    }


    private static String getUserInput() {
        return scanner.nextLine();
    }

    public static void deleteQuestion(){

        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

            try {

                entityManager.getTransaction().begin();
                PythonQuestionsEntity question = entityManager.find(PythonQuestionsEntity.class, userInput);

                entityManager.remove(question);
                entityManager.getTransaction().commit();

                System.out.println("Question was deleted successfully.");
            }catch (Exception e){
                System.out.println("Could not find question ID. Nothing was deleted");
            }
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
