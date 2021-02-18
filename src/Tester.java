import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {

       /* int[][] board = new int[4][4];
        board[1][1] = 1;
        board[2][2] = 1;
        board[1][2] = -1;
        board[2][1] = 1;
        board[2][0] = 1;
        board[0][1] = -1;
        Game.printBoard(board);
        State test_state = new State(board,1);
        Actions test_actions = new Actions(test_state);
        System.out.println(test_actions.getActions());
        State result_test = Game.get_result(test_state,test_actions.getActions().get(1));
        Game.printBoard(result_test.board);*/
        Game.play_reversi_mini_max();
    }

}
