import java.util.ArrayList;

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
	public static String minimax(State s){
		Actions alist = new Actions(s);
		if(alist.getActions().size() == 0){
			return "";
		}
		String action = "";
		int v = -2;
		for(int i=0; i<alist.getActions().size();i++){
			int holder = min_value(get_result(s,alist.actions.get(i)));
			if(holder > v){
				v = holder;
				action = alist.getActions().get(i);
			}
		}
		return action;
	}

	public static int max_value(State s){
		Actions alist = new Actions(s);
		if(alist.getActions().size() == 0){
			return utility(s);
		}
		int v = -2;
		for(int i=0; i<alist.getActions().size();i++){
			int holder = min_value(get_result(s,alist.actions.get(i)));
			if(holder > v ){
				v = holder;
			}
		}
		return v;
	}
	public static int min_value(State s){
		Actions alist = new Actions(s);
		if(alist.getActions().size() == 0){
			return utility(s);
		}
		int v = 2;
		for(int i=0; i<alist.getActions().size();i++){
			int holder = max_value(get_result(s,alist.actions.get(i)));
			if(holder < v){
				v = holder;
			}
		}
		return v;
	}




	public static int utility(State s){
		int result = 0;
		for(int i =0; i<s.board.length; i++) {
			for (int j = 0; j < s.board.length; j++) {
				result += s.board[i][j];
			}
		}
		return Integer.compare(result, 0);
	}


}

