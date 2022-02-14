package comp208.helferty;


import java.util.ArrayList;

public class Game {
    int selectCtr; //This will be counted each click. Will prevent selecting more than 2 cards.

    //Used to count the number of matches found.
    int scoreCtr = 0;

    //Used to count the number of guesses
    int guessCtr = 0;

    //Used to ensure that once 2 cards have been flipped, cannot continue until they are both
    //un-flipped
    boolean cardsFlipped = false;

    ArrayList<Integer> cardFaces = new ArrayList<Integer>();
}
