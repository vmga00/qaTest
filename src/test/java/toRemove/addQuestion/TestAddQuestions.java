package toRemove.addQuestion;

import com.cgm.qanda.QnAApplication;
import com.cgm.qanda.dataaccessobject.QuestionRepository;
import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Description;
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
public class TestAddQuestions {
    @Autowired
    QuestionRepository questionRepository;

    /*
    GIVEN a user ask a question
    WHEN  the system check the question in the DB
    THEN  the question matches exactly in DB
    AND   its answer is shown
    */
    @Test
    @Description("Verify question text is exactly the same in the db")
    public void testQuestionExactlyMatchDB() {

    }

    /*
    GIVEN a user ask a question
    WHEN  the system check the question in the DB
    THEN  the question DOES NOT match exactly in DB
    AND   default answer is shown
    */
    /*
    GIVEN a user ask a question
    WHEN  the system check the quesiton in DB
    THEN  the question DOES match exactly in DB
    AND   all answers are shown
    AND   answers are shown as bullet points
    */

    private Question createQnARepo(){
        Question question = new Question();
        question.setQuestion("question1");
        Answer answer = new Answer();
        answer.setAnswer("answer1");
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        return question;
    }

    private Question getOptQ(Optional<Question> question){
        return question.get();
    }

}
