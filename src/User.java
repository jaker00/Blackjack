import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakereisner on 3/6/17.
 */
public class User {
    private List<Card> cards = new ArrayList<>();
    private int cash;
    private List<Chip> chips;
    private String name;
    private List<Chip> bet;

    public String getName(){return name;}
    public List<Chip> getChips(){return chips;}
    public List<Card> getCards(){return cards;}


    /*
    Post-condition: only one user is constructed per game
     */
    public User(int cash, String name){
        this.name = name;
        this.cash = cash;
        chips = Chip.generateChips(cash);
        this.bet = null;
    }
    public int betChipsSum(){
        int total = 0;
        for(Chip c : bet){
            total += c.getPointValue();
        }
        return total;
    }
    public void makeBet(int amt){
        cash -= amt;
        bet = Chip.generateChips(amt);
    }

    public int getTotal(){
        int total = 0;
        for(Card c : cards){total += c.getPointValue();}
        return total;
    }

    public void modifyCash(int dollars){
        cash += dollars;
    }

    public void resetBet(){
        bet = null;
    }

    public void userHit(Card e){
        cards.add(e);
    }
}
