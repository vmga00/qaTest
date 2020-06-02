package gonzalez.victor.TestUtil;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.util.ValidationUtil;
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
public class TestValidationUtil extends TestBase {

    @Test
    public void testQuestionLengthIsNotNull() {
        //given
        String questionStr = null;
        //when
        boolean questionLength = validateLength(questionStr);
        //then
        assertThat(questionLength).
                withFailMessage(String.format("The question is NULL")).
                isFalse();
    }

    @Test
    public void testQuestionLengthIsNotEmpty() {
        //given
        String questionStr = getVariableLengthString(0);
        //when
        boolean questionLength = validateLength(questionStr);
        //then
        assertThat(questionLength).
                withFailMessage(String.format("The question %s size is %s", questionStr, questionStr.length())).
                isFalse();
    }

    @Test
    public void testQuestionLengthIsValid() {
        //given
        String questionStr = getVariableLengthString(255);
        //when
        boolean questionLength = validateLength(questionStr);
        //then
        assertThat(questionLength).
                withFailMessage(String.format("The question %s size is %s", questionStr, questionStr.length())).
                isTrue();
    }

    @Test
    public void testQuestionLengthIsInvalid() {
        //given
        String questionStr = getVariableLengthString(256);
        //when
        boolean questionLength = validateLength(questionStr);
        //then
        assertThat(questionLength).
                withFailMessage(String.format("The question %s size is %s", questionStr, questionStr.length())).
                isFalse();
    }

    @Test
    public void testAnswerFormatIsNotNull() {
        //given
        String answerString = null;
        //when
        boolean answerFormat = validateAnswerFormat(answerString);
        //then
        assertThat(answerFormat).
                withFailMessage(String.format("The answer is NULL ")).
                isFalse();
    }

    @Test
    public void testAnswerFormatIsNotEmpty() {
        //given
        String answerString = "";
        //when
        boolean answerFormat = validateAnswerFormat(answerString);
        //then
        assertThat(answerFormat).
                withFailMessage(String.format("The answer is empty [size:%s]", answerString.length())).
                isFalse();
    }

    @Test
    public void testAnswerFormatHasDoubleQuotes() {
        //given
        String answerString = "answer1\"answer2";
        //when
        boolean answerFormat = validateAnswerFormat(answerString);
        //then
        assertThat(answerFormat).
                withFailMessage(String.format("The answer %s does not have a double quotes [\"]", answerString)).
                isTrue();
    }

    @Test
    public void testAnswerFormatDoesNotHaveDoubleQuotes() {
        //given
        String answerString = "answer1 answer2";
        //when
        boolean answerFormat = validateAnswerFormat(answerString);
        //then
        assertThat(answerFormat).
                withFailMessage(String.format("The answer %s does not have a double quotes [\"]", answerString)).
                isFalse();

    }

    private boolean validateLength(String input) {
        return ValidationUtil.validateLength(input);
    }

    private boolean validateAnswerFormat(String input) {
        return ValidationUtil.validateAnswerFormat(input);
    }
}
