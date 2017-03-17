package service;

import model.Question;
import model.ResultQuize;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Professional on 2017-03-16.
 */
public class QuestionService {
    private EntityManager entityManager;

    private QuestionService() {
    }

    public QuestionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public QuestionService setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    public List<Question> getRandomQuestionList() {
        List<Question> questionList = new ArrayList<Question>();
        List<Integer> numberList = new ArrayList<Integer>();
        Random random = new Random();
        while (questionList.size() < 4) {
            Integer randomNumber = random.nextInt(4) + 1;
            if (!numberList.contains(randomNumber)) {
                numberList.add(randomNumber);
                TypedQuery<Question> typedQuery = entityManager
                        .createQuery("SELECT e FROM Question e where e.id='" + randomNumber.toString() + "'", Question.class);
                Question question = typedQuery.getSingleResult();
                questionList.add(question);
            }
        }
        return questionList;
    }

    public boolean askAQuestion(Question question) {
        boolean result = false;
        Scanner scanner = new Scanner(System.in);
        String questionString = question.getQuestion();
        System.out.println(questionString);
        System.out.println("Select answer A, B, C or D");
        System.out.println("A. : " + question.getAnswers().get(0));
        System.out.println("B. : " + question.getAnswers().get(1));
        System.out.println("C. : " + question.getAnswers().get(2));
        System.out.println("D. : " + question.getAnswers().get(3));
        String yourAnswer = scanner.nextLine();
        yourAnswer = yourAnswer.toLowerCase();
        System.out.println("Your answer is : " + yourAnswer);
        if (yourAnswer.equals(question.getAnswer())) {
            result = true;
        }
        return result;
    }

    public ResultQuize startQuize() {
        List<Question> questionList = this.getRandomQuestionList();
        ResultQuize resultQuize = new ResultQuize();
        for (Question question : questionList) {
            if (askAQuestion(question)) {
                resultQuize.addPoint();
            }
            resultQuize.addCountMaxresult();
        }
        return resultQuize;
    }

}
