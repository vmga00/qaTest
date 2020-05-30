package gonzalez.victor.service;


import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import com.cgm.qanda.service.QuestionAnswerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)

public class TestQuestionAnswerServiceImpl {

    @Autowired
    QuestionAnswerService questionAnswerService;

    @Mock
    QuestionRepository questionRepository;

    @Before
    public void setup(){
        Question question = createQuestion("question1","answer1");
        questionRepository.save(question);
    }

    @Test
    public void testAddQuestionServiceWithSingleAnswer(){
        //given
        String questionStr = "question2";
        String answerStr = "answer2";
        Question question = createQuestion(questionStr,answerStr);
        //when
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        Mockito.when(questionRepository.findByQuestion(question.getQuestion())).thenReturn(Optional.ofNullable(question));
        questionAnswerService.addQuestion(questionStr,answerStr);
        //then
        List<String> answers =  questionAnswerService.getAnswers(questionStr);
        assertThat(answers).
                withFailMessage(String.format("\nThe answers are null\n")).
                isNotNull();
        assertThat(answers.get(0)).
                withFailMessage(String.format("\nThe Answer [%s] does not match [%s] \n",answers.get(0),answerStr)).
                isEqualTo(answerStr);
    }


    private Question createQuestion(String questionText, String answerText){
        Question question = new Question();
        question.setQuestion(questionText);
        Answer answer = new Answer();
        answer.setAnswer(answerText);
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;
    }




}
