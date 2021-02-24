public class AlphaBeta implements Player {
    public AlphaBeta() {
    }

    public String get_action(State s) {
        return minimax_ab(s);
    }

    private static String minimax_ab(State s){
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
            double holder = min_value(Game.get_result(s,alist.actions.get(i)),Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            if(holder > v){
                v = holder;
                action = alist.getActions().get(i);
            }
        }
        return action;
    }

    private static double max_value(State s, double a,double b){
        //System.out.println("+++++++++++++++++++++");
        //printBoard(s.board);
        if(Game.terminal_test(s)){
            return Game.utility(s);
        }
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            s.activePlayer *=-1;
            return min_value(s,a,b);
        }
        double v = Double.NEGATIVE_INFINITY; //TODO: WAIT, isn't it supposed to be POSITIVE INFINITY?

        for(int i=0; i<alist.getActions().size();i++){
            //System.out.println(alist.actions+"action selected");
            //printBoard(get_result(s,alist.actions.get(i)).board);
            v  = Math.max(v,min_value(Game.get_result(s,alist.actions.get(i)),a,b));
            if(v >= a){
                return v;
            }
            b = Math.max(a,v);
        }
        return v;
    }
    private static double min_value(State s, double a, double b){
        if(Game.terminal_test(s)){
            return Game.utility(s);
        }
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            s.activePlayer *=-1;
            return max_value(s,a,b);
        }
        
        double v = Double.POSITIVE_INFINITY;

        for(int i=0; i<alist.getActions().size();i++){
            v  = Math.min(v,max_value(Game.get_result(s,alist.actions.get(i)),a,b));

            if(v <= a){
                return v;
            }
            b = Math.min(b,v);
        }
        return v;
    }


}