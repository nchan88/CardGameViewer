import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int point;
    //initialize image
    private Image cardImage;

    final private static int MARGIN = 50;
    public Card(String rank, String suit, int point, Image cardImage) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        this.cardImage = cardImage;
    }
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void draw(Graphics g, CrazyViewer c, String player, int index) {

        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.setColor(Color.BLACK);
        g.drawString(c.getG().getP2().getName(), 25, 80);
        g.drawString(c.getG().getP1().getName(), 25, 550);

        if (c.getG().getP1().getHand().size() == 0)
        {
            g.drawString(c.getG().getP1().getName() + "WINS!!!", 125, 575);
        }

        if (c.getG().getP2().getHand().size() == 0)
        {
            g.drawString(c.getG().getP2().getName() + "WINS!!!", 125, 100);
        }


        if (player.equals("P1"))
        {
            g.drawImage(cardImage, 120 * index + MARGIN, 575, 100, 155, c);
        }

        if (player.equals("P2"))
        {
            g.drawImage(cardImage, 120 * index + MARGIN, 100, 100, 155, c);
        }



//    }
    }
    //This overloaded constructor is used so I don't have to have a boolean value isActive
    //This constructor is only called on the active card, which has a designated spot regardless of the player
    public void draw(Graphics g, CrazyViewer c)
    {
        g.drawImage(cardImage, 500, 300, 100, 155, c);
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
