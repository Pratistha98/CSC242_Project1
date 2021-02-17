import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {

        int[][] board = new int[4][4];
        board[1][1] = 1;
        board[2][2] = 1;
        board[1][2] = -1;
        board[2][1] = -1;
        printBoard(board);
        State test_state = new State(board,1);
        Actions test_actions = new Actions(test_state);
        System.out.println(test_actions.getActions());
        State result_test = Game.get_result(test_state,test_actions.getActions().get(0));
        printBoard(result_test.board);
    }
    public static void printBoard(int[][] board) {
        System.out.print(" ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(" "+(char) ((char) i + 97));
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+" ");
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 1){
                    System.out.print("x ");
                }else if(board[i][j] == -1){
                    System.out.print("o ");
                }else{
                    System.out.print("  ");

                }
            }
            System.out.println();
        }
    }
}
