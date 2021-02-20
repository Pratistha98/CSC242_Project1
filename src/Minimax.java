public class Minimax implements Player {
    public Minimax() {
    }

    public String get_action(State s) {
        return minimax(s);
    }

    public static String minimax(State s){
        Actions alist = new Actions(s);
        if(Game.terminal_test(s)){ //terminal state checker
            // TODO: should also account for 2nd player's available actions
            //System.out.println("This is some wack shoot");
            return "";
        }
        String action = "";
        double v = Double.NEGATIVE_INFINITY; //refers to utility value
        for(int i=0; i<alist.getActions().size();i++){
            //System.out.println(alist.actions.get(i));
            //printBoard(get_result(s,alist.actions.get(i)).board);
            double holder = min_value(Game.get_result(s,alist.actions.get(i)));
            if(holder > v){
                v = holder;
                action = alist.getActions().get(i);
            }
        }
        return action;
    }

    public static double max_value(State s){
        //System.out.println("+++++++++++++++++++++");
        //printBoard(s.board);
        if(Game.terminal_test(s)){
            return Game.utility(s);
        }
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            s.activePlayer *=-1;
            return min_value(s);
        }
        double v = Double.NEGATIVE_INFINITY;

        for(int i=0; i<alist.getActions().size();i++){
            //System.out.println(alist.actions+"action selected");
            //printBoard(get_result(s,alist.actions.get(i)).board);
            double holder = min_value(Game.get_result(s,alist.actions.get(i)));
            if(holder > v ){
                v = holder;
            }
        }
        return v;
    }
    public static double min_value(State s){
        if(Game.terminal_test(s)){
            return Game.utility(s);
        }
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            s.activePlayer *=-1;
            return max_value(s);
        }
        double v = Double.POSITIVE_INFINITY;
        for(int i=0; i<alist.getActions().size();i++){
            double holder = max_value(Game.get_result(s,alist.actions.get(i)));

            if(holder < v){
                v = holder;
            }
        }
        return v;
    }


}
