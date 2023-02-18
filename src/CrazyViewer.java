import javax.swing.*;
import java.awt.*;

public class CrazyViewer extends JFrame {
    // TODO: Complete this class
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 750;
    private Game game;

    public CrazyViewer(Game g) {
        this.game = g;
        //Array of gameCards  is for efficiency purposes, so each square doesn't need a copy of the same gameCards

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("The Board");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public Game getG() {
        return game;
    }

    public void paint(Graphics g) {
        // TODO: Write the paint method.
        //creating white background
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 750);
        g.drawImage(new ImageIcon("Resources/CardGameBackground.png").getImage(), 0, 0, this);
        //draws face up card
        //should i overload my constructor in card to accept a two parameter constructor instead of doing this where i leave player blank?
        game.getCurrentCard().draw(g, this);
        //can i use this to print the cards in different locations?
        //I was still able to use a for each loop while also passing an index as a parameter by using the indexOf method
        for (Card c : game.getP1().getHand())
        {
            c.draw(g, this, "P1", game.getP1().getHand().indexOf(c));
        }
        for (Card c : game.getP2().getHand())
        {
            c.draw(g, this, "P2", game.getP2().getHand().indexOf(c));
        }
//          This is what I might have done if I didn't implement this solution.
//        for (int i = 0; i < game.getP1().getHand().size(); i++)
//        {
//            game.getP1().getHand().get(i).draw(g, this, "P1", i);
//        }
    }
}

