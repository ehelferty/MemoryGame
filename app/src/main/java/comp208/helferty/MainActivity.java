package comp208.helferty;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    final int ROWS = 4;
    final int COLS = 3;

    TableLayout mgBoard;

    Card[][] mgGrid = new Card[ROWS][COLS];

    Game game = new Game();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populate cardFaces ArrayList
        game.cardFaces.add(R.drawable.ace);
        game.cardFaces.add(R.drawable.ten);
        game.cardFaces.add(R.drawable.jack);
        game.cardFaces.add(R.drawable.queen);
        game.cardFaces.add(R.drawable.king);
        game.cardFaces.add(R.drawable.joker);
        game.cardFaces.add(R.drawable.ace);
        game.cardFaces.add(R.drawable.ten);
        game.cardFaces.add(R.drawable.jack);
        game.cardFaces.add(R.drawable.queen);
        game.cardFaces.add(R.drawable.king);
        game.cardFaces.add(R.drawable.joker);

        mgBoard = findViewById(R.id.mgBoard);
        initGame();
    }

    //Defines an OnClickListener for each of the ImageViews.
    //Whenever a square is clicked, the View object below will become a reference to that specific
    //ImageView selected.
    View.OnClickListener ivListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            ImageView iv = (ImageView) view;

            Card card = (Card) iv.getTag();

            boolean match = false;

            if(card.imageId != R.drawable.card ) {
                iv.setImageResource(R.drawable.card);
                card.imageId = R.drawable.card;
                game.selectCtr--;
            }
            else if (card.imageId == R.drawable.card && game.selectCtr < 2){
                iv.setImageResource(card.faceValue);
                card.imageId = card.faceValue;
                game.selectCtr++;
            }



//            if(card.imageId != R.drawable.card)
//                Toast.makeText(MainActivity.this, "Invalid Move", Toast.LENGTH_SHORT).show();
//            else if (game.selectCtr < 2)
//            {
//                card.imageId = card.faceValue;
//                iv.setImageResource(card.faceValue);
//                game.selectCtr++;
//            }

        }
    };

    private void initGame() {

        int cardCtr = 0;
        Collections.shuffle(game.cardFaces);

        game.selectCtr = 0;
        for (int row = 0; row < ROWS; row++) {
            TableRow tableRow = (TableRow) mgBoard.getChildAt(row);

            for (int col = 0; col < COLS; col++) {
                ImageView iv = (ImageView) tableRow.getChildAt(col);

                iv.setOnClickListener(ivListener);

                //When game starts, all cards are facedown.
                iv.setImageResource(R.drawable.card);

                Card card = new Card();
                card.imageId = R.drawable.card;
                card.row = row;
                card.col = col;

                card.faceValue = game.cardFaces.get(cardCtr);
                cardCtr++;

                mgGrid[row][col] = card;

                iv.setTag(card);
            }
        }

    }

    private boolean checkChoice(int choice1, int choice2)
    {
        return true;
    }
}

