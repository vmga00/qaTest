package gonzalez.victor.TestDataAccessObject;

import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;
import gonzalez.victor.TestCore.TestBase;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class TestAnswerRepository extends TestBase {

    @Test
    public void testFindByQuestionId(){
        //given
        Question savedQuestion = questionRepository.findByQuestion("question1").get();
        Set<Answer> savedAnswers = savedQuestion.getAnswers();
        //when
        List<Answer> searchedAnswers = answerRepository.findByQustionId(savedQuestion.getId());
        //then
        assertThat(compareAnswers(savedAnswers,searchedAnswers)).withFailMessage("The original answer and the searched ones are different");


    }

    private boolean compareAnswers(Set<Answer> savedQuestionAnswers, List<Answer> searchedQuestionAnswers){
       if(savedQuestionAnswers.size()!=searchedQuestionAnswers.size()){
           int savedQuestionAnswersSize = savedQuestionAnswers.size();
           savedQuestionAnswers.retainAll(searchedQuestionAnswers);
           if(savedQuestionAnswersSize == savedQuestionAnswers.size()){
               return true;
           }
       }
       return false;
    }




}
