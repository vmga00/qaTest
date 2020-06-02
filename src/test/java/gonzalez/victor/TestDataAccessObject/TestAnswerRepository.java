package gonzalez.victor.TestDataAccessObject;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import gonzalez.victor.TestCore.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class TestAnswerRepository extends TestBase {

    @Test
    public void testFindByQuestionId() {
        //given
        int count = (int) questionRepository.count();
        Question savedQuestion = questionRepository.findByQuestion("question1").get();
        Set<Answer> savedAnswers = savedQuestion.getAnswers();
        //when
        List<Answer> searchedAnswers = answerRepository.findByQustionId(savedQuestion.getId());
        //then
        assertThat(compareAnswers(savedAnswers, searchedAnswers)).withFailMessage("The original answer and the searched ones are different");
    }

    private boolean compareAnswers(Set<Answer> savedQuestionAnswers, List<Answer> searchedQuestionAnswers) {
        if (savedQuestionAnswers.size() != searchedQuestionAnswers.size()) {
            int savedQuestionAnswersSize = savedQuestionAnswers.size();
            savedQuestionAnswers.retainAll(searchedQuestionAnswers);
            if (savedQuestionAnswersSize == savedQuestionAnswers.size()) {
                return true;
            }
        }
        return false;
    }


}
