import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    //I don't use points in my game, but I'm leaving it in case I want to implement multiple rounds to one game
    private int points;

    public Player(String name) {
        hand = new ArrayList<Card>();
        this.name = name;
        this.points = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        this.points = 0;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

}

