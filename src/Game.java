import java.util.Scanner;
public class Game {
    //needs to be static to be used in main
    static Scanner reader = new Scanner(System.in);
    //This Magic Number can be modified to change the length of the game, the more cards the longer the game will take
    final int INITIAL_NUM_CARDS = 1;
    final String rank[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    final String suits[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
    final int values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private Player p1;
    private Player p2;
    //this card represents the card that is currently able to be played on
    private Card currentCard;
    private Deck deck;
    //when this is true playGame stops looping
    private boolean gameOver = false;

    private CrazyViewer window;

    public Game(String p1name, String p2name) {
        deck = new Deck(rank, suits, values);
        deck.shuffle();
        p1 = new Player(p1name);
        p2 = new Player(p2name);
        //gets a card from the top of the deck to place "face up"
        currentCard = deck.deal();
        //add marker to mark "current card" (face up) vs card in hand. two markers
        //deals cards one to each player until both players have INITIAL_NUM_CARDS
        for (int i = 0; i < INITIAL_NUM_CARDS; i++) {
            p1.addCard(deck.deal());
            p2.addCard(deck.deal());
        }
        this.window = new CrazyViewer(this);
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public static void main(String[] args) {
        //I ask for player names before instructions because I want to personalize my instructions with names to make it more clear
        System.out.println("Before we get started with this game of Crazy Eights, enter player 1 and 2's names below");
        System.out.println("Player 1 Name: ");
        String p1name = reader.nextLine();
        System.out.println("Player 2 Name: ");
        String p2name = reader.nextLine();
        Game g = new Game(p1name, p2name);
        g.playGame();
    }

    public void printInstructions()
    {
        System.out.println("Welcome to Crazy Eights, a 2 person card game where the objective is to get rid of all of yo" +
                "ur cards. To start, one card will be drawn from the deck so it is facing up. ");
        System.out.println("It starts as " + p1.getName() + "'s turn in which they must place a card from their hand with e" +
                "ither the same suit, the same rank, or any card with an 8");
        System.out.println("This card is chosen by selecting an index, starting at 0, from your hand to select the card " +
                "you want to place down.");
        System.out.println("If they are unable to place a card or place an invalid index then the they will be" +
                "given a card from the deck and " + p2.getName() + "'s turn will begin. ");
        System.out.println("If you are unable to place a card just press 0 to draw a card. The turns will repeat until a" +
                " player has placed all the cards in their hand, ending the game.");
    }


    public void turn(Player p) {
        System.out.println("\n\n");
        System.out.println("Player " + p.getName() + "'s turn");
        System.out.println("The card face up is " + currentCard);
        System.out.println(p.getName() + "'s hand: " + p.getHand());
        System.out.println("Pick a card (starting from 0) to play");

        System.out.print("Enter index: ");
        int a = reader.nextInt();
        //keeps prompting for an index that is in bounds
        while (a > p.getHand().size() - 1 || a < 0)
        {
            System.out.print("Enter a valid index: ");
            a = reader.nextInt();
        }
        //meat of my code if you will, basically says any of the current card attributes must match the attributes of the
        //card at the selected index in order for it to be valid, unless it is an 8 in which case it is automatically ok
        if (!(currentCard.getSuit().equals(p.getHand().get(a).getSuit()) || currentCard.getRank().equals(p.getHand().get
                (a).getRank()) || p.getHand().get(a).getRank().equals("8"))) {
            //any invalid selection results in a card being drawn
            System.out.println("Invalid Card, you have been given a card from deck");
            p.addCard(deck.deal());
            window.repaint();
        }
        else
        {
            //if the index provided was valid it will remove the card from hand as well as update the currentCard to be
            //the removed card
            currentCard = p.getHand().remove(a);
            window.repaint();
            System.out.println("Successfully placed card");
        }
        System.out.println(p.getName() + "'s updated hand is: " + p.getHand());
    }

    public void playGame() {
        printInstructions();
        //loops turns until isWinner returns true, in which case the ending sequence is triggered
        while (!gameOver)
        {
            turn(p1);
            isWinner(p1);
            turn(p2);
            isWinner(p2);
        }
        System.out.println("Game Over");
    }

    public boolean isWinner(Player p) {
        if (p.getHand().size() == 0)
        {
            System.out.println(p.getName() + " is the winner");
            gameOver = true;
            return true;
        }
        return false;
    }
}

