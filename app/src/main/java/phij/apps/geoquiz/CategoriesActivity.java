package phij.apps.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    // Buttons
    private Button mCategory1Button;
    private Button mCategory2Button;
    private Button mCategory3Button;
    private Button mCategory4Button;
    private Button mCategory5Button;
    private Button mCategory6Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Obtain the buttons elements
        mCategory1Button = (Button) findViewById(R.id.category_1_button);
        mCategory2Button = (Button) findViewById(R.id.category_2_button);
        mCategory3Button = (Button) findViewById(R.id.category_3_button);
        mCategory4Button = (Button) findViewById(R.id.category_4_button);
        mCategory5Button = (Button) findViewById(R.id.category_5_button);
        mCategory6Button = (Button) findViewById(R.id.category_6_button);

        mCategory1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(CategoriesActivity.this, 1);
                startActivity(i);
            }
        });

        mCategory2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(CategoriesActivity.this, 2);
                startActivity(i);
            }
        });

        mCategory3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(CategoriesActivity.this, 3);
                startActivity(i);
            }
        });

        mCategory4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(CategoriesActivity.this, 4);
                startActivity(i);
            }
        });

        mCategory5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(CategoriesActivity.this, 5);
                startActivity(i);
            }
        });

        /* TODO: Create a sixth category on database
        mCategory6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(CategoriesActivity.this, 6);
                startActivity(i);
            }
        });*/
    }
}
