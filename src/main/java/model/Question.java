package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Professional on 2017-03-16.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
    private String answer;

    @ElementCollection
    @CollectionTable(name = "answers")
    private List<String> answers;


    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Question setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Question setAnswers(List<String> answers) {
        this.answers = answers;
        return this;
    }
}
