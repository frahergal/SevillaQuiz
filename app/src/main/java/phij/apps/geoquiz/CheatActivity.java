package phij.apps.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {

    // Key para evitar colisiones con otros Intent Extras
    private  static final String EXTRA_ANSWER_IS_TRUE = "phij.apps.geoquiz.answer_is_true";
    // Key para establecer el Intent de respuesta
    private final static String EXTRA_ANSWER_SHOWN = "phij.apps.geoquiz.answe_shown";
    // Key para guardar si el usuario vió la respuesta (hizo trampas)
    private final static String KEY_IS_CHEATER = "isCheater";
    // Guarda si el usuario hizo trampas
    private boolean mUserCheated = false;
    // La respuesta es verdadera (o falsa)
    private boolean mAnswerIsTrue;
    // Texto y botón de la respuesta
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    // Constructor Intent
    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    /**
     * Obtiene si la respuesta fue mostrada al usuario o no.
     * (Si hizo trampas)
     * @param result Intent enviado como resultado
     * @return Verdadero o falso
     */
    public static boolean wasAnswerShown(Intent result){
        return  result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer(mAnswerIsTrue, mAnswerTextView);
                mUserCheated = true;
            }
        });

        // Recupera si el usuario hizo trampas
        if(savedInstanceState != null){
            mUserCheated = savedInstanceState.getBoolean(KEY_IS_CHEATER);
            if(mUserCheated){
                showAnswer(mAnswerIsTrue, mAnswerTextView);
            }
        }
    }

    /**
     * Establece un Intent como respuesta para la clase padre que indica que
     * el usuario ha hecho trampas
     * @param isAnswerShown Ha hecho trampas.
     */
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    // Guardar el estado de la aplicación
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_IS_CHEATER, mUserCheated);
    }

    // Muestra el texto con la respuesta correcta
    private void showAnswer(boolean answerIsTrue, TextView answerTextView){
        if (answerIsTrue) {
            answerTextView.setText(R.string.true_button);
        } else {
            answerTextView.setText(R.string.false_button);
        }
        // Enviamos el intent a la actividad padre para delatar que ha hecho trampa
        setAnswerShownResult(true);
    }
}
