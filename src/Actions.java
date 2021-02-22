import java.util.ArrayList;
public class Actions {
    ArrayList<String> actions;
    State state;
    public Actions(State state){
        this.state = state;
        this.actions = new ArrayList<>();
        for(int r=0;r<state.getBoard().length;r++){
            for(int c=0;c<state.getBoard().length;c++){
                if(state.getBoard()[r][c]*state.getActivePlayer()<0){
                    addAdjacentActions(r,c);
                }
            }
        }
       //System.out.println(this.actions);

    }
    private void addAdjacentActions(int r,int c) {
        for (int i = Math.max(r - 1, 0); i < Math.min(r + 2, this.state.getBoard().length); i++) {
            for (int j = Math.max(c - 1, 0); j < Math.min(c + 2, this.state.getBoard().length); j++) {
                if (this.state.getBoard()[i][j] == 0) {
                    //System.out.println("inside!!! i = "+i+" j = "+j+" r = "+r+" c = "+c+ " x = "+(c-j)+" y = "+(r-i));

                    //System.out.println("i "+i+" j "+j+" player:"+this.state.getActivePlayer());

                    if (checkDirection(r, c, c-j, r-i)) {

                        //System.out.println("in second if");
                        if (!this.actions.contains(Integer.toString(i) + Integer.toString(j))) {
                            //System.out.println("inside inside!!! i "+i+" j "+j+" player:"+this.state.getActivePlayer());

                            this.actions.add(Integer.toString(i) + Integer.toString(j));
                        }
                    }
                }
            }
        }
    }

    private boolean checkDirection(int r,int c, int x, int y) {
        //System.out.println("r "+r+" c "+c);

        /*System.out.println("== check direction ==");
        System.out.print(" c "+c);
        System.out.print(" r "+r);
        System.out.print(" x "+x);
        System.out.println(" y "+y);*/

        if (c + x >= this.state.getBoard().length || r + y >= this.state.getBoard().length || c + x < 0 || r + y < 0) {
            return false;
        }
        if (this.state.getBoard()[r + y][c + x] == 0) {
            return false;
        }
        if (this.state.getBoard()[r + y][c + x] == this.state.getActivePlayer()) {
            return true;
        }
        return checkDirection(r + y, c + x, x, y);
    }
    public ArrayList<String> getActions(){
        return actions;
    }
}
