package com.cgm.qanda.dataaccessobject;


import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class QuestionRepository_IntegrationTest {

    @Autowired
    QuestionRepository questionsRepo;

    @Before
    public void setup(){
        Question question = new Question();
        Answer answer = new Answer();
        question.setQuestion("question1");
        answer.setAnswer("answer1");
        Set<Answer> answers = new HashSet<>();
        answers.add(answer);
        questionsRepo.save(question);
    }

    @Test
    public void whenFindByQuestion(){
        //given
        String questionText = "question1";
        //when
        Optional<Question> found = questionsRepo.findByQuestion(questionText);
        //then
        Question q = found.get();
        assertThat(q.getQuestion()).
                withFailMessage(String.format("******\nAsked: %s\nGot:%s\n\n",questionText,q.getQuestion())).
                isEqualTo(questionText);
    }

}
