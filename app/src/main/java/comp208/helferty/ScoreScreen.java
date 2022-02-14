package comp208.helferty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Bundle extras = getIntent().getExtras();

        TextView guessOutput = findViewById(R.id.finalScore);

        guessOutput.setText(extras.getString("data"));

    }

    //Returns to previous page.
    public void newGame(View view){
        finish();
    }

    //Exits application
    public void exitGame(View view){
        Intent leaveApp = new Intent(getApplicationContext(), MainActivity.class);
        leaveApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        leaveApp.putExtra("EXIT", true);
        startActivity(leaveApp);

        finish();
        System.exit(0);
    }
}