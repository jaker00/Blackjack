import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a Board that can be used in a collection
 * of solitaire games similar to Elevens.  The variants differ in
 * card removal and the board size.
 */
public abstract class Board {

    private User user;
    private Deck deck;
    private List<Card> dealerCards;

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;

    /**
     * Creates a new <code>Board</code> instance.
     * @param ranks the names of the card ranks needed to create the deck
     * @param suits the names of the card suits needed to create the deck
     * @param pointValues the integer values of the cards needed to create
     *                    the deck
     */
    public Board(User user, String[] ranks, String[] suits, int[] pointValues) {
        this.user = user;
        deck = new Deck(ranks, suits, pointValues);

        dealerCards = new ArrayList<>();
        dealerCards.add(deck.deal());
        dealerCards.add(deck.deal());

        if (I_AM_DEBUGGING) {
            System.out.println(deck);
            System.out.println("----------");
        }
    }

    /**
     * Start a new game by shuffling the deck and
     * dealing some cards, which adds two cards
     * to the player's hand.
     */
    public void newGame() {
        deck.shuffle();
        dealMyCards();
        initDealer();
    }

    /**
     * Determines if the board is empty (has no cards).
     * @return true if this board is empty; false otherwise.
     */
    public boolean isEmpty() {
        for (int k = 0; k < dealerCards.size() + user.getCards().size(); k++) {
            if (dealerCards.get(k) != null && user.getCards().get(k) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Accessors
     */
    public Deck getDeck() {
        return deck;
    }
    public User getUser(){return user;}
    public Card userCardAt(int k) {
        return user.getCards().get(k);
    }
    public List<Card> getDealerCards() {return dealerCards;}
    public Card dealerCardAt(int k) { return dealerCards.get(k); }

    /**
     * Replaces selected cards on the board by dealing new cards.
     * @param selectedCards is a list of the indices of the
     *        cards to be replaced.
     */

    /**
     * Generates and returns a string representation of this board.
     * @return the string version of this board.
     */
    public String toString() {
        String s = "";
        for (int k = 0; k < user.getCards().size(); k++) {
            s = s + k + ": " + user.getCards().get(k) + "\n";
        }
        for (int k = 0; k < dealerCards.size(); k++) {
            s = s + k + ": " + dealerCards.get(k) + "\n";
        }
        return s;
    }

    /**
     * Determine whether or not the game has been won,
     * i.e. neither the board nor the deck has any more cards.
     * @return true when the current game has been won;
     *         false otherwise.
     */

    public abstract boolean anotherPlayIsPossible();

    /**
     * Deal cards to this board to start the game.
     */
    private void dealMyCards() {
        user.getCards().clear();
        for (int k = 0; k < 2; k++) {
            user.getCards().add(deck.deal());
        }
    }

    private void initDealer(){
        dealerCards.clear();
        for (int k = 0; k < 2; k++) {
            dealerCards.add(deck.deal());
        }
    }
    public void dealerHit(){
        dealerCards.add(deck.deal());
    }
}