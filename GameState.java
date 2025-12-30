import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final List<Player> players;
    private int currentPlayerIndex;
    public Player lastTrickWinner = null;
    /* Stikk */
    public List<TrickPlay> trick = new ArrayList<>();

    public GameState( List<Player> players ) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    /* Copy constructor for simulation */
    public GameState( GameState originalState ) {
        this.currentPlayerIndex = originalState.currentPlayerIndex;

        /* Copy players, players are mutable, so deep copy */
        this.players = new ArrayList<>();
        for ( Player p : originalState.players ) {
            this.players.add(p.copy());
        }

        /* Copy trick, tricks are mutable, so deep copy */
        this.trick = new ArrayList<>();
        for ( TrickPlay tp : originalState.trick ) {
            Player copiedPlayer = 
                this.getPlayers() // find player with same index in original
                .get(originalState.getPlayers().indexOf(tp.getPlayer()));
            this.trick.add(new TrickPlay(copiedPlayer, tp.getCard()));
        }
    }

    public List<Player> getPlayers( ) {
        return players;
    }

    public Player getCurrentPlayer( ) {
        return players.get(currentPlayerIndex);
    }

    public void setNextPlayer( int index ) {
        currentPlayerIndex = index;
    }

    public void nextPlayer( ) {
        currentPlayerIndex = ( currentPlayerIndex + 1) % players.size();
    }
}
