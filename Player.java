public abstract class Player {
    protected Hand hand = new Hand();
    protected final String name;
    public int tricksWon = 0;

    public Player( String name ) {
        this.name = name;
    }

    public Hand getHand( ) {
        return hand;
    }

    @Override
    public String toString( ) {
        return name;
    }

    public abstract Move chooseMove( GameState state, Rules rules );
    public abstract Player copy();
}