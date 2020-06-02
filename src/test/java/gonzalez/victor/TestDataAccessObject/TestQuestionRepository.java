package gonzalez.victor.TestDataAccessObject;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataobject.Question;
import gonzalez.victor.TestCore.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class TestQuestionRepository extends TestBase {

    @Test
    public void testFindByQuestion() {
        //given
        Question question = createQuestion("question2", "answer2");
        questionRepository.save(question);
        //when
        Question lookedUpQuestion = questionRepository.findByQuestion(question.getQuestion()).get();
        //then
        assertThat(lookedUpQuestion.getQuestion()).
                withFailMessage(String.format("\nThe lookedUpQuestion [%s] does not match original question [%s]", lookedUpQuestion.getQuestion(), question.getQuestion())).
                isEqualTo(question.getQuestion());
    }

}
