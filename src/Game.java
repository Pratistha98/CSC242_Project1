import java.util.Scanner;

public class Game {

	public static State generate_init_state(int size){
		int[][] board = new int[size][size];
		board[(size/2) -1][(size/2) -1] = 1;
		board[(size/2)][(size/2)] = 1;
		board[(size/2) -1][(size/2)] = -1;
		board[(size/2)][(size/2) -1] = -1;
		return new State(board,-1);
	}

	public static void play_game(Player p1, Player p2, State init_state) {
		String action;
		Actions alist = new Actions(init_state);
		State s = init_state;
		printBoard(s.getBoard());
		System.out.println("Dark's Turn");
		while (alist.getActions().size() > 0) {
			if (s.activePlayer == -1) {
				action = p1.get_action(s);
				System.out.println(action); // DEBUG
			} else {
				action = p2.get_action(s);
				System.out.println(action); // DEBUG
			}
			if (alist.getActions().contains(action)){
				System.out.println("Move Selected: "+action);
				s = get_result(s,action);
				printBoard(s.getBoard());
				alist = new Actions(s);
				if(s.activePlayer == -1){
					System.out.println("Dark's Turn");
				}else{
					System.out.println("Light's Turn");
				}
			}else{
				System.out.println("Chosen action: "+action+" is not a valid move");
			}
		}
		System.out.println("Game Over: player "+utility(s)+" wins");
	}
	// EDITED FOR MAIN
	public void play_reversi_mini_max1(int size, int human_player){
		int[][] board = new int[size][size];
		board[(size/2) -1][(size/2) -1] = 1;
		board[(size/2)][(size/2)] = 1;
		board[(size/2) -1][(size/2)] = -1;
		board[(size/2)][(size/2) -1] = -1;

		Game game = new Game();
		printBoard(board);
		State init_state = new State(board,human_player);
		Actions alist = new Actions(init_state);
		Scanner scn = new Scanner(System.in);  // Create a Scanner object
		while(alist.getActions().size() > 0){
			printBoard(init_state.board);
			//opposite player 
			if(init_state.activePlayer == human_player * -1){
				if (human_player == -1){
					System.out.println("Dark's Turn");
				}
				else {
					System.out.println("Light's Turn");
				}
				System.out.print("Enter Row: ");
				//String input = scn.nextLine();
				int r = scn.nextInt();
				//char c = (char)((int)input.charAt(0))-97;
				System.out.print("Enter Column: ");
				int c = scn.nextInt();
				init_state = get_result(init_state,Integer.toString(r) + Integer.toString(c));
			}else{
				System.out.println("\n");
				init_state = get_result(init_state,Minimax.minimax(init_state));
			}
		}
		System.out.println("player "+utility(init_state)+" wins");


	}

	//MAIN ONE
	public static void play_reversi_mini_max(){
		int[][] board = new int[4][4];
		board[1][1] = 1;
		board[2][2] = 1;
		board[1][2] = -1;
		board[2][1] = -1;
		State init_state = new State(board,1);
		Scanner scn = new Scanner(System.in);  // Create a Scanner object

		Actions alist = new Actions(init_state);
		while(alist.getActions().size() > 0){
			printBoard(init_state.board);
			if(init_state.activePlayer == -1){
				System.out.print("enter column");
				//String input = scn.nextLine();
				int c = scn.nextInt();
				//char c = (char)((int)input.charAt(0))-97;

				System.out.print("enter row");
				int r = scn.nextInt();
				init_state = get_result(init_state,Integer.toString(r) + Integer.toString(c));
			}else{
				init_state = get_result(init_state,Minimax.minimax(init_state));
			}
		}
		System.out.println("player "+utility(init_state)+"wins");


	}

	public static State get_result(State s, String a) {
		State new_state = new State(s.getBoard(),s.getActivePlayer());
		int c = Integer.parseInt(String.valueOf(a.charAt(0)));
		int r = Integer.parseInt(String.valueOf(a.charAt(1)));
		//System.out.println(c);
		for (int i = Math.max(c - 1, 0); i < Math.min(c + 2, s.getBoard().length); i++) {
			for (int j = Math.max(r - 1, 0); j < Math.min(r + 2, s.getBoard().length); j++) {
				if (s.getBoard()[i][j]*s.getActivePlayer() < 0) {
					updateDirection(c, r, i - c, j - r,new_state);
				}
			}
		}
		new_state.board[c][r] = new_state.activePlayer;
		new_state.activePlayer = new_state.getActivePlayer()*-1;
		return new_state;

	}


	private static boolean updateDirection(int c,int r, int x, int y,State s) {
		if (c + x >= s.getBoard().length || r + y >= s.getBoard().length || c + x < 0 || r + y < 0) {
			return false;
		}
		if (s.getBoard()[x + c][y + r] == 0) {
			return false;
		}
		if (s.getBoard()[x + c][y + r] == s.getActivePlayer()) {
			s.board[x + c][y + r] = s.getActivePlayer();
			return true;
		}
		if(updateDirection(c + x, r + y, x, y,s)){
			s.board[x + c][y + r] = s.getActivePlayer();
			return true;
		}
		return false;
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
					System.out.print("o ");
				}else if(board[i][j] == -1){
					System.out.print("x ");
				}else{
					System.out.print("  ");

				}
			}
			System.out.println();
		}
	}


	public static double utility(State s){
		double result = 0;
		for(int i =0; i<s.board.length; i++) {
			for (int j = 0; j < s.board.length; j++) {
				result += s.board[i][j];
			}
		}
		return result;
	}
	public static boolean terminal_test(State s){
		boolean active_no_moves = new Actions(s).getActions().size() == 0;
		s.activePlayer *=-1;
		boolean next_no_moves = new Actions(s).getActions().size() == 0;
		s.activePlayer *=-1;
		return active_no_moves && next_no_moves;
	}
}

