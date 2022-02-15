package comp208.helferty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    Button btnNewGame;
    Button btnExit;
    TextView txtGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        btnNewGame = findViewById(R.id.btnNewGame);
        btnExit = findViewById(R.id.btnExit);
        txtGuesses = findViewById(R.id.txtGuesses);
        txtGuesses.setText("It took you " + getIntent().getExtras().getInt("data"));

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1);
                ScoreScreen.this.finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0);
                ScoreScreen.this.finish();
            }
        });

    }

}