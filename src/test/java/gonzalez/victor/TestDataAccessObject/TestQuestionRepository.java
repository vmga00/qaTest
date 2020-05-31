package gonzalez.victor.TestDataAccessObject;

import com.cgm.qanda.dataobject.Question;
import gonzalez.victor.TestCore.TestBase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class TestQuestionRepository extends TestBase {

    @Test
    public void whenFindByQuestion(){
        //given
        Question question = createQuestion("question2","answer2");
        questionRepository.save(question);
        //when
        Question lookedUpQuestion = questionRepository.findByQuestion(question.getQuestion()).get();
        //then
        assertThat(lookedUpQuestion.getQuestion()).
                withFailMessage(String.format("\nThe lookedUpQuestion [%s] does not match original question [%s]",lookedUpQuestion.getQuestion(),question.getQuestion())).
                isEqualTo(question.getQuestion());
    }

}
