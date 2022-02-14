package comp208.helferty;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    final int ROWS = 4;
    final int COLS = 3;

    TableLayout mgBoard;

    Card[][] mgGrid = new Card[ROWS][COLS];

    Game game = new Game();

    Card card1;
    Card card2;
    ImageView choice1;
    ImageView choice2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getBooleanExtra("EXIT", false)){
            finish();
        }

        //Populate cardFaces ArrayList with 2 of each card.
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

            //If back of card is not being displayed when clicked, it will be changed so that it is.
            if(card.imageId != R.drawable.card ) {
                iv.setImageResource(R.drawable.card);
                card.imageId = R.drawable.card;

                //selectCtr keeps track of how many cards are flipped, and cardsFlipped boolean
                //won't allow player to continue until both cards are returned to face down, unless
                // there's a match.
                game.selectCtr--;
                if(game.selectCtr == 0)
                {
                    game.cardsFlipped = false;
                }
            }
            else if (card.imageId == R.drawable.card && game.selectCtr < 2 && !game.cardsFlipped){
                iv.setImageResource(card.faceValue);
                card.imageId = card.faceValue;
                game.selectCtr++;

                //If this is the first card flipped, Card object and ImageView object are assigned
                //to card1 Card, and choice1 ImageView.
                if(game.selectCtr < 2)
                {
                    card1 = card;
                    choice1 = iv;

                }
                //If this is the second card flipped, Card object and ImageView object are assigned
                //to card2 Card and choice2 ImageView
                else if(game.selectCtr == 2)
                {
                    game.cardsFlipped = true;
                    game.guessCtr++;
                    card2=card;
                    choice2=iv;

                    //If the two flipped cards have the same faceValue, their ImageView clickListeners
                    //are disabled, and card's flipped ctr values and booleans are reset.
                    if(card2.faceValue == card1.faceValue)
                    {
                        choice1.setOnClickListener(null);
                        choice2.setOnClickListener(null);
                        game.scoreCtr++;
                        game.cardsFlipped = false;
                        game.selectCtr=0;

                        //If all matches are found, move to next page.
                        if(game.scoreCtr == 6){
                            switchScreen(game.guessCtr);
                        }
                    }
                }
            }
        }
    };

    private void initGame() {

        int cardCtr = 0;

        //Shuffle the cards arrayList so the order changes at the start of each game
        Collections.shuffle(game.cardFaces);

        game.selectCtr = 0;
        game.guessCtr = 0;
        game.scoreCtr = 0;
        game.cardsFlipped=false;

        for (int row = 0; row < ROWS; row++) {
            TableRow tableRow = (TableRow) mgBoard.getChildAt(row);

            for (int col = 0; col < COLS; col++) {
                ImageView iv = (ImageView) tableRow.getChildAt(col);

                iv.setOnClickListener(ivListener);

                //When game starts, all cards are face down.
                iv.setImageResource(R.drawable.card);

                Card card = new Card();
                card.imageId = R.drawable.card;
                card.row = row;
                card.col = col;

                //Iterates through cardFaces ArrayList and assigns a face value to each
                //card individually
                card.faceValue = game.cardFaces.get(cardCtr);
                cardCtr++;

                mgGrid[row][col] = card;

                iv.setTag(card);
            }
        }

    }

    //Receives the number of guesses and sends to new activity. Resets game and switches screens.
    private void switchScreen(int noGuesses){
        Intent intent = new Intent(this, ScoreScreen.class);

        intent.putExtra("data","You made " + noGuesses + " guesses!");

        startActivity(intent);

        initGame();
    }

}

