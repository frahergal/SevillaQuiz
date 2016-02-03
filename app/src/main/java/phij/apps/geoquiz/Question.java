package phij.apps.geoquiz;

/**
 * Created by frahe_000 on 25/12/2015.
 */
public class Question {

    // Text of the question
    private String mText;
    // Answer of the question
    private boolean mAnswerTrue;
    // Category of the question
    private int mCategory;

    // Getters and Setters
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

    public int getCategory() {
        return mCategory;
    }

    public void setCategory(int mCategory) {
        this.mCategory = mCategory;
    }
}
