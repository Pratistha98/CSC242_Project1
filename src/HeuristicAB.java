public class HeuristicAB implements Player {
    int max_level;
    public HeuristicAB() {
        this.max_level = 10;
    }

    public String get_action(State s) {
        return heuristicAB(s);
    }

    private String heuristicAB(State s){
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
            double holder = min_value(Game.get_result(s,alist.actions.get(i)),Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,1);
            if(holder > v){
                v = holder;
                action = alist.getActions().get(i);
            }
        }
        return action;
    }

    private double max_value(State s, double a,double b, int l){
        //System.out.println("+++++++++++++++++++++");
        //printBoard(s.board);
        if(l >= this.max_level){
            return heuristic(s);
        }
        if(Game.terminal_test(s)){
            return Game.utility(s);
        }
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            s.activePlayer *=-1;
            return min_value(s,a,b,l+1);
        }
        double v = Double.NEGATIVE_INFINITY;

        for(int i=0; i<alist.getActions().size();i++){
            //System.out.println(alist.actions+"action selected");
            //printBoard(get_result(s,alist.actions.get(i)).board);
            v  = Math.max(v,min_value(Game.get_result(s,alist.actions.get(i)),a,b,l+1));
            if(v >= a){
                return v;
            }
            b = Math.max(a,v);
        }
        return v;
    }
    private double min_value(State s, double a, double b, int l){
        if(l >= this.max_level){
            return heuristic(s);
        }

        if(Game.terminal_test(s)){
            return Game.utility(s);
        }
        Actions alist = new Actions(s);
        if(alist.getActions().size() == 0){
            s.activePlayer *=-1;
            return max_value(s,a,b,l+1);
        }
        double v = Double.POSITIVE_INFINITY;
        for(int i=0; i<alist.getActions().size();i++){
            v  = Math.min(v,max_value(Game.get_result(s,alist.actions.get(i)),a,b,l+1));

            if(v <= a){
                return v;
            }
            b = Math.min(b,v);
        }
        return v;
    }
    private double heuristic(State s){
        double result = 0;
        int l = s.board.length;
        for(int i =0; i<l; i++) {
            for (int j = 0; j < l; j++) {
                result += s.board[i][j];
            }
        }
        result += s.board[0][0] * l;
        result += s.board[l-1][0] * l;
        result += s.board[0][l-1] * l;
        result += s.board[l-1][l-1] * l;




        return (1 / (1 + Math.exp(-result)))-.5;
    }


}