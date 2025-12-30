import java.util.*;
import java.util.Random;

public class ComputerControlledPlayer extends Player {
    
    private static final int SIMULATIONS = 500;
    private final Random random = new Random();

    public ComputerControlledPlayer( String name ) {
        super(name);
    }    

    @Override
    public Move chooseMove( GameState state, Rules rules ) {
       List<Move> legalMoves = rules.legalMoves(state, this);

       Move bestMove = null;
       int bestScore = Integer.MIN_VALUE;

       for ( Move move : legalMoves ) {
           int score = 0;
           
           for ( int i = 0; i < SIMULATIONS; i++ ) {
               GameState sim = simulate(state, move, rules);
               score += rules.evaluateScore(sim, this);
           }

           if ( score > bestScore ) {
            bestScore = score;
            bestMove = move;
           }
       }
       return bestMove;
    }

    private GameState simulate ( GameState state, Move move, Rules rules ) {
        GameState sim = new GameState(state);
        rules.applyMove(sim, move);
        
        while ( !rules.isGameOver(sim) ) {
            Player p = sim.getCurrentPlayer();
            List<Move> legalMoves = rules.legalMoves(sim, p);
            Move randomMove = legalMoves.get(random.nextInt(legalMoves.size()));
            rules.applyMove(sim, randomMove);
        }
        return sim;
    }

    @Override
    public Player copy() {
        ComputerControlledPlayer pc = new ComputerControlledPlayer(this.name);
        pc.hand = new Hand( this.hand );
        pc.tricksWon = this.tricksWon;
        return pc;
    }
}
