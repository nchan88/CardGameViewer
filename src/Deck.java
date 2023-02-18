import javax.swing.*;
import java.util.ArrayList;
//magic number definition
public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String rank[], String suits[], int values[]) {
        cards = new ArrayList<>();

        int counter = 1;

        for (int i = 0; i < rank.length; i++) {
            for (String suit : suits)
            {
                cards.add(new Card(rank[i], suit, values[i], new ImageIcon("Resources/" + counter++ + ".png").getImage()));
                cardsLeft++;
            }
        }
    }

    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        //should this be cards.size or should I return cardsLeft
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);

    }

    public void shuffle() {
        //fischer yates shuffle
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
            cardsLeft = cards.size();
        }


    }
}
