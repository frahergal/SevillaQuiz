package phij.apps.geoquiz;

/**
 * Created by frahe_000 on 25/12/2015.
 */
public class Question {

    private String mText;
    private boolean mAnswerTrue;

    public Question(String text, boolean answerTrue){
        mText = text;
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
