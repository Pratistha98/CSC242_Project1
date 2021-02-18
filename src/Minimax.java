public class Minimax implements Player {

    public String get_action(State s) {
        return minimax(s);
    }

    public static String minimax(State s){
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            return "";
        }
        String action = "";
        int v = -2;
        for(int i=0; i<alist.getActions().size();i++){
            //System.out.println(alist.actions.get(i));
            //printBoard(get_result(s,alist.actions.get(i)).board);
            int holder = min_value(Game.get_result(s,alist.actions.get(i)));
            if(holder > v){
                v = holder;
                action = alist.getActions().get(i);
            }
        }
        return action;
    }

    public static int max_value(State s){
        //System.out.println("+++++++++++++++++++++");
        //printBoard(s.board);
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            return utility(s);
        }
        int v = -2;

        for(int i=0; i<alist.getActions().size();i++){
            //System.out.println(alist.actions+"action selected");
            //printBoard(get_result(s,alist.actions.get(i)).board);
            int holder = min_value(Game.get_result(s,alist.actions.get(i)));
            if(holder > v ){
                v = holder;
            }
        }
        return v;
    }
    public static int min_value(State s){
        Actions alist = new Actions(s);
        //System.out.println(alist.actions);
        if(alist.getActions().size() == 0){
            return utility(s);
        }
        int v = 2;
        for(int i=0; i<alist.getActions().size();i++){
            int holder = max_value(Game.get_result(s,alist.actions.get(i)));

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
