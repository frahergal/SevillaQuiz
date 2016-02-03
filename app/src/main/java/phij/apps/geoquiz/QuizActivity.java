package phij.apps.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    // TAG
    private static final String TAG = "QuizActivity";
    // Bundle key (index of question)
    private static final String KEY_INDEX = "index";
    // Intent extra
    private static final String CATEGORY_QUESTIONS = "phij.apps.geoquiz.category_questions";
    // Buttons
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    // Question Text
    private TextView mQuestionTextView;
    // Index
    private int mCurrentIndex = 0;
    // List of Questions
    private List<Question> questions;
    // Category selected
    private int categoryQuestions;

    /**
     * Creates an intent to call this activity
     *
     * @param packageContext
     * @param category
     * @return
     */
    public static Intent newIntent(Context packageContext, int category) {
        Intent i = new Intent(packageContext, QuizActivity.class);
        i.putExtra(CATEGORY_QUESTIONS, category);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log
        Log.d(TAG, "onCreate(Bundle) called" );
        setContentView(R.layout.activity_quiz);

        // Obtains the category from the intent
        categoryQuestions = getIntent().getIntExtra(CATEGORY_QUESTIONS, 0);

        // Obtains questions from DB
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
        // The questions are obtained depending of the language of the user's system.
        String location = Locale.getDefault().getLanguage();
        //questions = myDbHelper.getAllQestions(location);
        questions = myDbHelper.getQuestionByCategory(location, categoryQuestions);


        // Obtain the element
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        // Listener to pass to the next question
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % questions.size();
                updateQuestion();
            }
        });

        // Obtain the buttons elements
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        // Buttons' listeners
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(false);
            }
        });

        // Button "Next Question"
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % questions.size();
                updateQuestion();
            }
        });

        // Button "Previous Question"
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % questions.size();
                if(mCurrentIndex<0){
                    mCurrentIndex = questions.size()-1;
                }
                updateQuestion();
            }
        });

        // Recovers the saved question index
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();
    }

    // Saves the state of the application
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    // Updates to the next question
    private void updateQuestion(){
        String textQuestion = questions.get(mCurrentIndex).getText();
        mQuestionTextView.setText(textQuestion);
    }

    /**
     * Checks the user's answer
     * @param userPressedTrue The user pressed "True" button
     */
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = questions.get(mCurrentIndex).isAnswerTrue();

        int messageResId = 0;

            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
    }
}
