import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {

       /*int[][] board = new int[4][4];
        board[0][0] = 1;
        board[0][1] = 1;
        board[0][2] = 1;
        board[1][0] = -1;
        board[1][1] = 1;
        board[1][2] = 1;
        //board[1][3] = 1;
        board[2][0] = -1;
        board[2][1] = -1;
        board[2][2] = 1;
        board[3][0] = -1;
        board[3][1] = -1;

        Game.printBoard(board);
        State test_state = new State(board,1);
        Actions test_actions = new Actions(test_state);
        System.out.println(test_actions.getActions());*/
        //State result_test = Game.get_result(test_state,test_actions.getActions().get(1));
        //Game.printBoard(result_test.board);
        //Game.play_reversi_mini_max();
        State init_s = Game.generate_init_state(4);
        Player human = new Human();
        Player mini = new Minimax();
        Game.printBoard(init_s.getBoard());
        Actions test_actions_neg_1 = new Actions(init_s);
        System.out.println(test_actions_neg_1.actions);
        init_s.activePlayer = 1;
        Actions test_actions_1 = new Actions(init_s);
        System.out.println(test_actions_1.actions);
     //Game.play_game(human,mini,init_s,false);
    }
}
