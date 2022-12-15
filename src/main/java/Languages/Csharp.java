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


    public static void addNewCSharpQuestion(){

        System.out.println("Enter a C# question: ");
        entityManager.getTransaction().begin();
        CsharpQuestionsEntity cSharpEntity = new CsharpQuestionsEntity();
        cSharpEntity.setQuestion(getUserInput());

        cSharpEntity.setCorrectAnswers(getTrueOrFalse());
        entityManager.persist(cSharpEntity);
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

    public static void CSharpQuiz() {

        Query query = entityManager.createQuery("SELECT cSharp FROM CsharpQuestionsEntity cSharp");

        List<CsharpQuestionsEntity> result = query.getResultList();

        int score = 0;
        for (CsharpQuestionsEntity cSharp : result) {
            System.out.println(cSharp.getQuestion());

            if (getUserInput().equals(cSharp.getCorrectAnswers()))
                score++;
        }
        System.out.println("Score: " + score + " out of " + result.size());
        System.out.println("------------");
    }
}
