public class Game {

	Players human; 
	Players computer;

	public static State get_result(State s, String a) {
		State new_state = new State(s.getBoard(),s.getActivePlayer());
		int c = Integer.parseInt(String.valueOf(a.charAt(0)));
		int r = Integer.parseInt(String.valueOf(a.charAt(1)));
		System.out.println(c);
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

}

