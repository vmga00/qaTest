package gonzalez.victor;

import antlr.StringUtils;
import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.AnswerRepository;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import com.cgm.qanda.service.QuestionAnswerService;
import com.cgm.qanda.service.QuestionAnswerServiceImpl;
import com.cgm.qanda.util.ValidationUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class ApplicationTest {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Before
    public void setup() {
        Question question = new Question();
        Answer answer = new Answer();
        question.setQuestion("what color is the sky");
        answer.setAnswer("blue");
        Set<Answer> answers = new HashSet<>();
        answers.add(answer);
        questionRepository.save(question);
    }


    /*
     * GIVEN I ask a question
     * AND   the question is NOT in the db
     * WHEN  I get the answer
     * THEN  I get "everything is 42..."
     */
    @Test
    @Description("When question is NOT in database then the answer is 42...")
    public void testQuestionNotInDB() {

    }

    @Test
    @Description("Validate that a question text has a valid length")
    public void testQuestionTextLengthIsValid() {
        //given
        String invalidStr = getInvalidLengthString();
        String validStr = "Test string";
        //when
        boolean isNull = ValidationUtil.validateLength(null);
        boolean isEmpty = ValidationUtil.validateLength("");
        boolean valid = ValidationUtil.validateLength(validStr);
        boolean invalid = ValidationUtil.validateLength(invalidStr);
        //then
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(isNull).withFailMessage(String.format("\nThe question is null.")).isFalse();
        softAssert.assertThat(isEmpty).withFailMessage(String.format("\nThe question is empty.")).isFalse();
        softAssert.assertThat(invalid).withFailMessage(String.format("\nThe question length is %s", invalidStr.length())).isFalse();
        softAssert.assertThat(valid).withFailMessage(String.format("\nThe question length is %s", validStr.length())).isTrue();
        softAssert.assertAll();
    }

    /*
     * GIVEN I ask a question
     * AND   the question is in the db
     * ANd   the question does not exactly match
     * WHEN  I get the answer
     * THEN  I get "everything is 42..."
     */



    /*
     * GIVEN I ask a question
     * AND   the question is in DB
     * AND   the question is exactly in the db
     * WHEN  I get the answer
     * THEN  I the related answer to the question
     */

    /* GIVEN I add a simple question
     * WHEN  I submit the simple question
     * THEN  the simple question is added to the system
     */
    /* GIVEN I add a question
     * AND   has more than 1 answer
     * WHEN  I submit the question
     * AND   more than 1 answer
     * THEN  the question is added to the system
     * AND   the answers are added to the system
     */
    /* GIVEN I choose to add a question from the menu
     * AND   is in the format of <question>?<answer1> ... <answerN>
     * WHEN  I press enter
     * THEN  the question is stored
     */
    /* GIVEN I choose to add a question from the menu
     * AND   is in the format of <question>?"<answer1>"<answer2>"..."<answerN>"
     * WHEN  I press enter
     * THEN  the question is stored
     */
    /* GIVEN I choose to add a question from the menu
     * AND   is NOT in the format of <question>?"<answer1>"..."<answerN>"
     * WHEN  I press enter
     * THEN  the question is NOT stored
     * AND   a message with the valid format is show
     */

    private String getInvalidLengthString() {
        char[] chars = new char[256];
        Arrays.fill(chars, '*');
        return new String(chars);
    }






    /* GIVEN
     * AND
     * WHEN
     * AND
     * THEN
     *
     * */


}
