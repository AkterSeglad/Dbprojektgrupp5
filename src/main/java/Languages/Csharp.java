package Languages;

import entity.CsharpQuestionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Csharp {

    static final Scanner scanner = new Scanner(System.in);

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();


    public static void addNewCSharpQuestion() {

        System.out.println("Enter new C# question: ");

        try {
        entityManager.getTransaction().begin();
        CsharpQuestionsEntity cSharpEntity = new CsharpQuestionsEntity();
        cSharpEntity.setQuestion(getUserInput());

        cSharpEntity.setCorrectAnswers(getTrueOrFalse());
        entityManager.persist(cSharpEntity);
        entityManager.getTransaction().commit();

        System.out.println("Question successfully added.");

        } catch (Exception e) {
            System.out.println("That question already exists.");
        }
    }

    public static void deleteQuestion() {


        listAllQuestions();

        System.out.println("Enter the ID of the question you want to delete: ");
        int userInput = Integer.parseInt(getUserInput());

        try {

        entityManager.getTransaction().begin();
        CsharpQuestionsEntity question = entityManager.find(CsharpQuestionsEntity.class, userInput);

        entityManager.remove(question);
        entityManager.getTransaction().commit();
        System.out.println("Question was deleted successfully.");
        } catch (Exception e) {
            System.out.println("Could not find Question ID. Nothing was deleted.");
        }

    }

    private static void listAllQuestions() {

        Query query = entityManager.createQuery("SELECT cq FROM CsharpQuestionsEntity cq");
        List<CsharpQuestionsEntity> list = query.getResultList();


        for (CsharpQuestionsEntity csharpQuestions : list) {
            System.out.println("ID: " + csharpQuestions.getId() + " | " + "Question: " + csharpQuestions.getQuestion()
                    + " | " + "Answer: " + csharpQuestions.getCorrectAnswers());
            System.out.println("----------------------------------------------------------------------");
        }

    }

    private static String getTrueOrFalse() {
        System.out.println("True or false: ");
        return String.valueOf(scanner.nextLine().toUpperCase().charAt(0));
    }

    private static String getUserInput() {
        return scanner.nextLine();
    }

    public static void CSharpQuiz() {

        Query query = entityManager.createQuery("SELECT cSharp FROM CsharpQuestionsEntity cSharp");

        List<CsharpQuestionsEntity> result = query.getResultList();

        int score = 0;
        int qCounter = 0;
        for (CsharpQuestionsEntity cSharp : result) {
            System.out.println(cSharp.getQuestion());
            qCounter++;
            if (getUserInput().equalsIgnoreCase(cSharp.getCorrectAnswers()))
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

    public static void updateQuestion() {

        listAllQuestions();
        System.out.println("Enter the ID of the question you want to update: ");
        int userInput = Integer.parseInt(getUserInput());

        try {

            entityManager.getTransaction().begin();
            CsharpQuestionsEntity question = entityManager.find(CsharpQuestionsEntity.class, userInput);

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

}