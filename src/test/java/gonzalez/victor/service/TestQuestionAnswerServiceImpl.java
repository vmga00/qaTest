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

import javax.swing.text.html.Option;
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
    public void setup() {
        Question question = createQuestion("question1", "answer1");
        questionRepository.save(question);
    }

    @Test
    public void testAddQuestionService() {
        //given
        String questionStr = "question2";
        String answerStr = "answer2a\"anser2b\"answer2c";
        Question question = createQuestion(questionStr, answerStr);
        //when
        mockServices(question);
        questionAnswerService.addQuestion(questionStr, answerStr);
        //then
        List<String> answers = questionAnswerService.getAnswers(questionStr);
        assertThat(answers).
                withFailMessage(String.format("\nThe answers are null\n")).
                isNotNull();
        assertThat(answers.size()).
                withFailMessage(String.format("\nThe answers are null\n")).
                isEqualTo(answerStr.split("\"").length);
    }

    @Test
    public void testGetAnswersService() {
        //given
        String answersText = "question3";
        Question question = createQuestion("question3", answersText);
        //when
        mockServices(question);
        List<String> answers = questionAnswerService.getAnswers(question.getQuestion());
        //then
        assertThat(answers).withFailMessage("The answers to the question were null").isNotNull();
        assertThat(answers.isEmpty()).withFailMessage("The answers to the question were empty");
        assertThat(answers.size()).withFailMessage("the size of the answer does not match").isEqualTo(answersText.split("\"").length);
    }

    @Test
    public void testSaveQuestionService(){
        //given
        Question question = createQuestion("question4","answer4");
        //when
        mockServices(question);
        Question savedQuestion = saveQuestion(question);
        //then
        assertThat(savedQuestion).withFailMessage("The question is null").isNotNull();
        assertThat(savedQuestion.getQuestion()).withFailMessage("The saved question and original question are different").isEqualTo(question.getQuestion());
        assertThat(savedQuestion.getAnswers()).withFailMessage("The saved answers and the original answers are different").isEqualTo(question.getAnswers());
    }


    private Question createQuestion(String questionText, String answerText) {
        Question question = new Question();
        question.setQuestion(questionText);
        Answer answer = new Answer();
        answer.setAnswer(answerText);
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;
    }

    private void mockSaveQuestionService(Question question) {
        Mockito.when(questionRepository.save(question)).thenReturn(question);
    }

    private void mockAnswerQuestionService(Question question) {
        Mockito.when(questionRepository.findByQuestion(question.getQuestion())).thenReturn(Optional.ofNullable(question));
    }

    private void mockServices(Question question) {
        mockSaveQuestionService(question);
        mockAnswerQuestionService(question);
    }

    private Question saveQuestion(Question question){
        Question savedQuestion = null;
        try {
            savedQuestion = questionAnswerService.save(question);
        }catch (Exception e){
            e.addSuppressed(e);
        }
        return savedQuestion;
    }

}
