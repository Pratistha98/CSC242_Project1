public class State {
    private int[][] board;
    private int activePlayer;

    public State(int[][] board, int activePlayer) {
        this.board = board;
        this.activePlayer = activePlayer;
    }
    public int[][] getBoard(){
        return this.board;
    }
    public int getActivePlayer(){
        return this.activePlayer;
    }

}
