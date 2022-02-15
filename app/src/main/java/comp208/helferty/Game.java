package comp208.helferty;


import java.util.ArrayList;

public class Game {
    int selectCtr; //This will be counted each click. Will prevent selecting more than 2 cards.

    //Used to count the number of matches found.
    int scoreCtr;

    //Used to count the number of guesses
    int guessCtr;

    ArrayList<Integer> cardFaces = new ArrayList<Integer>();
}
