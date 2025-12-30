import java.util.*;

public class Game {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        String playerName;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        playerName = sc.nextLine();
        List<Player> players = new ArrayList<>();
        Player user = new UserControlledPlayer(playerName);
        players.add(user);

        for ( int i = 1; i < 4; i++ ) {
            String name = "AI " + i;
            Player ccp = new ComputerControlledPlayer(name);
            players.add(ccp);
        }

        dealer.dealCards(players, 13);

        AmerikanerRules rules = new AmerikanerRules(Card.Suit.SPADES);
        /* IMPLEMENT BIDDING LATER */
        
        GameState state = new GameState(players);
        
        while ( !rules.isGameOver(state) ) {
            System.out.println("Current player: " + state.getCurrentPlayer());
            Player current = state.getCurrentPlayer();
            CardMove move = (CardMove) current.chooseMove(state, rules);
            rules.applyMove(state, move);
            System.out.println(current + " plays a " + move.card);
            if ( state.lastTrickWinner != null ) {
                Player p = state.lastTrickWinner;
                System.out.println("\n" + p + " wins the trick!");
                System.out.println("Trick overview: ");
                for ( int i = 0; i < players.size(); i++ ) {
                    System.out.println("Player " + players.get(i) + ": " + players.get(i).tricksWon);
                }
                state.lastTrickWinner = null;
            }
        }

        System.out.println("Game over!");
    }
}
