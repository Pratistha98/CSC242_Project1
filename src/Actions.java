import java.util.ArrayList;
public class Actions {
    ArrayList<String> actions;
    State state;
    public Actions(State state){
        this.state = state;
        this.actions = new ArrayList<String>();
        for(int c=0;c<state.getBoard().length;c++){
            for(int r=0;r<state.getBoard().length;r++){
                if(state.getBoard()[c][r]*state.getActivePlayer()<0){
                    addAdjacentActions(c,r);
                }
            }
        }
    }
    private void addAdjacentActions(int c,int r) {
        for (int i = Math.max(c - 1, 0); i < Math.min(c + 2, this.state.getBoard().length); i++) {
            //System.out.println("i "+i);
            for (int j = Math.max(r - 1, 0); j < Math.min(r + 2, this.state.getBoard().length); j++) {
                //System.out.println("j "+j);
                if (this.state.getBoard()[i][j] == 0) {
                    //System.out.println("in first if i "+i);
                    //System.out.println("in first if j "+j);

                    if (checkDirection(c, r, c-i, r-j)) {
                        //System.out.println("in second if");
                        if (!this.actions.contains(Integer.toString(i) + Integer.toString(j))) {
                            this.actions.add(Integer.toString(i) + Integer.toString(j));
                        }
                    }
                }
            }
        }
    }

    private boolean checkDirection(int c,int r, int x, int y) {
        /*System.out.println("== check direction ==");
        System.out.print(" c "+c);
        System.out.print(" r "+r);
        System.out.print(" x "+x);
        System.out.println(" y "+y);*/

        if (c + x >= this.state.getBoard().length || r + y >= this.state.getBoard().length || c + x < 0 || r + y < 0) {
            return false;
        }
        if (this.state.getBoard()[x + c][y + r] == 0) {
            return false;
        }
        if (this.state.getBoard()[x + c][y + r] == this.state.getActivePlayer()) {
            return true;
        }
        return checkDirection(c + x, r + y, x, y);
    }

    public ArrayList<String> getActions(){
        return actions;
    }
}
