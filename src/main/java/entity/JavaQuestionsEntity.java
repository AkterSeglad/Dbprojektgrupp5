package entity;

import jakarta.persistence.*;

@Entity
@NamedQuery(query = "SELECT javaQuestions from JavaQuestionsEntity javaQuestions WHERE javaQuestions.question = :javaQ", name = "javaQuestionQuery")
@NamedQuery(query = "SELECT javaQuestions from JavaQuestionsEntity javaQuestions", name = "bokQuery")
@Table(name = "java_questions", schema = "dbprojektgrupp5", catalog = "")
public class JavaQuestionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "question")
    private String question;
    @Basic
    @Column(name = "correct_answers")
    private String correctAnswers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JavaQuestionsEntity that = (JavaQuestionsEntity) o;

        if (id != that.id) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (correctAnswers != null ? !correctAnswers.equals(that.correctAnswers) : that.correctAnswers != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (correctAnswers != null ? correctAnswers.hashCode() : 0);
        return result;
    }
}
