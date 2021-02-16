public class State {
    public int[][] board;
    public int activePlayer;

    public State(int[][] board, int activePlayer) {
        this.board = new int[board.length][board.length];
        for(int i =0; i<board.length;i++){
            System.arraycopy(board[i], 0, this.board[i], 0, board.length);
        }
        this.activePlayer = activePlayer;
    }
    public int[][] getBoard(){
        return this.board;
    }
    public int getActivePlayer(){
        return this.activePlayer;
    }

}
