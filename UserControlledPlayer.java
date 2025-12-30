import java.util.Scanner;
import java.util.List;

public class UserControlledPlayer extends Player {
    private final Scanner sc = new Scanner(System.in);

    public UserControlledPlayer( String name ) {
        super(name);
    }

    @Override
    public Move chooseMove( GameState state, Rules rules ) {
        List<Move> legalMoves = rules.legalMoves(state, this);
        List<Card> playerCards = state.getCurrentPlayer().getHand().getCards();

        System.out.println(name + "'s hand:");
        for ( int i = 0; i < playerCards.size(); i++ ) {
            Card c = playerCards.get(i);
            System.out.println(" -- " + c);
        }
        
        System.out.println("\n Legal moves:");
        int i;
        for ( i = 0; i < legalMoves.size(); i++ ) {
            CardMove cm = (CardMove) legalMoves.get(i);
            System.out.println(i + ": " + cm.card);
        }
        int choice = sc.nextInt();
        while ( choice < 0 || choice > i ) {
            System.out.println("Choose a legal move!");
        }
        return legalMoves.get(choice);

    }

    /* Copy method for simulation purposes */
    @Override
    public Player copy() {
        UserControlledPlayer pc = new UserControlledPlayer(this.name);
        pc.tricksWon = this.tricksWon;
        pc.hand = new Hand(this.hand);
        return pc;
    }
}
