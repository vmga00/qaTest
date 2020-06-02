package gonzalez.victor.TestCore;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.AnswerRepository;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

@ContextConfiguration(classes = QnAApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class TestBase {

    @Autowired
    protected AnswerRepository answerRepository;

    @Autowired
    protected QuestionRepository questionRepository;

    @Before
    public void setup() throws Exception {
        Question question = createQuestion("question1", "answer1");
        questionRepository.save(question);
        saveAnswersToRepo(question);
    }

    @After
    public void teardown(){
        questionRepository.deleteAll();
        answerRepository.deleteAll();
    }

    protected Question createQuestion(String question, String answers) {
        return TestingUtils.createQuestion(question, answers);
    }

    protected String getVariableLengthString(int length) {
        return TestingUtils.getVariableLengthString(length);
    }

    private void saveAnswersToRepo(Question question){
        Set<Answer> answers = question.getAnswers();
        for(Answer answer:answers){
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
    }

}
