import java.util.List;

/**
 * Created by jakereisner on 3/6/17.
 */
public class TwentyOneBoard extends Board{

    private static final int BLACKJACK = 21;
    private static final boolean I_AM_DEBUGGING = false;
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    public TwentyOneBoard(User user){
        super(user, RANKS, SUITS, POINT_VALUES);
    }

    //**incomplete implementation**
    public boolean anotherPlayIsPossible(){
        return !isPlayerBlackjack() && !isPlayerBust() ;
    }

    public int dealerTotal(List<Card> dealerCards){
        int total = 0;
        for(Card c : dealerCards) {
            total += c.getPointValue();
        }
        return total;
    }

    //Boolean methods

    /**0 return*/
    public boolean isDealerBlackjack(){
        return dealerTotal(getDealerCards()) == BLACKJACK;
    }

    /**2.5x bet return */
    public boolean isPlayerBlackjack(){
        return getUser().getTotal() == BLACKJACK;
    }

    /**0 return*/
    public boolean isPlayerBust(){
        return getUser().getTotal() > BLACKJACK;
    }

    /**2x bet return*/
    public boolean isDealerBust(){
        return dealerTotal(getDealerCards()) > BLACKJACK;
    }

    /**2x bet return*/
    public boolean userTrumps(){
        //both dealer's and user's cards must be less than blackjack amt, and user sum > dealer sum
        return getUser().getTotal() < BLACKJACK && dealerTotal(getDealerCards()) < BLACKJACK
                && getUser().getTotal() > dealerTotal(getDealerCards());
    }

    /**0 return*/
    public boolean dealerTrumps(){
        return getUser().getTotal() < BLACKJACK && dealerTotal(getDealerCards()) < BLACKJACK
                && getUser().getTotal() < dealerTotal(getDealerCards());
    }

    /**1x bet return*/
    public boolean isTie(){
        return getUser().getTotal() == dealerTotal(getDealerCards());
    }


    /**
    * Modifies player's cash according to how much he/she won
    * user refers to the User instance variable of the Board class
    **/
    public void distributeEarnings(){
        if(userTrumps()){
            getUser().modifyCash(2* getUser().betChipsSum());
        }
        else if(isDealerBust() || isTie()){
            getUser().modifyCash(getUser().betChipsSum());
        }
        else if(isPlayerBlackjack()){getUser().modifyCash((int) Math.round(2.5 * getUser().betChipsSum()));}

        getUser().resetBet();
    }
}
