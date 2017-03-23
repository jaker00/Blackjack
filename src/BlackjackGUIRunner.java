import javax.swing.*;

/**
 * Created by jakereisner on 3/18/17.
 */
public class BlackjackGUIRunner extends JPanel {
    public static void main(String[] args) {
        Board game = new TwentyOneBoard(new User(50, "jake"));
        CardGameGUI gui = new CardGameGUI(game);
        gui.displayGame();
        System.out.println("user cards: " + game.getUser().getCards());
        System.out.println("dealer cards: " + game.getDealerCards());
    }
}
