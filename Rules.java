import java.util.List;

public interface Rules {
    List<Move> legalMoves( GameState state, Player player );
    void applyMove( GameState state, Move move );
    boolean isGameOver( GameState state );
    int evaluateScore( GameState state, Player player);
}
