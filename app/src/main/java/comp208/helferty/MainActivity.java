package comp208.helferty;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

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

            if(card.imageId != R.drawable.card)
                Toast.makeText(MainActivity.this, "Invalid Move", Toast.LENGTH_SHORT).show();
            else /*if (game.cardSelected < 2)*/
            {
                switch (card.faceValue)
                {
                    case 1:
                        iv.setImageResource(R.drawable.ace_of_spades2);
                        card.imageId = R.drawable.ace_of_spades2;
                        game.cardSelected++;
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.ten_of_spades);
                        card.imageId = R.drawable.ten_of_spades;
                        game.cardSelected++;
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.jack_of_spades2);
                        card.imageId = R.drawable.jack_of_spades2;
                        game.cardSelected++;
                        break;
                    case 4:
                        iv.setImageResource(R.drawable.queen_of_spades2);
                        card.imageId = R.drawable.queen_of_spades2;
                        game.cardSelected++;
                        break;
                    case 5:
                        iv.setImageResource(R.drawable.king_of_spades2);
                        card.imageId = R.drawable.king_of_spades2;
                        game.cardSelected++;
                        break;
                    case 6:
                        iv.setImageResource(R.drawable.red_joker);
                        card.imageId = R.drawable.red_joker;
                        game.cardSelected++;
                        break;
                }
            }
        }
    };

    private void initGame() {

        game.cardSelected = 0;
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

                card.faceValue = (int)Math.floor(Math.random()*(6-1+1)+1);

                mgGrid[row][col] = card;

                iv.setTag(card);
            }
        }
    }


}

