package service;

import model.Question;
import model.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Professional on 2017-03-16.
 */
public class DBStart {
    private List<User> usersList = new ArrayList<User>();
    private List<Question> questionList = new ArrayList<Question>();


    public void userStartFillInDB(EntityManager entityManager) {
        this.usersList = startUserList();
        for (int i = 0; i < usersList.size(); i++) {
            entityManager.getTransaction().begin();
            entityManager.persist(usersList.get(i));
            entityManager.getTransaction().commit();
        }
    }

    public void questionsStartFillInDB(EntityManager entityManager) {
        this.questionList = startQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            entityManager.getTransaction().begin();
            entityManager.persist(questionList.get(i));
            entityManager.getTransaction().commit();
        }
    }

    public List<User> startUserList() {
        User user = new User("arek28", "arek28");
        User user1 = new User("krzysztof83", "krzysztof83");
        User user2 = new User("vitalij", "vitalij");
        User user3 = new User("seba", "seba");
        User user4 = new User("nowszy94", "nowszy94");
        User user5 = new User("grzegorzgozdziak", "grzegorzgozdziak");
        User user6 = new User("kate", "kate");
        User user7 = new User("marta", "marta");
        User user8 = new User("michalbasinski", "michalbasinski");
        User user9 = new User("mikolaj", "mikolaj");
        User user10 = new User("db", "db");
        User user11 = new User("gronos1", "gronos1");
        User user12 = new User("mateusz", "mateusz");
        User user13 = new User("simon", "simon");

        usersList.add(user);
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
        usersList.add(user5);
        usersList.add(user6);
        usersList.add(user7);
        usersList.add(user8);
        usersList.add(user9);
        usersList.add(user10);
        usersList.add(user11);
        usersList.add(user12);
        usersList.add(user13);
        return usersList;
    }

    public List<Question> startQuestionList() {
        Question question = new Question();
        question.setQuestion("Typ zmiennych o nazwie float przechowuje:");
        question.setAnswer("c");
        List<String> answers = new ArrayList<String>();
        String answer1 = "liczby calkowite";
        String answer2 = "napisy";
        String answer3 = "liczby zmiennoprzecinkowe";
        String answer4 = "pojedyncze znaki";
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        question.setAnswers(answers);
        questionList.add(question);

        Question question1 = new Question();
        question1.setQuestion("Wstrzymanie wykonania programu na czas 2 sekund uzyskasz instrukcja:");
        question1.setAnswer("d");
        List<String> answers1 = new ArrayList<String>();
        String answer11 = "Wait(2000)";
        String answer12 = "Stop(2000)";
        String answer13 = "Delay(2000)";
        String answer14 = "Sleep(2000)";
        answers1.add(answer11);
        answers1.add(answer12);
        answers1.add(answer13);
        answers1.add(answer14);
        question1.setAnswers(answers1);
        questionList.add(question1);

        Question question2 = new Question();
        question2.setQuestion("Ktora deklaracja jest zapisana poprawnie?");
        question2.setAnswer("a");
        List<String> answers2 = new ArrayList<String>();
        String answer21 = "int x;";
        String answer22 = "integer x;";
        String answer23 = "x:integer;";
        String answer24 = "x=integer;";
        answers2.add(answer21);
        answers2.add(answer22);
        answers2.add(answer23);
        answers2.add(answer24);
        question2.setAnswers(answers2);
        questionList.add(question2);

        Question question3 = new Question();
        question3.setQuestion("Z jakiego przedzialu zostanie wylosowana liczba: rand()%10+2");
        question3.setAnswer("c");
        List<String> answers3 = new ArrayList<String>();
        String answer31 = "0..9";
        String answer32 = "2..9";
        String answer33 = "2..11";
        String answer34 = "0..11";
        answers3.add(answer31);
        answers3.add(answer32);
        answers3.add(answer33);
        answers3.add(answer34);
        question3.setAnswers(answers3);
        questionList.add(question3);

        Question question4 = new Question();
        question4.setQuestion("Ile razy wykona sie petla: for (int i=4; i>=-2; i--)");
        question4.setAnswer("d");
        List<String> answers4 = new ArrayList<String>();
        String answer41 = "ani razu";
        String answer42 = "nieskonczenie wiele razy";
        String answer43 = "6";
        String answer44 = "7";
        answers4.add(answer41);
        answers4.add(answer42);
        answers4.add(answer43);
        answers4.add(answer44);
        question4.setAnswers(answers4);
        questionList.add(question4);
        return questionList;
    }


}
