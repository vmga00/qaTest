package gonzalez.victor.TestCore;

import com.cgm.qanda.dataobject.Answer;
import com.cgm.qanda.dataobject.Question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestingUtils {

    public static Question createQuestion(String questionText, String answerText) {
        Question question = new Question();
        question.setQuestion(questionText);
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setAnswer(answerText);
        Set<Answer> set = new HashSet<>();
        set.add(answer);
        question.setAnswers(set);
        return question;
    }

    public static String getVariableLengthString(int stringLength) {
        char[] chars = new char[stringLength];
        Arrays.fill(chars, '*');
        return new String(chars);
    }

}
